/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.location.Location
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Environment
 *  android.os.Process
 *  android.os.StatFs
 *  android.telephony.NeighboringCellInfo
 *  android.text.TextUtils
 *  java.io.ByteArrayOutputStream
 *  java.io.File
 *  java.io.OutputStream
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.reflect.Method
 *  java.util.ArrayList
 *  java.util.BitSet
 */
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.NeighboringCellInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class agc {
    private Context a;
    private int b = 0;
    private int c = 0;
    private int d = 0;

    protected agc(Context context) {
        this.a = context;
        this.a(768);
    }

    private static int a(int n2, int n3) {
        if (n2 < n3) {
            return n2;
        }
        return n3;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static afv a(Location object, agf object2, int n2, byte by2, long l2, boolean bl2) {
        Object object3;
        int n3;
        afv afv2 = new afv();
        if (n2 <= 0 || n2 > 3 || object2 == null) {
            return null;
        }
        int n4 = n2 == 1 || n2 == 3 ? 1 : 0;
        boolean bl3 = n2 == 2 || n2 == 3;
        Object object4 = object2.p().getBytes();
        System.arraycopy((Object)object4, (int)0, (Object)afv2.c, (int)0, (int)agc.a(object4.length, afv2.c.length));
        object4 = object2.f().getBytes();
        System.arraycopy((Object)object4, (int)0, (Object)afv2.g, (int)0, (int)agc.a(object4.length, afv2.g.length));
        object4 = object2.g().getBytes();
        System.arraycopy((Object)object4, (int)0, (Object)afv2.a, (int)0, (int)agc.a(object4.length, afv2.a.length));
        object4 = object2.h().getBytes();
        System.arraycopy((Object)object4, (int)0, (Object)afv2.b, (int)0, (int)agc.a(object4.length, afv2.b.length));
        afv2.d = (short)object2.q();
        afv2.e = (short)object2.r();
        afv2.f = (byte)object2.s();
        object4 = object2.t().getBytes();
        System.arraycopy((Object)object4, (int)0, (Object)afv2.h, (int)0, (int)agc.a(object4.length, afv2.h.length));
        l2 /= 1000;
        n2 = object != null && object2.e() ? 1 : 0;
        if (n2 != 0) {
            object4 = new aid();
            object4.b = (int)l2;
            afu afu2 = new afu();
            afu2.a = (int)(object.getLongitude() * 1000000.0);
            afu2.b = (int)(object.getLatitude() * 1000000.0);
            afu2.c = (int)object.getAltitude();
            afu2.d = (int)object.getAccuracy();
            afu2.e = (int)object.getSpeed();
            afu2.f = (short)object.getBearing();
            afu2.g = !Build.MODEL.equals((Object)"sdk") && (!agf.b(object2.y()) || !aie.b) ? 0 : 1;
            afu2.h = by2;
            afu2.i = System.currentTimeMillis();
            afu2.j = object2.o();
            object4.c = afu2;
            afv2.j.add(object4);
        } else {
            if (!bl2) return null;
            object4 = new aid();
            object4.b = (int)l2;
            afx afx2 = new afx();
            afx2.a = object2.x();
            for (n2 = 0; n2 < afx2.a; ++n2) {
                object3 = new afy();
                object3.a = (byte)object2.a(n2).length();
                System.arraycopy((Object)object2.a(n2).getBytes(), (int)0, (Object)object3.b, (int)0, (int)object3.a);
                object3.c = object2.b(n2);
                object3.d = object2.c(n2);
                object3.e = object2.d(n2);
                object3.f = object2.e(n2);
                object3.g = object2.f(n2);
                object3.h = (byte)object2.g(n2).length();
                System.arraycopy((Object)object2.g(n2).getBytes(), (int)0, (Object)object3.i, (int)0, (int)object3.h);
                object3.j = object2.h(n2);
                afx2.b.add(object3);
            }
            object4.g = afx2;
            afv2.j.add(object4);
        }
        n2 = n3 = 1;
        if (object2.c()) {
            n2 = n3;
            if (!object2.i()) {
                n2 = n3;
                if (n4 != 0) {
                    n2 = n3;
                    if (!bl2) {
                        object4 = new aid();
                        object4.b = (int)l2;
                        aib aib2 = new aib();
                        object3 = object2.a(object.getSpeed());
                        if (object3 != null && object3.size() >= 3) {
                            aib2.a = (short)((Integer)object3.get(0)).intValue();
                            aib2.b = (Integer)object3.get(1);
                        }
                        aib2.c = object2.l();
                        object3 = object2.m();
                        aib2.d = (byte)object3.size();
                        for (n2 = 0; n2 < object3.size(); ++n2) {
                            age age2 = new age();
                            age2.a = (short)((NeighboringCellInfo)object3.get(n2)).getLac();
                            age2.b = ((NeighboringCellInfo)object3.get(n2)).getCid();
                            age2.c = (byte)((NeighboringCellInfo)object3.get(n2)).getRssi();
                            aib2.e.add((Object)age2);
                        }
                        object4.d = aib2;
                        n2 = 2;
                        afv2.j.add(object4);
                    }
                }
            }
        }
        n2 = n3 = n2;
        if (object2.c()) {
            n2 = n3;
            if (object2.i()) {
                n2 = n3;
                if (n4 != 0) {
                    n2 = n3;
                    if (!bl2) {
                        object4 = new aid();
                        object4.b = (int)l2;
                        agd agd2 = new agd();
                        if ((object = object2.b(object.getSpeed())) != null && object.size() >= 6) {
                            agd2.a = (Integer)object.get(3);
                            agd2.b = (Integer)object.get(4);
                            agd2.c = (short)((Integer)object.get(0)).intValue();
                            agd2.d = (short)((Integer)object.get(1)).intValue();
                            agd2.e = (Integer)object.get(2);
                            agd2.f = object2.l();
                        }
                        object4.e = agd2;
                        n2 = n3 + 1;
                        afv2.j.add(object4);
                    }
                }
            }
        }
        n4 = n2;
        if (object2.d()) {
            n4 = n2;
            if (bl3) {
                n4 = n2;
                if (!bl2) {
                    object = new aid();
                    object4 = new afz();
                    object2 = object2.u();
                    object.b = (int)((Long)object2.get(0) / 1000);
                    object4.a = (byte)(object2.size() - 1);
                    for (n4 = 1; n4 < object2.size(); ++n4) {
                        object3 = (List)object2.get(n4);
                        if (object3 == null || object3.size() < 3) continue;
                        aga aga2 = new aga();
                        byte[] arrby = ((String)object3.get(0)).getBytes();
                        System.arraycopy((Object)arrby, (int)0, (Object)aga2.a, (int)0, (int)agc.a(arrby.length, aga2.a.length));
                        aga2.b = (short)((Integer)object3.get(1)).intValue();
                        object3 = ((String)object3.get(2)).getBytes();
                        System.arraycopy((Object)object3, (int)0, (Object)aga2.c, (int)0, (int)agc.a(object3.length, aga2.c.length));
                        object4.b.add((Object)aga2);
                    }
                    object.f = object4;
                    n4 = n2 + 1;
                    afv2.j.add(object);
                }
            }
        }
        afv2.i = (short)n4;
        if (n4 < 2 && !bl2) {
            return null;
        }
        return afv2;
    }

    protected static File a(Context object) {
        object = "/Android/data/" + object.getPackageName() + "/files/";
        return new File(Environment.getExternalStorageDirectory().getPath() + (String)object);
    }

    public static /* varargs */ Object a(Object object, String string2, Object ... arrobject) {
        Class class_ = object.getClass();
        Class[] arrclass = new Class[arrobject.length];
        int n2 = arrobject.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            arrclass[i2] = arrobject[i2].getClass();
            if (arrclass[i2] != Integer.class) continue;
            arrclass[i2] = Integer.TYPE;
        }
        if (!(string2 = class_.getDeclaredMethod(string2, arrclass)).isAccessible()) {
            string2.setAccessible(true);
        }
        return string2.invoke(object, arrobject);
    }

    private static ArrayList a(File[] arrfile) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < arrfile.length; ++i2) {
            if (!arrfile[i2].isFile() || arrfile[i2].getName().length() != 10 || !TextUtils.isDigitsOnly((CharSequence)arrfile[i2].getName())) continue;
            arrayList.add((Object)arrfile[i2]);
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static byte[] a(BitSet bitSet) {
        byte[] arrby = new byte[bitSet.size() / 8];
        int n2 = 0;
        while (n2 < bitSet.size()) {
            int n3 = n2 / 8;
            byte by2 = arrby[n3];
            int n4 = bitSet.get(n2) ? 1 : 0;
            arrby[n3] = (byte)(n4 << 7 - n2 % 8 | by2);
            ++n2;
        }
        return arrby;
    }

    protected static byte[] a(byte[] arrby) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] arrby2 = null;
        Object object = arrby2;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            object = arrby2;
        }
        catch (Exception var0_1) {
            return object;
        }
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream((OutputStream)byteArrayOutputStream);
        object = arrby2;
        gZIPOutputStream.write(arrby);
        object = arrby2;
        gZIPOutputStream.finish();
        object = arrby2;
        gZIPOutputStream.close();
        object = arrby2;
        arrby = byteArrayOutputStream.toByteArray();
        object = arrby;
        byteArrayOutputStream.close();
        return arrby;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static byte[] a(byte[] arrby, int n2) {
        if (arrby == null || arrby.length == 0) {
            return null;
        }
        int n3 = new String(arrby).indexOf(0);
        if (n3 > 0) {
            if (n3 + 1 <= n2) {
                n2 = n3 + 1;
            }
        } else {
            n2 = 1;
        }
        byte[] arrby2 = new byte[n2];
        System.arraycopy((Object)arrby, (int)0, (Object)arrby2, (int)0, (int)n2);
        arrby2[n2 - 1] = 0;
        return arrby2;
    }

    public static /* varargs */ int b(Object object, String string2, Object ... arrobject) {
        Class class_ = object.getClass();
        Class[] arrclass = new Class[arrobject.length];
        int n2 = arrobject.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            arrclass[i2] = arrobject[i2].getClass();
            if (arrclass[i2] != Integer.class) continue;
            arrclass[i2] = Integer.TYPE;
        }
        if (!(string2 = class_.getDeclaredMethod(string2, arrclass)).isAccessible()) {
            string2.setAccessible(true);
        }
        return (Integer)string2.invoke(object, arrobject);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static BitSet b(byte[] arrby) {
        BitSet bitSet = new BitSet(arrby.length << 3);
        int n2 = 0;
        int n3 = 0;
        while (n2 < arrby.length) {
            for (int i2 = 7; i2 >= 0; --i2, ++n3) {
                boolean bl2 = (arrby[n2] & 1 << i2) >> i2 == 1;
                bitSet.set(n3, bl2);
            }
            ++n2;
        }
        return bitSet;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private File c(long l2) {
        Object object;
        boolean bl2;
        boolean bl3 = false;
        if (Process.myUid() == 1000) {
            return null;
        }
        try {
            bl2 = "mounted".equals((Object)Environment.getExternalStorageState());
        }
        catch (Exception var5_4) {
            bl2 = false;
        }
        if (!agc.c() || bl2) {
            object = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if ((long)object.getAvailableBlocks() * (long)object.getBlockSize() <= (long)(this.c / 2)) {
                return null;
            }
            object = agc.a(this.a).getPath();
            if (!(object = new File((String)object + File.separator + "carrierdata")).exists() || !object.isDirectory()) {
                object.mkdirs();
            }
            object = new File(object.getPath() + File.separator + l2);
            try {
                bl2 = object.createNewFile();
            }
            catch (IOException var6_6) {
                bl2 = bl3;
            }
        } else {
            object = null;
            bl2 = bl3;
        }
        if (bl2) return object;
        return null;
    }

    protected static boolean c() {
        if (Build.VERSION.SDK_INT >= 9) {
            try {
                boolean bl2 = (Boolean)Environment.class.getMethod("isExternalStorageRemovable", null).invoke((Object)null, null);
                return bl2;
            }
            catch (Exception var1_1) {
                // empty catch block
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private File d() {
        boolean bl2;
        if (Process.myUid() == 1000) {
            return null;
        }
        try {
            bl2 = "mounted".equals((Object)Environment.getExternalStorageState());
        }
        catch (Exception var2_2) {
            bl2 = false;
        }
        if (agc.c()) {
            if (!bl2) return null;
        }
        ArrayList arrayList = agc.a(this.a).getPath();
        if (!(arrayList = new File((String)arrayList + File.separator + "carrierdata")).exists()) return null;
        if (!arrayList.isDirectory()) return null;
        if ((arrayList = arrayList.listFiles()) == null) return null;
        if (arrayList.length <= 0) return null;
        if ((arrayList = agc.a((File[])arrayList)).size() == 1) {
            if (((File)arrayList.get(0)).length() >= (long)this.d) return null;
            return (File)arrayList.get(0);
        }
        if (arrayList.size() < 2) return null;
        File file = (File)arrayList.get(0);
        File file2 = (File)arrayList.get(1);
        arrayList = file;
        if (file.getName().compareTo(file2.getName()) > 0) return arrayList;
        return file2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private int e() {
        boolean bl2;
        if (Process.myUid() == 1000) {
            return 0;
        }
        try {
            bl2 = "mounted".equals((Object)Environment.getExternalStorageState());
        }
        catch (Exception var2_2) {
            bl2 = false;
        }
        if (agc.c()) {
            if (!bl2) return 0;
        }
        ArrayList arrayList = agc.a(this.a).getPath();
        if (!(arrayList = new File((String)arrayList + File.separator + "carrierdata")).exists()) return 0;
        if (!arrayList.isDirectory()) return 0;
        if ((arrayList = arrayList.listFiles()) == null) return 0;
        if (arrayList.length <= 0) return 0;
        if ((arrayList = agc.a((File[])arrayList)).size() == 1) {
            if (((File)arrayList.get(0)).length() > 0) return 1;
            return 10;
        }
        if (arrayList.size() < 2) return 0;
        return 2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private File f() {
        File file;
        boolean bl2;
        if (Process.myUid() == 1000) {
            return null;
        }
        try {
            bl2 = "mounted".equals((Object)Environment.getExternalStorageState());
        }
        catch (Exception var2_2) {
            bl2 = false;
        }
        if (agc.c()) {
            if (!bl2) return null;
        }
        if ((file = agc.a(this.a)) == null) return null;
        file = file.getPath();
        if (!(file = new File((String)file + File.separator + "carrierdata")).exists()) return null;
        if (!file.isDirectory()) return null;
        if ((file = file.listFiles()) == null) return null;
        if (file.length <= 0) return null;
        ArrayList arrayList = agc.a((File[])file);
        if (arrayList.size() < 2) return null;
        file = (File)arrayList.get(0);
        arrayList = (File)arrayList.get(1);
        if (file.getName().compareTo(arrayList.getName()) <= 0) return file;
        return arrayList;
    }

    protected int a() {
        return this.b;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected File a(long l2) {
        synchronized (this) {
            File file;
            File file2 = file = this.d();
            if (file != null) return file2;
            return this.c(l2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(int n2) {
        this.b = n2;
        this.c = (this.b << 3) * 1500 + this.b + 4;
        if (this.b == 256 || this.b == 768) {
            this.d = this.c / 100;
            return;
        } else {
            if (this.b != 8736) return;
            {
                this.d = this.c - 5000;
                return;
            }
        }
    }

    protected File b() {
        return this.f();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean b(long l2) {
        boolean bl2 = false;
        synchronized (this) {
            int n2;
            block5 : {
                n2 = this.e();
                if (n2 != 0) break block5;
                return bl2;
            }
            if (n2 == 1) {
                File file = this.c(l2);
                if (file == null) return bl2;
                return true;
            }
            if (n2 != 2) return bl2;
            return true;
        }
    }
}

