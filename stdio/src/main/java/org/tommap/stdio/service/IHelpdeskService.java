package org.tommap.stdio.service;

import org.tommap.stdio.model.entity.HelpdeskTicket;
import org.tommap.stdio.model.request.TicketRequest;

import java.util.List;

public interface IHelpdeskService {
    HelpdeskTicket createTicket(TicketRequest request);
    List<HelpdeskTicket> getByUsername(String username);
}
