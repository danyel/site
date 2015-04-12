package be.urpi.software.site.core.persistence.spring.aspect;

import be.urpi.software.site.core.persistence.api.generator.Generator;
import be.urpi.software.site.core.persistence.stereotype.IdSequenceGenerator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
public class IdSequenceGeneratorAspect {
    Field[] getAllFields(final Class<?> argumentType) {
        final List<Field> fields = new ArrayList<Field>();

        fields.addAll(Arrays.asList(argumentType.getDeclaredFields()));

        Class<?> subType = argumentType.getSuperclass();
        while (!subType.equals(Object.class)) {
            fields.addAll(Arrays.asList(subType.getDeclaredFields()));
            subType = subType.getSuperclass();
        }

        return fields.toArray(new Field[fields.size()]);
    }

    void performOnType(final Object entity, final Class<?> argumentType) throws IllegalAccessException, InstantiationException {
        final IdSequenceGenerator idSequenceGenerator = argumentType.getAnnotation(IdSequenceGenerator.class);
        final Class<? extends Generator> generatorType = idSequenceGenerator.generatorType();
        final Generator generator = generatorType.newInstance();
        final Field[] fields = getAllFields(argumentType);
        boolean found = false;

        for (final Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                performOnField(entity, field, generator);
                break;
            }
        }

        if (!found) {
            final Method[] declaredMethods = argumentType.getDeclaredMethods();

            for (final Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(Id.class)) {
                    if (Void.class.equals(declaredMethod.getReturnType())) {
                    }
                }
            }
        }
    }

    void performOnField(final Object entity, final Field field, final Generator generator) throws IllegalAccessException, InstantiationException {
        field.setAccessible(true);
        field.set(entity, generator.generate());
    }

    void performOnField(final Object entity, final Field field) throws IllegalAccessException, InstantiationException {
        if (field.isAnnotationPresent(Id.class)) {
            final IdSequenceGenerator idSequenceGenerator = field.getAnnotation(IdSequenceGenerator.class);
            final Generator generator = idSequenceGenerator.generatorType().newInstance();
            performOnField(entity, field, generator);
        }
    }

    void performOnMethod(final Method method) {
        final IdSequenceGenerator idSequenceGenerator = method.getAnnotation(IdSequenceGenerator.class);
    }

    @Before("execution(* *(..))")
    public void id(final JoinPoint joinPoint) throws InstantiationException, IllegalAccessException {
        final Object[] joinPointArgs = joinPoint.getArgs();

        for (final Object joinPointArg : joinPointArgs) {
            final Class<?> argumentType = joinPointArg.getClass();

            if (argumentType.isAnnotationPresent(Entity.class)) {
                if (argumentType.isAnnotationPresent(IdSequenceGenerator.class)) {
                    performOnType(joinPointArg, argumentType);
                } else {
                    boolean found = false;
                    final Field[] declaredFields = getAllFields(argumentType);

                    for (final Field declaredField : declaredFields) {
                        if (declaredField.isAnnotationPresent(IdSequenceGenerator.class) && declaredField.isAnnotationPresent(Id.class)) {
                            declaredField.setAccessible(true);
                            performOnField(joinPointArg, declaredField);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        final Method[] declaredMethods = argumentType.getDeclaredMethods();

                        for (final Method declaredMethod : declaredMethods) {
                            if (declaredMethod.isAnnotationPresent(IdSequenceGenerator.class)) {
                                performOnMethod(declaredMethod);
                            }
                        }
                    }
                }
            }
        }
    }
}
