/*
 * Pseudo-classe "Capitale", modele de donnees pour stocker les infos d'une capitale.
 */
var Capitale = function (nom, lat, long) {
  this.nom = nom;
  this.lat = lat;
  this.long = long;
};

Capitale.prototype.toString = function () {
  return JSON.stringify(this);
};


