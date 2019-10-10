/*
 * Couche de services HTTP (worker). Comme un objet Java,
 * mais créé avec une "closure" exécutée immédiatement (IIFE).
 * Permet de rendre privés ou publiques certaines fonctions.
 *
 * @author Jean-Claude Stritt
 */

/* global browser */
var httpServ = (() => {
  
  // récupération de la racine du nom de domaine du serveur
  var serverURL = browser.Url.getBaseUrl(); // si client et serveur au même endroit
  if (serverURL.includes('jcsinfo.ch')) {
    serverURL += '/parlement';
  }
  console.log("server URL: "+serverURL);

  function _getBaseUrl() {
    return serverURL;
  }

  function _getVersion(successCallback, errorCallback) {
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

  function _getCreateLoginInfo(successCallback, errorCallback) {
    var format = "json";
    var fullURL = serverURL + "/createLoginInfo";
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
    getBaseUrl: _getBaseUrl,
    getVersion: _getVersion,
    getCreateLoginInfo: _getCreateLoginInfo
  };

})();