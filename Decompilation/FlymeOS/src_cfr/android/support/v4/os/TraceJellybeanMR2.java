/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Trace
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.os;

import android.os.Trace;

class TraceJellybeanMR2 {
    TraceJellybeanMR2() {
    }

    public static void beginSection(String string2) {
        Trace.beginSection((String)string2);
    }

    public static void endSection() {
        Trace.endSection();
    }
}

