Archivos entregados:

Carpeta Comete: contiene un archivo package.scala que implementa las funciones min_p, rhoCMT_Gen y normalizar,
utilizadas como base para el análisis de distribuciones y medidas de polarización.

Carpeta Opinion: contiene un archivo package.scala que implementa las funciones rho, confBiasUpdate, showWeightedGraph,
simulate, rhoPar y confBiasUpdatePar, que corresponden a las versiones paralelas de rho y confBiasUpdate. Este archivo
implementa las funciones y estructuras relacionadas con la evolución de opiniones en redes de agentes a lo largo del
tiempo.

Instrucciones para ejecutar los programas:

Se debe tener la siguiente estructura, preferiblemente en un IDE, contar con archivos sbt, Benchmark y common, en este
caso brindados por el profesor y tener instalada por lo menos la versión 2.13 de Scala:

proyecto/
├── src/
│   └── main/
│       ├── scala/
│       │   ├── Benchmark/
│       │   │   └── package.scala
│       │   ├── Comete/
│       │   │   └── package.scala
│       │   ├── common/
│       │   │   └── package.scala
│       │   └── Opinion/
│       │       └── package.scala
│       └── test/
│           └── scala/
│               └── Pruebas.sc ## Aquí deben ir las pruebas que se quieran realizar
└── build.sbt

Para ejecutar los programas se accede a la carpeta del proyecto y se ejecuta en la terminal:
sbt

Una vez iniciado se ejecuta:
console

Se importan los archivos:
import main.scala.Comete._
import main.scala.Benchmark._
import main.scala.Opinion._

Y a partir de ahí se pueden ejecutar los programas con sus respectivas pruebas