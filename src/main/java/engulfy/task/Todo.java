package engulfy.task;

/**
 * Represents a Todo task in the system.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return a string representing the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
