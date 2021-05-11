package com.company;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintStream output = System.out;
        output.println("Welcome to the Goblin Tower!");
        output.println();
        boolean gameInPlay = true;
        int gold = 0;
        while (gameInPlay) {
            Hero hero = new Hero(gold);
            int stepTaken = 0;
            while (!hero.isDead()) {
                if (stepTaken % 10 == 0) {
                    // TODO: add potion shop mechanic
                    hero.levelUp();
                } else if (goblinFoundAtStep()){
                    Goblin goblin = new Goblin();
                    Fight clash = new Fight(hero, goblin);
                    clash.beginFight(input, output);
                }
                stepTaken++;
            }
            // TODO: ask to continue play
        }
    }

    public static boolean goblinFoundAtStep() {
        return Math.random() < 0.5;
    }
}
