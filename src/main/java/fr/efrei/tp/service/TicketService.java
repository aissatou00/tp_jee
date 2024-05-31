package fr.efrei.tp.service;

import fr.efrei.tp.dto.TicketDto;
import fr.efrei.tp.model.Ticket;
import fr.efrei.tp.model.User;
import fr.efrei.tp.model.Event;
import fr.efrei.tp.repository.TicketRepository;
import fr.efrei.tp.repository.UserRepository;
import fr.efrei.tp.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket createTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        User user = userRepository.findById(ticketDto.getUserId()).orElse(null);
        Event event = eventRepository.findById(ticketDto.getEventId()).orElse(null);

        if (user != null && event != null) {
            ticket.setUser(user);
            ticket.setEvent(event);
            return ticketRepository.save(ticket);
        } else {
            return null;
        }
    }

    @Transactional
    public Ticket updateTicket(Long id, TicketDto ticketDto) {
        Ticket ticket = getTicketById(id);
        if (ticket != null) {
            User user = userRepository.findById(ticketDto.getUserId()).orElse(null);
            Event event = eventRepository.findById(ticketDto.getEventId()).orElse(null);

            if (user != null && event != null) {
                ticket.setUser(user);
                ticket.setEvent(event);
                return ticketRepository.save(ticket);
            } else {
                return null;
            }
        }
        return null;
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}