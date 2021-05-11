package com.company;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner ui = new Scanner(System.in);
        PrintStream output = System.out;
        output.println("Welcome to the Goblin Tower!");
        output.println();
        boolean gameInPlay = true;
        int gold = 0;
        while (gameInPlay) {
            Hero hero = new Hero(gold);
            int stepTaken = 0;
            while (!hero.isDead()) {
                if (goblinFoundAtStep()){
                    Goblin goblin = new Goblin();
                    fight(hero, goblin);
                }
                stepTaken++;
            }
        }
    }

    public static void fight(Hero hero, Goblin goblin) {
        while (!hero.isDead() && !goblin.isDead()) {
            goblin.attack(hero);
            if (hero.isDead() && hero.drinkPotion()) {

            }
            hero.attack(goblin);
        }
    }

    public static boolean goblinFoundAtStep() {
        return Math.random() < 0.5;
    }
}
