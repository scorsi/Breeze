name := "kicklang"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.antlr" % "antlr4-runtime" % "4.7.1"
)

enablePlugins(Antlr4Plugin)
