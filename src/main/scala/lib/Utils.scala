package lib

package object Utils {

	object Shutdown {
		def hook (body: => Unit) =
			Runtime.getRuntime.addShutdownHook(new Thread {
				override def run { body }
			})
	}
}
