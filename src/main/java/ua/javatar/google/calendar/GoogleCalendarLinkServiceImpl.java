package ua.javatar.google.calendar;

import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;

public class GoogleCalendarLinkServiceImpl implements GoogleCalendarLinkService {
    private static final String BASIC_URL = "https://calendar.google.com/calendar/render";
    private static final String DATE_FORMAT_PATTERN = "yyyyMMdd'T'HHmmss";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
    private static final String PARAM_DELIMITER = "&";
    private static final String EQUAL_SIGN = "=";
    private static final String TWO_DOT = ":";
    private static final String COMMA = ",";
    private static final String SLASH = "/";

    public String generate(GoogleCalendarLink link) {
        StringBuilder linkBuilder = new StringBuilder(BASIC_URL);
        linkBuilder.append("?").append("action").append(EQUAL_SIGN).append(link.getAction());
        addParam("text", linkBuilder, link.getEventTitle());
        addParam("dates", linkBuilder, buildDates(link));
        addParam("ctz", linkBuilder, link.getTimezone());
        addParam("details", linkBuilder, link.getDescription());
        addParam("location", linkBuilder, link.getLocation());
        addParam("crm", linkBuilder, link.getAvailability().name());
        addParam("trp", linkBuilder, String.valueOf(link.isTransparent()));
        if (link.getEventSources() != null) {
            link.getEventSources().forEach(e -> addParam("sprop", linkBuilder, e));
        }
        addParam("add", linkBuilder, buildGuests(link.getGuests()));
        addParam("src", linkBuilder, link.getAlternativeEmail());
        addParam("recur", linkBuilder, link.getRecurrence());
        return linkBuilder.toString();
    }

    private String buildGuests(Collection<String> guests) {
        if (guests != null) {
            return String.join(COMMA, guests);
        }
        return null;
    }

    private String buildEvenSources(Collection<String> eventSources) {
        if (eventSources != null) {
            return String.join(PARAM_DELIMITER, eventSources);
        }
        return null;
    }

    private void addParam(String paramName, StringBuilder linkBuilder, String paramValue) {
        if (Objects.nonNull(paramValue) && !paramValue.isBlank()) {
            linkBuilder.append(PARAM_DELIMITER)
                    .append(paramName)
                    .append(EQUAL_SIGN)
                    .append(URLEncoder.encode(paramValue));
        }
    }

    private String buildDates(GoogleCalendarLink link) {
        return DATE_FORMAT.format(link.getStartTime()) + SLASH + DATE_FORMAT.format(link.getEndTime());
    }


}
