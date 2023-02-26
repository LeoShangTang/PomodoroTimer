package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskQueueTest {

    Task t1 = new Task("Math","Work Timer",1);
    Task t2 = new Task("Youtube","Break Timer",2);
    Task t3 = new Task("Science","Work Timer",5);

    TaskQueue q1;
    TaskQueue q2;
    TaskQueue q3;

    @BeforeEach
    public void beforeEach() {
        q1 = new TaskQueue();
        q2 = new TaskQueue();
        q3 = new TaskQueue();
    }

    @Test
    public void testAddTask(){
        q1.addTask(t1);
        assertEquals(0,q1.getIndexOfTask("Math"));
        q1.addTask(t2);
        assertEquals(1,q1.getIndexOfTask("Youtube"));
        q1.addTask(t3);
        assertEquals(2,q1.getIndexOfTask("Science"));
        assertEquals(3,q1.getQueueLength());
    }

    @Test
    public void testRemoveTask() {
        q1.addTask(t1);
        q1.addTask(t2);
        q1.addTask(t3);

        q2.addTask(t3);

        assertEquals(3,q1.getQueueLength());
        q1.removeTask("Math",1);
        assertEquals(2,q1.getQueueLength());
        q1.removeTask("Youtube",1);
        assertEquals(2,q1.getQueueLength());
        assertEquals(1,t2.getNumberOfTimes());
        q1.removeTask("Science",6);
        assertEquals(1,q1.getQueueLength());

        // Testing removing Science with various numOfTimes
        q2.removeTask("Science",3);
        assertEquals(2,t3.getNumberOfTimes());
        assertEquals(1,q2.getQueueLength());
        q2.removeTask("Science",1);
        assertEquals(1,t3.getNumberOfTimes());
        assertEquals(1,q2.getQueueLength());
        q2.removeTask("Science",3);
        assertEquals(0,q2.getQueueLength());
    }

    @Test
    public void testRetrieveRepetitions() {
        q1.addTask(t1);
        q1.addTask(t2);
        q1.addTask(t3);
        assertEquals(1,q1.retrieveRepetitions("Math"));
        assertEquals(2,q1.retrieveRepetitions("Youtube"));
        assertEquals(5,q1.retrieveRepetitions("Science"));
    }

    @Test
    public void testMemberOfQueue() {
        assertFalse(q1.memberOfQueue("Math"));
        q1.addTask(t1);
        assertTrue(q1.memberOfQueue("Math"));
        assertFalse(q1.memberOfQueue("Science"));
        q1.addTask(t2);
        q1.addTask(t3);
        assertTrue(q1.memberOfQueue("Science"));
        assertFalse(q1.memberOfQueue("Among Us"));
    }

    @Test
    public void testEmptyQueue() {
        assertTrue(q1.emptyQueue());
        q1.addTask(t1);
        assertFalse(q1.emptyQueue());
    }

    @Test
    public void testGetIndexOfTask() {
        q1.addTask(t1);
        assertEquals(0,q1.getIndexOfTask("Math"));
        q1.addTask(t2);
        assertEquals(1,q1.getIndexOfTask("Youtube"));
        q1.addTask(t3);
        assertEquals(2,q1.getIndexOfTask("Science"));

        q2.addTask(t2);
        assertEquals(0,q2.getIndexOfTask("Youtube"));
        q2.addTask(t1);
        assertEquals(1,q2.getIndexOfTask("Math"));
        q2.addTask(t3);
        assertEquals(2,q2.getIndexOfTask("Science"));
    }

    @Test
    public void testGetQueueLength() {
        assertEquals(0,q1.getQueueLength());
        q1.addTask(t1);
        assertEquals(1,q1.getQueueLength());
        q1.addTask(t2);
        assertEquals(2,q1.getQueueLength());
        q1.addTask(t3);
        assertEquals(3,q1.getQueueLength());
    }


}
