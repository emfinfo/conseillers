/* global httpServ */

var indexCtrl = (function () {

  // valeurs globales par défaut
  // ...

  /*
   * 1. METHODES D'AFFICHAGE
   */

  function afficherErreurHttp(jqXHR, textStatus, errorThrown) {
    var msg = textStatus + ": " + errorThrown + " " + jqXHR.responseText + " !";
    console.log(msg);
//    alert(msg);
  }


  /*
   * 2. CALLBACKS (RETOUR DE SERVICE)
   */
  function okLireVersionServeur(data, text, jqXHR) {
    var infoComponent = $("#versionServeur");
    infoComponent.html(data.versionServeur);
  }


  /*
   * 3. DOM PRET : chargement des premières données dans la vue
   */
  $(document).ready(function () {
    console.debug("DOM ready !!!");

    // chargement de la couche de service et des premières données par HTTP
    $.getScript("js/services/httpServ.js", function () {
      console.log("httpServ.js chargé !");
      httpServ.lireVersionServeur(okLireVersionServeur, afficherErreurHttp);
    });


    /*
     * 4. ECOUTEURS SUR DES ACTIONS DE l'UTILISATEUR
     */

  });

})();

