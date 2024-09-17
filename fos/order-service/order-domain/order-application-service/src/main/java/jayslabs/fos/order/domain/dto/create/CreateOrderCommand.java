//CreateOrderCommand using lombok @Data annotation with NotNull fields. 
//Immutable. UUID for id. BigDecimal for price. List of OrderItemDTO. OrderAddressDTO.

package jayslabs.fos.order.domain.dto.create;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    @NotNull private final UUID customerId;
    @NotNull private final UUID restaurantId;
    @NotNull private final BigDecimal price;
    @NotNull private final List<OrderItemDTO> items;
    @NotNull private final OrderAddressDTO address;
}

