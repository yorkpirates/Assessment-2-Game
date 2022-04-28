package io.team9.game.tests.Entitys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Entitys.Chest;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class ChestTest {

    @Test
    public void createChestTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Chest chest = new Chest();
        chest.getComponent(Transform.class).getPosition();
        Vector2 pos = new Vector2(0,0);
        assertEquals("Should start at 0,0",pos,chest.getComponent(Transform.class).getPosition());
        pos.x=50;
        pos.y=50;
        chest.setPosition(pos);
        assertEquals("Should be at the vectors location",pos,chest.getComponent(Transform.class).getPosition());

    }

}
