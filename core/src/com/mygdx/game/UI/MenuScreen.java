package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.DifficultyManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.PirateGame;
import com.mygdx.utils.SaveObject;


import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import static com.mygdx.utils.Constants.VIEWPORT_HEIGHT;

/**
 * Contains widgets defining the start-of-game menu screen.
 */
public class MenuScreen extends Page {
    public MenuScreen(PirateGame parent) {
        super(parent);
    }

    /**
     * Create menu widgets such as start button, labels, etc.
     */
    @Override
    protected void CreateActors() {
        Table t = new Table();
        t.setFillParent(true);

        float space = VIEWPORT_HEIGHT * 0.10f;

        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("newmenuBG.jpg")));
        Label l = new Label("Pirates the movie the game", parent.skin);
        l.setFontScale(2);
        t.add(l).top().spaceBottom(space * 0.5f);
        t.row();

        TextButton play = new TextButton("Play", parent.skin);
        t.add(play).top().size(100, 25);
        t.row();

        final SelectBox<String> selectBox=new SelectBox<String>(parent.skin);
        selectBox.setItems("Select Difficulty","Easy","Normal","Hard");

        t.add(selectBox).top().size(100, 25).spaceBottom(space);
        t.row();


        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switch (selectBox.getSelected()){
                    case "Select Difficulty":
                        return;
                    case "Easy":
                        DifficultyManager.SelectEasy();
                        break;
                    case "Normal":
                        DifficultyManager.SelectNormal();
                        break;
                    case "Hard":
                        DifficultyManager.SelectHard();
                        break;
                }
                parent.setScreen(parent.game);
                GameManager.getPlayer().updateHealth();
            }
        });


        TextButton load = new TextButton("Load", parent.skin);
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    try {
                        JOptionPane.showMessageDialog(null,"TEST");
                        showLoadMenu();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        });
        t.add(load).top().size(100, 25).spaceBottom(space);
        t.row();


        TextButton quit = new TextButton("Quit", parent.skin);
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
        t.add(quit).size(100, 25).top().spaceBottom(space);

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
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("newmenuBG.jpg"))); // prevent the bg being stretched
    }
    private void showLoadMenu() throws FileNotFoundException {
        if(System.getProperty("os.name").contains("Mac")){
            //Get ABSOLOUTE file for mac
            File f = new File("SAVED_GAME.xml");

            if(!f.exists()){
                throw new FileNotFoundException();
            }
            SaveObject.readXML("SAVED_GAME.xml");
            //to be removed upon implementation in save
            DifficultyManager.SelectEasy();
            parent.setScreen(parent.game);
            //to be removed upon implementation in save
            GameManager.getPlayer().updateHealth();

        }
        else{
            JFileChooser fileChooser = new JFileChooser();

            int value = fileChooser.showOpenDialog(null);
            if (value == JFileChooser.APPROVE_OPTION){
                File selected=  fileChooser.getSelectedFile();

                SaveObject.readXML(selected.getAbsolutePath());
                //to be removed upon implementation in save
                DifficultyManager.SelectEasy();
                parent.setScreen(parent.game);
                //to be removed upon implementation in save
                GameManager.getPlayer().updateHealth();

            }

        }


    }
}
