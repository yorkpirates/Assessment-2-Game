package io.team9.game.tests.Managers;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.ResourceManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class GameManagerTest {

    @Test
    public void spawnTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        int id_map = ResourceManager.addTileMap("Map.tmx");
        GameManager.SpawnGame(id_map);
        assertEquals("Expected amount of ships",15,GameManager.ships.size());
        assertEquals("Expected amount of ships",1,GameManager.monsters.size());
        assertEquals("Expected amount of ships",1,GameManager.weathers.size());
    }
}
