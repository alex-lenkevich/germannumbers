name := "germannumbers"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.sksamuel.diff" % "diff" % "1.1.11",
  "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.22",
  "com.google.cloud" % "google-cloud-texttospeech" % "0.67.0-beta",
  "de.johoop" %% "sbt-testng-interface" % "3.0.0" % Test
)

enablePlugins(TestNGPlugin)
