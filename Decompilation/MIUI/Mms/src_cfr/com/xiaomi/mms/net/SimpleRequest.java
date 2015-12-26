/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.xiaomi.mms.net;

import java.io.Closeable;
import java.io.IOException;

public interface SimpleRequest
extends Closeable {
    @Override
    public void close();

    public void connect() throws IOException;

    public byte[] getResponseBody() throws IOException;
}

