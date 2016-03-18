@echo off
cls
rem Efface tous les répertoires de build d'un projet Play.
rem (C) mars 2016 J.-C. Stritt

rem récupère le dossier courant
set mypath=%~dp0
echo Path: %mypath:~0,-1%

rem boucle sur les dossiers de build (target principalement)
for %%i in (target project\target project\project\target build models\target) do (
  echo Remove: %%i ...
  set myFolder="%mypath:~0,-1%\%%i"
  if exist myFolder (
    rem rmdir /s /q "%mypath:~0,-1%\%%i" > NUL
    echo "  " myFolder
  )
)
pause
