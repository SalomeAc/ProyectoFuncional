package src.main.test.scala


final class Pruebas$_ {
def args = Pruebas_sc.args$
def scriptPath = """src/main/test/scala/Pruebas.sc"""
/*<script>*/
import main.scala.Comete._
import main.scala.Benchmark._
import main.scala.Opinion._

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

// Pruebas de rhoCMT_Gen

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

// Pruebas de normalizar

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

// Build uniform belief state.
def uniformBelief(nags: Int): SpecificBelief = {
  Vector.tabulate(nags)((i: Int) => (i + 1).toDouble / nags.toDouble)
}

// Build mildly polarized belief state, in which
// half of agents has belief decreasing from 0.25, and
// half has belief increasing from 0.75, all by the given step.
def midlyBelief(nags: Int): SpecificBelief = {
  val middle = nags / 2
  Vector.tabulate(nags)((i: Int) =>
    if (i < middle) math.max(0.25 - 0.01 * (middle - i - 1), 0)
    else math.min(0.75 - 0.01 * (middle - i), 1)
  )
}

// Build extreme polarized belief state, in which half
// of the agents has belief 0, and half has belief 1.
def allExtremeBelief(nags: Int): SpecificBelief = {
  val middle = nags / 2
  Vector.tabulate(nags)((i: Int) =>
    if (i < middle) 0.0 else 1.0
  )
}

// Build three-pole belief state, in which each
// one third of the agents has belief 0, one third has belief 0.5,
// and one third has belief 1.
def allTripleBelief(nags: Int): SpecificBelief = {
  val oneThird = nags / 3
  val twoThird = (nags / 3) * 2
  Vector.tabulate(nags)((i: Int) =>
    if (i < oneThird) 0.0
    else if (i >= twoThird) 1.0
    else 0.5
  )
}

// Builds consensus belief state, in which each
// all agents have same belief.
def consensusBelief(b: Double)(nags: Int): SpecificBelief = {
  Vector.tabulate(nags)((_: Int) => b)
}

val sb_ext = allExtremeBelief(100)
val sb_cons = consensusBelief(0.2)(100)
val sb_unif = uniformBelief(100)
val sb_triple = allTripleBelief(100)
val sb_midly = midlyBelief(100)

// Pruebas de rho

val rho1 = rho(1.2, 1.2)
val rho2 = rho(2.0, 1.0)
val dist1 = Vector(0.0, 0.25, 0.50, 0.75, 1.0)
val dist2 = Vector(0.0, 0.2, 0.4, 0.6, 0.8, 1.0)

rho1(sb_ext, dist1)
rho2(sb_ext, dist1)
rho1(sb_ext, dist2)
rho2(sb_ext, dist2)

rho1(sb_cons, dist1)
rho2(sb_cons, dist1)
rho1(sb_cons, dist2)
rho2(sb_cons, dist2)

rho1(sb_unif, dist1)
rho2(sb_unif, dist1)
rho1(sb_unif, dist2)
rho2(sb_unif, dist2)

rho1(sb_triple, dist1)
rho2(sb_triple, dist1)
rho1(sb_triple, dist2)
rho2(sb_triple, dist2)

rho1(sb_midly, dist1)
rho2(sb_midly, dist1)
rho1(sb_midly, dist2)
rho2(sb_midly, dist2)

val i1_10 = i1(10)
val i2_10 = i2(10)
val i1_20 = i1(20)
val i2_20 = i2(20)

// Pruebas de showWeightedGraph

showWeightedGraph(i1_10)
showWeightedGraph(i1_20)
showWeightedGraph(i2_10)
showWeightedGraph(i2_20)

val sbu_10 = uniformBelief(10)
confBiasUpdate(sbu_10, i1_10)
rho1(sbu_10, dist1)
rho1(confBiasUpdate(sbu_10, i1_10), dist1)

val sbm_10 = midlyBelief(10)
confBiasUpdate(sbm_10, i1_10)
rho1(sbm_10, dist1)
rho1(confBiasUpdate(sbm_10, i1_10), dist1)

// Pruebas de simulate

for {
  b <- simulate(confBiasUpdate, i1_10, sbu_10, 2)
} yield (b, rho1(b, dist1))

for {
  b <- simulate(confBiasUpdate, i1_10, sbm_10, 2)
} yield (b, rho1(b, dist1))

// Comparar

val likert5 = Vector(0.0, 0.25, 0.5, 0.75, 1.0)
val sbms = for {
  n <- 2 until 16
  nags = math.pow(2, n).toInt
} yield midlyBelief(nags)

val polSec = rho(1.2, 1.2)
val polPar = rhoPar(1.2, 1.2)

val cmp1 = compararMedidasPol(sbms, likert5, polSec, polPar)

cmp1.map(t => t._6)

val i1_32768 = i1(32768)
val i2_32768 = i2(32768)
compararFuncionesAct(sbms.take(sbms.length / 2), i2_32768, confBiasUpdate, confBiasUpdatePar)

val sbms = for {
  n <-2 until 16
  nags = math.pow(2,n).toInt
} yield midlyBelief(nags)

val sbes = for {
  n <-2 until 16
  nags = math.pow(2,n).toInt
} yield allExtremeBelief(nags)

val sbts = for {
  n <-2 until 16
  nags = math.pow(2,n).toInt
} yield allTripleBelief(nags)

//val evolsSec = for {
//  i <- 0 until sbms.length
//} yield simEvolucion(Seq(sbms(i), sbes(i), sbts(i)),
//  i2_32768, 10, polSec, confBiasUpdate, likert5,
//  "Simulacion_Secuencial_" ++ i.toString ++ "-" ++ sbms(i).length.toString)

val evolsSec = simEvolucion(Seq(sbms(12), sbes(12), sbts(12)),
  i2_32768, 10, polSec, confBiasUpdate, likert5,
  "Simulacion_Secuencial_" ++ "12" ++ "-" ++ sbms(12).length.toString
)

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

