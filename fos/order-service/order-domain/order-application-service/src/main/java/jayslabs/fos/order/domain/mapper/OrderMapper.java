package jayslabs.fos.order.domain.mapper;

import jayslabs.fos.order.domain.dto.create.CreateOrderCommand;
import jayslabs.fos.order.domain.dto.create.CreateOrderResponse;
import jayslabs.fos.order.domain.entity.Order;
import jayslabs.fos.order.domain.entity.Restaurant;

public interface OrderMapper {
    Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand);   
    Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand);
    CreateOrderResponse orderToCreateOrderResponse(Order order);
}
