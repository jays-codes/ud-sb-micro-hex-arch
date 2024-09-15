package jayslabs.fos.order.domain.event;

import java.time.ZonedDateTime;

import jayslabs.fos.order.domain.entity.Order;

public class OrderCreatedEvent extends OrderEvent {

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
