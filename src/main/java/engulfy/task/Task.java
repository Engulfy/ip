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

    /**
     * Adds a tag to the list of tags associated with this task.
     *
     * @param tag the tag to be added
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Returns a formatted string representation of the tags associated with this task.
     * If there are no tags, an empty string is returned. Otherwise, the tags are
     * concatenated and prefixed with a '#' symbol, separated by commas.
     *
     * @return a string representation of the tags, or an empty string if no tags exist
     */
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
        return getStatusIcon() + " " + description + " " + getTagString();
    }
}
