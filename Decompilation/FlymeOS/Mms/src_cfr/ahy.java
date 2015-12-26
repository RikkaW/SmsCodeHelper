/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.charset.Charset
 */
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

final class ahy {
    static final Charset a = Charset.forName((String)"US-ASCII");
    static final Charset b = Charset.forName((String)"UTF-8");

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static void a(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
            return;
        }
        catch (RuntimeException var0_1) {
            throw var0_1;
        }
        catch (Exception var0_2) {
            return;
        }
    }

    static void a(File file2) {
        File[] arrfile = file2.listFiles();
        if (arrfile == null) {
            throw new IOException("not a readable directory: " + (Object)file2);
        }
        for (File file2 : arrfile) {
            if (file2.isDirectory()) {
                ahy.a(file2);
            }
            if (file2.delete()) continue;
            throw new IOException("failed to delete file: " + (Object)file2);
        }
    }
}

