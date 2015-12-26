/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Arrays
 */
package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.Arrays;

class BundleUtil {
    BundleUtil() {
    }

    public static Bundle[] getBundleArrayFromBundle(Bundle bundle, String string2) {
        Object[] arrobject = bundle.getParcelableArray(string2);
        if (arrobject instanceof Bundle[] || arrobject == null) {
            return (Bundle[])arrobject;
        }
        arrobject = (Bundle[])Arrays.copyOf((Object[])arrobject, (int)arrobject.length, (Class)Bundle[].class);
        bundle.putParcelableArray(string2, (Parcelable[])arrobject);
        return arrobject;
    }
}

