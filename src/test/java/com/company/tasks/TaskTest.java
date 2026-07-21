package com.company.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void newTaskStartsWithTodoStatus() {
        Task task = new Task(1, "Buy groceries", "2026-01-01T10:00:00");

        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test
    void newTaskHasMatchingCreatedAndUpdatedTimestamps() {
        Task task = new Task(1, "Buy groceries", "2026-01-01T10:00:00");

        assertEquals(task.getCreatedAt(), task.getUpdatedAt());
    }

    @Test
    void settersUpdateTheirFields() {
        Task task = new Task(1, "Buy groceries", "2026-01-01T10:00:00");

        task.setDescription("Buy groceries and milk");
        task.setStatus(TaskStatus.DONE);
        task.setUpdatedAt("2026-01-02T10:00:00");

        assertEquals("Buy groceries and milk", task.getDescription());
        assertEquals(TaskStatus.DONE, task.getStatus());
        assertEquals("2026-01-02T10:00:00", task.getUpdatedAt());
    }
}
