package io.team9.game.tests.Components;

import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class PirateTest {

    @Test
    public void plunderTest() {
        PhysicsManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.addPlunder(1);
        assertEquals("Plunder should add 1", 1, pirate.getPlunder());
    }

    @Test
    public void immortalityTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.setImmortality(false);
        assertFalse(pirate.isImmortality);
        pirate.setImmortality(true);
        assertTrue(pirate.isImmortality);
    }

    @Test
    public void unlimitedAmmoTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.setUnlimitedAmmo(false);
        assertFalse(pirate.isUnlimitedAmmo);
        pirate.setUnlimitedAmmo(true);
        assertTrue(pirate.isUnlimitedAmmo);
    }

    @Test
    public void shootEightDirectionsTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.setShootEightDirections(false);
        assertFalse(pirate.isShootEightDirections);
        pirate.setShootEightDirections(true);
        assertTrue(pirate.isShootEightDirections);
    }

    @Test
    public void biggerDamageTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.setBiggerDamage(false);
        assertFalse(pirate.isBiggerDamage);
        pirate.setBiggerDamage(true);
        assertTrue(pirate.isBiggerDamage);
    }

    @Test
    public void healthTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.health = 0;
        assertEquals("Health should be 0", 0, pirate.getHealth());
        pirate.setHealth(2);
        assertEquals("Health should be 2", 2, pirate.getHealth());
    }

    @Test
    public void ammoTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.ammo = 0;
        assertEquals("Ammo should be 0", 0, pirate.getAmmo());
        pirate.setAmmo(2);
        assertEquals("Ammo should be 2", 2, pirate.getAmmo());
    }

    @Test
    public void pointsTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.points = 0;
        assertEquals("Points should be 0", 0, pirate.getPoints());
        pirate.addPoints(2);
        assertEquals("Points should be 2", 2, pirate.getPoints());
    }

    @Test
    public void aliveTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        assertTrue(pirate.isAlive());
    }

    @Test
    public void killTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.health = 10;
        pirate.kill();
        assertEquals("Health should be 0", 0, pirate.getHealth());
        assertFalse(pirate.isAlive());
    }

    @Test
    public void takeDamageTest() {
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate pirate = new Pirate();
        pirate.health = 10;
        pirate.takeDamage(1);
        assertEquals("Health should be 9", 9, pirate.getHealth());
    }
}