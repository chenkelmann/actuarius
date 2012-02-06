import sbt._
import de.element34.sbteclipsify._
import eu.henkelmann.sbt._

class ActuariusProject(info: ProjectInfo) extends DefaultProject(info) 
    with IdeaProject with Eclipsify{
    
    val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test->default" withSources()
    val junit = "junit" % "junit" % "4.8.2" % "test->default"
    
    def junitXmlListener: TestReportListener = new JUnitXmlTestsListener(outputPath.toString)
    override def testListeners: Seq[TestReportListener] = super.testListeners ++ Seq(junitXmlListener)

    override def managedStyle = ManagedStyle.Maven    
    lazy val publishTo = Resolver.file("Development Repo", new java.io.File((Path.userHome /"srv"/"maven").toString))

    
    // define additional artifacts
    // create jar paths for javadoc and sources
    override def packageDocsJar = defaultJarPath("-javadoc.jar")
    override def packageSrcJar  = defaultJarPath("-sources.jar")
    val sourceArtifact = Artifact.sources(artifactID)
    val docsArtifact   = Artifact.javadoc(artifactID)

    override def packageToPublishActions = super.packageToPublishActions ++ Seq(packageDocs, packageSrc)
}
