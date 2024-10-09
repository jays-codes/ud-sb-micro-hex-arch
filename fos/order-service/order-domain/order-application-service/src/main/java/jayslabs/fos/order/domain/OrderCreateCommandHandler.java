package jayslabs.fos.order.domain;

import jayslabs.fos.order.domain.dto.create.CreateOrderCommand;
import jayslabs.fos.order.domain.dto.create.CreateOrderResponse;

public interface OrderCreateCommandHandler {
    CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand);
}
