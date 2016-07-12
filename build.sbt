import com.jamesward.play.BrowserNotifierKeys
import PlayKeys._
import com.typesafe.config._

routesGenerator := InjectedRoutesGenerator
// routesGenerator := StaticRoutesGenerator

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

libraryDependencies += filters

scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation")

javacOptions += "-Xlint:unchecked"

// pour récupérer basiclib et daolayer sur github
resolvers += "EMF-info Repository" at "http://emfinfo.github.io/javalibs/releases"

// dépendences (voir dernières versions sur http://mvnrepository.com)
libraryDependencies ++= Seq(
  "ch.emf.info" % "basiclib" % "1.01",
  "ch.emf.info" % "daolayer" % "5.35",
  "mysql" % "mysql-connector-java" % "5.1.38").map(_.force())

// à cause d'une "warning" : class path contains multiple SLF4J bindings
libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-log4j12")) }

lazy val commonSettings = Seq(
  name := conf.getString("application.name"),
  version := conf.getString("application.version"),
  scalaVersion := "2.11.7"
)

lazy val models = (project in file("models"))
    .enablePlugins(PlayJava)
    .settings(commonSettings: _*)

lazy val main = (project in file("."))
    .enablePlugins(PlayJava)
    .dependsOn(models)
    .aggregate(models)
    .settings(commonSettings: _*)

EclipseKeys.skipParents in ThisBuild := false

EclipseKeys.preTasks := Seq(compile in Compile)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

BrowserNotifierKeys.shouldOpenBrowser := true

// fork in run := true
//  scalacOptions += "-feature",
//  scalacOptions += "-deprecation",
//  javacOptions += "-Xlint:unchecked"
//  "org.mindrot" % "jbcrypt" % "0.3m", // pour hachage mot de passe
//  "commons-codec" % "commons-codec" % "1.10", // pour Base64
//  "commons-beanutils" % "commons-beanutils" % "1.9.2"
