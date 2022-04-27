package io.team9.game.tests.Entitys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entitys.Building;
import com.mygdx.game.Entitys.CannonBall;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Physics.CollisionInfo;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class BuildingTest {

    @Test
    public void CollisionTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Building building = new Building();

        assertTrue("Buildings should start alive",building.isAlive());

        CannonBall shot = new CannonBall();
        CollisionInfo info = new CollisionInfo();
        Vector2 blank = new Vector2();

        info.a= shot;
        info.b=building;
        building.EnterTrigger(info);

        assertFalse("Building should no longer be alive ",building.isAlive());

    }

    @Test
    public void flagTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Building flag = new Building(true);
        flag.destroy();
        assertTrue("Flag should never be destroyed", flag.isAlive());

    }
}
