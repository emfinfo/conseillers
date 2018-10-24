package dao;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;

import javax.inject.Inject;

/**
 * Contexte d'exécution personnalisé lié au thread-pool "database.dispatcher".
 * 
 * @author Jean-Claude Stritt
 * @see https://github.com/playframework/play-java-jpa-example
 */
public class DatabaseExecutionContext implements ExecutionContextExecutor {
  private final ExecutionContext executionContext;
  private static final String NAME = "database.dispatcher";

  @Inject
  public DatabaseExecutionContext(ActorSystem actorSystem) {
    this.executionContext = actorSystem.dispatchers().lookup(NAME);
  }
  
  @Override
  public void execute(Runnable command) {
    executionContext.execute(command);
  }

  @Override
  public void reportFailure(Throwable cause) {
    executionContext.reportFailure(cause);
  }
}
