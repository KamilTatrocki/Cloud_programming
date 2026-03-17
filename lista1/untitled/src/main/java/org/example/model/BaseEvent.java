package org.example.model;
import java.time.LocalDateTime;

abstract public class BaseEvent {
//    private LocalDateTime sendAt;
//
//    public BaseEvent(){
//        this.sendAt = LocalDateTime.now();
//    }
//
//    public LocalDateTime getSendAt() {
//        return sendAt;
//    }
//
//    public void setSendAt(LocalDateTime sendAt) {
//        this.sendAt = sendAt;
//    }
    @Override
    public String toString() {
        // Zwraca nazwę klasy i datę w czytelnym formacie
        return this.getClass().getSimpleName() ;
    }
}
