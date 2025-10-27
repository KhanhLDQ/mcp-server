package org.tommap.stdio.event.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tommap.stdio.event.application_event.HelpdeskTicketCreatedEvent;
import org.tommap.stdio.service.IEmailService;

@Component
@RequiredArgsConstructor
@Slf4j
public class HelpdeskTicketCreatedHandler {
    private final IEmailService emailService;

    @EventListener
    @Async("emailTaskExecutor")
    public void handle(HelpdeskTicketCreatedEvent event) {
        log.info("start processing helpdesk-ticket-created event!");

        emailService.sendEmail(event.getRecipient(), event.getUsername(), event.getTicketId());
    }
}
