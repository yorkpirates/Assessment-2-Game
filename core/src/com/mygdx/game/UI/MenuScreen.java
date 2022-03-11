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

        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("menuBG.jpg")));
        Label l = new Label("Pirates the movie the game", parent.skin);
        l.setFontScale(2);
        t.add(l).top().spaceBottom(space * 0.5f);
        t.row();

        final SelectBox<String> selectBox=new SelectBox<String>(parent.skin);
        selectBox.setItems("Select Difficulty","Easy","Normal","Hard");

        t.add(selectBox).top().size(100, 25);
        t.row();


        TextButton play = new TextButton("Play", parent.skin);
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

        t.add(play).top().size(100, 25).spaceBottom(space);
        t.row();




        TextButton load = new TextButton("Load", parent.skin);
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showLoadMenu();
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
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("menuBG.jpg"))); // prevent the bg being stretched
    }
    private void showLoadMenu(){
        if(System.getProperty("os.name").contains("Mac")){
            //Choose A
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
            FileDialog fileDialog = new FileDialog((java.awt.Frame)null,"Select file");
            fileDialog.setVisible(true);
            System.out.println("If you want to load GAMES on mac then due to apple 20% policies we will need to have 20% of the cost of this product wired to our accounts in Monero thanks ♥");
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
