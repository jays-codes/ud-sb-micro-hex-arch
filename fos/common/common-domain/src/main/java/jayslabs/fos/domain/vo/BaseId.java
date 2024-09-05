//abstract base id class typed for T with generic type
package jayslabs.fos.domain.vo;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseId<T extends Serializable> {
    private final T value;

    public BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }   

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseId<?> that = (BaseId<?>) obj;
        return Objects.equals(value, that.value);
    }
}

