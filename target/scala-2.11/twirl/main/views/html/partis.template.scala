
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object partis_Scope0 {
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

class partis extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[models.Parti],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(partis: List[models.Parti]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.30*/("""
"""),format.raw/*2.1*/("""<style>
  h1, li """),format.raw/*3.10*/("""{"""),format.raw/*3.11*/("""color:green"""),format.raw/*3.22*/("""}"""),format.raw/*3.23*/("""
"""),format.raw/*4.1*/("""</style>
<h1>Liste des partis</h1>
<ul>
	"""),_display_(/*7.3*/for(parti <- partis) yield /*7.23*/ {_display_(Seq[Any](format.raw/*7.25*/("""
    """),format.raw/*8.5*/("""<li>"""),_display_(/*8.10*/parti/*8.15*/.getNomParti()),format.raw/*8.29*/("""</li>
	""")))}),format.raw/*9.3*/("""
"""),format.raw/*10.1*/("""</ul>
"""))
      }
    }
  }

  def render(partis:List[models.Parti]): play.twirl.api.HtmlFormat.Appendable = apply(partis)

  def f:((List[models.Parti]) => play.twirl.api.HtmlFormat.Appendable) = (partis) => apply(partis)

  def ref: this.type = this

}


}

/**/
object partis extends partis_Scope0.partis
              /*
                  -- GENERATED --
                  DATE: Tue Mar 22 09:48:10 CET 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/partis.scala.html
                  HASH: d7a75a881ff8da3080b7ad1aa3173a163b9cf630
                  MATRIX: 759->1|882->29|909->30|953->47|981->48|1019->59|1047->60|1074->61|1141->103|1176->123|1215->125|1246->130|1277->135|1290->140|1324->154|1361->162|1389->163
                  LINES: 27->1|32->1|33->2|34->3|34->3|34->3|34->3|35->4|38->7|38->7|38->7|39->8|39->8|39->8|39->8|40->9|41->10
                  -- GENERATED --
              */
          