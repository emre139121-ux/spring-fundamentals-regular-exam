package app.model.entity.activity;

import app.model.entity.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {


        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "event_id")
        private Event event;

        @Column(nullable = false)
        private String title;

        @Column(name = "image_url")
        private String imageUrl;

        @Column(length = 3000)
        private String description;
    }
