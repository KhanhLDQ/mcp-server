package org.tommap.stdio.event.publisher;

import org.tommap.stdio.event.StdioGenericEvent;

public interface EventPublisher<T extends StdioGenericEvent> {
    void publish(T event);
}
