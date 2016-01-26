package fr.pinguet62.jsfring.util.querydsl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.support.QueryBase;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.ComparableExpressionBase;
import com.mysema.query.types.expr.SimpleExpression;

import fr.pinguet62.jsfring.util.reflection.PropertyResolver;

/**
 * Convert a {@link Map} who associate the property and the value, to the
 * {@link Predicate} used to filter a {@link QueryBase query}.
 * <p>
 * Filtered field must be a {@link ComparableExpressionBase}.
 * <p>
 * Default criteria is {@code like}, to test if <i>filed-value</i> contains the
 * {@link String} <i>filter-value</i>.
 */
public final class FilterConverter implements Function<Map<String, Object>, Predicate> {

    /** The meta-object. */
    private final EntityPath<?> meta;

    /**
     * Constructor.
     *
     * @param meta The meta-object.
     */
    public FilterConverter(EntityPath<?> meta) {
        this.meta = meta;
    }

    /**
     * Apply the filter: get the {@link Predicate}.
     *
     * @param filters The association of property/value.
     * @return The {@link Predicate} built with different filters.
     * @throws NullPointerException If {@code filters} is {@code null}.
     * @throws ClassCastException The target field is not a
     *             {@link ComparableExpressionBase}, so doen't support filter.
     * @see PropertyResolver Transform property name to {@link SimpleExpression}
     *      .
     */
    @Override
    public Predicate apply(Map<String, Object> filters) {
        PropertyResolver propertyConverter = new PropertyResolver(meta);
        BooleanBuilder builder = new BooleanBuilder();
        for (Entry<String, Object> filter : filters.entrySet()) {
            // Field
            String property = filter.getKey();
            SimpleExpression<?> attribute = (SimpleExpression<?>) propertyConverter.apply(property);
            ComparableExpressionBase<?> comparable = (ComparableExpressionBase<?>) attribute;

            // Value
            String value = filter.getValue().toString();

            // Apply
            BooleanExpression criteria = comparable.stringValue().contains(value);
            builder.and(criteria);
        }
        return builder;
    }

}