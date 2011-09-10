package app

object Config {
	// Try to fetch the configuration string from the environment, otherwise use the
	// provided default.
	def env(name: String, value: String): String = {
		System.getenv(name) match {
			case null => value
			case str => str
		}
	}

	val appName = "sbt-scalatra-example"

	val webPort = env("WEB_PORT", "8080").toInt
	val webRoot = env("WEB_ROOT", "web")

}
