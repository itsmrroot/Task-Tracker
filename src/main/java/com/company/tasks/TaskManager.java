package com.company.tasks;

import java.util.List;
import java.time.LocalDateTime;

public class TaskManager {

    List<Task> tasks;
    Storage storage;
    private int nextId;

    public TaskManager() {
        this.storage = new Storage();
        this.tasks =  storage.loadTasks();

        nextId = 1;
        for (Task t : tasks) {
            if (t.getId() >= nextId) {
                nextId = t.getId() + 1;
            }
        }

    }
    public void addTask(String description) {
        Task task = new Task(nextId++, description, LocalDateTime.now().toString());
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        storage.saveTasks(tasks);
        System.out.println("Task " + id + " has been deleted");
    }

    public void updateTask(int id, String newDescription) {
        boolean found = false;

        for (Task task : tasks) {
            if(task.getId() == id) {
                task.setDescription(newDescription);
                task.setUpdatedAt(LocalDateTime.now().toString());
                found = true;
                break;
            }

        }
        if (found) {
            storage.saveTasks(tasks);
            System.out.println("Task " + id + " has been updated");
        }
        else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void markInProgress(int id) {
        boolean found = false;

        for (Task task : tasks) {
            if(task.getId() == id) {
                task.setStatus(TaskStatus.IN_PROGRESS);
                task.setUpdatedAt(LocalDateTime.now().toString());
                found = true;
                break;

            }
        }
        if (found) {
            storage.saveTasks(tasks);
            System.out.println("Task " + id + " has been marked as in progress");
        }
        else {
            System.out.println("Task with ID " + id + " not found.");
        }

    }

    public void markDone(int id) {
        boolean found = false;
        for (Task task : tasks) {
            if(task.getId() == id) {
                task.setStatus(TaskStatus.DONE);
                task.setUpdatedAt(LocalDateTime.now().toString());
                found = true;
                break;
            }
        }
        if (found) {
            storage.saveTasks(tasks);
            System.out.println("Task " + id + " has been marked as done");
        }
        else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void listTasks(String filter) {
        for (Task task : tasks) {
            if (filter == null || task.getStatus().toString().equalsIgnoreCase(filter.replace("-", "_"))) {
                System.out.println("[" + task.getId() + "] " + task.getDescription() + " - " + task.getStatus());
            }


        }
    }
}
