package com.company;

import static java.lang.Math.*;

/**
 * A creature in goblin tower
 */
public class Creature {

    public final int MAX_HEALTH;

    private final int attack;
    private final int defense;

    public int healthPoints;

    /**
     * Instance a creature with random stats from the given ranges
     *
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
     * @param other the creature targeted by the attack
     * @return int representing actual damage dealt
     */
    public int attack(Creature other) {
        return other.takeDamage(attack);
    }

    /**
     * Creature takes some damage
     *
     * @param incomingDamage the incoming damage to be taken
     * @return int representing received damage
     */
    public int takeDamage(int incomingDamage) {
        int damage = incomingDamage - defense;
        healthPoints = Math.max(healthPoints - damage, 0);
        return damage;
    }

    /**
     * Tests health to see if creature is dead
     *
     * @return boolean representing the alive state of creature
     */
    public boolean isDead() {
        return healthPoints <= 0;
    }

    /**
     * String representation of creature
     *
     * @return String detailing stats of the creature
     */
    public String toString() {
        return String.format(
                "HP: %s/%s, ATT: %s, DEF: %s",
                healthPoints,
                MAX_HEALTH,
                attack,
                defense
        );
    }
}