package CSCC.data;

public class Entry {

    private String id;
    private String type;
    private String host;
    private String timeStarted;
    private String timeFinished;
    private boolean moreThan4ms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(String timeStarted) {
        this.timeStarted = timeStarted;
    }

    public String getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(String timeFinished) {
        this.timeFinished = timeFinished;
    }

    public boolean isMoreThan4ms() {
        return moreThan4ms;
    }

    public void setMoreThan4ms(boolean moreThan4ms) {
        this.moreThan4ms = moreThan4ms;
    }
}


