package com.company;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintStream output = System.out;
        output.println("Welcome to the Goblin Tower!");
        output.println();
        boolean gameInPlay = true;
        int gold = 0;
        Hero hero = null;
        while (gameInPlay) {
            hero = new Hero(gold);
            output.println(hero);
            int stepTaken = 1;
            while (!hero.isDead()) {
                if (stepTaken % 10 == 0) {
                    hero.levelUp();
                    output.printf("Level up! You are now level %s. %n", hero.getLevel());
                    output.println(hero);
                    output.println("You may visit the potion shop! Buy potions for 4 GP each.");
                    output.println("How many would you like to buy?");
                    // TODO: verify input
                    int potionNumber = Integer.parseInt(input.next());
                    int potionsBought = hero.buyPotions(potionNumber);
                    output.printf("You bought %s potions. %n", potionsBought);
                } else if (goblinFoundAtStep()){
                    Goblin goblin = new Goblin();
                    // TODO: consider removing
                    output.println(goblin);
                    Fight clash = new Fight(hero, goblin);
                    clash.beginFight(input, output);
                }
                stepTaken++;
            }
            output.println("Game Over. Would you like to play again, Y/N?");
            String decision = input.next().substring(0, 1).toLowerCase();
            gameInPlay = decision.equals("y");
            gold = hero.getGold();
        }
        output.printf(
                "You give up. You reached level %s, after killing %s goblins %n",
                hero.getLevel(),
                hero.getGoblinsSlain()
        );
    }

    public static boolean goblinFoundAtStep() {
        return Math.random() < 0.5;
    }
}
