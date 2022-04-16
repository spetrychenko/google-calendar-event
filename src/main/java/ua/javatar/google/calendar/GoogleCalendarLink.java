package ua.javatar.google.calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class GoogleCalendarLink {
    private String eventTitle;
    private String action;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String timezone;
    private String description;
    private String location;
    private AVAILABILITY availability;
    private boolean isTransparent;
    private Collection<String> eventSources;
    private Collection<String> guests;
    private String alternativeEmail;
    private String recurrence;

    private GoogleCalendarLink(Builder builder) {
        this.eventTitle = builder.eventTitle;
        this.action = builder.action;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.timezone = builder.timezone;
        this.description = builder.description;
        this.location = builder.location;
        this.availability = builder.availability;
        this.isTransparent = builder.isTransparent;
        this.eventSources = builder.eventSources;
        this.guests = builder.guests;
        this.alternativeEmail = builder.alternativeEmail;
        this.recurrence = builder.recurrence;
    }

    public static class Builder {
        private final String eventTitle;
        private final LocalDateTime startTime;
        private final LocalDateTime endTime;
        private String action = "TEMPLATE";
        private String timezone;
        private String description;
        private String location;
        private AVAILABILITY availability = AVAILABILITY.AVAILABLE;
        private boolean isTransparent = true;
        private Collection<String> eventSources;
        private Collection<String> guests;
        private String alternativeEmail;
        private String recurrence;

        public Builder(String eventTitle, LocalDateTime startTime, LocalDateTime endTime) {
            this.eventTitle = eventTitle;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder timezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder availability(AVAILABILITY availability) {
            this.availability = availability;
            return this;
        }

        public Builder isTransparent(boolean isTransparent) {
            this.isTransparent = isTransparent;
            return this;
        }

        public Builder addEventSource(String eventSource) {
            if (eventSource != null) {
                if (Objects.isNull(eventSources)) {
                    eventSources = new ArrayList<>();
                }
                eventSources.add(eventSource);
            }
            return this;
        }

        public Builder addGuest(String guest) {
            if (Objects.nonNull(guest) && !guest.isBlank()) {
                if (Objects.isNull(guests)) {
                    guests = new ArrayList<>();
                }
                guests.add(guest);
            }
            return this;
        }

        public Builder alternativeEmail(String alternativeEmail) {
            this.alternativeEmail = alternativeEmail;
            return this;
        }

        /**
         * Recurrence see for more details <a href="https://icalendar.org/iCalendar-RFC-5545/3-8-5-3-recurrence-rule.html">RFC-5545</a>
         *
         * @param recurrence in RFC-5545 format
         */
        public Builder recurrence(String recurrence) {
            this.recurrence = recurrence;
            return this;
        }

        public GoogleCalendarLink build() {
            return new GoogleCalendarLink(this);
        }
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public AVAILABILITY getAvailability() {
        return availability;
    }

    public boolean isTransparent() {
        return isTransparent;
    }

    public Collection<String> getEventSources() {
        return eventSources;
    }

    public Collection<String> getGuests() {
        return guests;
    }

    public String getAlternativeEmail() {
        return alternativeEmail;
    }

    public String getRecurrence() {
        return recurrence;
    }

    @Override
    public String toString() {
        return "GoogleCalendarLink{" +
                "eventTitle='" + eventTitle + '\'' +
                ", action='" + action + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", timezone='" + timezone + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", availability=" + availability +
                ", isTransparent=" + isTransparent +
                ", eventSources=" + eventSources +
                ", guests=" + guests +
                ", alternativeEmail='" + alternativeEmail + '\'' +
                ", recurrence='" + recurrence + '\'' +
                '}';
    }

    public enum AVAILABILITY {
        AVAILABLE, BUSY, BLOCKING
    }
}
