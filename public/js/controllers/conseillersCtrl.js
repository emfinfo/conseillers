/*
 * Contrôleur de la vue "conseillers".
 *
 * @author Jean-Claude Stritt
 */

/* global httpServ, googleMapWrk, google, localStorageWrk */

var ctrl = (function () {

  // valeurs globales par défaut
  var canton = 'CH';
  var conseil = 'CF';
  var parti = 'tous';
  var format = 'JSON';
  var actuels = true;
  var details = false;

  // pour mémoriser les conseillers de la dernière requête
  var dernListeConseillers;

  // pour memoriser une fois pour toute la map de la suisse
  var map;


  /*
   * 1. DEMARRAGE (DOM PRET)
   */
  $(document).ready(function () { // comme méthode start en Java

    // pour récupérer les dates correctement
    Date.setInputPattern("YYYY-MM-DD");

    // récupération dans le DOM des composants principaux de la vue
    var cmbCantons = $("#cmbCantons");
    var cmbConseils = $("#cmbConseils");
    var cmbPartis = $("#cmbPartis");
    var cbxActuels = $("#cbxActuels");
    var cbxDetails = $("#cbxDetails");

    // chargement des premières données depuis le serveur (par HTTP)
    httpServ.lireVersion(_okLireVersion);
    httpServ.lireStatusSession(_okLireStatusSession);
    httpServ.chargerCantons(_okChargerCantons);
    httpServ.chargerConseils(_okChargerConseils);
    httpServ.chargerPartis(_okChargerPartis);

    // met les valeurs par défaut dans la vue (canton défini après le chargement des cantons)
    cbxActuels.attr("checked", actuels);
    cbxDetails.attr("checked", details);

    // création d'écouteurs sur des changements dans la vue
    cmbCantons.change(function (event) {
      canton = cmbCantons.val();
      _majConseillers();
    });

    cmbConseils.change(function (event) {
      conseil = cmbConseils.val();
      _majConseillers();
    });

    cmbPartis.change(function (event) {
      parti = cmbPartis.val();
      _majConseillers();
    });

    cbxActuels.change(function (event) {
      actuels = cbxActuels.is(':checked');
      _majConseillers();
    });

    cbxDetails.change(function (event) {
      details = cbxDetails.is(':checked');
      _afficherLesConseillers(dernListeConseillers);
    });

    // affiche la carte GoogleMap de la Suisse centree sur Berne
    _afficherCarteSuisse();

    // maj la liste des conseillers
     _majConseillers();

  });


  /*
   * 2. LECTURE OU ECRITURE DANS LA VUE
   */
  function _afficherCarteSuisse() {
    // type de carte possible : google.maps.MapTypeId.SATELLITE, ROADMAP, HYBRID, TERRAIN
    var options = googleMapWrk.construireOptionsCarte(8, google.maps.MapTypeId.ROADMAP, "BE");

    // recuperation du calque dans le DOM
    var canvas = $("#map_canvas")[0];

    // affichage de la carte
    map = new google.maps.Map(canvas, options);
  }

  function _majConseillers() {
    var divData = $('#data');
    divData.html('...');
    httpServ.chargerConseillers(format, canton, conseil, parti, actuels, _okChargerConseillers);
  }  

  function _afficherUnConseiller(idx, conseiller, compDOM) {
    //    console.log("_afficherUnConseiller: " + JSON.stringify(conseiller));
    var couleur = '#4977B6';
    if (conseiller.actif) {
      couleur = 'green';
    }
    var decede = conseiller.dateDeces || (!conseiller.dateNaissance && !conseiller.dateDeces);
    if (decede) {
      couleur = 'brown';
    }

    // preparation pour un affichage avec lien cliquable
    compDOM.append('<li style="list-style-type: none"><a style="color:' + couleur 
      + '" href="javascript:ctrl.afficherMarqueurConseiller(' + idx + ');">' 
      + (idx+1) + '. ' + conseiller + '</a>');

    // boucle interne sur les différentes activités d'un conseiller
    if (details && conseiller.activites) {
      compDOM.append('<ul style="margin: 0; padding: 0; list-style-type: none">');
      $.each(conseiller.activites, function (i, activite) {
        compDOM.append('<li style="color:gray; margin-left: 1.25em">' + activite + '</li>');
      });
      compDOM.append('</ul>');
    }
    compDOM.append('</li>');
  }

  function _afficherLesConseillers(conseillers) {

    // on sauve la liste des conseillers
    dernListeConseillers = conseillers;

    // on récupère le composant du DOM
    var divData = $('#data');
    divData.html('');
    // style="list-style-type: decimal-leading-zero
    divData.append('<ul id="lesConseillers">');

    // on boucle pour ajouter chaque conseiller
    $.each(conseillers, function (idx, cons) {

      // pour bénéficier du toString() et de la conversion de date
      var cons = new Conseiller(cons);

      // idem pour les activités
      var activites = [];
      $.each(cons.activites, function (j, act) {
        activites.push(new Activite(act));
      });
      cons.activites = activites;

      // on affiche un conseiller
      _afficherUnConseiller(idx, cons, divData);
    });
    divData.append('</ol>');
  }


  /*
   * 3. FONCTIONS DE CALLBACKS (RETOUR DE REQUETES HTTP)
   */
  function _okLireVersion(data, text, jqXHR) {
    var lblVersionServeur = $("#versionServeur");
    lblVersionServeur.html(data['version-srv']);
  }

  function _okLireStatusSession(data, text, jqXHR) {
    var lblSessionStatus = $("#sessionStatus");
    lblSessionStatus.html("" + data.open);
  }

  function _okChargerCantons(cantons, text, jqXHR) {
    var cmbCantons = $("#cmbCantons");
    $.each(cantons, function (i, canton) {
      $('<option value="' + canton.abrev + '">' + canton.abrev +
        '</option>').appendTo(cmbCantons);
    });
    cmbCantons.val(canton);
  }

  function _okChargerConseils(conseils, text, jqXHR) {
    var cmbConseils = $("#cmbConseils");
    $.each(conseils, function (i, conseil) {
      $('<option value="' + conseil.abrev + '">' + conseil.abrev +
        '</option>').appendTo(cmbConseils);
    });
    cmbConseils.val(conseil);
  }

  function _okChargerPartis(partis, text, jqXHR) {
    var cmbPartis = $("#cmbPartis");
    $.each(partis, function (i, parti) {
      //      console.log("parti: " + JSON.stringify(parti))
      $('<option value="' + parti.abrev + '">' + parti.abrev +
        '</option>').appendTo(cmbPartis);
    });
    cmbPartis.val(parti);
    //    _majConseillers();
  }

  function _okChargerConseillers(data, text, jqXHR) {
    if ('message' in data) {
      var errNbr = parseInt(data.message.split(" - ")[0]);
      if (errNbr === 403) {
        swal(data.message, "Vous ne pouvez accéder aux opérations sur les conseillers", "warning");
      } else if (errNbr === 408) {
        swal(data.message, "Veuillez vous reloguer svp", "info");
        httpServ.chargerVue("login");
      } else {
        swal(data.message, "Veuillez vous reloguer svp", "error");
      } 
    } else {
      _afficherLesConseillers(data);
      _afficherMarqueursConseillers(); // on peut éventuellement enlever cela
    }
  }

  function _okEffectuerLogout(data, text, jqXHR) {
    httpServ.chargerVue('login');
  }


  /*
   * 4. FONCTIONS NECESSAIRES AUX ACTIONS DANS LA VUE
   */
  function _effectuerLogout() {
    httpServ.effectuerLogout(_okEffectuerLogout);
  }

  function _afficherMarqueurConseiller(idx) {
    // effacer tous les marqueurs
    googleMapWrk.effacerTousLesMarqueurs();

    // recuperer le conseiller selectionne
    var conseiller = new Conseiller(dernListeConseillers[idx]);

    // creer un marqueur pour le conseiller
    var marqueur = googleMapWrk.creerMarqueurConseiller(conseiller);

    // afficher le nouveau marqueur
    marqueur.setMap(map);
  }

  function _afficherMarqueursConseillers() {
    // effacer tous les marqueurs
    googleMapWrk.effacerTousLesMarqueurs();

    // creer tous les marqueurs pour les conseillers affiches
    var marqueurs = googleMapWrk.creerMarqueursConseillers(dernListeConseillers);

    // afficher les marqueurs
    for (var i = 0; i < marqueurs.length; i++) {
      marqueurs[i].setMap(map);
    }
  }


  /*
   * 5. INTERFACE (définition des méthodes publiques (à gauche)
   */
  return {
    effectuerLogout: _effectuerLogout,
    afficherMarqueurConseiller: _afficherMarqueurConseiller,
    afficherMarqueursConseillers: _afficherMarqueursConseillers
  };


})();