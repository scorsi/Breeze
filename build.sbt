name := "kicklang"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.antlr" % "antlr4-runtime" % "4.7.1"
)

enablePlugins(Antlr4Plugin)

antlr4Version in Antlr4 := "4.7.1"
antlr4PackageName in Antlr4 := Some("com.scorsi")
antlr4GenListener in Antlr4 := true
antlr4GenVisitor in Antlr4 := true


mainClass in assembly := Some("com.scorsi.Main")