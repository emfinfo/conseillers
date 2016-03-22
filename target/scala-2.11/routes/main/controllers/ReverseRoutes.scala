
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/conf/routes
// @DATE:Tue Mar 22 09:08:00 CET 2016

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers {

  // @LINE:14
  class ReverseConseillerCtrl(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:19
    def chargerConseils(fmt:String): Call = {
    
      (fmt: @unchecked) match {
      
        // @LINE:19
        case (fmt) if fmt == "html" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "html")))
          Call("GET", _prefix + { _defaultPrefix } + "conseils")
      
        // @LINE:20
        case (fmt) if fmt == "xml" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "xml")))
          Call("GET", _prefix + { _defaultPrefix } + "conseils.xml")
      
        // @LINE:21
        case (fmt) if fmt == "json" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "json")))
          Call("GET", _prefix + { _defaultPrefix } + "conseils.json")
      
      }
    
    }
  
    // @LINE:24
    def chargerPartis(fmt:String): Call = {
    
      (fmt: @unchecked) match {
      
        // @LINE:24
        case (fmt) if fmt == "html" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "html")))
          Call("GET", _prefix + { _defaultPrefix } + "partis")
      
        // @LINE:25
        case (fmt) if fmt == "xml" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "xml")))
          Call("GET", _prefix + { _defaultPrefix } + "partis.xml")
      
        // @LINE:26
        case (fmt) if fmt == "json" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "json")))
          Call("GET", _prefix + { _defaultPrefix } + "partis.json")
      
      }
    
    }
  
    // @LINE:14
    def chargerCantons(fmt:String): Call = {
    
      (fmt: @unchecked) match {
      
        // @LINE:14
        case (fmt) if fmt == "html" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "html")))
          Call("GET", _prefix + { _defaultPrefix } + "cantons")
      
        // @LINE:15
        case (fmt) if fmt == "xml" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "xml")))
          Call("GET", _prefix + { _defaultPrefix } + "cantons.xml")
      
        // @LINE:16
        case (fmt) if fmt == "json" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "json")))
          Call("GET", _prefix + { _defaultPrefix } + "cantons.json")
      
      }
    
    }
  
    // @LINE:29
    def chargerConseillers(fmt:String, canton:String, conseil:String, parti:String, actuels:String): Call = {
    
      (fmt: @unchecked, canton: @unchecked, conseil: @unchecked, parti: @unchecked, actuels: @unchecked) match {
      
        // @LINE:29
        case (fmt, canton, conseil, parti, actuels) if fmt == "html" && canton == "Suisse" && conseil == "" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "html"), ("canton", "Suisse"), ("conseil", ""), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers")
      
        // @LINE:31
        case (fmt, canton, conseil, parti, actuels) if fmt == "html" && conseil == "" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "html"), ("conseil", ""), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)))
      
        // @LINE:32
        case (fmt, canton, conseil, parti, actuels) if fmt == "html" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "html"), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)))
      
        // @LINE:33
        case (fmt, canton, conseil, parti, actuels) if fmt == "html" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "html"), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)) + "/" + implicitly[PathBindable[String]].unbind("parti", dynamicString(parti)))
      
        // @LINE:34
        case (fmt, canton, conseil, parti, actuels) if fmt == "html" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "html")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)) + "/" + implicitly[PathBindable[String]].unbind("parti", dynamicString(parti)) + "/" + implicitly[PathBindable[String]].unbind("actuels", dynamicString(actuels)))
      
        // @LINE:36
        case (fmt, canton, conseil, parti, actuels) if fmt == "xml" && canton == "Suisse" && conseil == "" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "xml"), ("canton", "Suisse"), ("conseil", ""), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.xml")
      
        // @LINE:37
        case (fmt, canton, conseil, parti, actuels) if fmt == "xml" && conseil == "" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "xml"), ("conseil", ""), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.xml/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)))
      
        // @LINE:38
        case (fmt, canton, conseil, parti, actuels) if fmt == "xml" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "xml"), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.xml/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)))
      
        // @LINE:39
        case (fmt, canton, conseil, parti, actuels) if fmt == "xml" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "xml"), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.xml/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)) + "/" + implicitly[PathBindable[String]].unbind("parti", dynamicString(parti)))
      
        // @LINE:40
        case (fmt, canton, conseil, parti, actuels) if fmt == "xml" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "xml")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.xml/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)) + "/" + implicitly[PathBindable[String]].unbind("parti", dynamicString(parti)) + "/" + implicitly[PathBindable[String]].unbind("actuels", dynamicString(actuels)))
      
        // @LINE:42
        case (fmt, canton, conseil, parti, actuels) if fmt == "json" && canton == "Suisse" && conseil == "" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "json"), ("canton", "Suisse"), ("conseil", ""), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.json")
      
        // @LINE:43
        case (fmt, canton, conseil, parti, actuels) if fmt == "json" && conseil == "" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "json"), ("conseil", ""), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.json/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)))
      
        // @LINE:44
        case (fmt, canton, conseil, parti, actuels) if fmt == "json" && parti == "" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "json"), ("parti", ""), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.json/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)))
      
        // @LINE:45
        case (fmt, canton, conseil, parti, actuels) if fmt == "json" && actuels == "false" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "json"), ("actuels", "false")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.json/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)) + "/" + implicitly[PathBindable[String]].unbind("parti", dynamicString(parti)))
      
        // @LINE:46
        case (fmt, canton, conseil, parti, actuels) if fmt == "json" =>
          implicit val _rrc = new ReverseRouteContext(Map(("fmt", "json")))
          Call("GET", _prefix + { _defaultPrefix } + "conseillers.json/" + implicitly[PathBindable[String]].unbind("canton", dynamicString(canton)) + "/" + implicitly[PathBindable[String]].unbind("conseil", dynamicString(conseil)) + "/" + implicitly[PathBindable[String]].unbind("parti", dynamicString(parti)) + "/" + implicitly[PathBindable[String]].unbind("actuels", dynamicString(actuels)))
      
      }
    
    }
  
  }

  // @LINE:7
  class ReverseApplicationCtrl(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def checkPreFlight(path:String): Call = {
      import ReverseRouteContext.empty
      Call("OPTIONS", _prefix + { _defaultPrefix } + implicitly[PathBindable[String]].unbind("path", path))
    }
  
    // @LINE:7
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
    // @LINE:11
    def lireVersionServeur(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "version")
    }
  
  }

  // @LINE:56
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:56
    def at(file:String): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
    }
  
  }

  // @LINE:49
  class ReverseLoginCtrl(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:53
    def unauthorizedAccess(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "unauthorizedAccess")
    }
  
    // @LINE:51
    def sessionStatus(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "sessionStatus")
    }
  
    // @LINE:49
    def login(loginName:String, pwd:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "login/" + implicitly[PathBindable[String]].unbind("loginName", dynamicString(loginName)) + "/" + implicitly[PathBindable[String]].unbind("pwd", dynamicString(pwd)))
    }
  
    // @LINE:50
    def logout(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "logout")
    }
  
    // @LINE:52
    def createLogin(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "createLogin")
    }
  
  }


}