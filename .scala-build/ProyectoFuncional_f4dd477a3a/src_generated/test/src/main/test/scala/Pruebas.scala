package src.main.test.scala


final class Pruebas$_ {
def args = Pruebas_sc.args$
def scriptPath = """src/main/test/scala/Pruebas.sc"""
/*<script>*/
import main.scala.Comete._

val pi_max = Vector(0.5, 0.0, 0.0, 0.0, 0.5)
val pi_min = Vector(0.0, 0.0, 1.0, 0.0, 0.0)
val pi_der = Vector(0.4, 0.0, 0.0, 0.0, 0.6)
val pi_izq = Vector(0.6, 0.0, 0.0, 0.0, 0.4)
val pi_int1 = Vector(0.0, 0.5, 0.0, 0.5, 0.0)
val pi_int2 = Vector(0.25, 0.0, 0.5, 0.0, 0.25)
val pi_int3 = Vector(0.25, 0.25, 0.0, 0.25, 0.25)
val pi_cons_centro = pi_min
val pi_cons_der = Vector(0.0, 0.0, 0.0, 0.0, 1.0)
val pi_cons_izq = Vector(1.0, 0.0, 0.0, 0.0, 0.0)

val likert5 = Vector(0.0, 0.25, 0.5, 0.75, 1.0)

val cmt1 = rhoCMT_Gen(1.2, 1.2)

cmt1 (pi_max, likert5)
cmt1 (pi_min ,likert5)
cmt1 (pi_der ,likert5)
cmt1 (pi_izq ,likert5)
cmt1 (pi_int1, likert5)
cmt1 (pi_int2, likert5)
cmt1 (pi_int3, likert5)
cmt1 (pi_cons_centro, likert5)
cmt1 (pi_cons_der, likert5)
cmt1 (pi_cons_izq, likert5)

val cmt1_norm = normalizar(cmt1)

cmt1_norm (pi_max, likert5)
cmt1_norm (pi_min ,likert5)
cmt1_norm (pi_der ,likert5)
cmt1_norm (pi_izq ,likert5)
cmt1_norm (pi_int1, likert5)
cmt1_norm (pi_int2, likert5)
cmt1_norm (pi_int3, likert5)
cmt1_norm (pi_cons_centro, likert5)
cmt1_norm (pi_cons_der, likert5)
cmt1_norm (pi_cons_izq, likert5)

//

def i1(nags: Int): SpecificWeightedGraph = {
  ((i: Int, j: Int) =>
    if (i == j) 1.0
    else if (i < j) 1.0 / (j - i).toDouble
    else 0.0, nags)
}

def i2(nags: Int): SpecificWeightedGraph = {
  ((i: Int, j: Int) =>
    if (i == j) 1.0
    else if (i < j) (j - i).toDouble / nags.toDouble
    else (nags - (i - j)).toDouble / nags.toDouble, nags)
}

val i1_10 = i1(10)
val i2_10 = i2(10)
val i1_20 = i1(20)
val i2_20 = i2(20)

for {
  b <- simulate(confBiasUpdate, i1_10, sbu_10, 2)
} yield (b, rho1(b, dist1))

for {
  b <- simulate(confBiasUpdate, i1_10, sbm_10, 2)
} yield (b, rho1(b, dist1))

/*</script>*/ /*<generated>*//*</generated>*/
}

object Pruebas_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new Pruebas$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export Pruebas_sc.script as `Pruebas`

