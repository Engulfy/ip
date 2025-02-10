package Engulfy.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = tryParseDeadline(deadline);
    }

    private LocalDateTime tryParseDeadline(String deadline) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

        try {
            return LocalDateTime.parse(deadline, formatter1);
        } catch (Exception e1) {
            try {
                return LocalDateTime.parse(deadline, formatter2);
            } catch (Exception e2) {
                throw new IllegalArgumentException("Invalid deadline format: " + deadline);
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

}
