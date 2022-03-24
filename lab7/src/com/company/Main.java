package com.company;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Main {
  private static final Lock lockProducer = new ReentrantLock();
  private static final Lock lockConsumer = new ReentrantLock();
  private static final AtomicInteger numberOfString = new AtomicInteger();
  private static final int millis = 1500;
  private static final int randomMillis = 3000;
  private static final int millisForConsumer = 100;

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("The quantify of arguments is greater than 1");
    } else {
      int quantity = Integer.parseInt(args[0]);
      BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
      ExecutorService executorProducer = Executors.newFixedThreadPool(quantity, new Factory("Producer"));
      ExecutorService executorConsumer = Executors.newFixedThreadPool(quantity, new Factory("Consumer"));
      for (int i = 0; i < quantity; i++) {
        executorProducer.execute(() ->
        {
          while (true)
            try {
              lockProducer.lock();
              numberOfString.compareAndExchange(5,-1);
              String str = getString(numberOfString.incrementAndGet());
              blockingQueue.put(str);
              System.out.println(Thread.currentThread().getName() + " add " + str);
              lockProducer.unlock();
              Thread.sleep((long) (millis + Math.random() * randomMillis));
            } catch (InterruptedException error) {
              error.printStackTrace();
            }
        });
        executorConsumer.execute(() -> {
            while (true) {
              lockConsumer.lock();
              try {
                Thread.sleep(millisForConsumer);
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take().toUpperCase());
                lockConsumer.unlock();
                Thread.sleep((long) (millis + Math.random() * randomMillis));
              } catch (InterruptedException error) {
                error.printStackTrace();
              }
            }
          }
        );
      }
      executorProducer.shutdown();
      executorConsumer.shutdown();
    }
  }

  private static String getString(int count) {
    String str = "";
    switch (count) {
      case 0:
        str = "Goodbye :)";
        break;
      case 1:
        str = "Hello";
        break;
      case 2:
        str = "friend";
        break;
      case 3:
        str = "How";
        break;
      case 4:
        str = "are";
        break;
      case 5:
        str = "you";
        break;
      default:
        break;
    }
    return str;
  }
}
