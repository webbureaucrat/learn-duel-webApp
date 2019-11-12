name := """learn-duel-webapp"""
organization := "com.webbureaucrat"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.7"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.webbureaucrat.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.webbureaucrat.binders._"
