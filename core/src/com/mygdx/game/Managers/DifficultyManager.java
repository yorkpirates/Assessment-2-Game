package com.mygdx.game.Managers;

import jdk.internal.org.jline.utils.DiffHelper;

public class DifficultyManager {
    private static String level = "n";

    public DifficultyManager() {

    }

    public void SelectNormal() {
        level = "n";
    }

    public void SelectHard() {
        level = "h";
    }

    public void SelectEasy() {
        level = "e";
    }

    public static String getDifficulty() {
        return level;
    }
}
/*
Easy - 2x health, 2x bullets,
Medium - normal health, normal bullets,
Hard - half health, half bullets,
 */