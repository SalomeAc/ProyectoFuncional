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

  type AgentsPolMeasure =
    (SpecificBelief, DistributionValues) => Double
  // Si rho: AgentsPolMeasure y sb: SpecificBelief
  // y d: DistributionValues,
  // rho(sb, d) es la polarización de los agentes
  // de acuerdo a esa medida

  def rho(alpha: Double, beta: Double): AgentsPolMeasure = {
    // rho es la medida de polarización de agentes basada
    // en comete

  }

  // Tipos para modelar la evolución de la opinión en una red
  type WeightedGraph = (Int, Int) => Double
  type SpecificWeightedGraph = (WeightedGraph, Int)
  type GenericWeightedGraph =
    Int => SpecificWeightedGraph
  type FunctionUpdate =
    (SpecificBelief, SpecificWeightedGraph) => SpecificBelief

  def confBiasUpdate(sb: SpecificBelief, swg: SpecificWeightedGraph): SpecificBelief = {

  }

  def showWeightedGraph(swg: SpecificWeightedGraph): IndexedSeq[IndexedSeq[Double]] = {
    val (funcionInfluencia, numAgentes) = swg // swg es una tupla

    IndexedSeq.tabulate(numAgentes, numAgentes) { (i, j) => // Construye una matriz de tamaño numAgentes x numAgentes
      funcionInfluencia(i, j) // Valor de la influencia del agente i sobre el j
    }
  }

  def simulate(fu: FunctionUpdate, swg: SpecificWeightedGraph, b0: SpecificBelief, t: Int): IndexedSeq[SpecificBelief] = {

  }

  // Versiones paralelas
  def rhoPar(alpha: Double, beta: Double): AgentsPolMeasure = {
    // rho es la medida de polarización de agentes basada
    // en comete

  }

  def confBiasUpdatePar(sb: SpecificBelief, swg: SpecificWeightedGraph): SpecificBelief = {

  }
}
