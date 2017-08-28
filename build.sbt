import com.jamesward.play.BrowserNotifierKeys
import PlayKeys._
import com.typesafe.config._

routesGenerator := InjectedRoutesGenerator

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

libraryDependencies += guice

scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation")

javacOptions += "-Xlint:unchecked"

// pour récupérer basiclib et daolayer sur github
resolvers += "EMF-info Repository" at "http://emfinfo.github.io/javalibs/releases"

// pour récupérer conseillers-models dans le dépôt local Maven
resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + ".m2/repository"

// dépendences (voir dernières versions sur http://mvnrepository.com )
libraryDependencies ++= Seq(
  javaJpa,
  "ch.emf.info" % "conseillers-models" % "1.0.5",
  "ch.emf.info" % "daolayer" % "5.1.4",
"mysql" % "mysql-connector-java" % "5.1.38").map(_.force())

// à cause d'une "warning" : class path contains multiple SLF4J bindings
libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-log4j12")) }

// récupération du nom de l'application et de la version depuis le fichier conf
lazy val commonSettings = Seq(
  name := conf.getString("application.name"),
  version := conf.getString("application.version"),
  scalaVersion := "2.12.3"
)

// monte l'application avec le plugin Java, les fichiers dans le projet
lazy val main = (project in file("."))
.enablePlugins(PlayJava)
.settings(commonSettings: _*)