package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSONfile {
    public static void writeee() {

        File file = new File("file.txt");
        try (FileWriter writer = new FileWriter(file)) {
            String text = "name age\n" +
                    "alice 21\n" +
                    "ryan 30";
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createuserJson() {
        List<String> col1 = new ArrayList<>();
        List<String> col2 = new ArrayList<>();
        List<User> user = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));


            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(" ");
                col1.add(columns[0]);
                col2.add(columns[1]);
            }

            user.add(new User(col1.get(1), Integer.parseInt(col2.get(1))));
            user.add(new User(col1.get(2), Integer.parseInt(col2.get(2))));

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
