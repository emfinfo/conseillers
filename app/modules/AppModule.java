package modules;

import com.google.inject.AbstractModule;

/**
 * Module de configuration des objets injectés avec Guice.
 * 
 * @author jcstritt
 */
public class AppModule extends AbstractModule {

  
  @Override
  protected void configure() {
    bind(AppStart.class).asEagerSingleton();
    bind(AppStop.class).asEagerSingleton();
  }
  
}
