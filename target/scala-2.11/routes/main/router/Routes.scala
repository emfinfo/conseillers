
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/conf/routes
// @DATE:Tue Mar 22 09:08:00 CET 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

object Routes extends Routes

class Routes extends GeneratedRouter {

  import ReverseRouteContext.empty

  override val errorHandler: play.api.http.HttpErrorHandler = play.api.http.LazyHttpErrorHandler

  private var _prefix = "/"

  def withPrefix(prefix: String): Routes = {
    _prefix = prefix
    router.RoutesPrefix.setPrefix(prefix)
    
    this
  }

  def prefix: String = _prefix

  lazy val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation: Seq[(String, String, String)] = List(
    ("""GET""", prefix, """controllers.ApplicationCtrl.index()"""),
    ("""OPTIONS""", prefix + (if(prefix.endsWith("/")) "" else "/") + """$path<.+>""", """controllers.ApplicationCtrl.checkPreFlight(path:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """version""", """controllers.ApplicationCtrl.lireVersionServeur()"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cantons""", """controllers.ConseillerCtrl.chargerCantons(fmt:String = "html")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cantons.xml""", """controllers.ConseillerCtrl.chargerCantons(fmt:String = "xml")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cantons.json""", """controllers.ConseillerCtrl.chargerCantons(fmt:String = "json")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseils""", """controllers.ConseillerCtrl.chargerConseils(fmt:String = "html")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseils.xml""", """controllers.ConseillerCtrl.chargerConseils(fmt:String = "xml")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseils.json""", """controllers.ConseillerCtrl.chargerConseils(fmt:String = "json")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """partis""", """controllers.ConseillerCtrl.chargerPartis(fmt:String = "html")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """partis.xml""", """controllers.ConseillerCtrl.chargerPartis(fmt:String = "xml")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """partis.json""", """controllers.ConseillerCtrl.chargerPartis(fmt:String = "json")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "html", canton:String = "Suisse", conseil:String = "", parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers/$canton<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "html", canton:String, conseil:String = "", parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers/$canton<[^/]+>/$conseil<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "html", canton:String, conseil:String, parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "html", canton:String, conseil:String, parti:String, actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>/$actuels<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "html", canton:String, conseil:String, parti:String, actuels:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.xml""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "xml", canton:String = "Suisse", conseil:String = "", parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.xml/$canton<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "xml", canton:String, conseil:String = "", parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.xml/$canton<[^/]+>/$conseil<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "xml", canton:String, conseil:String, parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.xml/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "xml", canton:String, conseil:String, parti:String, actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.xml/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>/$actuels<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "xml", canton:String, conseil:String, parti:String, actuels:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.json""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "json", canton:String = "Suisse", conseil:String = "", parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.json/$canton<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "json", canton:String, conseil:String = "", parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.json/$canton<[^/]+>/$conseil<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "json", canton:String, conseil:String, parti:String = "", actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.json/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "json", canton:String, conseil:String, parti:String, actuels:String = "false")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conseillers.json/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>/$actuels<[^/]+>""", """controllers.ConseillerCtrl.chargerConseillers(fmt:String = "json", canton:String, conseil:String, parti:String, actuels:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login/$loginName<[^/]+>/$pwd<[^/]+>""", """controllers.LoginCtrl.login(loginName:String, pwd:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""", """controllers.LoginCtrl.logout"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sessionStatus""", """controllers.LoginCtrl.sessionStatus"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """createLogin""", """controllers.LoginCtrl.createLogin"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """unauthorizedAccess""", """controllers.LoginCtrl.unauthorizedAccess()"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_ApplicationCtrl_index0_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_ApplicationCtrl_index0_invoker = createInvoker(
    controllers.ApplicationCtrl.index(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationCtrl",
      "index",
      Nil,
      "GET",
      """ Home page""",
      this.prefix + """"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_ApplicationCtrl_checkPreFlight1_route: Route.ParamsExtractor = Route("OPTIONS",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), DynamicPart("path", """.+""",false)))
  )
  private[this] lazy val controllers_ApplicationCtrl_checkPreFlight1_invoker = createInvoker(
    controllers.ApplicationCtrl.checkPreFlight(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationCtrl",
      "checkPreFlight",
      Seq(classOf[String]),
      "OPTIONS",
      """""",
      this.prefix + """$path<.+>"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_ApplicationCtrl_lireVersionServeur2_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("version")))
  )
  private[this] lazy val controllers_ApplicationCtrl_lireVersionServeur2_invoker = createInvoker(
    controllers.ApplicationCtrl.lireVersionServeur(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationCtrl",
      "lireVersionServeur",
      Nil,
      "GET",
      """ version du serveur""",
      this.prefix + """version"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_ConseillerCtrl_chargerCantons3_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cantons")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerCantons3_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerCantons(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerCantons",
      Seq(classOf[String]),
      "GET",
      """ requêtes sur les "cantons"""",
      this.prefix + """cantons"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_ConseillerCtrl_chargerCantons4_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cantons.xml")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerCantons4_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerCantons(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerCantons",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """cantons.xml"""
    )
  )

  // @LINE:16
  private[this] lazy val controllers_ConseillerCtrl_chargerCantons5_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cantons.json")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerCantons5_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerCantons(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerCantons",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """cantons.json"""
    )
  )

  // @LINE:19
  private[this] lazy val controllers_ConseillerCtrl_chargerConseils6_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseils")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseils6_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseils(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseils",
      Seq(classOf[String]),
      "GET",
      """ requêtes sur les "conseils"""",
      this.prefix + """conseils"""
    )
  )

  // @LINE:20
  private[this] lazy val controllers_ConseillerCtrl_chargerConseils7_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseils.xml")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseils7_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseils(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseils",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """conseils.xml"""
    )
  )

  // @LINE:21
  private[this] lazy val controllers_ConseillerCtrl_chargerConseils8_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseils.json")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseils8_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseils(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseils",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """conseils.json"""
    )
  )

  // @LINE:24
  private[this] lazy val controllers_ConseillerCtrl_chargerPartis9_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("partis")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerPartis9_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerPartis(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerPartis",
      Seq(classOf[String]),
      "GET",
      """ requêtes sur les "partis"""",
      this.prefix + """partis"""
    )
  )

  // @LINE:25
  private[this] lazy val controllers_ConseillerCtrl_chargerPartis10_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("partis.xml")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerPartis10_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerPartis(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerPartis",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """partis.xml"""
    )
  )

  // @LINE:26
  private[this] lazy val controllers_ConseillerCtrl_chargerPartis11_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("partis.json")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerPartis11_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerPartis(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerPartis",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """partis.json"""
    )
  )

  // @LINE:29
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers12_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers12_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """ requêtes sur les "conseillers"""",
      this.prefix + """conseillers"""
    )
  )

  // @LINE:31
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers13_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers/"), DynamicPart("canton", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers13_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers/$canton<[^/]+>"""
    )
  )

  // @LINE:32
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers14_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers14_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers/$canton<[^/]+>/$conseil<[^/]+>"""
    )
  )

  // @LINE:33
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers15_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true), StaticPart("/"), DynamicPart("parti", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers15_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>"""
    )
  )

  // @LINE:34
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers16_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true), StaticPart("/"), DynamicPart("parti", """[^/]+""",true), StaticPart("/"), DynamicPart("actuels", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers16_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>/$actuels<[^/]+>"""
    )
  )

  // @LINE:36
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers17_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.xml")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers17_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.xml"""
    )
  )

  // @LINE:37
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers18_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.xml/"), DynamicPart("canton", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers18_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.xml/$canton<[^/]+>"""
    )
  )

  // @LINE:38
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers19_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.xml/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers19_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.xml/$canton<[^/]+>/$conseil<[^/]+>"""
    )
  )

  // @LINE:39
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers20_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.xml/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true), StaticPart("/"), DynamicPart("parti", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers20_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.xml/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>"""
    )
  )

  // @LINE:40
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers21_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.xml/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true), StaticPart("/"), DynamicPart("parti", """[^/]+""",true), StaticPart("/"), DynamicPart("actuels", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers21_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.xml/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>/$actuels<[^/]+>"""
    )
  )

  // @LINE:42
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers22_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.json")))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers22_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.json"""
    )
  )

  // @LINE:43
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers23_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.json/"), DynamicPart("canton", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers23_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.json/$canton<[^/]+>"""
    )
  )

  // @LINE:44
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers24_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.json/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers24_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.json/$canton<[^/]+>/$conseil<[^/]+>"""
    )
  )

  // @LINE:45
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers25_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.json/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true), StaticPart("/"), DynamicPart("parti", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers25_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.json/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>"""
    )
  )

  // @LINE:46
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers26_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("conseillers.json/"), DynamicPart("canton", """[^/]+""",true), StaticPart("/"), DynamicPart("conseil", """[^/]+""",true), StaticPart("/"), DynamicPart("parti", """[^/]+""",true), StaticPart("/"), DynamicPart("actuels", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ConseillerCtrl_chargerConseillers26_invoker = createInvoker(
    controllers.ConseillerCtrl.chargerConseillers(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ConseillerCtrl",
      "chargerConseillers",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """conseillers.json/$canton<[^/]+>/$conseil<[^/]+>/$parti<[^/]+>/$actuels<[^/]+>"""
    )
  )

  // @LINE:49
  private[this] lazy val controllers_LoginCtrl_login27_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login/"), DynamicPart("loginName", """[^/]+""",true), StaticPart("/"), DynamicPart("pwd", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LoginCtrl_login27_invoker = createInvoker(
    controllers.LoginCtrl.login(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LoginCtrl",
      "login",
      Seq(classOf[String], classOf[String]),
      "GET",
      """ requêtes sur le login""",
      this.prefix + """login/$loginName<[^/]+>/$pwd<[^/]+>"""
    )
  )

  // @LINE:50
  private[this] lazy val controllers_LoginCtrl_logout28_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("logout")))
  )
  private[this] lazy val controllers_LoginCtrl_logout28_invoker = createInvoker(
    controllers.LoginCtrl.logout,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LoginCtrl",
      "logout",
      Nil,
      "GET",
      """""",
      this.prefix + """logout"""
    )
  )

  // @LINE:51
  private[this] lazy val controllers_LoginCtrl_sessionStatus29_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sessionStatus")))
  )
  private[this] lazy val controllers_LoginCtrl_sessionStatus29_invoker = createInvoker(
    controllers.LoginCtrl.sessionStatus,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LoginCtrl",
      "sessionStatus",
      Nil,
      "GET",
      """""",
      this.prefix + """sessionStatus"""
    )
  )

  // @LINE:52
  private[this] lazy val controllers_LoginCtrl_createLogin30_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("createLogin")))
  )
  private[this] lazy val controllers_LoginCtrl_createLogin30_invoker = createInvoker(
    controllers.LoginCtrl.createLogin,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LoginCtrl",
      "createLogin",
      Nil,
      "POST",
      """""",
      this.prefix + """createLogin"""
    )
  )

  // @LINE:53
  private[this] lazy val controllers_LoginCtrl_unauthorizedAccess31_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unauthorizedAccess")))
  )
  private[this] lazy val controllers_LoginCtrl_unauthorizedAccess31_invoker = createInvoker(
    controllers.LoginCtrl.unauthorizedAccess(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LoginCtrl",
      "unauthorizedAccess",
      Nil,
      "GET",
      """""",
      this.prefix + """unauthorizedAccess"""
    )
  )

  // @LINE:56
  private[this] lazy val controllers_Assets_at32_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at32_invoker = createInvoker(
    controllers.Assets.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/$file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_ApplicationCtrl_index0_route(params) =>
      call { 
        controllers_ApplicationCtrl_index0_invoker.call(controllers.ApplicationCtrl.index())
      }
  
    // @LINE:8
    case controllers_ApplicationCtrl_checkPreFlight1_route(params) =>
      call(params.fromPath[String]("path", None)) { (path) =>
        controllers_ApplicationCtrl_checkPreFlight1_invoker.call(controllers.ApplicationCtrl.checkPreFlight(path))
      }
  
    // @LINE:11
    case controllers_ApplicationCtrl_lireVersionServeur2_route(params) =>
      call { 
        controllers_ApplicationCtrl_lireVersionServeur2_invoker.call(controllers.ApplicationCtrl.lireVersionServeur())
      }
  
    // @LINE:14
    case controllers_ConseillerCtrl_chargerCantons3_route(params) =>
      call(Param[String]("fmt", Right("html"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerCantons3_invoker.call(controllers.ConseillerCtrl.chargerCantons(fmt))
      }
  
    // @LINE:15
    case controllers_ConseillerCtrl_chargerCantons4_route(params) =>
      call(Param[String]("fmt", Right("xml"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerCantons4_invoker.call(controllers.ConseillerCtrl.chargerCantons(fmt))
      }
  
    // @LINE:16
    case controllers_ConseillerCtrl_chargerCantons5_route(params) =>
      call(Param[String]("fmt", Right("json"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerCantons5_invoker.call(controllers.ConseillerCtrl.chargerCantons(fmt))
      }
  
    // @LINE:19
    case controllers_ConseillerCtrl_chargerConseils6_route(params) =>
      call(Param[String]("fmt", Right("html"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerConseils6_invoker.call(controllers.ConseillerCtrl.chargerConseils(fmt))
      }
  
    // @LINE:20
    case controllers_ConseillerCtrl_chargerConseils7_route(params) =>
      call(Param[String]("fmt", Right("xml"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerConseils7_invoker.call(controllers.ConseillerCtrl.chargerConseils(fmt))
      }
  
    // @LINE:21
    case controllers_ConseillerCtrl_chargerConseils8_route(params) =>
      call(Param[String]("fmt", Right("json"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerConseils8_invoker.call(controllers.ConseillerCtrl.chargerConseils(fmt))
      }
  
    // @LINE:24
    case controllers_ConseillerCtrl_chargerPartis9_route(params) =>
      call(Param[String]("fmt", Right("html"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerPartis9_invoker.call(controllers.ConseillerCtrl.chargerPartis(fmt))
      }
  
    // @LINE:25
    case controllers_ConseillerCtrl_chargerPartis10_route(params) =>
      call(Param[String]("fmt", Right("xml"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerPartis10_invoker.call(controllers.ConseillerCtrl.chargerPartis(fmt))
      }
  
    // @LINE:26
    case controllers_ConseillerCtrl_chargerPartis11_route(params) =>
      call(Param[String]("fmt", Right("json"))) { (fmt) =>
        controllers_ConseillerCtrl_chargerPartis11_invoker.call(controllers.ConseillerCtrl.chargerPartis(fmt))
      }
  
    // @LINE:29
    case controllers_ConseillerCtrl_chargerConseillers12_route(params) =>
      call(Param[String]("fmt", Right("html")), Param[String]("canton", Right("Suisse")), Param[String]("conseil", Right("")), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers12_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:31
    case controllers_ConseillerCtrl_chargerConseillers13_route(params) =>
      call(Param[String]("fmt", Right("html")), params.fromPath[String]("canton", None), Param[String]("conseil", Right("")), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers13_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:32
    case controllers_ConseillerCtrl_chargerConseillers14_route(params) =>
      call(Param[String]("fmt", Right("html")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers14_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:33
    case controllers_ConseillerCtrl_chargerConseillers15_route(params) =>
      call(Param[String]("fmt", Right("html")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), params.fromPath[String]("parti", None), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers15_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:34
    case controllers_ConseillerCtrl_chargerConseillers16_route(params) =>
      call(Param[String]("fmt", Right("html")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), params.fromPath[String]("parti", None), params.fromPath[String]("actuels", None)) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers16_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:36
    case controllers_ConseillerCtrl_chargerConseillers17_route(params) =>
      call(Param[String]("fmt", Right("xml")), Param[String]("canton", Right("Suisse")), Param[String]("conseil", Right("")), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers17_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:37
    case controllers_ConseillerCtrl_chargerConseillers18_route(params) =>
      call(Param[String]("fmt", Right("xml")), params.fromPath[String]("canton", None), Param[String]("conseil", Right("")), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers18_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:38
    case controllers_ConseillerCtrl_chargerConseillers19_route(params) =>
      call(Param[String]("fmt", Right("xml")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers19_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:39
    case controllers_ConseillerCtrl_chargerConseillers20_route(params) =>
      call(Param[String]("fmt", Right("xml")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), params.fromPath[String]("parti", None), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers20_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:40
    case controllers_ConseillerCtrl_chargerConseillers21_route(params) =>
      call(Param[String]("fmt", Right("xml")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), params.fromPath[String]("parti", None), params.fromPath[String]("actuels", None)) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers21_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:42
    case controllers_ConseillerCtrl_chargerConseillers22_route(params) =>
      call(Param[String]("fmt", Right("json")), Param[String]("canton", Right("Suisse")), Param[String]("conseil", Right("")), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers22_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:43
    case controllers_ConseillerCtrl_chargerConseillers23_route(params) =>
      call(Param[String]("fmt", Right("json")), params.fromPath[String]("canton", None), Param[String]("conseil", Right("")), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers23_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:44
    case controllers_ConseillerCtrl_chargerConseillers24_route(params) =>
      call(Param[String]("fmt", Right("json")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), Param[String]("parti", Right("")), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers24_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:45
    case controllers_ConseillerCtrl_chargerConseillers25_route(params) =>
      call(Param[String]("fmt", Right("json")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), params.fromPath[String]("parti", None), Param[String]("actuels", Right("false"))) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers25_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:46
    case controllers_ConseillerCtrl_chargerConseillers26_route(params) =>
      call(Param[String]("fmt", Right("json")), params.fromPath[String]("canton", None), params.fromPath[String]("conseil", None), params.fromPath[String]("parti", None), params.fromPath[String]("actuels", None)) { (fmt, canton, conseil, parti, actuels) =>
        controllers_ConseillerCtrl_chargerConseillers26_invoker.call(controllers.ConseillerCtrl.chargerConseillers(fmt, canton, conseil, parti, actuels))
      }
  
    // @LINE:49
    case controllers_LoginCtrl_login27_route(params) =>
      call(params.fromPath[String]("loginName", None), params.fromPath[String]("pwd", None)) { (loginName, pwd) =>
        controllers_LoginCtrl_login27_invoker.call(controllers.LoginCtrl.login(loginName, pwd))
      }
  
    // @LINE:50
    case controllers_LoginCtrl_logout28_route(params) =>
      call { 
        controllers_LoginCtrl_logout28_invoker.call(controllers.LoginCtrl.logout)
      }
  
    // @LINE:51
    case controllers_LoginCtrl_sessionStatus29_route(params) =>
      call { 
        controllers_LoginCtrl_sessionStatus29_invoker.call(controllers.LoginCtrl.sessionStatus)
      }
  
    // @LINE:52
    case controllers_LoginCtrl_createLogin30_route(params) =>
      call { 
        controllers_LoginCtrl_createLogin30_invoker.call(controllers.LoginCtrl.createLogin)
      }
  
    // @LINE:53
    case controllers_LoginCtrl_unauthorizedAccess31_route(params) =>
      call { 
        controllers_LoginCtrl_unauthorizedAccess31_invoker.call(controllers.LoginCtrl.unauthorizedAccess())
      }
  
    // @LINE:56
    case controllers_Assets_at32_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at32_invoker.call(controllers.Assets.at(path, file))
      }
  }
}