# conseillers 1.3.8 - january 2018
A small Play application with a MySQL DB (Swiss parliament).
This release is now full adapted to last Play 2.6.x framework.

* New in release 1.3.8 (january 2018) :
  * Use of Play 2.6.21
  * DB with new counselors (11.1.2019)

* New in release 1.3.7 (december 2018) :
  * Use of PlayLib 1.0.1
  * LoginCtrl has been rewrited
  * LoginWrk has been completed with some SessionManager methods
  * SessionManager has been deleted (use SessionUtils from PlayLib)
  * Security classes have been moved to controllers.security
  * Use of MessagesApi for i18n messages (used in security classes)
  * new messages.fr in conf for french messages
  * Use of Play Logger in controllers, workers
  * Use of all last librairies ou plugins for Play
  * Use of mariabdb dependency java driver
  * Updated data from Swiss parliament (10.11.2018)

* New in release 1.3.6 (november 2018) :
  * use of DaoPlay 1.0.1 (new for PlayDao)
  * security by profile
  * timeout support (10s for tests)

* New in release 1.3.5 (november 2018) :
  * use of DaoLayer 6.0.1 and PlayDao 1.0.1
  * use of multiple dedicated workers
  * use of conseillers-models 1.0.9
  * better tests

* New in release 1.3.4 (october 2018) :
  * adapted to Play Framework 2.6.20
  * adapted to library DaoLayer 6.0.0 (Guice use)

* New in release 1.3.3 (august 2018) :
  * adapted to Play Framework 2.6.16

* New in release 1.3.2 (may 2018) :
  * adapted to Play Framework 2.6.15
  * logout now returns JSON {open:false}

* New in release 1.3.1 (march 2018) :
  * adapted to DaoLayer 5.2.0 (correction of a big bug with multi-users)
  * adapted to Play Framework 2.6.12

* New in release 1.3.0 (february 2018) :
  * You must now encrypt login and createLogin data with aeslogin.js
library and the method AesUtil.encrypt(data)

* New in release 1.2.1 (february 2018) :
  * Adapted to Play framework 2.6.11
  * Use of BasicLib 1.1.1

* New in release 1.2.0 (december 2017) :
  * Adapted to Play framework 2.6.10
  * new Data in DB

* Prerequisites to use and test :
  * Netbeans (8.2 or 9.x) and the « Pleasure Play Framework Plugin » installed
  * Java 8 JRE or SDK
  * A running MySQL server (min 5.5) for local database operations
  * MySQL Workbench application (or other) to mount a local DB
  * Run the application with standard F6 button.




