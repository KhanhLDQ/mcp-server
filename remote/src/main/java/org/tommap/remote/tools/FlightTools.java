package org.tommap.remote.tools;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.tommap.remote.model.entity.FlightTicket;
import org.tommap.remote.model.request.TicketRequest;
import org.tommap.remote.service.IFlightTicketService;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FlightTools {
    private final IFlightTicketService flightTicketService;

    @Tool(name = "createTicket", description = "create a flight ticket")
    String createTicket(@ToolParam(description = "details to create a flight ticket") TicketRequest request) {
        log.info("create flight ticket for user: {} with details: {}", request.username(), request.bookingDetails());
        FlightTicket ticket = flightTicketService.createTicket(request);
        log.info("flight ticket created successfully for user: {} - ticketId: {}", request.username(), ticket.getId());

        return String.format("flight ticket #%d created successfully for user: %s", ticket.getId(), request.username());
    }

    @Tool(name = "getFlightTickets", description = "fetch the status of flight tickets based on a given username")
    List<FlightTicket> getFlightTickets(@ToolParam(description = "username to fetch the status of flight tickets") String username) {
        log.info("fetch flight tickets for user: {}", username);
        List<FlightTicket> tickets = flightTicketService.getByUsername(username);
        log.info("found {} flight tickets for user: {}", tickets.size(), username);

        return tickets;
    }
}
