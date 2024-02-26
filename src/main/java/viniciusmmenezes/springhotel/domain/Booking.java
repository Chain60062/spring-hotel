package viniciusmmenezes.springhotel.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.Set;
import java.util.HashSet;
import org.hibernate.annotations.CreationTimestamp;

import viniciusmmenezes.springhotel.domain.enums.BookingStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant inDateTime;
    private Instant outDateTime;
    private BookingStatus status;
    @CreationTimestamp
    private Instant createdOn;
    @ManyToMany
    @JoinTable(name = "booking_guests", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "cpf"))
    private Set<Guest> guests = new HashSet<>();
    @OneToMany(mappedBy = "booking")
    private Set<Room> rooms = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
}