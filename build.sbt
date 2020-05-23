scalaVersion := "2.13.1"

name := "bsp"
organization := "ch.epfl.scala"
version := "1.0"

//libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"

libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "3.3.2"


/*
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.1.1"

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"
*/

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"        % "3.4.+",
  "com.h2database"  %  "h2"                 % "1.4.+",
  "ch.qos.logback"  %  "logback-classic"    % "1.2.+"
)

libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "3.4.0"


lazy val betterVersion = "3.8.0"

libraryDependencies += "com.github.pathikrit" %% "better-files" % betterVersion

libraryDependencies += "io.argonaut" %% "argonaut" % "6.2.3" 

