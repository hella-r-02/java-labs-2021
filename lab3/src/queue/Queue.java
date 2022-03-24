package queue;

import javax.naming.SizeLimitExceededException;

public class Queue<T> {

  private static class Node<E> {
    private final E value;
    private Node<E> next;

    public Node(E value) {
      this.value = value;
      this.next = null;
    }
  }

  private Node<T> head;
  private final int size;
  private int realSize;

  public Queue(int size) {
    if (size < 1) {
      throw new IllegalArgumentException("Size less than 1");
    }
    this.size = size;
    this.realSize = 0;
    this.head = null;
  }

  public void add(Object node) throws SizeLimitExceededException {
    if (realSize == size) {
      throw new SizeLimitExceededException("Real size equal to size");
    }
    if (realSize == 0) {
      this.head = new Node(node);
    } else {
      Node<T> tempNode = head;
      while (tempNode.next != null) {
        tempNode = tempNode.next;
      }
      tempNode.next = new Node(node);
    }
    realSize++;
  }

  public Object get(int index) {
    if (index >= realSize) {
      throw new IllegalArgumentException("Index more than real size");
    }
    int tempIndex = 0;
    Node<T> tempNode = head;
    while (tempIndex != index) {
      tempNode = tempNode.next;
      tempIndex++;
    }
    return tempNode.value;
  }

  public int getSize() {
    return size;
  }

  public void print() {
    for (int i = 0; i < size; i++) {
      System.out.println(get(i).toString());
    }
  }

  public boolean isEmpty() {
    return realSize == 0;
  }
}
