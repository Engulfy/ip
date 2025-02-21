package engulfy.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to mark the task as done or not done, and to retrieve its status.
 */
public class Task {
    private final String description;
    private boolean isDone;
    private final List<String> tags;

    /**
     * Constructs a Task with the specified description. The task is initially not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Adds a tag to the list of tags associated with this task.
     *
     * @param tag the tag to be added
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Returns the list of tags.
     *
     * @return a list of tags
     */
    public List<String> getTag() {
        return this.tags;
    }

    /**
     * Removes a specified tag from the list of tags if it exists.
     * If the tag is not found, prints a message indicating so.
     *
     * @param tag the tag to be removed
     */
    public void removeTag(String tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
        } else {
            System.out.println("Tag not found: " + tag);
        }
    }

    /**
     * Returns a formatted string representation of the tags, prefixed with '#'.
     * If there are no tags, an empty string is returned.
     *
     * @return a space-separated string of tags prefixed with '#', or an empty string if no tags exist
     */
    public String getTagString() {
        if (tags.isEmpty()) {
            return "";
        }
        return "#" + String.join(" #", tags);
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
