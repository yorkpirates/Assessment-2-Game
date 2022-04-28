package io.team9.game.tests.utils;

import com.badlogic.gdx.math.Vector2;
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
    
    @Test
    public void testViewportUpdate() {
        constants.SCREEN_WIDTH = 2;
        constants.SCREEN_HEIGHT = 2;
        constants.UPDATE_VIEWPORT(1, 2);
        assertEquals(constants.VIEWPORT_HEIGHT, 2);
        assertEquals(constants.VIEWPORT_WIDTH, 1);
        assertEquals(constants.ASPECT_RATIO, constants.SCREEN_WIDTH / constants.SCREEN_HEIGHT, constants.SCREEN_WIDTH / constants.SCREEN_HEIGHT);
        assertEquals(constants.HALF_VIEWPORT_HEIGHT, constants.VIEWPORT_HEIGHT / 2);
        assertEquals(constants.HALF_VIEWPORT_WIDTH, constants.VIEWPORT_WIDTH / 2);
        assertEquals(constants.DIMENSIONS, new Vector2(constants.VIEWPORT_WIDTH, constants.VIEWPORT_HEIGHT));
        assertEquals(constants.HALF_DIMENSIONS, new Vector2(constants.HALF_VIEWPORT_WIDTH, constants.HALF_VIEWPORT_HEIGHT));
    }

}