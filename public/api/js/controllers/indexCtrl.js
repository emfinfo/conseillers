/*
 * Contrôleur de la page API des conseillers.
 *
 * @author Jean-Claude Stritt
 */

/* global httpServ, AesUtil */

var indexCtrl = (() => {

  /*
   * 1. DOM PRET : chargement des premières données dans la vue
   */
  $(document).ready(() => {
    console.debug("DOM ready !!!");

    // chargement de la couche de service et des premières données par HTTP
    $.getScript("js/services/httpServ.js", () => {
//      console.log("httpServ.js chargé !");
      httpServ.getVersion(afficherVersion, afficherErreurHttp);
      _afficherRoutes();
    });

  });

  
  /*
   * 2. METHODES D'AFFICHAGE
   */
  function _afficherUneRoute(url) {
    var baseUrl = httpServ.getBaseUrl();
    if (baseUrl.includes('vps617676.ovh.net')) {
      baseUrl += '/parlement';
    }
    var href = baseUrl + url;
 
    // noeud du DOM à modifier
    var divData = $('#data');

    // test spécial sur les données du login qui doivent être encryptées
    var check = '/session/login/';
    if (url.includes(check)) {

      // extraction des paramètres du login (dont 3 obligatoires)
      var data = url.substring(check.length);
      var t = data.split('/');
      if (t.length >= 3) {

        // préparation des données de la requête avec timestamp
        data = t[0] + '/' + t[1] + '/' + t[2] + '/' + Date.now();

        // on ajoute éventuellement les paramètres supplémentaires
        if (t.length > 4) {
          for (i = 5; i < t.length; i++) {
            data += '/' + t[i];
          }
        }

        // encryptage des données de la requête
        var encData = AesUtil.encrypt(data);

        // création de l'URL
        href = baseUrl + check + encData;
      }
    }
//    divData.append('<li><a href="' + url + '" target="_blank">' + url + '</a></li>');
    divData.append('<li><a href="' + href + '" >' + baseUrl + url + '</a></li>');
  }

  function _afficherRoutes() {
    let baseUrl = httpServ.getBaseUrl();
    let divData = $('#data');
    divData.html('');

    // versions
    divData.append('<ul>');
    _afficherUneRoute('/version');
    divData.append('</ul>');

    // login-logout
    divData.append('<ul>');
    _afficherUneRoute('/session/login/mettrauxpa/edu/Emf123');
    _afficherUneRoute('/session/login/strittjc/edu/Emf123');
    _afficherUneRoute('/session/login/user1/studentfr/Emf123');
    _afficherUneRoute('/session/status');
    _afficherUneRoute('/session/logout');
    divData.append('</ul>');

    // createLogin
    divData.append('<ul>');
    _afficherUneRoute('/createLogin');
    divData.append('</ul>');

    // etat civil
    divData.append('<ul>');
    _afficherUneRoute('/etats-civils');
    _afficherUneRoute('/etats-civils.xml');
    _afficherUneRoute('/etats-civils.json');
    divData.append('</ul>');

    // canton
    divData.append('<ul>');
    _afficherUneRoute('/cantons');
    _afficherUneRoute('/cantons.xml');
    _afficherUneRoute('/cantons.json');
    divData.append('</ul>');

    // partis
    divData.append('<ul>');
    _afficherUneRoute('/partis');
    _afficherUneRoute('/partis.xml');
    _afficherUneRoute('/partis.json');
    divData.append('</ul>');

    // conseils
    divData.append('<ul>');
    _afficherUneRoute('/conseils');
    _afficherUneRoute('/conseils.xml');
    _afficherUneRoute('/conseils.json');
    divData.append('</ul>');

    // groupes
    divData.append('<ul>');
    _afficherUneRoute('/groupes');
    _afficherUneRoute('/groupes.xml');
    _afficherUneRoute('/groupes.json');
    divData.append('</ul>');

    // conseillers fribourgeois
    divData.append('<ul>');
    _afficherUneRoute('/conseillers/FR/tous/tous/true');
    _afficherUneRoute('/conseillers.xml/FR/tous/tous/true');
    _afficherUneRoute('/conseillers.json/FR/tous/tous/true');
    divData.append('</ul>');

    // conseillers nationaux au parti socialiste genevois
    divData.append('<ul>');
    _afficherUneRoute('/conseillers/GE/CN/PSS/true');
    _afficherUneRoute('/conseillers.xml/GE/CN/PSS/true');
    _afficherUneRoute('/conseillers.json/GE/CN/PSS/true');
    divData.append('</ul>');

    // conseillers fédéraux
    divData.append('<ul>');
    _afficherUneRoute('/conseillers/Suisse/tous/tous/true');
    _afficherUneRoute('/conseillers.xml/Suisse/tous/tous/true');
    _afficherUneRoute('/conseillers.json/Suisse/tous/tous/true');
    divData.append('</ul>');

  }


  /*
   * 3. CALLBACKS (RETOUR DE SERVICE)
   */
  function afficherVersion(data, text, jqXHR) {
    let infoComponent1 = $("#release-application");
    infoComponent1.html(data["application"]);
    let infoComponent2 = $("#release-data");
    infoComponent2.html(data["data"]);
    let infoComponent3 = $("#release-server");
    infoComponent3.html(data["server"]);
  }

  function afficherErreurHttp(jqXHR, textStatus, errorThrown) {
    let msg = textStatus + ": " + errorThrown + " " + jqXHR.responseText + " !";
    console.log(msg);
  }

})();

