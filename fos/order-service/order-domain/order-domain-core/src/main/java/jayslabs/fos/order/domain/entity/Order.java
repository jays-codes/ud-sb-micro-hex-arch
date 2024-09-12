package jayslabs.fos.order.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jayslabs.fos.domain.entity.BaseEntity;
import jayslabs.fos.domain.vo.CustomerId;
import jayslabs.fos.domain.vo.Money;
import jayslabs.fos.domain.vo.OrderId;
import jayslabs.fos.domain.vo.OrderStatus;
import jayslabs.fos.domain.vo.RestaurantId;
import jayslabs.fos.order.domain.vo.OrderItemId;
import jayslabs.fos.order.domain.vo.TrackingId;

public class Order extends BaseEntity<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final Money price;
    private final List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    // Getters
    public CustomerId getCustomerId() { return customerId; }
    public RestaurantId getRestaurantId() { return restaurantId; }
    public Money getPrice() { return price; }
    public List<OrderItem> getItems() { return items; }
    public TrackingId getTrackingId() { return trackingId; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public List<String> getFailureMessages() { return failureMessages; }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {}

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        failureMessages = new ArrayList<>();
        initializeOrderItems();
    }

    // initialize order items with order id and order item id
    private void initializeOrderItems() {
        long itemId = 1;
        for (OrderItem item : items) {
            item.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }

    public void validateOrder() {
        //validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
        // validateWithRestaurantMenu();
        // validateItemQuantityWithRestaurantMenu();
        // validateRestaurantActive();
        // validateOrderItemsPrice();
        // validateOrderItemsQuantity();
        // validateOrderItemsPriceWithRestaurant();
        // validateOrderItemsQuantityWithRestaurant();
    }

    private void validateItemsPrice() {
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("Invalid items: " + items);
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new IllegalStateException("Invalid price: " + price);
        }
    }

    

}