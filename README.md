# EventPlanner

## Overview

EventPlanner is a Spring Boot web application that enables organizers to create and manage events while allowing attendees to discover, book, and participate in upcoming activities. The platform provides a seamless experience for event management, ticket reservations, and attendee engagement.

---

## Technology Stack

* Java 17
* Spring Boot 3.4.0
* Spring MVC
* Spring Data JPA
* Spring Security
* Thymeleaf
* MySQL
* Maven
* HTML5
* CSS3

---

## Domain Entities

### User

Represents a registered user of the platform.

#### Properties

* UUID id
* username
* email
* password
* role

### Event

Represents an event published on the platform.

#### Properties

* UUID id
* title
* location
* description
* startDate
* endDate
* organizer

### Booking

Represents a reservation made by an attendee for a specific event.

#### Properties

* UUID id
* event
* attendee
* bookingDate

### Activity

Represents an activity, session, or attraction associated with an event.

#### Properties

* UUID id
* event
* title
* imageUrl
* description

---

## Functionalities

### Create Event

Organizers can create and publish new events.

### Edit Event

Event organizers can update event details and information.

### Delete Event

Event organizers can remove events from the platform.

### Book Event

Authenticated users can reserve a spot for an available event.

### Cancel Booking

Attendees can cancel their reservations before the event takes place.

### Manage Activities

Event organizers can add, edit, and remove activities related to their events.

---

## Security

### Guests can:

* Register
* Login
* Access public pages

### Authenticated users can:

* Browse events
* Book events
* Manage their bookings

### Organizers can:

* Create events
* Edit events
* Delete events
* Manage event activities

Passwords are stored using BCrypt hashing.

---

## Validation

All forms include server-side validation.

### Examples

* Required field validation
* Minimum and maximum length restrictions
* Email validation
* Date validation
* Business rule validation

Validation messages are displayed directly next to invalid fields.

---

## Pages

* Home
* Login
* Register
* Event Catalog
* Create Event
* Edit Event
* Event Details
* My Events
* Manage Activities

---

## Relationships

* Event → User (ManyToOne)
* Booking → User (ManyToOne)
* Booking → Event (ManyToOne)
* Activity → Event (ManyToOne)


---

## Project Features

* User registration and authentication
* Role-based authorization
* Event management
* Booking management
* Activity management
* Server-side validation
* Exception handling
* Responsive user interface
* UUID-based entity identifiers
* Relational database persistence using Spring Data JPA
