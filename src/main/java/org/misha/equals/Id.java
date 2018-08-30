package org.misha.equals;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class Id {
    private final String code;
    private final String category;
    private final String product;

    public Id(@Nonnull final String code, @Nullable final String category, @Nullable final String product) {
        this.code = code;
        this.category = category;
        this.product = product;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Id id = (Id) o;
        return Objects.equals(code, id.code) &&
                (("1".equals(category) && Objects.equals(category, id.category)) ||
                product != null && product.equals(id.product));
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
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
