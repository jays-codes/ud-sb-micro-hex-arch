package jayslabs.fos.order.domain;

import org.springframework.stereotype.Component;

import jayslabs.fos.order.domain.dto.track.TrackOrderQuery;
import jayslabs.fos.order.domain.dto.track.TrackOrderResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
