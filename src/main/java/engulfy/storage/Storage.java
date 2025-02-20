package engulfy.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import engulfy.error.EngulfyError;
import engulfy.task.Deadline;
import engulfy.task.Event;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.task.Todo;

/**
 * The Storage class handles loading and saving tasks to and from a file.
 * It ensures that the task list is preserved between application sessions.
 */
public class Storage {
    private static final String FILE_PATH = "data/engulfy.txt";
    private static final String DIRECTORY_PATH = "data";

    /**
     * Constructs a Storage instance.
     * Ensures that the necessary directories and files are ready for use.
     */
    public Storage() {
    }

    /**
     * Loads tasks from a file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws EngulfyError If an error occurs while loading tasks.
     */
    public List<Task> load() throws EngulfyError {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!ensureDirectoryExists()) {
                return tasks;
            }

            File file = new File(FILE_PATH);
            if (!ensureFileExists(file)) {
                return tasks;
            }

            tasks.addAll(readTasksFromFile(file));
        } catch (IOException e) {
            throw new EngulfyError("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Ensures that the directory for storing tasks exists.
     *
     * @return True if the directory exists or was successfully created, false otherwise.
     */
    private boolean ensureDirectoryExists() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Error: Failed to create directory " + DIRECTORY_PATH);
            return false;
        }
        return true;
    }

    /**
     * Ensures that the task file exists.
     *
     * @param file The file to check.
     * @return True if the file exists or was successfully created, false otherwise.
     * @throws IOException If an error occurs while creating the file.
     */
    private boolean ensureFileExists(File file) throws IOException {
        if (!file.exists() && file.createNewFile()) {
            System.out.println("File created: " + FILE_PATH);
            return false;
        }
        return true;
    }

    /**
     * Reads tasks from the given file and returns them as a list.
     *
     * @param file The file to read tasks from.
     * @return A list of tasks read from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    private List<Task> readTasksFromFile(File file) throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                Task task = parseTaskFromString(fileScanner.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }


    /**
     * Saves the given task list to the storage file.
     * Overwrites the existing file contents with the updated tasks.
     *
     * @param tasks the task list to save
     * @throws EngulfyError if there is an error writing to the file
     */
    public void save(TaskList tasks) throws EngulfyError {
        try {
            FileWriter writer = new FileWriter(FILE_PATH, false);
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new EngulfyError("error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a task from a string representation loaded from the storage file.
     *
     * @param line the string representation of a task
     * @return the corresponding Task object, or null if parsing fails
     */
    private Task parseTaskFromString(String line) {
        try {
            char type = line.charAt(1);
            boolean isDone = line.charAt(4) == 'X';

            if (type == 'T') {
                String description = line.substring(7);
                Todo task = new Todo(description);
                if (isDone) {
                    task.markAsDone();
                }
                return task;
            } else if (type == 'D') {
                int byIndex = line.lastIndexOf("(by: ");
                String description = line.substring(7, byIndex - 1);
                String by = line.substring(byIndex + 5, line.length() - 1);
                Deadline task = new Deadline(description, by);
                if (isDone) {
                    task.markAsDone();
                }
                return task;
            } else if (type == 'E') {
                int fromIndex = line.indexOf("(from: ");
                int toIndex = line.indexOf(" to: ");
                String description = line.substring(7, fromIndex - 1);
                String from = line.substring(fromIndex + 7, toIndex);
                String to = line.substring(toIndex + 5, line.length() - 1);
                Event task = new Event(description, from, to);
                if (isDone) {
                    task.markAsDone();
                }

                return task;
            }
        } catch (Exception e) {
            System.out.println("error parsing task: " + line);
        }

        return null;
    }
}
