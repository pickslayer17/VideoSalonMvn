import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VideoSalonJsonParser {

    public static void main(String[] args) throws IOException {


        VideoTape videoTape = new VideoTape("Alice in Wonderland", true, "Igor", LocalDateTime.now());
        VideoTape videoTape2 = new VideoTape("Resident Evil", true, "Igor", LocalDateTime.now());
        VideoTape videoTape3 = new VideoTape("Batman. The beginning", true, "Igor", LocalDateTime.now());
        VideoTape videoTape4 = new VideoTape("The amazing Spider-Man", true, "Igor", LocalDateTime.now());

        List<VideoTape> videoTapeList = new ArrayList<>();
        videoTapeList.add(videoTape);
        videoTapeList.add(videoTape2);
        videoTapeList.add(videoTape3);
        videoTapeList.add(videoTape4);

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(writer, videoTapeList);
        String result =  writer.toString();
        System.out.println(result);
        System.out.println("-----");

//        Scanner scanner = new Scanner(new File("src/main/java/VideoTapes.txt"));
//        String jsonArray = scanner.nextLine();
        List<VideoTape> jVideoTapeList = mapper.readValue(result, new TypeReference<List<VideoTape>>(){});
        System.out.println(jVideoTapeList.toString());
        System.out.println(jVideoTapeList.get(0));

       }

    ObjectMapper mapper = new ObjectMapper();
    StringWriter writer = new StringWriter();

    public VideoSalonJsonParser() {

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
