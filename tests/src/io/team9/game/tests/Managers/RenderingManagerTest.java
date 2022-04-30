package io.team9.game.tests.Managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Managers.DifficultyManager;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.utils.Constants;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class RenderingManagerTest {
    

    @Test
    public void testSetCamera() {
        RenderingManager manager = new RenderingManager();
        OrthographicCamera cam = new OrthographicCamera();
        manager.setCamera(cam);
        assertEquals("true", manager.getCamera(), cam);
    }




}