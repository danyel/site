package be.urpi.software.site.core.persistence.stereotype;

import be.urpi.software.site.core.persistence.api.generator.Generator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = {TYPE, METHOD, FIELD})
@Retention(value = RUNTIME)
@Documented
public @interface IdSequenceGenerator {
    Class<? extends Generator> generatorType();
}
