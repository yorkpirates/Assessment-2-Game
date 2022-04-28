package io.team9.game.tests.AI;
import com.mygdx.game.AI.Node;
import com.mygdx.game.AI.Path;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class PathTest {

    @Test
    public void pathCreationTest(){
        Node a = new Node(0,0);
        Node b = new Node(10,10);
        Path path = new Path(a,b);

        assertEquals("Get from node",a,path.getFromNode());
        assertEquals("Get to node",b,path.getToNode());
    }
}
