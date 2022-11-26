package si.jernej.ocp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationWithAnnotation
{
    ContainedAnnotation value();
}
