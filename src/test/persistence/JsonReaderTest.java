package persistence;

import model.Task;
import model.TaskQueue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests data reader
// ADAPTED FROM: JsonSerializationDemo
public class JsonReaderTest {

    @Test
    void testReaderInvalidFile() {
        JsonReader reader = new JsonReader("./data/ImInYourWalls.json");
        try {
            TaskQueue tq = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // Caught IO exception as expected
        }
    }

    @Test
    void testReaderEmptyTaskQueue() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTaskQueue.json");
        try {
            TaskQueue tq = reader.read();
            assertEquals(0, tq.getQueueLength());
        } catch (IOException e) {
            fail("File could not be read");
        }
    }

    @Test
    void testReaderGeneralTaskQueue() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTaskQueue.json");
        try {
            TaskQueue tq = reader.read();
            List<Task> tasks = tq.getTaskQueue();
            assertEquals(2, tq.getQueueLength());
            assertEquals("Youtube",tasks.get(0).getTaskName());
            assertEquals("Break",tasks.get(0).getTimerType());
            assertEquals(2,tasks.get(0).getNumberOfTimes());
            assertEquals("Science",tasks.get(1).getTaskName());
            assertEquals("Work",tasks.get(1).getTimerType());
            assertEquals(5,tasks.get(1).getNumberOfTimes());
        } catch (IOException e) {
            fail("File could not be read");
        }
    }
}
