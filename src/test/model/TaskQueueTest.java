package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    }

    @Test
    public void testGetIndexOfTask() {

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
