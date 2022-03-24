package com.company;

public class Supervisor extends Thread {
  private final Count count;

  public Supervisor(Count count) {
    this.count = count;
  }

  @Override
  public void run() {
    while (!count.getStop()) {
      synchronized (Count.lock) {
        while (count.getCondition() == null) {
          try {
            Count.lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        switch (count.getCondition()) {
          case FATAL_ERROR:
            stopProgram();
            System.out.println("STOP");
            Utils.pause(1000);
            break;
          case STOPPING, UNKNOWN:
            System.out.println("RESTART (Condition = " + count.getCondition() + ")");
            count.setRestartTrue();
            count.setCondition(null);
            Count.lock.notify();
            Utils.pause(1000);
            break;
          case RUNNING:
            System.out.println("RUNNING");
            count.setCondition(null);
            Count.lock.notify();
            Utils.pause(1000);
            break;
          default:
            break;
        }
      }
    }
  }

  public void startProgram() {
    System.out.println("START");
    new Thread(count).start();
  }

  public void stopProgram() {
    count.setStopTrue();
  }
}
