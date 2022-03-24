package com.company;

public class SwimmingStrategy implements MethodsOfTransportationStrategy {
    public void move(Point position) {
        System.out.println(" swam to the point x = " + position.x + ", y = " + position.y);
    }
}
