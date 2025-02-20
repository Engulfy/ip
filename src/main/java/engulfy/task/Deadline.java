package engulfy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 * It extends the Task class and includes a specific deadline date and time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter[] ACCEPTED_FORMATTERS = {
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")
    };
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description the description of the task
     * @param deadline the deadline in a string format (either "M/d/yyyy HHmm" or "MMM dd yyyy, h:mm a")
     * @throws IllegalArgumentException if the deadline format is invalid
     */
    public Deadline(String description, String deadline) {
        super(description);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert deadline != null && !deadline.isEmpty() : "Deadline cannot be null or empty";
        this.deadline = tryParseDeadline(deadline);
    }

    /**
     * Attempts to parse the deadline string into a LocalDateTime object.
     * It tries two formats: "M/d/yyyy HHmm" and "MMM dd yyyy, h:mm a".
     *
     * @param deadline the string representing the deadline
     * @return a LocalDateTime object representing the parsed deadline
     * @throws IllegalArgumentException if the deadline format is invalid
     */
    private LocalDateTime tryParseDeadline(String deadline) {
        for (DateTimeFormatter formatter : ACCEPTED_FORMATTERS) {
            try {
                return LocalDateTime.parse(deadline, formatter);
            } catch (Exception e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Are you sure you are in the correct timezone?: " + deadline);

    }

    /**
     * Returns the string representation of the Deadline task, including the description and formatted deadline.
     *
     * @return a string representation of the Deadline task
     */
    @Override
    public String toString() {
        assert deadline != null : "Deadline should not be null";
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(DATETIME_FORMATTER));
    }

}
