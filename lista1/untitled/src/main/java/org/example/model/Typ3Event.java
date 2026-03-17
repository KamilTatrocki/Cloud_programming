package org.example.model;

public class Typ3Event extends BaseEvent {
    private String message3;

    public Typ3Event() {
        super();
        this.message3 = "Default Typ3 Message";
    }

    public Typ3Event(String message3) {
        super();
        this.message3 = message3;
    }

    public String getMessage3() {
        return message3;
    }

    public void setMessage3(String message3) {
        this.message3 = message3;
    }
}

