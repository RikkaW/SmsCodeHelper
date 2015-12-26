/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.et;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

class et$1
extends ByteArrayOutputStream {
    final /* synthetic */ et a;

    et$1(et et2, int n2) {
        this.a = et2;
        super(n2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String toString() {
        int n2 = this.count > 0 && this.buf[this.count - 1] == 13 ? this.count - 1 : this.count;
        try {
            return new String(this.buf, 0, n2, et.a(this.a).name());
        }
        catch (UnsupportedEncodingException var2_3) {
            throw new AssertionError((Object)var2_3);
        }
    }
}

