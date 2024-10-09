package jayslabs.fos.order.domain.mapper;

import java.util.Currency;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import jayslabs.fos.domain.vo.CustomerId;
import jayslabs.fos.domain.vo.Money;
import jayslabs.fos.domain.vo.ProductId;
import jayslabs.fos.domain.vo.RestaurantId;
import jayslabs.fos.order.domain.dto.create.CreateOrderCommand;
import jayslabs.fos.order.domain.dto.create.CreateOrderResponse;
import jayslabs.fos.order.domain.dto.create.OrderAddressDTO;
import jayslabs.fos.order.domain.dto.create.OrderItemDTO;
import jayslabs.fos.order.domain.entity.Order;
import jayslabs.fos.order.domain.entity.OrderItem;
import jayslabs.fos.order.domain.entity.Product;
import jayslabs.fos.order.domain.entity.Restaurant;
import jayslabs.fos.order.domain.vo.StreetAddress;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream()
                .map(product -> new Product(new ProductId(product.getProductId()))).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .price(new Money(createOrderCommand.getPrice(), Currency.getInstance("CAD")))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .build();
    }

    @Override
    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddressDTO address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity());
    }

    private List<OrderItem> orderItemsToOrderItemEntities(List<OrderItemDTO> items) {
        return items.stream()
                .map(item -> OrderItem.builder()
                        .product(new Product(new ProductId(item.getProductId())))
                        .price(new Money(item.getPrice(), Currency.getInstance("CAD")))
                        .quantity(item.getQuantity())
                        .subTotal(new Money(item.getSubTotal(), Currency.getInstance("CAD")))
                        .build())
                .collect(Collectors.toList());
    }
}
