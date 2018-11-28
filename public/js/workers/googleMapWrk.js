/*
 * Worker pour gerer une carte GoogleMap avec des marqueurs sur des conseillers
 * nationaux. Un tableau interne mémorise les marqueurs produits.
 *
 * @author L. Waeber & J.-C. Stritt
 */

/* global google */

var googleMapWrk = (function () {

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
    var capitale = mapCantons[cantonCentrage];

    // on crée un objet "latitude-longitude" de l'API GoogleMap pour la capitale trouvée
    var latLng = new google.maps.LatLng(capitale.lat, capitale.long);

    // on crée les options de la carte et on les retourne
    var options = {
      zoom: niveauZoom,
      center: latLng,
      mapTypeId: typeCarte
    };
    return options;
  }


  /**
   * Créer un marqueur pour un conseiller.
   * Celui-ci est retourné, mais également poussé dans le tableau interne des marqueurs.
   *
   * @param {type} conseiller : un bean "conseiller"
   * @returns {google.maps.Marker} : un marqueur
   */
  function _creerMarqueurConseiller(conseiller) {

    // on récupère la capitale avec le canton du conseiller
    var capitale = mapCantons[conseiller.canton.abrev];

    // on crée un objet "latitude-longitude" de l'API GoogleMap pour la capitale trouvée
    var latLng = new google.maps.LatLng(capitale.lat, capitale.long);

    // on crée un marqueur avec la classe google.maps.Marker
    var marqueur = new google.maps.Marker({
      position: latLng,
      title: conseiller.toString(),
      draggable: true,
      animation: google.maps.Animation.DROP
    });

    // on pousse ce marqueur à la fin du tableau interne des marqueurs
    marqueurs.push(marqueur);

    // on retourne le marqueur
    return marqueur;
  }


  /**
   * Créer un tableau de marqueurs pour une liste de conseillers fournie.
   * Ceux-ci sont retournés, mais également stockés dans le tableau interne des marqueurs.
   *
   * @param {type} conseiller : un bean "conseiller"
   * @returns {google.maps.Marker} : un tableau de marqueurs
   */
  function _creerMarqueursConseillers(conseillers) {
  
    // si la liste des conseillers est définie, on boucle sur ces conseillers
    if (conseillers) {
      for (var i = 0; i < conseillers.length; i++) {
        var cons = new Conseiller(conseillers[i]);
        marqueurs[i] = _creerMarqueurConseiller(cons);
      }
    }

    // on retourne la liste interne des marqueurs
    return marqueurs;
  }


  /**
   * Effacer tous les marqueurs memorisés dans le tableau interne des marqueurs.
   *
   * @returns {undefined} : pas de retour
   */
  function _effacerTousLesMarqueurs() {

    // on boucle sur tous les marqueurs mémorisés
    for (var i = 0; i < marqueurs.length; i++) {

      // on met le marqueur à "null" sur la carte pour l'effacer
      marqueurs[i].setMap(null);
    }

    // on reinitialise à "vide", le tableau des marqueurs
    marqueurs = [];
  }


  // interface : definition des methodes publiques (a gauche)
  return {
    construireOptionsCarte: _construireOptionsCarte,
    creerMarqueurConseiller: _creerMarqueurConseiller,
    creerMarqueursConseillers: _creerMarqueursConseillers,
    effacerTousLesMarqueurs: _effacerTousLesMarqueurs
  };
})();


