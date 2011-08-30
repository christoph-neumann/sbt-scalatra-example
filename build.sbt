name := "SBT Test Project"

version := "0.1"

scalaVersion := "2.9.0-1"

logLevel := Level.Info

cleanAll <<= (streams, baseDirectory) map {
	(out, basedir) => {
		IO.delete(basedir / "project" / "boot")
		IO.delete(basedir / "project" / "target")
		IO.delete(basedir / "target")
	}
}

cleanAll <<= cleanAll.dependsOn(clean)
