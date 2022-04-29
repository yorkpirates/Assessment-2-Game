package io.team9.game.tests.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.*;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Physics.CollisionInfo;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static javax.swing.text.StyleConstants.getComponent;
import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ShipTest {
    @Test
    public void ImmortalityTest() {
        PhysicsManager.Initialize();

        Ship boat = new Ship();

        boat.tempImmortality(true);
        assertTrue(boat.getComponent(Pirate.class).getImmortality());

        boat.tempImmortality(false);
        assertFalse(boat.getComponent(Pirate.class).getImmortality());
    }

    @Test
    public void UnlimtedAmmoTest() {
        PhysicsManager.Initialize();

        Ship boat = new Ship();

        boat.unlimitedAmmo(true);
        assertTrue(boat.getComponent(Pirate.class).getUnlimitedAmmo());

        boat.unlimitedAmmo(false);
        assertFalse(boat.getComponent(Pirate.class).getUnlimitedAmmo());
    }

    @Test
    public void shoot8DirectionsTest() {
        PhysicsManager.Initialize();

        Ship boat = new Ship();

        boat.shoot8Directions(true);
        assertTrue(boat.getComponent(Pirate.class).getShootEightDirections());

        boat.shoot8Directions(false);
        assertFalse(boat.getComponent(Pirate.class).getShootEightDirections());
    }

    @Test
    public void biggerDamageTest() {
        PhysicsManager.Initialize();

        Ship boat = new Ship();

        boat.biggerDamage(true);
        assertTrue(boat.getComponent(Pirate.class).getBiggerDamage());

        boat.biggerDamage(false);
        assertFalse(boat.getComponent(Pirate.class).getBiggerDamage());
    }

    @Test
    public void freezeTest() {
        PhysicsManager.Initialize();

        Ship boat = new Ship();

        boat.setFreeze(true);
        assertTrue(boat.getFreeze());

        boat.setFreeze(false);
        assertFalse(boat.getFreeze());
    }

}
