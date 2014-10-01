import sbt._
import sbt.Keys._

import scala.scalajs.sbtplugin.env.nodejs.NodeJSEnv
import scala.scalajs.sbtplugin.ScalaJSPlugin._
import scala.scalajs.sbtplugin.ScalaJSPlugin.ScalaJSKeys._

object ScalaJSReact extends Build {

  val SCALA_VERSION = "2.11.2"

  val scalajsDom = "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6"
  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
  val scalaReflect = "org.scala-lang" % "scala-reflect" % SCALA_VERSION
  val macroParadisePlugin = compilerPlugin("org.scalamacros" % "paradise" % "2.0.1" cross CrossVersion.full)
  val jasmine = "org.scala-lang.modules.scalajs" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test"
  val reactjs = "org.webjars" % "react" % "0.11.0" / "react.js" commonJSName "React"

  val commonSettings = Seq(
    version := "0.3.2-SNAPSHOT",
    organization := "com.xored.scalajs",
    scalaVersion := SCALA_VERSION,
    licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(url("http://github.com/xored/scala-js-react/")),
    scalacOptions := Seq("-unchecked", "-deprecation",
      "-encoding", "utf8", "-feature", "-Yinline-warnings",
      "-language:implicitConversions", "-language:higherKinds")
  )

  lazy val react = Project("scalajs-react", file("scalajs-react"))
    .settings(scalaJSSettings: _*)
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        scalajsDom,
        scalaXml,
        scalaReflect,
        macroParadisePlugin
      ),
      jsDependencies += reactjs
    )

  lazy val reactTests = Project("scalajs-react-tests", file("scalajs-react-tests"))
    .settings(scalaJSSettings: _*)
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        scalajsDom,
        scalaXml,
        macroParadisePlugin,
        jasmine
      ),
      jsEnv in Test := new NodeJSEnv,
      publishArtifact := false
    )
    .dependsOn(react)

  lazy val examples = Project("scalajs-react-examples", file("scalajs-react-examples"))
    .settings(scalaJSSettings: _*)
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        scalajsDom,
        scalaXml,
        macroParadisePlugin
      ),
      skip in ScalaJSKeys.packageJSDependencies := false, // creates scalajs-react-examples-jsdeps.js
      publishArtifact := false
    )
    .dependsOn(react)

  val root = Project("root", file("."))
    .settings(publishArtifact := false)
    .aggregate(react, reactTests, examples)

  resolvers += Resolver.sonatypeRepo("releases")

}
