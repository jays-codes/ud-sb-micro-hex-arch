//uses lombok @Data annotation. Immutable. NotNull fields.
//UUID for productId. Integer for quantity. 
//BigDecimal for price and subTotal.
package jayslabs.fos.order.domain.dto.create;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderItemDTO {
    @NotNull private final UUID productId;
    @NotNull private final Integer quantity;
    @NotNull private final BigDecimal price;
    @NotNull private final BigDecimal subTotal;
}
