package viniciusmmenezes.springhotel.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import viniciusmmenezes.springhotel.models.enums.RoomStatus;

@Data
@NoArgsConstructor
@Entity
public class Room implements Serializable {

    public Room(Long id, String number, String description, Short numberOfBeds, Short numberOfBedrooms,
            Short numberOfSuites) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.numberOfBeds = numberOfBeds;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfSuites = numberOfSuites;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String description;
    private Short numberOfBeds;
    private Short numberOfBedrooms;
    private Short numberOfSuites;
    private HotelUnit hotelUnit;
    private RoomStatus status;
    private Booking booking;
}