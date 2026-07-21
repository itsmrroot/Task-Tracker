package com.company.tasks;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        if (args.length == 0) {
            printHelp();
            return;
        }

        switch (args[0]) {
            case "help":
                printHelp();
                break;
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
    private static void printHelp() {
        System.out.println("Task Tracker CLI");
        System.out.println("================");
        System.out.println("Usage: task <command> [arguments]");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  add <description>           Add a new task");
        System.out.println("  update <id> <description>   Update a task");
        System.out.println("  delete <id>                 Delete a task");
        System.out.println("  mark-in-progress <id>       Mark task as in progress");
        System.out.println("  mark-done <id>              Mark task as done");
        System.out.println("  list                        List all tasks");
        System.out.println("  list todo                   List todo tasks");
        System.out.println("  list in-progress            List in-progress tasks");
        System.out.println("  list done                   List done tasks");
        System.out.println("  help                        Show this help message");
    }
}
