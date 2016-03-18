# conseillers
A small Play 2.4.6 application with a MySQL DB (Swiss parlament). 

* First mount the DB with the MySQL script in data folder.
 
* If you want use and modifiy the source project, download it and use NetBeans to open it. Some links are defect. This is because you must :
  * edit the file "nbproject/private/private.properties" and "models/nbproject/private/private.properties" to set the correct "user.home" information in your environment (MacOS, Windows or Linux).
  * compile the project with the Play framework "activator compile" command. All links must now been ok.

* You can download a runnable distribution too here :<br>
  https://dl.dropboxusercontent.com/u/3539873/conseillers-1.05.zip
  * Unzip in 
      * c:\Play for Windows,
      * somewhere else for other OS
  * Execute the play application in the console (or terminal) and in the unzipeed folder with :
      * bin\conseillers.bat (for Windows OS)
      * ./bin/conseillers (for Unix like OS, give execution rights for the script "conseillers")
  * Test the application with :
    http://localhost:9000
