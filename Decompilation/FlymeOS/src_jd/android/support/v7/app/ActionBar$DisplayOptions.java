package android.support.v7.app;

import android.support.annotation.IntDef;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef(flag=true, value={1L, 2L, 4L, 8L, 16L, 64L})
public @interface ActionBar$DisplayOptions {}

/* Location:
 * Qualified Name:     android.support.v7.app.ActionBar.DisplayOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */