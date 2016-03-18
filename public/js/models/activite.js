/*
 * Pseudo-classe "Activite" (utile pour la compatibilité XML-->JSON).
 *
 * @author Jean-Claude Stritt
 */
var Activite = function () {
};

Activite.prototype.setPkActivite = function (pkActivite) {
  this.pkActivite = pkActivite;
};

Activite.prototype.setConseil = function (conseil) {
  this.conseil = conseil;
};

Activite.prototype.setDateEntree = function (date) {
  this.dateEntree = Date.parseDate(date);
};

Activite.prototype.setDateSortie = function (date) {
  this.dateSortie = Date.parseDate(date);
};

Activite.prototype.setGroupe = function (groupe) {
  this.groupe = groupe;
};

Activite.prototype.setFonction = function (fonction) {
  this.fonction = fonction;
};

Activite.prototype.toString = function () {
  return this.conseil.abrev
    + ((this.dateEntree || this.dateSortie)?' (':'')
    + ((this.dateEntree) ? this.dateEntree.format2('yyyy') : '')
    + ((this.dateSortie) ? '-' + this.dateSortie.format2('yyyy') : '-...')
    + ((this.dateEntree || this.dateSortie)?')':'')
    + ((this.groupe) ? ', ' + this.groupe.nomGroupe 
    + ' (' + this.fonction.nomFonction.toLowerCase() + ')':'');

};

