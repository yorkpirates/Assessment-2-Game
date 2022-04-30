package io.team9.game.tests.utils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.utils.SaveObject;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test.*;

import java.io.File;
import java.lang.annotation.Repeatable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class SaveTest {
    @Test
    public void saveLoadTest(){
        String path = "UnitTestsave.xml";
        PhysicsManager.Initialize();
        GameManager.Initialize();
        int id_map = ResourceManager.addTileMap("Map.tmx");
        GameManager.SpawnGame(id_map);

        GameManager.getPlayer().setAmmo(42);
        SaveObject.writeXMl(path);
        //Save game at the start
        saveTest();
        SaveObject.readXML(path);
        loadTest();
        //cleanup
        File file = new File(path);
        file.delete();
    }

    protected void saveTest(){
        String path = "UnitTestsave.xml";
        File file = new File(path);
        assertTrue("The file should exist",file.exists());

    }
    protected void loadTest(){
        assertEquals("Expect the value changed in saveLoadTest",42,GameManager.getPlayer().getAmmo());
        Vector2 vec =  new Vector2(805,805);
        assertEquals("Expected location 800 800",vec,GameManager.getPlayer().getComponent(Transform.class).getPosition());

    }
}
