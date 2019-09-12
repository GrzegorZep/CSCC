package CSCC.data;

public class Event {

private String id;
private String state;
private String type;
private String host;
private String timeStamp;

    public Event(String id, String state, String timeStamp) {
        this.id = id;
        this.state = state;
        this.timeStamp = timeStamp;
    }

    public Event(String id, String state, String type, String host, String timeStamp) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timeStamp = timeStamp;
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

    public String getTimeStamp() {
        return timeStamp;
    }
}
