package com.company;

/**
 * A Goblin for goblin tower
 */
public class Goblin extends Creature {

    private static final int[] HEALTH_RANGE = new int[2];
    private static final int[] ATTACK_RANGE = new int[2];
    private static final int[] DEFENSE_RANGE = new int[2];
    static {
        HEALTH_RANGE[0] = 5;
        HEALTH_RANGE[1] = 10;

        ATTACK_RANGE[0] = 2;
        ATTACK_RANGE[1] = 3;

        DEFENSE_RANGE[0] = 1;
        DEFENSE_RANGE[1] = 2;
    }

    /**
     * Instance a Goblin with semirandom stats
     */
    public Goblin() {
        super(HEALTH_RANGE, ATTACK_RANGE, DEFENSE_RANGE);
    }
}
