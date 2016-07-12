
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.32*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
  <head>
    <title>"""),_display_(/*5.13*/title),format.raw/*5.18*/("""</title>
    <link rel="stylesheet" media="screen" href=""""),_display_(/*6.50*/routes/*6.56*/.Assets.versioned("stylesheets/main.css")),format.raw/*6.97*/("""">
          <link rel="shortcut icon" type="image/png" href=""""),_display_(/*7.61*/routes/*7.67*/.Assets.versioned("images/favicon.png")),format.raw/*7.106*/("""">
    <script src='"""),_display_(/*8.19*/routes/*8.25*/.Assets.versioned("javascripts/hello.js")),format.raw/*8.66*/("""' type="text/javascript"></script>
  </head>
  <body>
    """),_display_(/*11.6*/content),format.raw/*11.13*/("""
  """),format.raw/*12.3*/("""</body>
</html>"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


}

/**/
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Tue Jul 12 17:36:34 CEST 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/main.scala.html
                  HASH: 5887c2b7ba975528587e0519c1bbf493716131bf
                  MATRIX: 748->1|873->31|900->32|970->76|995->81|1079->139|1093->145|1154->186|1243->249|1257->255|1317->294|1364->315|1378->321|1439->362|1524->421|1552->428|1582->431
                  LINES: 27->1|32->1|33->2|36->5|36->5|37->6|37->6|37->6|38->7|38->7|38->7|39->8|39->8|39->8|42->11|42->11|43->12
                  -- GENERATED --
              */
          