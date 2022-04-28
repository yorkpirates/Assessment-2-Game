package io.team9.game.tests.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Entitys.Weather;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.PhysicsManager;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class WeatherTest {

    @Test
    public void createWeatherTest(){
        PhysicsManager.Initialize();
        GameManager.Initialize();

        Weather weather = new Weather();
        weather.getComponent(Transform.class).getPosition();
        Vector2 pos = new Vector2(900, 800);
        assertEquals("Should start at 900, 800",pos,weather.getComponent(Transform.class).getPosition());
    }

}
