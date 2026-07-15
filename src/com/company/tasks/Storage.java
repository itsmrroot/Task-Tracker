package com.company.tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {



    public void saveTasks(List<Task> tasks) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(int i = 0; i < tasks.size(); i++) {

            Task task = tasks.get(i);

                sb.append("{");
                sb.append("\"id\":" + task.getId() + ",");
                sb.append("\"description\":\"" + task.getDescription() + "\",");
                sb.append("\"status\":\"" + task.getStatus() + "\",");
                sb.append("\"createdAt\":\"" + task.getCreatedAt() + "\",");
                sb.append("\"updatedAt\":\"" + task.getUpdatedAt() + "\"");
                sb.append("}");
                if (i < tasks.size() - 1) sb.append(",");



        }
        sb.append("]");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.json"));
            bw.write(sb.toString());
            bw.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }


    }

    public List<Task>  loadTasks() {

        File file = new File("tasks.json");
        if (!file.exists()) return new ArrayList<>();

        try {
            BufferedReader br   = new BufferedReader(new FileReader("tasks.json"));
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

                    start  = json.indexOf("{", end);

                }
                return tasks;

            }else {
                return new ArrayList<>();
            }


        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>() ;

    }

    private String extractValue(String block, String key) {
        String search = "\"" + key + "\":\"";
        int start = block.indexOf(search) + search.length();
        int end = block.indexOf("\"", start);
        return block.substring(start, end);
    }

}
