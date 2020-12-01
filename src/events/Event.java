package events;

public class Event {

    // maybe move this to NewsEvent to make events more standalone?
    public enum Type {
        PUBLISHED,  // new news article is published
        UPDATED,    // news article is modified or deleted.
        READ
    }

    private Type type;

    protected Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
