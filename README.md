# conseillers
A small Play 2.4.6 application with a MySQL DB (Swiss parlament). 

* Prerequisites :
  * Java 8 JRE or SDK
  * A running MySQL server (min 5.5)
  * MySQL Workbench application (or other) to mount the DB 

* First mount wthe DB with the MySQL script in data folder.
 
* If you want modifiy the source project, download it and use NetBeans to open it. Some links are defect. This is because you must :
  * edit the file "nbproject/private/private.properties" and "models/nbproject/private/private.properties" to set the correct "user.home" information in your environment (MacOS, Windows or Linux).
  * compile the project with the Play framework "activator compile" command. All links should now be ok.

* You can download a runnable distribution here :<br>
  https://dl.dropboxusercontent.com/u/3539873/conseillers-1.05.zip
  * Unzip in 
      * c:\Play for Windows,
      * somewhere else for other OS
  * In the console or terminal, go to the unzipped folder and execute the play application with :
      * bin\conseillers.bat (Windows OS)
      * ./bin/conseillers (Unix like OS, give execution rights for the script "conseillers")
  * Test the application with :
    http://localhost:9000
