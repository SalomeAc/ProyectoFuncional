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

val sb_ext = allExtremeBelief(100)
val sb_cons = consensusBelief(0.2)(100)
val sb_unif = uniformBelief(100)
val sb_triple = allTripleBelief(100)
val sb_midly = midlyBelief(100)


val sb_ext32768 = allExtremeBelief(32768)
val sb_cons32768 = consensusBelief(0.2)(32768)
val sb_unif32768 = uniformBelief(32768)
val sb_triple32768 = allTripleBelief(32768)
val sb_midly32768 = midlyBelief(32768)


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

////// pruebas para 32768

rho1(sb_ext32768, dist1)
rho2(sb_ext32768, dist1)
rho1(sb_ext32768, dist2)
rho2(sb_ext32768, dist2)

rho1(sb_cons32768, dist1)
rho2(sb_cons32768, dist1)
rho1(sb_cons32768, dist2)
rho2(sb_cons32768, dist2)

rho1(sb_unif32768, dist1)
rho2(sb_unif32768, dist1)
rho1(sb_unif32768, dist2)
rho2(sb_unif32768, dist2)

rho1(sb_triple32768, dist1)
rho2(sb_triple32768, dist1)
rho1(sb_triple32768, dist2)
rho2(sb_triple32768, dist2)

rho1(sb_midly32768, dist1)
rho2(sb_midly32768, dist1)
rho1(sb_midly32768, dist2)
rho2(sb_midly32768, dist2)

// pruebas para 64

val sb_ext64 = allExtremeBelief(64)
val sb_cons64 = consensusBelief(0.2)(64)
val sb_unif64 = uniformBelief(64)
val sb_triple64 = allTripleBelief(64)
val sb_midly64 = midlyBelief(64)

rho1(sb_ext64, dist1)
rho2(sb_ext64, dist1)
rho1(sb_ext64, dist2)
rho2(sb_ext64, dist2)

rho1(sb_cons64, dist1)
rho2(sb_cons64, dist1)
rho1(sb_cons64, dist2)
rho2(sb_cons64, dist2)

rho1(sb_unif64, dist1)
rho2(sb_unif64, dist1)
rho1(sb_unif64, dist2)
rho2(sb_unif64, dist2)

rho1(sb_triple64, dist1)
rho2(sb_triple64, dist1)
rho1(sb_triple64, dist2)
rho2(sb_triple64, dist2)

rho1(sb_midly64, dist1)
rho2(sb_midly64, dist1)
rho1(sb_midly64, dist2)
rho2(sb_midly64, dist2)

// pruebas para 2048

val sb_ext2048 = allExtremeBelief(2048)
val sb_cons2048 = consensusBelief(0.2)(2048)
val sb_unif2048 = uniformBelief(2048)
val sb_triple2048 = allTripleBelief(2048)
val sb_midly2048 = midlyBelief(2048)

rho1(sb_ext2048, dist1)
rho2(sb_ext2048, dist1)
rho1(sb_ext2048, dist2)
rho2(sb_ext2048, dist2)

rho1(sb_cons2048, dist1)
rho2(sb_cons2048, dist1)
rho1(sb_cons2048, dist2)
rho2(sb_cons2048, dist2)

rho1(sb_unif2048, dist1)
rho2(sb_unif2048, dist1)
rho1(sb_unif2048, dist2)
rho2(sb_unif2048, dist2)

rho1(sb_triple2048, dist1)
rho2(sb_triple2048, dist1)
rho1(sb_triple2048, dist2)
rho2(sb_triple2048, dist2)

rho1(sb_midly2048, dist1)
rho2(sb_midly2048, dist1)
rho1(sb_midly2048, dist2)
rho2(sb_midly2048, dist2)


////
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


// pruebas de confBiasUpdate 2048

val i1_2048 = i1(2048)

val sbu_2048 = uniformBelief(2048)
confBiasUpdate(sbu_2048, i1_2048)

val sbm_2048 = midlyBelief(2048)
confBiasUpdate(sbm_2048, i1_2048)

// pruebas de confBiasUpdate 2048

val i1_2048 = i1(2048)

val sbu_2048 = uniformBelief(2048)
confBiasUpdate(sbu_2048, i1_2048)

val sbm_2048 = midlyBelief(2048)
confBiasUpdate(sbm_2048, i1_2048)


// pruebas de confBiasUpdate 8192

val i1_8192 = i1(8192)

val sbu_8192 = uniformBelief(8192)
confBiasUpdate(sbu_8192, i1_8192)

val sbm_8192 = midlyBelief(8192)
confBiasUpdate(sbm_8192, i1_8192)

// pruebas de confBiasUpdate 64

val i1_64 = i1(64)

val sbu_64 = uniformBelief(64)
confBiasUpdate(sbu_64, i1_64)

val sbm_64 = midlyBelief(64)
confBiasUpdate(sbm_64, i1_64)

// pruebas de confBiasUpdate 32768

val i1_32768 = i1(32768)
//
val sbu_32768 = uniformBelief(32768)
//confBiasUpdate(sbu_32768, i1_32768)
//
//val sbm_32768 = midlyBelief(32768)
//confBiasUpdate(sbm_32768, i1_32768)


// Pruebas de simulate

//for {
//  b <- simulate(confBiasUpdate, i1_32768, sbu_32768, 2)
//} yield (b, rho1(b, dist1))
//
//for {
//  b <- simulate(confBiasUpdate, i1_32768, sbm_32768, 2)
//} yield (b, rho1(b, dist1))

// Pruebas de simulate 64

for {
  b <- simulate(confBiasUpdate, i1_64, sbu_64, 2)
} yield (b, rho1(b, dist1))

for {
  b <- simulate(confBiasUpdate, i1_64, sbm_64, 2)
} yield (b, rho1(b, dist1))

// Pruebas de simulate 2048

for {
  b <- simulate(confBiasUpdate, i1_2048, sbu_2048, 2)
} yield (b, rho1(b, dist1))

for {
  b <- simulate(confBiasUpdate, i1_2048, sbm_2048, 2)
} yield (b, rho1(b, dist1))


// Pruebas de simulate 8192

for {
  b <- simulate(confBiasUpdate, i1_8192, sbu_8192, 2)
} yield (b, rho1(b, dist1))

for {
  b <- simulate(confBiasUpdate, i1_8192, sbm_8192, 2)
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

cmp1.map(t => t._6) // aceleración
cmp1.map(t => t._4) // tiempo secuencial
cmp1.map(t => t._5) // tiempo concurrente
cmp1.map(t => t._2) // medida de polarización secuencial
cmp1.map(t => t._3) // medida de polarización concurrente



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
//
//val evolsSec = for {
//  i <- 0 until sbms.length
//} yield simEvolucion(Seq(sbms(i), sbes(i), sbts(i)),
//  i2_32768, 10, polSec, confBiasUpdate, likert5,
//  "Simulacion_Secuencial_" ++ i.toString ++ "-" ++ sbms(i).length.toString)
//
//val evolsPar = for {
//  i <- 0 until sbms.length
//} yield simEvolucion(Seq(sbms(i),sbes(i),sbts(i)),
//  i2_32768,10,polPar,confBiasUpdatePar,likert5,
//  "Simulación Paralela " ++ i.toString ++ "-"
//    ++ sbms(i).length.toString)
