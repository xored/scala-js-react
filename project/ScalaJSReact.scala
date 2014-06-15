import sbt._
import sbt.Keys._

import scala.scalajs.sbtplugin.ScalaJSPlugin._

object ScalaJSReact extends Build {

  val SCALA_VERSION = "2.11.1"

  val scalajsDom = "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6"
  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
  val scalaReflect = "org.scala-lang" % "scala-reflect" % SCALA_VERSION
  val macroParadisePlugin = compilerPlugin("org.scalamacros" % "paradise" % "2.0.0" cross CrossVersion.full)

  val commonSettings = Seq(
    version := "0.1-SNAPSHOT",
    organization := "org.scala-lang.modules.scalajs",
    scalaVersion := SCALA_VERSION,
    homepage := Some(url("http://github.com/xored/scala-js-react/"))
  )

  lazy val react = Project("scalajs-react", file("scalajs-react"))
    .settings(scalaJSSettings: _*)
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
        "org.scala-lang.modules" %% "scala-xml" % "1.0.2",
        "org.scala-lang" % "scala-reflect" % "2.11.1",
        compilerPlugin("org.scalamacros" % "paradise" % "2.0.0" cross CrossVersion.full)
      )
    )

  lazy val examples = Project("scalajs-react-examples", file("scalajs-react-examples"))
    .settings(scalaJSSettings: _*)
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        scalajsDom,
        scalaXml,
        macroParadisePlugin
      )
    )
    .dependsOn(react)

  val root = Project("root", file(".")).aggregate(react, examples)

  resolvers += Resolver.sonatypeRepo("releases")

}
