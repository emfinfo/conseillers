# conseillers
A small Play 2.4.6 application with a MySQL DB (Swiss parlament). 

First mount the DB with the MySQL script in data folder.

* You can download a runnable 1.05 distribution here :
    https://dl.dropboxusercontent.com/u/3539873/conseillers-1.05.zip

* Unzip, give rights for the bin/conseillers or bin\conseillers.bat and execute with :<br>
    ./bin/conseillers (for Unix like OS)<br>
    bin/conseillers.bat (for Windows OS)<br>

* Test the application with :
    http://localhost:9000

If you want use and modifiy the source project, download it and use NetBeans to open it. Some links are defect. This is because you must :<br>
* First edit the file "nbproject/private/private.properties" and "models/nbproject/private/private.properties" to set the correct "user.home" information for you in your environment (MacOS, Windows or Linux).
* Second, compile the project with the Play framework "activator compile" command. All links must now been ok.
