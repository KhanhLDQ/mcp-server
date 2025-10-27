package org.tommap.stdio.tool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.tommap.stdio.model.entity.HelpdeskTicket;
import org.tommap.stdio.model.request.TicketRequest;
import org.tommap.stdio.service.IHelpdeskService;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class HelpdeskTools {
    private final IHelpdeskService helpdeskService;

    @Tool(name = "createTicket", description = "create a support helpdesk ticket")
    String createTicket(@ToolParam(description = "details to create a support helpdesk ticket") TicketRequest ticketRequest) {
        log.info("create support helpdesk ticket for user: {} with details: {}", ticketRequest.username(), ticketRequest.issue());
        HelpdeskTicket savedTicket = helpdeskService.createTicket(ticketRequest);
        log.info("helpdesk ticket created successfully for user: {} - ticketId: {}", ticketRequest.username(), savedTicket.getId());

        return String.format("helpdesk ticket #%d created successfully for user: %s", savedTicket.getId(), ticketRequest.username());
    }

    @Tool(name = "getHelpdeskTickets", description = "fetch the status of helpdesk tickets based on a given username")
    List<HelpdeskTicket> getHelpdeskTickets(@ToolParam(description = "username to fetch the status of the helpdesk tickets") String username) {
        log.info("fetch helpdesk tickets for user: {}", username);
        List<HelpdeskTicket> helpdeskTickets = helpdeskService.getByUsername(username);
        log.info("found {} helpdesk tickets for user: {}", helpdeskTickets.size(), username);

        return helpdeskTickets;
    }
}
