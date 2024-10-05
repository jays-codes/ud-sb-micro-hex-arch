package jayslabs.fos.order.domain;

import org.springframework.stereotype.Component;

import jayslabs.fos.order.domain.dto.create.CreateOrderCommand;
import jayslabs.fos.order.domain.dto.create.CreateOrderResponse;
import jayslabs.fos.order.domain.mapper.OrderMapper;
import jayslabs.fos.order.domain.ports.output.repository.CustomerRepository;
import jayslabs.fos.order.domain.ports.output.repository.OrderRepository;
import jayslabs.fos.order.domain.ports.output.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDomainService orderDomainService;
    private final OrderMapper orderMapper;

    OrderCreateCommandHandler(OrderRepository orderRepository, CustomerRepository customerRepository,
            RestaurantRepository restaurantRepository, OrderDomainService orderDomainService,
            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDomainService = orderDomainService;
        this.orderMapper = orderMapper;
    }

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }
}
