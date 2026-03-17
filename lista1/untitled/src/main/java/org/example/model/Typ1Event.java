package org.example.model;

public class Typ1Event extends BaseEvent {
    private String message1;

    public Typ1Event() {
        super();
        this.message1 = "Default Typ1 Message";
    }

    public Typ1Event(String message1) {
        super();
        this.message1 = message1;
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }
}
