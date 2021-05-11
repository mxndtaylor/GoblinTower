package com.company;

import java.util.Arrays;

public class Hero extends Creature {

    private static final int[] HEALTH_RANGE = new int[2];
    private static final int[] ATTACK_RANGE = new int[2];
    private static final int[] DEFENSE_RANGE = new int[2];
    static {
        HEALTH_RANGE[0] = 20;
        HEALTH_RANGE[1] = 30;

        ATTACK_RANGE[0] = 1;
        ATTACK_RANGE[1] = 3;

        DEFENSE_RANGE[0] = 1;
        DEFENSE_RANGE[1] = 5;
    }

    private int gold;
    private final int[] potions;
    private int level;
    private int goblinsSlain;

    /**
     * Instance a Hero with semi-random stats
     *
     */
    public Hero(int leftoverGold) {
        super(HEALTH_RANGE, ATTACK_RANGE, DEFENSE_RANGE);
        gold = leftoverGold;
        level = 0;
        goblinsSlain = 0;
        potions = new int[5];
        Arrays.fill(potions, 2);
    }

    /**
     * the hero drinks a potion healing themselves up to 2pts
     *
     * @return boolean representing success of action
     */
    public boolean drinkPotion() {
        int i = 0;
        while (potions[i] == 0) {
            i++;
        }
        if (i < potions.length) {
            healthPoints = Math.max(healthPoints + potions[i], MAX_HEALTH);
            potions[i] = 0;
            return true;
        }
        return false;
    }

    /**
     * increment goblinsSlain
     */
    public void slayedAGoblin() {
        goblinsSlain += 1;
    }

    /**
     * Hero level up
     */
    public void levelUp() {
        level += 1;
    }

    /**
     * Hero gets a potion the first empty slot
     */
    private void getPotion() {
        for (int i = 0; i < potions.length; i++) {
            if (potions[i] == 0) {
                potions[i] = 2;
                break;
            }
        }
    }

    /**
     * Hero buys some number of potions
     */
    public void buyPotions(int numberOfPotions) {
        int currentPotionNumber = 0;
        for (int potion : potions) {
            currentPotionNumber += potion / 2;
        }
        if (gold >= numberOfPotions * 4 && numberOfPotions <= currentPotionNumber) {
            gold -= numberOfPotions;
            for (int i = 0; i < numberOfPotions; i++) {
                getPotion();
            }
        } //TODO: figure out what to do when hero cannot buy as many potions
    }

    /**
     * fetches the hero's level
     * @return int representing hero's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * fetches the hero's goblinsSlain
     * @return int representing hero's goblinsSlain
     */
    public int getGoblinsSlain() {
        return goblinsSlain;
    }
}
