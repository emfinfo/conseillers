
package views.xml

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object partis_Scope0 {
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

class partis extends BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with play.twirl.api.Template1[List[models.Parti],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(partis: List[models.Parti]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.30*/("""
"""),format.raw/*2.1*/("""<partis>
  """),_display_(/*3.4*/for(parti <- partis) yield /*3.24*/ {_display_(Seq[Any](format.raw/*3.26*/("""
    """),format.raw/*4.5*/("""<parti>
      <id>"""),_display_(/*5.12*/parti/*5.17*/.getPkParti()),format.raw/*5.30*/("""</id>
      <abrev>"""),_display_(/*6.15*/parti/*6.20*/.getNomParti()),format.raw/*6.34*/("""</abrev>
    </parti>
  """)))}),format.raw/*8.4*/("""
"""),format.raw/*9.1*/("""</partis>
"""))
      }
    }
  }

  def render(partis:List[models.Parti]): play.twirl.api.XmlFormat.Appendable = apply(partis)

  def f:((List[models.Parti]) => play.twirl.api.XmlFormat.Appendable) = (partis) => apply(partis)

  def ref: this.type = this

}


}

/**/
object partis extends partis_Scope0.partis
              /*
                  -- GENERATED --
                  DATE: Tue Jul 12 17:36:34 CEST 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/partis.scala.xml
                  HASH: 34ee7224ab84741563681478746abf57bafc9aab
                  MATRIX: 753->1|875->29|902->30|939->42|974->62|1013->64|1044->69|1089->88|1102->93|1135->106|1181->126|1194->131|1228->145|1282->170|1309->171
                  LINES: 27->1|32->1|33->2|34->3|34->3|34->3|35->4|36->5|36->5|36->5|37->6|37->6|37->6|39->8|40->9
                  -- GENERATED --
              */
          