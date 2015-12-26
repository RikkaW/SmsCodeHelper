/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.io.FileInputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.nio.ByteBuffer
 *  java.security.MessageDigest
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.util.d;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class l {
    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static String a(File var0) {
        var3_5 = MessageDigest.getInstance((String)"MD5");
        var1_8 = var2_6 = new FileInputStream((File)var0);
        try {
            var3_5.update((ByteBuffer)var2_6.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, var0.length()));
            var1_8 = var2_6;
            var0 = l.a(var3_5.digest());
        }
        catch (Throwable var0_4) {
            ** GOTO lbl19
        }
        catch (NoSuchAlgorithmException var1_10) {
            var0 = var2_6;
            ** GOTO lbl13
        }
        d.a((Closeable)var2_6);
        return var0;
        catch (NoSuchAlgorithmException var1_9) {
            var0 = null;
lbl13: // 3 sources:
            var1_8.printStackTrace();
            d.a((Closeable)var0);
lbl15: // 2 sources:
            do {
                return null;
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            var2_6 = null;
lbl19: // 2 sources:
            var1_8 = var2_6;
            var0.printStackTrace();
            d.a((Closeable)var2_6);
            ** continue;
        }
        catch (Throwable var0_2) {
            var1_8 = null;
lbl26: // 3 sources:
            do {
                d.a((Closeable)var1_8);
                throw var0;
                break;
            } while (true);
        }
        {
            catch (Throwable var0_3) {
                ** GOTO lbl26
            }
        }
        {
            catch (Throwable var2_7) {
                var1_8 = var0;
                var0 = var2_7;
                ** continue;
            }
        }
    }

    private static String a(byte[] arrby) {
        int n2 = 0;
        if (arrby == null) {
            return null;
        }
        char[] arrc = new char[arrby.length * 2];
        int n3 = 0;
        while (n3 < arrby.length) {
            arrc[n2] = "0123456789abcdef".charAt(arrby[n3] >> 4 & 15);
            byte by2 = arrby[n3];
            arrc[++n2] = "0123456789abcdef".charAt(by2 & 15);
            ++n3;
            ++n2;
        }
        return String.valueOf((char[])arrc);
    }
}

