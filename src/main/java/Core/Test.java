package Core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    static class EmptyException extends Throwable {
        private EmptyException() {};
    }
    Class<? extends Throwable> expected() default EmptyException.class;
}
