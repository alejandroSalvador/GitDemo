package dataPrivider;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    /*
    public  HashMap<String, String> getJsonDataToMap() throws IOException{
        //Read json to string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")
                    + "//src//test//java//dataPrivider//purchaseOrder.json"), StandardCharsets.UTF_8);

        //Stringt to HashMap jackson DataBind
        ObjectMapper mapper = new JsonMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return (HashMap<String, String>) data;  //

    }

     */

}
