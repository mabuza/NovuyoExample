name := """NovuyoExample"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

val lombokVersion = "1.16.18"
val hibernateVersion = "5.2.12.Final"
val mySQLDriverVersion = "6.0.6"
val modelMapperVersion = "0.7.5"

libraryDependencies ++= Seq(
  guice,
  javaJpa,
  "org.hibernate" % "hibernate-core" % hibernateVersion,
  "org.hibernate" % "hibernate-entitymanager" % hibernateVersion,
  "mysql" % "mysql-connector-java" % mySQLDriverVersion,
  "org.modelmapper" % "modelmapper" % modelMapperVersion,
  "org.projectlombok" % "lombok" % lombokVersion
)
