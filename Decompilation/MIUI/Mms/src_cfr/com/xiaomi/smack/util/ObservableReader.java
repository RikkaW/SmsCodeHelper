/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.smack.util;

import com.xiaomi.smack.util.ReaderListener;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ObservableReader
extends Reader {
    List listeners = new ArrayList();
    Reader wrappedReader = null;

    public ObservableReader(Reader reader) {
        this.wrappedReader = reader;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void addReaderListener(ReaderListener readerListener) {
        if (readerListener == null) {
            return;
        }
        List list = this.listeners;
        synchronized (list) {
            if (!this.listeners.contains(readerListener)) {
                this.listeners.add(readerListener);
            }
            return;
        }
    }

    public void close() throws IOException {
        this.wrappedReader.close();
    }

    public void mark(int n) throws IOException {
        this.wrappedReader.mark(n);
    }

    public boolean markSupported() {
        return this.wrappedReader.markSupported();
    }

    public int read() throws IOException {
        return this.wrappedReader.read();
    }

    public int read(char[] arrc) throws IOException {
        return this.wrappedReader.read(arrc);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int read(char[] object, int n, int n2) throws IOException {
        ReaderListener[] arrreaderListener;
        if ((n2 = this.wrappedReader.read((char[])object, n, n2)) <= 0) {
            return n2;
        }
        String string2 = new String((char[])object, n, n2);
        object = this.listeners;
        synchronized (object) {
            arrreaderListener = new ReaderListener[this.listeners.size()];
            this.listeners.toArray(arrreaderListener);
        }
        n = 0;
        while (n < arrreaderListener.length) {
            arrreaderListener[n].read(string2);
            ++n;
        }
        return n2;
    }

    public boolean ready() throws IOException {
        return this.wrappedReader.ready();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void removeReaderListener(ReaderListener readerListener) {
        List list = this.listeners;
        synchronized (list) {
            this.listeners.remove(readerListener);
            return;
        }
    }

    public void reset() throws IOException {
        this.wrappedReader.reset();
    }

    public long skip(long l) throws IOException {
        return this.wrappedReader.skip(l);
    }
}

