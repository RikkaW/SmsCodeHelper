/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.io.RandomAccessFile
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.BitSet
 *  java.util.zip.GZIPInputStream
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;

public final class ahc {
    private RandomAccessFile a;
    private agc b;
    private File c = null;

    protected ahc(agc agc2) {
        this.b = agc2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static byte a(byte[] object) {
        Object object2;
        Object object3 = object2 = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[])object);
            object3 = object2;
            GZIPInputStream gZIPInputStream = new GZIPInputStream((InputStream)byteArrayInputStream);
            object3 = object2;
            object = new byte[1024];
            object3 = object2;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            do {
                object3 = object2;
                int n2 = gZIPInputStream.read((byte[])object, 0, object.length);
                if (n2 == -1) break;
                object3 = object2;
                byteArrayOutputStream.write((byte[])object, 0, n2);
            } while (true);
            object3 = object2;
            object3 = object = (Object)byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            object3 = object;
            byteArrayOutputStream.close();
            object3 = object;
            gZIPInputStream.close();
            object3 = object;
            byteArrayInputStream.close();
            return (byte)object[0];
        }
        catch (Exception var0_1) {
            object = object3;
            return (byte)object[0];
        }
    }

    private static int a(int n2, int n3, int n4) {
        for (n2 = (n4 - 1) * 1500 + n2; n2 >= n3; n2 -= 1500) {
        }
        return n2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int a(BitSet bitSet) {
        int n2 = 0;
        int n3 = 0;
        do {
            int n4 = n2;
            if (n3 >= bitSet.length()) return n4;
            if (bitSet.get(n3)) {
                return this.b.a() + (n3 * 1500 + 4);
            }
            ++n3;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ArrayList a(int n2, int n3) {
        ArrayList arrayList = new ArrayList();
        do {
            if (n2 > n3) {
                return arrayList;
            }
            try {
                this.a.seek((long)n2);
                int n4 = this.a.readInt();
                this.a.readLong();
                if (n4 <= 0) break;
                if (n4 > 1500) {
                    return null;
                }
                byte[] arrby = new byte[n4];
                this.a.read(arrby);
                n4 = ahc.a(arrby);
                if (n4 != 3 && n4 != 4 && n4 != 41) break;
                arrayList.add((Object)arrby);
            }
            catch (IOException var5_6) {}
            n2 += 1500;
        } while (true);
        return null;
    }

    private BitSet b() {
        Object object = new byte[this.b.a()];
        try {
            this.a.read((byte[])object);
            object = (Object)agc.b((byte[])object);
            return object;
        }
        catch (IOException var1_2) {
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected final int a() {
        var2_1 = 0;
        var6_2 = 0;
        var7_3 = 0;
        var8_4 = 0;
        var1_5 = 0;
        // MONITORENTER : this
        this.c = this.b.b();
        var3_6 = var6_2;
        var4_7 = var7_3;
        var5_8 = var8_4;
        if (this.c != null) {
            var3_6 = var6_2;
            var4_7 = var7_3;
            var5_8 = var8_4;
            this.a = new RandomAccessFile(this.b.b(), "rw");
            var3_6 = var6_2;
            var4_7 = var7_3;
            var5_8 = var8_4;
            var10_9 = new byte[this.b.a()];
            var3_6 = var6_2;
            var4_7 = var7_3;
            var5_8 = var8_4;
            this.a.read((byte[])var10_9);
            var3_6 = var6_2;
            var4_7 = var7_3;
            var5_8 = var8_4;
            var10_9 = agc.b((byte[])var10_9);
            var6_2 = 0;
            do {
                var2_1 = var1_5;
                var3_6 = var1_5;
                var4_7 = var1_5;
                var5_8 = var1_5;
                if (var6_2 >= var10_9.size()) break;
                var3_6 = var1_5;
                var4_7 = var1_5;
                var5_8 = var1_5;
                var9_21 = var10_9.get(var6_2);
                var2_1 = var1_5;
                if (var9_21) {
                    var2_1 = var1_5 + 1;
                }
                ++var6_2;
                var1_5 = var2_1;
            } while (true);
        }
        var10_9 = this.a;
        var1_5 = var2_1;
        if (var10_9 == null) ** GOTO lbl96
        this.a.close();
        var1_5 = var2_1;
        catch (FileNotFoundException var10_10) {
            var10_11 = this.a;
            var1_5 = var3_6;
            if (var10_11 == null) ** GOTO lbl96
            try {
                this.a.close();
                var1_5 = var3_6;
            }
            catch (IOException var10_12) {
                var1_5 = var3_6;
            }
        }
        catch (IOException var10_13) {
            var10_14 = this.a;
            var1_5 = var4_7;
            if (var10_14 == null) ** GOTO lbl96
            try {
                this.a.close();
                var1_5 = var4_7;
            }
            catch (IOException var10_15) {
                var1_5 = var4_7;
            }
        }
        catch (NullPointerException var10_16) {
            var10_17 = this.a;
            var1_5 = var5_8;
            if (var10_17 == null) ** GOTO lbl96
            try {
                this.a.close();
                var1_5 = var5_8;
            }
            catch (IOException var10_18) {
                var1_5 = var5_8;
            }
        }
        catch (Throwable var10_19) {
            var11_22 = this.a;
            if (var11_22 == null) throw var10_19;
            try {
                this.a.close();
            }
            catch (IOException var11_23) {
                throw var10_19;
            }
            throw var10_19;
        }
        catch (IOException var10_20) {
            var1_5 = var2_1;
        }
lbl96: // 12 sources:
        this.c = null;
        // MONITOREXIT : this
        return var1_5;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected final agb a(int var1_1) {
        block35 : {
            block36 : {
                block34 : {
                    block33 : {
                        var4_2 = null;
                        // MONITORENTER : this
                        var3_4 = this.b;
                        if (var3_4 == null) {
                            var3_4 = var4_2;
                            // MONITOREXIT : this
                            return var3_4;
                        }
                        // MONITORENTER : this
                        this.c = this.b.b();
                        if (this.c == null) {
                            // MONITOREXIT : this
                            return var4_2;
                        }
                        this.a = new RandomAccessFile(this.c, "rw");
                        var3_4 = this.b();
                        if (var3_4 != null) break block33;
                        this.c.delete();
                        var3_4 = this.a;
                        if (var3_4 == null) return var4_2;
                        try {
                            this.a.close();
                            // MONITOREXIT : this
                            return var4_2;
                        }
                        catch (Exception var3_12) {}
                    }
                    var2_13 = this.a((BitSet)var3_4);
                    var1_1 = ahc.a(var2_13, (int)this.c.length(), var1_1);
                    var3_4 = this.a(var2_13, var1_1);
                    if (var3_4 != null) break block34;
                    this.c.delete();
                    var3_4 = this.a;
                    if (var3_4 == null) return var4_2;
                    try {
                        this.a.close();
                        // MONITOREXIT : this
                        return var4_2;
                    }
                    catch (Exception var3_11) {}
                }
                try {
                    var2_13 = (var2_13 - this.b.a() - 4) / 1500;
                    var1_1 = (var1_1 - this.b.a() - 4) / 1500;
                    var5_14 = new agb(this.c, (ArrayList)var3_4, new int[]{var2_13, var1_1});
                    var6_15 = this.a;
                    var3_4 = var5_14;
                    ** if (var6_15 == null) goto lbl-1000
                }
                catch (FileNotFoundException var3_5) {
                    var3_4 = this.a;
                    if (var3_4 != null) {
                        try {
                            this.a.close();
                            var3_4 = null;
                        }
                        catch (Exception var3_6) {
                            var3_4 = null;
                        }
                    }
                    break block36;
                }
                catch (Exception var3_7) {
                    var3_4 = this.a;
                    if (var3_4 != null) {
                        try {
                            this.a.close();
                            var3_4 = null;
                        }
                        catch (Exception var3_8) {
                            var3_4 = null;
                        }
                    }
                    break block36;
                }
                catch (Throwable var3_9) {
                    var4_2 = this.a;
                    if (var4_2 == null) throw var3_9;
                    try {
                        this.a.close();
                    }
                    catch (Exception var4_3) {
                        throw var3_9;
                    }
                    throw var3_9;
                }
lbl-1000: // 1 sources:
                {
                    this.a.close();
                    var3_4 = var5_14;
                }
lbl-1000: // 1 sources:
                {
                    break block35;
                }
                catch (Exception var3_10) {
                    var3_4 = var5_14;
                }
                ** GOTO lbl89
                return var4_2;
                return var4_2;
            }
            var3_4 = null;
        }
        if (var3_4 != null && var3_4.c() > 100 && var3_4.c() < 5242880) {
            // MONITOREXIT : this
            return var3_4;
        }
        this.c.delete();
        this.c = null;
        // MONITOREXIT : this
        return var4_2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected final void a(agb var1_1) {
        var6_8 = null;
        var5_9 = null;
        // MONITORENTER : this
        // MONITORENTER : this
        this.c = var1_1.a;
        if (this.c == null) {
            // MONITOREXIT : this
            // MONITOREXIT : this
            return;
        }
        var3_10 = var5_9;
        var4_12 = var6_8;
        this.a = new RandomAccessFile(this.c, "rw");
        var3_10 = var5_9;
        var4_12 = var6_8;
        var7_13 = new byte[this.b.a()];
        var3_10 = var5_9;
        var4_12 = var6_8;
        this.a.read(var7_13);
        var3_10 = var5_9;
        var4_12 = var6_8;
        var3_10 = var5_9 = agc.b(var7_13);
        var4_12 = var5_9;
        if (var1_1.b()) {
            var3_10 = var5_9;
            var4_12 = var5_9;
            var2_14 = var1_1.b[0];
            do {
                var3_10 = var5_9;
                var4_12 = var5_9;
                if (var2_14 > var1_1.b[1]) break;
                var3_10 = var5_9;
                var4_12 = var5_9;
                var5_9.set(var2_14, false);
                ++var2_14;
            } while (true);
            var3_10 = var5_9;
            var4_12 = var5_9;
            this.a.seek(0);
            var3_10 = var5_9;
            var4_12 = var5_9;
            this.a.write(agc.a((BitSet)var5_9));
        }
        var3_10 = this.a;
        var1_1 = var5_9;
        if (var3_10 == null) ** GOTO lbl82
        this.a.close();
        var1_1 = var5_9;
        catch (FileNotFoundException var1_2) {
            var4_12 = this.a;
            var1_1 = var3_10;
            if (var4_12 == null) ** GOTO lbl82
            try {
                this.a.close();
                var1_1 = var3_10;
            }
            catch (IOException var1_3) {
                var1_1 = var3_10;
            }
        }
        catch (IOException var1_4) {
            var3_10 = this.a;
            var1_1 = var4_12;
            if (var3_10 == null) ** GOTO lbl82
            try {
                this.a.close();
                var1_1 = var4_12;
            }
            catch (IOException var1_5) {
                var1_1 = var4_12;
            }
        }
        catch (Throwable var1_6) {
            var3_10 = this.a;
            if (var3_10 == null) throw var1_6;
            try {
                this.a.close();
            }
            catch (IOException var3_11) {
                throw var1_6;
            }
            throw var1_6;
        }
        catch (IOException var1_7) {
            var1_1 = var5_9;
        }
lbl82: // 9 sources:
        if (var1_1.isEmpty()) {
            this.c.delete();
        }
        this.c = null;
        // MONITOREXIT : this
    }
}

