enablePlugins(ScalaJSPlugin)
scalaJSStage in Global := FastOptStage
lazy val root = (project in file(".")).
  settings(
    name := "gameoflife",
    version := "0.0.1",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation"),
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.3.6"
  )
