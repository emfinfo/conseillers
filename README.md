# conseillers
A small Play! framework 2.4.6 application with a MySQL DB (Swiss parlament). 

* Prerequisites :
  * Java 8 JRE or SDK
  * A running MySQL server (min 5.5)
  * MySQL Workbench application (or other) to mount the DB 

* Mount the DB with the MySQL script in data folder (adapt if necessary).
 
* If you want modify the source project, download it and use NetBeans 8.1 to open it. It's a standard NetBeans project, but some links are defect when you open it for the first time. This is because you must :
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
