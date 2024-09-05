//abstract base entity typed for id with generic type
package jayslabs.fos.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity<ID extends Serializable> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    
    
} 




