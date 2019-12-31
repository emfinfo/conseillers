/*
 * Contrôleur de la vue "conseillers".
 *
 * @author Jean-Claude Stritt
 */

/* global httpServ, googleMapWrk, google, localStorageWrk */

var ctrl = (() => {

  // valeurs globales par défaut
  var canton = 'CH';
  var conseil = 'CF';
  var parti = 'tous';
  var format = 'JSON';
  var actuels = true;
  var details = false;

  // pour mémoriser les conseillers de la dernière requête
  var dernListeConseillers;

  // variables pour googlemap
  var map;
  var geocoder;


  /*
   * 1. DEMARRAGE (DOM PRET)
   */
  $(document).ready(() => { // comme méthode start en Java

    // pour récupérer les dates correctement
    Date.setInputPattern("YYYY-MM-DD");

    // récupération dans le DOM des composants principaux de la vue
    let cmbCantons = $("#cmbCantons");
    let cmbConseils = $("#cmbConseils");
    let cmbPartis = $("#cmbPartis");
    let cbxActuels = $("#cbxActuels");
    let cbxDetails = $("#cbxDetails");

    // met les valeurs par défaut dans la vue (canton défini après le chargement des cantons)
    cbxActuels.attr("checked", actuels);
    cbxDetails.attr("checked", details);

    // création d'écouteurs sur des changements dans la vue
    cmbCantons.change((event) =>  {
      canton = cmbCantons.val();
      _majConseillers();
    });

    cmbConseils.change((event) =>  {
      conseil = cmbConseils.val();
      _majConseillers();
    });

    cmbPartis.change((event) =>  {
      parti = cmbPartis.val();
      _majConseillers();
    });

    cbxActuels.change((event) => {
      actuels = cbxActuels.is(':checked');
      _majConseillers();
    });

    cbxDetails.change((event) => {
      details = cbxDetails.is(':checked');
      _afficherLesConseillers(dernListeConseillers);
    });

    // affiche la carte GoogleMap de la Suisse centree sur Berne
    _afficherCarteSuisse();
    
    // chargement des premières données depuis le serveur
    httpServ.lireVersion().then(data => {
      _afficherVersion(data);
      httpServ.chargerCantons().then(data => {
        _afficherCantons(data);
        httpServ.chargerConseils().then(data => {
          _afficherConseils(data);
          httpServ.chargerPartis().then(data => {
            _afficherPartis(data);
            _majConseillers();
          });
        }).catch(error => {
          console.log("Erreur: "+error);
        });
      })
    });
  });


  /*
   * 2. METHODES D'AFFICHAGE
   */
  function _afficherCarteSuisse() {
    // type de carte possible : google.maps.MapTypeId.SATELLITE, ROADMAP, HYBRID, TERRAIN
    let options = googleMapWrk.construireOptionsCarte(8, google.maps.MapTypeId.ROADMAP, "LU");

    // recuperation du calque dans le DOM
    let canvas = $("#map_canvas")[0];

    // création des objets de l'API googlemap (affichage de la carte)
    map = new google.maps.Map(canvas, options);
    geocoder = new google.maps.Geocoder();
  }

  function _afficherVersion(data) {
    let infoComponent1 = $("#release-application");
    infoComponent1.html(data["application"]);
    let infoComponent2 = $("#release-data");
    infoComponent2.html('  au ' + data["data"]);
    let infoComponent3 = $("#release-server");
    infoComponent3.html(data["server"]);
  }

  function _afficherStatusSession(data) {
    let infoComponent1 = $("#sessionStatus");
    infoComponent1.html("" + data.open);
    // _majConseillers();
    // httpServ.chargerConseillers(format, canton, conseil, parti, actuels, _okChargerConseillers);
  }

  function _afficherCantons(cantons) {
    let cmbCantons = $("#cmbCantons");
    $.each(cantons, (i, canton) => {
      $('<option value="' + canton.abrev + '">' + canton.abrev +
        '</option>').appendTo(cmbCantons);
    });
    cmbCantons.val(canton);
  }

  function _afficherConseils(conseils) {
    let cmbConseils = $("#cmbConseils");
    $.each(conseils, (i, conseil) => {
      $('<option value="' + conseil.abrev + '">' + conseil.abrev +
        '</option>').appendTo(cmbConseils);
    });
    cmbConseils.val(conseil);
  }

  function _afficherPartis(partis) {
    let cmbPartis = $("#cmbPartis");
    $.each(partis, (i, parti) => {
      //      console.log("parti: " + JSON.stringify(parti))
      $('<option value="' + parti.abrev + '">' + parti.abrev +
        '</option>').appendTo(cmbPartis);
    });
    cmbPartis.val(parti);
  }

  function _afficherUnConseiller(idx, conseiller, compDOM) {
    let couleur = '#4977B6';
    if (conseiller.actif) {
      couleur = 'green';
    }
    let decede = conseiller.dateDeces || (!conseiller.dateNaissance && !conseiller.dateDeces);
    if (decede) {
      couleur = 'brown';
    }

    // préparation pour un affichage avec lien cliquable
    compDOM.append('<li style="list-style-type: none"><a style="color:' + couleur +
      '" href="javascript:ctrl.afficherUnMarqueur(' + idx + ');">' +
      (idx + 1) + '. ' + conseiller + '</a>');

    // boucle interne sur les différentes activités d'un conseiller
    if (details && conseiller.activites) {
      compDOM.append('<ul style="margin: 0; padding: 0; list-style-type: none">');
      $.each(conseiller.activites, (i, activite) => {
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
    let divData = $('#data');
    divData.html('');
    // style="list-style-type: decimal-leading-zero
    divData.append('<ul id="lesConseillers">');

    // on boucle pour ajouter chaque conseiller
    $.each(conseillers, (idx, conseiller) => {

      // pour bénéficier du toString() et de la conversion de date
      let cons = new Conseiller(conseiller);

      // idem pour les activités
      let activites = [];
      $.each(cons.activites, (j, act) => {
        activites.push(new Activite(act));
      });
      cons.activites = activites;

      // on affiche un conseiller
      _afficherUnConseiller(idx, cons, divData);
    });
    divData.append('</ol>');
  }


  /*
   * 3. FONCTIONS NECESSAIRES AUX ACTIONS DANS LA VUE
   */
  function _majConseillers() {
    var divData = $('#data');
    divData.html('...');

    // requête vers le serveur
    httpServ.chargerConseillers(format, canton, conseil, parti, actuels).then(data => {
      if ('message' in data) {
        let errNbr = parseInt(data.message.split(" - ")[0]);
        if (errNbr === 403) {
          swal(data.message, "Vous ne pouvez accéder aux opérations sur les conseillers", "warning");
        } else if (errNbr === 408) {
          swal(data.message, "Veuillez vous reloguer svp", "info");
          _effectuerLogout();
          httpServ.chargerVue("login");
        } else {
          swal(data.message, "Veuillez vous reloguer svp", "error");
        }
      } else {
        _afficherLesConseillers(data);
        _afficherTousLesMarqueurs();
        httpServ.lireStatusSession().then(data => {
          _afficherStatusSession(data);
        });
      }
    });
  }

  function _effectuerLogout() {
    httpServ.effectuerLogout().then(data => {
      httpServ.chargerVue('login');
    });
  }

  function _afficherUnMarqueur(idx) {

    // effacer tous les marqueurs
    googleMapWrk.effacerLesMarqueurs();

    // recuperer le conseiller selectionne
    let conseiller = new Conseiller(dernListeConseillers[idx]);

    // chercher les coordonnées d'un conseiller sous la forme d'une promesse
    let promesse = googleMapWrk.geolocaliserUnConseiller(geocoder, conseiller);

    // résoudre la promesse et créer un marqueur
    promesse.then((location) => {

      // créer un marqueur
      let marqueur = googleMapWrk.creerUnMarqueur(map, location, true);

      // afficher ce nouveau marqueur
      marqueur.setMap(map);

    });
  }

  function _afficherTousLesMarqueurs() {

    // effacer tous les marqueurs
    googleMapWrk.effacerLesMarqueurs();

    // geolocaliser tous les conseillers sous la forme d'un tableau de promesses
    let locations = googleMapWrk.geolocaliserLesConseillers(geocoder, dernListeConseillers);

    // résoud le tableau de toutes les promesses
    Promise.all(locations).then((locations) => {
      for (let i = 0; i < locations.length; i++) {

        // créer un marqueur
        let marqueur = googleMapWrk.creerUnMarqueur(map, locations[i], false);

        // afficher ce nouveau marqueur
        marqueur.setMap(map);

      }
    }).catch((e) => {
      console.log("erreur: " + e)
    });

  }


  /*
   * 4. INTERFACE (définition des méthodes publiques (à gauche)
   */
  return {
    effectuerLogout: _effectuerLogout,
    afficherUnMarqueur: _afficherUnMarqueur,
    afficherTousLesMarqueurs: _afficherTousLesMarqueurs
  };


})();