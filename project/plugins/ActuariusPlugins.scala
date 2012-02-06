import sbt._

class ActuariusPlugins(info: ProjectInfo) extends PluginDefinition(info) {
    val ideaRepo  = "GH-pages repo" at "http://mpeltonen.github.com/maven/"
    lazy val idea = "com.github.mpeltonen" % "sbt-idea-plugin" % "0.1-SNAPSHOT"

    val junitXmlRepo  = "Christoph's Maven Repo" at "http://maven.henkelmann.eu/"
    lazy val junitXml = "eu.henkelmann" % "junit_xml_listener" % "0.2"

    lazy val eclipse = "de.element34" % "sbt-eclipsify" % "0.7.0"
} 
