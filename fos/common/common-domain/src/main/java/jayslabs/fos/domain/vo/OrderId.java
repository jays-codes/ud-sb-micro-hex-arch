//OrderId Value Object typed to UUID
package jayslabs.fos.domain.vo;

import java.util.UUID;

public class OrderId extends BaseId<UUID> {
    public OrderId(UUID value) {
        super(value);
    }
}