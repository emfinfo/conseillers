
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jcstritt/Dropbox/_DEV/Java/Projets/NetBeans/Play/conseillers/conf/routes
// @DATE:Tue Jul 12 23:14:10 CEST 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseConseillerCtrl ConseillerCtrl = new controllers.ReverseConseillerCtrl(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApplicationCtrl ApplicationCtrl = new controllers.ReverseApplicationCtrl(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseLoginCtrl LoginCtrl = new controllers.ReverseLoginCtrl(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseConseillerCtrl ConseillerCtrl = new controllers.javascript.ReverseConseillerCtrl(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApplicationCtrl ApplicationCtrl = new controllers.javascript.ReverseApplicationCtrl(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseLoginCtrl LoginCtrl = new controllers.javascript.ReverseLoginCtrl(RoutesPrefix.byNamePrefix());
  }

}
