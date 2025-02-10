package engulfy.storage;

import engulfy.error.EngulfyError;
import engulfy.task.Deadline;
import engulfy.task.Event;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "data/Engulfy.txt";
    private static final String DIRECTORY_PATH = "data";

    public Storage() {
    }

    public List<Task> load() throws EngulfyError {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists() && !directory.mkdirs()) {
                System.out.println("error: Failed to create directory " + DIRECTORY_PATH);
                return tasks;
            }

            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + FILE_PATH);
                }
                return tasks;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTaskFromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

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