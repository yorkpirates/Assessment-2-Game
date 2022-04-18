package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.Text;
import com.mygdx.game.Entitys.NPCShip;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Managers.*;
import com.mygdx.game.PirateGame;
import com.mygdx.game.Quests.Quest;


import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static com.mygdx.utils.Constants.*;

public class GameScreen extends Page {
    public static Label healthLabel;
    public static Label dosh;
    public static Label ammo;

    public static Label points;
    public static Label questName;
    public static Label questDesc;

    private Label power_up_name1;
    private Label power_up_time1;
    private Label power_up_name2;
    private Label power_up_time2;
    private Label warn1;
    private Label warn2;


    public static float[] powers;
    public static int[] durations;
    public static float warn1_time, warn2_time;
    public static int timer;
    public static int num_powers;
    public static int message1, message2;
    private Texture texture;
    private float interval;
    private Random r = new Random();



    /*private final Label questComplete;
    private float showTimer = 0;
    // in seconds
    private static final float showDuration = 1;*/

    /**
     * Boots up the actual game: starts PhysicsManager, GameManager, EntityManager,
     * loads texture atlases into ResourceManager. Draws quest and control info.
     *
     * @param parent PirateGame UI screen container
     * @param id_map the resource id of the tile map
     */
    public GameScreen(PirateGame parent, int id_map) {
        super(parent);
        INIT_CONSTANTS();
        PhysicsManager.Initialize(false);

        /*int id_ship = ResourceManager.addTexture("ship.png");
        int id_map = ResourceManager.addTileMap("Map.tmx");
        int atlas_id = ResourceManager.addTextureAtlas("Boats.txt");
        int extras_id = ResourceManager.addTextureAtlas("UISkin/skin.atlas");
        int buildings_id = ResourceManager.addTextureAtlas("Buildings.txt");
        ResourceManager.loadAssets();*/


        GameManager.SpawnGame(id_map);
        //QuestManager.addQuest(new KillQuest(c));

        EntityManager.raiseEvents(ComponentEvent.Awake, ComponentEvent.Start);

        Window questWindow = new Window("Current Quest", parent.skin);

        Quest q = QuestManager.currentQuest();
        Table t = new Table();
        questName = new Label("NAME", parent.skin);
        t.add(questName);
        t.row();
        questDesc = new Label("DESCRIPTION", parent.skin);
        if (q != null) {
            questName.setText(q.getName());
            questDesc.setText(q.getDescription());
        }

        t.add(questDesc).left();
        questWindow.add(t);
        actors.add(questWindow);

        Table t1 = new Table();
        t1.top().right();
        t1.setFillParent(true);
        actors.add(t1);

        Window tutWindow = new Window("Controls", parent.skin);
        Table table = new Table();
        tutWindow.add(table);
        t1.add(tutWindow);

        table.add(new Label("Move with", parent.skin)).top().left();
        table.add(new Image(parent.skin, "key-w"));
        table.add(new Image(parent.skin, "key-s"));
        table.add(new Image(parent.skin, "key-a"));
        table.add(new Image(parent.skin, "key-d"));
        table.row();
        table.add(new Label("Shoot in direction of mouse", parent.skin)).left();
        //table.add(new Image(parent.skin, "space"));
        table.add(new Image(parent.skin, "mouse"));
        table.row();
        table.add(new Label("Shoot in direction of ship", parent.skin)).left();
        table.add(new Image(parent.skin, "space"));
        table.row();
        table.add(new Label("Quit", parent.skin)).left();
        table.add(new Image(parent.skin, "key-esc"));
        table.row();
        table.add(new Label("Shop", parent.skin)).left();
        table.add(new Image(parent.skin, "key-e"));

        powers = new float[6];
        durations = new int[]{20, 30, 25, 20, 15, 5};
        num_powers = 0;
        message1 = -1;
        message2 = -1;
        warn1_time = 0;
        warn2_time = 0;
        interval = 0f;
    }

    private float accumulator;

