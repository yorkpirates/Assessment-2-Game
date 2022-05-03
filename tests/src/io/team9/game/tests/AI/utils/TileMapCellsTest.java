package io.team9.game.tests.AI.utils;

import com.mygdx.utils.TileMapCells;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class TileMapCellsTest {

    TileMapCells tilemap = new TileMapCells();
    @Test
    public void testObstacle() {
        assertEquals("obstacle", tilemap.OBSTACLE, 61);
    }

    @Test
    public void testPassable() {
        assertEquals("passable", tilemap.PASSABLE, 97);
    }

    @Test
    public void testObstacleCost() {
        assertEquals( tilemap.OBSTACLE_COST, 100000f, 100000f);
    }

}
