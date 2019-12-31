/*
 * Contrôleur de la vue "login".
 *
 * @author Jean-Claude Stritt
 */

/* global httpServ, localStorageWrk */
var ctrl = (() => {

  /*
   * 1. DOM PRET : initialisations diverses
   */
  $(document).ready(() => { // comme methode start en Java
    var login = localStorageWrk.lireLoginMemorise();
    _afficherLogin(login);
  });


  /*
   * 2. LECTURE OU ECRITURE DANS LA VUE
   */

  // met à jour les données dans les champs du login
  function _afficherLogin(login) {
    var memorise = login.memoriserOuiNon === true;

    // récupère les composants visuels
    var txtUsername = $("#txtUsernameLogin");
    var txtPassword = $("#txtPasswordLogin");
    var selDomaine = $("#selDomaine");
    var cbxMemorise = $("#cbxMemoriserOuiNon");

    // change le contenu de ces composants
    if (login && memorise) {
      txtUsername.val(login.nom);
      txtPassword.val(login.motDePasse);
      selDomaine.val(login.domaine.toUpperCase());
    } else {
      txtUsername.val(null);
      txtPassword.val(null);
    }
    cbxMemorise.prop("checked", memorise);
  }

  // lire les champs dans la vue "login"
  function _lireInfosLogin() {
    var nom = $("#txtUsernameLogin").val();
    var mdp = $("#txtPasswordLogin").val();
    var domaine = $("#selDomaine option:selected").text().toLowerCase();
    var memoriser = $('#cbxMemoriserOuiNon').is(':checked');
    var login = {"nom": nom, "domaine": domaine, "motDePasse": mdp, "memoriserOuiNon": memoriser};
    // console.log("login: "+JSON.stringify(login));
    return login;
  }


  /*
   * 3. METHODES NECESSAIRES AUX ACTIONS DANS LA VUE
   */
  function _effectuerLogin() {
    var login = _lireInfosLogin();
    // console.info("_effectuerLogin: " + JSON.stringify(login));
    httpServ.effectuerLogin(login).then(data => {
      if (data.nom) {

        // on mémorise le login dans le localstorage
        var login = _lireInfosLogin();
        localStorageWrk.memoriserLogin(login);
  
        // on charge une nouvelle vue
        httpServ.chargerVue('conseillers');
      } else {
        swal("Accès à l'application refusé !", "Veuillez introduire des données valides svp", "warning");
      }
    });
  }

  function _creerCompte() {
    httpServ.chargerVue("compte");
  }

  // interface : définition des méthodes publiques
  return {
    effectuerLogin: _effectuerLogin,
    creerCompte: _creerCompte
  };

})();

