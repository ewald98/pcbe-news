package events;

abstract public class Event {

    public interface Type {}

    private Type type;

    protected Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
