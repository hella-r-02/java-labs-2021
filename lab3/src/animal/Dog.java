package animal;

public class Dog extends Canine {
  private static final String type = "gender";
  private static final String nameClass = "dog";

  @Override
  public String toString() {
    return nameClass + " - " + type;
  }
}
