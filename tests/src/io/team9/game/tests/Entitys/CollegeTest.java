package io.team9.game.tests.Entitys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entitys.College;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class CollegeTest {

    @Test
    public void spawnAndDeathTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        GameManager.CreatePlayer();

        College college = new College(1);

        assertTrue("College should be alive",college.isAlive());

        college.kill();
        assertFalse("College shouldn't be alive", college.isAlive());

    }

    @Test
    public void positionTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();

        College college = new College(1);
        Vector2 loc = new Vector2(0,0);
        assertEquals("Has the correct coordinates for that college relating to its faction",loc,college.getPosition());
    }
}
