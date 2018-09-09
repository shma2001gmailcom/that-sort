package org.misha.equals;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class Id {
    private final String code;
    private final String category;
    private final String product;

    Id(@Nonnull final String code, @Nullable final String category, @Nullable final String product) {
        this.code = code;
        this.category = category;
        this.product = product;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Id id = (Id) o;
        if (Objects.equals(code, id.code)) {
            if (("1".equals(category) && "1".equals(id.category))) {
                return true;
            } else  {
                return product != null && product.equals(id.product);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, product);
    }

    @Override
    public String toString() {
        return "Id{" +
                "code='" + code + '\'' +
                ", category='" + category + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}
