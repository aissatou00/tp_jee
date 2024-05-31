package fr.efrei.tp.repository;

import fr.efrei.tp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
