package events;

import java.util.ArrayList;
import java.util.HashMap;

import utils.Pair;

public class EventDispatcher {

    private final HashMap<Event.Type, ArrayList<Pair<EventHandler, EventFilter>>> handlers = new HashMap<>();
    private ArrayList<Event> eventsList = new ArrayList<>();
    private final boolean listening = true;

    public void dispatch(Event event) {
        for (Pair<EventHandler, EventFilter> pair : handlers.get(event.getType())) {
            if (pair.getValue().filterEvent(event))
                pair.getKey().handleEvent(event);
        }
    }

    public synchronized void registerListener(Event.Type eventType, EventHandler handler, EventFilter filter) {
        if (!handlers.containsKey(eventType)) {
            handlers.put(eventType, new ArrayList<>());
        }
        handlers.get(eventType).add(new Pair<>(handler, filter));
    }

    public synchronized void registerListener(Event.Type eventType, EventHandler handler) {
        registerListener(eventType, handler, new EventFilter());
    }

    public synchronized void addEvent(Event newEvent) {
        eventsList.add(newEvent);
    }

    public synchronized void startListening()
    {
        while(listening)
        {
            while(eventsList.size() > 0)
            {
                Event currentEvent = eventsList.get(0);

                // TODO: implement event handling

                eventsList.remove(0);
            }
        }
    }
}
