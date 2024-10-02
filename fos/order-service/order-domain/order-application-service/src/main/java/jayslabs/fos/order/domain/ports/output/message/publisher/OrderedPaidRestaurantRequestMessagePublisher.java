package jayslabs.fos.order.domain.ports.output.message.publisher;

import jayslabs.fos.domain.event.DomainEventPublisher;
import jayslabs.fos.order.domain.event.OrderPaidEvent;

public interface OrderedPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {

}
