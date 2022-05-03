package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.PirateGame;

import static com.mygdx.utils.Constants.*;
import static com.mygdx.utils.Constants.PHYSICS_TIME_STEP;

/**NEW
 * Contains widgets defining the shop screen.
 */
public class ShopScreen extends Page {
    public ShopScreen(PirateGame parent) {
        super(parent);
    }


    private float accumulator;

    /**
     * Called every frame calls all other functions that need to be called every frame by rasing events and update methods
     *
     * @param delta delta time
     */
    @Override
    public void render(float delta) {
        // show game screen if ESC key is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            parent.setScreen(parent.game);
        }
        super.render(delta);
    }

    @Override
    protected void update() {
        super.update();
        Player p = GameManager.getPlayer();

        GameScreen.healthLabel.setText(String.valueOf(p.getHealth()));
        GameScreen.dosh.setText(String.valueOf(p.getPlunder()));
        GameScreen.ammo.setText(String.valueOf(p.getAmmo()));
    }


    /**
     * Create menu widgets such as start button, labels, etc.
     */
    @Override
    protected void CreateActors() {
        Table t = new Table();
        t.setFillParent(true);

        float space = VIEWPORT_HEIGHT* 0.05f; // gap between rows in table

        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("shopBG.jpg")));
        Label l = new Label("Shop", parent.skin);
        l.setFontScale(2);
        t.add(l).top().spaceBottom(space * 0.5f);
        
        t.row();
        TextButton buyAmmo = new TextButton("Buy Ammo", parent.skin);
        buyAmmo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(Pirate.plunder >= 10){
                    Pirate.ammo += 10; // add 10 ammo
                    Pirate.plunder -= 10; // take away 10 coins
                }
            }
        });
        t.add(buyAmmo).size(100, 25).top().spaceBottom(10);
        t.row();
        t.add(new Label("Buy 10 cannons for 10 coins", parent.skin)).spaceBottom(10);
        t.row();
        t.add(new Image(parent.skin, "ball")).top().left();
        t.add(new Label("10", parent.skin)).right().spaceBottom(space);


        t.row();
        TextButton buyPowerups = new TextButton("Powerups", parent.skin);
        buyPowerups.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(parent.powerup);
            }
        });
        t.add(buyPowerups).size(100, 25).top().spaceBottom(10);
        t.row();
        t.add(new Label("Continue to the powerup page", parent.skin)).spaceBottom(space);


        t.row();
        TextButton back = new TextButton("Return", parent.skin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(parent.game);
            }
        });
        t.add(back).top().size(100, 25).spaceBottom(space);

        t.top();

        actors.add(t);
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    /**
     * Used to resize the background texture to the correct size to fill the window
     * @param width  new dim x width of the screen
     * @param height new dom y height of the screen
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Table t = (Table) actors.get(0);
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("shopBG.jpg"))); // prevent the bg being stretched
    }
}
