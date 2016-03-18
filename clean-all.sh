#!/bin/bash

# Efface tous les répertoires de build d'un projet Play
# (C) mars 2016 J.-C. Stritt

# on initialise l'environnement
set +v

# chemin du script
MY_PATH="`dirname \"$0\"`"              # relatif
MY_PATH="`( cd \"$MY_PATH\" && pwd )`"  # absolu et normalisé
if [ -z "$MY_PATH" ] ; then
  exit 1  # fail
fi
echo "Path: "$MY_PATH

# boucle sur une série de dossiers
for f in target project/target project/project/target build models/target
do
  echo Removing: "$f" ... 
  if [ -d "$MY_PATH/$f" ]; then
    #ls "$MY_PATH/$f"
    rm -rf "$MY_PATH/$f"
  fi  
done

# on efface également les fichiers .DS_Store de MacOS
echo "Removing: .DS_Store files ..."
#find "$MY_PATH" -type f -name .DS_Store
find "$MY_PATH" -type f -name .DS_Store -exec rm {} \;
