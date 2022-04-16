package ua.javatar.google;

import org.junit.jupiter.api.Test;
import ua.javatar.google.calendar.GoogleCalendarLink;
import ua.javatar.google.calendar.GoogleCalendarLinkServiceImpl;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ua.javatar.google.calendar.GoogleCalendarLinkServiceImpl.DATE_FORMAT;


class GoogleCalendarLinkServiceTest {

    private static final String EXPECTED_LINK = "https://calendar.google.com/calendar/render?action=TEMPLATE&text=Weekly+Stand+Up&dates={startTime}%2F{endTime}&details=Discuss+current+state+of+the+project&location=Kyiv%2C+Ukraine&crm=BUSY&trp=true&add=guest1%40gmail.com";

    @Test
    void generate_google_calendar_link() {
        var startTime = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0).withSecond(0);
        var endTime = LocalDateTime.now().plusDays(1).withHour(12).withMinute(30).withSecond(0);
        var calendarLink = new GoogleCalendarLink.Builder("Weekly Stand Up", startTime, endTime)
                .location("Kyiv, Ukraine")
                .addGuest("guest1@gmail.com")
                .description("Discuss current state of the project")
                .availability(GoogleCalendarLink.AVAILABILITY.BUSY)
                .build();

        var calendarLinkService = new GoogleCalendarLinkServiceImpl();
        var link = calendarLinkService.generate(calendarLink);

        var expectedLink = EXPECTED_LINK.replace("{startTime}", DATE_FORMAT.format(startTime)).replace("{endTime}", DATE_FORMAT.format(endTime));
        assertEquals(expectedLink, link);
    }
}
