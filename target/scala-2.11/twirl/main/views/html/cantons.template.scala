
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object cantons_Scope0 {
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

class cantons extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[models.Canton],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(cantons: List[models.Canton]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.32*/("""
"""),format.raw/*3.1*/("""<style>
  h1, li """),format.raw/*4.10*/("""{"""),format.raw/*4.11*/("""color:green"""),format.raw/*4.22*/("""}"""),format.raw/*4.23*/("""
"""),format.raw/*5.1*/("""</style>
<h1>Liste des cantons</h1>
<ul>
	"""),_display_(/*8.3*/for(canton <- cantons) yield /*8.25*/ {_display_(Seq[Any](format.raw/*8.27*/("""
    """),format.raw/*9.5*/("""<li>"""),_display_(/*9.10*/canton/*9.16*/.getAbrev()),format.raw/*9.27*/("""</li>
	""")))}),format.raw/*10.3*/("""
"""),format.raw/*11.1*/("""</ul>


"""))
      }
    }
  }

  def render(cantons:List[models.Canton]): play.twirl.api.HtmlFormat.Appendable = apply(cantons)

  def f:((List[models.Canton]) => play.twirl.api.HtmlFormat.Appendable) = (cantons) => apply(cantons)

  def ref: this.type = this

}


}

/**/
object cantons extends cantons_Scope0.cantons
              /*
                  -- GENERATED --
                  DATE: Tue Mar 22 09:08:01 CET 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/cantons.scala.html
                  HASH: b763c508ef46babaa56fd0e048506c99a007fd8b
                  MATRIX: 762->2|887->32|914->33|958->50|986->51|1024->62|1052->63|1079->64|1147->107|1184->129|1223->131|1254->136|1285->141|1299->147|1330->158|1368->166|1396->167
                  LINES: 27->2|32->2|33->3|34->4|34->4|34->4|34->4|35->5|38->8|38->8|38->8|39->9|39->9|39->9|39->9|40->10|41->11
                  -- GENERATED --
              */
          