package net.slipp.ddd.events;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by joenggyu0@gmail.com on 4/21/20
 * Github : http://github.com/lenkim
 */


public final class DomainEventPublisher {

    private static final ThreadLocal<DomainEventPublisher> domainEventPublisher = ThreadLocal.withInitial(DomainEventPublisher::new);

    public static DomainEventPublisher instance() {
        return domainEventPublisher.get();
    }


    private List<DomainEventSubscriber> subscribers;
    private boolean publishing;

    private DomainEventPublisher() {
        ensureSubscribers();
        setPublishing(false);
    }

    public void subscribe(DomainEventSubscriber aSubscriber) {
        if (!this.isPublishing()) {
            this.ensureSubscribers();

            subscribers.add(aSubscriber);
        }
    }

    public void publish(DomainEvent aDomainEvent) {
        if (isPublishing()) {
            return;
        }

        try {
            startPublishing();

            subscribers().stream()
                    .filter(s -> s.support(aDomainEvent))
                    .forEach(s -> s.handle(aDomainEvent));
        } finally {
            finishPublishing();
        }
    }

    private void finishPublishing() {
        setPublishing(false);
    }

    private void startPublishing() {
        setPublishing(true);
    }

    private void setPublishing(boolean publishing) {
        this.publishing = publishing;
    }

    private boolean isPublishing() {
        return publishing;
    }

    private void ensureSubscribers() {
        if (!this.hasSubscribers()) {
            this.setSubscribers(new ArrayList<>());
        }
    }

    private boolean hasSubscribers() {
        return this.subscribers() != null;
    }

    private void setSubscribers(List<DomainEventSubscriber> subscribers) {
        this.subscribers = subscribers;
    }


    private List<DomainEventSubscriber> subscribers() {
        return this.subscribers;
    }

    public void reset() {
        if(isPublishing()) {
            return;
        }

        this.setSubscribers(new ArrayList<>());
    }



}
