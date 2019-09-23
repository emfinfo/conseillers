package workers;

import ch.emf.dao.JpaDaoAPI;
//import ch.emf.dao.exceptions.JpaException;
import ch.emf.dao.helpers.Logger;
import com.google.inject.AbstractModule;
//import com.google.inject.Provides;

public class GuiceModule extends AbstractModule {
  private JpaDaoAPI dao;
  private String className;

  @Override
  protected void configure() {
    className = GuiceModule.class.getSimpleName();
    Logger.debug(GuiceModule.class, "start", className);
  }

//  @Provides
//  public ConnexionWrk provideConnexionWorker() throws JpaException {
//    ConnexionWrk conWrk = new ConnexionWrk();
////    dao = conWrk.getDao();
//    Logger.debug(GuiceModule.class, dao, className);
//    return conWrk;
//  }

//  @Provides
//  public LoginWrk provideLoginWorker() {
//    Logger.debug(GuiceModule.class, dao, className);
//    return new LoginWrk(dao);
//  }

//  @Provides
//  public ConseillerWrk provideConseillerWorker() {
//    Logger.debug(GuiceModule.class, dao, className);
//    return new ConseillerWrk(dao);
//  }

}
