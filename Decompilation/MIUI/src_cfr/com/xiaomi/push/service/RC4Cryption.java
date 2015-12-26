/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.string.Base64Coder;
import java.io.PrintStream;

public class RC4Cryption {
    private static int keylength = 8;
    private byte[] S = new byte[256];
    private int next_j = -666;
    private int the_i = 0;
    private int the_j = 0;

    public static byte[] decrypt(byte[] arrby, String string2) {
        return RC4Cryption.encrypt(arrby, Base64Coder.decode(string2));
    }

    public static String encrypt(byte[] arrby, String string2) {
        return String.valueOf((char[])Base64Coder.encode(RC4Cryption.encrypt(arrby, string2.getBytes())));
    }

    public static byte[] encrypt(byte[] arrby, byte[] arrby2) {
        byte[] arrby3 = new byte[arrby2.length];
        RC4Cryption rC4Cryption = new RC4Cryption();
        rC4Cryption.ksa(arrby);
        rC4Cryption.init();
        for (int i = 0; i < arrby2.length; ++i) {
            arrby3[i] = (byte)(arrby2[i] ^ rC4Cryption.nextVal());
        }
        return arrby3;
    }

    public static byte[] generateKeyForRC4(String string2, String string3) {
        int n;
        string2 = (String)Base64Coder.decode(string2);
        string3 = (String)string3.getBytes();
        byte[] arrby = new byte[string2.length + 1 + string3.length];
        for (n = 0; n < string2.length; ++n) {
            arrby[n] = string2[n];
        }
        arrby[string2.length] = 95;
        for (n = 0; n < string3.length; ++n) {
            arrby[string2.length + 1 + n] = string3[n];
        }
        return arrby;
    }

    private void init() {
        this.the_j = 0;
        this.the_i = 0;
    }

    private void ksa(int n, byte[] arrby, boolean bl) {
        int n2;
        int n3 = arrby.length;
        for (n2 = 0; n2 < 256; ++n2) {
            this.S[n2] = (byte)n2;
        }
        this.the_j = 0;
        this.the_i = 0;
        while (this.the_i < n) {
            this.the_j = (this.the_j + RC4Cryption.posify(this.S[this.the_i]) + RC4Cryption.posify(arrby[this.the_i % n3])) % 256;
            RC4Cryption.sswap(this.S, this.the_i, this.the_j);
            ++this.the_i;
        }
        if (n != 256) {
            this.next_j = (this.the_j + RC4Cryption.posify(this.S[n]) + RC4Cryption.posify(arrby[n % n3])) % 256;
        }
        if (bl) {
            System.out.print("S_" + (n - 1) + ":");
            for (n2 = 0; n2 <= n; ++n2) {
                System.out.print(" " + RC4Cryption.posify(this.S[n2]));
            }
            System.out.print("   j_" + (n - 1) + "=" + this.the_j);
            System.out.print("   j_" + n + "=" + this.next_j);
            System.out.print("   S_" + (n - 1) + "[j_" + (n - 1) + "]=" + RC4Cryption.posify(this.S[this.the_j]));
            System.out.print("   S_" + (n - 1) + "[j_" + n + "]=" + RC4Cryption.posify(this.S[this.next_j]));
            if (this.S[1] != 0) {
                System.out.print("   S[1]!=0");
            }
            System.out.println();
        }
    }

    private void ksa(byte[] arrby) {
        this.ksa(256, arrby, false);
    }

    public static int posify(byte by) {
        if (by >= 0) {
            return by;
        }
        return by + 256;
    }

    private static void sswap(byte[] arrby, int n, int n2) {
        byte by = arrby[n];
        arrby[n] = arrby[n2];
        arrby[n2] = by;
    }

    byte nextVal() {
        this.the_i = (this.the_i + 1) % 256;
        this.the_j = (this.the_j + RC4Cryption.posify(this.S[this.the_i])) % 256;
        RC4Cryption.sswap(this.S, this.the_i, this.the_j);
        return this.S[(RC4Cryption.posify(this.S[this.the_i]) + RC4Cryption.posify(this.S[this.the_j])) % 256];
    }
}

