/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.xiaomi.smack;

import com.xiaomi.smack.Connection;

public interface ConnectionListener {
    public void connectionClosed(Connection var1, int var2, Exception var3);

    public void connectionStarted(Connection var1);

    public void reconnectionFailed(Connection var1, Exception var2);

    public void reconnectionSuccessful(Connection var1);
}

