package net.slipp.common.domain.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by joenggyu0@gmail.com on 4/21/20
 * Github : http://github.com/lenkim
 */


public class DomainEventPublisher {

    private static DomainEventPublisher domainEventPublisher = new DomainEventPublisher();
    private List<DomainEvent> events = new ArrayList<>();

    public static DomainEventPublisher instance() {
        return domainEventPublisher;
    }

    public void publish(DomainEvent aDomainEvent) {
        events.add(aDomainEvent);
    }


    public List<Class<? extends DomainEvent>> getEventsClass() {

        return events.stream().map(DomainEvent::getClass).collect(toList());
    }

    public void reset() {
        events = new ArrayList<>();
    }
}
