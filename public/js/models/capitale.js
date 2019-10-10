/*
 * Pseudo-classe "Capitale", modele de donnees pour stocker les infos d'une capitale.
 */
var Capitale = function (nom, lat, lng) {
  this.nom = nom;
  this.lat = lat;
  this.lng = lng;
};

Capitale.prototype.toString = function () {
  return JSON.stringify(this);
};


