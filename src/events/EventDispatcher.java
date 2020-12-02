package events;

import java.util.ArrayList;
import java.util.HashMap;
import utils.Pair;

public class EventDispatcher {

    private final HashMap<Event.Type, ArrayList<Pair<EventHandler, EventFilter>>> handlers = new HashMap<>();

    public void dispatch(Event event) {
        for (Pair<EventHandler, EventFilter> pair: handlers.get(event.getType())) {
            if (pair.getValue().filterEvent(event))
                pair.getKey().handleEvent(event);
        }
    }

    public void registerListener(Event.Type eventType, EventHandler handler, EventFilter filter) {
        if (!handlers.containsKey(eventType)) {
            handlers.put(eventType, new ArrayList<>());
        }
        handlers.get(eventType).add(new Pair<>(handler, filter));
    }

    public void registerListener(Event.Type eventType, EventHandler handler) {
        registerListener(eventType, handler, new EventFilter());
    }
}
