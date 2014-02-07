//Project Information
name := "actuarius"

description := "Actuarius is a Markdown Processor written in Scala using parser combinators."

scalaVersion := "2.10.0"

scalacOptions += "-deprecation"

publishMavenStyle := true

autoCompilerPlugins := true

organization := "net.redhogs.actuarius" 

resolvers += "Scala Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/" 

resolvers += "Scala" at "https://oss.sonatype.org/content/groups/scala-tools/"

version := "0.2.7-SNAPSHOT"

crossScalaVersions in ThisBuild := Seq("2.9.2", "2.10.0")

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "1.8" % "test" withSources(),
    "junit" % "junit" % "4.8.2" % "test"
  )
}

//TODO: reactivate once junit-XML listener is on maven central
//testListeners <<= target.map(t => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath)))

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
  <url>https://github.com/grahamar/actuarius</url>
  <licenses>
    <license>
      <name>BSD 3 clause</name>
      <url>http://opensource.org/licenses/BSD-3-Clause</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
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

