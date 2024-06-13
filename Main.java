package parserJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String json = readString("new_data.json");
        //  System.out.println(json);
        List<Employee> list = jsonToList(json);
        list.forEach(System.out::println);
    }
    private static List<Employee> jsonToList(String jsonToClass) {
        List<Employee> employeeList = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        JSONParser jsonParser = new JSONParser();

        try {
            Object jsonObj = jsonParser.parse(jsonToClass);
            JSONArray jsonArray = (JSONArray) jsonObj;
            for (Object obj : jsonArray) {
                employeeList.add(gson.fromJson(obj.toString(), Employee.class));
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return employeeList;
    }
    private static String readString(String fileJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileJson)));
    }
}
