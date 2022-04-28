package io.team9.game.tests.Quests;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Quests.Quest;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.mygdx.game.Quests.LocateQuest;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class LocateQuestTest {

    @Test
    public void questCreationTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        Vector2 location = new Vector2(5,5);
        LocateQuest quest = new LocateQuest(location,2);

        assertEquals("Should return input vector",new Vector2(5,5),quest.getLocation());

    }
    @Test
    public void questCompletionTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        Player player = new Player();
        LocateQuest quest = new LocateQuest();

        assertFalse("Should return false if no vector is given",quest.checkCompleted(player));

        Vector2 location = new Vector2(50,50);

        quest = new  LocateQuest(location,5);
        player.setPosition((float) 0,(float)0);
        assertFalse("Should return false if outside of radius",quest.checkCompleted(player));

        player.setPosition(45f,45f);
        assertTrue("Should return true if inside of radius",quest.checkCompleted(player));

        player.setPosition(45f,49f);
        assertTrue("Should return true if inside of radius",quest.checkCompleted(player));
        player.setPosition( 45f,41f);
        assertTrue("Should return true if inside of radius",quest.checkCompleted(player));

    }
}
