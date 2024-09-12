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
import jayslabs.fos.order.domain.exception.OrderDomainException;
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
        validateInitialOrder();
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

    private void validateInitialOrder() {
        //check orderstatus is null
        if (orderStatus != null) {
            throw new OrderDomainException("Order is not in the correct state for initialization");
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero");
        }
    }

    // validate items price with order price using stream and reduce. 
    // validate item price using validateItemPrice()
    private void validateItemsPrice() {
        Money orderItemsTotal = items.stream().map(item -> {
            validateItemPrice(item);
            return item.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!price.equals(orderItemsTotal)) {
            throw new OrderDomainException("Total price: " + price.getAmount() 
            + " is not equal to the order items total: " + orderItemsTotal.getAmount());
        }
    }

    // validate item price using validateItemPrice()
    private void validateItemPrice(OrderItem item) {
        if (item.isPriceValid()==false) {
            throw new OrderDomainException("Order item price is not valid");
        }   
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not in the correct state for payment");
        }
        orderStatus = OrderStatus.PAID;
        trackingId = new TrackingId(UUID.randomUUID());
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in the correct state for approval");
        }
        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in the correct state for initialization of cancellation");
        }
        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    //if orderstatus is pending or cancelling, cancel the order
    public void cancel() {
        if (orderStatus != OrderStatus.PENDING || orderStatus != OrderStatus.CANCELLING) {
            throw new OrderDomainException("Order is not in the correct state for cancellation");
        }
        orderStatus = OrderStatus.CANCELLED;
        trackingId = null;
    }

    //update failure messages using stream to filter out empty messages
    //if failure messages is null, then set it to the parameter failure messages
    //if failure messages is not null, then add the parameter failure messages to the existing failure messages
    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages == null) {
            this.failureMessages = new ArrayList<>();
        }
        this.failureMessages.addAll(failureMessages.stream()
        .filter(message -> !message.isEmpty()).toList());
    }   
}