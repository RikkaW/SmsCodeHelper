/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Handler
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.UUID
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class aba {
    public static final Uri a = Uri.parse((String)"content://com.meizu.cloud/recvtype_bind_sim");
    private static aba f;
    private Context b;
    private Handler c;
    private b d;
    private int e = -1;
    private aiq g = null;
    private aio h;
    private air i;

    private aba(Context context, b b2) {
        this.h = new abb(this);
        this.i = new abc(this);
        this.b = context;
        this.c = new Handler();
        this.d = b2;
        this.e();
    }

    static /* synthetic */ int a(aba aba2) {
        return aba2.e;
    }

    static /* synthetic */ int a(aba aba2, int n2) {
        aba2.e = n2;
        return n2;
    }

    public static aba a() {
        if (f == null) {
            throw new IllegalStateException("Uninitialized.");
        }
        return f;
    }

    static /* synthetic */ aiq a(aba aba2, aiq aiq2) {
        aba2.g = aiq2;
        return aiq2;
    }

    public static ArrayList<abd> a(Intent intent) {
        int n2;
        ArrayList arrayList = new ArrayList();
        try {
            intent = new JSONArray(intent.getStringExtra("results"));
            n2 = 0;
        }
        catch (JSONException var0_1) {
            var0_1.printStackTrace();
        }
        do {
            if (n2 < intent.length()) {
                arrayList.add((Object)new abd(intent.getJSONObject(n2)));
                ++n2;
                continue;
            }
            break;
        } while (true);
        return arrayList;
    }

    public static void a(Context context, b b2) {
        if (f != null) {
            Log.w((String)"SipSmsManager", (String)"Already initialized.");
        }
        f = new aba(context, b2);
    }

    static /* synthetic */ b b(aba aba2) {
        return aba2.d;
    }

    static /* synthetic */ Context c(aba aba2) {
        return aba2.b;
    }

    static /* synthetic */ aio d(aba aba2) {
        return aba2.h;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void e() {
        if (this.g != null) return;
        try {
            this.g = new aiq(this.b, this.i);
            this.g.a();
            return;
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    private aiq f() {
        if (this.g != null) {
            return this.g;
        }
        Log.e((String)"SipSmsManager", (String)"getMzCloudService is null begin bindSmsService");
        this.e();
        return this.g;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(aio aio2) {
        Log.e((String)"SipSmsManager", (String)"registerCallback");
        long l2 = System.currentTimeMillis();
        try {
            if (this.f() != null) {
                this.f().a(aio2);
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SipSmsManager", (String)("registerCallback error" + var1_2.getMessage()));
        }
        wd.a("SipSmsManager", "registerCallback", l2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(aip aip2) {
        Log.e((String)"SipSmsManager", (String)"registerFileListener");
        long l2 = System.currentTimeMillis();
        try {
            if (this.f() != null) {
                this.f().a(aip2);
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SipSmsManager", (String)("registerFileListener error" + var1_2.getMessage()));
        }
        wd.a("SipSmsManager", "registerFileListener", l2);
    }

    public void a(Context context, String[] arrstring) {
        Intent intent = new Intent("com.android.mms.sip.dest_check_popup");
        intent.setData(Uri.parse((String)"content://sipmessagedestcheck/"));
        context = PendingIntent.getBroadcast((Context)context, (int)0, (Intent)intent, (int)134217728);
        boolean bl2 = aba.a().a(arrstring, (PendingIntent)context);
        Log.d((String)"SipSmsManager", (String)("checkSipOnline result = " + bl2));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void a(String var1_1, String var2_3, PendingIntent var3_4, String var4_5, int var5_6, boolean var6_7) {
        block9 : {
            var7_8 = System.currentTimeMillis();
            var2_3 = this.a((String)var2_3);
            Log.e((String)"SipSmsManager", (String)("sendSipTextMessage, fragment size is " + var2_3.length));
            try {
                if (this.f() != null) {
                    var5_6 = 0;
                    break block9;
                }
                Log.e((String)"SipSmsManager", (String)"getMzCloudService() is return null!");
            }
            catch (Exception var1_2) {
                Log.e((String)"SipSmsManager", (String)("sendSipTextMessage error" + var1_2.getMessage()));
            }
            ** GOTO lbl-1000
        }
        do {
            if (var5_6 < var2_3.length) {
                if (var5_6 == var2_3.length - 1) {
                    this.f().a(var1_1, var2_3[var5_6], var3_4, var4_5, var6_7);
                } else {
                    var9_9 = UUID.randomUUID().toString();
                    this.f().a(var1_1, var2_3[var5_6], null, var9_9, false);
                }
            } else lbl-1000: // 3 sources:
            {
                wd.a("SipSmsManager", "sendSipTextMessage", var7_8);
                return;
            }
            ++var5_6;
        } while (true);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean a(String string2, String string3, String string4, String string5, long l2) {
        long l3;
        boolean bl2;
        block4 : {
            Log.e((String)"SipSmsManager", (String)"getFlymeMMS");
            l3 = System.currentTimeMillis();
            bl2 = this.f() != null ? this.f().a(string2, string3, string4, string5, l2) : false;
            Log.e((String)"SipSmsManager", (String)("getFlymeMMS result = " + bl2));
            break block4;
            {
                catch (Exception exception) {}
            }
            catch (Exception exception) {
                void var1_3;
                bl2 = false;
                Log.e((String)"SipSmsManager", (String)("getFlymeMMS error" + var1_3.getMessage()));
            }
        }
        wd.a("SipSmsManager", "getFlymeMMS", l3);
        return bl2;
    }

    public boolean a(String[] arrstring, PendingIntent pendingIntent) {
        Log.d((String)"SipSmsManager", (String)"checkSipOnlineAsync");
        long l2 = System.currentTimeMillis();
        try {
            if (this.f() != null) {
                boolean bl2 = this.f().a(arrstring, pendingIntent);
                return bl2;
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SipSmsManager", (String)("checkSipDestAddrIsAvailable error" + var1_2.getMessage()));
        }
        wd.a("SipSmsManager", "checkSipOnlineAsync", l2);
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a(String[] arrstring, String string2, byte[] arrby, String string3, boolean bl2, String string4) {
        long l2;
        block3 : {
            block2 : {
                Log.e((String)"SipSmsManager", (String)"sendFlymeMMS");
                l2 = System.currentTimeMillis();
                try {
                    if (this.f() == null) break block2;
                    bl2 = this.f().a(arrstring, string2, arrby, string3, bl2, string4);
                    break block3;
                }
                catch (Exception var1_2) {
                    Log.e((String)"SipSmsManager", (String)("sendFlymeMMS error" + var1_2.getMessage()));
                }
            }
            bl2 = false;
        }
        wd.a("SipSmsManager", "sendFlymeMMS", l2);
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String[] a(String string2) {
        int n2 = (string2.length() + 1000 - 1) / 1000;
        String[] arrstring = new String[n2];
        int n3 = string2.length();
        int n4 = 0;
        while (n4 < n2) {
            arrstring[n4] = n3 > (n4 + 1) * 1000 ? string2.substring(n4 * 1000, (n4 + 1) * 1000) : string2.substring(n4 * 1000, n3);
            ++n4;
        }
        return arrstring;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(aio aio2) {
        Log.e((String)"SipSmsManager", (String)"unregisterCallback");
        long l2 = System.currentTimeMillis();
        try {
            if (this.g != null) {
                this.g.b(aio2);
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SipSmsManager", (String)("unregisterCallback error" + var1_2.getMessage()));
        }
        wd.a("SipSmsManager", "unregisterCallback", l2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(aip aip2) {
        Log.e((String)"SipSmsManager", (String)"unregisterFileListener");
        long l2 = System.currentTimeMillis();
        try {
            if (this.g != null) {
                this.g.b(aip2);
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SipSmsManager", (String)("unregisterFileListener error" + var1_2.getMessage()));
        }
        wd.a("SipSmsManager", "unregisterFileListener", l2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(String string2) {
        Log.e((String)"SipSmsManager", (String)"cancelFlymeMMSTransaction");
        long l2 = System.currentTimeMillis();
        try {
            if (this.f() != null) {
                this.f().a(string2);
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SipSmsManager", (String)("cancelFlymeMMSTransaction error" + var1_2.getMessage()));
        }
        wd.a("SipSmsManager", "cancelFlymeMMSTransaction", l2);
    }

    public boolean b() {
        return true;
    }

    public boolean c() {
        if (!wd.j() || this.e == 0) {
            return true;
        }
        return false;
    }

    public boolean d() {
        if (this.e == 1) {
            return true;
        }
        return false;
    }

    public static abstract class a
    extends aip {
    }

    public static interface b {
        public void a(int var1);
    }

}

