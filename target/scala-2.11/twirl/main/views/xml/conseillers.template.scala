
package views.xml

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object conseillers_Scope0 {
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

class conseillers extends BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with play.twirl.api.Template1[List[models.Conseiller],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(conseillers: List[models.Conseiller]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.40*/("""
"""),format.raw/*3.1*/("""<conseillers>"""),_display_(/*3.15*/for(conseiller <- conseillers) yield /*3.45*/ {_display_(Seq[Any](format.raw/*3.47*/("""
  """),format.raw/*4.3*/("""<conseiller>
    <pkConseiller>"""),_display_(/*5.20*/conseiller/*5.30*/.getPkConseiller()),format.raw/*5.48*/("""</pkConseiller>
    <nom>"""),_display_(/*6.11*/conseiller/*6.21*/.getNom()),format.raw/*6.30*/("""</nom>
    <prenom>"""),_display_(/*7.14*/conseiller/*7.24*/.getPrenom()),format.raw/*7.36*/("""</prenom>
    <sexe>"""),_display_(/*8.12*/conseiller/*8.22*/.getSexe()),format.raw/*8.32*/("""</sexe>
    """),_display_(/*9.6*/if(conseiller.getOrigine==null)/*9.37*/ {_display_(Seq[Any](format.raw/*9.39*/("""<origine>null</origine>""")))}/*9.64*/else/*9.69*/{_display_(Seq[Any](format.raw/*9.70*/("""<origine>"""),_display_(/*9.80*/conseiller/*9.90*/.getOrigine()),format.raw/*9.103*/("""</origine>""")))}),format.raw/*9.114*/("""
    """),_display_(/*10.6*/if(conseiller.getDateNaissance==null)/*10.43*/ {_display_(Seq[Any](format.raw/*10.45*/("""<dateNaissance>null</dateNaissance>""")))}/*10.82*/else/*10.87*/{_display_(Seq[Any](format.raw/*10.88*/("""<dateNaissance>"""),_display_(/*10.104*/conseiller/*10.114*/.getDateNaissance.format("yyyy-MM-dd")),format.raw/*10.152*/("""</dateNaissance>""")))}),format.raw/*10.169*/("""
    """),_display_(/*11.6*/if(conseiller.getDateDeces==null)/*11.39*/ {_display_(Seq[Any](format.raw/*11.41*/("""<dateDeces>null</dateDeces>""")))}/*11.70*/else/*11.75*/{_display_(Seq[Any](format.raw/*11.76*/("""<dateDeces>"""),_display_(/*11.88*/conseiller/*11.98*/.getDateDeces().format("yyyy-MM-dd")),format.raw/*11.134*/("""</dateDeces>""")))}),format.raw/*11.147*/("""
    """),format.raw/*12.5*/("""<canton>
      <pkCanton>"""),_display_(/*13.18*/conseiller/*13.28*/.getCanton().getPkCanton()),format.raw/*13.54*/("""</pkCanton>
      <abrev>"""),_display_(/*14.15*/conseiller/*14.25*/.getCanton().getAbrev()),format.raw/*14.48*/("""</abrev>
    </canton>    
    <parti>
      <pkParti>"""),_display_(/*17.17*/conseiller/*17.27*/.getParti().getPkParti()),format.raw/*17.51*/("""</pkParti>
      <nomParti>"""),_display_(/*18.18*/conseiller/*18.28*/.getParti().getNomParti()),format.raw/*18.53*/("""</nomParti>
    </parti>    
    <activites>"""),_display_(/*20.17*/for(activite <- conseiller.getActivites()) yield /*20.59*/ {_display_(Seq[Any](format.raw/*20.61*/("""
      """),format.raw/*21.7*/("""<activite>
        <pkActivite>"""),_display_(/*22.22*/activite/*22.30*/.getPkActivite()),format.raw/*22.46*/("""</pkActivite>
        <conseil>
          <pkConseil>"""),_display_(/*24.23*/activite/*24.31*/.getConseil().getPkConseil()),format.raw/*24.59*/("""</pkConseil>
          <abrev>"""),_display_(/*25.19*/activite/*25.27*/.getConseil().getAbrev()),format.raw/*25.51*/("""</abrev>
        </conseil>
        """),_display_(/*27.10*/if(activite.getDateEntree==null)/*27.42*/ {_display_(Seq[Any](format.raw/*27.44*/("""<dateEntree>null</dateEntree>""")))}/*27.75*/else/*27.80*/{_display_(Seq[Any](format.raw/*27.81*/("""<dateEntree>"""),_display_(/*27.94*/activite/*27.102*/.getDateEntree.format("yyyy-MM-dd")),format.raw/*27.137*/("""</dateEntree>""")))}),format.raw/*27.151*/("""
        """),_display_(/*28.10*/if(activite.getDateSortie==null)/*28.42*/ {_display_(Seq[Any](format.raw/*28.44*/("""<dateSortie>null</dateSortie>""")))}/*28.75*/else/*28.80*/{_display_(Seq[Any](format.raw/*28.81*/("""<dateSortie>"""),_display_(/*28.94*/activite/*28.102*/.getDateSortie().format("yyyy-MM-dd")),format.raw/*28.139*/("""</dateSortie>""")))}),format.raw/*28.153*/("""
        """),_display_(/*29.10*/if(activite.getGroupe()!=null)/*29.40*/ {_display_(Seq[Any](format.raw/*29.42*/("""<groupe>
          <pkGroupe>"""),_display_(/*30.22*/activite/*30.30*/.getGroupe().getPkGroupe()),format.raw/*30.56*/("""</pkGroupe>
          <nomGroupe>"""),_display_(/*31.23*/activite/*31.31*/.getGroupe().getNomGroupe()),format.raw/*31.58*/("""</nomGroupe>
        </groupe>""")))}),format.raw/*32.19*/("""
        """),_display_(/*33.10*/if(activite.getFonction()!=null)/*33.42*/ {_display_(Seq[Any](format.raw/*33.44*/("""<fonction>
          <pkFonction>"""),_display_(/*34.24*/activite/*34.32*/.getFonction().getPkFonction()),format.raw/*34.62*/("""</pkFonction>
          <nomFonction>"""),_display_(/*35.25*/activite/*35.33*/.getFonction().getNomFonction()),format.raw/*35.64*/("""</nomFonction>
        </fonction>""")))}),format.raw/*36.21*/("""
      """),format.raw/*37.7*/("""</activite>""")))}),format.raw/*37.19*/("""
    """),format.raw/*38.5*/("""</activites>
  </conseiller>""")))}),format.raw/*39.17*/("""
"""),format.raw/*40.1*/("""</conseillers>



"""))
      }
    }
  }

  def render(conseillers:List[models.Conseiller]): play.twirl.api.XmlFormat.Appendable = apply(conseillers)

  def f:((List[models.Conseiller]) => play.twirl.api.XmlFormat.Appendable) = (conseillers) => apply(conseillers)

  def ref: this.type = this

}


}

