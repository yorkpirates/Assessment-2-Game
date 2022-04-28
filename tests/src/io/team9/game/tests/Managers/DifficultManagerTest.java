package io.team9.game.tests.Managers;

import com.mygdx.game.Managers.DifficultyManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class DifficultManagerTest {

    @Test
    public void testNormal() {
        DifficultyManager manager = new DifficultyManager();
        manager.SelectNormal();
        String difficulty ="n";
        assertEquals("true", manager.getDifficulty(), difficulty);
    }

    @Test
    public void testEasy() {
        DifficultyManager manager = new DifficultyManager();
        manager.SelectEasy();
        String difficulty ="e";
        assertEquals("true", manager.getDifficulty(), difficulty);
    }

    @Test
    public void testHard() {
        DifficultyManager manager = new DifficultyManager();
        manager.SelectHard();
        String difficulty ="h";
        assertEquals("true", manager.getDifficulty(), difficulty);
    }

}