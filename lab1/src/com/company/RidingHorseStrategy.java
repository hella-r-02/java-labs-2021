package com.company;

public class RidingHorseStrategy implements MethodsOfTransportationStrategy {
    public void move(Point position) {
        System.out.println(" rode a horse to the point x = " + position.x + ", y = " + position.y);
    }
}
