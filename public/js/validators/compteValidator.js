// contraintes de validation pour les champs de l'ajout de comptes
var validator = (function () {

  function _getContraints() {
    return {
      message: "Cette valeur n'est pas valide",
      fields: {
        nom: {
          validators: {
            notEmpty: {
              message: "Un nom d'utilisateur doit être fourni !"
            },
            stringLength: {
              min: 6,
              max: 30,
              message: 'Le nom doit être composé de 6 à 30 caractères !'
            },
            regexp: {
              regexp: /^[a-zA-Z0-9_\.]+$/,
              message: 'Le nom ne peut comporter que des lettres sans accent, des chiffres, le point et le souligné !'
            },
            different: {
              field: 'password',
              message: "Le nom d'utilisateur et le mot de passe ne peuvent être les mêmes"
            }
          }
        },
        motDePasse: {
          validators: {
            notEmpty: {
              message: "Un mot de passe doit être fourni !"
            },
            different: {
              field: 'username',
              message: "Le nom d'utilisateur et le mot de passe ne peuvent être les mêmes !"
            }
          }
        },
        confirmerMotDePasse: {
          validators: {
            notEmpty: {
              message: 'Le mot de passe de confirmation est requis et ne peut être vide !'
            },
            identical: {
              field: 'motDePasse',
              message: 'Le mot de passe et sa confirmation ne sont pas les mêmes !'
            }
          }
        },
        domaine: {
          validators: {
            callback: {
              message: "Les domaines acceptés sont EDU ou STUDENTFR !",
              callback: function (value, validator) {
                return !value || value.toUpperCase() === "EDU" || value.toUpperCase() === "STUDENTFR";
              }
            }
          }
        },
        profil: {
          validators: {
            callback: {
              message: "Les profils acceptés sont PROF ou ELEVE !",
              callback: function (value, validator) {
                return !value || value.toUpperCase() === "PROF" || value.toUpperCase() === "ELEVE";
              }
            }
          }
        },
        initiales: {
          validators: {
            notEmpty: {
              message: "Les initiales de l'utilisateur sont requises !"
            },
            stringLength: {
              min: 2,
              max: 5,
              message: 'Les initiales doivent être composées de 2 à 5 caractères !'
            },
            regexp: {
              regexp: /^[a-zA-Z]+$/,
              message: 'Les initiales ne doivent comporter que des lettres sans accent !'
            }
          }
        },
        email: {
          validators: {
            notEmpty: {
              message: 'Une adresse email est requise !'
            },
            emailAddress: {
              message: "L'adresse email introduite n'est pas une adresse valide !"
            }
          }
        }
      }
    };
  }

  return {
    get: _getContraints
  };

}());
