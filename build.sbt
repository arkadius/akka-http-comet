import com.banno.license.Plugin.LicenseKeys._
import com.banno.license.Licenses._

licenseSettings

license := apache2("Copyright 2015 the original author or authors.")

name := "akka-http-comet"

version := "0.0.1"

organization := "org.github"

scalaVersion := "2.11.5"

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases")

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val akkaV = "1.0-M2"
  Seq(
    "com.typesafe.akka" %% "akka-http-experimental" % akkaV,
    "org.scalatest"     %% "scalatest"              % "2.2.3" % "test"
  )
}
