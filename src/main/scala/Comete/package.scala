package main.scala

import scala.math._

package object Comete {

  type DistributionValues = Vector[Double]
  // Tipo para los valores, reales ordenados entre 0 y 1,
  // incluidos 0 y 1, de una distribución

  type Frequency = Vector[Double]
  // Pi_k es una frecuencia de longitud k
  // si Pi_k.length = k, 0 <= Pi_k(i) <= 1, 0 <= i <= k − 1
  // y Pi_k.sum == 1

  type Distribution = (Frequency, DistributionValues)
  // (Pi, dv) es una distribución si pi es una Frecuencia
  // y dv son los valores de distribución y pi y dv son
  // de la misma longitud

  type MedidaPol = Distribution => Double

  def min_p(f: Double => Double, min: Double, max: Double, prec: Double): Double = {
         if ((max - min) < prec) (min + max) / 2
         else {
               val p1 = min + (max - min) / 3
               val p2 = max - (max - min) / 3
            if (f(p1) < f(p2)) min_p(f, min, p2, prec)
             else min_p(f, p1, max, prec)
         }
   }

  def rhoCMT_Gen(alpha: Double, beta: Double): MedidaPol = {
    // Dados alpha y beta, devuelve la función que calcula la medida
    // cometa parametrizada en alpha y beta
    (Distribution: (Frequency, DistributionValues)) => {
      val (pi, y) = Distribution
      val com = pi zip y

      def aux(p: Double): Double = {
        val Pcmt = (for {
          i <- com
        } yield (math.pow(i._1, alpha) * math.pow(math.abs(i._2 - p), beta))).sum
        if (Pcmt < 0.01) 0.0 else Pcmt
      }

      val min = min_p(aux, 0.0, 1.0, 0.01)
      ((aux(min) * 1000.0).round) / 1000.0
    }
  }

  def normalizar(m: MedidaPol): MedidaPol = {
    // Recibe una medida de polarización, y devuelve la correspondiente medida que la calcula normalizada

    def construirPeorCaso(dv: DistributionValues): Distribution = {
      val frecuencias = Vector(0.5) ++ Vector.fill(dv.length - 2)(0.0) ++ Vector(0.5)
      (frecuencias, dv) // Retornar la distribución construida
    }

    (distribucion: Distribution) => {
      val peorCasoDistribucion = construirPeorCaso(distribucion._2)
      val peorCaso = m(peorCasoDistribucion) // Calcular el peor caso
      val resultadoNormalizado = m(distribucion) / peorCaso
      ((resultadoNormalizado * 1000.0).round) / 1000.0
    }
  }

}

