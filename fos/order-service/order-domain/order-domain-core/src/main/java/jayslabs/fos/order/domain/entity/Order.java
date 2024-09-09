//create Order aggregate root class typed with OrderId
package jayslabs.fos.order.domain.entity;

import jayslabs.fos.domain.entity.AggregateRoot;
import jayslabs.fos.domain.vo.OrderId;
import java.time.ZonedDateTime;
import java.util.List;
import jayslabs.fos.domain.vo.CustomerId;
import jayslabs.fos.domain.vo.RestaurantId;
import jayslabs.fos.domain.vo.OrderStatus;
import jayslabs.fos.order.domain.vo.StreetAddress;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress streetAddress;


    private final List<Product> products;
    private final OrderStatus orderStatus;
    private final String customerId;
    private final String restaurantId;
    private final ZonedDateTime orderDate;
}