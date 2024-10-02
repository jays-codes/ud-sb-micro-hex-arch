package jayslabs.fos.order.domain.ports.output.repository;

import java.util.Optional;

import jayslabs.fos.order.domain.entity.Order;
import jayslabs.fos.order.domain.vo.TrackingId;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
