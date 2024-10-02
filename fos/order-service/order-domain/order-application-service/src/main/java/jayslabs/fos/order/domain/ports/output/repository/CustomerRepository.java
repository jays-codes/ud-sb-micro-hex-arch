package jayslabs.fos.order.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import jayslabs.fos.order.domain.entity.Customer;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
