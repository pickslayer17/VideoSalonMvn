import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class VideoSalonApp {
    public static Path path = Paths.get("src/main/java/VideoTapes.txt");

    public final static String VIDEO_TAPES_FILE_NAME = "src/main/java/VideoTapes.txt";

    public static void main(String[] args) throws IOException {

        System.out.println(path);
        System.out.println(path.toAbsolutePath());
        System.out.println(path.toRealPath());


        VideoSalon videoSalon = new VideoSalon(VIDEO_TAPES_FILE_NAME, "Kupalinka");
        System.out.println("Welcome to our video salon \"" + videoSalon.getSalonName() + "\"!");
        System.out.println("Please, type the action:");
        System.out.println("1. Show available video tapes");
        System.out.println("2. Show rent video tapes");
        System.out.println("3. Rent the tape");
        System.out.println("4. Back the tape");
        System.out.println("5. Exit");
        Scanner userInputScanner = new Scanner(System.in);
        while(true) {

            int userAction = userInputScanner.nextInt();
            Scanner userInputSwitch = new Scanner(System.in);
            switch (userAction) {
                case 1:
                    System.out.println("-------Show Available------");
                    videoSalon.showAvailableVideoTapes();
                    System.out.println("---------------------------");
                    break;
                case 2:
                    System.out.println("----------Show Rent--------");
                    videoSalon.showRentVideoTapes();
                    System.out.println("---------------------------");
                    break;
                case 3:
                    System.out.println("-----------Rent The Tape----");
                    System.out.println("Please type name of the film:");
                    String filmName = userInputSwitch.nextLine();
                    System.out.println("And please also type your name:");
                    String clientName = userInputSwitch.nextLine();

                    videoSalon.rentVideoTape(filmName, clientName);

                    System.out.println("---------------------------");
                    break;
                case 4:
                    System.out.println("-----------Back The Tape-----");
                    System.out.println("Please type name of the film:");
                    filmName = userInputSwitch.nextLine();
                    System.out.println("And please also type your name:");
                    clientName = userInputSwitch.nextLine();

                    videoSalon.backVideoTape(filmName, clientName);

                    System.out.println("---------------------------");
                    break;
                case 5:
                    System.exit(0);
                default:
        }


        }


    }
}
