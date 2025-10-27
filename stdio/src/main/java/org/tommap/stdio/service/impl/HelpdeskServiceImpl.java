package org.tommap.stdio.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tommap.stdio.event.application_event.ApplicationEvent;
import org.tommap.stdio.event.application_event.HelpdeskTicketCreatedEvent;
import org.tommap.stdio.event.publisher.EventPublisher;
import org.tommap.stdio.model.entity.HelpdeskTicket;
import org.tommap.stdio.model.request.TicketRequest;
import org.tommap.stdio.repository.HelpdeskTicketRepository;
import org.tommap.stdio.service.IHelpdeskService;

import java.time.LocalDateTime;
import java.util.List;

import static org.tommap.stdio.model.entity.TicketStatus.OPEN;
import static org.tommap.stdio.event.EventType.HELPDESK_TICKET_CREATED;

@Service
@RequiredArgsConstructor
public class HelpdeskServiceImpl implements IHelpdeskService {
    private static final String RECIPIENT = "tommapcoder@gmail.com";

    private final HelpdeskTicketRepository helpdeskTicketRepository;
    private final EventPublisher<ApplicationEvent> eventPublisher;

    @Override
    @Transactional
    public HelpdeskTicket createTicket(TicketRequest request) {
        var ticket = HelpdeskTicket.builder() //transient state
                .issue(request.issue())
                .username(request.username())
                .status(OPEN)
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7))
                .build();

        var savedTicket = helpdeskTicketRepository.save(ticket); //move to persistent context -> manged by entity manager -> after transaction ends the EM will be closed -> no longer manged by EM -> entity becomes detached

        //publish event to send email
        var event = HelpdeskTicketCreatedEvent.builder()
                .eventType(HELPDESK_TICKET_CREATED)
                .timestamp(savedTicket.getCreatedAt())
                .recipient(RECIPIENT)
                .username(savedTicket.getUsername())
                .ticketId(savedTicket.getId())
                .build();
        eventPublisher.publish(event);

        return savedTicket;
    }

    @Override
    public List<HelpdeskTicket> getByUsername(String username) {
        return helpdeskTicketRepository.findByUsername(username);
    }
}
