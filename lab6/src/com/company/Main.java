package com.company;

public class Main {
  public static void main(String[] args) {
    try {
      Count count = new Count();
      Supervisor supervisor = new Supervisor(count);
      supervisor.start();
      supervisor.startProgram();
    }
    catch (IllegalArgumentException error){
      error.printStackTrace();
    }
  }
}
