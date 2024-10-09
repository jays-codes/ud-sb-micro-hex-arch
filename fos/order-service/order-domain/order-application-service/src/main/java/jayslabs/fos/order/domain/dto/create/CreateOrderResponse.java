//CreateOrderResponse using lombok @Data annotation. 
//Immutable. NotNull fields. UUID for orderTrackingId.

package jayslabs.fos.order.domain.dto.create;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jayslabs.fos.domain.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    @NotNull private final UUID orderTrackingId;
    @NotNull private final OrderStatus orderStatus;
    @NotNull private final String message;
}
