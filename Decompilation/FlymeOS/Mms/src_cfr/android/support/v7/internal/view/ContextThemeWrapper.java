/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.view.LayoutInflater
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.support.v7.appcompat.R;
import android.view.LayoutInflater;

public class ContextThemeWrapper
extends ContextWrapper {
    private LayoutInflater mInflater;
    private Resources.Theme mTheme;
    private int mThemeResource;

    public ContextThemeWrapper(Context context, int n2) {
        super(context);
        this.mThemeResource = n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initializeTheme() {
        boolean bl2 = this.mTheme == null;
        if (bl2) {
            this.mTheme = this.getResources().newTheme();
            Resources.Theme theme = this.getBaseContext().getTheme();
            if (theme != null) {
                this.mTheme.setTo(theme);
            }
        }
        this.onApplyThemeResource(this.mTheme, this.mThemeResource, bl2);
    }

    public Object getSystemService(String string2) {
        if ("layout_inflater".equals((Object)string2)) {
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from((Context)this.getBaseContext()).cloneInContext((Context)this);
            }
            return this.mInflater;
        }
        return this.getBaseContext().getSystemService(string2);
    }

    public Resources.Theme getTheme() {
        if (this.mTheme != null) {
            return this.mTheme;
        }
        if (this.mThemeResource == 0) {
            this.mThemeResource = R.style.Theme_AppCompat_Light;
        }
        this.initializeTheme();
        return this.mTheme;
    }

    public int getThemeResId() {
        return this.mThemeResource;
    }

    protected void onApplyThemeResource(Resources.Theme theme, int n2, boolean bl2) {
        theme.applyStyle(n2, true);
    }

    public void setTheme(int n2) {
        this.mThemeResource = n2;
        this.initializeTheme();
    }
}

