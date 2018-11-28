// contraintes de validation pour les champs du login
var validator = (function() {

  function _getContraints() {
    return {
      message: "Cette valeur n'est pas valide",
      fields: {
        usernameLogin: {
          validators: {
            notEmpty: {
              message: "Un nom d'utilisateur doit être fourni !"
            }
          }
        },
        passwordLogin: {
          validators: {
            notEmpty: {
              message: "Un mot de passe doit être fourni !"
            }
          }
        }
      }
    };
  }
  
  return {
    get: _getContraints
  };

})();




