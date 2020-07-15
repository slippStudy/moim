package net.slipp.ddd.events;

import net.slipp.ddd.domain.DomainEvent;

import java.time.LocalDateTime;

public class StoredEvent {

    private Long eventId;
    private String eventBody;
    private LocalDateTime occurredOn;
    private String typeName;

    public StoredEvent(String aTypeName, LocalDateTime anOccurredOn, String anEventBody) {
        this();

        this.setEventBody(anEventBody);
        this.setOccurredOn(anOccurredOn);
        this.setTypeName(aTypeName);
    }

    public StoredEvent(String aTypeName, LocalDateTime anOccurredOn, String anEventBody, Long anEventId) {
        this(aTypeName, anOccurredOn, anEventBody);

        this.setEventId(anEventId);
    }

    public String eventBody() {
        return this.eventBody;
    }

    public Long eventId() {
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


    public StoredEvent() {
        super();
        this.setEventId(-1);
    }

    protected void setEventBody(String anEventBody) {
        //TODO  this.assertArgumentNotEmpty(anEventBody, "The event body is required.");
        //this.assertArgumentLength(anEventBody, 1, 65000, "The event body must be 65000 characters or less.");

        this.eventBody = anEventBody;
    }

    protected void setEventId(long anEventId) {
        this.eventId = anEventId;
    }

    protected void setOccurredOn(LocalDateTime anOccurredOn) {
        //LocalDateTime.of()
        this.occurredOn = anOccurredOn;
    }

    protected void setTypeName(String aTypeName) {
        //TODO this.assertArgumentNotEmpty(aTypeName, "The event type name is required.");
        //this.assertArgumentLength(aTypeName, 1, 100, "The event type name must be 100 characters or less.");

        this.typeName = aTypeName;
    }
}
