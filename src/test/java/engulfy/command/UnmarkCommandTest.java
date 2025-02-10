package Engulfy.Command;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;

import Engulfy.Error.EngulfyError;
import Engulfy.Storage.Storage;
import Engulfy.Task.Task;
import Engulfy.Task.TaskList;
import Engulfy.Task.Todo;
import Engulfy.Ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnmarkCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(outContent));

        // Create a temporary file for storage
        tempFile = Files.createTempFile("test", ".txt").toFile();
        tempFile.deleteOnExit();

        // Initialize dependencies
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage() {
            @Override
            public List<Task> load() throws EngulfyError {
                return List.of(); // Return an empty list for testing
            }

            @Override
            public void save(TaskList tasks) throws EngulfyError {
                try (FileWriter writer = new FileWriter(tempFile)) {
                    for (Task task : tasks.getAllTasks()) {
                        writer.write(task.toString() + "\n");
                    }
                } catch (IOException e) {
                    throw new EngulfyError("error saving tasks: " + e.getMessage());
                }
            }
        };

        // Add some tasks to the TaskList
        taskList.addTask(new Todo("task 1"));
        taskList.addTask(new Todo("task 2"));
        taskList.addTask(new Todo("task 3"));

        // Mark all tasks as done initially for testing unmarking
        for (Task task : taskList.getAllTasks()) {
            task.markAsDone();
        }
    }

    @AfterEach
    void tearDown() {
        // Restore System.out
        System.setOut(originalOut);

        // Delete the temporary file
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testConstructor_ValidInput() {
        // Test valid input
        assertDoesNotThrow(() -> new UnmarkCommand("1"));
    }

    @Test
    void testConstructor_InvalidInput() {
        // Test invalid input (non-numeric)
        EngulfyError exception = assertThrows(EngulfyError.class, () -> new UnmarkCommand("abc"));
        assertEquals("Please specify a valid task number to unmark", exception.getMessage());
    }

    @Test
    void testExecute_UnmarkTaskSuccessfully() throws EngulfyError {
        // Arrange
        UnmarkCommand command = new UnmarkCommand("2");

        // Act
        command.execute(taskList, ui, storage);

        // Assert
        Task unmarkedTask = taskList.getAllTasks().get(1); // Index 1 corresponds to "task 2"
        assertFalse(unmarkedTask.isDone); // Verify that the task is marked as not done

        String expectedOutput = "OK, I've marked this task as not done yet:\n" +
                "    " + unmarkedTask + "\n" +
                "____________________________________________________________\n";
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testExecute_InvalidIndex() {
        // Arrange
        UnmarkCommand command;
        try {
            command = new UnmarkCommand("10"); // Invalid index
        } catch (EngulfyError e) {
            fail("Unexpected EngulfyError during UnmarkCommand creation: " + e.getMessage());
            return;
        }

        // Act & Assert
        EngulfyError exception = assertThrows(EngulfyError.class, () -> command.execute(taskList, ui, storage));
        assertEquals("Your task number is a little TOOOO big or small! try again :D", exception.getMessage());
    }

    @Test
    void testIsExit() {
        // Arrange
        UnmarkCommand command;
        try {
            command = new UnmarkCommand("1");
        } catch (EngulfyError e) {
            fail("Unexpected EngulfyError during UnmarkCommand creation: " + e.getMessage());
            return;
        }

        // Act & Assert
        assertFalse(command.isExit());
    }
}