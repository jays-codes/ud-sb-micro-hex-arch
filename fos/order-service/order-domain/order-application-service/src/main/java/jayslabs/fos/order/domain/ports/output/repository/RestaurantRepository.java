package jayslabs.fos.order.domain.ports.output.repository;

import java.util.Optional;

import jayslabs.fos.order.domain.entity.Restaurant;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
