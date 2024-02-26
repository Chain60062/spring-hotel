package viniciusmmenezes.springhotel.domain;

import java.io.Serializable;

import viniciusmmenezes.springhotel.domain.utils.PhoneId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Phone implements Serializable {
    
    public Phone(PhoneId phoneId) {
        this.phoneId = phoneId;
    }
    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private PhoneId phoneId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
}
