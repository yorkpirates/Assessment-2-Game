package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.DifficultyManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.QuestManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.PirateGame;
import com.mygdx.utils.SaveObject;

import static com.mygdx.utils.Constants.VIEWPORT_HEIGHT;

/**
 * Contains widgets defining the game end screen.
 */
public class EndScreen extends Page {
    Label wonText;
    Label playerStats;

    Integer questsNeeded ;
    Integer pointsNeeded;

    public EndScreen(PirateGame game) {
        super(game);
    }

    /**
     * Set game end screen status to report a win.
     */
    public void win() {
        wonText.setText("Congrats You Have Won");
    }

    /**
     * Create game end screen widgets, initialised to game loss status.
     */
    @Override
    protected void CreateActors() {
        Table t = new Table();
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("menuBG.jpg")));

        float space = VIEWPORT_HEIGHT * 0.25f;
        t.setFillParent(true);
        actors.add(t);
        wonText = new Label("You have lost", parent.skin);
        wonText.setFontScale(2);
        t.top();
        t.add(wonText).top().spaceBottom(space);
        t.row();
        playerStats = new Label("Player Stats:\n", parent.skin);
        t.add(playerStats).spaceBottom(space);
        t.row();
        TextButton b = new TextButton("Exit", parent.skin);
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
        t.add(b).spaceBottom(5);;
        t.row();
        TextButton RestartBtn = new TextButton("Restart", parent.skin);
        RestartBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SaveObject.readXML("restart.xml");
                DifficultyManager.SelectEasy();
                parent.setScreen(parent.game);
                //to be removed upon implementation in save
                GameManager.getPlayer().updateHealth();

            }
        });
        t.add(RestartBtn).top().size(100, 25).spaceBottom(space);
        t.row();

    }

    @Override
    protected void update() {
        super.update();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }

    }

    /**
     * Get player stats such as plunder etc. and display game end screen.
     */
    @Override
    public void show() {
        super.show();
        if(WinCheck()){
            win();
        }
        Player p = GameManager.getPlayer();
        String stats = String.format("Health: %s\nAmmo: %s\nPlunder: %s\nPoints: %s/%s\nQuests Completed: %s/%s", p.getHealth(), p.getAmmo(), p.getPlunder(),p.getComponent(Pirate.class).getPoints(),pointsNeeded,QuestManager.numCompleted,questsNeeded);
        playerStats.setText(stats);



    }

    /**NEW
     * Checks if the player meets the win conditions based on the difficulty level
     * @return A Boolean based on whether the player has won
     */
    private boolean WinCheck(){
        Boolean result = false;
        questsNeeded = 0;
        pointsNeeded = 0;
        switch (DifficultyManager.getDifficulty()){
            case "e":
                questsNeeded =1;
                pointsNeeded = 100;
                break;

            case "n":
                questsNeeded = 3;
                pointsNeeded = 500;
                break;


            case "h":
                questsNeeded = 5;
                pointsNeeded = 1000;
                break;

        }
        if(QuestManager.numCompleted>=questsNeeded){
            if(GameManager.getPlayer().getComponent(Pirate.class).getPoints()>pointsNeeded){
                result = true;
            }
        }
        return result;

    }


    /**
     * @param width  new dim x
     * @param height new dom y
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Table t = (Table) actors.get(0);
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("menuBG.jpg"))); // prevent the bg being stretched
    }
}
