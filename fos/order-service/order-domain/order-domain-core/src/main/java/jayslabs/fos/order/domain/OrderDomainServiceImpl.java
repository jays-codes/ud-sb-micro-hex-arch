//OrderDomainServiceImpl.java
package jayslabs.fos.order.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import jayslabs.fos.domain.vo.ProductId;
import jayslabs.fos.order.domain.entity.Order;
import jayslabs.fos.order.domain.entity.Product;
import jayslabs.fos.order.domain.entity.Restaurant;
import jayslabs.fos.order.domain.event.OrderCancelledEvent;
import jayslabs.fos.order.domain.event.OrderCreatedEvent;
import jayslabs.fos.order.domain.event.OrderPaidEvent;
import jayslabs.fos.order.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    private static final String UTC = "UTC";
    
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);

        //check if order can be created in the restaurant
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with ID: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    /*
    implement setOrderProductInformation(). Update the product for each order item
     based on products available in the restaurant. use streams and collectors to create 
     a map of restaurant products and then update the order items with the corresponding 
     product details from the restaurant.
    */    
    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        Map<ProductId, Product> restaurantProducts = restaurant.getProducts().stream()
            .collect(Collectors.toMap(Product::getId, Function.identity()));

        order.getItems().forEach(orderItem -> {
            Product currentProduct = orderItem.getProduct();
            Product restaurantProduct = restaurantProducts.get(currentProduct.getId());
            if (restaurantProduct != null) {
                currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
            }
        });
    }
    
    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with ID: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with ID: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with ID: {} is cancelled", order.getId().getValue());   
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with ID " + restaurant.getId().getValue() + " is currently not active!");
        }
    }
}

