package com.company;

public class WalkingStrategy implements MethodsOfTransportationStrategy {
    public void move(Point position) {

        System.out.println(" walked to the point x = " + position.x + ", y = " + position.y);
    }
}