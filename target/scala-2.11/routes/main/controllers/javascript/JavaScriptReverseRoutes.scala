
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/conf/routes
// @DATE:Tue Jul 12 17:36:33 CEST 2016

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
        function(fmt0) {
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseils"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseils.xml"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("json") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseils.json"})
          }
        
        }
      """
    )
  
    // @LINE:24
    def chargerPartis: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ConseillerCtrl.chargerPartis",
      """
        function(fmt0) {
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "partis"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "partis.xml"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("json") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "partis.json"})
          }
        
        }
      """
    )
  
    // @LINE:14
    def chargerCantons: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ConseillerCtrl.chargerCantons",
      """
        function(fmt0) {
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cantons"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cantons.xml"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("json") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cantons.json"})
          }
        
        }
      """
    )
  
    // @LINE:29
    def chargerConseillers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ConseillerCtrl.chargerConseillers",
      """
        function(fmt0,canton1,conseil2,parti3,actuels4) {
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("html") + """ && canton1 == """ + implicitly[JavascriptLiteral[String]].to("Suisse") + """ && conseil2 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("html") + """ && conseil2 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("html") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("html") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti3))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti3)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("actuels", encodeURIComponent(actuels4))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("xml") + """ && canton1 == """ + implicitly[JavascriptLiteral[String]].to("Suisse") + """ && conseil2 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("xml") + """ && conseil2 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("xml") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("xml") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti3))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.xml/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti3)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("actuels", encodeURIComponent(actuels4))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("json") + """ && canton1 == """ + implicitly[JavascriptLiteral[String]].to("Suisse") + """ && conseil2 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json"})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("json") + """ && conseil2 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("json") + """ && parti3 == """ + implicitly[JavascriptLiteral[String]].to("") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("json") + """ && actuels4 == """ + implicitly[JavascriptLiteral[String]].to("false") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti3))})
          }
        
          if (fmt0 == """ + implicitly[JavascriptLiteral[String]].to("json") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conseillers.json/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("canton", encodeURIComponent(canton1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("conseil", encodeURIComponent(conseil2)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parti", encodeURIComponent(parti3)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("actuels", encodeURIComponent(actuels4))})
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
        function(path0) {
          return _wA({method:"OPTIONS", url:"""" + _prefix + { _defaultPrefix } + """" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("path", path0)})
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
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
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
        function(loginName0,pwd1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("loginName", encodeURIComponent(loginName0)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("pwd", encodeURIComponent(pwd1))})
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
