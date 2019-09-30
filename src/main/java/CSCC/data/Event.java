package CSCC.data;

import java.util.Objects;

public class Event {

    private String id;
    private String state;
    private String type;
    private String host;
    private String timestamp;

    public Event() {
    }

    public Event(String id, String state, String timestamp) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
    }

    public Event(String id, String state, String type, String host, String timestamp) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(state, event.state) &&
                Objects.equals(timestamp, event.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, timestamp);
    }
}
