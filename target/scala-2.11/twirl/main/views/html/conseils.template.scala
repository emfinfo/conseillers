
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object conseils_Scope0 {
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

class conseils extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[models.Conseil],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(conseils: List[models.Conseil]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.34*/("""
"""),format.raw/*3.1*/("""<style>
  h1, li """),format.raw/*4.10*/("""{"""),format.raw/*4.11*/("""color:green"""),format.raw/*4.22*/("""}"""),format.raw/*4.23*/("""
"""),format.raw/*5.1*/("""</style>
<h1>Liste des conseils</h1>
<ul>
	"""),_display_(/*8.3*/for(conseil <- conseils) yield /*8.27*/ {_display_(Seq[Any](format.raw/*8.29*/("""
    """),format.raw/*9.5*/("""<li>"""),_display_(/*9.10*/conseil/*9.17*/.getAbrev()),format.raw/*9.28*/("""</li>
	""")))}),format.raw/*10.3*/("""
"""),format.raw/*11.1*/("""</ul>

"""))
      }
    }
  }

  def render(conseils:List[models.Conseil]): play.twirl.api.HtmlFormat.Appendable = apply(conseils)

  def f:((List[models.Conseil]) => play.twirl.api.HtmlFormat.Appendable) = (conseils) => apply(conseils)

  def ref: this.type = this

}


}

/**/
object conseils extends conseils_Scope0.conseils
              /*
                  -- GENERATED --
                  DATE: Tue Mar 22 09:08:01 CET 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/conseils.scala.html
                  HASH: f42c1515a13349da0998f925fea95fedfb1c0121
                  MATRIX: 765->2|892->34|919->35|963->52|991->53|1029->64|1057->65|1084->66|1153->110|1192->134|1231->136|1262->141|1293->146|1308->153|1339->164|1377->172|1405->173
                  LINES: 27->2|32->2|33->3|34->4|34->4|34->4|34->4|35->5|38->8|38->8|38->8|39->9|39->9|39->9|39->9|40->10|41->11
                  -- GENERATED --
              */
          