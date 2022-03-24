package com.company;


public class Hero {
    private final String name;

    private Point position;


    private MethodsOfTransportationStrategy strategy;

    public Hero(String newName) {
        name = newName;
        position = new Point(0, 0);
    }

    public void setStrategy(MethodsOfTransportationStrategy strategy) {
        this.strategy = strategy;
    }

    public void move(Point p) {
        System.out.print(name + " from the starting point x = " + position.x + ", y = " + position.y);
        strategy.move(p);
        position.x = p.x;
        position.y = p.y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }
}

