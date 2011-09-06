package lib

trait Logging {
	import org.slf4j.Logger
	import org.slf4j.LoggerFactory
	lazy val log = LoggerFactory.getLogger(getClass())
}
