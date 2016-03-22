
package views.xml

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object cantons_Scope0 {
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

class cantons extends BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with play.twirl.api.Template1[List[models.Canton],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(cantons: List[models.Canton]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.32*/("""
"""),format.raw/*3.1*/("""<cantons>
  """),_display_(/*4.4*/for(canton <- cantons) yield /*4.26*/ {_display_(Seq[Any](format.raw/*4.28*/("""
    """),format.raw/*5.5*/("""<canton>
      <id>"""),_display_(/*6.12*/canton/*6.18*/.getPkCanton()),format.raw/*6.32*/("""</id>
      <abrev>"""),_display_(/*7.15*/canton/*7.21*/.getAbrev()),format.raw/*7.32*/("""</abrev>
    </canton>
  """)))}),format.raw/*9.4*/("""
"""),format.raw/*10.1*/("""</cantons>

"""))
      }
    }
  }

  def render(cantons:List[models.Canton]): play.twirl.api.XmlFormat.Appendable = apply(cantons)

  def f:((List[models.Canton]) => play.twirl.api.XmlFormat.Appendable) = (cantons) => apply(cantons)

  def ref: this.type = this

}


}

/**/
object cantons extends cantons_Scope0.cantons
              /*
                  -- GENERATED --
                  DATE: Tue Mar 22 09:48:09 CET 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/cantons.scala.xml
                  HASH: 058948d5c03656cbdf33aa56d8119d5c15e6eb7a
                  MATRIX: 756->2|880->32|907->33|945->46|982->68|1021->70|1052->75|1098->95|1112->101|1146->115|1192->135|1206->141|1237->152|1292->178|1320->179
                  LINES: 27->2|32->2|33->3|34->4|34->4|34->4|35->5|36->6|36->6|36->6|37->7|37->7|37->7|39->9|40->10
                  -- GENERATED --
              */
          