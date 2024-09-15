package jayslabs.fos.order.domain.event;

import java.time.ZonedDateTime;

import jayslabs.fos.order.domain.entity.Order;

public class OrderPaidEvent extends OrderEvent {

    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }   

}
