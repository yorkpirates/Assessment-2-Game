package io.team9.game.tests.Entitys;
import com.mygdx.game.Components.PlayerController;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.DifficultyManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class PlayerTest {

    @Test
    public void playerSpawnTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        Player p = new Player();
        DifficultyManager.SelectEasy();
        p.updateHealth();
        assertEquals("Easy health should be 200",200,p.getHealth());
        DifficultyManager.SelectNormal();
        p.updateHealth();
        assertEquals("Normal health should be 100",100,p.getHealth());
        DifficultyManager.SelectHard();
        p.updateHealth();
        assertEquals("Hard health should be 50",50,p.getHealth());
    }
}
