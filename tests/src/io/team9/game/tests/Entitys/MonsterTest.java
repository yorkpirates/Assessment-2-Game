package io.team9.game.tests.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.*;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Physics.CollisionInfo;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class MonsterTest {
    @Test
    public void deathTest(){
        PhysicsManager.Initialize();

        Monster monster = new Monster();
        monster.setHealth(0);
        assertFalse(monster.isAlive());
        monster.setHealth(1);
        assertTrue(monster.isAlive());
    }
    @Test
    public void cannonballCollisionTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        Ship shooter = new Ship();
        shooter.setFaction(1);

        Monster hit = new Monster();
        CannonBall shot = new CannonBall();
        CollisionInfo info = new CollisionInfo();
        Vector2 blank = new Vector2();

        shot.fire(blank,blank,shooter);
        info.b= hit;
        info.a=shot;
        Integer healthBefore = hit.getHealth();
        hit.EnterTrigger(info);
        assertEquals("After being hit the health should be 5 less than before",healthBefore-5,hit.getHealth());


        hit.setHealth(5);
        shooter.setFaction(1);
        hit.EnterTrigger(info);
        assertEquals(200,shooter.getPlunder());
        assertEquals(100,shooter.getComponent(Pirate.class).getPoints());
        assertFalse(hit.isAlive());
    }
}
