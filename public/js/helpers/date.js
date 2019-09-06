/**
 * LIBRAIRIE AVEC DES FONCTIONS AJOUTEES A LA CLASSE DATE.
 *
 * Le principal atout de cette librairie est la fonction
 * "parseString" qui convertit correctement des dates String
 * que ce soit avec des dates américaines ou autres.
 *
 * Auteur:     Jean-Claude Stritt, sauf
 *             Date.prototype.format:
 *             http://www.javascripttoolbox.com
 *
 * Création:      décembre 2014
 * Modification:  juin 2017
 * Version:       2.0.3
 *
 */

// si la fonction getFullYear n'existe pas, on l'a crée
if (!Date.prototype.getFullYear) {
  Date.prototype.getFullYear = function () {
    var yy = this.getYear();
    return (yy < 1900 ? yy + 1900 : yy);
  };
}

// diverses valeurs par défaut
Date.ONE_DAY_MS = 24 * 3600 * 1000;
Date.DEFAULT_YEAR = new Date().getFullYear();
Date.YEAR_DIGITS = ('' + Date.DEFAULT_YEAR).length;
Date.INPUT_PATTERN = 'yyyy-MM-dd'; // format d'entrée des dates par défaut
Date.OUTPUT_PATTERN = (Date.YEAR_DIGITS === 4) ? 'd.M.yyyy' : 'dd.MM.yy'; // format de sortie par défaut
Date.OUTPUT_PATTERN2 = 'd.M'; // pour formatage des dates sans l'année
//Date.INPUT_PATTERN = 'MM/dd/y'; // format americain
//Date.OUTPUT_PATTERN = 'MM/dd/yyyy'; // format americain

/*
console.info(
  'defaultYear: ' + Date.DEFAULT_YEAR
  + ', yearDigits: ' + Date.YEAR_DIGITS
  + ', inputPattern: ' + Date.INPUT_PATTERN
  + ', outputPattern: ' + Date.OUTPUT_PATTERN
  + ', outputPattern2: ' + Date.OUTPUT_PATTERN2
  );
*/


/*
 * FONCTIONS GENERIQUES
 */

// permet de vérifier le type (Date ou String) d'une date
Date.getType = function (obj) {
  return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase();
};

// debug un objet complexe
Date.debugObject = function (obj) {
  console.debug('\nObject properties:\n');
  var type, value, i = 1;
  for (var property in obj) {
    type = Date.getType(obj[property]);
    if (type !== 'function') {
      value = obj[property];
      if (type === 'object') {
        value = JSON.stringify(obj[property]);
      }
      console.debug(i + '. ' + property + ': ' + value + ' [' + Date.getType(obj[property]) + ']');
      i++;
    }
  }
};

// ajoute éventuellement un zéro à un jour ou un mois avec un seul digit
Date.addLeadingZero = function (x) {
  return(x < 0 || x > 9 ? "" : "0") + x;
};

// retourne le dernier jour du mois
Date.lastDayMonth = function (year, month) {
  var d = new Date(year, month, 0);
  return d.getDate();
};



/*
 * FONCTIONS POUR ADAPTER OU CONNAITRE L'ANNEE PAR DEFAUT
 * pour pouvoir introduire des dates courtes sans l'année
 */

// définit l'année par défaut
Date.setDefaultYear = function (year) {
  Date.DEFAULT_YEAR = year;
};

// définit l'année par défaut (depuis une date donnée)
Date.setDefaultYearFromDate = function (date) {
  var type = Date.getType(date);
//  console.debug('DEFAULT_YEAR FROM DATE: ' + date + ' type: '+type);
  if (type === 'date') {
    Date.DEFAULT_YEAR = date.getFullYear();
  } else if (type === 'number') {
    var d = new Date(date);
    Date.DEFAULT_YEAR = d.getFullYear();
  }
  console.debug('DEFAULT_YEAR = ' + Date.DEFAULT_YEAR);
};

// retourne l'année par défaut mémorisée
Date.getDefaultYear = function () {
  return Date.DEFAULT_YEAR;
};



