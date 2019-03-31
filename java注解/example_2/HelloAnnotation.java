package annotation_2;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HelloAnnotation {

    String say() default "Hi";

}
