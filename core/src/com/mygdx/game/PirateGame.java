package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.UI.*;

/**
 * Contains class instances of game UI screens.
 */
public class PirateGame extends Game {
    public MenuScreen menu;
    public GameScreen game;
    public EndScreen end;
    public Stage stage;
    public Skin skin;
    public ShopScreen shop;
    public PowerupScreen powerup;
    public QuitConfirmationScreen quitConfirm;

    /**
     * Create instances of game stage and UI screens.
     */
    @Override
    public void create() {
        // load resources
        int id_ship = ResourceManager.addTexture("ship.png");
        int id_map = ResourceManager.addTileMap("Map.tmx");
        int atlas_id = ResourceManager.addTextureAtlas("Boats.txt");
        int extras_id = ResourceManager.addTextureAtlas("UISkin/skin.atlas");
        int buildings_id = ResourceManager.addTextureAtlas("Buildings.txt");
        ResourceManager.addTexture("menuBG.jpg");
        ResourceManager.addTexture("newmenuBG.jpg");
        ResourceManager.addTexture("shopBG.jpg");
        ResourceManager.addTexture("Chest.png");
        ResourceManager.addTexture("powerups/powerup1.png");
        ResourceManager.addTexture("powerups/powerup2.png");
        ResourceManager.addTexture("powerups/powerup3.png");
        ResourceManager.addTexture("powerups/powerup4.png");
        ResourceManager.addTexture("powerups/powerup5.png");
        ResourceManager.addTexture("powerups/powerup6.png");
        ResourceManager.loadAssets();
        // cant load any more resources after this point (just functionally I choose not to implement)
        stage = new Stage(new ScreenViewport());
        createSkin();
        menu = new MenuScreen(this);
        shop = new ShopScreen(this);
        game = new GameScreen(this, id_map);
        end = new EndScreen(this);
        powerup = new PowerupScreen(this);
        quitConfirm = new QuitConfirmationScreen(this);
        setScreen(menu);
    }

    /**
     * Clean up prevent memory leeks
     */
    @Override
    public void dispose() {
        menu.dispose();
        shop.dispose();
        game.dispose();
        stage.dispose();
        skin.dispose();
        powerup.dispose();
        quitConfirm.dispose();
    }

    /**
     * load ui skin from assets
     */
    private void createSkin() {
        skin = new Skin(Gdx.files.internal("UISkin/skin.json"));
    }
}
