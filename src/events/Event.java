package events;

public class Event {

    // For the moment, we're doing only these three types.
    public enum Type {
        PUBLISHED,
        UPDATED,
        DELETED
    }

    private Type type;
    boolean handled;

    protected Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
