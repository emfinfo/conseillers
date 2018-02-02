// comment to get more information during initialization
logLevel := Level.Warn

// use the Play sbt plugin for Play projects (02.02.2018) https://www.playframework.com/
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.11")

// add eclipse-plugin (02.02.2018) https://github.com/sbt/sbteclipse/releases
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

// pour auto-load d'un changement dans Google Chrome https://github.com/jamesward/play-auto-refresh
addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.16")

// pour faire des packages light sous heroku (20.12.2017) https://github.com/heroku/sbt-heroku
addSbtPlugin("com.heroku" % "sbt-heroku" % "2.1.1")