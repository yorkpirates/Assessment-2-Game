import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class WeatherTests {

    @Test
    public void testTrue() {
        assertTrue(true);
    }

    @Test
    public void testFalse() {
        assertTrue(false);
    }
}