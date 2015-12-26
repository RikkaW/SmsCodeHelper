/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PrintWriter
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.util;

import java.io.PrintWriter;

public class TimeUtils {
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static char[] sFormatStr;
    private static final Object sFormatSync;

    static {
        sFormatSync = new Object();
        sFormatStr = new char[24];
    }

    private static int accumField(int n2, int n3, boolean bl2, int n4) {
        if (n2 > 99 || bl2 && n4 >= 3) {
            return n3 + 3;
        }
        if (n2 > 9 || bl2 && n4 >= 2) {
            return n3 + 2;
        }
        if (bl2 || n2 > 0) {
            return n3 + 1;
        }
        return 0;
    }

    public static void formatDuration(long l2, long l3, PrintWriter printWriter) {
        if (l2 == 0) {
            printWriter.print("--");
            return;
        }
        TimeUtils.formatDuration(l2 - l3, printWriter, 0);
    }

    public static void formatDuration(long l2, PrintWriter printWriter) {
        TimeUtils.formatDuration(l2, printWriter, 0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void formatDuration(long l2, PrintWriter printWriter, int n2) {
        Object object = sFormatSync;
        synchronized (object) {
            n2 = TimeUtils.formatDurationLocked(l2, n2);
            printWriter.print(new String(sFormatStr, 0, n2));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void formatDuration(long l2, StringBuilder stringBuilder) {
        Object object = sFormatSync;
        synchronized (object) {
            int n2 = TimeUtils.formatDurationLocked(l2, 0);
            stringBuilder.append(sFormatStr, 0, n2);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static int formatDurationLocked(long l2, int n2) {
        int n3;
        int n4;
        boolean bl2;
        int n5;
        int n6;
        int n7;
        if (sFormatStr.length < n2) {
            sFormatStr = new char[n2];
        }
        char[] arrc = sFormatStr;
        if (l2 == 0) {
            do {
                if (n2 - 1 >= 0) {
                    arrc[0] = 48;
                    return 1;
                }
                arrc[0] = 32;
            } while (true);
        }
        if (l2 > 0) {
            n4 = 43;
        } else {
            l2 = - l2;
            n4 = 45;
        }
        int n8 = (int)(l2 % 1000);
        int n9 = (int)Math.floor((double)(l2 / 1000));
        int n10 = 0;
        int n11 = n9;
        if (n9 > 86400) {
            n10 = n9 / 86400;
            n11 = n9 - 86400 * n10;
        }
        if (n11 > 3600) {
            n3 = n9 = n11 / 3600;
            n11 -= n9 * 3600;
        } else {
            n3 = 0;
        }
        if (n11 > 60) {
            n7 = n9 = n11 / 60;
            n9 = n11 - n9 * 60;
        } else {
            n7 = 0;
            n9 = n11;
        }
        if (n2 != 0) {
            n11 = TimeUtils.accumField(n10, 1, false, 0);
            bl2 = n11 > 0;
            bl2 = (n11 += TimeUtils.accumField(n3, 1, bl2, 2)) > 0;
            bl2 = (n11 += TimeUtils.accumField(n7, 1, bl2, 2)) > 0;
            n6 = n11 + TimeUtils.accumField(n9, 1, bl2, 2);
            n11 = n6 > 0 ? 3 : 0;
            n5 = TimeUtils.accumField(n8, 2, true, n11);
            n11 = 0;
            n6 = n5 + 1 + n6;
            do {
                n5 = n11++;
                if (n6 < n2) {
                    arrc[n11] = 32;
                    ++n6;
                    continue;
                }
                break;
                break;
            } while (true);
        } else {
            n5 = 0;
        }
        arrc[n5] = n4;
        n6 = n5 + 1;
        n2 = n2 != 0 ? 1 : 0;
        bl2 = (n10 = TimeUtils.printField(arrc, n10, 'd', n6, false, 0)) != n6;
        n11 = n2 != 0 ? 2 : 0;
        bl2 = (n10 = TimeUtils.printField(arrc, n3, 'h', n10, bl2, n11)) != n6;
        n11 = n2 != 0 ? 2 : 0;
        bl2 = (n10 = TimeUtils.printField(arrc, n7, 'm', n10, bl2, n11)) != n6;
        n11 = n2 != 0 ? 2 : 0;
        n11 = TimeUtils.printField(arrc, n9, 's', n10, bl2, n11);
        n2 = n2 != 0 && n11 != n6 ? 3 : 0;
        n2 = TimeUtils.printField(arrc, n8, 'm', n11, true, n2);
        arrc[n2] = 115;
        return n2 + 1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static int printField(char[] var0, int var1_1, char var2_2, int var3_3, boolean var4_4, int var5_5) {
        if (!var4_4) {
            var6_6 = var3_3;
            if (var1_1 <= 0) return var6_6;
        }
        if (var4_4 && var5_5 >= 3 || var1_1 > 99) {
            var7_7 = var1_1 / 100;
            var0[var3_3] = (char)(var7_7 + 48);
            var6_6 = var3_3 + 1;
            var1_1 -= var7_7 * 100;
        } else {
            var6_6 = var3_3;
        }
        if (var4_4 && var5_5 >= 2 || var1_1 > 9) ** GOTO lbl-1000
        var7_7 = var6_6;
        var5_5 = var1_1;
        if (var3_3 != var6_6) lbl-1000: // 2 sources:
        {
            var3_3 = var1_1 / 10;
            var0[var6_6] = (char)(var3_3 + 48);
            var7_7 = var6_6 + 1;
            var5_5 = var1_1 - var3_3 * 10;
        }
        var0[var7_7] = (char)(var5_5 + 48);
        var1_1 = var7_7 + 1;
        var0[var1_1] = var2_2;
        return var1_1 + 1;
    }
}

