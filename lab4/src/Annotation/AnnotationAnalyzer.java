package Annotation;

import Game.Player;

import java.lang.reflect.Method;

public class AnnotationAnalyzer {
  public static void analyze(Player player) throws Exception {
    for (Method method : player.getClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(MyAnnotation.class)) {
        int numberOfIterations = method.getAnnotation(MyAnnotation.class).numberOfIterations();
        while (numberOfIterations > 0) {
          method.setAccessible(true);
          method.invoke(player);
          numberOfIterations--;
        }
      }
    }
  }
}
