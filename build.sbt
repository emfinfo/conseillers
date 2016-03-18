import com.jamesward.play.BrowserNotifierKeys

name := "Conseillers"

libraryDependencies += filters

scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation")

// pour récupérer basiclib et daolayer sur github
resolvers += "EMF-info Repository" at "http://emfinfo.github.io/javalibs/releases"

// selon versions au 14.3.2016 sur http://mvnrepository.com/
libraryDependencies ++= Seq(
  "ch.emf.info" % "basiclib" % "1.01",
  "ch.emf.info" % "daolayer" % "5.34",  
  "mysql" % "mysql-connector-java" % "5.1.38"
)

lazy val commonSettings = Seq(
  version := "1.05",
  scalaVersion := "2.11.6",
  javacOptions += "-Xlint:unchecked",
  scalacOptions += "-deprecation"
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
