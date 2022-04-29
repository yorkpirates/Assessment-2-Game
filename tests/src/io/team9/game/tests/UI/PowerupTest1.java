package io.team9.game.tests.UI;
import io.team9.game.tests.GdxTestRunner;
import com.mygdx.game.UI.GameScreen;
import com.mygdx.game.UI.PowerupScreen;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.PirateGame;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.QuestManager;
import com.mygdx.game.Managers.ResourceManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


@RunWith(GdxTestRunner.class)

public class PowerupTest1 {
    @Test
    public void CleanUpTest(){
        GameManager.Initialize();
        EntityManager.Initialize();
        QuestManager.Initialize();
        PhysicsManager.Initialize();
        ResourceManager.Initialize();
        int id_map = ResourceManager.addTileMap("Map.tmx");
        PirateGame pirateGaming = new PirateGame();
        GameScreen scr = new GameScreen(pirateGaming, id_map);
        scr.render(80);
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        scr.dispose();
        long freeMemoryNew = runtime.freeMemory();
        assertTrue("Memory cleaned up", (freeMemory <= freeMemoryNew));
    }
}