package jayslabs.fos.order.domain;

import java.util.List;

import jayslabs.fos.order.domain.entity.Order;
import jayslabs.fos.order.domain.entity.Restaurant;
import jayslabs.fos.order.domain.event.OrderCancelledEvent;
import jayslabs.fos.order.domain.event.OrderCreatedEvent;
import jayslabs.fos.order.domain.event.OrderPaidEvent;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);
    OrderPaidEvent payOrder(Order order);
    void approveOrder(Order order);
    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);
    OrderCancelledEvent cancelOrder(Order order, List<String> failureMessages);
}
