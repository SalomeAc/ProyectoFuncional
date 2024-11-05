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
    // Devuelve el punto p en [min, max] tal que f(p) es mínimo
    // Suponiendo que f es cóncava
    // Si max - min < prec, devuelve el punto medio de [min, max]

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
