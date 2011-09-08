name := "sbt-scalatra-example"

scalaVersion := "2.9.1"

{
var scalatraVersion = "2.1.0-SNAPSHOT"
var jettyVersion = "7.4.1.v20110513"
libraryDependencies ++= Seq (
	"org.eclipse.jetty" % "jetty-server" % jettyVersion,
	"org.eclipse.jetty" % "jetty-servlet" % jettyVersion,
	"org.scalatra" %% "scalatra" % scalatraVersion,
	"org.scalatra" %% "scalatra-scalatest" % scalatraVersion,
	"ch.qos.logback" % "logback-classic" % "0.9.28"
)
}

resolvers ++= Seq (
	"sonatype-s" at "https://oss.sonatype.org/content/repositories/snapshots"
)

logLevel := Level.Info
