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
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.PirateGame;

import static com.mygdx.utils.Constants.TILE_SIZE;
import static com.mygdx.utils.Constants.VIEWPORT_HEIGHT;

/**
 * Contains widgets defining the shop screen.
 */
public class PowerupScreen extends Page {
    public PowerupScreen(PirateGame parent) {
        super(parent);
    }

    /**
     * Called every frame calls all other functions that need to be called every frame by rasing events and update methods
     *
     * @param delta delta time
     */
    @Override
    public void render(float delta) {
        // show game screen if ESC key is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            parent.setScreen(parent.shop);
        }
        super.render(delta);
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

        t.row();
        TextButton buyPowerup1 = new TextButton("Buy Powerup 1", parent.skin);
        buyPowerup1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(parent.game);
            }
        });
        t.add(buyPowerup1).top().size(100, 25).spaceBottom(10);
        t.row();
        t.add(new Image(parent.skin, "coin")).top().left();
        t.add(new Label("20", parent.skin)).right().spaceBottom(space);


        t.row();
        TextButton buyPowerup2 = new TextButton("Buy Powerup 2", parent.skin);
        buyPowerup2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(parent.game);
            }
        });
        t.add(buyPowerup2).size(100, 25).top().spaceBottom(10);
        t.row();
        t.add(new Image(parent.skin, "coin")).top().left();
        t.add(new Label("10", parent.skin)).right().spaceBottom(space);


        t.row();
        TextButton buyPowerup3 = new TextButton("Buy Powerup 3", parent.skin);
        buyPowerup3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(parent.game);
            }
        });
        t.add(buyPowerup3).size(100, 25).top().spaceBottom(10);
        t.row();
        t.add(new Image(parent.skin, "coin")).top().left().spaceBottom(space);
        t.add(new Label("10", parent.skin)).right().spaceBottom(space);

        t.row();
        TextButton buyPowerup4 = new TextButton("Buy Powerup 4", parent.skin);
        buyPowerup4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(parent.game);
            }
        });
        t.add(buyPowerup4).size(100, 25).top().spaceBottom(10);
        t.row();
        t.add(new Image(parent.skin, "coin")).top().left().spaceBottom(space);
        t.add(new Label("10", parent.skin)).right().spaceBottom(space);

        t.row();
        TextButton back = new TextButton("Return", parent.skin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(parent.shop);
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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Table t = (Table) actors.get(0);
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("shopBG.jpg"))); // prevent the bg being stretched
    }
}
