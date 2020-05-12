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
 resolvers += "EMF-info Repository" at "https://emfinfo.github.io/javalibs/releases"

// dépendances (voir dernières versions sur https://mvnrepository.com )
libraryDependencies ++= Seq(
  jdbc, javaJpa,
  "commons-codec" % "commons-codec" % "1.7",
//  "mysql" % "mysql-connector-java" % "5.1.48"
//  ATTENTION mariadb connector > 2.3.0 et <= 2.6.0 provoque un bug de SEQUENCE avec jpa tag @Identity
  "org.mariadb.jdbc" % "mariadb-java-client" % "2.3.0", 
  "ch.jcsinfo.libs" % "basiclib" % "1.5.0",
  "ch.jcsinfo.libs" % "basiclib" % "1.5.0" classifier "sources",
  "ch.jcsinfo.libs" % "basiclib" % "1.5.0" classifier "javadoc",
  "ch.jcsinfo.libs" % "cypherlib" % "1.2.1",
  "ch.jcsinfo.libs" % "cypherlib" % "1.2.1" classifier "sources",
  "ch.jcsinfo.libs" % "cypherlib" % "1.2.1" classifier "javadoc",
  "ch.jcsinfo.libs" % "playlib" % "2.8.1",
  "ch.jcsinfo.libs" % "playlib" % "2.8.1" classifier "sources",
  "ch.jcsinfo.libs" % "playlib" % "2.8.1" classifier "javadoc",
  "ch.emf.info" % "daolayer" % "6.1.1",
  "ch.emf.info" % "daolayer" % "6.1.1" classifier "sources",
  "ch.emf.info" % "daolayer" % "6.1.1" classifier "javadoc",
  "ch.jcsinfo.models" % "conseillers-models" % "1.0.12",
  "ch.jcsinfo.models" % "conseillers-models" % "1.0.12" classifier "sources",
  "ch.jcsinfo.models" % "conseillers-models" % "1.0.12" classifier "javadoc"
).map(_.force())

// à cause d'une "warning" : class path contains multiple SLF4J bindings, on prend logback
libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-log4j12")) }

// évite certaines warning à la mise à jour (compile) de l'application
evictionWarningOptions in update := EvictionWarningOptions.default.withWarnTransitiveEvictions(false)

// récupération du nom de l'application et de la version depuis le fichier conf
lazy val commonSettings = Seq(
  name := conf.getString("application.name"),
  version := conf.getString("application.version"),
  scalaVersion := "2.13.1",
  scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation", "-J-Xss16M"),
  javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")
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