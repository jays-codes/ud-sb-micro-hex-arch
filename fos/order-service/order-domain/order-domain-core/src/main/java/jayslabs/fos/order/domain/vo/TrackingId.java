package jayslabs.fos.order.domain.vo;

import java.util.UUID;

import jayslabs.fos.domain.vo.BaseId;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}




