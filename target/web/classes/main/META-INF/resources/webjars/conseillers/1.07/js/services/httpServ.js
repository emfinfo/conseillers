/*
 * Couche de services HTTP (worker). Comme un objet Java,
 * mais créé avec une "closure" exécutée immédiatement (IIFE).
 * Permet de rendre privés ou publiques certaines fonctions.
 *
 * @author Jean-Claude Stritt
 */

/* global browser */
var httpServ = (function () {
//  var serverURL = 'http://172.23.87.45:9000'; // EMF
//  var serverURL = 'http://192.168.0.4:9000'; // maison
//  var serverURL = 'http://192.168.0.5:9000'; // raspberry pi2
//  var serverURL = 'http://localhost:9000'; // local
  var serverURL = browser.Url.getBaseUrl(); // si client et serveur au même endroit
//  var serverURL = 'http://conseillers.herokuapp.com';

  function _lireVersionServeur(successCallback, errorCallback) {
    var format = "json";
    var fullURL = serverURL + "/version";
    $.ajax({
      type: "GET",
      dataType: format,
      url: fullURL,
      success: successCallback,
      error: errorCallback
    });
  }

  function _recupererNomServeur() {
    return serverURL;
  }

  // définition des noms de methodes publiques
  return {
    lireVersionServeur: _lireVersionServeur,
    recuperNomServeur: _recupererNomServeur
  };

})();


