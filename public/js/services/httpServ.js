/*
 * Couche de services "httpServ" (worker) permettant de centraliser toutes
 * les requêtes HTTP à l'intention des contrôleurs. Comme un objet Java,
 * mais créé avec une "closure" exécutée immédiatement (IIFE).<br>
 * <br>
 * Cette version utilise maintenant des "promesses" pour retourner les résultats.
 * Il s'en suit que les requêtes peuvent maintenant parfaitement être chainées.
 *
 * @author Jean-Claude Stritt
 */

/* global browser, validator, AesUtil */

var httpServ = (() => {
  var JSON_TYPE = 'json';
  var TEXT_TYPE = 'text';
  var SEPARATOR = '♂♥♀';

  // récupération de la racine du nom de domaine du serveur
  var serverURL = browser.Url.getBaseUrl(); // si client et serveur au même endroit
  if (serverURL.includes('jcsinfo.ch')) {
    serverURL += '/parlement';
  }
  console.log("server URL: "+serverURL);

  $.ajaxSetup({
    // error: function (jqXHR, exception) {
    //   var errIdx;
    //   if (jqXHR.status === 500) {
    //     errIdx = 0; // 500 - Internal server error
    //   } else if (jqXHR.status === 0) {
    //     errIdx = 1; // 503 - Service unavailable
    //   } else if (jqXHR.status === 404) {
    //     errIdx = 2; // 404 - Not found
    //   } else if (exception === 'parsererror') {
    //     errIdx = 3; // 401 - Parsing error
    //   } else if (exception === 'timeout') {
    //     errIdx = 4; // 408 - Timeout
    //   } else if (exception === 'abort') {
    //     errIdx = 5; // 413 - Aborted
    //   } else {
    //     errIdx = 6; // 400 - Bad request
    //   }
    //   httpErrorCallbackFn(errIdx);
    // },
    xhrFields: { // pour utiliser le cookie de session
      withCredentials: true
    },
    cache: false
  });

 
  /*
   * CHARGEMENT D'UNE SOUS-VUE (HTTP GET)
   */
  function _chargerVue(nomVue) {
    var nomFichierHtml = 'views/' + nomVue + '.html';
    var nomFichierCtrl = 'js/controllers/' + nomVue + 'Ctrl.js';
    //    var nomFichierVal = 'js/validators/' + nomVue + 'Validator.js';

    // chargement de la vue
    $('#view').load(nomFichierHtml, () => {
      console.debug(nomFichierHtml + ' OK !');

      // chargement du controleur de la vue
      $.getScript(nomFichierCtrl, () => {
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
  function _lireVersion() {
    return new Promise((resolve, reject) => {
      $.ajax({
        type: "GET",
        dataType: JSON_TYPE,
        url: serverURL + "/version",
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }

  function _lireStatusSession() {
    return new Promise((resolve, reject) => {
      $.ajax({
        type: "GET",
        dataType: JSON_TYPE,
        url: serverURL + "/session/status",
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }


  /*
   * OPERATIONS "METIER" CONCERNANT LA GESTION DU LOGIN ET LA CREATION DE COMPTE
   */
  function _effectuerLogin(login) {
    return new Promise((resolve, reject) => {
      let data = login.nom + SEPARATOR + login.domaine + SEPARATOR +
        login.motDePasse + SEPARATOR + Date.now();
      let encData = AesUtil.encrypt(data);
      $.ajax({
        type: "GET",
        dataType: JSON_TYPE,
        url: serverURL + "/session/login/" + encData,
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }

  function _effectuerLogout() {
    return new Promise((resolve, reject) => {
      $.ajax({
        type: "GET",
        dataType: JSON_TYPE,
        url: serverURL + "/session/logout",
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }

  function _creerCompte(compte) {
    return new Promise((resolve, reject) => {
      let data =
        compte.nom + SEPARATOR + compte.domaine + SEPARATOR + compte.motDePasse +
        SEPARATOR + Date.now() +
        SEPARATOR + compte.profil + SEPARATOR + compte.email +
        SEPARATOR + compte.initiales + SEPARATOR + compte.langue;
      let encData = AesUtil.encrypt(data);
      $.ajax({
        type: "POST",
        dataType: TEXT_TYPE,
        url: serverURL + "/createLogin",
        data: encData,
        contentType: "text/plain; CHARSET=UTF-8",
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }


  /*
   * OPERATIONS "METIER" SUR LA GESTION DES CONSEILLERS
   */
  function _chargerCantons() {
    return new Promise((resolve, reject) => {
      $.ajax({
        type: "GET",
        dataType: JSON_TYPE,
        url: browser.Url.buildUrl(serverURL, "cantons", JSON_TYPE),
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }

  function _chargerConseils() {
    return new Promise((resolve, reject) => {
      $.ajax({
        type: "GET",
        dataType: JSON_TYPE,
        url: browser.Url.buildUrl(serverURL, "conseils", JSON_TYPE),
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }

  function _chargerPartis() {
    return new Promise((resolve, reject) => {
      $.ajax({
        type: "GET",
        dataType: JSON_TYPE,
        url: browser.Url.buildUrl(serverURL, "partis", JSON_TYPE),
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }

  function _chargerConseillers(fmt, canton, conseil, parti, actuels) {
    return new Promise((resolve, reject) => {
      let format = fmt.toLowerCase();
      let fullURL = browser.Url.buildUrl(serverURL, "conseillers", format);
      fullURL += "/" + canton;
      fullURL += "/" + conseil;
      fullURL += "/" + parti;
      fullURL += "/" + actuels;
      // console.log('chargerConseillers: ' + fullURL);
      $.ajax({
        type: "GET",
        dataType: format,
        url: fullURL,
        success: function (data) {
          resolve(data)
        },
        error: function (error) {
          reject(error)
        }
      });
    });
  }


  // interface : définition des méthodes publiques
  return {
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