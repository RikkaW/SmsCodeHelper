/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.util;

import java.io.File;
import java.io.FileFilter;

public final class n
implements FileFilter {
    private String a = "";
    private String b = "";

    public n(String string2, String string3) {
        this.a = string2;
        this.b = string3;
    }

    @Override
    public final boolean accept(File object) {
        if ((object = object.getName()).startsWith(this.a) && object.endsWith(this.b)) {
            return true;
        }
        return false;
    }
}

