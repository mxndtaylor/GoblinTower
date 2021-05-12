package com.company;

import java.io.PrintStream;
import java.util.*;

/**
 * Encapsulates fight-related behaviors for goblin tower
 */
public class Fight {

    private static final Map<String, String> promptsMap = new HashMap<>();
    static {
        promptsMap.put("begin", "A goblin approaches! It doesn't look very friendly.");

        promptsMap.put("mob-turn", "The goblin readies its weapon.");
        promptsMap.put("mob-attack", "The goblin attacks! It deals %s damage.");
        promptsMap.put("mob-health", "The goblin has %s health left.");
        promptsMap.put("mob-death", "You killed the goblin! It dropped 2 gold.");
        promptsMap.put("mob-defend", "The goblin assumes a defensive position.");

        promptsMap.put("pc-turn", "It's your turn! Attack (1|A), Defend (2|D), or Potion (3|P)?");
        promptsMap.put("pc-attack", "You attack! Dealing %s damage.");
        promptsMap.put("pc-health", "You have %s health left.");
        promptsMap.put("pc-death", "You died.");
        promptsMap.put("pc-potion", "You drink a potion, healing %s health points.");
        promptsMap.put("pc-potions-left", "You have %s potions left.");
        promptsMap.put("pc-gold", "You now have %s gold!");
        promptsMap.put("pc-defend", "You raise your shield.");
        promptsMap.put("pc-no-potions", "You are out of potions.");
    }

    private final Hero player;
    private final Goblin mob;
    private boolean playerTurn;

    /**
     * constructs an object to handle a fight between the hero and a single goblin
     *
     * @param player the hero object
     * @param mob the goblin object
     */
    public Fight(Hero player, Goblin mob) {
        this.player = player;
        this.mob = mob;
        playerTurn = Math.random() < 0.5;
    }

    /**
     * Prints a prompt using the given look-up key
     *
     * @param output the PrintStream that the user reads
     * @param promptKey the look-up key for the desired prompt
     */
    private void printPrompt(PrintStream output, String promptKey) {
        output.println(promptsMap.get(promptKey));
    }

    /**
     * outputs a formatted line to the output
     *
     * @param output the PrintStream that the user reads
     * @param promptKey the look-up key for the desired prompt
     * @param arg the object to be formatted into the format string
     */
    private void printPromptWithArgument(PrintStream output, String promptKey, Object arg) {
        output.printf(promptsMap.get(promptKey) + "%n", arg);
    }

    /**
     * check if the fight is finished
     *
     * @return boolean true if none of the creatures involved are dead
     */
    public boolean fightIsNotOver() {
        return !player.isDead() && !mob.isDead();
    }

    /**
     * Executes the player's action
     * @param input the Scanner that captures user input
     * @param output the PrintStream that the user reads
     */
    public void playerAction(Scanner input, PrintStream output) {
        if (player.getDefending()) {
            player.toggleDefend();
        }
        // TODO: catch invalid input
        printPrompt(output, "pc-turn");
        boolean choiceNotMade = true;
        while (choiceNotMade) {
            String userChoice = input.next().substring(0, 1).toLowerCase();
            switch (userChoice) {
                case "1":
                case "a":
                    int damageDealt = player.attack(mob);
                    printPromptWithArgument(output, "pc-attack", damageDealt);
                    choiceNotMade = false;
                    break;
                case "2":
                case "d":
                    player.toggleDefend();
                    printPrompt(output, "pc-defend");
                    choiceNotMade = false;
                    break;
                case "3":
                case "p":
                    if (player.currentPotionNumber() == 0) {
                        printPrompt(output, "pc-no-potions");
                    } else {
                        int healthRestored = player.drinkPotion();
                        printPromptWithArgument(output, "pc-potion", healthRestored);
                        choiceNotMade = false;
                    }
                    break;
            }
        }
        playerTurn = false;
    }

    /**
     * Executes the goblin's action
     * @param output the PrintStream that the user reads
     */
    public void goblinAction(PrintStream output) {
        if (mob.getDefending()) {
            mob.toggleDefend();
        }
        printPrompt(output, "mob-turn");
        if (Math.random() > 0.5) {
            int damageDealt = mob.attack(player);
            printPromptWithArgument(output, "mob-attack", damageDealt);
        } else {
            mob.toggleDefend();
            printPrompt(output, "mob-defend");
        }
        playerTurn = true;
    }

    /**
     * prints out the status of the fight
     * @param output the PrintStream that the user reads
     */
    private void fightStatus(PrintStream output) {
        if (fightIsNotOver() && playerTurn) {
            printPromptWithArgument(output, "pc-health", player.healthPoints);
        } else if (fightIsNotOver()) {
            printPromptWithArgument(output, "pc-potions-left", player.currentPotionNumber());
            printPromptWithArgument(output, "mob-health", mob.healthPoints);
        } else if (player.isDead()) {
            printPrompt(output, "pc-death");
        } else if (mob.isDead()) {
            printPrompt(output, "mob-death");
            player.slayedAGoblin();
        }
    }

    /**
     * Runs the fight
     *
     * @param input the Scanner that captures user input
     * @param output the PrintStream that the user reads
     */
    public void beginFight(Scanner input, PrintStream output) {
        printPrompt(output, "begin");
        while (fightIsNotOver()) {
            if (playerTurn) {
                playerAction(input, output);
            } else {
                goblinAction(output);
            }
            fightStatus(output);
        }
    }
}
