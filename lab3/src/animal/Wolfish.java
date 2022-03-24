package animal;

public class Wolfish extends Canine {
  private static final String type="gender";
  private static final String nameClass="wolfish";

  @Override
  public String toString() {
    return nameClass+" - " + type;
  }
}
