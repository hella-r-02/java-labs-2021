package com.company;

import java.util.Random;

enum Condition {
  UNKNOWN,
  STOPPING,
  RUNNING,
  FATAL_ERROR
}

class Count implements Runnable {
  static final Object lock = new Object();
  private Condition condition;
  private int value;
  private boolean isRestart;
  private boolean isStop;
  private static final int defaultValue = 1;
  private static final int valueZero = 0;

  Count() {
    daemon.setDaemon(true);
    this.value = valueZero;
    this.condition = null;
    isRestart = false;
    isStop = false;
  }

  private Thread daemon = new Thread(() -> {
    Random random = new Random();
    while (!Thread.currentThread().isInterrupted()) {
      synchronized (lock) {
        while (condition != null) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        int number = 1 + random.nextInt(4);
        switch (number) {
          case 1 -> condition = Condition.UNKNOWN;
          case 2 -> condition = Condition.STOPPING;
          case 3 -> condition = Condition.RUNNING;
          case 4 -> condition = Condition.FATAL_ERROR;
          default -> throw new IllegalArgumentException("Invalid condition");
        }
        lock.notify();
        Utils.pause(2000);
      }
    }
  });

  @Override
  public void run() {
    daemon.start();
    while (!isStop) {
      if (isRestart) {
        value = defaultValue;
        isRestart = false;
      } else {
        value++;
      }
      System.out.println("Count = " + value);
      Utils.pause(1000);
    }
  }

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  public boolean getStop() {
    return isStop;
  }

  public void setRestartTrue() {
    isRestart = true;
  }

  public void setStopTrue() {
    isStop = true;
  }
}
