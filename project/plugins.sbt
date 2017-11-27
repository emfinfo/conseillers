// Comment to get more information during initialization
logLevel := Level.Warn

// Use the Play sbt plugin for Play projects (27.11.2017)
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.7")

// add eclipse-plugin https://github.com/typesafehub/sbteclipse
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.2")

// pour auto-load d'un changement dans Google Chrome https://github.com/jamesward/play-auto-refresh
addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.15")

// pour faire des packages light sous heroku https://github.com/heroku/sbt-heroku
addSbtPlugin("com.heroku" % "sbt-heroku" % "2.0.0")