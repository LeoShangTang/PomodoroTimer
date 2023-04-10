package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for EventLog class
// ADAPTED FROM: AlarmSystem
public class EventLogTest {

	private Event event1;
	private Event event2;
	private Event event3;
	
	@BeforeEach
	public void loadEvents() {
		event1 = new Event("AmongUs1");
		event2 = new Event("AmongUs2");
		event3 = new Event("AmongUs3");
		EventLog eventLog = EventLog.getInstance();
		eventLog.logEvent(event1);
		eventLog.logEvent(event2);
		eventLog.logEvent(event3);
	}
	
	@Test
	public void testLogEvent() {	
		List<Event> log = new ArrayList<Event>();
		
		EventLog eventLog = EventLog.getInstance();
		for (Event next : eventLog) {
			log.add(next);
		}
		
		assertTrue(log.contains(event1));
		assertTrue(log.contains(event2));
		assertTrue(log.contains(event3));
	}

	@Test
	public void testClear() {
		EventLog eventLog = EventLog.getInstance();
		eventLog.clear();
		Iterator<Event> itr = eventLog.iterator();
		assertTrue(itr.hasNext());
		assertEquals("Event log cleared.", itr.next().getDescription());
		assertFalse(itr.hasNext());
	}
}
