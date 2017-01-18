package rx.plugins;

/**
 * Created by andresato on 1/16/17.
 */

public class RxJavaTestPlugins extends RxJavaPlugins {
  RxJavaTestPlugins() {
    super();
  }

  public static void resetPlugins(){
    getInstance().reset();
  }
}
