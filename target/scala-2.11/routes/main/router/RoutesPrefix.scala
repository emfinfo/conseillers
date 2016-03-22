
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/conf/routes
// @DATE:Tue Mar 22 09:08:00 CET 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
