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
import com.mygdx.utils.Utilities;

import java.util.Objects;

/**
 * Base class for game ships, Player & NPC.
 */
public class Weather extends Entity implements CollisionCallBack {
    private static int weatherCount = 0;
    public static ObjectMap<Vector2, String> shipDirections;

    private final Vector2 currentDir;
    /**
     * Creates a ship entity, containing Transform, Renderable, RigidBody, and Pirate components.
     */
    public Weather() {
        super(4);
        currentDir = new Vector2();
        setName("Storm (" + weatherCount++ + ")"); // each ship has a unique name
        if (shipDirections == null) {
            shipDirections = new ObjectMap<>();
            shipDirections.put(new Vector2(0, 1), "-up");
            shipDirections.put(new Vector2(0, -1), "-down");
            shipDirections.put(new Vector2(1, 0), "-right");
            shipDirections.put(new Vector2(-1, 0), "-left");
            shipDirections.put(new Vector2(1, 1), "-ur");
            shipDirections.put(new Vector2(-1, 1), "-ul");
            shipDirections.put(new Vector2(1, -1), "-dr");
            shipDirections.put(new Vector2(-1, -1), "-dl");
        }

        Transform t = new Transform();
        t.setPosition(800, 800);
        Renderable r = new Renderable(3, "white-up", RenderLayer.Transparent);
        RigidBody rb = new RigidBody(PhysicsBodyType.Dynamic, r, t);
        rb.setCallback(this);
        
        addComponents(t, r, rb);
    }

    /**
     * gets the string representation of the direction the ship is facing
     *
     * @param dir the vector dir the ship is facing
     * @return the string representation of the direction
     */
    private String getShipDirection(Vector2 dir) {
        if (!currentDir.equals(dir) && shipDirections.containsKey(dir)) {
            currentDir.set(dir);
            return shipDirections.get(dir);
        }
        return "";
    }

    /**
     * @return copy of the transform's position
     */
//    public Vector2 getPosition() {
//        return getComponent(Transform.class).getPosition().cpy();
//    }

//    public void setPosition(Float x,Float y){
//        getComponent(Transform.class).setPosition(x,y);
//    }

    @Override
    public void BeginContact(CollisionInfo info) {
    }

    @Override
    public void EndContact(CollisionInfo info) {
    }


    /**
     * if called on a Player against anything else call it on the other thing
     */
    @Override
    public void EnterTrigger(CollisionInfo info) {
//        if (this instanceof Weather && !(info.b instanceof Player)) {
//            ((CollisionCallBack) info.b).EnterTrigger(info);
//        }
    }

    /**
     * if called on a Player against anything else call it on the other thing
     */
    @Override
    public void ExitTrigger(CollisionInfo info) {
//        if (this instanceof Weather && !(info.b instanceof Player)) {
//            ((CollisionCallBack) info.b).ExitTrigger(info);
//        }
    }
}
