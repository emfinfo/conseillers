
package views.xml

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object conseils_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.xml._
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

class conseils extends BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with play.twirl.api.Template1[List[models.Conseil],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(conseils: List[models.Conseil]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.34*/("""
"""),format.raw/*2.1*/("""<conseils>
  """),_display_(/*3.4*/for(conseil <- conseils) yield /*3.28*/ {_display_(Seq[Any](format.raw/*3.30*/("""
    """),format.raw/*4.5*/("""<conseil>
      <id>"""),_display_(/*5.12*/conseil/*5.19*/.getPkConseil()),format.raw/*5.34*/("""</id>
      <abrev>"""),_display_(/*6.15*/conseil/*6.22*/.getAbrev()),format.raw/*6.33*/("""</abrev>
    </conseil>
  """)))}),format.raw/*8.4*/("""
"""),format.raw/*9.1*/("""</conseils>
"""))
      }
    }
  }

  def render(conseils:List[models.Conseil]): play.twirl.api.XmlFormat.Appendable = apply(conseils)

  def f:((List[models.Conseil]) => play.twirl.api.XmlFormat.Appendable) = (conseils) => apply(conseils)

  def ref: this.type = this

}


}

/**/
object conseils extends conseils_Scope0.conseils
              /*
                  -- GENERATED --
                  DATE: Tue Jul 12 17:36:34 CEST 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/conseils.scala.xml
                  HASH: 6fa6352ca980990fc2c9d517f667e77f9efd35cb
                  MATRIX: 759->1|885->33|912->34|951->48|990->72|1029->74|1060->79|1107->100|1122->107|1157->122|1203->142|1218->149|1249->160|1305->187|1332->188
                  LINES: 27->1|32->1|33->2|34->3|34->3|34->3|35->4|36->5|36->5|36->5|37->6|37->6|37->6|39->8|40->9
                  -- GENERATED --
              */
          