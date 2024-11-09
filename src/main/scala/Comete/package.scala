package main.scala

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

  type PolMeasure = Distribution => Double

  def min_p(f: Double => Double, min: Double, max: Double, prec: Double): Double = {
         if ((max - min) < prec) (min + max) / 2
         else {
               val p1 = min + (max - min) / 3
               val p2 = max - (max - min) / 3
           } if (f(p1) < f(p2)) min_p(f, min, p2, prec)
             else min_p(f, p1, max, prec)
         }
   }

  def rhoCMT_Gen(alpha: Double, beta: Double): PolMeasure = {
    // Dados alpha y beta, devuelve la función que calcula la medida
    // cometa parametrizada en alpha y beta

  }

  def normalizar(m: PolMeasure): PolMeasure = {
    // Recibe una medida de polarización, y devuelve la
    // correspondiente medida que la calcula normalizada

  }


}
