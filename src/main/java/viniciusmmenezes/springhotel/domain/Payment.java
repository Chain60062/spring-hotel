package viniciusmmenezes.springhotel.domain;

import java.io.Serializable;

import viniciusmmenezes.springhotel.domain.enums.PaymentMethods;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment implements Serializable {

    public Payment(Long id, PaymentMethods paymentMethod) {
        this.id = id;
        this.paymentMethod = paymentMethod;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PaymentMethods paymentMethod;
    @OneToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    private Bill bill;
}