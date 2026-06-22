package app.service.event;

import app.model.dto.event.EventDto;
import app.model.entity.event.Event;
import app.model.entity.user.User;
import app.repository.event.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
    public EventDto getEventById(UUID id) {
        return eventRepository.findById(id)
                .map(this::entityToDto)
                .orElse(null);
    }

    public EventDto createEvent(EventDto eventDto, User organizer) {
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setLocation(eventDto.getLocation());
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setOrganizer(organizer);

        Event savedEvent = eventRepository.save(event);
        return entityToDto(savedEvent);
    }
    public EventDto updateEvent(UUID id, EventDto eventDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setTitle(eventDto.getTitle());
        event.setLocation(eventDto.getLocation());
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());

        Event updatedEvent = eventRepository.save(event);
        return entityToDto(updatedEvent);

    }

    public void deleteEvent(UUID id) {
        eventRepository.deleteById(id);
    }
    private EventDto entityToDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .location(event.getLocation())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .organizer(event.getOrganizer() != null ? app.model.dto.user.UserDto.builder()
                        .id(event.getOrganizer().getId())
                        .username(event.getOrganizer().getUsername())
                        .email(event.getOrganizer().getEmail())
                        .role(event.getOrganizer().getRole())
                        .build() : null)
                .build();
    }
}
