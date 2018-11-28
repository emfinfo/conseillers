/*
 * Worker pour lire/écrire dans le "local storage" du navigateur.
 *
 * @author Jean-Claude Stritt
 */

/* global browser */

var localStorageWrk = (function () {
  var LS_KEY_LOGIN = "projet_conseillers";

  // initialisation cle login si inexistante dans localstorage
  if (!browser.LocalStorage.exist(LS_KEY_LOGIN)) {
    var login = {nom: "", motDePasse: "", memoriserOuiNon: false};
    browser.LocalStorage.write(LS_KEY_LOGIN, login);
  }

  // lire un login memorise dans localstorage avec la cle "ex18_login"
  function _lireLoginMemorise() {
    return browser.LocalStorage.read(LS_KEY_LOGIN);
  }

  // memoriser un login dans localstorage avec la cle "ex18_login"
  function _memoriserLogin(login) {
    browser.LocalStorage.write(LS_KEY_LOGIN, login);
  }

  // interface : methodes publiques (sur la gauche)
  return{
    lireLoginMemorise: _lireLoginMemorise,
    memoriserLogin: _memoriserLogin
  };

})();
