package jayslabs.fos.order.domain.entity;

import java.util.List;

import jayslabs.fos.domain.entity.AggregateRoot;
import jayslabs.fos.domain.vo.RestaurantId;

public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private boolean active;

    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    //constructor using Builder pattern
    //static builder method returning Builder
    private Restaurant(Builder builder) {
        super.setId(builder.restaurantId);
        this.products = builder.products;
        this.active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    //Builder inner class
    public static class Builder {
        private RestaurantId restaurantId;
        private List<Product> products;
        private boolean active;

        public Builder restaurantId(RestaurantId restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder products(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);    
        }
    }
}
