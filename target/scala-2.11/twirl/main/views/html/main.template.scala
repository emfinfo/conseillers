
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
        <title>"""),_display_(/*5.17*/title),format.raw/*5.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*6.54*/routes/*6.60*/.Assets.at("stylesheets/main.css")),format.raw/*6.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*7.59*/routes/*7.65*/.Assets.at("images/favicon.png")),format.raw/*7.97*/("""">
        <script src='"""),_display_(/*8.23*/routes/*8.29*/.Assets.at("javascripts/jquery-1.7.1.min.js")),format.raw/*8.74*/("""' type="text/javascript"></script>
    </head>
    <body>
        """),_display_(/*11.10*/content),format.raw/*11.17*/("""
    """),format.raw/*12.5*/("""</body>
</html>
"""))
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
                  DATE: Tue Mar 22 09:08:01 CET 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/main.scala.html
                  HASH: 03d1bd34089210cda343875e241341f1238596b1
                  MATRIX: 748->1|873->31|900->32|976->82|1001->87|1089->149|1103->155|1157->189|1244->250|1258->256|1310->288|1361->313|1375->319|1440->364|1534->431|1562->438|1594->443
                  LINES: 27->1|32->1|33->2|36->5|36->5|37->6|37->6|37->6|38->7|38->7|38->7|39->8|39->8|39->8|42->11|42->11|43->12
                  -- GENERATED --
              */
          