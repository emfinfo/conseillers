/*
 * Contrôleur de la vue "compte".
 *
 * @author Jean-Claude Stritt
 */

/* global httpServ */

var ctrl = (() => {

  /*
   * 1. DOM PRET : initialisations diverses
   */
  //  $(document).ready(() => {
  //  });


  /*
   * 2. LECTURE OU ECRITURE DANS LA VUE
   */
  function _lireInfosCompte() {
    var nom = $("#txtNom").val();
    var mdp = $("#txtMotDePasse").val();
    var domaine = $("#selDomaine option:selected").text();
    var profil = (domaine === "EDU") ? "prof" : "eleve";
    var email = $("#txtEmail").val();
    var initiales = $("#txtInitiales").val();
    var langue = $("input[name='langues']:checked").val();
    var compte = {
      nom: nom,
      motDePasse: mdp,
      domaine: domaine,
      profil: profil,
      email: email,
      initiales: initiales.toUpperCase(),
      langue: langue.toUpperCase()
    };
    return compte;
  }


  /*
   * 3. METHODES NECESSAIRES AUX ACTIONS DANS LA VUE
   */
  function _enregistrerCompte() {
    var compte = _lireInfosCompte();
    if (!compte.nom || !compte.motDePasse) {
      swal("Nom et mot de passe sont obligatoires !", "Veuillez compléter svp", "warning");      
    } else {
      httpServ.creerCompte(compte).then(data => {
        var login = JSON.parse(data);
        if (login.nom) {
          httpServ.chargerVue("login");
          swal("OK, le compte a été créé !", login.nom + " - " + login.domaine, "info");
        } else {
          swal("Le compte existe déjà !", "Veuillez changer de nom et/ou de domaine", "warning");
        }
      });
    }  
  }

  function _retournerLogin() {
    httpServ.chargerVue("login");
  }

  // interface : définition des méthodes publiques
  return {
    enregistrerCompte: _enregistrerCompte,
    retournerLogin: _retournerLogin
  };

})();