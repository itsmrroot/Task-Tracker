# Task Tracker CLI

A lightweight command-line task manager built in Java. Add, update, and track your tasks from the terminal — no internet, no account, no fluff. Tasks are saved locally in a `tasks.json` file.

## Features

- Add, update, and delete tasks
- Mark tasks as **in-progress** or **done**
- Filter tasks by status
- Persistent storage via a local JSON file
- Zero external dependencies — pure Java

## Installation

### Requirements
- Java 11 or higher installed on your machine

### Steps

1. Download the latest `task-tracker.jar` from the [Releases](../../releases) page

2. Set up a terminal alias so you can run it from anywhere:

```bash
echo 'alias task="java -jar /path/to/task-tracker.jar"' >> ~/.zshrc
source ~/.zshrc
```

> On Linux or using bash, replace `~/.zshrc` with `~/.bashrc`

3. Verify it works:
```bash
task help
```

## Usage

```bash
task add "Task description"
task update <id> "New description"
task delete <id>
task mark-in-progress <id>
task mark-done <id>
task list
task list todo
task list in-progress
task list done
task help
```

## Examples

```bash
# Add tasks
task add "Buy groceries"
task add "Finish project report"

# List all tasks
task list
# [1] Buy groceries - TODO
# [2] Finish project report - TODO

# Update a task
task update 2 "Finish and submit project report"

# Mark as in progress
task mark-in-progress 1

# Mark as done
task mark-done 1

# List only done tasks
task list done
# [1] Buy groceries - DONE

# Delete a task
task delete 2
```

## Task Properties

| Field | Description |
|-------|-------------|
| `id` | Auto-incremented unique identifier |
| `description` | What the task is about |
| `status` | `TODO`, `IN_PROGRESS`, or `DONE` |
| `createdAt` | Timestamp when the task was created |
| `updatedAt` | Timestamp of the last update |

## Storage

Tasks are saved in a `tasks.json` file in the directory where you run the command. The file is created automatically on first use.

## Project Structure

```
src/com/company/tasks/
├── Main.java          # Entry point, CLI argument parsing
├── Task.java          # Task data model
├── TaskStatus.java    # Enum: TODO, IN_PROGRESS, DONE
├── TaskManager.java   # Business logic
└── Storage.java       # Read/write tasks.json
```

## Building from Source

1. Clone the repo:
```bash
git clone https://github.com/your-username/Task-Tracker.git
cd Task-Tracker
```

2. Open in IntelliJ IDEA

3. Build the JAR: **Build → Build Artifacts → task-tracker → Build**

4. The JAR will be at:
```
out/artifacts/task_tracker/task-tracker.jar
```

## License

MIT
