//RestaurantApprovalResponse using lombok @Data annotation. 
//private fields: id, sagaId, orderId, restaurantId, 
//orderApprovalStatus, createdAt, failureMessages

package jayslabs.fos.order.domain.dto.message;

import java.time.Instant;
import java.util.List;

import jayslabs.fos.domain.vo.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String restaurantId;
    private OrderApprovalStatus orderApprovalStatus;
    private Instant createdAt;
    private List<String> failureMessages;
}