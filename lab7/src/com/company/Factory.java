package com.company;

import java.util.concurrent.ThreadFactory;

public class Factory implements ThreadFactory {
  private int counter;
  private final String prefix;

  public Factory(String prefix) {
    this.prefix = prefix;
    this.counter = 1;
  }

  @Override
  public Thread newThread(Runnable r) {
    return new Thread(r, prefix + "-" + counter++);
  }
}
