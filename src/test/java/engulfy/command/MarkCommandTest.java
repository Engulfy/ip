package engulfy.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.task.Todo;
import engulfy.ui.Ui;

/**
 * Unit tests for the MarkCommand class, which marks tasks as done.
 */
class MarkCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private File tempFile;

    /**
     * Set up the environment for testing by initializing necessary components.
     * Redirects System.out to capture output for testing.
     *
     * @throws IOException If an error occurs while creating a temporary file.
     */
    @BeforeEach
    void setUp() throws IOException {
        System.setOut(new PrintStream(outContent));

        tempFile = Files.createTempFile("test", ".txt").toFile();
        tempFile.deleteOnExit();

        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage() {
            @Override
            public List<Task> load() throws EngulfyError {
                return List.of();
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

        taskList.addTask(new Todo("task 1"));
        taskList.addTask(new Todo("task 2"));
        taskList.addTask(new Todo("task 3"));
    }

    /**
     * Clean up resources after each test method.
     * Restores the original System.out and deletes the temporary file.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);

        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    /**
     * Tests that the MarkCommand constructor handles valid input correctly.
     */
    @Test
    void testConstructorValidInput() {
        assertDoesNotThrow(() -> new MarkCommand("1"));
    }

    /**
     * Tests that the MarkCommand constructor throws an exception for invalid input.
     */
    @Test
    void testConstructorInvalidInput() {
        EngulfyError exception = assertThrows(EngulfyError.class, () -> new MarkCommand("abc"));
        assertEquals("This does not seem like a number to Engulfy :/", exception.getMessage());
    }

    /**
     * Tests that the execute method marks a task as done successfully.
     *
     * @throws EngulfyError If an error occurs during task marking.
     */
    @Test
    void testExecuteMarkTaskSuccessfully() throws EngulfyError {
        MarkCommand command = new MarkCommand("2");

        command.execute(taskList, ui, storage);

        Task markedTask = taskList.getAllTasks().get(1);
        assertTrue(markedTask.isDone());

        String expectedOutput = "NICEE! Keep up the good work!\n"
                + "    " + markedTask + "\n"
                + "____________________________________________________________\n";

        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests that the execute method throws an exception when the task index is invalid.
     */
    @Test
    void testExecuteInvalidIndex() {
        MarkCommand command;
        try {
            command = new MarkCommand("10");
        } catch (EngulfyError e) {
            fail("Unexpected EngulfyError during MarkCommand creation: " + e.getMessage());
            return;
        }

        EngulfyError exception = assertThrows(EngulfyError.class, () -> command.execute(taskList, ui, storage));
        assertEquals("Your task number is a little TOOOO big or small! try again :D", exception.getMessage());
    }

    /**
     * Tests that the isExit method returns false for MarkCommand (indicating it does not exit the program).
     */
    @Test
    void testIsExit() {
        MarkCommand command;
        try {
            command = new MarkCommand("1");
        } catch (EngulfyError e) {
            fail("Unexpected EngulfyError during MarkCommand creation: " + e.getMessage());
            return;
        }

        assertFalse(command.isExit());
    }
}
