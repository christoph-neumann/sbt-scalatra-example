package svc

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.{ContextHandler, ContextHandlerCollection, ResourceHandler}
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

	class ExampleApp extends ScalatraServlet with Logging {
		notFound { response.setStatus(404) ; "" }

		get("/hello/:name") {
			val name = params("name")
			contentType = "text/html"
			"<b>Hello %s</b>".format(name)
		}
	}

	private object JettyServer extends Logging {

		// Setup the handler for the static content. This can coexist with the same context path as
		// the Scalatra servlet. The "DefaultServlet" can't.
		private val staticContext = new ContextHandler()
		staticContext.setContextPath("/")
		staticContext.setResourceBase(Config.webRoot)
		staticContext.setHandler(new ResourceHandler())

		// Setup the Scalatra servlet.
		private val appContext = new ServletContextHandler(ServletContextHandler.SESSIONS)
		appContext.addServlet(new ServletHolder(new ExampleApp), "/*")

		// All the contexts
		private val collection = new ContextHandlerCollection()
		collection.addHandler(staticContext)
		collection.addHandler(appContext)

		// Create the server and associate the config.
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
