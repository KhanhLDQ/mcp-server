package org.tommap.stdio.event.application_event;

import lombok.Getter;
import org.tommap.stdio.event.EventType;
import org.tommap.stdio.event.StdioGenericEvent;

import java.time.LocalDateTime;

@Getter
public abstract class ApplicationEvent implements StdioGenericEvent {
    private final EventType eventType;
    private final LocalDateTime timestamp;

    public ApplicationEvent(EventType eventType, LocalDateTime timestamp) {
        this.eventType = eventType;
        this.timestamp = timestamp;
    }
}
