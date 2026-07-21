package com.company.tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final String filename;

    public Storage() {
        this("tasks.json");
    }

    public Storage(String filename) {
        this.filename = filename;
    }

    public void saveTasks(List<Task> tasks) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < tasks.size(); i++) {

            Task task = tasks.get(i);

            sb.append("{");
            sb.append("\"id\":" + task.getId() + ",");
            sb.append("\"description\":\"" + escape(task.getDescription()) + "\",");
            sb.append("\"status\":\"" + task.getStatus() + "\",");
            sb.append("\"createdAt\":\"" + escape(task.getCreatedAt()) + "\",");
            sb.append("\"updatedAt\":\"" + escape(task.getUpdatedAt()) + "\"");
            sb.append("}");
            if (i < tasks.size() - 1) sb.append(",");
        }
        sb.append("]");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }

    public List<Task> loadTasks() {

        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            if (sb.length() != 0) {
                String json = sb.toString();
                List<Task> tasks = new ArrayList<>();

                int start = json.indexOf("{");

                while (start != -1) {
                    int end = json.indexOf("}", start);
                    String block = json.substring(start, end + 1);

                    String description = extractValue(block, "description");
                    String status = extractValue(block, "status");
                    String createdAt = extractValue(block, "createdAt");
                    String updatedAt = extractValue(block, "updatedAt");

                    String idSearch = "\"id\":";
                    int idStart = block.indexOf(idSearch) + idSearch.length();
                    int idEnd = block.indexOf(",", idStart);
                    int id = Integer.parseInt(block.substring(idStart, idEnd));

                    Task task = new Task(id, description, createdAt);
                    task.setStatus(TaskStatus.valueOf(status));
                    task.setUpdatedAt(updatedAt);
                    tasks.add(task);

                    start = json.indexOf("{", end);
                }
                return tasks;

            } else {
                return new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private String extractValue(String block, String key) {
        String search = "\"" + key + "\":\"";
        int start = block.indexOf(search) + search.length();
        int end = start;

        // Scan forward, skipping over escaped quotes (\") until we hit a real closing quote
        while (end < block.length()) {
            if (block.charAt(end) == '"' && block.charAt(end - 1) != '\\') {
                break;
            }
            end++;
        }

        String raw = block.substring(start, end);
        return unescape(raw);
    }

    private String unescape(String value) {
        return value
                .replace("\\\"", "\"")
                .replace("\\n", "\n")
                .replace("\\\\", "\\");
    }
}
