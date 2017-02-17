// Comment to get more information during initialization
logLevel := Level.Warn

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.12")

// add eclipse-plugin
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.0.1")

// pour auto-load d'un changement dans Google Chrome
addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.15")

// pour faire des packages light sous heroku
addSbtPlugin("com.heroku" % "sbt-heroku" % "1.0.1")