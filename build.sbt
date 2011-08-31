name := "sbt-scalatra-example"

scalaVersion := "2.9.0-1"

libraryDependencies ++= Seq (
	"org.eclipse.jetty" % "jetty-server" % "7.4.1.v20110513",
	"org.eclipse.jetty" % "jetty-servlet" % "7.4.1.v20110513",
	"ch.qos.logback" % "logback-classic" % "0.9.28"
)

logLevel := Level.Info
