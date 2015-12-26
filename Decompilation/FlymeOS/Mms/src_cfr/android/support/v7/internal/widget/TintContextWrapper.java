/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.widget.ResourcesWrapper;
import android.support.v7.internal.widget.TintManager;

public class TintContextWrapper
extends ContextWrapper {
    private Resources mResources;

    private TintContextWrapper(Context context) {
        super(context);
    }

    public static Context wrap(Context context) {
        Object object = context;
        if (!(context instanceof TintContextWrapper)) {
            object = new TintContextWrapper(context);
        }
        return object;
    }

    public Resources getResources() {
        if (this.mResources == null) {
            this.mResources = new TintResources(super.getResources(), TintManager.get((Context)this));
        }
        return this.mResources;
    }

    static class TintResources
    extends ResourcesWrapper {
        private final TintManager mTintManager;

        public TintResources(Resources resources, TintManager tintManager) {
            super(resources);
            this.mTintManager = tintManager;
        }

        @Override
        public Drawable getDrawable(int n2) {
            Drawable drawable2 = super.getDrawable(n2);
            if (drawable2 != null) {
                this.mTintManager.tintDrawableUsingColorFilter(n2, drawable2);
            }
            return drawable2;
        }
    }

}