    /**
     * Called every frame calls all other functions that need to be called every frame by rasing events and update methods
     *
     * @param delta delta time
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_COLOUR.x, BACKGROUND_COLOUR.y, BACKGROUND_COLOUR.z, 1);

        EntityManager.raiseEvents(ComponentEvent.Update, ComponentEvent.Render);

        accumulator += EntityManager.getDeltaTime();

        // fixed update loop so that physics manager is called regally rather than somewhat randomly
        while (accumulator >= PHYSICS_TIME_STEP) {
            PhysicsManager.update();
            accumulator -= PHYSICS_TIME_STEP;
        }

        // show end screen if esc is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            parent.setScreen(parent.quitConfirm);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            parent.setScreen(parent.shop);
        }

        GameManager.update();
        super.render(0);
    }

    /**
     * disposed of all stuff it something is missing from this method you will get memory leaks
     */
    @Override
    public void dispose() {
        super.dispose();
        ResourceManager.cleanUp();
        EntityManager.cleanUp();
        RenderingManager.cleanUp();
        PhysicsManager.cleanUp();
    }

    /**
     * Resize camera, effectively setting the viewport to display game assets
     * at pixel ratios other than 1:1.
     *
     * @param width  of camera viewport
     * @param height of camera viewport
     */
    @Override
    public void resize(int width, int height) {
        //((Table) actors.get(0)).setFillParent(false);
        super.resize(width, height);
        OrthographicCamera cam = RenderingManager.getCamera();
        cam.viewportWidth = width / ZOOM;
        cam.viewportHeight = height / ZOOM;
        cam.update();

        // ((Table) actors.get(0)).setFillParent(true);
    }

    /**
     * Update the UI with new values for health, quest status, etc.
     * also called once per frame but used for actors by my own convention
     */
    //private String prevQuest = "";
    @Override
    protected void update() {
        super.update();
        Player p = GameManager.getPlayer();
        boolean reward_powerUp = p.getReward_powerUp();

        if (reward_powerUp == true){
            rewardPowerUp();
            p.setReward_powerUp(false);
        }

        powerUp();
        if (timer ==60){
            timer =0;
            p.getComponent(Pirate.class).addPoints(1);
        }
        else{
            timer++;
        }
        healthLabel.setText(String.valueOf(p.getHealth()));
        dosh.setText(String.valueOf(p.getPlunder()));
        ammo.setText(String.valueOf(p.getAmmo()));
        points.setText(String.valueOf(p.getComponent(Pirate.class).getPoints()));
        if (!QuestManager.anyQuests()) {
            parent.end.win();
            parent.setScreen(parent.end);}
        else if(!p.isAlive()){
            parent.setScreen(parent.end);
        }
         else {
            Quest q = QuestManager.currentQuest();
            /*if(Objects.equals(prevQuest, "")) {
                prevQuest = q.getDescription();
            }
            if(!Objects.equals(prevQuest, q.getDescription())) {
                questComplete.setText("Quest Competed");
                prevQuest = "";
            }*/
            questName.setText(q.getName());
            questDesc.setText(q.getDescription());
        }
        /*if(!questComplete.getText().equals("")) {
            showTimer += EntityManager.getDeltaTime();
        }
        if(showTimer >= showDuration) {
            showTimer = 0;
            questComplete.setText("");
        }*/
        //createMessage();

    }

    /**
     * Draw UI elements showing player health, plunder, and ammo.
     */
    @Override
    protected void CreateActors() {
        Table table = new Table();
        table.setFillParent(true);
        actors.add(table);


        table.add(new Image(parent.skin.getDrawable("heart"))).top().left().size(1.25f * TILE_SIZE);
        healthLabel = new Label("N/A", parent.skin);
        table.add(healthLabel).top().left().size(50);

        table.row();
        table.setDebug(false);

        table.add(new Image(parent.skin.getDrawable("coin"))).top().left().size(1.25f * TILE_SIZE);

        dosh = new Label("N/A", parent.skin);
        table.add(dosh).top().left().size(50);

        table.row();
        points = new Label("N/A", parent.skin);
        table.add(points).top().left().size(50);

        table.row();

        table.add(new Image(parent.skin.getDrawable("ball"))).top().left().size(1.25f * TILE_SIZE);
        ammo = new Label("N/A", parent.skin);
        table.add(ammo).top().left().size(50);

        table.top().left();

        Table table_message = new Table();
        table_message.setFillParent(true);
        actors.add(table_message);


        // Power Up Stuff

        power_up_name1 = new Label("", parent.skin);
        power_up_time1 = new Label("", parent.skin);
        power_up_name2 = new Label("", parent.skin);
        power_up_time2 = new Label("", parent.skin);

        warn1 = new Label("", parent.skin);
        warn2 = new Label("", parent.skin);

        table_message.add(warn2).bottom().right().size(60);
        table_message.row();

        table_message.add(warn1).bottom().right().size(60);
        table_message.row();

        table_message.add(power_up_name2).bottom().right().size(60);
        table_message.row();

        table_message.add(power_up_time2).bottom().right().size(60);
        table_message.row();

        table_message.add(power_up_name1).bottom().right().size(60);
        table_message.row();

        table_message.add(power_up_time1).bottom().right().size(60);
        table_message.row();

        table_message.bottom();
    }

