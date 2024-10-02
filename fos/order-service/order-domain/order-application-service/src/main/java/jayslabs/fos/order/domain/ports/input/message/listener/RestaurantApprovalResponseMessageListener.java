package jayslabs.fos.order.domain.ports.input.message.listener;

import jayslabs.fos.order.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {
    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);
}
