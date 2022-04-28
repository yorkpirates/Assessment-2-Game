package io.team9.game.tests.Quests;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Quests.KillQuest;
import com.mygdx.game.Quests.Quest;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class KillQuestTest {


    @Test
    public void questCompletionTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Pirate target = new Pirate();
        Quest quest = new KillQuest(target);
        assertEquals("Quest should not be completed while pirate is alive",!(target.isAlive()),quest.isCompleted());
        target.setHealth(0);
        assertEquals("Quest  be completed while pirate is dead",!(target.isAlive()),quest.isCompleted());


    }
    @Test
    public void questGetterSetterTests(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        Quest quest = new KillQuest();

        assertEquals("Getter test","Kill the college",quest.getName());
        assertEquals("Getter test","KILL KILL KILL",quest.getDescription());
        assertEquals("Getter test",100,quest.getReward());

    }
}
