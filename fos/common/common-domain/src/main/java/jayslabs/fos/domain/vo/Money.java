//Money Value Object with currency and amount
package jayslabs.fos.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    //add Money applying setScale
    public Money add(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies do not match");
        }
        return new Money(setScale(amount.add(other.getAmount())), currency);
    }

    //subtract Money applying setScale
    public Money subtract(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies do not match");
        }
        return new Money(setScale(amount.subtract(other.getAmount())), currency);
    }

    //multiply
    public Money multiply(int multiplier) {
        return new Money(setScale(amount.multiply(BigDecimal.valueOf(multiplier))), currency);
    }

    //isGreaterThanZero
    public boolean isGreaterThanZero() {
        //use compareTo instead of equals
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    //isGreaterThan
    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    //setScale with BigDecimal argument scale set to 2 and rounding mode HALF_EVEN
    private BigDecimal setScale(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }
}