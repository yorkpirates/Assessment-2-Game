package io.team9.game.tests.Components;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.PhysicsManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Vector;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class TransformTest {

    @Test
    public void positionSettingTest(){
        PhysicsManager.Initialize();
        Transform transform = new Transform();
        Vector2 vec = new Vector2();
        assertEquals("",vec,transform.getPosition());

        vec.x=5;
        vec.y=5;
        transform.setPosition(vec);
        assertEquals("Should equal vector",vec,transform.getPosition());

        vec.x=10;
        vec.y=10;
        transform.setPosition(10,10);
        assertEquals("Should equal vector",vec,transform.getPosition());

        vec.x=5;
        vec.y=5;
        transform.setPosition(vec,false);
        assertEquals("Should equal vector",vec,transform.getPosition());

        vec.x=10;
        vec.y=10;
        transform.setPosition(10,10,false);
        assertEquals("Should equal vector",vec,transform.getPosition());
    }
    @Test
    public void newLocationTest(){
        PhysicsManager.Initialize();
        Transform transform = new Transform();
        assertEquals(transform.getPosition(),transform.newLocation().getPosition());

    }


}
