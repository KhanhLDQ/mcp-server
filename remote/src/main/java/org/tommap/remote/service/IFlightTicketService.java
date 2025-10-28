package org.tommap.remote.service;

import org.tommap.remote.model.entity.FlightTicket;
import org.tommap.remote.model.request.TicketRequest;

import java.util.List;

public interface IFlightTicketService {
    FlightTicket createTicket(TicketRequest request);
    List<FlightTicket> getByUsername(String username);
}
