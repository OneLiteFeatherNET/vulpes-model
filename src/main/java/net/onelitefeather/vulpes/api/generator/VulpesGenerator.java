package net.onelitefeather.vulpes.api.generator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark a field or method for UUIDv7-based ID generation within the Vulpes model.
 * <p>
 * This annotation is a specialization that applies {@link UuidV7Generator} as the ID generation strategy.
 * It can be applied to fields or methods to instruct the persistence framework to generate a UUIDv7
 * when persisting the entity.
 * </p>
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @see UuidV7Generator
 * @see IdGeneratorType
 * @since 0.1.0
 */
@IdGeneratorType(UuidV7Generator.class)
@Retention(RUNTIME)
@Target({METHOD, FIELD})
public @interface VulpesGenerator {
}
