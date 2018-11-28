/*
 * Couche de services "httpServ" (worker) permettant de centraliser toutes
 * les requêtes HTTP à l'intention des contrôleurs. Comme un objet Java,
 * mais créé avec une "closure" exécutée immédiatement (IIFE).
 *
 * @author Jean-Claude Stritt
 */

/* global browser, validator, AesUtil */

var httpServ = (function () {
  // var SERVER_URL = 'http://localhost:9000'; // local
  // var SERVER_URL = 'http://172.23.87.10:9000'; // EMF
  // var SERVER_URL = 'http://192.168.0.11:9000'; // maison
  // var SERVER_URL = 'http:///51.77.136.188'; // OVH
  var SERVER_URL = browser.Url.getBaseUrl(); // si client et serveur au même endroit
  var JSON_TYPE = 'json';
  var SEPARATOR = '♂♥♀';

  /*
   * GESTION GLOBALE DES ERREURS HTTP
   */
  function _centraliserErreursHttp(httpErrorCallbackFn) {
    $.ajaxSetup({
      error: function (jqXHR, exception) {
        var errIdx;
        if (jqXHR.status === 500) {
          errIdx = 0; // 500 - Internal server error
        } else if (jqXHR.status === 0) {
          errIdx = 1; // 503 - Service unavailable
        } else if (jqXHR.status === 404) {
          errIdx = 2; // 404 - Not found
        } else if (exception === 'parsererror') {
          errIdx = 3; // 401 - Parsing error
        } else if (exception === 'timeout') {
          errIdx = 4; // 408 - Timeout
        } else if (exception === 'abort') {
          errIdx = 5; // 413 - Aborted
        } else {
          errIdx = 6; // 400 - Bad request
        }
        httpErrorCallbackFn(errIdx);
      },
      xhrFields: { // pour utiliser le cookie de session
        withCredentials: true
      },
      cache: false
    });
  }


  /*
   * CHARGEMENT D'UNE SOUS-VUE (HTTP GET)
   */

  function _chargerVue(nomVue) {
    var nomFichierHtml = 'views/' + nomVue + '.html';
    var nomFichierCtrl = 'js/controllers/' + nomVue + 'Ctrl.js';
    //    var nomFichierVal = 'js/validators/' + nomVue + 'Validator.js';

    // chargement de la vue
    $('#view').load(nomFichierHtml, function () {
      console.debug(nomFichierHtml + ' OK !');

      // chargement du controleur de la vue
      $.getScript(nomFichierCtrl, function () {
        console.debug(nomFichierCtrl + ' OK !');
      });

      // chargement des validateurs de cette vue
      //      $.getScript(nomFichierVal, function () {
      //        console.debug(nomFichierVal + " OK !");
      //        $("#frm-" + nomVue).formValidation(validator.get());
      //      });
      //
      //      // traitement special pour le texte des switchs
      //      var compSwitch = $("[name='switch-checkbox']");
      //      compSwitch.bootstrapSwitch('onText', 'Oui');
      //      compSwitch.bootstrapSwitch('offText', 'Non');
      //
      //      // traitement special pour les boutons "groupes" (ex: les langues)
      //      var compBtnGroupes = $(".btn-group > .btn");
      //      compBtnGroupes.click(function () {
      ////        console.log(this);
      //        compBtnGroupes.removeClass("btn-primary");
      //        compBtnGroupes.removeClass("active");
      //        $(this).addClass("active");
      //        $(this).addClass("btn-primary");
      //      });

    });

    // chargement de la vue
    //    $.ajax({
    //      type: "GET",
    //      url: nomFichierHtml
    //    }).done(function (data) {
    //      $('#view').html(data);
    //      okChargement(nomVue);
    //    });
  }


  /*
   * OPERATIONS GENERIQUES
   */
  function _lireVersion(successCallbackFn) {
    var fullURL = SERVER_URL + "/version";
    $.ajax({
      type: "GET",
      dataType: JSON_TYPE,
      url: fullURL,
      success: successCallbackFn
    });
  }

  function _lireStatusSession(successCallbackFn) {
    var fullURL = SERVER_URL + "/session/status";
    $.ajax({
      type: "GET",
      dataType: JSON_TYPE,
      url: fullURL,
      success: successCallbackFn
    });
  }


  /*
   * OPERATIONS "METIER" CONCERNANT LA GESTION DU LOGIN ET LA CREATION DE COMPTE
   */
  function _effectuerLogin(login, successCallbackFn) {
    var data = login.nom + SEPARATOR + login.domaine + SEPARATOR +
      login.motDePasse + SEPARATOR + Date.now();
    var encData = AesUtil.encrypt(data);
    var fullURL = SERVER_URL + "/session/login/" + encData;
    $.ajax({
      type: "GET",
      dataType: JSON_TYPE,
      url: fullURL,
      success: successCallbackFn
    });
  }

  function _effectuerLogout(successCallbackFn) {
    var fullURL = SERVER_URL + "/session/logout";
    $.ajax({
      type: "GET",
      dataType: JSON_TYPE,
      url: fullURL,
      success: successCallbackFn
    });
  }

  function _creerCompte(compte, successCallbackFn) {
    var data =
      compte.nom + SEPARATOR + compte.domaine + SEPARATOR + compte.motDePasse +
      SEPARATOR + Date.now() +
      SEPARATOR + compte.profil + SEPARATOR + compte.email +
      SEPARATOR + compte.initiales + SEPARATOR + compte.langue;
    var encData = AesUtil.encrypt(data);
    // json = "{data: " + encData + "}";
    // $.ajax({
    //   type: "POST",
    //   dataType: JSON_TYPE,
    //   url: SERVER_URL + "/createLogin",
    //   data: json,
    //   contentType: "application/json; CHARSET=UTF-8",
    //   success: successCallbackFn
    // });
    $.ajax({
      type: "POST",
      dataType: "text",
      url: SERVER_URL + "/createLogin",
      data: encData,
      contentType: "text/plain; CHARSET=UTF-8",
      success: successCallbackFn
    });


  }


  /*
   * OPERATIONS "METIER" SUR LA GESTION DES CONSEILLERS
   */
  function _chargerCantons(successCallbackFn) {
    var fullURL = browser.Url.buildUrl(SERVER_URL, "cantons", JSON_TYPE);
    $.ajax({
      type: "GET",
      dataType: JSON_TYPE,
      url: fullURL,
      success: successCallbackFn
    });
  }

  function _chargerConseils(successCallbackFn) {
    var fullURL = browser.Url.buildUrl(SERVER_URL, "conseils", JSON_TYPE);
    $.ajax({
      type: "GET",
      dataType: JSON_TYPE,
      url: fullURL,
      success: successCallbackFn
    });
  }

  function _chargerPartis(successCallbackFn) {
    var fullURL = browser.Url.buildUrl(SERVER_URL, "partis", JSON_TYPE);
    $.ajax({
      type: "GET",
      dataType: JSON_TYPE,
      url: fullURL,
      success: successCallbackFn
    });
  }

  function _chargerConseillers(fmt, canton, conseil, parti, actuels, successCallbackFn) {
    var format = fmt.toLowerCase();
    var fullURL = browser.Url.buildUrl(SERVER_URL, "conseillers", format);
    fullURL += "/" + canton;
    fullURL += "/" + conseil;
    fullURL += "/" + parti;
    fullURL += "/" + actuels;
    // console.log('chargerConseillers: ' + fullURL);
    $.ajax({
      type: "GET",
      dataType: format,
      url: fullURL,
      success: successCallbackFn
    });
  }


  // interface : définition des méthodes publiques
  return {
    centraliserErreursHttp: _centraliserErreursHttp,
    chargerVue: _chargerVue,
    effectuerLogin: _effectuerLogin,
    effectuerLogout: _effectuerLogout,
    creerCompte: _creerCompte,
    lireVersion: _lireVersion,
    lireStatusSession: _lireStatusSession,
    chargerCantons: _chargerCantons,
    chargerConseils: _chargerConseils,
    chargerPartis: _chargerPartis,
    chargerConseillers: _chargerConseillers
  };

})();