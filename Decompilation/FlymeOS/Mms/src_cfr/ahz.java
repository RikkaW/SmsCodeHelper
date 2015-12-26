/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.location.Location
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.SystemClock
 *  android.provider.Settings
 *  android.provider.Settings$Global
 *  android.provider.Settings$System
 *  android.telephony.CellLocation
 *  android.telephony.TelephonyManager
 *  android.telephony.gsm.GsmCellLocation
 *  android.text.TextUtils
 *  android.widget.Toast
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Character
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  org.apache.http.params.HttpParams
 */
import android.content.ContentResolver;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.widget.Toast;
import com.amap.api.location.core.c;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.params.HttpParams;

public class ahz {
    static float a(double[] arrd) {
        if (arrd.length != 4) {
            return 0.0f;
        }
        float[] arrf = new float[1];
        Location.distanceBetween((double)arrd[0], (double)arrd[1], (double)arrd[2], (double)arrd[3], (float[])arrf);
        return arrf[0];
    }

    static int a(int n2) {
        return n2 * 2 - 113;
    }

    static int a(CellLocation cellLocation, Context context) {
        if (ahz.a(context)) {
            ahz.a(new Object[]{"air plane mode on"});
            return 9;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName((String)"android.telephony.cdma.CdmaCellLocation");
            return 2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return 9;
        }
    }

    static long a() {
        return System.currentTimeMillis();
    }

    /*
     * Enabled aggressive block sorting
     */
    static void a(Context context, String string2) {
        boolean bl2;
        String string3 = string2;
        if (string2 == null) {
            string3 = "null";
        }
        if (c.j().indexOf("test") != -1) {
            bl2 = true;
        } else if (ahk.d.indexOf("test") != -1) {
            bl2 = true;
        } else {
            void var1_4;
            Object var1_2 = null;
            if (c.j().length() > 0) {
                char[] arrc = c.j().substring(7, 8).toCharArray();
            }
            if (var1_4 != null && Character.isLetter((char)var1_4[0])) {
                return;
            }
            bl2 = true;
        }
        if (bl2) {
            if (context == null) return;
            Toast.makeText((Context)context, (CharSequence)string3, (int)0).show();
            ahz.a(new Object[]{string3});
        }
    }

    public static void a(Throwable throwable) {
    }

    static void a(HttpParams httpParams, int n2) {
        httpParams.setIntParameter("http.connection.timeout", n2);
        httpParams.setIntParameter("http.socket.timeout", n2);
        httpParams.setLongParameter("http.conn-manager.timeout", (long)n2);
    }

    public static /* varargs */ void a(Object ... arrobject) {
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    static boolean a(ahf ahf2) {
        if (ahf2 == null) {
            return false;
        }
        if (ahf2.j().equals((Object)"5")) return false;
        if (ahf2.j().equals((Object)"6")) return false;
        double d2 = ahf2.e();
        double d3 = ahf2.f();
        float f2 = ahf2.g();
        if (d2 != 0.0) return true;
        if (d3 != 0.0) return true;
        if ((double)f2 == 0.0) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static boolean a(Context context) {
        boolean bl2 = true;
        boolean bl3 = true;
        if (context == null) {
            return false;
        }
        context = context.getContentResolver();
        if (ahz.c() < 17) {
            try {
                int n2 = Settings.System.getInt((ContentResolver)context, (String)"airplane_mode_on", (int)0);
                if (n2 != 1) return false;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return false;
            }
            return bl3;
        }
        try {
            int n3 = Settings.Global.getInt((ContentResolver)context, (String)"airplane_mode_on", (int)0);
            if (n3 != 1) return false;
            return bl2;
        }
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2) || !TextUtils.isDigitsOnly((CharSequence)string2)) {
            return false;
        }
        return ",111,123,134,199,202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,560,598,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,665,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,850,901,".contains((CharSequence)("," + string2 + ","));
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static byte[] a(byte[] object) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream((OutputStream)byteArrayOutputStream);
            gZIPOutputStream.write((byte[])object);
            gZIPOutputStream.close();
            object = byteArrayOutputStream.toByteArray();
        }
        catch (Throwable var1_2) {
            void var1_3;
            object = null;
            var1_3.printStackTrace();
            return object;
        }
        byteArrayOutputStream.close();
        return object;
        {
            catch (Throwable throwable) {}
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String[] a(TelephonyManager var0) {
        block11 : {
            var1_3 = 1;
            var2_4 = 0;
            var4_5 = new String[]{"0", "0"};
            var3_6 = null;
            if (var0 == null) ** GOTO lbl8
            try {
                var3_6 = var0.getNetworkOperator();
lbl8: // 2 sources:
                if (TextUtils.isEmpty((CharSequence)var3_6)) {
                    var1_3 = 0;
                } else if (!TextUtils.isDigitsOnly((CharSequence)var3_6)) {
                    var1_3 = 0;
                } else if (var3_6.length() <= 4) {
                    var1_3 = 0;
                }
                if (var1_3 == 0) break block11;
                var4_5[0] = var3_6.substring(0, 3);
                var0 = var3_6.substring(3).toCharArray();
            }
            catch (Exception var0_1) {
                return var4_5;
            }
            for (var1_3 = 0; var1_3 < var0.length && Character.isDigit((char)var0[var1_3]); ++var1_3) {
            }
            var4_5[1] = var3_6.substring(3, var1_3 + 3);
        }
        try {
            var1_3 = Integer.parseInt((String)var4_5[0]);
        }
        catch (Exception var0_2) {
            var1_3 = var2_4;
        }
        if (var1_3 != 0) return var4_5;
        var4_5[0] = "0";
        return var4_5;
    }

    static long b() {
        return SystemClock.elapsedRealtime();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static NetworkInfo b(Context context) {
        if ((context = (ConnectivityManager)ahz.b(context, "connectivity")) == null) return null;
        try {
            return context.getActiveNetworkInfo();
        }
        catch (SecurityException var0_1) {
            return null;
        }
    }

    static Object b(Context context, String string2) {
        if (context == null) {
            return null;
        }
        return context.getApplicationContext().getSystemService(string2);
    }

    static int c() {
        try {
            int n2 = Build.VERSION.SDK_INT;
            return n2;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            try {
                int n3 = Integer.parseInt((String)Build.VERSION.SDK.toString());
                return n3;
            }
            catch (Throwable var1_3) {
                var1_3.printStackTrace();
                ahz.a(var1_3);
                return 0;
            }
        }
    }
}

