/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.xiaomi.channel.commonutils.logger;

import android.util.Log;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;

public class DefaultAndroidLogger
implements LoggerInterface {
    private String mTag = "xiaomi";

    @Override
    public void log(String string2) {
        Log.v((String)this.mTag, (String)string2);
    }

    @Override
    public void log(String string2, Throwable throwable) {
        Log.v((String)this.mTag, (String)string2, (Throwable)throwable);
    }
}

