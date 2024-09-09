//Product Entity
package jayslabs.fos.order.domain.entity;

import jayslabs.fos.domain.entity.BaseEntity;
import jayslabs.fos.domain.vo.Money;
import jayslabs.fos.domain.vo.ProductId;

public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;

    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}