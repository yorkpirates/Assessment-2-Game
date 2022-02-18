package com.mygdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * creates game constants and is updated when appropriate (I know some aren't technically constants)
 */
public final class Constants {
    /**
     * Create constants need so it can properly source screen dimensions
     */
    public static void INIT_CONSTANTS() {
        // FULLSCREEN = !Boolean.parseBoolean(System.getProperty("windowed"));
        FULLSCREEN = false;
        try {
            SCREEN_WIDTH = Gdx.graphics.getWidth();
            SCREEN_HEIGHT = Gdx.graphics.getHeight();
        } catch (Exception e) {
            SCREEN_WIDTH = 1920;
            SCREEN_HEIGHT = 1080;
        }
        ASPECT_RATIO = !FULLSCREEN ? 1.0f / 1.0f : (float) SCREEN_WIDTH / (float) SCREEN_HEIGHT;
        VIEWPORT_HEIGHT = !FULLSCREEN ? 800 : SCREEN_HEIGHT;
        VIEWPORT_WIDTH = !FULLSCREEN ? (int) (ASPECT_RATIO * VIEWPORT_HEIGHT) : SCREEN_WIDTH;
        HALF_VIEWPORT_HEIGHT = VIEWPORT_WIDTH / 2;
        HALF_VIEWPORT_WIDTH = VIEWPORT_HEIGHT / 2;
        DIMENSIONS = new Vector2(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        HALF_DIMENSIONS = new Vector2(HALF_VIEWPORT_WIDTH, HALF_VIEWPORT_HEIGHT);
        VIEWPORT_TITLE = "Pirate Game";
        BACKGROUND_COLOUR = new Vector3(0.0f, 0.0f, 0.0f);
        PHYSICS_TIME_STEP = 1.0f / 60.0f;

        OPERATING_SYSTEM = System.getProperty("os.name");

        TILE_SIZE = 32;
    }

    /**
     * Update viewport data on resize
     *
     * @param x new dim x
     * @param y new dim y
     */
    public static void UPDATE_VIEWPORT(int x, int y) {
        VIEWPORT_HEIGHT = y;
        VIEWPORT_WIDTH = x;
        ASPECT_RATIO = (float) SCREEN_WIDTH / (float) SCREEN_HEIGHT;
        HALF_VIEWPORT_HEIGHT = VIEWPORT_HEIGHT / 2;
        HALF_VIEWPORT_WIDTH = VIEWPORT_WIDTH / 2;
        DIMENSIONS = new Vector2(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        HALF_DIMENSIONS = new Vector2(HALF_VIEWPORT_WIDTH, HALF_VIEWPORT_HEIGHT);
    }

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static boolean FULLSCREEN;
    public static float ASPECT_RATIO;
    public static int VIEWPORT_HEIGHT;
    public static int VIEWPORT_WIDTH;
    public static int HALF_VIEWPORT_HEIGHT;
    public static int HALF_VIEWPORT_WIDTH;
    public static Vector2 DIMENSIONS;
    public static Vector2 HALF_DIMENSIONS;
    public static String VIEWPORT_TITLE;
    public static float PHYSICS_TIME_STEP;
    public static final float ZOOM = 2.15f;
    public static final boolean VSYNC = true;
    public static final float BUILDING_SCALE = 1.5f;

    public static float TILE_SIZE;

    public static Vector3 BACKGROUND_COLOUR;

    public static String OPERATING_SYSTEM;
}
