/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.util.Date
 *  java.util.zip.ZipEntry
 */
import java.util.Date;
import java.util.zip.ZipEntry;

public class bh
extends ZipEntry {
    String a;
    long b = -1;
    long c = -1;
    long d = -1;
    long e = -1;
    int f = -1;
    byte[] g;
    int h;
    int i;

    public bh(String string2) {
        super(string2);
        this.a = string2;
    }

    private static long a(long l2) {
        Date date = new Date(l2);
        int n2 = date.getYear() + 1900;
        if (n2 < 1980) {
            return 2162688;
        }
        int n3 = date.getMonth();
        int n4 = date.getDate();
        int n5 = date.getHours();
        int n6 = date.getMinutes();
        return date.getSeconds() >> 1 | (n2 - 1980 << 25 | n3 + 1 << 21 | n4 << 16 | n5 << 11 | n6 << 5);
    }

    public void setTime(long l2) {
        this.b = bh.a(l2);
    }
}

