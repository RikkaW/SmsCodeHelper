/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.apache.http.Header
 */
import android.util.Log;
import java.io.UnsupportedEncodingException;
import org.apache.http.Header;

public abstract class auv
extends atg {
    public auv(String string2) {
        this.b(string2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(byte[] var0, String var1_2) {
        var0 = var0 == null ? null : new String((byte[])var0, var1_2);
        {
            if (var0 == null) return var0;
            ** try [egrp 0[TRYBLOCK] [0 : 10->38)] { 
lbl4: // 1 sources:
            if (var0.startsWith("\ufeff") == false) return var0;
            return var0.substring(1);
        }
lbl6: // 1 sources:
        catch (UnsupportedEncodingException var0_1) {
            Log.e((String)"TextHttpResponseHandler", (String)"Encoding response into string failed", (Throwable)var0_1);
            return null;
        }
    }

    public abstract void a(int var1, Header[] var2, String var3);

    public abstract void a(int var1, Header[] var2, String var3, Throwable var4);

    @Override
    public void a(int n2, Header[] arrheader, byte[] arrby) {
        this.a(n2, arrheader, auv.a(arrby, this.e()));
    }

    @Override
    public void b(int n2, Header[] arrheader, byte[] arrby, Throwable throwable) {
        this.a(n2, arrheader, auv.a(arrby, this.e()), throwable);
    }
}

