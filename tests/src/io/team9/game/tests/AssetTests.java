package io.team9.game.tests;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AssetTests {

    // Root assets directory
    @Test
    public void testBeachTilesetAssetPNGExists() {
        assertTrue("This test will only pass when the Beach Tileset.png asset exists", Gdx.files
                .internal("Beach Tileset.png").exists());
    }

    @Test
    public void testBeachTilesetTSXAssetExists() {
        assertTrue("This test will only pass when the Beach Tileset.tsx asset exists", Gdx.files
                .internal("Beach Tileset.tsx").exists());
    }

    @Test
    public void testBoatsTXTAssetExists() {
        assertTrue("This test will only pass when the Boats.txt asset exists", Gdx.files
                .internal("Boats.txt").exists());
    }

    @Test
    public void testBuildingsTXTAssetExists() {
        assertTrue("This test will only pass when the Beach Buildings.txt asset exists", Gdx.files
                .internal("Buildings.txt").exists());
    }

    @Test
    public void testChestAssetExists() {
        assertTrue("This test will only pass when the Chest.png asset exists", Gdx.files
                .internal("Chest.png").exists());
    }

    @Test
    public void testGameSettingsJSONAssetExists() {
        assertTrue("This test will only pass when the GameSettings.json asset exists", Gdx.files
                .internal("GameSettings.json").exists());
    }

    @Test
    public void testMapTMXAssetExists() {
        assertTrue("This test will only pass when the Map.tmx asset exists", Gdx.files
                .internal("Map.tmx").exists());
    }

    @Test
    public void testArialTTFAssetExists() {
        assertTrue("This test will only pass when the arial.ttf asset exists", Gdx.files
                .internal("arial.ttf").exists());
    }

    @Test
    public void testBoatsAssetExists() {
        assertTrue("This test will only pass when the boats.png asset exists", Gdx.files
                .internal("boats.png").exists());
    }

    @Test
    public void testMenuBGAssetExists() {
        assertTrue("This test will only pass when the menuBG.jpg asset exists", Gdx.files
                .internal("menuBG.jpg").exists());
    }

    @Test
    public void testNewMenuBGAssetExists() {
        assertTrue("This test will only pass when the newmenuBG.jpg asset exists", Gdx.files
                .internal("newmenuBG.jpg").exists());
    }

    @Test
    public void testOtherAssetExists() {
        assertTrue("This test will only pass when the other.png asset exists", Gdx.files
                .internal("other.png").exists());
    }

    @Test
    public void testShipAssetExists() {
        assertTrue("This test will only pass when the ship.png asset exists", Gdx.files
                .internal("ship.png").exists());
    }

    @Test
    public void testShopBGAssetExists() {
        assertTrue("This test will only pass when the shopBG.jpg asset exists", Gdx.files
                .internal("shopBG.jpg").exists());
    }

    @Test
    public void testTestXMLAssetExists() {
        assertTrue("This test will only pass when the test.xml asset exists", Gdx.files
                .internal("test.xml").exists());
    }

    // /UISkin files

    @Test
    public void testDefaultFNTAssetExists() {
        assertTrue("This test will only pass when the /UISkin/default.fnt asset exists", Gdx.files
                .internal("UISkin/default.fnt").exists());
    }

    @Test
    public void testUISkinDefaultAssetExists() {
        assertTrue("This test will only pass when the /UISkin/default.png asset exists", Gdx.files
                .internal("UISkin/default.png").exists());
    }

    @Test
    public void testSkinATLASAssetExists() {
        assertTrue("This test will only pass when the /UISkin/skin.atlas asset exists", Gdx.files
                .internal("UISkin/skin.atlas").exists());
    }

    @Test
    public void testSkinJSONAssetExists() {
        assertTrue("This test will only pass when the /UISkin/skin.json asset exists", Gdx.files
                .internal("UISkin/skin.json").exists());
    }

    @Test
    public void testUISkinAssetExists() {
        assertTrue("This test will only pass when the /UISkin/uiskin.png asset exists", Gdx.files
                .internal("UISkin/uiskin.png").exists());
    }


    //  /powerups files
    @Test
    public void testPowerup1AssetExists() {
        assertTrue("This test will only pass when the powerups/powerup1.png asset exists", Gdx.files
                .internal("powerups/powerup1.png").exists());
    }

    @Test
    public void testPowerup2AssetExists() {
        assertTrue("This test will only pass when the powerups/powerup2.png asset exists", Gdx.files
                .internal("powerups/powerup2.png").exists());
    }

    @Test
    public void testPowerup3AssetExists() {
        assertTrue("This test will only pass when the powerups/powerup3.png asset exists", Gdx.files
                .internal("powerups/powerup3.png").exists());
    }

    @Test
    public void testPowerup4AssetExists() {
        assertTrue("This test will only pass when the powerups/powerup4.png asset exists", Gdx.files
                .internal("powerups/powerup4.png").exists());
    }

    @Test
    public void testPowerup5AssetExists() {
        assertTrue("This test will only pass when the powerups/powerup5.png asset exists", Gdx.files
                .internal("powerups/powerup5.png").exists());
    }

    @Test
    public void testPowerup6AssetExists() {
        assertTrue("This test will only pass when the powerups/powerup6.png asset exists", Gdx.files
                .internal("powerups/powerup6.png").exists());
    }
}