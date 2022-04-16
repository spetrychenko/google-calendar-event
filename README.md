# Google calendar event link generator

1. Use `GoogleCalendarLink.Builder` class for creating link object
2. Use `GoogleCalendarLinkServiceImpl.generate()` class for generating link
3. Check `GoogleCalendarLinkServiceTest` out as an example of usage
    ```java
         var calendarLink = new GoogleCalendarLink.Builder("Weekly Stand Up", startTime, endTime)
                .location("Kyiv, Ukraine")
                .addGuest("guest1@gmail.com")
                .description("Discuss current state of the project")
                .availability(GoogleCalendarLink.AVAILABILITY.BUSY)
                .build();

        var calendarLinkService = new GoogleCalendarLinkServiceImpl();
        var link = calendarLinkService.generate(calendarLink);
    ```
