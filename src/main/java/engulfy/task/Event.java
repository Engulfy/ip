package engulfy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a start and end time.
 * It extends the Task class and includes two specific LocalDateTime objects, 'from' and 'to'.
 */
public class Event extends Task {
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter[] ACCEPTED_FORMATTERS = {
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a")
    };
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time in a string format (either "M/d/yyyy HHmm" or "MMM dd yyyy, h:mm a")
     * @param to the end time in a string format (either "M/d/yyyy HHmm" or "MMM dd yyyy, h:mm a")
     * @throws IllegalArgumentException if the start or end time format is invalid
     */
    public Event(String description, String from, String to) {
        super(description);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert from != null && !from.isEmpty() : "Start time cannot be null or empty";
        assert to != null && !to.isEmpty() : "End time cannot be null or empty";
        this.from = tryParseDateTime(from);
        this.to = tryParseDateTime(to);
    }

    /**
     * Attempts to parse a string representing a date-time into a LocalDateTime object.
     * It tries two formats: "M/d/yyyy HHmm" and "MMM dd yyyy, h:mm a".
     *
     * @param dateTime the string representing the date-time
     * @return a LocalDateTime object representing the parsed date-time
     * @throws IllegalArgumentException if the date-time format is invalid
     */
    private LocalDateTime tryParseDateTime(String dateTime) {
        for (DateTimeFormatter formatter : ACCEPTED_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (Exception e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Are you sure you are in the correct timezone?: " + dateTime);
    }

    /**
     * Returns the string representation of the Event task, including the description,
     * start time, and end time in a formatted string.
     *
     * @return a string representation of the Event task
     */
    @Override
    public String toString() {
        assert from != null : "Start time should not be null";
        assert to != null : "End time should not be null";
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(DATETIME_FORMATTER),
                to.format(DATETIME_FORMATTER));
    }
}
