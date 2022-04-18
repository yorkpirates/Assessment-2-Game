package com.mygdx.game.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Faction;
import com.mygdx.game.Managers.DifficultyManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.utils.QueueFIFO;

/**
 * Gives the concepts of health plunder, etc. Allows for firing of cannonballs, factions, death, targets
 */
public class Pirate extends Component {
    private int factionId;
    public static int plunder;
    protected boolean isAlive;
    public  int health;

    public int points;
    public static int ammo;
    private final int attackDmg;
    private boolean isImmortality;
    private boolean isUnlimitedAmmo;
    private boolean isShootEightDirections;
    private boolean isBiggerDamage;

    /**
     * The enemy that is being targeted by the AI.
     */
    private final QueueFIFO<Ship> targets;

    public Pirate() {
        super();
        targets = new QueueFIFO<>();
        type = ComponentType.Pirate;
        plunder = GameManager.getSettings().get("starting").getInt("plunder");
        factionId = 1;
        isAlive = true;
        JsonValue starting = GameManager.getSettings().get("starting");
        health = starting.getInt("health");
        attackDmg = starting.getInt("damage");
        ammo = starting.getInt("ammo");
        health = starting.getInt("health");
        isUnlimitedAmmo = false;
        isImmortality = false;
        isShootEightDirections = false;
        isBiggerDamage = false;
        points =0;
    }

    public void addTarget(Ship target) {
        targets.add(target);
    }

    public int getPlunder() {
        return plunder;
    }

    public  void addPlunder(int money) {
        plunder += money;
    }

    public Faction getFaction() {
        return GameManager.getFaction(factionId);
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public void setImmortality(boolean state){
        isImmortality = state;
    }

    public void setUnlimitedAmmo(boolean state){
        isUnlimitedAmmo = state;
    }

    public void setShootEightDirections(boolean state){
        isShootEightDirections = state;
    }

    public void setBiggerDamage(boolean state){
        isBiggerDamage = state;
    }
    public void takeDamage(float dmg) {
        if (isImmortality)
            dmg = 0;

        health -= dmg;
        if (health <= 0) {
            health = 0;
            isAlive = false;
        }
    }

    /**
     * Will shoot a cannonball assigning this.parent as the cannonball's parent (must be Ship atm)
     *
     * @param dir the direction to shoot in
     */
    public void shoot(Vector2 dir) {
        if (ammo == 0) {
            return;
        }
        if(!isUnlimitedAmmo)
            ammo--;
        if(!isShootEightDirections)
            GameManager.shoot((Ship) parent, dir);
        else{
            GameManager.shoot((Ship) parent, new Vector2(0, 1));
            GameManager.shoot((Ship) parent, new Vector2(0, -1));
            GameManager.shoot((Ship) parent, new Vector2(1, 0));
            GameManager.shoot((Ship) parent, new Vector2(-1, 0));
            GameManager.shoot((Ship) parent, new Vector2(1, 1));
            GameManager.shoot((Ship) parent, new Vector2(-1, 1));
            GameManager.shoot((Ship) parent, new Vector2(1, -1));
            GameManager.shoot((Ship) parent, new Vector2(-1, -1));
        }
    }

    /**
     * Adds ammo
     *
     * @param newAmmo amount to add
     */
    public static void addAmmo(int newAmmo) {
        ammo += newAmmo;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int overrideHealth) {
        health = overrideHealth;
    }

    /**
     * if dst to target is less than attack range
     * target will be null if not in agro range
     */
    public boolean canAttack() {
        if (targets.peek() != null) {
            final Ship p = (Ship) parent;
            final Vector2 pos = p.getPosition();
            final float dst = pos.dst(targets.peek().getPosition());
            // withing attack range
            return dst < Ship.getAttackRange();
        }
        return false;
    }

    /**
     * if dst to target is >= attack range
     * target will be null if not in agro range
     */
    public boolean isAgro() {
        if (targets.peek() != null) {
            final Ship p = (Ship) parent;
            final Vector2 pos = p.getPosition();
            final float dst = pos.dst(targets.peek().getPosition());
            // out of attack range but in agro range
            return dst >= Ship.getAttackRange();
        }
        return false;
    }

    public Ship getTarget() {
        return targets.peek();
    }

    public void removeTarget() {
        targets.pop();
    }

    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Kill its self
     */
    public void kill() {
        health = 0;
        isAlive = false;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmo() {
        return ammo;
    }

    public int targetCount() {
        return targets.size();
    }

    public QueueFIFO<Ship> getTargets() {
        return targets;
    }

    public int getPoints(){return points;}

    public void addPoints(int value){points += value;}
}
