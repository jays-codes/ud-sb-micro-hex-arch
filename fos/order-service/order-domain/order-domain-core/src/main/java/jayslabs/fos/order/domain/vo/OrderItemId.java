//OrderItemId Value Object
package jayslabs.fos.order.domain.vo;

import jayslabs.fos.domain.vo.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}