package jayslabs.fos.order.domain;

import jayslabs.fos.order.domain.dto.track.TrackOrderQuery;
import jayslabs.fos.order.domain.dto.track.TrackOrderResponse;

public interface OrderTrackCommandHandler {
    TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery);
}
