package jayslabs.fos.order.domain.entity;

import jayslabs.fos.domain.entity.BaseEntity;
import jayslabs.fos.domain.vo.Money;
import jayslabs.fos.domain.vo.OrderId;
import jayslabs.fos.order.domain.vo.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private final Product product;
    private OrderId orderId;
    private final Money price;
    private final Integer quantity;
    private final Money subTotal;

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        product = builder.product;
        price = builder.price;
        quantity = builder.quantity;
        subTotal = builder.subTotal;
    }

    // initialize order item with order id and order item id
    public void initializeOrderItem(OrderId orderId, 
    OrderItemId orderItemId) {
        super.setId(orderItemId);
        this.orderId = orderId;
    }

    //getters
    public Product getProduct() {
        return product;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Money getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getSubTotal() {
        return subTotal;
    }


    public static final class Builder {
        private Product product;
        private OrderItemId orderItemId;
        private Money price;
        private Integer quantity;
        private Money subTotal;

        private Builder() {
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

    boolean isPriceValid() {
        return 
        price.isGreaterThanZero() &&
        price.equals(product.getPrice()) &&
        quantity > 0 &&
        price.multiply(quantity).equals(subTotal);
    }
}
