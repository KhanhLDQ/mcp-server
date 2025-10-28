package org.tommap.remote.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.remote.model.entity.FlightTicket;

import java.util.List;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, Long> {
    List<FlightTicket> findByUsername(String username);
}
