package app.model.entity.booking;

import app.model.entity.event.Event;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;
import app.model.entity.user.User;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {


        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "event_id")
        private Event event;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "attendee_id")
        private User attendee;

       @Column(name = "booking_date")
       private LocalDate bookingDate;
    }