/*
 * FONCTIONS POUR ADAPTER OU CONNAITRE LE NOMBRE DE DIGITS DE L'ANNEE LORS DES FORMATAGES
 */

// définit le nb de digits par défaut pour le formatage des dates
Date.setYearDigits = function (yearDigits) {
  Date.YEAR_DIGITS = yearDigits;
};

// retourne le nb de digits par défaut mémorisé
Date.getYearDigits = function () {
  return Date.YEAR_DIGITS;
};



/*
 * FONCTIONS POUR MODIFIER OU CONNAITRE LES FORMATS D'ENTREE-SORTIE DES DATES
 */

// mémorise les patrons de sortie -formatage- (mais aussi d'entrée -dataPicker-) d'une date
Date.setDatePatterns = function (refPattern) {
  if (refPattern !== undefined) {
    var outPattern = refPattern;
    var n = outPattern.split("y").length - 1;
    if (n === 2 && Date.YEAR_DIGITS === 4) {
      outPattern += 'yy';
    }
    Date.OUTPUT_PATTERN = outPattern;
    var fmtParts = outPattern.split(/[\.\-\/]/);
    if (fmtParts.length === 3) {
      var inPattern = outPattern;
      inPattern = inPattern.replace(/yyyy/g, 'yy');
      inPattern = inPattern.replace(/yy/g, 'y');
      inPattern = inPattern.replace(/MM/g, 'M');
      inPattern = inPattern.replace(/dd/g, 'd');
      Date.INPUT_PATTERN = inPattern;
    }

    // new JCS 3.1.2015
    if (Date.YEAR_DIGITS === 0) {
      outPattern = outPattern.replace(/y/g, '');
      Date.OUTPUT_PATTERN2 = outPattern.substring(0, outPattern.length - 1);
    } else {
      Date.OUTPUT_PATTERN2 = outPattern;

      // contrôle
      console.info('refsPattern: ' + refPattern
        + ', yearDigits: ' + Date.YEAR_DIGITS
        + ', inputPattern: ' + Date.INPUT_PATTERN
        + ', outputPattern: ' + Date.OUTPUT_PATTERN
        + ', outputPattern2: ' + Date.OUTPUT_PATTERN2);
    }
  }
};

// set l'input pattern s'il n'est pas définit automatiquement avec setDatePatterns
Date.setInputPattern = function (inPattern) {
  Date.INPUT_PATTERN = inPattern;
};

// retourne le patron d'entrée d'une date
Date.getDateInputPattern = function (yearStyle) {
  var inPattern = Date.INPUT_PATTERN;
  if (yearStyle === 1) {
    inPattern = inPattern.replace(/y/g, 'yy');
  } else if (yearStyle === 2) {
    inPattern = inPattern.replace(/y/g, '');
    var lastChar = inPattern.charAt(inPattern.length - 1);
    if (lastChar === '/' || lastChar === '-' || lastChar === '.') {
      inPattern = inPattern.substr(0, inPattern.length - 1);
    }
  }
//  console.debug('Date.getDateInputPattern: ' + inPattern + ' yearStyle= ' + yearStyle);
  return inPattern;
};

// retourne le patron de sortie d'une date
Date.getDateOutputPattern = function () {
  return Date.OUTPUT_PATTERN;
};

// retourne le 2ème patron de sortie d'une date
Date.getDateOutputPattern2 = function () {
  return Date.OUTPUT_PATTERN2;
};



/*
 * FONCTIONS UTILISES POUR CONTROLER LA SAISE DE DATE
 */

// retourne les positions de l'année, du mois et du jour dans le format d'entrée
Date.getDateFormatIndexes = function () {
  var indexes = [0, 1, 2];
  var fmtParts = Date.INPUT_PATTERN.split(/[\.\-\/]/);
  if (fmtParts.length === 3) {
    for (i = 0; i < fmtParts.length; i++) {
      var check = fmtParts[i].substr(0, 1);
//      console.debug('getDateFormatIndexes: check='+check);
      if (check === 'y') {
        indexes[0] = i;
      } else if (check === 'M') {
        indexes[1] = i;
      } else if (check === 'd') {
        indexes[2] = i;
      }
    }
  }
  return indexes;
};

