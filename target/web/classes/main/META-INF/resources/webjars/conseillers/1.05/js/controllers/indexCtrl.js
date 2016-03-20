/* global httpServ */

var indexCtrl = (function () {

  // valeurs globales par défaut
  var canton = 'FR';
  var conseil = 'tous';
  var parti = 'tous';
  var format = 'JSON';
  var actuels = true;
  var details = false;
  
  // pour mémoriser les conseillers de la dernière requête
  var dernListeConseillers;
  var dernFormat;

  /*
   * 1. METHODES D'AFFICHAGE
   */
  function afficherUnConseiller(conseiller, compDOM) {
    var couleur = 'dodgerblue';
    if (!conseiller.dateSortie && !conseiller.dateDeces) {
      couleur = 'green';
    }
    if (conseiller.dateDeces) {
      couleur = 'darkgray';
    }
    compDOM.append('<li style="color:' + couleur + '">' + conseiller);
//    console.debug("Conseiller: " +JSON.stringify(conseiller));

    // boucle interne sur les différentes activités d'un conseiller
    if (details && conseiller.activites) {
      compDOM.append('<ul style="margin: 0; padding: 0; list-style-type: circle">');
      $.each(conseiller.activites, function (i, activite) {
        compDOM.append('<li style="color:gray; margin-left: 1.25em">' + activite + '</li>');
      });
      compDOM.append('</ul>');
    }
    compDOM.append('</li>');
  }
  
  function afficherLesConseillers(conseillers, format) {
    
    // on sauve (si détails demandés par la suite)
    dernListeConseillers = conseillers;
    dernFormat = format;
    
    // on récupère le composant du DOM
    var divData = $('#data');
    divData.html('');
    divData.append('<ol>');

    // si c'est du JSON
    if (format === 'JSON') {

      // on boucle pour ajouter chaque conseiller
      $.each(conseillers, function (i, cons) {

        // pour bénéficier du toString() et de la conversion de date de la pseudo-classe Conseiller
        var conseiller = new Conseiller();
        conseiller.setNom(cons.nom);
        conseiller.setPrenom(cons.prenom);
        conseiller.setOrigine(cons.origine);
        conseiller.setDateNaissance(cons.dateNaissance);
        conseiller.setDateDeces(cons.dateDeces);
        conseiller.setCanton(cons.canton);
        conseiller.setParti(cons.parti);
        var activites = [];
        $.each(cons.activites, function (j, act) {
          var activite = new Activite();
          activite.setConseil(act.conseil);
          activite.setDateEntree(act.dateEntree);
          activite.setDateSortie(act.dateSortie);
          activite.setGroupe(act.groupe);
          activite.setFonction(act.fonction);
          activites.push(activite);
        });
        conseiller.setActivites(activites);
        afficherUnConseiller(conseiller, divData);
      });
    } else { // si c'est du XML
      
      // on boucle sur chaque conseiller
      $(conseillers).find('conseiller').each(function () {

        // on crée un objet Conseiller
        var conseiller = new Conseiller();
        conseiller.setPkConseiller(Number($(this).find('pkConseiller').text()));
        conseiller.setNom($(this).find('nom').text());
        conseiller.setPrenom($(this).find('prenom').text());
        conseiller.setOrigine($(this).find('origine').text());
        conseiller.setDateNaissance($(this).find('dateNaissance').text());
        conseiller.setDateDeces($(this).find('dateDeces').text());

        // on gère un objet Canton
        var xmlCanton = $(this).find('canton');
        var canton = {pkCanton: Number(xmlCanton.find('pkCanton').text()),
          abrev: xmlCanton.find('abrev').text()};
        conseiller.setCanton(canton);

        // on gère un objet Parti
        var xmlParti = $(this).find('parti');
        var parti = {pkParti: Number(xmlParti.find('pkParti').text()),
          nomParti: xmlParti.find('nomParti').text()};
        conseiller.setParti(parti);

        // gère les activites
        var activites = [];
        $(this).find('activite').each(function () {

          // on gère un objet Activite
          var activite = new Activite();
          activite.setPkActivite(Number($(this).find('pkActivite').text()));
          activite.setDateEntree($(this).find('dateEntree').text());
          activite.setDateSortie($(this).find('dateSortie').text());

          // on gère un objet Conseil
          var xmlConseil = $(this).find('conseil');
          var conseil = {pkConseil: Number(xmlConseil.find('pkConseil').text()),
            abrev: xmlConseil.find('abrev').text()};
          activite.setConseil(conseil);

          // on gère un objet Groupe
          var xmlGroupe = $(this).find('groupe');
          var groupe = {pkGroupe: Number(xmlGroupe.find('pkGroupe').text()),
            nomGroupe: xmlGroupe.find('nomGroupe').text()};
          activite.setGroupe(groupe.pkGroupe > 0 ? groupe : null);

          // on gère un objet Fonction
          var xmlFonction = $(this).find('fonction');
          var fonction = {pkFonction: Number(xmlFonction.find('pkFonction').text()),
            nomFonction: xmlFonction.find('nomFonction').text()};
          activite.setFonction(fonction.pkFonction > 0 ? fonction : null);

          // on ajoute une activite au tableau des activites
          activites.push(activite);

        });
        conseiller.setActivites(activites);

        // on affiche un conseiller
        afficherUnConseiller(conseiller, divData);
      });

    }
    divData.append('</ol>');
  }  

  function afficherErreurHttp(jqXHR, textStatus, errorThrown) {
    var msg = textStatus + ": " + errorThrown + " " + jqXHR.responseText + " !";
    console.log(msg);
//    alert(msg);
  }


  /*
   * 2. CALLBACKS (RETOUR DE SERVICE)
   */
  function okLireVersionServeur(data, text, jqXHR) {
    var infoComponent = $("#versionServeur");
    infoComponent.html(data.versionServeur);
  }

  function okChargerCantons(cantons, text, jqXHR) {
    var cmbCantons = $("#cmbCantons");
    $.each(cantons, function (i, canton) {
      $('<option value="' + canton.abrev + '">' + canton.abrev
        + '</option>').appendTo(cmbCantons);
    });
    cmbCantons.val(canton);
  }

  function okChargerConseils(conseils, text, jqXHR) {
    var cmbConseils = $("#cmbConseils");
    $.each(conseils, function (i, conseil) {
      $('<option value="' + conseil.abrev + '">' + conseil.abrev
        + '</option>').appendTo(cmbConseils);
    });
    cmbConseils.val(conseil);
  }

  function okChargerPartis(partis, text, jqXHR) {
    var cmbPartis = $("#cmbPartis");
    $.each(partis, function (i, parti) {
      $('<option value="' + parti.nomParti + '">' + parti.nomParti
        + '</option>').appendTo(cmbPartis);
    });
    cmbPartis.val(parti);
  }
  
  function okChargerConseillers(conseillers, text, jqXHR) {
    var format = (jqXHR.responseXML) ? 'XML' : 'JSON';
    dernListeConseillers = conseillers;
    afficherLesConseillers(conseillers, format);
  }


  /*
   * 3. DOM PRET : chargement des premières données dans la vue
   */
  $(document).ready(function () {
    console.debug("DOM ready !!!");
    Date.setDatePatterns("d.M.yy");

    // récupération des composants principaux de la vue (dans le DOM)
    var cmbCantons = $("#cmbCantons");
    var cmbConseils = $("#cmbConseils");
    var cmbPartis = $("#cmbPartis");
    var cmbFormats = $("#cmbFormats");
    var cbxActuels = $("#cbxActuels");
    var cbxDetails = $("#cbxDetails");

    // met les valeurs par défaut dans la vue (canton défini après le chargement des cantons)
    cmbFormats.val(format);
    if (actuels) {
      cbxActuels.attr("checked", true);
    }
    if (details) {
      cbxDetails.attr("checked", true);
    }

    // définit le format d'entrée des dates
    Date.setInputPattern("yyyy-MM-dd");

    // chargement des pseudo-classes "model"
    var CHEMIN_MODELES = "js/models/";
    $.getScript(CHEMIN_MODELES + "conseiller.js", function () {
      console.log("conseiller.js chargé !");

      // activite
      $.getScript(CHEMIN_MODELES + "activite.js", function () {
        console.log("activite.js chargé !");
      });
    });

    // chargement de la couche de service et des premières données par HTTP
    $.getScript("js/services/httpServ.js", function () {
      console.log("httpServ.js chargé !");
      httpServ.lireVersionServeur(okLireVersionServeur, afficherErreurHttp);
      httpServ.chargerCantons(okChargerCantons, afficherErreurHttp);
      httpServ.chargerConseils(okChargerConseils, afficherErreurHttp);
      httpServ.chargerPartis(okChargerPartis, afficherErreurHttp);
      httpServ.chargerConseillers(format, canton, conseil, parti, actuels, okChargerConseillers, afficherErreurHttp);
    });


    /*
     * 4. ECOUTEURS SUR DES ACTIONS DE l'UTILISATEUR
     */
    function effectuerRequeteServeur() {
      var divData = $('#data');
      divData.html('...');
      if (httpServ) {
        httpServ.chargerConseillers(format, canton, conseil, parti, actuels, okChargerConseillers, afficherErreurHttp);
      }      
    }
    
    cmbCantons.change(function (event) {
      canton = cmbCantons.val();
      effectuerRequeteServeur();
    });

    cmbConseils.change(function (event) {
      conseil = cmbConseils.val();
      effectuerRequeteServeur();
    });

    cmbPartis.change(function (event) {
      parti = cmbPartis.val();
      effectuerRequeteServeur();
    });

    cmbFormats.change(function (event) {
      format = cmbFormats.val();
      effectuerRequeteServeur();
    });

    cbxActuels.change(function (event) {
      actuels = cbxActuels.is(':checked');
      effectuerRequeteServeur();
    });

    cbxDetails.change(function (event) {
      details = cbxDetails.is(':checked');
      afficherLesConseillers(dernListeConseillers, dernFormat);
    });

  });

})();

