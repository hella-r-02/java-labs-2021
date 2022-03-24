package animal;

public class Predatory extends Mammal {
  private static final String type = "squad";
  private static final String nameClass = "predatory";

  @Override
  public String toString() {
    return nameClass + " - " + type;
  }
}
