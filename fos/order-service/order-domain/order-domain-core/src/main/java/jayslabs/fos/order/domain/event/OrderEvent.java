package jayslabs.fos.order.domain.event;

import java.time.ZonedDateTime;

import jayslabs.fos.domain.event.DomainEvent;
import jayslabs.fos.order.domain.entity.Order;

public class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;

    //constructor
    public OrderEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

//getters
    public Order getOrder() {
        return order;
    }


    public ZonedDateTime getCreatedAt() {
    return createdAt;
}

}


