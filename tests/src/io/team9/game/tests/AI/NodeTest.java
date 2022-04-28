package io.team9.game.tests.AI;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AI.Node;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class NodeTest {

    @Test
    public void createNodeTest(){
        Node node = new Node(5,5);
        Vector2 vec = new Vector2(5,5);
        assertEquals(" correct position",vec,node.getPosition());

    }
    @Test
    public void positionSetterTest(){

        Node node = new Node(5,5);
        Vector2 vec = new Vector2(25,25);
        node.set(25,25);
        assertEquals(" correct position",vec,node.getPosition());

    }

}
