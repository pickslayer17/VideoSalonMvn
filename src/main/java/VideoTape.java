import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;

@JsonAutoDetect
public class VideoTape {
    private String filmName;
    private boolean isAvailable;
    private String clientName;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime rentDate;

    //for Jackson
    public VideoTape (){}

    public VideoTape(String filmName, boolean isAvailable, String clientName, LocalDateTime rentDate) {
        this.filmName = filmName;
        this.isAvailable = isAvailable;
        this.clientName = clientName;
        this.rentDate = rentDate;
    }

    public String getFilmName() {
        return filmName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDateTime getRentDate() {
        

        return rentDate;
    }

    @JsonIgnore
    public String getRendDateString(){
        return rentDate != null ? rentDate.toString() : "";
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public String toString() {
        return filmName + " - " +
                isAvailable + " - " +
                clientName + " - " +
                rentDate;

    }
}
