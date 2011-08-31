name := "SBT Test Project"

version := "0.1"

scalaVersion := "2.9.0-1"

libraryDependencies ++= Seq (
	"org.eclipse.jetty" % "jetty-server" % "7.4.1.v20110513",
	"org.eclipse.jetty" % "jetty-servlet" % "7.4.1.v20110513",
	"ch.qos.logback" % "logback-classic" % "0.9.28"
)

logLevel := Level.Info

cleanAll <<= (streams, baseDirectory) map {
	(out, basedir) => {
		IO.delete(basedir / "project" / "boot")
		IO.delete(basedir / "project" / "target")
		IO.delete(basedir / "target")
	}
}

cleanAll <<= cleanAll.dependsOn(clean)

dist <<= (streams, baseDirectory, externalDependencyClasspath in Runtime, artifactPath in makePom) map {
	(out, basedir, deps, aPath) => {
		val scripts = new File(basedir, "src/main/scripts")
		val dist = new File(basedir, "target/dist")
		val lib = new File(dist, "lib")
		val artifacts = aPath.getParentFile
		//
		// Copy everything in the scripts directory and make all the shell scripts executable.
		IO.copyDirectory(scripts, dist)
		for ( file <- dist.listFiles; if (file.isFile && file.name.endsWith("sh")) ) yield {
			file.setExecutable(true)
		}
		//
		// Get the jar that was produced from the "package" task and copy it into the "lib"
		// directory
		for ( file <- artifacts.listFiles; if (file.isFile && file.name.endsWith(".jar")) ) yield {
			out.log.info("Copying "+ file.getName)
			IO.copyFile(file, new File(lib, file.getName))
		}
		//
		// Copy all the dependencies into the "lib" directory.
		deps.map(_.data) foreach { file =>
			out.log.info("Copying "+ file.getName)
			IO.copyFile(file, new File(lib, file.getName))
		}
	}
}

dist <<= dist.dependsOn(packageBin in Compile)
