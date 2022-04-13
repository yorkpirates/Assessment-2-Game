package io.team9.game.tests.utils;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entitys.CannonBall;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.utils.Utilities;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class UtilitiesTest {
    @Test
    public void angleToVectorTest() {
        Vector2 v = new Vector2(0,0);
        Vector2 a = new Vector2((float)-0.89399666,(float)-0.44807363);
        assertEquals(Utilities.angleToVector(v,90),a);

    }

    @Test
    public void roundTest(){
        Vector2 v = new Vector2(0,0);
        Vector2 a = new Vector2(0,0);
        assertEquals(Utilities.round(v),a);
        v.x= (float)0.4999;
        v.y= (float)0.4999;
        assertEquals("Round down",Utilities.round(v),a);
        v.x= (float)-0.5;
        v.y= (float)-0.5;
        assertEquals("Round Up",Utilities.round(v),a);

    }

    @Test
    public void RandomPosTest(){
        float max = 17;
        float min =-5;
        Vector2 v = Utilities.randomPos(min,max);
        assertTrue((min<=v.x)&(v.x<max)&(min<=v.y)&(v.y<max) );
         v = Utilities.randomPos(min,max);
        assertTrue((min<=v.x)&(v.x<max)&(min<=v.y)&(v.y<max) );
         v = Utilities.randomPos(min,max);
        assertTrue((min<=v.x)&(v.x<max)&(min<=v.y)&(v.y<max) );
         v = Utilities.randomPos(min,max);
        assertTrue((min<=v.x)&(v.x<max)&(min<=v.y)&(v.y<max) );
    }

    @Test
    public void ContainsTest(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Bob");
        names.add("Brian");
        names.add("Ann");
        assertTrue("Should Return true",Utilities.contains(names,"Ann"));
        assertFalse("Should Return false",Utilities.contains(names,"Lucy"));

    }



}


