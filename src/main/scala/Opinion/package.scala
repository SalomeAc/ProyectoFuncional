package main.scala

import Comete._
import common._
import scala.collection.parallel.CollectionConverters._

package object Opinion {

  // Si n es el número de agentes, estos se identifican
  // con los enteros entre 0 y n-1
  // O sea, el conjunto de agentes A es
  // implícitamente el conjunto {0,1,2,...,n-1}

  // Si b:BeliefConf, para cada i en Int, b[i] es un número
  // entre 0 y 1 que indica cuánto crece el agente i en
  // la veracidad de la proposición p
  // Si existe i: b(i) < 0 o b(i) > 1, b está mal definida

  type SpecificBelief = Vector[Double]
  // Si b:SpecificBelief, para cada i en Int, b[i] es un
  // número entre 0 y 1 que indica cuánto crece el
  // agente i en la veracidad de la proposición p
  // El número de agentes es b.length
  // Si existe i : b(i) < 0 o b(i) > 1, b está mal definida.
  // Para i en Int \ A, b(i) no tiene sentido

  type GenericBeliefConf = Int => SpecificBelief
  // si gb: GenericBelief, entonces gb(n) = b tal que
  // b: SpecificBelief

  type AgentsPolMeasure = (SpecificBelief, DistributionValues) => Double
  // Si rho: AgentsPolMeasure y sb: SpecificBelief
  // y d: DistributionValues,
  // rho(sb, d) es la polarización de los agentes
  // de acuerdo a esa medida

  def rho(alpha: Double, beta: Double): AgentsPolMeasure = {
    // rho es la medida de polarización de agentes basada en comete

    (specificBelief: SpecificBelief, distributionValues: DistributionValues) => {
      val numAgents = specificBelief.length
      val k = distributionValues.length

      // Creación de intervalos considerando el primer y último elemento
      val firstInterval = (0.0, (distributionValues(1) + distributionValues(0)) / 2)
      val middleIntervals = (1 until k - 1).map(i =>
        ((distributionValues(i) + distributionValues(i - 1)) / 2,
          (distributionValues(i) + distributionValues(i + 1)) / 2))
      val lastInterval = ((distributionValues(k - 2) + distributionValues(k - 1)) / 2, 1.0)

      val intervals = firstInterval +: middleIntervals :+ lastInterval

      // Clasificación de agentes en intervalos
      val classifiedAgents = specificBelief.map { belief =>
        intervals.indexWhere { case (start, end) => start <= belief && belief < end } match {
          case -1 => k - 1  // Asigna al último intervalo si no hay coincidencia
          case idx => idx
        }
      }
      // Cálculo de frecuencias relativas por intervalo
      val frequency = intervals.indices.map(i =>
        classifiedAgents.count(_ == i) / numAgents.toDouble
      ).toVector

      // Calcula la medida de polarización con normalización
      val rhoAux = rhoCMT_Gen(alpha, beta)
      val normalized = normalizar(rhoAux)

      // Calcula la medida de polarización normalizada
      normalized((frequency, distributionValues))
      }
  }

  // Tipos para modelar la evolución de la opinión en una red
  type WeightedGraph = (Int, Int) => Double
  type SpecificWeightedGraph = (WeightedGraph, Int)
  type GenericWeightedGraph =
    Int => SpecificWeightedGraph
  type FunctionUpdate =
    (SpecificBelief, SpecificWeightedGraph) => SpecificBelief

  def confBiasUpdate(sb: SpecificBelief, swg: SpecificWeightedGraph): SpecificBelief = {
    val Ai =
      (for {
        i <- 0 until sb.length
      }yield
        (for {
          j <- 0 until sb.length
          if swg._1(j, i) > 0
        } yield j)).toVector
    
    (for {
      i <- 0 until sb.length
    }yield sb(i) +
      (for{
        j <- Ai(i)
      }yield ((1-math.abs(sb(j)-sb(i)))*(swg._1(j,i))*(sb(j)-sb(i)))/Ai(i).length).sum).toVector
  }

  def showWeightedGraph(swg: SpecificWeightedGraph): IndexedSeq[IndexedSeq[Double]] = {
    val (funcionInfluencia, numAgentes) = swg // swg es una tupla

    IndexedSeq.tabulate(numAgentes, numAgentes) { (i, j) => // Construye una matriz de tamaño numAgentes x numAgentes
      funcionInfluencia(i, j) // Valor de la influencia del agente i sobre el j
    }
  }

  def simulate(fu: FunctionUpdate, swg: SpecificWeightedGraph, b0: SpecificBelief, t: Int): IndexedSeq[SpecificBelief] = {
    // Devuelve la secuencia de creencias específicas por cada t
    def iterar(paso: Int, creencias: SpecificBelief, acumulador: IndexedSeq[SpecificBelief]): IndexedSeq[SpecificBelief] = {
      if (paso >= t) acumulador
      else {
        // Calcular las nuevas creencias para el siguiente paso
        val nuevasCreencias = fu(creencias, swg)
        // Llamada recursiva con paso + 1, las nuevas creencias, y el acumulador actualizado
        iterar(paso + 1, nuevasCreencias, acumulador :+ nuevasCreencias)
      }
    }

    iterar(0, b0, IndexedSeq(b0))
  }

  // Versiones paralelas
  def rhoPar(alpha: Double, beta: Double): AgentsPolMeasure = {
    // rho es la medida de polarización de agentes basada
    // en comete
    (specificBelief: SpecificBelief, distributionValues: DistributionValues) => {
      val numAgents = specificBelief.length
      val k = distributionValues.length

      val intervalsTask = task {
        (0 until k).map {
          case 0 => (0.0, (distributionValues(1) + distributionValues(0)) / 2)
          case i if i == k - 1 => ((distributionValues(k - 2) + distributionValues(k - 1)) / 2, 1.0)
          case i => ((distributionValues(i) + distributionValues(i - 1)) / 2,
            (distributionValues(i) + distributionValues(i + 1)) / 2)
        }.toVector
      }

      val classifiedAgentsTask = task {
        specificBelief.map { belief =>
          intervalsTask.join().indexWhere { case (start, end) =>
            start <= belief && belief < end
          } match {
            case -1 => k - 1  // Asigna al último intervalo si no hay coincidencia
            case idx => idx
          }
        }.toVector
      }

      val frequencyTask = task {
        val classifiedAgents = classifiedAgentsTask.join()
        (0 until k).map(i =>
          classifiedAgents.count(_ == i) / numAgents.toDouble
        ).toVector
      }

      val rhoAux = rhoCMT_Gen(alpha, beta)
      val normalized = normalizar(rhoAux)

      normalized((frequencyTask.join(), distributionValues))
    }
}

  def confBiasUpdatePar(sb: SpecificBelief, swg: SpecificWeightedGraph): SpecificBelief = {
    val Ai = (0 until sb.length).map { i =>
      task {
        (0 until sb.length).collect {case j if swg._1(j, i) > 0 => j}.toVector
      }
    }.toVector
      
    val calcularActualizacion = (0 until sb.length).map { i =>
      task {
        sb(i) + Ai(i).join().map { j => ((1 - math.abs(sb(j) - sb(i))) * (swg._1(j, i)) * (sb(j) - sb(i))) / Ai(i).join().length}.sum
      }
    }.toVector
      
    calcularActualizacion.map(_.join()).toVector
  }
  
}
