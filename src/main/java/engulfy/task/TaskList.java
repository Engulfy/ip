package engulfy.task;

import java.util.ArrayList;
import java.util.List;

import engulfy.error.EngulfyError;

/**
 * The TaskList class manages a list of tasks and provides methods to add, delete, mark, unmark tasks.
 */
public class TaskList {
    private static final String OUT_OF_BOUND_ERROR = "Your task number is a little TOOOO big or small! try again :D";
    private static final String DELETE_ERROR = "Your task number is not in my database to be deleted! try again :D";
    private final List<Task> tasks;

    /**
     * Constructs a new TaskList with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with the specified list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskList
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Deletes a task by its index in the TaskList.
     *
     * @param index the index of the task to delete
     * @return the deleted task
     * @throws EngulfyError if the index is out of bounds
     */
    public Task deleteTask(int index) throws EngulfyError {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EngulfyError(DELETE_ERROR);
        }
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index the index of the task to mark
     * @return the marked task
     * @throws EngulfyError if the index is out of bounds
     */
    public Task markTask(int index) throws EngulfyError {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new EngulfyError(OUT_OF_BOUND_ERROR);
        }
    }

    /**
     * Unmarks a task by its index.
     *
     * @param index the index of the task to unmark
     * @return the unmarked task
     * @throws EngulfyError if the index is out of bounds
     */
    public Task unmarkTask(int index) throws EngulfyError {
        try {
            Task task = tasks.get(index - 1);
            task.markAsNotDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new EngulfyError(OUT_OF_BOUND_ERROR);
        }
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if the list has no tasks, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns all tasks in the TaskList.
     *
     * @return a list of all tasks
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the size of the TaskList
     */
    public int size() {
        return tasks.size();
    }
}
