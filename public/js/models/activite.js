/*
 * Pseudo-classe "Activite" (utile pour le toString).
 *
 * @author Jean-Claude Stritt
 */
var Activite = function (act) {
  this.conseil = act.conseil;
  this.dateEntree = Date.parseString(act.dateEntree);
  this.dateSortie = Date.parseString(act.dateSortie);
  this.groupe = act.groupe;
  this.fonction = act.fonction;
};

Activite.prototype.toString = function () {
  return this.conseil.abrev
    + ((this.dateEntree || this.dateSortie) ? ' (' : '')
    + ((this.dateEntree) ? this.dateEntree.formatOnlyYear() : '')
    + ((this.dateSortie) ? '-' + this.dateSortie.formatOnlyYear() : '-...')
    + ((this.dateEntree || this.dateSortie) ? ')' : '')
    + ((this.groupe && this.conseil.abrev != "CF") ? ', ' + this.groupe.nom : '');
//      + ' (' + this.fonction.nomFonction.toLowerCase() + ')' : '');

};

