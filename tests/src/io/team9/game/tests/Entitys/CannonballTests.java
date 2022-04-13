package io.team9.game.tests.Entitys;

import com.mygdx.game.Entitys.CannonBall;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class CannonballTests {

    @Test
    public void testKill() {
        CannonBall testBall= new CannonBall();
        testBall.kill();
        assertTrue(true);
    }

    @Test
    public void testFalse() {
        assertTrue(false);

    }
}