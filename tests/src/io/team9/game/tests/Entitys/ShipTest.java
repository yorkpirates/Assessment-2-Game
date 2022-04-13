package io.team9.game.tests.Entitys;

import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Managers.RenderingManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class ShipTest {

    @Test
    public void testIsAlive() {
        RenderingManager.Initialize();
        Ship ship = new Ship();
        assertTrue(ship.isAlive());
    }

    @Test
    public void testFalse() {
        assertTrue(false);

    }
}