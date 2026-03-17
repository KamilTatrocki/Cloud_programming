package org.example.model;

public class Typ4Event extends BaseEvent {
    private String message4;

    public Typ4Event() {
        super();
        this.message4 = "Default Typ4 Message";
    }

    public Typ4Event(String message4) {
        super();
        this.message4 = message4;
    }

    public String getMessage4() {
        return message4;
    }

    public void setMessage4(String message4) {
        this.message4 = message4;
    }
}