package io.team9.game.tests.utils;
import com.mygdx.utils.QueueFIFO;
import io.team9.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class QueueTest {

    @Test
    public void sizeTest(){
        QueueFIFO<Integer> testQueue = new QueueFIFO<>();
        assertEquals(testQueue.size(),0);
        testQueue.add(1);
        testQueue.add(2);
        assertEquals(testQueue.size(),2);
        testQueue.peek();
        assertEquals(testQueue.size(),2);
        testQueue.pop();
        assertEquals(testQueue.size(),1);
        testQueue.clear();
        assertEquals(testQueue.size(),0);
    }

    @Test
    public void dataTest(){
        QueueFIFO<Integer> testQueue = new QueueFIFO<>();
        testQueue.add(1);
        testQueue.add(2);
        testQueue.add(3);
        int poped = testQueue.remove();
        assertEquals(poped,1);
         poped = testQueue.pop();
        assertEquals(poped,2);
         poped = testQueue.pop();
        assertEquals(poped,3);


    }
    @Test
    public void containsTest(){
        QueueFIFO<Integer> testQueue = new QueueFIFO<>();
        for (int i =0; i<100; i ++){
            testQueue.add(i);

        }

        assertTrue(testQueue.contains(25));
        assertTrue(testQueue.contains(2));
        assertTrue(testQueue.contains(3));
        assertFalse(testQueue.contains(101));


    }

    @Test
    public void emptyTest(){
        QueueFIFO<Integer> testQueue = new QueueFIFO<>();
        assertEquals(testQueue.poll(),null);
        assertTrue(testQueue.isEmpty());

        Exception exception = assertThrows( RuntimeException.class, () ->testQueue.pop());
        assertEquals("Queue is empty",exception.getMessage());
    }

    @Test
    public void collectionTests(){
        ArrayList<String> test = new ArrayList<>();
        test.add("Allen");
        test.add("Bruce");
        test.add("Catherine");
        QueueFIFO<String> testQueue = new QueueFIFO<>();
        testQueue.addAll(test);
        assertTrue("Should contain Test",testQueue.containsAll(test));
        assertTrue("Should contain Allen",testQueue.contains("Allen"));
        assertTrue("Should contain Bruce",testQueue.contains("Bruce"));
        assertTrue("Should contain Catherine",testQueue.contains("Catherine"));

        testQueue.add("Alice");
        testQueue.add("Beatrice");
        testQueue.retainAll(test);
        assertFalse("Should not contain Alice",testQueue.contains("Alice"));
        assertFalse("Should not contain Beatrice",testQueue.contains("Beatrice"));
        assertTrue("Should contain Allen",testQueue.contains("Allen"));
        assertTrue("Should contain Bruce",testQueue.contains("Bruce"));
        assertTrue("Should contain Catherine",testQueue.contains("Catherine"));

        testQueue.add("David");
        testQueue.add("Elizabeth");
        testQueue.removeAll(test);
        assertFalse("Should not contain Allen",testQueue.contains("Allen"));
        assertFalse("Should not contain Bruce",testQueue.contains("Bruce"));
        assertFalse("Should not contain Catherine",testQueue.contains("Catherine"));
        assertTrue("Should contain David",testQueue.contains("David"));
        assertTrue("Should contain Elizabeth",testQueue.contains("Elizabeth"));


   }



}
