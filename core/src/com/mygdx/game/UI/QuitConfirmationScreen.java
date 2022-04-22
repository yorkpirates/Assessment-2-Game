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
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.DifficultyManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.PirateGame;
import com.mygdx.utils.SaveObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static com.mygdx.utils.Constants.VIEWPORT_HEIGHT;

/**
 * Contains widgets defining the shop screen.
 */
public class QuitConfirmationScreen extends Page {
    public QuitConfirmationScreen(PirateGame parent) {
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
    }


    /**
     * Create menu widgets such as start button, labels, etc.
     */
    @Override
    protected void CreateActors() {
        Table t = new Table();
        t.setFillParent(true);

        float space = VIEWPORT_HEIGHT* 0.05f; // gap between rows in table

        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("menuBG.jpg")));
        Label l = new Label("Ending Game", parent.skin);
        l.setFontScale(2);
        t.add(l).top().spaceBottom(space * 0.5f);
        t.row();

        t.row();
        t.add(new Label("Are you sure you want to quit?", parent.skin)).spaceBottom(10);
        t.row();
        TextButton quit = new TextButton("Quit", parent.skin);
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(parent.end);
            }
        });
        t.add(quit).top().size(100, 25).spaceBottom(space);
        t.row();
        TextButton SaveBtn = new TextButton("Save", parent.skin);
        SaveBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showSaveMenu();
            }
        });
        t.add(SaveBtn).top().size(100, 25).spaceBottom(space);
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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Table t = (Table) actors.get(0);
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("shopBG.jpg"))); // prevent the bg being stretched
    }
    private void showSaveMenu(){
        if(System.getProperty("os.name").contains("Mac")){
            //Choose A
            SaveObject.writeXMl("SAVED_GAME.xml");
        }
        else{
            JFileChooser fileChooser = new JFileChooser();

            int value = fileChooser.showSaveDialog(null);
            if (value == JFileChooser.APPROVE_OPTION){
                File selected=  fileChooser.getSelectedFile();

                SaveObject.writeXMl(selected.getAbsolutePath());
                //to be removed upon implementation in save
                DifficultyManager.SelectEasy();
                parent.setScreen(parent.game);
                //to be removed upon implementation in save
                GameManager.getPlayer().updateHealth();

            }

        }


    }
}
