package org.example.feedbackservice.application.mediator;

public interface Mediator {
    <R> R send(Command<R> command);
    <R> R send(Query<R> query);
}
