package app.model.dto.activity;

import app.model.dto.event.EventDto;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {

    private EventDto event;
    private String title;
    private String imageUrl;
    private String description;
}
