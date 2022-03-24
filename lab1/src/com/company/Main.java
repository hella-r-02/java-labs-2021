package com.company;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero("Winner");
        hero.setStrategy(new WalkingStrategy());
        Point p = new Point(2, 1);
        hero.move(p);
        Point p2 = new Point(3, 4);
        hero.setStrategy(new RidingHorseStrategy());
        hero.move(p2);
        Point p3 = new Point(-1, 0);
        hero.setStrategy(new SwimmingStrategy());
        hero.move(p3);
        System.out.println("The final coordinates of " + hero.getName() + ": x = " + hero.getX() + ", y = " + hero.getY());

    }
}
