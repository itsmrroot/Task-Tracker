# Task Tracker CLI

[![CI](https://github.com/itsmrroot/Task-Tracker/actions/workflows/ci.yml/badge.svg)](https://github.com/itsmrroot/Task-Tracker/actions/workflows/ci.yml)

A lightweight command-line task manager built in Java. Add, update, and track your tasks from the terminal — no internet, no account, no fluff. Tasks are saved locally in a `tasks.json` file.

## Features

- Add, update, and delete tasks
- Mark tasks as **in-progress** or **done**
- Filter tasks by status
- Persistent storage via a local JSON file
- Zero external dependencies at runtime — pure Java

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

Tasks are saved in a `tasks.json` file in the directory where you run the command. The file is created automatically on first use. Descriptions containing quote characters (`"`) are properly escaped, so special characters in task text won't corrupt the file.

## Project Structure

```
src/
├── main/java/com/company/tasks/
│   ├── Main.java          # Entry point, CLI argument parsing
│   ├── Task.java          # Task data model
│   ├── TaskStatus.java    # Enum: TODO, IN_PROGRESS, DONE
│   ├── TaskManager.java   # Business logic
│   └── Storage.java       # Read/write tasks.json
└── test/java/com/company/tasks/
    ├── TaskTest.java
    └── StorageTest.java
```
## Building from Source

Requires [Maven](https://maven.apache.org/) in addition to Java 11+.

```bash
git clone https://github.com/itsmrroot/Task-Tracker.git
cd Task-Tracker
mvn package
```

The runnable JAR will be at:
```
target/task-tracker.jar
```
Run it directly:
```bash
java -jar target/task-tracker.jar help
```

## Running Tests

```bash
mvn test
```

## License

MIT — see [LICENSE](LICENSE).
