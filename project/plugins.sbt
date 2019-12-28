// comment to get more information during initialization
logLevel := Level.Warn

// use the last Play sbt plugin for Play projects (13.12.2019) https://www.playframework.com/
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.0")

// add eclipse-plugin (17.11.2017) https://github.com/sbt/sbteclipse/releases
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

// pour auto-load d'un changement dans Google Chrome (23.04.2019) https://github.com/jamesward/play-auto-refresh
addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.17")