/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.Object
 */
package com.xiaomi.smack.debugger;

import com.xiaomi.smack.PacketListener;
import java.io.Reader;
import java.io.Writer;

public interface SmackDebugger {
    public Reader getReader();

    public PacketListener getReaderListener();

    public Writer getWriter();

    public PacketListener getWriterListener();

    public Reader newConnectionReader(Reader var1);

    public Writer newConnectionWriter(Writer var1);
}

