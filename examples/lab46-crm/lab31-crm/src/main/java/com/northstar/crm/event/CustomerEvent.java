package com.northstar.crm.event;

import java.time.Instant;
import java.util.Objects;

/** Immutable CRM customer domain event. */
public record CustomerEvent(
    String eventId,
    String eventType,
    int eventVersion,
    Instant occurredAt,
    String customerId,
    String correlationId,
    String source,
    CustomerData data
) {
    public CustomerEvent {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(customerId);

        if (eventVersion != 1) {
            throw new UnsupportedEventVersionException();
        }
    }

    public record CustomerData(String fullName, String status) {
    }
}
