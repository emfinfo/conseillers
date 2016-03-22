
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object conseillers_Scope0 {
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

class conseillers extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,List[models.Conseiller],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(canton: String, conseillers: List[models.Conseiller]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.56*/("""
"""),format.raw/*3.1*/("""<style>
  h1, li """),format.raw/*4.10*/("""{"""),format.raw/*4.11*/("""color:green"""),format.raw/*4.22*/("""}"""),format.raw/*4.23*/("""
"""),format.raw/*5.1*/("""</style>
<h1>Liste des conseillers ("""),_display_(/*6.29*/canton),format.raw/*6.35*/(""")</h1>
<ol>
  """),_display_(/*8.4*/for(conseiller <- conseillers) yield /*8.34*/ {_display_(Seq[Any](format.raw/*8.36*/("""
  """),format.raw/*9.3*/("""<li>
    """),_display_(/*10.6*/conseiller/*10.16*/.getNom()),format.raw/*10.25*/(""" """),_display_(/*10.27*/conseiller/*10.37*/.getPrenom()),format.raw/*10.49*/(""" """),_display_(/*10.51*/conseiller/*10.61*/.getCanton().getAbrev()),format.raw/*10.84*/(""" 
    """),format.raw/*11.5*/("""("""),_display_(/*11.7*/if(conseiller.getDateNaissance()==null)/*11.46*/ {_display_(Seq[Any](format.raw/*11.48*/("""?""")))}/*11.51*/else/*11.56*/{_display_(_display_(/*11.58*/conseiller/*11.68*/.getDateNaissance().format("yyyy")))}),_display_(/*11.104*/if(conseiller.getDateDeces()!=null)/*11.139*/ {_display_(Seq[Any](format.raw/*11.141*/("""-"""),_display_(/*11.143*/conseiller/*11.153*/.getDateDeces().format("yyyy"))))}),format.raw/*11.184*/("""), """),_display_(/*11.188*/conseiller/*11.198*/.getParti().getNomParti()),format.raw/*11.223*/("""
    """),format.raw/*12.5*/("""<ul>
      """),_display_(/*13.8*/for(activite <- conseiller.getActivites()) yield /*13.50*/ {_display_(Seq[Any](format.raw/*13.52*/("""
      """),format.raw/*14.7*/("""<li style="color:gray">
        """),_display_(/*15.10*/activite/*15.18*/.getConseil().getAbrev()),format.raw/*15.42*/(""", """),_display_(/*15.45*/if(activite.getDateEntree()==null)/*15.79*/ {_display_(Seq[Any](format.raw/*15.81*/("""?""")))}/*15.84*/else/*15.89*/{_display_(_display_(/*15.91*/activite/*15.99*/.getDateEntree().format("yyyy")))}),format.raw/*15.131*/("""-"""),_display_(/*15.133*/if(activite.getDateSortie()==null)/*15.167*/ {_display_(Seq[Any](format.raw/*15.169*/("""...""")))}/*15.174*/else/*15.179*/{_display_(_display_(/*15.181*/activite/*15.189*/.getDateSortie().format("yyyy")))}),_display_(/*15.222*/if(activite.getGroupe()!=null)/*15.252*/ {_display_(Seq[Any](format.raw/*15.254*/(""", """),_display_(/*15.257*/activite/*15.265*/.getGroupe().getNomGroupe()),format.raw/*15.292*/(""" """),format.raw/*15.293*/("""("""),_display_(/*15.295*/activite/*15.303*/.getFonction().getNomFonction().toLowerCase()),format.raw/*15.348*/(""")""")))}),format.raw/*15.350*/("""
      """),format.raw/*16.7*/("""</li>""")))}),format.raw/*16.13*/("""
    """),format.raw/*17.5*/("""</ul>
  </li>""")))}),format.raw/*18.9*/("""
"""),format.raw/*19.1*/("""</ol>

"""))
      }
    }
  }

  def render(canton:String,conseillers:List[models.Conseiller]): play.twirl.api.HtmlFormat.Appendable = apply(canton,conseillers)

  def f:((String,List[models.Conseiller]) => play.twirl.api.HtmlFormat.Appendable) = (canton,conseillers) => apply(canton,conseillers)

  def ref: this.type = this

}


}

/**/
object conseillers extends conseillers_Scope0.conseillers
              /*
                  -- GENERATED --
                  DATE: Tue Mar 22 09:08:01 CET 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/conseillers.scala.html
                  HASH: 711ff818520a273153ca0038225e864038650fb8
                  MATRIX: 781->2|930->56|957->57|1001->74|1029->75|1067->86|1095->87|1122->88|1185->125|1211->131|1251->146|1296->176|1335->178|1364->181|1400->191|1419->201|1449->210|1478->212|1497->222|1530->234|1559->236|1578->246|1622->269|1655->275|1683->277|1731->316|1771->318|1792->321|1805->326|1835->328|1854->338|1913->374|1958->409|1999->411|2029->413|2049->423|2105->454|2137->458|2157->468|2204->493|2236->498|2274->510|2332->552|2372->554|2406->561|2466->594|2483->602|2528->626|2558->629|2601->663|2641->665|2662->668|2675->673|2705->675|2722->683|2778->715|2808->717|2852->751|2893->753|2917->758|2931->763|2962->765|2980->773|3036->806|3076->836|3117->838|3148->841|3166->849|3215->876|3245->877|3275->879|3293->887|3360->932|3394->934|3428->941|3465->947|3497->952|3541->966|3569->967
                  LINES: 27->2|32->2|33->3|34->4|34->4|34->4|34->4|35->5|36->6|36->6|38->8|38->8|38->8|39->9|40->10|40->10|40->10|40->10|40->10|40->10|40->10|40->10|40->10|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|42->12|43->13|43->13|43->13|44->14|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|45->15|46->16|46->16|47->17|48->18|49->19
                  -- GENERATED --
              */
          