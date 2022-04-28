package io.team9.game.tests.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.TileMap;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Entitys.Chest;
import com.mygdx.game.Entitys.WorldMap;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.ResourceManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class WorldMapTest {

    @Test
    public void createMapTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        int id_map = ResourceManager.addTileMap("Map.tmx");
        WorldMap map = new WorldMap(id_map);

        assertEquals("tile map", map.getComponent(TileMap.class).getTileMap(), map.getTileMap());
    }

}
