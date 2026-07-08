package com.company.tasks;

public class Task {
    private int id;
    private String description;
    private TaskStatus status;
    private String createdAt;
    private String updatedAt;

    public Task(int id, String description, String createdAt){
        this.id = id;
        this.description = description;
        this.updatedAt = createdAt;
        this.status=TaskStatus.TODO;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
