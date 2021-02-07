// comment to get more information during initialization
logLevel := Level.Warn

// use the last Play sbt plugin for Play projects (07.02.2021) https://www.playframework.com/
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.7")

// add eclipse-plugin (17.11.2017) https://github.com/sbt/sbteclipse/releases
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

// pour auto-load d'un changement dans Google Chrome (25.03.2020) https://github.com/jamesward/play-auto-refresh
addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.18")

// pour mettre à jour les dépendances pas à jour
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.5.1")