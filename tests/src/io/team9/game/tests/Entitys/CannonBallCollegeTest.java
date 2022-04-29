package io.team9.game.tests.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.RigidBody;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Entitys.CannonBall;
import com.mygdx.game.Entitys.CannonBallCollege;
import com.mygdx.game.Entitys.College;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.utils.Constants;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    @Test
    public void moveTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        College college = new College(1);
        CannonBallCollege ball = new CannonBallCollege();
        Vector2 vec = new Vector2(0,0);
        Vector2 target = new Vector2(100,100);
        ball.getComponent(Transform.class).setPosition(vec);
        System.out.println(ball.getComponent(Transform.class).getPosition());
        ball.fire(vec,target,college);

        Constants.INIT_CONSTANTS();
        for(int i =0;i<100;i++){
            PhysicsManager.update();
            ball.getComponent(RigidBody.class).update();


        }
        assertTrue("Should have equal x and y",ball.getComponent(Transform.class).getPosition().x==ball.getComponent(Transform.class).getPosition().y);
        assertTrue("Not at Start",ball.getComponent(Transform.class).getPosition() !=vec);
    }
}
