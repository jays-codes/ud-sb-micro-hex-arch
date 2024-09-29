package jayslabs.fos.order.domain.ports.input.service;

import jakarta.validation.Valid;
import jayslabs.fos.order.domain.dto.create.CreateOrderCommand;
import jayslabs.fos.order.domain.dto.create.CreateOrderResponse;
import jayslabs.fos.order.domain.dto.track.TrackOrderQuery;
import jayslabs.fos.order.domain.dto.track.TrackOrderResponse;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
