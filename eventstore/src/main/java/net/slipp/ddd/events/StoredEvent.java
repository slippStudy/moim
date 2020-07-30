package net.slipp.ddd.events;

import net.slipp.ddd.domain.DomainEvent;

import java.time.LocalDateTime;

public class StoredEvent {

    private Long id;
    private final String eventId;
    private final String eventBody;
    private final LocalDateTime occurredOn;
    private final String typeName;

    public String eventBody() {
        return this.eventBody;
    }

    public String eventId() {
        return this.eventId;
    }

    public LocalDateTime occurredOn() {
        return this.occurredOn;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;

        try {
            domainEventClass = (Class<T>) Class.forName(this.eventTypeName());
            return Serializer
                    .getInstance()
                    .deserialize(this.eventBody(), domainEventClass);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Class load error, because: "
                            + e.getMessage());
        }

    }

    public String eventTypeName() {
        return this.typeName;
    }


    public StoredEvent(Long id, String eventId, String eventBody, LocalDateTime occurredOn, String typeName) {
        this.id = id;
        this.eventId = eventId;
        this.eventBody = eventBody;
        this.occurredOn = occurredOn;
        this.typeName = typeName;
    }

    public StoredEvent(String eventId, String eventBody, LocalDateTime occurredOn, String typeName) {
        super();
        this.eventId = eventId;
        this.eventBody = eventBody;
        this.occurredOn = occurredOn;
        this.typeName = typeName;
    }

}
