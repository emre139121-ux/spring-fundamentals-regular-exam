package app.model.dto.booking;

import app.model.dto.event.EventDto;
import app.model.dto.user.UserDto;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private UUID id;
    private EventDto event;
    private UserDto attendee;
    private LocalDate bookingDate;
}
