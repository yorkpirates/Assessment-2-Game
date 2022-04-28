package io.team9.game.tests.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.RigidBody;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Entitys.CannonBall;
import com.mygdx.game.Entitys.CannonBallCollege;
import com.mygdx.game.Managers.PhysicsManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class CannonBallCollegeTest {

    @Test
    public void deathTest(){
        PhysicsManager.Initialize();
        CannonBallCollege ball = new CannonBallCollege();
        ball.kill();
        ball.update();
        Vector2 loc = new Vector2(10005,10005);
        assertEquals(loc,ball.getComponent(Transform.class).getPosition());
        loc.x=0;
        loc.y=0;
        assertEquals(loc,ball.getComponent(RigidBody.class).getVelocity());
    }
}
