
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VideoSalon {
    private File videoTapesFile;
    private String salonName;
    private List<VideoTape> videoTapeList = new ArrayList<>();
    private VideoSalonJsonParser videoSalonJsonParser = new VideoSalonJsonParser();

    public String getSalonName() {
        return salonName;
    }

    public VideoSalon(String videoTapesFileName, String salonName) throws IOException {
        videoTapesFile = new File(videoTapesFileName);
        this.salonName = salonName;
        readTapesFromFile();
    }

    private void readTapesFromFile() throws  IOException{
        videoTapeList = videoSalonJsonParser.readJsonListFromFile(videoTapesFile);
    }

    private void updateFile()  {
        try {
            videoSalonJsonParser.WriteListAsJsonToFile(videoTapesFile, videoTapeList);
        } catch (IOException e) {
            System.out.println("The problem with saving data occurs. The data could be lost.");
            e.printStackTrace();
        }
    }

    public void showAvailableVideoTapes() {
        for(VideoTape videoTape : videoTapeList){
            if(videoTape.isAvailable()) {
                System.out.println(videoTape);
            }
        }
    }

    public void showRentVideoTapes() {
        for(VideoTape videoTape : videoTapeList){
            if(!videoTape.isAvailable()) {
                System.out.println(videoTape);
            }
        }
    }

    public void rentVideoTape(String videoTapeName, String clientName) {
        boolean isRentSuccess = false;
        for(VideoTape videoTape : videoTapeList){
           if(videoTape.getFilmName().equalsIgnoreCase(videoTapeName) && videoTape.isAvailable()) {
               videoTape.setAvailable(false);
               videoTape.setClientName(clientName);
               videoTape.setRentDate(LocalDateTime.now());
               isRentSuccess = true;
               break;
           }
        }
        if(isRentSuccess){
            System.out.println("Thank you, " + clientName + ", for using our video salon. ");
            System.out.println("You take the \"" + videoTapeName + "\" video tape.");
            System.out.println("We will wait for tape back for a month. Have a good watching!");
            updateFile();
        } else {
            System.out.println("Sorry, we don't have this tape this time");

        }
    }



    public void backVideoTape(String videoTapeName, String clientName) {
        boolean isBackSuccess = false;
        for(VideoTape videoTape : videoTapeList){
            if(videoTape.getFilmName().equalsIgnoreCase(videoTapeName) && !videoTape.isAvailable() && videoTape.getClientName().equalsIgnoreCase(clientName)) {
                videoTape.setAvailable(true);
                videoTape.setRentDate(LocalDateTime.now());
                isBackSuccess = true;
                break;
            }
        }
        if(isBackSuccess){
            System.out.println("Thank you for tape back, " + clientName + ". Here is your passport :)");
            updateFile();
        } else {
            System.out.println("Sorry, it's not our video tape.");
        }

    }
}
