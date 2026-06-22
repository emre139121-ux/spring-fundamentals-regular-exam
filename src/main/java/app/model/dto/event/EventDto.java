package app.model.dto.event;

import app.model.dto.activity.ActivityDto;
import app.model.dto.user.UserDto;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private UUID id;
    private String title;
    private String location;
    private String description;
    private LocalDate eventDate;
    private UserDto organizer;
    private List<ActivityDto> activities = new ArrayList<>();
}
