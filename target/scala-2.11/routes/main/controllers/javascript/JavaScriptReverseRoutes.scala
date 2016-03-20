
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/conf/routes
// @DATE:Sat Mar 19 16:32:01 CET 2016

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:14
  class ReverseConseillerCtrl(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:19
    def chargerConseils: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ConseillerCtrl.chargerConseils",
      """
        function(fmt) {
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseils"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseils.xml"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("json") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseils.json"})
          }
        
        }
      """
    )
  
    // @LINE:24
    def chargerPartis: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ConseillerCtrl.chargerPartis",
      """
        function(fmt) {
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "partis"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "partis.xml"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("json") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "partis.json"})
          }
        
        }
      """
    )
  
    // @LINE:14
    def chargerCantons: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ConseillerCtrl.chargerCantons",
      """
        function(fmt) {
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cantons"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cantons.xml"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("json") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cantons.json"})
          }
        
        }
      """
    )
  
    // @LINE:29
    def chargerConseillers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ConseillerCtrl.chargerConseillers",
      """
        function(fmt,canton,conseil,parti,actuels) {
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("html") + """ && canton == """ + implicitly[JavascriptLiteral[String]].to("Suisse") + """ && conseil == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("html") + """ && conseil == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("html") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("html") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("actuels", encodeURIComponent(actuels))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("xml") + """ && canton == """ + implicitly[JavascriptLiteral[String]].to("Suisse") + """ && conseil == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("xml") + """ && conseil == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("xml") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("xml") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("actuels", encodeURIComponent(actuels))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("json") + """ && canton == """ + implicitly[JavascriptLiteral[String]].to("Suisse") + """ && conseil == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json"})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("json") + """ && conseil == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("json") + """ && parti == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("json") + """ && actuels == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti))})
          }
        
          if (fmt == """ + implicitly[JavascriptLiteral[String]].to("json") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("actuels", encodeURIComponent(actuels))})
          }
        
        }
      """
    )
  
  }

  // @LINE:7
  class ReverseApplicationCtrl(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def checkPreFlight: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationCtrl.checkPreFlight",
      """
        function(path) {
          return _wA({method:"OPTIONS", url:"""" + _prefix + { _defaultPrefix } + """" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("path", path)})
        }
      """
    )
  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationCtrl.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:11
    def lireVersionServeur: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationCtrl.lireVersionServeur",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "version"})
        }
      """
    )
  
  }

  // @LINE:56
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:56
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
  }

  // @LINE:49
  class ReverseLoginCtrl(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:53
    def unauthorizedAccess: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginCtrl.unauthorizedAccess",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "unauthorizedAccess"})
        }
      """
    )
  
    // @LINE:51
    def sessionStatus: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginCtrl.sessionStatus",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sessionStatus"})
        }
      """
    )
  
    // @LINE:49
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginCtrl.login",
      """
        function(loginName,pwd) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("loginName", encodeURIComponent(loginName)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("pwd", encodeURIComponent(pwd))})
        }
      """
    )
  
    // @LINE:50
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginCtrl.logout",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
        }
      """
    )
  
    // @LINE:52
    def createLogin: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginCtrl.createLogin",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "createLogin"})
        }
      """
    )
  
  }


}