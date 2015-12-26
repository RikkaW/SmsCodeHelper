/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.io.RandomAccessFile
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.BitSet
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;

public final class ahd {
    private RandomAccessFile a;
    private agc b;
    private String c = "";
    private File d = null;

    protected ahd(agc agc2) {
        this.b = agc2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected final void a(long var1_1, byte[] var3_2) {
        block25 : {
            var5_12 = 0;
            // MONITORENTER : this
            var7_13 = this.d = this.b.a(var1_1);
            if (var7_13 == null) ** GOTO lbl-1000
            this.a = new RandomAccessFile(this.d, "rw");
            var7_13 = new byte[this.b.a()];
            var4_15 = this.a.read((byte[])var7_13) == -1 ? 0 : this.a.readInt();
            var7_13 = agc.b((byte[])var7_13);
            var6_16 = this.b.a();
            if (var4_15 < 0 || var4_15 > this.b.a() << 3) {
                this.a.close();
                this.d.delete();
                var3_2 = this.a;
                ** if (var3_2 != null) goto lbl19
            }
            ** GOTO lbl24
lbl-1000: // 2 sources:
            {
                // MONITOREXIT : this
                return;
            }
lbl19: // 1 sources:
            try {
                this.a.close();
                return;
            }
            catch (IOException var3_3) {
                return;
            }
lbl24: // 1 sources:
            this.a.seek((long)(var6_16 + 4 + var4_15 * 1500));
            var3_2 = agc.a((byte[])var3_2);
            this.a.writeInt(var3_2.length);
            this.a.writeLong(var1_1);
            this.a.write((byte[])var3_2);
            var7_13.set(var4_15, true);
            this.a.seek(0);
            this.a.write(agc.a((BitSet)var7_13));
            if (++var4_15 == this.b.a() << 3) {
                var4_15 = var5_12;
            }
            this.a.writeInt(var4_15);
            if (!this.c.equalsIgnoreCase(this.d.getName())) {
                this.c = this.d.getName();
            }
            this.d.length();
            var3_2 = this.a;
            if (var3_2 == null) ** GOTO lbl68
            try {
                this.a.close();
                break block25;
            }
            catch (IOException var3_11) {}
            catch (FileNotFoundException var3_4) {
                var3_5 = this.a;
                if (var3_5 == null) ** GOTO lbl68
                try {
                    this.a.close();
                }
                catch (IOException var3_6) {}
            }
            catch (IOException var3_7) {
                var3_8 = this.a;
                if (var3_8 == null) ** GOTO lbl68
                try {
                    this.a.close();
                }
                catch (IOException var3_9) {}
            }
            catch (Throwable var3_10) {
                var7_13 = this.a;
                if (var7_13 == null) throw var3_10;
                try {
                    this.a.close();
                }
                catch (IOException var7_14) {
                    throw var3_10;
                }
                throw var3_10;
            }
        }
        this.d = null;
    }
}