    private void send_warn(int type){
        if(type == 1 ) {
            warn1_time = 5;
            warn1.setText("*You cannot have 2 identical power-ups at the same time*");
        }
        else{
            warn2_time=5;
            warn2.setText("*You can only have maximum 2 power-ups at the same time *");
        }
    }

    private void rewardPowerUp(){
        ArrayList<Ship> ships = GameManager.getShips();
        int j = r.nextInt(5);
        switch (j){
            case 0:
                // TempImmortality
                if (PowerupScreen.isPowerup1Owned && num_powers < 2) {
                    if (message1 == -1) {
                        power_up_name1.setText("Power Up - Temporary Immortality");
                        message1 = 0;
                    } else {
                        power_up_name2.setText("Power Up - Temporary Immortality");
                        message2 = 0;
                    }
                    ships.get(0).tempImmortality(true);
                    powers[0] = durations[0];
                    num_powers++;
                    interval = 0;
                }
                else if (powers[0] > 0 && num_powers < 2)
                    send_warn(1);
                else if (num_powers==2)
                    send_warn(2);

                break;
            case 1:
                // BiggerDamage
                if (PowerupScreen.isPowerup2Owned && num_powers < 2){
                    if (message1==-1){
                        power_up_name1.setText("Power Up - Bigger Damage");
                        message1 = 1;
                    }
                    else{
                        power_up_name2.setText("Power Up - Bigger Damage");
                        message2 = 1;
                    }
                    ships.get(0).biggerDamage(true);
                    powers[1] = durations[1];
                    num_powers++;
                }
                else if (powers[1] > 0 && num_powers < 2)
                    send_warn(1);
                else if (num_powers==2)
                    send_warn(2);
                break;
            case 2:
                // EightDirections
                if (PowerupScreen.isPowerup3Owned && num_powers < 2){
                    if (message1==-1){
                        power_up_name1.setText("Power Up - Shoot All 8 Directions");
                        message1 = 2;
                    }
                    else{
                        power_up_name2.setText("Power Up - Shoot All 8 Directions");
                        message2 = 2;
                    }
                    ships.get(0).shoot8Directions(true);
                    powers[2] = durations[2];
                    num_powers++;
                }
                else if (powers[2] > 0 && num_powers < 2)
                    send_warn(1);
                else if (num_powers==2)
                    send_warn(2);
                break;
            case 3:
                // UnlimitedAmmo
                if (PowerupScreen.isPowerup4Owned && num_powers < 2){
                    if (message1==-1){
                        power_up_name1.setText("Power Up - Unlimited Ammo");
                        message1 = 3;
                    }
                    else{
                        power_up_name2.setText("Power Up - Unlimited Ammo");
                        message2 = 3;
                    }
                    ships.get(0).unlimitedAmmo(true);
                    powers[3] = durations[3];
                    num_powers++;
                }

                else if (powers[3] > 0 && num_powers < 2)
                    send_warn(1);

                else if (num_powers==2)
                    send_warn(2);

                break;
            case 4:
                // FreezeEnemies
                if (PowerupScreen.isPowerup5Owned && num_powers < 2){
                    if (message1==-1){
                        power_up_name1.setText("Power Up - Freeze Enemies");
                        message1 = 4;
                    }
                    else{
                        power_up_name2.setText("Power Up - Freeze Enemies");
                        message2 = 4;
                    }
                    for(int i=1; i<ships.size();i++){
                        ships.get(i).setFreeze(true);
                    }
                    powers[4] = durations[4];
                    num_powers++;
                }
                else if (powers[4] > 0 && num_powers < 2)
                    send_warn(1);
                else if (num_powers==2)
                    send_warn(2);
                break;
            case 5:
                // More ship
                if(PowerupScreen.isPowerup6Owned && num_powers < 2){
                    if (message1==-1){
                        power_up_name1.setText("Power Up - More Ships");
                        message1 = 5;
                    }
                    else{
                        power_up_name2.setText("Power Up - More Ships");
                        message2 = 5;
                    }
                    GameManager.addNPCMyShip();
                    powers[5]=5;
                    num_powers++;
                }
                else if (powers[5]>0 && num_powers < 2)
                    send_warn(1);

                else if (num_powers==2)
                    send_warn(2);
                break;
            default:
                break;
        }
    }

