package com.company;

import animal.*;
import policies.Policies;
import queue.Queue;

import javax.naming.SizeLimitExceededException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static final int numberOfClasses = 9;
  public static final int firstNumberOfGender = 5;
  public static final int lastNumberOfGender = 6;
  public static final int firstNumberOfSpecies = 7;
  public static final int sizeForGender = 2;
  public static final int sizeForSpecies = 1;
  public static void main(String[] args) {

    try {
      System.out.println("forming upper bound queue:");
      int numberUpperBound = getNumberBound();
      int sizeForProduce = getSizeForProduce(numberUpperBound);
      Queue<?> upperBoundQueue = switch (numberUpperBound) {
        case (1) -> Policies.produce(Chordal.class, sizeForProduce);
        case (2) -> Policies.produce(Mammal.class, sizeForProduce);
        case (3) -> Policies.produce(Predatory.class, sizeForProduce);
        case (4) -> Policies.produce(Canine.class, sizeForProduce);
        case (5) -> Policies.produce(Dog.class, sizeForProduce);
        case (6) -> Policies.produce(Wolfish.class, sizeForProduce);
        case (7) -> Policies.produce(GermanShepherdDog.class, sizeForProduce);
        case (8) -> Policies.produce(EurasianWolf.class, sizeForProduce);
        case (9) -> Policies.produce(GrayWolf.class, sizeForProduce);
        default -> throw new IllegalStateException("Unexpected value: " + numberUpperBound);
      };

      System.out.println("\nUpper bound generic queue");
      System.out.println(" -----------");
      System.out.println("Parent: " + upperBoundQueue.get(0));
      upperBoundQueue.print();
      System.out.println(" -----------\n");
      System.out.println("forming lower bound queue:");

      int numberLowerBound = getNumberBound();
      int sizeForConsume = getSizeForConsume(numberUpperBound, numberLowerBound);

      Queue<?> lowerBoundQueue = switch (numberLowerBound) {
        case (1) -> Policies.consume(upperBoundQueue, Chordal.class, sizeForConsume);
        case (2) -> Policies.consume(upperBoundQueue, Mammal.class, sizeForConsume);
        case (3) -> Policies.consume(upperBoundQueue, Predatory.class, sizeForConsume);
        case (4) -> Policies.consume(upperBoundQueue, Canine.class, sizeForConsume);
        case (5) -> Policies.consume(upperBoundQueue, Dog.class, sizeForConsume);
        case (6) -> Policies.consume(upperBoundQueue, Wolfish.class, sizeForConsume);
        case (7) -> Policies.consume(upperBoundQueue, GermanShepherdDog.class, sizeForConsume);
        case (8) -> Policies.consume(upperBoundQueue, EurasianWolf.class, sizeForConsume);
        case (9) -> Policies.consume(upperBoundQueue, GrayWolf.class, sizeForConsume);
        default -> throw new IllegalStateException("Unexpected value: " + numberLowerBound);
      };

      System.out.println("\nLower bound generic queue");
      System.out.println(" -----------");
      if (!lowerBoundQueue.isEmpty()) {
        System.out.println("Kid: " + lowerBoundQueue.get(sizeForConsume - 1));
        lowerBoundQueue.print();
      } else {
        throw new IllegalArgumentException("Parents are not in the queue");
      }
      System.out.println(" -----------");
    } catch (IllegalArgumentException | IllegalStateException | InputMismatchException | SizeLimitExceededException e) {
      e.printStackTrace();
    }
  }

  public static int getNumberBound() {
    Scanner s = new Scanner(System.in);
    System.out.println("""
      1 - Chordal
      2 - Mammal
      3 - Predatory
      4 - Canine
      5 - Dog
      6 - Wolfish
      7 - German shepherd dog
      8 - eurasian wolf
      9 - gray wolf""");
    return s.nextInt();
  }

  public static int getSizeForProduce(int numberUpperBound) {
    if (numberUpperBound < firstNumberOfGender) {
      return numberOfClasses + 1 - numberUpperBound;
    }
    if (numberUpperBound < firstNumberOfSpecies) {
      return sizeForGender;
    }
    return sizeForSpecies;
  }

  public static int getSizeForConsume(int numberUpperBound, int numberLowerBound) {
    if (numberLowerBound < lastNumberOfGender) {
      return numberLowerBound - numberUpperBound + 1;

    }
    if (numberLowerBound == lastNumberOfGender) {
      return numberLowerBound - numberUpperBound;
    }
    return firstNumberOfSpecies - numberUpperBound;
  }
}
