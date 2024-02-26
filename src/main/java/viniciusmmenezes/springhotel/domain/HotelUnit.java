package viniciusmmenezes.springhotel.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HotelUnit implements Serializable {

    public HotelUnit(Long id, String name, Instant checkInTime, Instant checkOutTime) {
        this.id = id;
        this.name = name;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Instant checkInTime;
    private Instant checkOutTime;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "hotelUnit")
    private Set<Rating> ratings = new HashSet<>();
}