package org.tommap.stdio.event.application_event;

import lombok.Builder;
import lombok.Getter;
import org.tommap.stdio.event.EventType;

import java.time.LocalDateTime;

@Getter
public class HelpdeskTicketCreatedEvent extends ApplicationEvent {
    private final String recipient;
    private final String username;
    private final Long ticketId;

    @Builder
    public HelpdeskTicketCreatedEvent(EventType eventType, LocalDateTime timestamp, String recipient, String username, Long ticketId) {
        super(eventType, timestamp);
        this.recipient = recipient;
        this.username = username;
        this.ticketId = ticketId;
    }
}
