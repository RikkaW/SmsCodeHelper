/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 */
package com.xiaomi.mms.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtils {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void closeQuietly(InputStream inputStream) {
        if (inputStream == null) return;
        try {
            inputStream.close();
            return;
        }
        catch (IOException var0_1) {
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void closeQuietly(OutputStream outputStream) {
        if (outputStream == null) return;
        try {
            outputStream.close();
            return;
        }
        catch (IOException var0_1) {
            return;
        }
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        long l = IOUtils.copyLarge(inputStream, outputStream);
        if (l > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)l;
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream) throws IOException {
        return IOUtils.copyLarge(inputStream, outputStream, new byte[1024]);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, byte[] arrby) throws IOException {
        int n;
        long l = 0;
        while (-1 != (n = inputStream.read(arrby))) {
            outputStream.write(arrby, 0, n);
            l += (long)n;
        }
        return l;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(inputStream, (OutputStream)byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}

