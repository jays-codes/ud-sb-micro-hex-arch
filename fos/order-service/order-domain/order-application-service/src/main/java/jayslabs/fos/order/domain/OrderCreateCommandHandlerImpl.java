package jayslabs.fos.order.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jayslabs.fos.order.domain.dto.create.CreateOrderCommand;
import jayslabs.fos.order.domain.dto.create.CreateOrderResponse;
import jayslabs.fos.order.domain.entity.Customer;
import jayslabs.fos.order.domain.entity.Order;
import jayslabs.fos.order.domain.entity.Restaurant;
import jayslabs.fos.order.domain.event.OrderCreatedEvent;
import jayslabs.fos.order.domain.exception.OrderDomainException;
import jayslabs.fos.order.domain.mapper.OrderMapper;
import jayslabs.fos.order.domain.ports.output.repository.CustomerRepository;
import jayslabs.fos.order.domain.ports.output.repository.OrderRepository;
import jayslabs.fos.order.domain.ports.output.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCreateCommandHandlerImpl implements OrderCreateCommandHandler {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDomainService orderDomainService;
    private final OrderMapper orderMapper;

    OrderCreateCommandHandlerImpl(OrderRepository orderRepository, 
            CustomerRepository customerRepository,
            RestaurantRepository restaurantRepository, 
            OrderDomainService orderDomainService,
            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDomainService = orderDomainService;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = persistOrder(createOrderCommand);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        
        CreateOrderResponse createOrderResponse = 
            orderMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(), 
            "Order created successfully");
        
        // orderCreatedPaymentRequestMessagePublisher 
        //     .publish(orderCreatedEvent);        

        return createOrderResponse;
    }

    private OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = 
            orderDomainService.validateAndInitiateOrder(order, restaurant);
        Order orderResult = saveOrder(order);
        log.info("Order is created with id: {}", orderResult.getId().getValue());

        return orderCreatedEvent;
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Order not saved!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> restaurantOptional =   restaurantRepository.findRestaurantInformation(restaurant);

        if (restaurantOptional.isEmpty()) {
            log.warn("Restaurant with id {} not found", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Restaurant with id " + createOrderCommand.getRestaurantId() + " not found");
        }

        return restaurantOptional.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);  
        if (customer.isEmpty()) {
            log.warn("Customer with id {} not found", customerId);
            throw new OrderDomainException("Customer with id " + customerId + " not found");
        }
    }
}
