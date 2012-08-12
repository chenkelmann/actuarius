//Project Information
name := "actuarius"

description := "Actuarius is a Markdown Processor written in Scala using parser combinators."

scalaVersion := "2.9.2"

scalacOptions += "-deprecation"

publishMavenStyle := true

autoCompilerPlugins := true

checksums := Nil

organization := "eu.henkelmann" 

resolvers += "Scala Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/" 

resolvers += "Scala" at "https://oss.sonatype.org/content/groups/scala-tools/"

version := "0.2.4-SNAPSHOT"

crossScalaVersions in ThisBuild := Seq("2.9.2", "2.9.1-1", "2.9.1", "2.9.0-1", "2.9.0", "2.8.1", "2.8.2")

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "1.8" % "test" withSources(),
    "junit" % "junit" % "4.8.2" % "test"
  )
}

testListeners <<= target.map(t => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath)))

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/chenkelmann/actuarius</url>
  <licenses>
    <license>
      <name>BSD 3 clause</name>
      <url>http://opensource.org/licenses/BSD-3-Clause</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/chenkelmann/actuarius.git</url>
    <connection>scm:git:git://github.com/chenkelmann/actuarius.git</connection>
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

