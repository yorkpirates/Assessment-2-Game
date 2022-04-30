package io.team9.game.tests.Managers;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.College;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.QuestManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.Quests.KillQuest;
import com.mygdx.game.Quests.LocateQuest;
import com.mygdx.game.Quests.Quest;
import io.team9.game.tests.GdxTestRunner;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test.*;

import java.lang.annotation.Repeatable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class QuestManagerTest {

    @Test
    public void completedTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();
        int id_map = ResourceManager.addTileMap("Map.tmx");
        GameManager.SpawnGame(id_map);
        QuestManager manager= new QuestManager();
        Vector2 location = new Vector2(50,50);

        Quest quest = new  LocateQuest(location,5);
        manager.addQuest(quest);

        assertTrue("Should be quests",manager.anyQuests());
        Player player = GameManager.getPlayer();

        quest = manager.currentQuest();
        if(quest instanceof LocateQuest){
            LocateQuest l = (LocateQuest) quest;
            location = l.getLocation();
            player.setPosition(location.x,location.y);


        }
        else{

            KillQuest q = (KillQuest) quest;

            for(College c :GameManager.colleges){

                System.out.println(c.getComponent(Pirate.class).getFaction());
                c.getComponent(Pirate.class).kill();
                c.kill();
            }
        }
        System.out.println(quest.getDescription());
        manager.checkCompleted();
        assertTrue(player.getPlunder()>0);

    }

    public void reloadTest(){


    }
}
