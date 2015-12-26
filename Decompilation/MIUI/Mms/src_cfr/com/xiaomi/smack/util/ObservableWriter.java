/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.smack.util;

import com.xiaomi.smack.util.WriterListener;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ObservableWriter
extends Writer {
    List listeners = new ArrayList();
    Writer wrappedWriter = null;

    public ObservableWriter(Writer writer) {
        this.wrappedWriter = writer;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void notifyListeners(String string2) {
        WriterListener[] arrwriterListener;
        List list = this.listeners;
        synchronized (list) {
            arrwriterListener = new WriterListener[this.listeners.size()];
            this.listeners.toArray(arrwriterListener);
        }
        int n = 0;
        while (n < arrwriterListener.length) {
            arrwriterListener[n].write(string2);
            ++n;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void addWriterListener(WriterListener writerListener) {
        if (writerListener == null) {
            return;
        }
        List list = this.listeners;
        synchronized (list) {
            if (!this.listeners.contains(writerListener)) {
                this.listeners.add(writerListener);
            }
            return;
        }
    }

    public void close() throws IOException {
        this.wrappedWriter.close();
    }

    public void flush() throws IOException {
        this.wrappedWriter.flush();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void removeWriterListener(WriterListener writerListener) {
        List list = this.listeners;
        synchronized (list) {
            this.listeners.remove(writerListener);
            return;
        }
    }

    public void write(int n) throws IOException {
        this.wrappedWriter.write(n);
    }

    public void write(String string2) throws IOException {
        this.wrappedWriter.write(string2);
        this.notifyListeners(string2);
    }

    public void write(String string2, int n, int n2) throws IOException {
        this.wrappedWriter.write(string2, n, n2);
        this.notifyListeners(string2.substring(n, n + n2));
    }

    public void write(char[] arrc) throws IOException {
        this.wrappedWriter.write(arrc);
        this.notifyListeners(new String(arrc));
    }

    public void write(char[] arrc, int n, int n2) throws IOException {
        this.wrappedWriter.write(arrc, n, n2);
        this.notifyListeners(new String(arrc, n, n2));
    }
}

