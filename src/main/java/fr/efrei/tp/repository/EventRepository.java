package fr.efrei.tp.repository;

import fr.efrei.tp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long> {

}
