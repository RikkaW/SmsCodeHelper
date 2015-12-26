/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class FragmentTransaction {
    public static final int TRANSIT_ENTER_MASK = 4096;
    public static final int TRANSIT_EXIT_MASK = 8192;
    public static final int TRANSIT_FRAGMENT_CLOSE = 8194;
    public static final int TRANSIT_FRAGMENT_FADE = 4099;
    public static final int TRANSIT_FRAGMENT_OPEN = 4097;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;

    public abstract FragmentTransaction add(int var1, Fragment var2);

    public abstract FragmentTransaction add(int var1, Fragment var2, @Nullable String var3);

    public abstract FragmentTransaction add(Fragment var1, String var2);

    public abstract FragmentTransaction addSharedElement(View var1, String var2);

    public abstract FragmentTransaction addToBackStack(@Nullable String var1);

    public abstract FragmentTransaction attach(Fragment var1);

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract FragmentTransaction detach(Fragment var1);

    public abstract FragmentTransaction disallowAddToBackStack();

    public abstract FragmentTransaction hide(Fragment var1);

    public abstract boolean isAddToBackStackAllowed();

    public abstract boolean isEmpty();

    public abstract FragmentTransaction remove(Fragment var1);

    public abstract FragmentTransaction replace(int var1, Fragment var2);

    public abstract FragmentTransaction replace(int var1, Fragment var2, @Nullable String var3);

    public abstract FragmentTransaction setBreadCrumbShortTitle(int var1);

    public abstract FragmentTransaction setBreadCrumbShortTitle(CharSequence var1);

    public abstract FragmentTransaction setBreadCrumbTitle(int var1);

    public abstract FragmentTransaction setBreadCrumbTitle(CharSequence var1);

    public abstract FragmentTransaction setCustomAnimations(int var1, int var2);

    public abstract FragmentTransaction setCustomAnimations(int var1, int var2, int var3, int var4);

    public abstract FragmentTransaction setTransition(int var1);

    public abstract FragmentTransaction setTransitionStyle(int var1);

    public abstract FragmentTransaction show(Fragment var1);

    @Retention(value=RetentionPolicy.SOURCE)
    @IntDef(value={0, 4097, 8194})
    static @interface Transit {
    }

}

