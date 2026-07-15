package com.company.tasks;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        switch (args[0]) {
            case "add":
                manager.addTask(args[1]);
                break;
            case "delete":
                manager.deleteTask(Integer.parseInt(args[1]));
                break;
            case "update":
                manager.updateTask(Integer.parseInt(args[1]), args[2]);
                break;
            case "mark-in-progress":
                manager.markInProgress(Integer.parseInt(args[1]));
                break;
            case "mark-done":
                manager.markDone(Integer.parseInt(args[1]));
                break;
            case "list":
                manager.listTasks(args.length > 1 ? args[1] : null);
                break;
            default:
                System.out.println("Invalid command.");
        }




    }
}