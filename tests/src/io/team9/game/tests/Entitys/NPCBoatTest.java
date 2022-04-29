package io.team9.game.tests.Entitys;
import com.mygdx.game.AI.EnemyState;
import com.mygdx.game.Components.AINavigation;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.RigidBody;
import com.mygdx.game.Entitys.NPCShip;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Physics.CollisionInfo;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class NPCBoatTest {

    @Test
    public void targetingTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        NPCShip ship = new NPCShip();
        Ship target = new Ship();
        target.setFaction(2);
        ship.setFaction(1);

        CollisionInfo info = new CollisionInfo();
        info.a=target;
        info.b = ship;
        ship.EnterTrigger(info);

        assertEquals("Should return the target",ship.getComponent(Pirate.class).getTarget(),target);
        AINavigation nav = ship.getComponent(AINavigation.class);

    }

    @Test
    public void updateTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        NPCShip ship = new NPCShip();
        Ship target = new Ship();
        target.setFaction(2);
        ship.setFaction(1);

        ship.getComponent(Pirate.class).addTarget(target);
        ship.setHealth(0);
        EnemyState state = ship.stateMachine.getCurrentState();
        ship.update();
        assertEquals("StateMachine should not update when dead", state,ship.stateMachine.getCurrentState());


        ship.setHealth(100);

        ship.update();





    }
   @Test
   public void exitTest(){
       PhysicsManager.Initialize();
       GameManager.Initialize();
       NPCShip ship = new NPCShip();
       Ship target = new Ship();
       target.setFaction(2);
       ship.setFaction(1);

       CollisionInfo info = new CollisionInfo();
       info.a=target;
       info.b = ship;
       ship.EnterTrigger(info);
       ship.ExitTrigger(info);
       assertEquals(null,ship.getComponent(Pirate.class).getTarget());

   }

}
