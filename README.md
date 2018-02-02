# conseillers
A small Play application with a MySQL DB (Swiss parliament).
This release is now full adapted to last Play 2.6.x framework.
DbWorkerFactory inject JPA entity manager into the dao layer.
DbWorker and dao are only loaded one time (singleton).

* 1.2.1 (january 2018)
  * Adapted to release 2.6.11

* 1.2.0 (december 2017)
  * Adapted to release 2.6.10
  * new Data in DB

* Prerequisites :
  * Netbeans 8.2 and the « Pleasure Play Framework Plugin » installed
  * Java 8 JRE or SDK
  * A running MySQL server (min 5.5) for local database operations
  * MySQL Workbench application (or other) to mount a local DB

* A default db is installed on the web (emf-informatique.ch).
  * If you want, mount a local DB with the MySQL script in data folder.

* Use and test in NetBeans 8.2
  * If you want modify the source project, download it and use NetBeans 8.2 to open it (with « Pleasure Play Framework Plugin » installed).
  * Run the application with standard F6 button.

* You can test a runnable version on heroku
http://conseillers.herokuapp.com/


