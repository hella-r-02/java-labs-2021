package animal;

public class Chordal extends Animal {
  private static final String type = "type";
  private static final String nameClass = "chordal";

  @Override
  public String toString() {
    return nameClass + " - " + type;
  }
}
