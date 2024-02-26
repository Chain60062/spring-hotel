package viniciusmmenezes.springhotel.domain.utils;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class PhoneId implements Serializable{
    private static final long serialVersionUID = 1L;
    private String ddd;
    private String number;
}