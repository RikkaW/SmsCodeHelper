/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.io.PrintWriter
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package com.xiaomi.smack;

import com.xiaomi.smack.packet.StreamError;
import com.xiaomi.smack.packet.XMPPError;
import java.io.PrintStream;
import java.io.PrintWriter;

public class XMPPException
extends Exception {
    private XMPPError error = null;
    private StreamError streamError = null;
    private Throwable wrappedThrowable = null;

    public XMPPException() {
    }

    public XMPPException(StreamError streamError) {
        this.streamError = streamError;
    }

    public XMPPException(String string2) {
        super(string2);
    }

    public XMPPException(String string2, Throwable throwable) {
        super(string2);
        this.wrappedThrowable = throwable;
    }

    public XMPPException(Throwable throwable) {
        this.wrappedThrowable = throwable;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String getMessage() {
        String string2 = super.getMessage();
        if (string2 == null && this.error != null) {
            return this.error.toString();
        }
        String string3 = string2;
        if (string2 != null) return string3;
        string3 = string2;
        if (this.streamError == null) return string3;
        return this.streamError.toString();
    }

    public Throwable getWrappedThrowable() {
        return this.wrappedThrowable;
    }

    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.wrappedThrowable != null) {
            printStream.println("Nested Exception: ");
            this.wrappedThrowable.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.wrappedThrowable != null) {
            printWriter.println("Nested Exception: ");
            this.wrappedThrowable.printStackTrace(printWriter);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String string2 = super.getMessage();
        if (string2 != null) {
            stringBuilder.append(string2).append(": ");
        }
        if (this.error != null) {
            stringBuilder.append(this.error);
        }
        if (this.streamError != null) {
            stringBuilder.append(this.streamError);
        }
        if (this.wrappedThrowable != null) {
            stringBuilder.append("\n  -- caused by: ").append((Object)this.wrappedThrowable);
        }
        return stringBuilder.toString();
    }
}

