//OrderDomainException.java
package jayslabs.fos.order.domain.exception;

import jayslabs.fos.domain.exception.DomainException;
public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}