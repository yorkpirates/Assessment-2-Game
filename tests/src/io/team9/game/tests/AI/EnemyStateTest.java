package io.team9.game.tests.AI;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.NPCShip;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.ResourceManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.mygdx.game.AI.EnemyState;
import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class EnemyStateTest {

    @Test
    public void wanderTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        NPCShip ship = new NPCShip();
        assertEquals("Ships should start in wander", EnemyState.WANDER,ship.stateMachine.getCurrentState());
        ship.getComponent(Pirate.class).addTarget(ship);
        ship.stateMachine.update();
        assertEquals("Ships should pursue if agro", EnemyState.PURSUE,ship.stateMachine.getCurrentState());
        ship.getComponent(Pirate.class).removeTarget();
        ship.stateMachine.update();
        assertEquals("Ship should return to wander",EnemyState.WANDER,ship.stateMachine.getCurrentState());
    }

    @Test
    public void PuruseTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();

        int id_map = ResourceManager.addTileMap("Map.tmx");

        GameManager.SpawnGame(id_map);
        NPCShip ship = new NPCShip();
        Ship target = new Ship();

        ship.getComponent(Pirate.class).addTarget(target);
        ship.stateMachine.update();
        ship.stateMachine.update();
        assertEquals("Ships should attack", EnemyState.ATTACK,ship.stateMachine.getCurrentState());
        target.setHealth(0);
        ship.stateMachine.update();
        assertEquals("Ship should return to wander",EnemyState.WANDER,ship.stateMachine.getCurrentState());

    }
}
