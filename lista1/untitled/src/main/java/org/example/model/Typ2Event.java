package org.example.model;

public class Typ2Event extends BaseEvent {
    private String message2;

    public Typ2Event() {
        super();
        this.message2 = "Default Typ2 Message";
    }

    public Typ2Event(String message2) {
        super();
        this.message2 = message2;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}