package jayslabs.fos.order.domain.event;

import java.time.ZonedDateTime;

import jayslabs.fos.order.domain.entity.Order;

public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
