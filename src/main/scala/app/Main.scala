package app

import lib.Logging
import lib.Utils._
import svc._


object Main extends Logging {
	val services = List(WebService)

	def main(args: Array[String]) {
		Shutdown.hook {
			log.info("shutting down all the services")
			services.foreach(_.stop)
		}

		log.info("starting "+ Config.appName)
		services.foreach(_.start)
	}
}
