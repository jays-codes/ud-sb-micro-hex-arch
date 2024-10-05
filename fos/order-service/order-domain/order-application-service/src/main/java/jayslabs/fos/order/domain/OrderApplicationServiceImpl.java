package jayslabs.fos.order.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jayslabs.fos.order.domain.dto.create.CreateOrderCommand;
import jayslabs.fos.order.domain.dto.create.CreateOrderResponse;
import jayslabs.fos.order.domain.dto.track.TrackOrderQuery;
import jayslabs.fos.order.domain.dto.track.TrackOrderResponse;
import jayslabs.fos.order.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
            OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
