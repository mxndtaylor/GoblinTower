package com.company;

import static java.lang.Math.*;

/**
 * A creature in goblin tower
 */
public class Creature {

    public final int MAX_HEALTH;

    private int attack;
    private int defense;

    public int healthPoints;
    /**
     * Instance a creature with semirandom stats
     * @param healthRange the range of each creature's health
     * @param attackRange the range of each creature's attack
     * @param defenseRange the range of each creature's defense
     */
    public Creature(int[] healthRange, int[] attackRange, int[] defenseRange) {
        this.MAX_HEALTH = (int) (random() * (healthRange[1] - healthRange[0]) + healthRange[0]);
        this.healthPoints = MAX_HEALTH;
        this.attack = (int) (random() * (attackRange[1] - attackRange[0]) + attackRange[0]);
        this.defense = (int) (random() * (defenseRange[1] - defenseRange[0]) + defenseRange[0]);
    }

    /**
     * Attack another creature
     *
     * @param other the creature targetted by the attack
     */
    public void attack(Creature other) {
        other.takeDamage(attack);
    }

    /**
     * Creature takes some damage
     *
     * @param incomingDamage the incoming damage to be taken
     */
    public void takeDamage(int incomingDamage) {
        healthPoints = Math.max(healthPoints - (incomingDamage - defense), 0);
    }

    /**
     * Tests health to see if creature is dead
     *
     * @return boolean representing the alive state of creature
     */
    public boolean isDead() {
        return healthPoints <= 0;
    }
}
