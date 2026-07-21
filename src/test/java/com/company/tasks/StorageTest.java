package com.company.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private static final String TEST_FILE = "test-tasks.json";

    @AfterEach
    void cleanUp() {
        // Delete the test file after every test so runs don't interfere with each other
        new File(TEST_FILE).delete();
    }

    @Test
    void savedTasksCanBeLoadedBack() {
        Storage storage = new Storage(TEST_FILE);

        Task task = new Task(1, "Buy groceries", "2026-01-01T10:00:00");
        storage.saveTasks(List.of(task));

        List<Task> loaded = storage.loadTasks();

        assertEquals(1, loaded.size());
        assertEquals("Buy groceries", loaded.get(0).getDescription());
        assertEquals(TaskStatus.TODO, loaded.get(0).getStatus());
    }

    @Test
    void loadingNonExistentFileReturnsEmptyList() {
        Storage storage = new Storage("this-file-does-not-exist.json");

        List<Task> loaded = storage.loadTasks();

        assertTrue(loaded.isEmpty());
    }

    @Test
    void descriptionContainingCommaSurvivesRoundTrip() {
        Storage storage = new Storage(TEST_FILE);

        Task task = new Task(1, "Buy milk, eggs, and bread", "2026-01-01T10:00:00");
        storage.saveTasks(List.of(task));

        List<Task> loaded = storage.loadTasks();

        assertEquals("Buy milk, eggs, and bread", loaded.get(0).getDescription());
    }
    @Test
    void descriptionContainingQuoteSurvivesRoundTrip() {
        Storage storage = new Storage(TEST_FILE);

        Task task = new Task(1, "Fix the \"login\" bug", "2026-01-01T10:00:00");
        storage.saveTasks(List.of(task));

        List<Task> loaded = storage.loadTasks();

        assertEquals("Fix the \"login\" bug", loaded.get(0).getDescription());
    }
}
