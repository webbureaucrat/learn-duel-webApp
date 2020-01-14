import com.typesafe.sbt.SbtScalariform._
import scalariform.formatter.preferences._

name := """learn-duel-webapp"""
organization := "com.webbureaucrat"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.7"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test

resolvers += Resolver.jcenterRepo

resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "com.mohiva" %% "play-silhouette" % "6.1.0",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "6.1.0",
  "com.mohiva" %% "play-silhouette-persistence" % "6.1.0",
  "com.mohiva" %% "play-silhouette-crypto-jca" % "6.1.0",
  "com.mohiva" %% "play-silhouette-totp" % "6.1.0",
  "org.webjars" %% "webjars-play" % "2.7.0",
  "org.webjars" % "bootstrap" % "4.1.0" exclude("org.webjars", "jquery"),
  "org.webjars" % "jquery" % "3.2.1",
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "com.iheart" %% "ficus" % "1.4.3",
  "com.typesafe.play" %% "play-mailer" % "7.0.0",
  "com.typesafe.play" %% "play-mailer-guice" % "7.0.0",
  "com.enragedginger" %% "akka-quartz-scheduler" % "1.6.1-akka-2.5.x",
  "com.adrianhurt" %% "play-bootstrap" % "1.5-P27-B3-SNAPSHOT",
  "com.mohiva" %% "play-silhouette-testkit" % "6.1.0" % "test",
  specs2 % Test,
  ehcache,
  guice,
  filters
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.webbureaucrat.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.webbureaucrat.binders._"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, SbtWeb, SbtVuefy)
  .settings(
    scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      guice
    ),
    Assets / VueKeys.vuefy / VueKeys.prodCommands := Set("stage"),
    Assets / VueKeys.vuefy / VueKeys.webpackBinary := {
      // Detect windows
      if (sys.props.getOrElse("os.name", "").toLowerCase.contains("win")) {
        (new File(".") / "node_modules" / ".bin" / "webpack.cmd").getAbsolutePath
      } else {
        (new File(".") / "node_modules" / ".bin" / "webpack").getAbsolutePath
      }
    },
    Assets / VueKeys.vuefy / VueKeys.webpackConfig := (new File(".") / "webpack.config.js").getAbsolutePath,
    // All non-entry-points components, which are not included directly in HTML, should have the prefix `_`.
    // Webpack shouldn't compile non-entry-components directly. It's wasteful.
    Assets / VueKeys.vuefy / excludeFilter := "_*"
  )

routesImport += "utils.route.Binders._"

// https://github.com/playframework/twirl/issues/105
TwirlKeys.templateImports := Seq()

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  //"-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  // Play has a lot of issues with unused imports and unsued params
  // https://github.com/playframework/playframework/issues/6690
  // https://github.com/playframework/twirl/issues/105
  "-Xlint:-unused,_"
)

//********************
// Scalariform settings
//********************

scalariformAutoformat := true

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(FormatXml, false)
  .setPreference(DoubleIndentConstructorArguments, false)
  .setPreference(DanglingCloseParenthesis, Preserve)
