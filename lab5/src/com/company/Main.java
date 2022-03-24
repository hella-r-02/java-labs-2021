package com.company;

import java.io.IOException;
import java.util.*;

import static com.company.Policies.*;

public class Main {
  public static final int defaultSize = 5;

  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      List<Integer> listInt = new ArrayList<>();
      List<String> listString = new ArrayList<>();
      Collection<String> collectionString = new ArrayList<>();
      System.out.println("Enter the size of array");
      int size = s.nextInt();
      if (size <= 0) {
        System.out.println("A non-positive number is entered. The default value is set (5)");
      }
      int[] array = new int[size];
      System.out.println("Enter the " + size + " numbers for array");
      for (int i = 0; i < size; i++) {
        array[i] = s.nextInt();
      }
      System.out.println("Enter 5 numbers for list");

      for (int i = 0; i < defaultSize; i++)
        listInt.add(s.nextInt());

      System.out.println("Enter 5 lines for list");
      for (int i = 0; i < defaultSize; i++) {
        listString.add(s.next());
      }
      System.out.println("Enter 5 lines for collection");
      for (int i = 0; i < defaultSize; i++) {
        collectionString.add(s.next());
      }
      System.out.println("Enter char for task");
      char symbol = (char) System.in.read();
      System.out.println("Calculate average value for list (integer): " + calculateAverageValue(listInt));
      System.out.println("convert strings to uppercase: " + convertToUppercase(listString));
      System.out.println("Get list squares: " + getListOfSquares(listInt));
      System.out.println("Get lines with same first letter: " + getLinesWithSameFirstLetter(collectionString, symbol));
      System.out.println("Get Last Element: " + getLastElement(collectionString));
      System.out.println("Gem sum of even numbers: " + getSumOfEvenNumbers(array));
      System.out.println("Convert list to map: " + convertListToMap(listString));

    } catch (RuntimeException | IOException error) {
      error.printStackTrace();
    }
  }
}