    private void powerUp(){
        //System.out.println("test");

        super.update();


        Table t2 = new Table();
        t2.bottom().right();
        t2.setFillParent(true);
        actors.add(t2);

        Window powerWindow = new Window("Powerups", parent.skin);
        Table powerTable = new Table();
        powerWindow.add(powerTable);
        t2.row();
        t2.add(powerWindow).right();

        if(PowerupScreen.isPowerup1Owned){
            powerTable.add(new Image(ResourceManager.getTexture("powerups/powerup1.png")));
        }
        if(PowerupScreen.isPowerup2Owned){
            powerTable.add(new Image(ResourceManager.getTexture("powerups/powerup2.png")));
        }
        if(PowerupScreen.isPowerup3Owned){
            powerTable.add(new Image(ResourceManager.getTexture("powerups/powerup3.png")));
        }
        if(PowerupScreen.isPowerup4Owned){
            powerTable.add(new Image(ResourceManager.getTexture("powerups/powerup4.png")));
        }
        if(PowerupScreen.isPowerup5Owned){
            powerTable.add(new Image(ResourceManager.getTexture("powerups/powerup5.png")));
        }
        if(PowerupScreen.isPowerup6Owned){
            powerTable.add(new Image(ResourceManager.getTexture("powerups/powerup6.png")));
        }



        ArrayList<Ship> ships = GameManager.getShips();
        float threshold = 0.1f;
        interval += Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            // TempImmortality
            if (PowerupScreen.isPowerup1Owned && num_powers < 2) { // If owned
                if (message1 == -1) {
                    power_up_name1.setText("Power Up - Temporary Immortality");
                    message1 = 0;
                } else {
                    power_up_name2.setText("Power Up - Temporary Immortality");
                    message2 = 0;
                }
                ships.get(0).tempImmortality(true);
                PowerupScreen.isPowerup1Owned = false;
                powers[0] = durations[0];
                num_powers++;
                interval = 0;
            } else if (powers[0] > 0 && num_powers < 2) {
                if (interval>threshold){
                    send_warn(1);
                    interval = 0;
                }
            } else if (num_powers==2) {
                if (interval>threshold){
                    send_warn(2);
                    interval = 0;
                }
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            // BiggerDamage
            if (PowerupScreen.isPowerup2Owned && num_powers < 2){
                if (message1==-1){
                    power_up_name1.setText("Power Up - Bigger Damage");
                    message1 = 1;
                }
                else{
                    power_up_name2.setText("Power Up - Bigger Damage");
                    message2 = 1;
                }
                ships.get(0).biggerDamage(true);
                PowerupScreen.isPowerup2Owned = false;
                powers[1] = durations[1];
                num_powers++;
                interval = 0;
            }
            else if (powers[1] > 0 && num_powers < 2) {
                if (interval>threshold){
                    send_warn(1);
                    interval = 0;
                }
            } else if (num_powers==2) {
                if (interval>threshold){
                    send_warn(2);
                    interval = 0;
                }
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            // EightDirections
            if (PowerupScreen.isPowerup3Owned && num_powers < 2){
                if (message1==-1){
                    power_up_name1.setText("Power Up - Shoot All 8 Directions");
                    message1 = 2;
                }
                else{
                    power_up_name2.setText("Power Up - Shoot All 8 Directions");
                    message2 = 2;
                }
                ships.get(0).shoot8Directions(true);
                PowerupScreen.isPowerup3Owned = false;
                powers[2] = durations[2];
                num_powers++;
                interval =0;
            }
            else if (powers[2] > 0 && num_powers < 2) {
                if (interval>threshold){
                    send_warn(1);
                    interval = 0;
                }
            } else if (num_powers==2) {
                if (interval>threshold){
                    send_warn(2);
                    interval = 0;
                }
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
            // UnlimitedAmmo
            if (PowerupScreen.isPowerup4Owned && num_powers < 2){
                if (message1==-1){
                    power_up_name1.setText("Power Up - Unlimited Ammo");
                    message1 = 3;
                }
                else{
                    power_up_name2.setText("Power Up - Unlimited Ammo");
                    message2 = 3;
                }
                ships.get(0).unlimitedAmmo(true);
                PowerupScreen.isPowerup4Owned = false;
                powers[3] = durations[3];
                num_powers++;
                interval = 0;
            }

            else if (powers[3] > 0 && num_powers < 2) {
                if (interval>threshold){
                    send_warn(1);
                    interval = 0;
                }
            } else if (num_powers==2) {
                if (interval>threshold){
                    send_warn(2);
                    interval = 0;
                }
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
            // FreezeEnemies
            if (PowerupScreen.isPowerup5Owned && num_powers < 2){
                if (message1==-1){
                    power_up_name1.setText("Power Up - Freeze Enemies");
                    message1 = 4;
                }
                else{
                    power_up_name2.setText("Power Up - Freeze Enemies");
                    message2 = 4;
                }
                for(int i=1; i<ships.size();i++){
                    ships.get(i).setFreeze(true);
                }
                PowerupScreen.isPowerup5Owned = false;
                powers[4] = durations[4];
                num_powers++;
                interval = 0;
            }
            else if (powers[4] > 0 && num_powers < 2) {
                if (interval>threshold){
                    send_warn(1);
                    interval = 0;
                }
            } else if (num_powers==2) {
                if (interval>threshold){
                    send_warn(2);
                    interval = 0;
                }
            }

        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
            // More ship
            if(PowerupScreen.isPowerup6Owned && num_powers < 2){
                if (message1==-1){
                    power_up_name1.setText("Power Up - More Ships");
                    message1 = 5;
                }
                else{
                    power_up_name2.setText("Power Up - More Ships");
                    message2 = 5;
                }
                GameManager.addNPCMyShip();
                PowerupScreen.isPowerup6Owned = false;
                powers[5]=5;
                num_powers++;
                interval = 0;
            }
            else if (powers[5]>0 && num_powers < 2){
                if (interval>threshold){
                    send_warn(1);
                    interval = 0;
                }
            }
            else if (num_powers==2) {
                if (interval>threshold){
                    send_warn(2);
                    interval = 0;
                }
            }
        }



        for(int i=0 ; i<6 ; i++){
            if (powers[i]>=5){
                powers[i] -= Gdx.graphics.getDeltaTime();

            }
            else if( powers[i]>0 && powers[i]<5){
                powers[i] -= Gdx.graphics.getDeltaTime();
                switch (i){
                    case 0:
                        ships.get(0).tempImmortality(false);
                        break;
                    case 1:
                        ships.get(0).biggerDamage(false);
                        break;
                    case 2:
                        ships.get(0).shoot8Directions(false);
                        break;
                    case 3:
                        ships.get(0).unlimitedAmmo(false);
                        break;
                    case 4:
                        for(int j=1; j<ships.size();j++)
                            ships.get(j).setFreeze(false);
                        break;
                    default:
                        break;
                }
            }
            else if( powers[i]<0){
                powers[i] = 0;
                num_powers--;
            }
        }


        if(message1!=-1){
            if (powers[message1]>5)
                power_up_time1.setText(String.valueOf((int)powers[message1]-5));
            else if(powers[message1]<0){
                power_up_name1.setText("");
                power_up_time1.setText("");
                message1 = -1;
            }
            else{
                power_up_time1.setText("TIME UP");
                if (message1==5)
                    power_up_time1.setText("ACTIVATED");
            }
        }

        if(message2!=-1){
            if (powers[message2]>5)
                power_up_time2.setText(String.valueOf((int)powers[message2]-5));
            else if(powers[message2]<0){
                power_up_name2.setText("");
                power_up_time2.setText("");
                message2 = -1;
            }
            else{
                power_up_time2.setText("TIME UP");
                if (message2==5)
                    power_up_time2.setText("ACTIVATED");
            }
        }

        if (warn1_time>0){
            warn1_time -= Gdx.graphics.getDeltaTime();
        }
        else if (warn1_time<0){
            warn1_time = 0;
            warn1.setText("");
        }

        if (warn2_time>0){
            warn2_time -= Gdx.graphics.getDeltaTime();
        }
        else if (warn2_time<0){
            warn2_time = 0;
            warn2.setText("");
        }
    }
}

