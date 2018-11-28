/*
 * Pseudo-classe "Conseiller", modele de donnees pour stocker les infos d'un conseiller
 * national (surtout utile pour le toString).
 *
 * "cons" est un objet JSON recupere d'un appel AJAX
 */
var Conseiller = function (cons) {
  this.actif = cons.actif;
  this.nom = cons.nom;
  this.prenom = cons.prenom;
  this.citoyennete = cons.citoyennete;
  this.dateNaissance = Date.parseString(cons.dateNaissance);
  this.dateDeces = Date.parseString(cons.dateDeces);
  this.canton = cons.canton;
  this.parti = cons.parti;
  this.activites = cons.activites;
};

Conseiller.prototype.toString = function () {
  return this.nom + ' ' + this.prenom
    + ((this.dateNaissance || this.dateDeces) ? ' (' : '')
    + ((this.dateNaissance) ? this.dateNaissance.formatWithPattern('yyyy') : '')
    + ((this.dateDeces) ? "-" + this.dateDeces.formatWithPattern('yyyy') : '')
    + ((this.dateNaissance || this.dateDeces) ? ')' : '')
    + ', ' + this.canton.abrev
    + ', ' + this.parti.abrev;
};

