package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.company.Policies.*;

public class TestPolicies {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static List<Integer> listInt = Stream.of(1, 2, 3, 4, 5, 1, 1, 2, 3, 8).collect(Collectors.toList());
  private static List<String> listString = Stream.of("aks", "kaks", "lskdk", "slldksla", "aldkejcskw").collect(Collectors.toList());
  private static List<String> listStringEmpty = new ArrayList<>();
  private static List<Integer> listIntEmpty = new ArrayList<>();
  private static final double expectedTestCalculateAverageValue = 3;
  private static final String expectedTestConvertToUppercase = "[_new_AKS, _new_KAKS, _new_LSKDK, _new_SLLDKSLA, _new_ALDKEJCSKW]\n";
  private static final String expectedTestGetListOfSquares = "[1, 4, 9, 16, 25, 64]\n";
  private static final String expectedTestGetLinesWithSameFirstLetter = "[aks, aldkejcskw]\n";
  private static final int expectedTestGetLastElement = 5;
  private static final int expectedTestGetSumOfEvenNumbers1 = 8;
  private static final int expectedTestGetSumOfEvenNumbers2 = 0;
  private static final String expectedTestConvertListToMap = "{3=aks, 4=kaks, 5=lskdk, 8=slldksla, 10=aldkejcskw}\n";

  @Test
  void testCalculateAverageValue() {
    Assertions.assertEquals(expectedTestCalculateAverageValue, calculateAverageValue(listInt));
    Assertions.assertThrows(RuntimeException.class, () -> calculateAverageValue(listIntEmpty));
  }

  @Test
  void testConvertToUppercase() {
    System.setOut(new PrintStream(outContent));
    System.out.println(convertToUppercase(listString));
    Assertions.assertEquals(expectedTestConvertToUppercase, outContent.toString());
    Assertions.assertThrows(RuntimeException.class, () -> convertToUppercase(listStringEmpty));
  }

  @Test
  void testGetListOfSquares() {
    System.setOut(new PrintStream(outContent));
    System.out.println(getListOfSquares(listInt));
    Assertions.assertEquals(expectedTestGetListOfSquares, outContent.toString());
    Assertions.assertThrows(RuntimeException.class, () -> getListOfSquares(listIntEmpty));
  }

  @Test
  void testGetLinesWithSameFirstLetter() {
    System.setOut(new PrintStream(outContent));
    System.out.println(getLinesWithSameFirstLetter(listString, 'a'));
    Assertions.assertEquals(expectedTestGetLinesWithSameFirstLetter, outContent.toString());
    Assertions.assertThrows(RuntimeException.class, () -> getLinesWithSameFirstLetter(listStringEmpty, 'a'));
  }

  @Test
  void testGetLastElement() {
    Collection<Integer> collection = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
    Assertions.assertEquals(expectedTestGetLastElement, getLastElement(collection));
    Collection<Integer> collectionEmpty = new ArrayList<>();
    Assertions.assertThrows(RuntimeException.class, () -> getLastElement(collectionEmpty));
  }

  @Test
  void testGetSumOfEvenNumbers() {
    int[] array = new int[]{1, 2, 3, 4, 1, 2};
    Assertions.assertEquals(expectedTestGetSumOfEvenNumbers1, getSumOfEvenNumbers(array));
    int[] array2 = new int[]{1, 3, 5};
    Assertions.assertEquals(expectedTestGetSumOfEvenNumbers2, getSumOfEvenNumbers(array2));
  }

  @Test
  void testConvertListToMap() {
    System.setOut(new PrintStream(outContent));
    System.out.println(convertListToMap(listString));
    Assertions.assertEquals(expectedTestConvertListToMap, outContent.toString());
    List<String> listString2 = Stream.of("aks", "kak").collect(Collectors.toList());
    Assertions.assertThrows(RuntimeException.class, () -> convertListToMap(listStringEmpty));
    Assertions.assertThrows(RuntimeException.class, () -> convertListToMap(listString2));
  }
}
