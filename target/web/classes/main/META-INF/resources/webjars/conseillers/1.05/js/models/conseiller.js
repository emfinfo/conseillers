/*
 * Pseudo-classe "Conseiller" (utile pour la compatibilité XML-->JSON).
 *
 * @author Jean-Claude Stritt
 */
var Conseiller = function () {
};

Conseiller.prototype.setPkConseiller = function (pkConseiller) {
  this.pkConseiller = pkConseiller;
};

Conseiller.prototype.setNom = function (nom) {
  this.nom = nom;
};

Conseiller.prototype.setPrenom = function (prenom) {
  this.prenom = prenom;
};

Conseiller.prototype.setOrigine = function (origine) {
  this.origine = origine;
};

Conseiller.prototype.setDateNaissance = function (date) {
  this.dateNaissance = Date.parseDate(date);
};

Conseiller.prototype.setDateDeces = function (date) {
  this.dateDeces = Date.parseDate(date);
};

Conseiller.prototype.setCanton = function (canton) {
  this.canton = canton;
};

Conseiller.prototype.setParti = function (parti) {
  this.parti = parti;
};

Conseiller.prototype.setActivites = function (activites) {
  this.activites = activites;
};

Conseiller.prototype.toString = function () {
  return this.nom + ' ' + this.prenom + ', ' + this.canton.abrev 
       + ((this.dateNaissance || this.dateDeces)?' (':'')
       + ((this.dateNaissance) ? this.dateNaissance.format2('yyyy') : '')
       + ((this.dateDeces) ? "-"+this.dateDeces.format2('yyyy') : '') 
       + ((this.dateNaissance || this.dateDeces)?')':'')
       + ', ' + this.parti.nomParti;
};

