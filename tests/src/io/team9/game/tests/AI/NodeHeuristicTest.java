package io.team9.game.tests.AI;
import com.mygdx.game.AI.Node;
import com.mygdx.game.AI.NodeHeuristic;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class NodeHeuristicTest {

    @Test
    public void estimateTest(){
        Node a = new Node(0,0);
        Node b = new Node(10,10);
        NodeHeuristic heuristic= new NodeHeuristic();
        assertEquals("Distance squared",200, heuristic.estimate(a,b),0.002);
    }
}
