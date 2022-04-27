package com.mygdx.game.Managers;

//import com.mygdx.game.Entitys.Player;
import jdk.internal.org.jline.utils.DiffHelper;

public class DifficultyManager {
    private static String level = "n";

    public DifficultyManager() {

    }

    public static void SelectNormal() {
        level = "n";
    }

    public static void SelectHard() {
        level = "h";
    }

    public static void SelectEasy() {
        level = "e";
    }

    public static String getDifficulty() {
        return level;
    }
}
/*
Easy - 2x health, 2x bullets,1 quest to win
Medium - normal health, normal bullets, 3 quests to win
Hard - half health, half bullets, 5 quests to win
 */
