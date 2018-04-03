name := "s2utils"

version := "1.1.0"

scalaVersion := "2.12.4"
crossScalaVersions := Seq("2.10.5", "2.11.8", "2.12.4")

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

// POM settings for Sonatype
organization := "com.github.dmarcous"
sonatypeProfileName := "com.github.dmarcous"
homepage := Some(url("https://github.com/dmarcous/S2Utilities"))
scmInfo := Some(ScmInfo(url("https://github.com/dmarcous/S2Utilities"),
  "scm:git@github.com:dmarcous/S2Utilities.git"))
developers := List(Developer("dmarcous",
  "Daniel Marcous",
  "dmarcous@gmail.com",
  url("https://github.com/dmarcous")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
pomIncludeRepository := (_ => false)
publishMavenStyle := true
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}