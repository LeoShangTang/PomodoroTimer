package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


// Represents a log of taskQueue events and implements logEvents
public class EventLog implements Iterable<Event> {

    private static EventLog theLog;
    private Collection<Event> events;


    //EFFECTS: Constructor prevents external construction
    private EventLog() {
        events = new ArrayList<Event>();
    }

    //EFFECTS: Gets instance of EventLog. If null, constructs EventLog first
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    //MODIFIES: this
    //EFFECTS: Adds event to logEvent
    public void logEvent(Event e) {
        events.add(e);
    }

    //MODIFIES: this
    //EFFECTS: clears logEvent
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
