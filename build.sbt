name := "rogue"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

resolvers += "Local Maven Repository" at "file:///c:/data/r/prod-m2"

libraryDependencies ++= Seq(
  "org.mech" % "terminator" % "0.0.1-SNAPSHOT",
  "com.miglayout" % "miglayout" % "3.7.4",
  "org.slf4j" % "slf4j-api" % "1.7.16",
  "org.slf4j" % "slf4j-simple" % "1.7.16",
  "junit" % "junit" % "4.12" % Test
)