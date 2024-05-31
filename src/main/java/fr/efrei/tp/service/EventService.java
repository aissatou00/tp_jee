package fr.efrei.tp.service;

import fr.efrei.tp.dto.EventDto;
import fr.efrei.tp.model.Event;
import fr.efrei.tp.model.Stadium;
import fr.efrei.tp.repository.EventRepository;
import fr.efrei.tp.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final StadiumRepository stadiumRepository;

    @Autowired
    public EventService(EventRepository eventRepository, StadiumRepository stadiumRepository) {
        this.eventRepository = eventRepository;
        this.stadiumRepository = stadiumRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(EventDto eventDto) {
        Stadium stadium = stadiumRepository.findById(eventDto.getStadiumId())
            .orElseThrow(() -> new RuntimeException("Stadium not found"));

        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());
        event.setStadium(stadium);

        return eventRepository.save(event);
    }

    @Transactional
    public Event updateEvent(Long id, EventDto eventDto) {
        Event event = getEventById(id);
        if (event != null) {
            Stadium stadium = stadiumRepository.findById(eventDto.getStadiumId())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));

            event.setName(eventDto.getName());
            event.setDate(eventDto.getDate());
            event.setStadium(stadium);
            return eventRepository.save(event);
        }
        return null;
    }

    @Transactional
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }
}