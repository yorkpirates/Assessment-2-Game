package io.team9.game.tests.Entitys;
import com.mygdx.game.Components.AINavigation;
import com.mygdx.game.Components.Pirate;
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
}
