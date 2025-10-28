package org.tommap.remote.model.entity;

import lombok.Getter;

@Getter
public enum TicketStatus {
    BOOKED("booked"),
    CANCELED("canceled");

    private final String value;

    TicketStatus(String value) {
        this.value = value;
    }
}
