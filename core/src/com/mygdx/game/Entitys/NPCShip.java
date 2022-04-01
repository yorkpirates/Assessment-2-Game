package com.mygdx.game.Entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.AI.EnemyState;
import com.mygdx.game.Components.*;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.Physics.CollisionCallBack;
import com.mygdx.game.Physics.CollisionInfo;
import com.mygdx.utils.QueueFIFO;
import com.mygdx.utils.Utilities;

import java.util.Objects;
import java.util.Vector;

/**
 * NPC ship entity class.
 */
public class NPCShip extends Ship implements CollisionCallBack {
    public StateMachine<NPCShip, EnemyState> stateMachine;
    private static JsonValue AISettings;
    private final QueueFIFO<Vector2> path;
    private int timer;

    /**
     * Creates an initial state machine
     */
    public NPCShip() {
        super();
        path = new QueueFIFO<>();

        if (AISettings == null) {
            AISettings = GameManager.getSettings().get("AI");
        }

        stateMachine = new DefaultStateMachine<>(this, EnemyState.WANDER);

        setName("NPC");
        AINavigation nav = new AINavigation();

        addComponent(nav);


        RigidBody rb = getComponent(RigidBody.class);
        // rb.setCallback(this);

        JsonValue starting = GameManager.getSettings().get("starting");

        // agro trigger
        rb.addTrigger(Utilities.tilesToDistance(starting.getFloat("argoRange_tiles")), "agro");
        timer = 0;
    }

    /**
     * gets the top of targets from pirate component
     *
     * @return the top target
     */
    private Ship getTarget() {
        return getComponent(Pirate.class).getTarget();
    }

    /**
     * updates the state machine
     */
    @Override
    public void update() {
        if(!isAlive()){
            return;
        }
        super.update();
        stateMachine.update();
        AINavigation nav = getComponent(AINavigation.class);
        if(stateMachine.isInState(EnemyState.ATTACK)){
            if (timer ==100){
                Vector2 target = new Vector2(-1 * (this.getPosition().x - GameManager.ships.get(0).getPosition().x), -1 * (this.getPosition().y - GameManager.ships.get(0).getPosition().y));
                GameManager.shoot(this, target);
                
                //shoot();

                timer = 0;
            }
            else {
                timer ++;
            }


        }
        // System.out.println(getComponent(Pirate.class).targetCount());
    }

    /**
     * is meant to path find to the target but didn't work
     */
    public void goToTarget() {
        /*path = GameManager.getPath(
                Utilities.distanceToTiles(getPosition()),
                Utilities.distanceToTiles(getTarget().getPosition()));*/
    }

    /**
     * creates a new steering behaviour that will make the NPC beeline for the target doesn't factor in obstetrical
     */
    public void followTarget() {
        if (getTarget() == null || getFreeze()) {
            stopMovement();
            return;
        }
        AINavigation nav = getComponent(AINavigation.class);

        Arrive<Vector2> arrives = new Arrive<>(nav,
                getTarget().getComponent(Transform.class))
                .setTimeToTarget(AISettings.getFloat("accelerationTime"))
                .setArrivalTolerance(AISettings.getFloat("arrivalTolerance"))
                .setDecelerationRadius(AISettings.getFloat("slowRadius"));

        nav.setBehavior(arrives);
    }

    /**
     * stops all movement and sets the behaviour to null
     */
    public void stopMovement() {
        AINavigation nav = getComponent(AINavigation.class);
        nav.setBehavior(null);
        nav.stop();
    }

    /**
     * Meant to cause the npc to wander
     */
    public void wander() {

    }

    @Override
    public void BeginContact(CollisionInfo info) {

    }

    @Override
    public void EndContact(CollisionInfo info) {

    }

    /**
     * if the agro fixture hit a ship set it as the target
     *
     * @param info the collision info
     */
    @Override
    public void EnterTrigger(CollisionInfo info) {
        super.EnterTrigger(info);
        if (!(info.a instanceof Ship)) {
            return;
        }
        Ship other = (Ship) info.a;
        if (Objects.equals(other.getComponent(Pirate.class).getFaction().getName(), getComponent(Pirate.class).getFaction().getName())) {
            // is the same faction
            return;
        }
        // add the new collision as a new target
        Pirate pirate = getComponent(Pirate.class);
        pirate.addTarget(other);

    }

    /**
     * if a taget has left remove it from the potential targets Queue
     *
     * @param info collision info
     */
    @Override
    public void ExitTrigger(CollisionInfo info) {

        if (!(info.a instanceof Ship)) {
            return;
        }
        Pirate pirate = getComponent(Pirate.class);
        Ship o = (Ship) info.a;
        // remove the object from the targets list
        for (Ship targ : pirate.getTargets()) {
            if (targ == o) {
                pirate.getTargets().remove(targ);
                break;
            }
        }
    }
    @Override
    public void ShipDeath(){
        stopMovement();
        getComponent(Renderable.class).hide();


        RigidBody rb = getComponent(RigidBody.class);
        rb.removeFromPhysicsWorld();

    }
}
