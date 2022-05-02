package com.mygdx.game.Managers;

//import com.mygdx.game.Entitys.Player;
import jdk.internal.org.jline.utils.DiffHelper;
//NEW
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
