//TrackOrderResponse using lombok @Data annotation. 
//Immutable. NotNull fields. UUID for orderTrackingId. 
//OrderStatus enum. List of String for failureMessages.

package jayslabs.fos.order.domain.dto.track;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jayslabs.fos.domain.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
    @NotNull private final UUID orderTrackingId;
    @NotNull private final OrderStatus orderStatus;
    private final List<String> failureMessages;
}




