package com.mygdx.game.Entitys;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Faction;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.utils.Utilities;

import java.util.ArrayList;

/**
 * Defines a college and its associated buildings.
 */
public class College extends Entity {
    private static ArrayList<String> buildingNames;
    private final ArrayList<Building> buildings;
    private int i;
    private Vector2 target;

    private boolean active;

    public College() {
        super();
        buildings = new ArrayList<>();
        buildingNames = new ArrayList<>();
        buildingNames.add("big");
        buildingNames.add("small");
        buildingNames.add("clock");
        i = 0;
        Transform t = new Transform();
        Pirate p = new Pirate();
        addComponents(t, p);
        active = true;
    }

    /**
     * Creates a college at the location associated with the given faction id.
     *
     * @param factionId numerical id of the faction
     */
    public College(int factionId) {
        this();
        Faction f = GameManager.getFaction(factionId);
        Transform t = getComponent(Transform.class);
        t.setPosition(f.getPosition());
        Pirate p = getComponent(Pirate.class);
        p.setFactionId(factionId);
        spawn(f.getColour());
    }

    /**
     * Randomly populates the college radius with buildings.
     *
     * @param colour used to pull the appropriate flag sprite
     */
    private void spawn(String colour) {
        JsonValue collegeSettings = GameManager.getSettings().get("college");
        float radius = collegeSettings.getFloat("spawnRadius");
        // radius = Utilities.tilesToDistance(radius) * BUILDING_SCALE;
        final Vector2 origin = getComponent(Transform.class).getPosition();
        ArrayList<Vector2> posList = new ArrayList<>();
        posList.add(new Vector2(0, 0));

        for (int i = 0; i < collegeSettings.getInt("numBuildings"); i++) {
            Vector2 pos = Utilities.randomPos(-radius, radius);
            pos = Utilities.floor(pos);

            if (!posList.contains(pos)) {
                posList.add(pos);

                pos = Utilities.tilesToDistance(pos).add(origin);

                Building b = new Building();
                buildings.add(b);

                String b_name = Utilities.randomChoice(buildingNames, 0);

                b.create(pos, b_name);
            }


        }
        Building flag = new Building(true);
        buildings.add(flag);
        flag.create(origin, colour);
    }

    /**
     * True as long as unharmed buildings remain, false otherwise.
     */
    public void isAlive() {
        boolean res = false;
        for (int i = 0; i < buildings.size() - 1; i++) {
            Building b = buildings.get(i);
            if (b.isAlive()) {
                res = true;
            }
        }
        if (!res) {
            if(getComponent(Pirate.class).isAlive()){
                GameManager.getPlayer().getComponent(Pirate.class).addPoints(10000);
                GameManager.getPlayer().getComponent(Pirate.class).addPlunder(1000);
                active = false;
            }
            getComponent(Pirate.class).kill();


        }
    }

    public void shoot(Vector2 target) {
        GameManager.shoot2(this, target);
    }

    public Vector2 getPosition() {
        return getComponent(Transform.class).getPosition().cpy();
    }

    @Override
    public void update() {
        super.update();
        isAlive();

        if(active){
            if (i == 50) {
                target = new Vector2(-1 * (this.getPosition().x - GameManager.ships.get(0).getPosition().x), -1 * (this.getPosition().y - GameManager.ships.get(0).getPosition().y));
                shoot(target);
                i = 0;
            }
            i++;

        }

    }

}
