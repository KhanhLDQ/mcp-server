package org.tommap.remote.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tommap.remote.model.entity.FlightTicket;
import org.tommap.remote.model.request.TicketRequest;
import org.tommap.remote.repository.FlightTicketRepository;
import org.tommap.remote.service.IFlightTicketService;

import java.time.LocalDateTime;
import java.util.List;

import static org.tommap.remote.model.entity.TicketStatus.BOOKED;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightTicketServiceImpl implements IFlightTicketService {
    private final FlightTicketRepository flightTicketRepository;

    @Override
    @Transactional
    public FlightTicket createTicket(TicketRequest request) {
        var ticket = FlightTicket.builder()
                .username(request.username())
                .bookingDetails(request.bookingDetails())
                .status(BOOKED)
                .createdAt(LocalDateTime.now())
                .build();

        return flightTicketRepository.save(ticket);
    }

    @Override
    public List<FlightTicket> getByUsername(String username) {
        return flightTicketRepository.findByUsername(username);
    }
}
