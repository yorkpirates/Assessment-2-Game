package com.mygdx.game.Entitys;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.RigidBody;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.Physics.CollisionCallBack;
import com.mygdx.game.Physics.CollisionInfo;
import com.mygdx.game.Physics.PhysicsBodyType;

import java.util.Objects;

/**
 * Base class for game ships, Player & NPC.
 */
public class Monster extends Entity implements CollisionCallBack {
    private static int monsterCount = 0;
    public static ObjectMap<Vector2, String> directions;

    private final Vector2 currentDir;
    RigidBody rb;
    int count = 0;
    /**
     * Creates a ship entity, containing Transform, Renderable, RigidBody, and Pirate components.
     */
    public Monster() {
        super(4);
        currentDir = new Vector2();
        setName("Storm (" + monsterCount++ + ")"); // each ship has a unique name
        if (directions == null) {
            directions = new ObjectMap<>();
            directions.put(new Vector2(0, 1), "-up");
            directions.put(new Vector2(0, -1), "-down");
            directions.put(new Vector2(-1, 0), "-right");
            directions.put(new Vector2(1, 0), "-left");
//            directions.put(new Vector2(1, 1), "-ur");
//            directions.put(new Vector2(-1, 1), "-ul");
//            directions.put(new Vector2(1, -1), "-dr");
//            directions.put(new Vector2(-1, -1), "-dl");
        }

        Transform t = new Transform();
        t.setPosition(400, 600);
        Renderable r = new Renderable("monster-up.png", RenderLayer.Transparent) ;
        rb = new RigidBody(PhysicsBodyType.Dynamic, r, t);
        rb.setCallback(this);

        addComponents(t, r, rb);
    }

    /**
     * gets the string representation of the direction the ship is facing
     *
     * @param dir the vector dir the ship is facing
     * @return the string representation of the direction
     */
    private String getDirection(Vector2 dir) {
        if (!currentDir.equals(dir) && directions.containsKey(dir)) {
            currentDir.set(dir);
            return directions.get(dir);
        }
        return "";
    }

    /**
     * will rotate the ship to face the direction (just changes the sprite doesn't actually rotate)
     *
     * @param dir the dir to face (used to get the correct sprite from the texture atlas
     */
    public void setDirection(Vector2 dir) {
        setDirection(getDirection(dir));
    }

    /**
     * will rotate the ship to face the direction (just changes the sprite doesn't actually rotate)
     *
     * @param direction the dir to face (used to get the correct sprite from the texture atlas
     */
    public void setDirection(String direction) {
        if (Objects.equals(direction, "")) {
            return;
        }
        Renderable r = getComponent(Renderable.class);
        Sprite s = ResourceManager.getSprite("monster" + direction + ".png");

        try {
            r.setTexture(s);
        } catch (Exception ignored) {

        }
    }


    public Vector2 getPosition() {
        return getComponent(Transform.class).getPosition().cpy();
    }

    private void moveMonster(Vector2 dir) {
        rb.setVelocity(dir);
    }


    @Override
    public void update() {
        super.update();

        float x = this.getPosition().x - GameManager.ships.get(0).getPosition().x;
        float y = this.getPosition().y - GameManager.ships.get(0).getPosition().y;
        Vector2 dir = new Vector2(x / -3, y / -3);
        if (y==0 && x==0){
            this.setDirection(new Vector2( x, y));
            System.out.println(x + " **** " + y);
        } else if (x < y) {
            this.setDirection(new Vector2( Math.round(x/y), Math.round(y/y)));
            System.out.println(Math.round(x/y) + " **** " + Math.round(y/y));
        } else {
            this.setDirection(new Vector2( Math.round(x/x), Math.round(y/x)));
            System.out.println(Math.round(x/x) + " **** " + Math.round(y/y));
        }
        if (count == 200) {
            moveMonster(dir);
            count = 0;
        }
        count++;
    }


    @Override
    public void BeginContact(CollisionInfo info) {
        EnterTrigger(info);
    }

    @Override
    public void EndContact(CollisionInfo info) {
    }

    /**
     * if called on a Player against anything else call it on the other thing
     */
    @Override
    public void EnterTrigger(CollisionInfo info) {
        if (this instanceof Monster && !(info.a instanceof Monster) && !(info.a == null)) {
            ((CollisionCallBack) info.a).EnterTrigger(info);
        }
    }

    /**
     * if called on a Player against anything else call it on the other thing
     */
    @Override
    public void ExitTrigger(CollisionInfo info) {
        if (this instanceof Monster && !(info.a instanceof Monster) && !(info.a == null)) {
            ((CollisionCallBack) info.a).ExitTrigger(info);
        }
    }
}