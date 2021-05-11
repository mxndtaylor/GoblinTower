package com.company;

import java.util.Arrays;

public class Hero extends Creature {

    // defines the range of possibilities for the primary stats
    private static final int[] HEALTH_RANGE = {20, 30};
    private static final int[] ATTACK_RANGE = {1, 3};
    private static final int[] DEFENSE_RANGE = {1, 5};

    private int gold;
    private final int[] potions;
    private int level;
    private int goblinsSlain;

    /**
     * Instance a Hero with semi-random stats
     *
     * @param leftoverGold allows the hero to retain gold from previous adventures
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
     * @return int representing the amount healed by the potion
     */
    public int drinkPotion() {
        int index = 0;
        while (potions[index] == 0) {
            index++;
        }
        if (index < potions.length) {
            int healthBefore = healthPoints;
            healthPoints = Math.max(healthPoints + potions[index], MAX_HEALTH);
            potions[index] = 0;
            return healthPoints - healthBefore;
        }
        return 0;
    }

    /**
     * increment goblinsSlain
     */
    public void slayedAGoblin() {
        goblinsSlain += 1;
        gold += 2;
    }

    /**
     * Hero level up
     */
    public void levelUp() {
        // TODO: increase hero potency every level?
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

    public String toString() {
        String result = super.toString();
        result += String.format("GP: %s, POT: %s", gold, Arrays.toString(potions));
        return result;
    }
}
