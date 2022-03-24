package Game;

import Annotation.MyAnnotation;


enum Weapon {
  KNIFE,
  RIFLE,
  GUN
}

public class Player {
  private static final int upperBoundOfPower = 1000;
  private static final int lowerBoundOfPower = 100;
  private static final String regex = "[0-9A-Za-z]+";
  private final String name;
  private int life;
  private int power;
  private final Weapon weapon;
  private int numberOfDefeats;

  public Player(String name, Weapon weapon) {
    if (!parseName(name)) {
      throw new IllegalArgumentException("Incorrect name");
    }
    this.name = name;
    this.weapon = weapon;
    this.life = 10;
    this.power = (int) (Math.random() * (upperBoundOfPower + 1 - lowerBoundOfPower)) + lowerBoundOfPower;
  }

  public int getLife() {
    return life;
  }

  public int getPower() {
    return power;
  }

  public String getName() {
    return name;
  }

  public Weapon getWeapon() {
    return weapon;
  }

  @Override
  public String toString() {
    return "life: " + life + " power: " + power + " number of defeats: " + numberOfDefeats;

  }

  private static boolean parseName(String name) {
    return name.matches(regex);
  }

  @MyAnnotation(numberOfIterations = 1)
  private void reducingLives() {
    life--;
  }

  @MyAnnotation(numberOfIterations = 10)
  private void reducingPower() {
    power--;
  }

  @MyAnnotation(numberOfIterations = 1)
  private void increasedDefeats() {
    numberOfDefeats++;
  }
}
