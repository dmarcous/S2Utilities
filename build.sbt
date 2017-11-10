name := "S2Utilities"

version := "0.1"

scalaVersion := "2.12.4"

// Testing framework
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.+" % "test"

// Geometry objects
libraryDependencies += "com.vividsolutions" % "jts" % "1.13"

// S2 core library (on maven - fork of original Google lib)
libraryDependencies += "io.sgr" % "s2-geometry-library-java" % "1.0.0"

// Enable sbt-ghpages to create a github site for docs
enablePlugins(GhpagesPlugin)
git.remoteRepo := "https://github.com/dmarcous/S2Utilities"

enablePlugins(SiteScaladocPlugin)