// retourne un string qui contient le bon regex d'après le format de date locale
Date.getDateStringRegex = function () {
  var sep = '\\' + Date.INPUT_PATTERN.substr(1, 1);
  var regexDay = '(0?[1-9]|[12][0-9]|3[01])';
  var regexMonth = '(0?[1-9]|1[012])';
  var regexYear = '(' + sep + '\\d{0,4})?';
  var indexes = Date.getDateFormatIndexes();
  var regex = '^(\\d{4})-(\\d{2})-(\\d{2})$'; // format ISO yyyy-MM-dd
  if (indexes[0] === 2) { // formats européen ou américain
    if (indexes[1] === 0) { // mois en premier = format américain
      regex = '^' + regexMonth + sep + regexDay + regexYear + '$';
    } else { // format européen
      regex = '^' + regexDay + sep + regexMonth + regexYear + '$';
    }
//    regex = '^\\d{1,2}' + sep + '\\d{1,2}' + sep + '{0,1}\\d{0,4}$';
  }
//  console.debug('>>> regex pattern: '+regex);
  return regex;
};

// retourne un object "regex" qui contrôle la saisie dans un masque
Date.getDateRegex = function () {
  return new RegExp(Date.getDateStringRegex(), '');
};

// fonction qui convertit en date JS une date introduite au format String
Date.parseString = function (dateStr) {
  var date = null;
//  console.debug('Date.parseString: ' + dateStr + ' [' + Date.getType(dateStr) + '] input format: ' + Date.getDateInputPattern());
  if (dateStr !== undefined && Date.getType(dateStr) === 'string') {
    var indexes = Date.getDateFormatIndexes();
    var dateParts = dateStr.split(/[\.\-\/]/);
    if (dateParts.length === 2 || dateParts.length === 3) {
      var year = parseInt(dateParts[indexes[0]], 10);
      var month = parseInt(dateParts[indexes[1]], 10);
      var day = parseInt(dateParts[indexes[2]], 10);
      if (isNaN(year)) {
        year = Date.DEFAULT_YEAR;
      }
      if (year > 0 && year < 100) {
        year = year + parseInt(new Date().getFullYear() / 100) * 100;
      }
      var maxDay = Date.lastDayMonth(year, month);
//      console.log('year: ' + year + ' month: ' + month + ' day: ' + day + ' maxDay: ' + maxDay);
      if (year >= 1900 && year < 2400 && month > 0 && month <= 12 && day > 0 && day <= maxDay) {
        date = new Date(year, month - 1, day, 0, 0, 0, 0);
      }
    }
  }
  return date;
};

// convertit une valeur de date qui peut déjà être une date (viewvalue dans un datepicker)
Date.parseInput = function (viewValue) {
  var date = null;
  if (Date.getType(viewValue) === 'date') {
    date = viewValue;
  } else {
    var isValid = Date.getDateRegex().test(viewValue);
    if (isValid) {
      date = Date.parseString(viewValue);
    }
  }
//  console.debug('>>> Date.parseInput: value: ' + viewValue
//          + ' [' + Date.getType(viewValue) + ']'
//          + ', date: ' + (date ? date.format() : '?undefined')
//          + ', pattern: ' + Date.getDateInputPattern());
  return date;
};

// teste si une date au format String est valide
Date.isValid = function (dateStr) {
  return Date.parseString(dateStr) !== null;
};



/*
 * FONCTION UTILISEE POUR FORMATER UNE DATE
 */

