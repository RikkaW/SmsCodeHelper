/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.security.KeyFactory
 *  java.security.cert.Certificate
 *  java.security.cert.CertificateFactory
 *  java.util.Arrays
 *  java.util.zip.ZipEntry
 *  java.util.zip.ZipOutputStream
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.mapcore2d.ed;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class di {
    public static String a(String string2) {
        if (string2 == null) {
            return null;
        }
        Object object = string2.split("&");
        Arrays.sort((Object[])object);
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = object.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            stringBuffer.append((String)object[i2]);
            stringBuffer.append("&");
            continue;
        }
        try {
            object = stringBuffer.toString();
            if (object.length() > 1) {
                object = (String)object.subSequence(0, object.length() - 1);
                return object;
            }
        }
        catch (Throwable var3_3) {
            var3_3.printStackTrace();
            ed.a(var3_3, "Utils", "sortParams");
        }
        return string2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    static PublicKey a(Context var0) {
        block16 : {
            block15 : {
                var1_5 = var3_4 = new ByteArrayInputStream(new byte[]{48, -126, 2, -98, 48, -126, 2, 7, -96, 3, 2, 1, 2, 2, 9, 0, -99, 15, 119, 58, 44, -19, -105, -40, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, 30, 23, 13, 49, 51, 48, 56, 49, 53, 48, 55, 53, 54, 53, 53, 90, 23, 13, 50, 51, 48, 56, 49, 51, 48, 55, 53, 54, 53, 53, 90, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -15, -27, -128, -56, 118, -59, 62, -127, 79, 125, -36, 121, 0, 63, -125, -30, 118, 5, -85, -121, 91, 39, 90, 123, 72, -126, -83, -41, -45, -77, -42, -120, -81, 23, -2, -121, -29, 123, -7, 22, -114, -20, -25, 74, 67, -43, 65, 124, -7, 11, -72, 38, -123, 16, -58, 80, 32, 58, -33, 14, 11, 36, 60, 13, -121, 100, 105, -32, 123, -31, 114, -101, -41, 12, 100, 33, -120, 63, 126, -123, 48, 55, 80, -116, 28, -10, 125, 59, -41, -95, -126, 118, -70, 43, -127, 9, 93, -100, 81, -19, -114, -41, 85, -103, -37, -116, 118, 72, 86, 125, -43, -92, -11, 63, 69, -38, -10, -65, 126, -53, -115, 60, 62, -86, -80, 1, 39, 19, 2, 3, 1, 0, 1, -93, 80, 48, 78, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 12, 6, 3, 85, 29, 19, 4, 5, 48, 3, 1, 1, -1, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -127, -127, 0, -32, -74, 55, -125, -58, -128, 15, -62, 100, -60, 3, -86, 81, 112, -61, -56, -69, -126, 8, 99, -100, -38, -108, -56, -122, 125, 19, -64, -61, 90, 85, -47, -8, -123, -103, 105, 77, -32, -65, -62, -28, 67, -28, -78, 116, -49, 120, -2, 33, 13, 47, 46, -5, -112, 3, -101, -125, -115, 92, -124, 58, 80, 107, -67, 82, 6, -63, 39, -90, -1, 85, -58, 82, -115, 119, 13, -4, -32, 0, -98, 100, -41, 94, -75, 75, -103, 126, -80, 85, 40, -27, 60, 105, 28, -27, -21, -15, -98, 103, -88, -109, 35, -119, -27, -26, -122, 113, 63, 35, -33, 70, 23, 33, -23, 66, 108, 56, 112, 46, -85, -123, -123, 33, 118, 27, 96, -7, -103});
                var2_6 = CertificateFactory.getInstance((String)"X.509");
                var1_5 = var3_4;
                var0 = KeyFactory.getInstance((String)"RSA");
                var1_5 = var3_4;
                var1_5 = var2_6 = var2_6.generateCertificate(var3_4);
                var4_11 = var0;
                if (var3_4 == null) break block15;
                var3_4.close();
                var4_11 = var0;
                var1_5 = var2_6;
            }
lbl17: // 3 sources:
            if (var1_5 == null || var4_11 == null) {
                return null;
            }
            ** GOTO lbl37
            catch (Throwable var2_7) {
                var0 = null;
                var3_4 = null;
lbl23: // 3 sources:
                var1_5 = var3_4;
                var2_8.printStackTrace();
                if (var3_4 == null) break block16;
                var3_4.close();
                var1_5 = null;
                var4_11 = var0;
                ** GOTO lbl17
            }
            catch (Throwable var0_1) {
                var1_5 = null;
lbl33: // 2 sources:
                do {
                    if (var1_5 != null) {
                        var1_5.close();
                    }
                    throw var0_2;
                    break;
                } while (true);
            }
lbl37: // 1 sources:
            return var4_11.generatePublic((KeySpec)new X509EncodedKeySpec(var1_5.getPublicKey().getEncoded()));
            {
                catch (Throwable var0_3) {
                    ** continue;
                }
            }
            {
                catch (Throwable var2_9) {
                    var0 = null;
                    ** GOTO lbl23
                }
                catch (Throwable var2_10) {
                    ** GOTO lbl23
                }
            }
        }
        var1_5 = null;
        var4_11 = var0;
        ** GOTO lbl17
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] a(byte[] arrby) {
        try {
            return di.f(arrby);
        }
        catch (IOException var0_1) {
            ed.a(var0_1, "Utils", "gZip");
            var0_1.printStackTrace();
            do {
                return new byte[0];
                break;
            } while (true);
        }
        catch (Throwable var0_2) {
            ed.a(var0_2, "Utils", "gZip");
            var0_2.printStackTrace();
            return new byte[0];
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static byte[] b(byte[] var0) {
        block38 : {
            var6_7 = null;
            var2_9 = var7_8 = null;
            if (var0 == null) ** GOTO lbl6
            if (var0.length == 0) {
                var2_9 = var7_8;
lbl6: // 4 sources:
                do {
                    return var2_9;
                    break;
                } while (true);
            }
            var1_12 = new ByteArrayOutputStream();
            var5_14 = new ZipOutputStream((OutputStream)var1_12);
            var3_15 = var1_12;
            var2_9 = var5_14;
            var5_14.putNextEntry(new ZipEntry("log"));
            var3_15 = var1_12;
            var2_9 = var5_14;
            var5_14.write((byte[])var0);
            var3_15 = var1_12;
            var2_9 = var5_14;
            var5_14.closeEntry();
            var3_15 = var1_12;
            var2_9 = var5_14;
            var5_14.finish();
            var3_15 = var1_12;
            var2_9 = var5_14;
            var0 = var1_12.toByteArray();
            if (var5_14 == null) break block38;
            try {
                var5_14.close();
            }
            catch (Throwable var2_11) {
                var3_15 = ed.b();
                if (var3_15 != null) {
                    var3_15.b(var2_11, "Utils", "zip1");
                }
                var2_11.printStackTrace();
                ** continue;
            }
        }
lbl32: // 2 sources:
        do {
            var2_9 = var0;
            if (var1_12 == null) ** GOTO lbl6
            try {
                var1_12.close();
                return var0;
            }
            catch (Throwable var3_16) {
                var4_17 = ed.b();
                var1_12 = var0;
                var2_9 = var3_16;
                if (var4_17 != null) {
                    var4_17.b(var3_16, "Utils", "zip2");
                    var2_9 = var3_16;
                    var1_12 = var0;
                }
lbl46: // 5 sources:
                do {
                    var2_9.printStackTrace();
                    return var1_12;
                    break;
                } while (true);
            }
            break;
        } while (true);
        catch (Throwable var4_18) {
            block39 : {
                var1_12 = null;
                var0 = null;
lbl52: // 3 sources:
                var3_15 = var1_12;
                var2_9 = var0;
                var4_19.printStackTrace();
                var3_15 = var1_12;
                var2_9 = var0;
                ed.a((Throwable)var4_19, "Utils", "zip");
                if (var0 == null) break block39;
                var0.close();
            }
lbl63: // 2 sources:
            do {
                var2_9 = var7_8;
                if (var1_12 == null) ** continue;
                try {
                    var1_12.close();
                    return null;
                }
                catch (Throwable var0_1) {
                    var3_15 = ed.b();
                    var1_12 = var6_7;
                    var2_9 = var0_1;
                    if (var3_15 == null) ** GOTO lbl46
                    var3_15.b(var0_1, "Utils", "zip2");
                    var1_12 = var6_7;
                    var2_9 = var0_1;
                    ** continue;
                }
                break;
            } while (true);
        }
        catch (Throwable var0_2) {
            var1_12 = null;
            var2_9 = null;
lbl81: // 3 sources:
            do {
                if (var2_9 != null) {
                    var2_9.close();
                }
lbl85: // 4 sources:
                do {
                    if (var1_12 != null) {
                        var1_12.close();
                    }
lbl89: // 4 sources:
                    do {
                        throw var0_3;
                        break;
                    } while (true);
                    break;
                } while (true);
                catch (Throwable var2_10) {
                    var3_15 = ed.b();
                    if (var3_15 != null) {
                        var3_15.b(var2_10, "Utils", "zip1");
                    }
                    var2_10.printStackTrace();
                    ** continue;
                }
                catch (Throwable var1_13) {
                    var2_9 = ed.b();
                    if (var2_9 != null) {
                        var2_9.b(var1_13, "Utils", "zip2");
                    }
                    var1_13.printStackTrace();
                    ** continue;
                }
                break;
            } while (true);
        }
        {
            catch (Throwable var0_4) {
                var2_9 = ed.b();
                if (var2_9 != null) {
                    var2_9.b(var0_4, "Utils", "zip1");
                }
                var0_4.printStackTrace();
                ** continue;
            }
        }
        catch (Throwable var0_5) {
            var2_9 = null;
            ** GOTO lbl81
        }
        {
            catch (Throwable var0_6) {
                var1_12 = var3_15;
                ** continue;
            }
        }
        catch (Throwable var4_20) {
            var0 = null;
            ** GOTO lbl52
        }
        catch (Throwable var4_21) {
            var0 = var5_14;
            ** GOTO lbl52
        }
    }

    static String c(byte[] object) {
        try {
            object = di.e((byte[])object);
            return object;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "Utils", "HexString");
            var0_1.printStackTrace();
            return null;
        }
    }

    static String d(byte[] object) {
        try {
            object = di.e((byte[])object);
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    private static String e(byte[] arrby) {
        StringBuilder stringBuilder = new StringBuilder();
        if (arrby == null) {
            return null;
        }
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            String string2;
            String string3 = string2 = Integer.toHexString((int)(arrby[i2] & 255));
            if (string2.length() == 1) {
                string3 = "" + '0' + string2;
            }
            stringBuilder.append(string3);
        }
        return stringBuilder.toString();
    }

    /*
     * Exception decompiling
     */
    private static byte[] f(byte[] var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }
}

