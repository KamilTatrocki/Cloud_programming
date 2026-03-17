package org.example.service;


import org.example.core.EventPublisher;
import org.example.model.BaseEvent;


import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class PublisherService<T extends BaseEvent> {

    private final EventPublisher<T> publisher;
    private final Supplier<T> eventSupplier;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final Random random = new Random();

    public PublisherService(EventPublisher<T> publisher, Supplier<T> eventSupplier) {
        this.publisher = publisher;
        this.eventSupplier = eventSupplier;
    }

    public void startFixedRate(long intervalMs) {
        scheduler.scheduleAtFixedRate(() -> publisher.publish(eventSupplier.get()), 0, intervalMs, TimeUnit.MILLISECONDS);
    }

    public void startRandomRate(long minMs, long maxMs) {
        scheduleNextRandom(minMs, maxMs);
    }

    private void scheduleNextRandom(long minMs, long maxMs) {
        long delay = minMs + (long) (random.nextDouble() * (maxMs - minMs));
        scheduler.schedule(() -> {
            publisher.publish(eventSupplier.get());
            scheduleNextRandom(minMs, maxMs);
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}