// formatte une date avec un format donné
Date.format = function (date, fmt) {
  var result = 'undefined';
  if (Date.getType(date) === 'date') {
    result = '';
    var format = '' + fmt;
    var i_format = 0;
    var c = '';
    var token = '';
    var y = date.getYear() + '';
    var M = date.getMonth() + 1;
    var d = date.getDate();
    var E = date.getDay();
    var H = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    var yyyy, yy, MMM, MM, dd, hh, h, mm, ss, ampm, HH, H, KK, K, kk, k;

    // convertit les parties réelles d'une date dans leur version formattée
    var value = new Object();
    if (y.length < 4) {
      y = '' + (+y + 1900);
    }
    value['y'] = '' + y;
    value['yyyy'] = y;
    value['yy'] = y.substring(2, 4);
    value['M'] = M;
    value['MM'] = Date.addLeadingZero(M);
    value['d'] = d;
    value['dd'] = Date.addLeadingZero(d);
    value['H'] = H;
    value['HH'] = Date.addLeadingZero(H);
    if (H === 0) {
      value['h'] = 12;
    } else if (H > 12) {
      value['h'] = H - 12;
    } else {
      value['h'] = H;
    }
    value['hh'] = Date.addLeadingZero(value['h']);
    value['K'] = value['h'] - 1;
    value['k'] = value['H'] + 1;
    value['KK'] = Date.addLeadingZero(value['K']);
    value['kk'] = Date.addLeadingZero(value['k']);
    if (H > 11) {
      value['a'] = 'PM';
    } else {
      value['a'] = 'AM';
    }
    value['m'] = m;
    value['mm'] = Date.addLeadingZero(m);
    value['s'] = s;
    value['ss'] = Date.addLeadingZero(s);
    while (i_format < format.length) {
      c = format.charAt(i_format);
      token = '';
      while ((format.charAt(i_format) === c) && (i_format < format.length)) {
        token += format.charAt(i_format++);
      }
      if (typeof (value[token]) !== 'undefined') {
        result = result + value[token];
      } else {
        result = result + token;
      }
    }
  }
  return result;
};



/**
 * FONCTIONS GENERIQUES UTILISABLES DIRECTEMENT SUR UN OBJET "DATE"
 */

// formatte la date qui appelle cette fonction avec le format standard de sortie
Date.prototype.format = function () {
  return Date.format(this, Date.OUTPUT_PATTERN);
};

// formatte la date qui appelle cette fonction avec un format spécifié
Date.prototype.formatWithPattern = function (fmt) {
  return Date.format(this, fmt);
};

// formatte la date, mais uniquement l'année avec le siècle
Date.prototype.formatOnlyYear = function () {
  return Date.format(this, 'yyyy');
};

// teste si la date qui appelle cette fonction précède une deuxième
Date.prototype.isBefore = function (date2) {
  return (date2 === null) ? false : this.getTime() < date2.getTime();
};

// teste si la date qui appelle cette fonction suit une deuxième
Date.prototype.isAfter = function (date2) {
  return (date2 === null) ? false : this.getTime() > date2.getTime();
};

// teste si la date qui appelle cette fonction est égale à une deuxième
Date.prototype.equals = function (date2) {
  return (date2 === null) ? false : this.getTime() === date2.getTime();
};

// efface l'heure dans la date qui appelle cette fonction
Date.prototype.clearTime = function () {
  this.setHours(0);
  this.setMinutes(0);
  this.setSeconds(0);
  this.setMilliseconds(0);
  return this;
};

// teste si la date qui appelle cette fonction est égale à une deuxième
Date.prototype.equalsIgnoreTime = function (date2) {
  var ok = date2 !== null;
  if (ok) {
    var d1 = new Date(this.getTime()).clearTime();
    var d2 = new Date(date2.getTime()).clearTime();
    ok = (d1.getTime() === d2.getTime());
  }
  return ok;
};

// retourne le nb de jours entre la date qui appelle cette fonction et une deuxième
Date.prototype.getDaysBetween = function (date2) {
  var diffDays = 0;
  if (date2 !== null) {
    var timeDiff = Math.abs(date2.getTime() - this.getTime());
    diffDays = Math.round(timeDiff / Date.ONE_DAY_MS);
  }
  return diffDays;
};




