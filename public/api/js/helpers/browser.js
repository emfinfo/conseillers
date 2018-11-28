/**
 * Espace de nommage principal "browser". Contient les pseudo-classes :<br>
 * - Url --> avec des fonctions utiles pour gérer les URL<br>
 * - LocalStorage --> avec des fonctions de lecture/ecriture dans la zone du même nom<br>
 * 
 * @author Jean-Claude Stritt
 * @version 1.0.5 / 29.9.2015
 */
var browser = {
  
  /**
   * Pseudo-classe Url rassemblant des fonctions sur le navigateur,
   * principalement des requêtes sur l'URL utilisée.
   *
   * @param {Object} w une copie de l'objet window du navigateur
   */
  Url: (function(w) {

    function _getUrlParts() {
      var url = w.location.href;
      var url_parts = url.split("/");
      var domain_name_parts = url_parts[2].split(":");
      var info = new Array();
      info[0] = url_parts[0]; // protocole
      info[1] = domain_name_parts[0]; // domain
      info[2] = domain_name_parts[1]; // port
      return info;
    }

    /**
     * Retourne l'URL de base de la page actuelle dans le navigateur.
     * 
     * @returns {String} l'URL
     */
    function _getBaseUrl() {
      var info = _getUrlParts();
      var url = info[0] + "//" + info[1];
      if (info[2]) {
        url += ":" + info[2];
      }
      return url;
    }

    /**
     * Retourne une URL composée de l'adresse de base du serveur
     * + une requête + "." + un format.
     *
     * @param {String} baseURL l'URL de base du serveur (ex: "http://172.23.87.10:9000")
     * @param {String} request la requête supplémentaire (ex: "cantons")
     * @param {String} fmt le format de cette requête (ex: "xml" ou "json")
     * 
     * @returns {String} une URL
     */
    function _buildUrl(baseURL, request, fmt) {
      return baseURL + "/" + request + "." + fmt;
    }
    
    function _getIpFromString(a) {
      var r = a.match(/\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/);
      return r[0];
    }
        
    /**
     * Récupère l'adresse IP du réseau local.
     * 
     * @param callback une méthode de callback si l'IP a été lu
     */
    function _getLocalIP(callback) {
      var RTCPeerConnection = 
              w.RTCPeerConnection || 
              w.webkitRTCPeerConnection || 
              w.mozRTCPeerConnection;
      var configuration = {"iceServers": []};
      var pc;
      var localIP = null;

      if (RTCPeerConnection) {
        pc = new RTCPeerConnection(configuration);
        pc.onicecandidate = function(evt) {
          if (evt.candidate) {
            if (!localIP) {
              localIP = _getIpFromString(evt.candidate.candidate);
              callback(localIP);
            }
          }
        };

        pc.createOffer(function(offerDesc) {
          pc.setLocalDescription(offerDesc);
        }, function(e) {
          console.warn("offer failed", e);
        });

      } else {
        console.warn("Browser doesn't support webrtc !");
      }      
    }

    return{
      getUrlParts: _getUrlParts,
      getBaseUrl: _getBaseUrl,
      buildUrl:   _buildUrl,
      getLocalIP: _getLocalIP
    };
  })(window),
  
  
  
  /**
   * Pseudo-classe LocalStorage qui regroupe des fonctions pour gérer le localstorage.
   *
   * @param {Object} w une copie de l'objet window du navigateur
   */
  LocalStorage: (function(w) {

    /**
     * Méthode privée pour tester si une clé existe dans le localstorage.
     * 
     * @param {type} key une clé
     * @returns {Boolean} true si elle existe, false autrement
     */
    function _exist(key) {
      return w.localStorage.getItem(key) !== null;
    }

    /**
     * Méthode privée pour lire la valeur d'une clé dans le localstorage.
     * 
     * @param {type} key la clé
     * @returns {Array|Object} un objet ou tableau d'objets
     */
    function _read(key) {
      var value = w.localStorage.getItem(key);
//      console.log("read - key: " + key + " value:" + value);
      return JSON.parse(value);
    }

    /**
     * Méthode privée pour écrire un couple clé-valeur dans le localstorage.
     * @param {type} key une clé
     * @param {type} object un objet ou un tableau d'objets à mémoriser
     */
    function _write(key, object) {
      var value = JSON.stringify(object);
      w.localStorage.setItem(key, value);
//      console.log("write - key: " + key + " value:" + value);
    }

    // définition des méthodes publiques (à gauche)
    return{
      exist: _exist,
      read: _read,
      write: _write
    };

  }
  )(window)

};

