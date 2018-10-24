import com.typesafe.config._

// pour les routes, utilise l'ibjection de dépendances
routesGenerator := InjectedRoutesGenerator

// objet permettant l'accès aux variables du fichier ".conf"
val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

// utilisation de l'injection de dépendances "Guice" de Google
libraryDependencies += guice

// pour récupérer conseillers-models dans le dépôt local Maven
resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + ".m2/repository"

// pour récupérer éventuellement basiclib et daolayer sur github (si pas dans .m2)
resolvers += "EMF-info Repository" at "http://emfinfo.github.io/javalibs/releases"

// dépendances (voir dernières versions sur http://mvnrepository.com )
libraryDependencies ++= Seq(
  javaJpa,
  "ch.emf.info" % "conseillers-models" % "1.0.7",
  "ch.emf.info" % "conseillers-models" % "1.0.7" classifier "sources",
  "ch.emf.info" % "conseillers-models" % "1.0.7" classifier "javadoc",
  "ch.emf.info" % "basiclib" % "1.3.2",
  "ch.emf.info" % "basiclib" % "1.3.2" classifier "sources",
  "ch.emf.info" % "basiclib" % "1.3.2" classifier "javadoc",
  "ch.emf.info" % "cypherlib" % "1.0.2",
  "ch.emf.info" % "cypherlib" % "1.0.2" classifier "sources",
  "ch.emf.info" % "cypherlib" % "1.0.2" classifier "javadoc",
  "ch.emf.info" % "daolayer" % "6.0.0",
  "ch.emf.info" % "daolayer" % "6.0.0" classifier "sources",
  "ch.emf.info" % "daolayer" % "6.0.0" classifier "javadoc",
  "commons-codec" % "commons-codec" % "1.7",
  "mysql" % "mysql-connector-java" % "5.1.38").map(_.force()
)

// à cause d'une "warning" : class path contains multiple SLF4J bindings
libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-log4j12")) }

// évite certaines warning
evictionWarningOptions in update := EvictionWarningOptions.default.withWarnTransitiveEvictions(false)

// récupération du nom de l'application et de la version depuis le fichier conf
lazy val commonSettings = Seq(
  name := conf.getString("application.name"),
  version := conf.getString("application.version"),
  scalaVersion := "2.12.4",
  scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation"),
  javacOptions += "-Xlint:unchecked"
)

// monte l'application avec le plugin Java, les fichiers dans le projet
lazy val main = (project in file("."))
.enablePlugins(PlayJava)
.settings(commonSettings: _*)

// projet Java, pas d'utilisation de SCALA
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

// essai de téléchargement des sources pour les librairies dépendantes
EclipseKeys.withSource := true

// essai de téléchargement de la javadoc pour les librairies dépendantes
EclipseKeys.withJavadoc := true

// utilise les fichiers .class à la place des fichiers .scala générés pour les vues et les routes
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)