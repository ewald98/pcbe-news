package events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher {

    private HashMap<Event.Type, ArrayList<EventHandler>> handlers = new HashMap<>();

    public EventDispatcher() {
        for (Event.Type eventType: Event.Type.values()) {
            handlers.put(eventType, new ArrayList<>());
        }
    }

    public void dispatch(Event event) {
        for (EventHandler handler: handlers.get(event.getType())) {
            handler.handleEvent(event);
        }
    }

    public void registerListener(Event.Type eventType, EventHandler handler) {
        handlers.get(eventType).add(handler);
    }

}
