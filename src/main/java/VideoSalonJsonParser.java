import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VideoSalonJsonParser {

    ObjectMapper mapper = new ObjectMapper();
    StringWriter writer = new StringWriter();

    public VideoSalonJsonParser() {

    }

    public List<VideoTape> getVideoTapeListFromJson(String jsonList) throws IOException {
        return  mapper.readValue(jsonList, new TypeReference<List<VideoTape>>(){});
    }

    public String convertVideoTapeListToJson(List<VideoTape> videoTapeList) throws IOException {
        mapper.writeValue(writer, videoTapeList);
        return writer.toString();
    }

    public  List<VideoTape> readJsonListFromFile(File file) throws IOException {
       Scanner scanner = new Scanner(file);
       StringBuilder stringBuilder = new StringBuilder();
       while(scanner.hasNextLine()){
           stringBuilder.append(scanner.nextLine());
       }
       String jsonArray = stringBuilder.toString().trim();
       return  mapper.readValue(jsonArray, new TypeReference<List<VideoTape>>(){});

   }

   public void WriteListAsJsonToFile(File file, List<VideoTape> videoTapeList) throws IOException {
       mapper.writeValue(writer, videoTapeList);
       try (FileWriter videoTapesWriter = new FileWriter(file)) {
           videoTapesWriter.write(writer.toString());
       } catch (IOException e) {
           e.printStackTrace();
       }

   }


}
