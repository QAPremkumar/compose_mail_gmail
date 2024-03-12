package org.hplx.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ReadJsonData {
    public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    public static void main(String[] args) throws IOException {
        String jsonFilePath = "/Users/abhinavkumardubey/EMR3_UI_Automation/src/test/resources/testData/newPatientRegistrationData.json";
        List<HashMap<String, String>> data = getJsonData(jsonFilePath);

        if (!data.isEmpty()) {
            int randomIndex = new Random().nextInt(data.size());
            HashMap<String, String> randomData = data.get(randomIndex);
            System.out.println("Last Name: " + randomData.get("lName"));
        } else {
            System.out.println("No JSON data loaded.");
        }
    }
}

