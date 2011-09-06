package svc

import lib.Logging
import lib.Utils._


trait Service extends Logging {
	override def toString = getClass.getName
	def start
	def stop
}

