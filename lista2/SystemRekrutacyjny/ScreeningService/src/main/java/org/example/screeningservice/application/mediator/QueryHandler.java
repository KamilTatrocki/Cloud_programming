package org.example.screeningservice.application.mediator;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}
