import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = tryParseDateTime(from);
        this.to = tryParseDateTime(to);
    }

    private LocalDateTime tryParseDateTime(String dateTime) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

        try {
            return LocalDateTime.parse(dateTime, formatter1);
        } catch (Exception e1) {
            try {
                return LocalDateTime.parse(dateTime, formatter2);
            } catch (Exception e2) {
                throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}