/**
 * Les principaux atouts de cette librairie sont les fonctions
 * de cryptage et décryptage en AES avec l'algorythme PBKDF2.
 *
 * Cette librairie possède donc des dépendances sur CryptoJS,
 * en particulier les modules suivants :
 * - aes.js
 * - enc-base64.js
 * - pbkdf2.js
 *
 * Auteur:   Jean-Claude Stritt
 * Création: février 2018
 */

/* global CryptoJS */

var AesUtil = function (keySize, iterationCount) {
  this.keySize = keySize / 32;
  this.iterationCount = iterationCount;
};

AesUtil.toBase64 = function (txt) {
  var wordArray = CryptoJS.enc.Utf8.parse(txt);
  return CryptoJS.enc.Base64.stringify(wordArray);
};

AesUtil.toHex = function (txt) {
  var wordArray = CryptoJS.enc.Utf8.parse(txt);
  return CryptoJS.enc.Hex.stringify(wordArray);
};

AesUtil.randomHex = function (size) {
  return CryptoJS.lib.WordArray.random(size).toString(CryptoJS.enc.Hex);
};

AesUtil.passPhrase = function () {
  var date = new Date();
  return AesUtil.toBase64("qWe" + date.toISOString().slice(0, 10) + "PoI");
};

AesUtil.prototype.generateKey = function (salt, passPhrase) {
  var key = CryptoJS.PBKDF2(passPhrase, CryptoJS.enc.Hex.parse(salt),
    {keySize: this.keySize, iterations: this.iterationCount});
  return key;
};

AesUtil.prototype.encrypt = function (salt, iv, passPhrase, plainText) {
  var key = this.generateKey(salt, passPhrase);
  var encrypted = CryptoJS.AES.encrypt(plainText, key, {iv: CryptoJS.enc.Hex.parse(iv)});
  return encrypted.ciphertext.toString(CryptoJS.enc.Hex);
};

AesUtil.prototype.decrypt = function (salt, iv, passPhrase, cipherText) {
  var key = this.generateKey(salt, passPhrase);
  var cipherParams = CryptoJS.lib.CipherParams.create({ciphertext: CryptoJS.enc.Hex.parse(cipherText)});
  var decrypted = CryptoJS.AES.decrypt(cipherParams, key, {iv: CryptoJS.enc.Hex.parse(iv)});
  return decrypted.toString(CryptoJS.enc.Utf8);
};

AesUtil.encrypt = function (data) {

  // préparation des données de base AES-PBKDF2 pour l'encryptage
  var keySize = 128;
  var iterationCount = 1000;
  var salt = AesUtil.randomHex(16);
  var iv = AesUtil.randomHex(16);
  var passPhrase = AesUtil.passPhrase();

  // contrôle encryptage
//  console.log('passPhrase: ' + passPhrase + " len=" + passPhrase.length);
//  console.log('salt: ' + salt + " len=" + salt.length);
//  console.log('iv:   ' + iv + " len=" + iv.length);
//  console.log('data: ' + data + " len=" + data.length);

  // création de l'utilitaire d'encryptage
  var aesUtil = new AesUtil(keySize, iterationCount);

  // encryptage des données
  var encData = aesUtil.encrypt(salt, iv, passPhrase, data);
//  console.log('encData:  ' + encData + ' len=' + encData.length);

  // contrôle en décryptant immédiatement
//  var decData = aesUtil.decrypt(salt, iv, passPhrase, encData);
//  console.log('decData: ' + decData + ' len=' + decData.length);

  // retourne le sel, le vecteur d'initialisation et les données encryptées
  return salt + iv + encData;
};


