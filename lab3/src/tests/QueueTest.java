package tests;

import animal.Chordal;
import animal.GrayWolf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import queue.Queue;

import javax.naming.SizeLimitExceededException;


class QueueTest {
  static final int size = 5;

  @Test
  void isEmpty() {
    Queue<? extends Chordal> queue = new Queue<>(size);
    Assertions.assertTrue(queue.isEmpty());
  }

  @Test
  void getSize() {
    Queue<? extends Chordal> queue = new Queue<>(size);
    Assertions.assertTrue(queue.getSize() == size);
  }

  @Test
  void addAndGet() throws SizeLimitExceededException {
    Queue<? extends Chordal> queue = new Queue<>(size);
    queue.add(new GrayWolf());
    Assertions.assertEquals(queue.get(0).getClass(), GrayWolf.class);
    Assertions.assertThrows(IllegalArgumentException.class, () -> queue.get(size + 1));
  }
}