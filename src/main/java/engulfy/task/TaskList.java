package engulfy.task;

import engulfy.error.EngulfyError;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws EngulfyError {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EngulfyError("Your task number is not in my database to be deleted! try again :D");
        }
    }

    public Task markTask(int index) throws EngulfyError {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new EngulfyError("Your task number is a little TOOOO big or small! try again :D");
        }
    }

    public Task unmarkTask(int index) throws EngulfyError {
        try {
            Task task = tasks.get(index - 1);
            task.markAsNotDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new EngulfyError("Your task number is a little TOOOO big or small! try again :D");
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}