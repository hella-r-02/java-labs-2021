package animal;

public class EurasianWolf extends Wolfish {
  private static final String type="species";
  private static final String nameClass="eurasian wolf";

  @Override
  public String toString() {
    return nameClass+" - " + type;
  }
}
