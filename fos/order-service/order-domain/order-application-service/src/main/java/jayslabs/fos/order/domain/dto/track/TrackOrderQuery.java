//TrackOrderQuery using lombok @Data annotation. 
//Immutable. NotNull fields. UUID for orderTrackingId.

package jayslabs.fos.order.domain.dto.track;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TrackOrderQuery {
    @NotNull private final UUID orderTrackingId;
}

