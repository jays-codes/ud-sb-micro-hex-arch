package jayslabs.fos.order.domain.ports.output.message.publisher;

import jayslabs.fos.domain.event.DomainEventPublisher;
import jayslabs.fos.order.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {

}
