package sdk.meizu.traffic.hybird.method;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.PARAMETER})
public @interface Parameter
{
  String value();
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.Parameter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */