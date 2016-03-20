/*
 * Couche de services HTTP (worker). Comme un objet Java,
 * mais créé avec une "closure" exécutée immédiatement (IIFE).
 * Permet de rendre privés ou publiques certaines fonctions.
 *
 * @author Jean-Claude Stritt
 */

/* global browser */

var httpServ = (function() {
//var serverURL = 'http://172.23.87.45:9000'; // EMF
//  var serverURL = 'http://192.168.0.4:9000'; // maison
//  var serverURL = 'http://192.168.0.5:9000'; // raspberry pi2
//  var serverURL = 'http://localhost:9000';
  var serverURL = browser.Url.getBaseUrl();
  
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

  function _chargerCantons(successCallback, errorCallback) {
    var format = "json";
    var fullURL = browser.Url.buildUrl(serverURL, "cantons", format);
    $.ajax({
      type: "GET",
      dataType: format,
      url: fullURL,
      success: successCallback,
      error: errorCallback
    });
  }

  function _chargerConseils(successCallback, errorCallback) {
    var format = "json";
    var fullURL = browser.Url.buildUrl(serverURL, "conseils", format);
    $.ajax({
      type: "GET",
      dataType: format,
      url: fullURL,
      success: successCallback,
      error: errorCallback
    });
  }

  function _chargerPartis(successCallback, errorCallback) {
    var format = "json";
    var fullURL = browser.Url.buildUrl(serverURL, "partis", format);
    $.ajax({
      type: "GET",
      dataType: format,
      url: fullURL,
      success: successCallback,
      error: errorCallback
    });
  }

  function _chargerConseillers(fmt, canton, conseil, parti, actuels, successCallback, errorCallback) {
    var format = fmt.toLowerCase();
    var fullURL = browser.Url.buildUrl(serverURL, "conseillers", format);
    fullURL += "/" + canton;
    fullURL += "/" + conseil;
    fullURL += "/" + parti;
    fullURL += "/" + actuels;
    console.debug('chargerConseillers: '+fullURL);
    $.ajax({
      type: "GET",
      dataType: format,
      url: fullURL,
      success: successCallback,
      error: errorCallback
    });
  }

  // définition des noms de methodes publiques
  return {
    lireVersionServeur: _lireVersionServeur,
    chargerCantons: _chargerCantons,
    chargerConseils: _chargerConseils,
    chargerPartis: _chargerPartis,
    chargerConseillers: _chargerConseillers
  };

})();


