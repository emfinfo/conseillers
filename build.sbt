import com.jamesward.play.BrowserNotifierKeys
import PlayKeys._
import com.typesafe.config._

routesGenerator := InjectedRoutesGenerator

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

libraryDependencies += filters

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

lazy val commonSettings = Seq(
  name := conf.getString("application.name"),
  version := conf.getString("application.version"),
  scalaVersion := "2.12.3"
)

lazy val main = (project in file("."))
.enablePlugins(PlayJava)
.settings(commonSettings: _*)

EclipseKeys.preTasks := Seq(compile in Compile)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses,
EclipseCreateSrc.ManagedResources)

BrowserNotifierKeys.shouldOpenBrowser := true

//EclipseKeys.skipParents in ThisBuild := false
//
//lazy val models = (project in file("models"))
//.enablePlugins(PlayJava)
//.settings(commonSettings: _*)
//.dependsOn(models)
//.aggregate(models)
// fork in run := true
//  scalacOptions += "-feature",
//  scalacOptions += "-deprecation",
//  javacOptions += "-Xlint:unchecked"
//  "org.mindrot" % "jbcrypt" % "0.3m", // pour hachage mot de passe
//  "commons-codec" % "commons-codec" % "1.10", // pour Base64
//  "commons-beanutils" % "commons-beanutils" % "1.9.2"