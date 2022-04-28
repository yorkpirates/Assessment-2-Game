package io.team9.game.tests.utils;

import com.mygdx.utils.Constants;
import com.mygdx.utils.TileMapCells;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ConstantsTest {

    Constants constants = new Constants();
    @Test
    public void testFullScreen() {
        assertFalse(constants.FULLSCREEN);
    }

    @Test
    public void testTileSize() {
        assertEquals( constants.TILE_SIZE, 32, 32);
    }

    @Test
    public void testPhysics() {
        assertEquals(constants.PHYSICS_TIME_STEP, 1.0f / 60.0f, 1.0f / 60.0f);
    }

    @Test
    public void testVSTNC() {
        assertTrue(constants.VSYNC);
    }

    @Test
    public void testZoom() {
        assertEquals(constants.ZOOM, 1.75f, 1.75f);
    }

    @Test
    public void testBuildingScale() {
        assertEquals(constants.BUILDING_SCALE, 1.5f, 1.5f);
    }


}