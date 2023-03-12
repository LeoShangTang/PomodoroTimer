package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests task class
public class TaskTest {

    Task t1;
    Task t2;
    Task t3;

    @BeforeEach
    public void beforeEach() {
        t1 = new Task("Math","Work",1);
        t2 = new Task("Youtube","Break",2);
        t3 = new Task("Science","Work",5);
    }

    @Test
    public void testChangeNumberOfTime() {
        t1.changeNumberOfTimes(5);
        assertEquals(5,t1.getNumberOfTimes());
        t2.changeNumberOfTimes(3);
        assertEquals(3,t2.getNumberOfTimes());
        t3.changeNumberOfTimes(1);
        assertEquals(1,t3.getNumberOfTimes());
    }

    @Test
    public void testGetTimerType() {
        assertEquals("Work",t1.getTimerType());
        assertEquals("Break",t2.getTimerType());
        assertEquals("Work",t3.getTimerType());
    }

}
