package events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import utils.Pair;

public class EventDispatcher extends Thread {

    private final HashMap<Event.Type, ArrayList<Pair<EventHandler, EventFilter>>> handlers = new HashMap<>();
    private List<Event> eventsList = Collections.synchronizedList(new ArrayList<>());
    private final boolean listening = true;

    public void registerListener(Event.Type eventType, EventHandler handler, EventFilter filter) {
        if (!handlers.containsKey(eventType)) {
            handlers.put(eventType, new ArrayList<>());
        }
        handlers.get(eventType).add(new Pair<>(handler, filter));
    }

    public void registerListener(Event.Type eventType, EventHandler handler) {
        registerListener(eventType, handler, new EventFilter());
    }

    public void addEvent(Event newEvent) {
        eventsList.add(newEvent);
    }

    public synchronized void run() {
        while (listening) {
            while (eventsList.size() > 0) {
                Event currentEvent = eventsList.get(0);

                try{
                    for (Pair<EventHandler, EventFilter> pair : handlers.get(currentEvent.getType())) {
                        if (pair.getValue().filterEvent(currentEvent))
                            pair.getKey().handleEvent(currentEvent);
                    }
                }
                catch(Exception e){

                }

                eventsList.remove(0);
            }
        }
    }
}
