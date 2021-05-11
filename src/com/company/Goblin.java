package com.company;

/**
 * A Goblin for goblin tower
 */
public class Goblin extends Creature {

    // defines the range of possibilities for the primary stats
    private static final int[] HEALTH_RANGE = {5, 10};
    private static final int[] ATTACK_RANGE = {2, 3};
    private static final int[] DEFENSE_RANGE = {1, 2};

    /**
     * Instance a Goblin with semi-random stats
     */
    public Goblin() {
        super(HEALTH_RANGE, ATTACK_RANGE, DEFENSE_RANGE);
    }
}
