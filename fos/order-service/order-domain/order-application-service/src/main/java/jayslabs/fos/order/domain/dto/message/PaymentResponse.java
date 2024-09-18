//PaymentResponse using lombok @Data annotation. 
//private fields: id, sagaId, orderId, paymentId, 
//customerId, price, paymentStatus, createdAt, failureMessages
package jayslabs.fos.order.domain.dto.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import jayslabs.fos.domain.vo.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String paymentId;
    private String customerId;
    private BigDecimal price;
    private PaymentStatus paymentStatus;
    private Instant createdAt;
    private List<String> failureMessages;
}