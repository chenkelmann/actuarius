import sbtrelease.ReleasePlugin.ReleaseKeys._

name := "actuarius"

organization := "net.redhogs.actuarius"

description := "Actuarius is a Markdown Processor written in Scala using parser combinators."

scalaVersion := "2.10.2"

crossScalaVersions in ThisBuild := Seq("2.10.2", "2.10.0", "2.9.2")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8")

publishArtifact in Test := false

publishMavenStyle := true

pomIncludeRepository := { _ => false }

licenses := Seq("BSD 3 clause" -> url("http://opensource.org/licenses/BSD-3-Clause"))

homepage := Some(url("https://github.com/grahamar/actuarius"))

libraryDependencies += "junit" % "junit" % "4.8.2" % "test"

libraryDependencies <+= scalaVersion {
  case "2.9.2" => "org.scalatest" %% "scalatest" % "1.8" % "test" withSources()
  case _ => "org.scalatest" %% "scalatest" % "1.9" % "test" withSources()
}

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <scm>
    <url>git@github.com:grahamar/actuarius.git</url>
    <connection>scm:git:git@github.com:grahamar/actuarius.git</connection>
  </scm>
  <developers>
    <developer>
      <id>chenkelmann</id>
      <name>Christoph Henkelmann</name>
      <url>http://henkelmann.eu/</url>
    </developer>
    <developer>
      <id>dpp</id>
      <name>David Pollak</name>
      <url>http://blog.goodstuff.im</url>
    </developer>
  </developers>)

releaseSettings
