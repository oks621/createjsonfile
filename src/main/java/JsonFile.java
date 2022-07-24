package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class JsonFile {
    public void writeFile() {

        File file = new File("file.txt");
        try (FileWriter writer = new FileWriter(file)) {
            String text = "name age\n" +
                    "alice 21\n" +
                    "ryan 30\n";
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createJson() {
        List<User> user = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] array = line.split(" ");
                for (int j = 0; j < array.length - 1; j++) {
                    for (int i = j + 1; i < array.length; i++) {
                        user.add(new User(array[j], Integer.parseInt(array[i])));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);
        System.out.println(json);
        try {
            FileWriter fileWriter = new FileWriter("user.json");
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
