package io.team9.game.tests.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.*;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.game.Physics.CollisionInfo;
import com.mygdx.game.PirateGame;
import com.mygdx.utils.Utilities;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class BoatTest {
    @Test
    public void deathTest(){
        PhysicsManager.Initialize();

        Ship boat = new Ship();
        boat.setHealth(0);
        assertFalse(boat.isAlive());
        boat.setHealth(1);
        assertTrue(boat.isAlive());


    }
    @Test
    public void cannonballCollisionTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        Ship shooter = new Ship();
        shooter.setFaction(1);

        Ship hit = new Ship();
        hit.setFaction(2);
        CannonBall shot = new CannonBall();
        CollisionInfo info = new CollisionInfo();
        Vector2 blank = new Vector2();

        shot.fire(blank,blank,shooter);
        info.b= hit;
        info.a=shot;
        Integer healthBefore = hit.getHealth();
        hit.EnterTrigger(info);
        assertEquals("After being hit the health should be 5 less than before",healthBefore-5,hit.getHealth());

        shooter.setFaction(2);
        healthBefore = hit.getHealth();
        hit.EnterTrigger(info);
        assertEquals("After being hit from the same faction no health should be lost",healthBefore-0,hit.getHealth());

        hit.setHealth(5);
        shooter.setFaction(1);
        hit.EnterTrigger(info);
        assertEquals(50,shooter.getPlunder());
        assertEquals(100,shooter.getComponent(Pirate.class).getPoints());
        assertFalse(hit.isAlive());

    }
    @Test
    public void cannonballcollegeTest(){
        PhysicsManager.Initialize();
        Ship hit = new Ship();
        CannonBallCollege shot = new CannonBallCollege();
        CollisionInfo info = new CollisionInfo();
        Vector2 blank = new Vector2();
        Integer healthBefore = hit.getHealth();
        info.b= hit;
        info.a=shot;

        healthBefore = hit.getHealth();
        hit.EnterTrigger(info);
        assertEquals("Health should be 15 lower after being hit",healthBefore-15,hit.getHealth());

        hit.setHealth(15);
        hit.EnterTrigger(info);
        assertFalse(hit.isAlive());


    }

    @Test
    public void weatherCollisionTest(){
        PhysicsManager.Initialize();
        Ship hit = new Ship();
        Weather storm = new Weather();
        CollisionInfo info = new CollisionInfo();
        Vector2 blank = new Vector2();
        Integer healthBefore = hit.getHealth();
        info.a= hit;
        info.b=storm;

        healthBefore = hit.getHealth();
        hit.EnterTrigger(info);
        assertEquals("Health should be 10 lower after being hit",healthBefore-10,hit.getHealth());

        hit.setHealth(10);
        hit.EnterTrigger(info);
        assertFalse(hit.isAlive());


    }
    @Test
    public void monsterCollisionTest(){
        PhysicsManager.Initialize();
        Ship hit = new Ship();
        Monster monster = new Monster();
        CollisionInfo info = new CollisionInfo();
        Vector2 blank = new Vector2();
        Integer healthBefore = hit.getHealth();
        info.a= hit;
        info.b=monster;

        healthBefore = hit.getHealth();
        hit.EnterTrigger(info);
        assertEquals("Health should be 40 lower after being hit",healthBefore-40,hit.getHealth());

        hit.setHealth(40);
        hit.EnterTrigger(info);
        assertFalse(hit.isAlive());


    }



}
