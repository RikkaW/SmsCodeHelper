package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.FIELD})
public @interface NonNull {}

/* Location:
 * Qualified Name:     android.support.annotation.NonNull
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */