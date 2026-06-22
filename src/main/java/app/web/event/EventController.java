package app.web.event;

import app.mapper.user.UserMapper;
import app.model.dto.event.EventDto;
import app.model.dto.user.UserDto;
import app.service.event.EventService;
import app.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final UserService userService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllEvents() {
        List<EventDto> events = eventService.getAllEvents();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("events");
        modelAndView.addObject("events", events);
        return modelAndView;
    }
    @GetMapping("/{id}")
    public ModelAndView getEventDetails(@PathVariable UUID id) {
        EventDto event = eventService.getEventById(id);
        if (event == null) {
            return new ModelAndView("redirect:/events");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("event-details");
        modelAndView.addObject("event", event);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView getCreateEventPage() {
        EventDto eventDto = EventDto.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create-event");
        modelAndView.addObject("eventRequest", eventDto);
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createEvent(@Valid @ModelAttribute("eventRequest") EventDto eventDto, BindingResult bindingResult, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("create-event");
            return modelAndView;
        }
        UUID userId = (UUID) httpSession.getAttribute("user_id");

        UserDto organizer = userService.getUserById(userId);
        
        if (organizer == null) {
            return new ModelAndView("redirect:/login");
        }

        EventDto createdEvent = eventService.createEvent(eventDto, UserMapper.toEntity(organizer));

        return new ModelAndView("redirect:/events/" + createdEvent.getId());
    }
    @GetMapping("/{id}/edit")
    public ModelAndView getEditEventPage(@PathVariable UUID id, HttpSession httpSession) {
        EventDto event = eventService.getEventById(id);
        if (event == null) {
            return new ModelAndView("redirect:/events");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit-event");
        modelAndView.addObject("event", event);
        return modelAndView;
    }
    @PostMapping("/{id}/edit")
    public ModelAndView updateEvent(@PathVariable UUID id,@Valid @ModelAttribute("event")  EventDto eventDto, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("edit-event");
            modelAndView.addObject("event", eventDto);
            return modelAndView;
        }
        UUID userId = (UUID) httpSession.getAttribute("user_id");
        EventDto existingEvent = eventService.getEventById(id);
        if (!existingEvent.getOrganizer().getId().equals(userId)) {
            return new ModelAndView("redirect:/events/" + id);
        }
        eventDto.setId(id);
        EventDto updatedEvent = eventService.updateEvent(id,eventDto);
        return new ModelAndView("redirect:/events/" + updatedEvent.getId());
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteEvent(@PathVariable UUID id, HttpSession httpSession) {
        EventDto event = eventService.getEventById(id);
        if (event == null) {
            return new ModelAndView("redirect:/events");
        }
        eventService.deleteEvent(id);
        return new ModelAndView("redirect:/events");
    }

}
