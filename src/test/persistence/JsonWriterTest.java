package persistence;

import model.Task;
import model.TaskQueue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests data writer
// ADAPTED FROM: JsonSerializationDemo
public class JsonWriterTest {

    Task t1 = new Task("Art","Work",4);
    Task t2 = new Task("Badminton","Break",2);

    @Test
    void testWriterInvalidFile() {
        try {
            TaskQueue tq = new TaskQueue();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // IOException caught as expected
        }
    }

    @Test
    void testWriterEmptyTaskQueue() {
        try {
            TaskQueue tq = new TaskQueue();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTaskQueue.json");
            writer.open();
            writer.write(tq);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyTaskQueue.json");
            tq = reader.read();
            assertEquals(0, tq.getQueueLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTaskQueue() {
        try {
            TaskQueue tq = new TaskQueue();
            tq.addTask(t1);
            tq.addTask(t2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTaskQueue.json");
            writer.open();
            writer.write(tq);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTaskQueue.json");
            tq = reader.read();
            List<Task> tasks = tq.getTaskQueue();
            assertEquals(2, tq.getQueueLength());
            assertEquals("Art",tasks.get(0).getTaskName());
            assertEquals("Work",tasks.get(0).getTimerType());
            assertEquals(4,tasks.get(0).getNumberOfTimes());
            assertEquals("Badminton",tasks.get(1).getTaskName());
            assertEquals("Break",tasks.get(1).getTimerType());
            assertEquals(2,tasks.get(1).getNumberOfTimes());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
