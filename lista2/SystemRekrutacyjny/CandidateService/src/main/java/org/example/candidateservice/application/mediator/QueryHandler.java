package org.example.candidateservice.application.mediator;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}
