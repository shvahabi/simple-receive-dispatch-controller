val ScalatraVersion = "2.7.0-RC1"

organization := "com.bsp"

name := "Receive Dispatch Controller"

version := "0.30.8"

scalaVersion := "2.12.10"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.19.v20190610" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
)


libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"        % "3.3.+",
  "com.h2database"  %  "h2"                 % "1.4.196",
  "ch.qos.logback"  %  "logback-classic"    % "1.2.+"
)

libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "3.4.0"

libraryDependencies += "io.argonaut" %% "argonaut" % "6.2.3" 


enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
