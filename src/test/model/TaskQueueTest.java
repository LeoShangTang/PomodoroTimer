package model;

import exceptions.NegativeNumberOrZero;
import exceptions.OptionNotInList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests taskQueue class
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
        q1.addTask(t1);
        assertEquals(3,q1.getQueueLength());
        assertEquals(2,t1.getNumberOfTimes());
    }

    @Test
    public void testRemoveTask() throws NegativeNumberOrZero, OptionNotInList {
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
        assertEquals(-1,q1.retrieveRepetitions("Among us"));
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
        assertEquals(-1,q1.getIndexOfTask("Balling"));
        q1.addTask(t1);
        assertEquals(0,q1.getIndexOfTask("Math"));
        q1.addTask(t2);
        assertEquals(1,q1.getIndexOfTask("Youtube"));
        q1.addTask(t3);
        assertEquals(2,q1.getIndexOfTask("Science"));
        assertEquals(-1,q1.retrieveRepetitions("Economics"));

        q2.addTask(t2);
        assertEquals(0,q2.getIndexOfTask("Youtube"));
        q2.addTask(t1);
        assertEquals(1,q2.getIndexOfTask("Math"));
        q2.addTask(t3);
        assertEquals(2,q2.getIndexOfTask("Science"));
        assertEquals(-1,q2.getIndexOfTask("Among us"));
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

    @Test
    public void testGetTaskQueue() {
        assertEquals(0,q1.getTaskQueue().size());
        q1.addTask(t1);
        q1.addTask(t2);
        q1.addTask(t3);
        assertEquals(3,q1.getTaskQueue().size());
        assertEquals(t1,q1.getTaskQueue().getFirst());
        assertEquals(t3,q1.getTaskQueue().getLast());
    }

    @Test
    public void taskContainsAndPositive() {
        // No exceptions thrown and expecting taskQueue size to be 2 after removing full task
        q1.addTask(t1);
        q1.addTask(t2);
        q1.addTask(t3);
        try {
            q1.removeTask("Math",1);
        } catch (NegativeNumberOrZero e) {
            fail("Detected number to be negative when shouldn't have");
        } catch (OptionNotInList e) {
            fail("Option not in list when it should be in list");
        }
        assertTrue(q1.getQueueLength() == 2);
    }

    @Test
    public void taskContainsAndNegative() {
        // NegativeNumberOrZero exception thrown and expecting taskQueue size to be 3
        q1.addTask(t1);
        q1.addTask(t2);
        q1.addTask(t3);
        try {
            q1.removeTask("Math",-1);
        } catch (NegativeNumberOrZero e) {
            // caught as expected
        } catch (OptionNotInList e) {
            fail("Option not in list when it should be in list");
        }
        assertTrue(q1.getQueueLength() == 3);
    }

    @Test
    public void taskContainsAndZero() {
        // NegativeNumberOrZero exception thrown and expecting taskQueue size to be 3
        q1.addTask(t1);
        q1.addTask(t2);
        q1.addTask(t3);
        try {
            q1.removeTask("Math",0);
        } catch (NegativeNumberOrZero e) {
            // caught as expected
        } catch (OptionNotInList e) {
            fail("Option not in list when it should be in list");
        }
        assertTrue(q1.getQueueLength() == 3);
    }

    @Test
    public void taskNotContainsAndPositive() {
        // OptionNotInList exception thrown and expecting taskQueue size to be 3
        q1.addTask(t1);
        q1.addTask(t2);
        q1.addTask(t3);
        try {
            q1.removeTask("AmongUs",1);
        } catch (NegativeNumberOrZero e) {
            fail("Detected number to be negative when shouldn't have");
        } catch (OptionNotInList e) {
            // caught as expected
        }
        assertTrue(q1.getQueueLength() == 3);
    }

    @Test
    public void taskNotContainsAndNegative() {
        // OptionNotInList exception thrown and expecting taskQueue size to be 3
        q1.addTask(t1);
        q1.addTask(t2);
        q1.addTask(t3);
        try {
            q1.removeTask("AmongUs",-1);
        } catch (NegativeNumberOrZero e) {
            fail("Detected number to be negative when shouldn't have");
        } catch (OptionNotInList e) {
            // caught as expected
        }
        assertTrue(q1.getQueueLength() == 3);
    }


}
