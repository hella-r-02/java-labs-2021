package com.company;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Policies {
  public static double calculateAverageValue(List<Integer> list) throws IllegalArgumentException {
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List is empty");
    }
    return list.stream()
      .mapToInt(Integer::intValue)
      .average()
      .orElse(Double.NaN);
  }

  public static List<String> convertToUppercase(List<String> list) throws IllegalArgumentException {
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List is empty");
    }
    return list.stream()
      .map(String::toUpperCase)
      .map(line -> "_new_" + line)
      .collect(Collectors.toList());
  }

  public static List<Integer> getListOfSquares(List<Integer> list) throws IllegalArgumentException {
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List is empty");
    }
    return list.stream()
      .distinct()
      .map(item -> item * item)
      .collect(Collectors.toList());
  }

  public static List<String> getLinesWithSameFirstLetter(Collection<String> collection, char letter) throws IllegalArgumentException {
    if (collection.isEmpty()) {
      throw new IllegalArgumentException("Collection is empty");
    }
    return collection.stream()
      .filter(line -> Character.isLetter(letter) && (line.charAt(0) == letter || line.charAt(0) == Character.toUpperCase(letter)))
      .sorted()
      .collect(Collectors.toList());
  }

  public static <T> T getLastElement(Collection<T> collection) throws IllegalArgumentException {
    return collection.stream()
      .skip(collection.size() - 1)
      .findFirst()
      .orElseThrow(IllegalArgumentException::new);
  }

  public static int getSumOfEvenNumbers(int[] array) throws IllegalArgumentException {
    return Arrays.stream(array)
      .filter(item -> item % 2 == 0)
      .sum();
  }

  public static Map<Integer, String> convertListToMap(List<String> list) throws IllegalArgumentException {
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List is empty");
    }
    return list.stream()
      .collect(Collectors.toMap(String::length, Function.identity()));
  }
}
