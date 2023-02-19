package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    Task t1;
    Task t2;
    Task t3;

    @BeforeEach
    public void beforeEach() {
        t1 = new Task("Math","Work Timer",1);
        t2 = new Task("Youtube","Break Timer",2);
        t3 = new Task("Science","Work Timer",5);
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

}
