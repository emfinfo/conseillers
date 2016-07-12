/* global httpServ */

var indexCtrl = (function () {

  /*
   * 1. DOM PRET : chargement des premières données dans la vue
   */
  $(document).ready(function () {
    console.debug("DOM ready !!!");

    // chargement de la couche de service et des premières données par HTTP
    $.getScript("js/services/httpServ.js", function () {
      console.log("httpServ.js chargé !");
      httpServ.lireVersionServeur(okLireVersionServeur, afficherErreurHttp);

      _afficheListeRoutes();
    });

  });

  /*
   * 2. METHODES D'AFFICHAGE
   */
  function _addLI(url) {
    var divData = $('#data');
//    divData.append('<li><a href="' + url + '" target="_blank">' + url + '</a></li>');
    divData.append('<li><a href="' + url + '" >' + url + '</a></li>');
  }

  function _afficheListeRoutes() {
    var baseUrl = httpServ.recuperNomServeur();
    var divData = $('#data');
    divData.html('');

    // version
    divData.append('<ul>');
    _addLI(baseUrl + '/version');
    divData.append('</ul>');

    // canton
    divData.append('<ul>');
    _addLI(baseUrl + '/cantons');
    _addLI(baseUrl + '/cantons.xml');
    _addLI(baseUrl + '/cantons.json');
    divData.append('</ul>');

    // conseils
    divData.append('<ul>');
    _addLI(baseUrl + '/conseils');
    _addLI(baseUrl + '/conseils.xml');
    _addLI(baseUrl + '/conseils.json');
    divData.append('</ul>');

    // partis
    divData.append('<ul>');
    _addLI(baseUrl + '/partis');
    _addLI(baseUrl + '/partis.xml');
    _addLI(baseUrl + '/partis.json');
    divData.append('</ul>');

    // conseillers fribourgeois
    divData.append('<ul>');
    _addLI(baseUrl + '/conseillers/FR/CE/tous/true');
    _addLI(baseUrl + '/conseillers.xml/FR/CE/tous/true');
    _addLI(baseUrl + '/conseillers.json/FR/CE/tous/true');
    divData.append('</ul>');

    // conseillers nationaux au parti socialiste genevois
    divData.append('<ul>');
    _addLI(baseUrl + '/conseillers/GE/CN/Parti Socialiste Suisse/true');
    _addLI(baseUrl + '/conseillers.xml/GE/CN/Parti Socialiste Suisse/true');
    _addLI(baseUrl + '/conseillers.json/GE/CN/Parti Socialiste Suisse/true');
    divData.append('</ul>');

    // conseillers fédéraux
    divData.append('<ul>');
    _addLI(baseUrl + '/conseillers/Suisse/CF/tous/true');
    _addLI(baseUrl + '/conseillers.xml/Suisse/CF/tous/true');
    _addLI(baseUrl + '/conseillers.json/Suisse/CF/tous/true');
    divData.append('</ul>');

    // login-logout
    divData.append('<ul>');
    _addLI(baseUrl + '/login/mettrauxpa/Emf123');
    _addLI(baseUrl + '/login/strittjc/Emf123');
    _addLI(baseUrl + '/login/user1/Emf123');
    _addLI(baseUrl + '/sessionStatus');
    _addLI(baseUrl + '/logout');
    divData.append('</ul>');

    // createLogin
    divData.append('<ul>');
    _addLI(baseUrl + '/createLogin');
    divData.append('</ul>');
    divData.append('createLogin utilise une méthode HTTP de type POST pour créer un nouvel utilisateur. ');
    divData.append("Elle nécessite d'envoyer un objet JSON dans le corps (body) de la méthode, ex: " + '{"nom":"TartampionJ", "motDePasse":"Emf123"}. ');
    divData.append("D'autres champs peuvent être spécifiés: " + '"domaine", "profil", "email", "initiales", "langue". ');
    divData.append("Le retour est un objet " + '"login" rempli ou avec le nom=null si la création ' + "n'a pas pu se faire (login existant).");
    divData.append('<br>');

  }


  function afficherErreurHttp(jqXHR, textStatus, errorThrown) {
    var msg = textStatus + ": " + errorThrown + " " + jqXHR.responseText + " !";
    console.log(msg);
//    alert(msg);
  }


  /*
   * 3. CALLBACKS (RETOUR DE SERVICE)
   */
  function okLireVersionServeur(data, text, jqXHR) {
    var infoComponent = $("#versionServeur");
    infoComponent.html(data.versionServeur);
  }


})();

