package animal;

public class GrayWolf extends Wolfish {
  private static final String type = "species";
  private static final String nameClass = "gray wolf";

  @Override
  public String toString() {
    return nameClass + " - " + type;
  }
}
