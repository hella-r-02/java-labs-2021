package tests;

import animal.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import policies.Policies;
import queue.Queue;

import javax.naming.SizeLimitExceededException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class PoliciesTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final String expectedProduce = "chordal - type\nmammal - class\npredatory - squad\ncanine - family\ndog - gender\nwolfish - gender\ngerman shepherd dog - species\neurasian wolf - species\ngray wolf - species\n";
  private static final String expectedConsume = "chordal - type\nmammal - class\npredatory - squad\ncanine - family\nwolfish - gender\n";

  @Test
  void produce() throws SizeLimitExceededException {
    Queue<? extends Chordal> queue = new Queue(9);
    queue.add(new Chordal());
    queue.add(new Mammal());
    queue.add(new Predatory());
    queue.add(new Canine());
    queue.add(new Dog());
    queue.add(new Wolfish());
    queue.add(new GermanShepherdDog());
    queue.add(new EurasianWolf());
    queue.add(new GrayWolf());
    System.setOut(new PrintStream(outContent));
    var queue2 = Policies.produce(Chordal.class, 9);
    System.setOut(new PrintStream(outContent));
    queue2.print();
    Assertions.assertEquals(expectedProduce, outContent.toString());
  }

  @Test
  void correctConsume() throws SizeLimitExceededException {
    Queue<? extends Chordal> queue = new Queue(9);
    queue.add(new Chordal());
    queue.add(new Mammal());
    queue.add(new Predatory());
    queue.add(new Canine());
    queue.add(new Dog());
    queue.add(new Wolfish());
    queue.add(new GermanShepherdDog());
    queue.add(new EurasianWolf());
    queue.add(new GrayWolf());
    var queue2 = Policies.consume(queue, Wolfish.class, 5);
    System.setOut(new PrintStream(outContent));
    queue2.print();
    Assertions.assertEquals(expectedConsume, outContent.toString());
  }

  @Test
  void incorrectConsume() throws SizeLimitExceededException {
    Queue<? extends Dog> queue = new Queue(9);
    queue.add(new Dog());
    queue.add(new GermanShepherdDog());
    Assertions.assertThrows(IllegalArgumentException.class, () -> Policies.consume(queue, GrayWolf.class, -2));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Policies.consume(queue, Mammal.class, -2));
  }
}