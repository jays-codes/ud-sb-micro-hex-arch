package jayslabs.fos.order.domain.ports.input.message.listener;

import jayslabs.fos.order.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentCancelled(PaymentResponse paymentResponse);
}
