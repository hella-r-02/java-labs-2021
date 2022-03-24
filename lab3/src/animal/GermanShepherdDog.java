package animal;

public class GermanShepherdDog extends Dog {
  private static final String type = "species";
  private static final String nameClass = "german shepherd dog";

  @Override
  public String toString() {
    return nameClass + " - " + type;
  }
}
