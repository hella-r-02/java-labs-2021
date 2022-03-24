package animal;

public class Mammal extends Chordal {
  private static final String type = "class";
  private static final String nameClass = "mammal";

  @Override
  public String toString() {
    return nameClass + " - " + type;
  }
}
