/*
 * Worker pour gerer une carte GoogleMap avec des marqueurs sur des conseillers
 * nationaux. Un tableau interne mémorise les marqueurs produits.
 *
 * @author J.-C. Stritt
 */

/* global google */

var googleMapWrk = (() => {

  // hashmap des cantons avec leur capitale
  var mapCantons = {};
  mapCantons['AG'] = new Capitale("Aarau", 47.391798, 8.051128);
  mapCantons['AI'] = new Capitale("Appenzell", 47.335324, 9.406353);
  mapCantons['AR'] = new Capitale("Herisau", 47.390909, 9.271962);
  mapCantons['BE'] = new Capitale("Berne", 46.947852, 7.451173);
  mapCantons['BL'] = new Capitale("Liestal", 47.485036, 7.731649);
  mapCantons['BS'] = new Capitale("Bâle", 47.559823, 7.587593);
  mapCantons['FR'] = new Capitale("Fribourg", 46.802930, 7.152298);
  mapCantons['GE'] = new Capitale("Genève", 46.205737, 6.146148);
  mapCantons['GL'] = new Capitale("Glaris", 47.035369, 9.063203);
  mapCantons['GR'] = new Capitale("Coire", 46.850709, 9.529901);
  mapCantons['JU'] = new Capitale("Delémont", 47.365951, 7.346139);
  mapCantons['LU'] = new Capitale("Lucerne", 47.049837, 8.309084);
  mapCantons['NE'] = new Capitale("Neuchâtel", 46.990343, 6.925422);
  mapCantons['NW'] = new Capitale("Stans", 46.957432, 8.364180);
  mapCantons['OW'] = new Capitale("Sarnen", 46.894638, 8.247804);
  mapCantons['SG'] = new Capitale("Saint Gall", 47.424873, 9.374528);
  mapCantons['SH'] = new Capitale("Shaffouse", 47.696651, 8.637090);
  mapCantons['SO'] = new Capitale("Soleure", 47.208895, 7.531588);
  mapCantons['SZ'] = new Capitale("Schwytz", 47.020889, 8.649292);
  mapCantons['TG'] = new Capitale("Frauenfeld", 47.553474, 8.896944);
  mapCantons['TI'] = new Capitale("Belinzone", 46.194425, 9.024568);
  mapCantons['UR'] = new Capitale("Altdorf", 46.882354, 8.641225);
  mapCantons['VD'] = new Capitale("Lausanne", 46.519757, 6.629147);
  mapCantons['VS'] = new Capitale("Sion", 46.232825, 7.360458);
  mapCantons['ZG'] = new Capitale("Zoug", 47.165987, 8.515546);
  mapCantons['ZH'] = new Capitale("Zürich", 47.376504, 8.539116);

  // gestion interne d'un tableau de marqueurs (pour l'effacement)
  var marqueurs = [];

  // création d'une fenêtre d'info pour popup après clic sur marqueur
  var infowindow = new google.maps.InfoWindow({
    maxWidth: 400,
    pixelOffset: new google.maps.Size(0, -30)
  });


  /**
   * Contruire les options d'une Google Map.
   *
   * @param {type} niveauZoom : le niveau de zoom de la carte, 8 semble bien
   * @param {type} typeCarte : le type de carte (ROADMAP, SATELLITE, HYBRID ou TERRAIN)
   * @param {type} cantonCentrage : le canton dont la capitale va centrer la carte
   *
   * @returns {options} un objet JSON avec les options de la carte (voir API Google MAP)
   */
  function _construireOptionsCarte(niveauZoom, typeCarte, cantonCentrage) {

    // on récupere la capitale pour le canton fourni
    let capitale = mapCantons[cantonCentrage];

    // on crée un objet "latitude-longitude" de l'API GoogleMap pour la capitale trouvée
    let latLng = new google.maps.LatLng(capitale.lat, capitale.lng);

    // on crée les options de la carte et on les retourne
    let options = {
      zoom: niveauZoom,
      center: latLng,
      mapTypeId: typeCarte
    };
    return options;
  }

  /**
   * Trouver la geolocalisation d'un conseiller sous la forme d'une "promesse".
   * Pourquoi "promesse", car c'est une opération asynchrone vers l'API de google qui nous
   * fournit la géolocation d'une conseiller (objet avec les 2 propriétés latitude-longitude).
   * 
   * @param {*} geocoder l'objet Google pour trouver une geolocalisation
   * @param {*} conseiller la source pour la trouver
   */
  function _geolocaliserUnConseiller(geocoder, conseiller) {
    return new Promise((resolve, reject) => {

      // récupération de la citoyenneté
      let citizenship = conseiller.citoyennete.split(',');
      let address = citizenship[0].replace('(', '').replace(')', '');
      
      // essaye de trouver la geolocalisation  de cette adresse
      geocoder.geocode({ 'address': address}, (results, status) => {
        
        // crée un objet qui permettra de créer un marqueur
        let location = {};
        location.name = conseiller.toString();
        location.address = address;
        
        // on détermine la géolocalisation (trouvée ou de la capitale)
        if (status === 'OK') {
          location.latLng = results[0].geometry.location;
        } else {
          let capitale = mapCantons[conseiller.canton.abrev];
          location.latLng = new google.maps.LatLng(capitale.lat, capitale.lng);
        }

        // résoud la promesse de découverte de la géolocalisation
        resolve(location);
      });
    })
  }

  /**
   * Créer un tableau de "promesses" de geolocalisation de chaque conseiller d'une liste.
   * Pourquoi "promesse", car c'est une opération asynchrone vers l'API de google qui nous
   * fournit chaque géolocation (objet avec les 2 propriétés latitude-longitude).
   * 
   * @param {*} geocoder l'objet Google pour trouver une geolocalisation
   * @param {*} conseillers une liste de conseillers
   */
  function _geolocaliserLesConseillers(geocoder, conseillers) {
    let results = [];

    // si la liste des conseillers est définie, on boucle sur ces conseillers
    if (conseillers) {
      for (let i = 0; i < conseillers.length; i++) {
        let conseiller = new Conseiller(conseillers[i]);
        results.push(_geolocaliserUnConseiller(geocoder, conseiller));
      }
    }
    return results;
  }

  /**
   * Construit une fonction qui sera appelée après un clic sur un marqueur.
   */
  function _infoFn(map, location) {
    return function (e) {
      let content =
        // '<div>' +
        '<div>' + location.name + '</div>' +
        '<div>' + location.address + '</div>'
        // '</div>';
      infowindow.setContent(content);
      infowindow.open(map);
      infowindow.setPosition(location.latLng);
    }
  };

  /**
   * Créer un objet google.maps.Marker grâce à l'objet localisation fourni.
   * Exemple :  {"name":"Berset Alain (1972), FR, PSS","latLng":{"lat":46.8641022,"lng":7.084824499999968}}
   * 
   * @param {*} location un objet de localisation 
   */
  function _creerUnMarqueur(map, location, show) {
    infowindow.close();
    let marqueur = new google.maps.Marker({
      title: location.name + "\n" + location.address,
      position: location.latLng,
      draggable: false,
      animation: google.maps.Animation.DROP,
      // label: {
      //   color: 'Crimson',
      //   fontWeight: 'bold',
      //   fontSize: '8pt',
      //   text: location.name,
      // },
      // icon: {
      //   labelOrigin: new google.maps.Point(11, 50),
      //   url: "images/marker_red.png",
      //   size: new google.maps.Size(22, 40),
      //   origin: new google.maps.Point(0, 0),
      //   anchor: new google.maps.Point(11, 40),
      // }
    });

    // ajoute un écouteur pour afficher une popup
    let fn = _infoFn(map, location);
    google.maps.event.addListener(marqueur, 'click', fn);

    if (show) {
      fn(null);
    }

    // ajoute le marqueur à la liste interne
    marqueurs.push(marqueur);

    // retourne le marqueur
    return marqueur;
  }



  /**
   * Effacer tous les marqueurs memorisés dans le tableau interne des marqueurs.
   *
   * @returns {undefined} : pas de retour
   */
  function _effacerLesMarqueurs() {

    // on boucle sur tous les marqueurs mémorisés
    for (let i = 0; i < marqueurs.length; i++) {

      // on met le marqueur à "null" sur la carte pour l'effacer
      marqueurs[i].setMap(null);
    }

    // on reinitialise à "vide", le tableau des marqueurs
    marqueurs = [];
  }


  // interface : définition des méthodes publiques (à gauche)
  return {
    construireOptionsCarte: _construireOptionsCarte,
    geolocaliserUnConseiller: _geolocaliserUnConseiller,
    geolocaliserLesConseillers: _geolocaliserLesConseillers,
    creerUnMarqueur: _creerUnMarqueur,
    effacerLesMarqueurs: _effacerLesMarqueurs
  };
})();