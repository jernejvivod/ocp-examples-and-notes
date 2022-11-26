package si.jernej.ocp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@MetaAnnotation("test")
public @interface FieldAnnotationWithMetaAnnotation
{
}
