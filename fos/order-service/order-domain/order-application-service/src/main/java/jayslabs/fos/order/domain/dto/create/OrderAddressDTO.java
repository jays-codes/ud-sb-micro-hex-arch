//OrderAddressDTO using lombok @Data annotation. 
//Immutable. NotNull fields. 
//String for street - max of 50 characters, postalCode - 6 characters, and city - max of 50 characters.

package jayslabs.fos.order.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderAddressDTO {
    @NotNull 
    @Size(max = 50) 
    private final String street;

    @NotNull 
    @Size(max = 6) 
    private final String postalCode;

    @NotNull
    @Size(max = 50)
    private final String city;
}
