name := "sbt-scalatra-example"

scalaVersion := "2.9.1"

resolvers ++= Seq (
	"sonatype-s" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq (
	"org.eclipse.jetty" % "jetty-server" % "7.4.1.v20110513",
	"org.eclipse.jetty" % "jetty-servlet" % "7.4.1.v20110513",
	"org.scalatra" % "scalatra_2.9.1" % "2.1.0-SNAPSHOT",
	"org.scalatra" % "scalatra-scalatest_2.9.1" % "2.1.0-SNAPSHOT",
	"ch.qos.logback" % "logback-classic" % "0.9.28"
)

logLevel := Level.Info
