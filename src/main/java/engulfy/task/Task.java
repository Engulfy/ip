package engulfy.task;

import java.util.HashSet;
import java.util.Set;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to mark the task as done or not done, and to retrieve its status.
 */
public class Task {
    private final String description;
    private boolean isDone;
    private Set<String> tags;

    /**
     * Constructs a Task with the specified description. The task is initially not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getTagString() {
        return tags.isEmpty() ? "" : "#" + String.join(", ", tags);
    }

    /**
     * Returns the status icon of the task. "[X]" if done, "[ ]" if not done.
     *
     * @return a string representing the task's status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Retrieves the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task's completion status.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation of the task, including the status icon and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description + "    " + getTagString();
    }
}
