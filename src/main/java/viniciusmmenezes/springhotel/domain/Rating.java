package viniciusmmenezes.springhotel.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating implements Serializable {

    public Rating(Long id, Short stars, String comment) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short stars;
    private String comment;
    private User user;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private HotelUnit hotelUnit;
}