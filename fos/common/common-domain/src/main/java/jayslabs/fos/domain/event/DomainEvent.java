package jayslabs.fos.domain.event;

public interface DomainEvent<T> {
    T getEventIdentifier();
    void setEventIdentifier(T eventIdentifier);
}
