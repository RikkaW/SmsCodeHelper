/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Constructor
 */
package com.android.mms.ui;

import android.content.Context;
import android.util.Log;
import com.android.mms.model.Model;
import com.android.mms.ui.Presenter;
import com.android.mms.ui.ViewInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PresenterFactory {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Presenter getPresenter(String object, Context context, ViewInterface viewInterface, Model model) {
        Object object2;
        Object object3;
        block9 : {
            object2 = object;
            object3 = object;
            if (object.indexOf(".") != -1) break block9;
            object3 = object;
            object2 = "com.android.mms.ui." + (String)object;
        }
        object3 = object2;
        try {
            return (Presenter)Class.forName((String)object2).getConstructor(new Class[]{Context.class, ViewInterface.class, Model.class}).newInstance(new Object[]{context, viewInterface, model});
        }
        catch (ClassNotFoundException var0_1) {
            Log.e((String)"PresenterFactory", (String)("Type not found: " + (String)object3), (Throwable)var0_1);
            do {
                return null;
                break;
            } while (true);
        }
        catch (NoSuchMethodException var0_2) {
            Log.e((String)"PresenterFactory", (String)"No such constructor.", (Throwable)var0_2);
            return null;
        }
        catch (InvocationTargetException var0_3) {
            Log.e((String)"PresenterFactory", (String)"Unexpected InvocationTargetException", (Throwable)var0_3);
            return null;
        }
        catch (IllegalAccessException var0_4) {
            Log.e((String)"PresenterFactory", (String)"Unexpected IllegalAccessException", (Throwable)var0_4);
            return null;
        }
        catch (InstantiationException var0_5) {
            Log.e((String)"PresenterFactory", (String)"Unexpected InstantiationException", (Throwable)var0_5);
            return null;
        }
    }
}

