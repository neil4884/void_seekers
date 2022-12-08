package com.game.void_seekers.character.base;

import com.game.void_seekers.interfaces.Attack;
import com.game.void_seekers.tools.Coordinates;

import java.util.ArrayList;

public class PlayableCharacter extends GameCharacter implements Attack {
    private ArrayList<PlayerEffects> stats;

    public PlayableCharacter(String name, int health, Coordinates coordinate) {
        super(name, health, coordinate);
        setStats(new ArrayList<>());
    }

    public PlayableCharacter(String name, int health, double x, double y) {
        super(name, health, x, y);
        setStats(new ArrayList<>());
    }

    public PlayableCharacter() {
        super();
        super.setName("Untitled Player");
        setStats(new ArrayList<>());
    }

    @Override
    public void attack(GameCharacter character) {
        character.reduceHealth(getBaseDamage());
    }

    public ArrayList<PlayerEffects> getStats() {
        return stats;
    }

    public void setStats(ArrayList<PlayerEffects> stats) {
        this.stats = stats;
    }
}
