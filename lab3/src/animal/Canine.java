package animal;

public class Canine extends Predatory {
  private static final String type = "family";
  private static final String nameClass = "canine";

  @Override
  public String toString() {
    return nameClass + " - " + type;
  }
}
