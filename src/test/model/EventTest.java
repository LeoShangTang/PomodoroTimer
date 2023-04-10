package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests for Event class
// ADAPTED FROM: AlarmSystem
public class EventTest {

	private Event event;
	private Date date;

	@BeforeEach
	public void runBefore() {
		event = new Event("Added Task");
		date = Calendar.getInstance().getTime();
	}
	
	@Test
	public void testEvent() {
		assertEquals("Added Task", event.getDescription());
		assertEquals(date, event.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(date.toString() + "\n" + "Added Task", event.toString());
	}
}
