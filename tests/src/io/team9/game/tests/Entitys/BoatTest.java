package io.team9.game.tests.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entitys.CannonBall;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.utils.Utilities;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    }


}
