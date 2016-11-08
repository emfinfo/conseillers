// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
// resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.9")

// add eclipse-plugin
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")

// pour auto-load d'un changement dans Google Chrome
addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.15")

// pour faire des packages light sous heroku
addSbtPlugin("com.heroku" % "sbt-heroku" % "1.0.1")