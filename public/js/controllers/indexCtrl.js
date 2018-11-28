/*
 * Contrôleur de la Single Page Application (SPA).
 *
 * @author Jean-Claude Stritt
 */

/* global httpServ */

var indexCtrl = (function () {

  /*
   * 1. DOM PRET : chargement de la fenêtre de login
   */
  $(document).ready(function () {
    if (httpServ) {
      httpServ.centraliserErreursHttp(_afficherErreurHttp);
      httpServ.chargerVue("login");
    }
  });


  /*
   * 2. METHODES DE LECTURE/ECRITURE DANS LA VUE (ou la console du navigateur)
   */
  function _afficherErreurHttp(errIdx) {
    var msg = [
      "500 - Erreur interne sur le serveur !",
      "503 - Service indisponible !",
      "404 - Information non trouvée !",
      "401 - Erreur de données",
      "408 - Timeout (délai serveur) !",
      "413 - Opération interrompue !",
      "400 - Demande inacceptable !"
    ];
    if (errIdx == 2) {
      swal(msg[errIdx], "Veuillez modifier votre requête svp", "warning");
    } else if (errIdx === 3 || errIdx === 4) {
      swal(msg[errIdx], "Veuillez vous reloguer svp", "info");
      httpServ.chargerVue("login");
    } else {
      swal( msg[errIdx], "Veuillez contacter un administrateur svp", "error");
    }
  }


  /*
   * 3. CALLBACKS (RETOUR DE REQUETES HTTP)
   */


  /*
   * 4. METHODES NECESSAIRES AUX ACTIONS DANS LA VUE
   */

})();