/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.LayoutInflater$Factory
 *  android.view.LayoutInflater$Factory2
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 */
package android.support.v4.view;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompatBase;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Field;

class LayoutInflaterCompatHC {
    private static final String TAG = "LayoutInflaterCompatHC";
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;

    LayoutInflaterCompatHC() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    static void forceSetFactory2(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        if (!sCheckedField) {
            try {
                sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            }
            catch (NoSuchFieldException var2_3) {
                Log.e((String)"LayoutInflaterCompatHC", (String)("forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results."), (Throwable)var2_3);
            }
            sCheckedField = true;
        }
        if (sLayoutInflaterFactory2Field == null) return;
        try {
            sLayoutInflaterFactory2Field.set((Object)layoutInflater, (Object)factory2);
            return;
        }
        catch (IllegalAccessException var1_2) {
            Log.e((String)"LayoutInflaterCompatHC", (String)("forceSetFactory2 could not set the Factory2 on LayoutInflater " + (Object)layoutInflater + "; inflation may have unexpected results."), (Throwable)var1_2);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory object) {
        object = object != null ? new FactoryWrapperHC((LayoutInflaterFactory)object) : null;
        layoutInflater.setFactory2((LayoutInflater.Factory2)object);
        LayoutInflater.Factory factory = layoutInflater.getFactory();
        if (factory instanceof LayoutInflater.Factory2) {
            LayoutInflaterCompatHC.forceSetFactory2(layoutInflater, (LayoutInflater.Factory2)factory);
            return;
        }
        LayoutInflaterCompatHC.forceSetFactory2(layoutInflater, (LayoutInflater.Factory2)object);
    }

    static class FactoryWrapperHC
    extends LayoutInflaterCompatBase.FactoryWrapper
    implements LayoutInflater.Factory2 {
        FactoryWrapperHC(LayoutInflaterFactory layoutInflaterFactory) {
            super(layoutInflaterFactory);
        }

        public View onCreateView(View view, String string2, Context context, AttributeSet attributeSet) {
            return this.mDelegateFactory.onCreateView(view, string2, context, attributeSet);
        }
    }

}