/**/
object conseillers extends conseillers_Scope0.conseillers
              /*
                  -- GENERATED --
                  DATE: Tue Mar 22 09:08:01 CET 2016
                  SOURCE: /Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/app/views/conseillers.scala.xml
                  HASH: 8aa81590365fdf4afc94a3284d1d7a562c0c31b1
                  MATRIX: 768->2|900->40|927->41|967->55|1012->85|1051->87|1080->90|1138->122|1156->132|1194->150|1246->176|1264->186|1293->195|1339->215|1357->225|1389->237|1436->258|1454->268|1484->278|1522->291|1561->322|1600->324|1642->349|1654->354|1692->355|1728->365|1746->375|1780->388|1822->399|1854->405|1900->442|1940->444|1995->481|2008->486|2047->487|2091->503|2111->513|2171->551|2220->568|2252->574|2294->607|2334->609|2381->638|2394->643|2433->644|2472->656|2491->666|2549->702|2594->715|2626->720|2679->746|2698->756|2745->782|2798->808|2817->818|2861->841|2943->896|2962->906|3007->930|3062->958|3081->968|3127->993|3199->1038|3257->1080|3297->1082|3331->1089|3390->1121|3407->1129|3444->1145|3525->1199|3542->1207|3591->1235|3649->1266|3666->1274|3711->1298|3775->1335|3816->1367|3856->1369|3905->1400|3918->1405|3957->1406|3997->1419|4015->1427|4072->1462|4118->1476|4155->1486|4196->1518|4236->1520|4285->1551|4298->1556|4337->1557|4377->1570|4395->1578|4454->1615|4500->1629|4537->1639|4576->1669|4616->1671|4673->1701|4690->1709|4737->1735|4798->1769|4815->1777|4863->1804|4925->1835|4962->1845|5003->1877|5043->1879|5104->1913|5121->1921|5172->1951|5237->1989|5254->1997|5306->2028|5372->2063|5406->2070|5449->2082|5481->2087|5541->2116|5569->2117
                  LINES: 27->2|32->2|33->3|33->3|33->3|33->3|34->4|35->5|35->5|35->5|36->6|36->6|36->6|37->7|37->7|37->7|38->8|38->8|38->8|39->9|39->9|39->9|39->9|39->9|39->9|39->9|39->9|39->9|39->9|40->10|40->10|40->10|40->10|40->10|40->10|40->10|40->10|40->10|40->10|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|41->11|42->12|43->13|43->13|43->13|44->14|44->14|44->14|47->17|47->17|47->17|48->18|48->18|48->18|50->20|50->20|50->20|51->21|52->22|52->22|52->22|54->24|54->24|54->24|55->25|55->25|55->25|57->27|57->27|57->27|57->27|57->27|57->27|57->27|57->27|57->27|57->27|58->28|58->28|58->28|58->28|58->28|58->28|58->28|58->28|58->28|58->28|59->29|59->29|59->29|60->30|60->30|60->30|61->31|61->31|61->31|62->32|63->33|63->33|63->33|64->34|64->34|64->34|65->35|65->35|65->35|66->36|67->37|67->37|68->38|69->39|70->40
                  -- GENERATED --
              */
          