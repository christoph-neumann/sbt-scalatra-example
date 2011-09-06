package svc

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.ContextHandlerCollection
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}

import org.scalatra.ScalatraServlet

import app.Config
import lib.Logging


object WebService extends Service with Logging {
	override def start() {
		log.info("starting " + this)
		JettyServer.start
	}

	override def stop() {
		log.info("stopping " + this)
		JettyServer.stop
	}

	class HelloServlet extends ScalatraServlet with Logging {
		notFound { response.setStatus(404) ; "" }

		get("/hello/:name") {
			val name = params("name")
			contentType = "text/html"
			"<b>Hello %s</b>".format(name)
		}
	}

	private object JettyServer extends Logging {

		private val appContext = new ServletContextHandler(ServletContextHandler.SESSIONS)
		appContext.addServlet(new ServletHolder(new HelloServlet), "/*")

		private val collection = new ContextHandlerCollection()
		collection.addHandler(appContext)

		private val server = new Server(Config.webPort)
		server.setSendServerVersion(false)
		server.setHandler(collection)

		def start {
			server.start
		}

		def stop {
			server.stop
		}
	}
}
