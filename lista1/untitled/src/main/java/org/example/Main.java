package org.example;

import org.example.core.EventConsumer;
import org.example.core.EventPublisher;
import org.example.model.*;
import org.example.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Co chcesz utworzyć?");
        System.out.println("1 - Consumer Typ1Event (x2)");
        System.out.println("2 - Consumer Typ2Event ");
        System.out.println("3 - Consumer Typ3Event (z generowaniem Typ4)");
        System.out.println("4 - Consumer Typ4Event");
        System.out.println("5 - Publisher Typ1Event (3000ms) (x3 do)");
        System.out.println("6 - Publisher Typ2Event (Random 1-5s)");
        System.out.println("7 - Publisher Typ3Event (Random 2-6s)");

        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1":
                    new EventConsumer<>(Typ1Event.class, e -> logger.info("Consumer received Typ1Event"));
                    break;
                case "2":
                    new EventConsumer<>(Typ2Event.class, e -> logger.info("Consumer received Typ2Event"));
                    break;
                case "3":
                    EventPublisher<Typ4Event> typ4Pub = new EventPublisher<>(Typ4Event.class);
                    new EventConsumer<>(Typ3Event.class, e -> {
                        logger.info("Consumer received Typ3Event -> Publishing Typ4");
                        typ4Pub.publish(new Typ4Event());
                    });
                    break;
                case "4":
                    new EventConsumer<>(Typ4Event.class, e -> logger.info("Consumer received Typ4Event"));
                    break;
                case "5":
                    EventPublisher<Typ1Event> pub1 = new EventPublisher<>(Typ1Event.class);
                    new PublisherService<>(pub1, Typ1Event::new).startFixedRate(3000);
                    break;
                case "6":
                    EventPublisher<Typ2Event> pub2 = new EventPublisher<>(Typ2Event.class);
                    new PublisherService<>(pub2, Typ2Event::new).startRandomRate(1000, 5000);
                    break;
                case "7":
                    EventPublisher<Typ3Event> pub3 = new EventPublisher<>(Typ3Event.class);
                    new PublisherService<>(pub3, Typ3Event::new).startRandomRate(2000, 6000);
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
                    return;
            }
            logger.info("Uruchomiono wybrany obiekt. System działa...");
        } catch (Exception e) {
            logger.error("Błąd podczas tworzenia obiektu", e);
        }
    }
}