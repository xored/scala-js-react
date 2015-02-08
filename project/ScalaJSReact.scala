import sbt._
import sbt.Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object ScalaJSReact extends Build {

  val SCALA_VERSION = "2.11.5"

  val uTestVersion = "0.3.0"
  val scalajsDomVersion = "0.7.0"
  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
  val scalaReflect = "org.scala-lang" % "scala-reflect" % SCALA_VERSION
  val macroParadisePlugin = compilerPlugin("org.scalamacros" % "paradise" % "2.0.1" cross CrossVersion.full)
  val jasmine = "org.scala-js" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test"
  val reactjs = "org.webjars" % "react" % "0.11.0" / "react.js" commonJSName "React"

  val commonSettings = Seq(
    version := "0.3.3-SNAPSHOT",
    organization := "com.xored.scalajs",
    scalaVersion := SCALA_VERSION,
    licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(url("http://github.com/xored/scala-js-react/")),
    scalacOptions := Seq("-unchecked", "-deprecation",
      "-encoding", "utf8", "-feature", "-Yinline-warnings",
      "-language:implicitConversions", "-language:higherKinds")
  )

  lazy val react = Project("scalajs-react", file("scalajs-react"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "org.scala-js" %%% "scalajs-dom" % scalajsDomVersion,
        scalaXml,
        scalaReflect,
        macroParadisePlugin
      ),
      jsDependencies += reactjs,
      test in Test := {}
    )

  lazy val reactTests = Project("scalajs-react-tests", file("scalajs-react-tests"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "org.scala-js" %%% "scalajs-dom" % scalajsDomVersion,
        scalaXml,
        macroParadisePlugin,
        "com.lihaoyi" %%% "utest" % uTestVersion % "test"
      ),
      scalaJSStage := FastOptStage,
      testFrameworks += new TestFramework("utest.runner.Framework"),
      publishArtifact := false
    )
    .dependsOn(react)

  lazy val examples = Project("scalajs-react-examples", file("scalajs-react-examples"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        macroParadisePlugin
      ),
      skip in packageJSDependencies := false, // creates scalajs-react-examples-jsdeps.js
      test in Test := {}
    )
    .dependsOn(react)

  val root = Project("root", file("."))
    .settings(publishArtifact := false)
    .aggregate(react, reactTests, examples)

  resolvers += Resolver.sonatypeRepo("releases")

}
