package Game;

import Annotation.AnnotationAnalyzer;

import java.util.Scanner;

public class Game {
  private static final int lowerBoundLive = 0;
  private static final int invincibleDifference = 700;
  private static final int numberOfPlayer1 = 1;
  private static final int numberOfPlayer2 = 2;

  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      System.out.println("Enter the name for player 1");
      String namePlayer1 = s.next();
      System.out.println("Choose weapon for player 1:\n1 - knife\n2 - rifle\n3 - gun");
      int numberOfWeaponPlayer1 = s.nextInt();

      Player player1 = generatingPlayer(namePlayer1, numberOfWeaponPlayer1);
      System.out.println("Enter the name for player 2");
      String namePlayer2 = s.next();

      System.out.println("Choose weapon for player 2:\n1 - knife\n2 - rifle\n3 - gun");
      int numberOfWeaponPlayer2 = s.nextInt();

      Player player2 = generatingPlayer(namePlayer2, numberOfWeaponPlayer2);
      System.out.println("player1: name: " + player1.getName() + " weapon: " + player1.getWeapon() + "\n" + player1 + "\n" + "player2: name: " + player2.getName() + " weapon: " + player2.getWeapon() + "\n" + player2);

      determineWinner(player1, player2);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Player generatingPlayer(String name, int numberOfWeapon) {
    return switch (numberOfWeapon) {
      case (1) -> new Player(name, Weapon.KNIFE);
      case (2) -> new Player(name, Weapon.RIFLE);
      case (3) -> new Player(name, Weapon.GUN);
      default -> throw new IllegalArgumentException("Non-direct weapon number");
    };
  }

  public static int generatingWinner(Player player1, Player player2) {
    if (player1.getPower() - player2.getPower() > invincibleDifference) {
      return numberOfPlayer1;
    } else if (player2.getPower() - player1.getPower() > invincibleDifference) {
      return numberOfPlayer2;
    } else {
      return (int) (Math.random() * (numberOfPlayer2 + 1 - numberOfPlayer1)) + numberOfPlayer1;
    }
  }

  public static void determineWinner(Player player1, Player player2) throws Exception {
    int countRound = 1;
    while (player1.getLife() != lowerBoundLive && player2.getLife() != lowerBoundLive) {
      int winner = generatingWinner(player1, player2);
      if (winner == numberOfPlayer1) {
        AnnotationAnalyzer.analyze(player2);
      } else {
        AnnotationAnalyzer.analyze(player1);
      }
      System.out.println("round " + countRound + "\nplayer 1: " + player1 + "\nplayer 2: " + player2);
      countRound++;
    }
    if (player1.getLife() != 0) {
      System.out.println("player 1 (" + player1.getName() + ") is winner!!!\n" + player1);
    } else {
      System.out.println("player 2 (" + player2.getName() + ") is winner!!!\n" + player2);
    }
  }

}
