/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.database.Cursor
 *  android.database.DatabaseUtils
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 */
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;

public class aad {
    private static final String[] g = new String[]{"_id"};
    private ano a;
    private b b;
    private a c;
    private Context d;
    private Uri e;
    private Handler f;

    public aad() {
        this.f = new aae(this);
        this.d = MmsApp.c().getApplicationContext();
    }

    private int a(int n2) {
        return 2131493796;
    }

    static /* synthetic */ int a(aad aad2, int n2) {
        return aad2.a(n2);
    }

    public static void a(Context context) {
        new Thread((Runnable)new aaf(context), "asyncCreateMZAssistant").start();
    }

    private void a(Context context, String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            new Thread((Runnable)new aag(this, string2, context), "asyncInsertAssistantRecvMsg").start();
        }
    }

    static /* synthetic */ boolean b(Context context) {
        return aad.c(context);
    }

    private static boolean c() {
        if (!MmsApp.a && !MmsApp.d) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static boolean c(Context var0) {
        block12 : {
            block14 : {
                block13 : {
                    if (!aad.c() || !ga.e(var0)) ** GOTO lbl35
                    var3_4 = Uri.parse((String)"content://mms-sms/thread_id").buildUpon();
                    var3_4.appendQueryParameter("recipient", "4007883333");
                    var3_4 = var3_4.build();
                    var0 = var0.getContentResolver().query((Uri)var3_4, aad.g, null, null, null);
                    Log.d((String)"MzAssistantHelper", (String)("uri = " + (Object)var3_4 + ", cursor = " + DatabaseUtils.dumpCursorToString((Cursor)var0)));
                    if (var0 == null) break block12;
                    if (!var0.moveToFirst()) break block13;
                    var1_6 = var0.getLong(0);
lbl12: // 2 sources:
                    do {
                        var0.close();
lbl15: // 3 sources:
                        Log.d((String)"MzAssistantHelper", (String)("isNeedCreateMZAssistant() --> threadId = " + var1_6));
                        if (var1_6 == -1) {
                            return true;
                        }
                        break block14;
                        break;
                    } while (true);
                }
                try {
                    Log.d((String)"MzAssistantHelper", (String)"getThreadId returned no rows!");
                    var1_6 = -1;
                    ** continue;
                }
                catch (Throwable var3_5) {
                    try {
                        var0.close();
                        throw var3_5;
                    }
                    catch (IllegalArgumentException var0_1) {
                        var1_6 = -1;
lbl30: // 2 sources:
                        do {
                            var0_2.printStackTrace();
                            ** GOTO lbl15
                            break;
                        } while (true);
                    }
                }
            }
            return false;
lbl35: // 1 sources:
            return false;
            catch (IllegalArgumentException var0_3) {
                ** continue;
            }
        }
        var1_6 = -1;
        ** GOTO lbl15
    }

    public void a() {
        this.b = new b();
        Log.d((String)"MzAssistantHelper", (String)("bind() --> mVoiceAssistantServiceConn = " + this.b));
        Intent intent = new Intent("com.meizu.voiceassistant.support.IVoiceAssistantService");
        intent.setPackage("com.meizu.voiceassistant");
        this.d.bindService(intent, (ServiceConnection)this.b, 1);
    }

    public void a(Uri uri) {
        this.e = uri;
    }

    public void a(Uri uri, int n2) {
        if (uri == null) {
            return;
        }
        new Thread((Runnable)new aah(this, n2, uri), "asyncUpdateMZAssistantSendState").start();
    }

    public void a(String string2) {
        if (this.a == null) {
            return;
        }
        try {
            this.a.a("com.android.mms", string2);
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public Uri b(String string2) {
        ContentValues contentValues = new ContentValues(6);
        contentValues.put("address", "4007883333");
        contentValues.put("body", string2);
        contentValues.put("date", new Long(System.currentTimeMillis()));
        contentValues.put("read", Integer.valueOf((int)0));
        contentValues.put("seen", Integer.valueOf((int)0));
        contentValues.put("type", Integer.valueOf((int)2));
        return this.d.getContentResolver().insert(Telephony.Sms.Inbox.CONTENT_URI, contentValues);
    }

    public void b() {
        if (this.b != null) {
            this.d.unbindService((ServiceConnection)this.b);
            this.b = null;
            this.a = null;
        }
    }

    public void b(Uri uri, int n2) {
        if (uri == null) {
            return;
        }
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("type", Integer.valueOf((int)n2));
        this.d.getContentResolver().update(uri, contentValues, null, null);
    }

    public class a
    extends ann.a {
        @Override
        public void a(Intent intent) {
            String string2 = intent.getStringExtra("result_dialog_answer");
            Log.d((String)"MzAssistantHelper", (String)("IVoiceAssistantCallback.Stub | onSuccess | answer = " + string2 + ", rlt = " + (Object)intent));
            aad.this.a(aad.this.d, string2);
        }

        @Override
        public void b(Intent intent) {
            int n2 = intent.getIntExtra("error_code", -1);
            Log.d((String)"MzAssistantHelper", (String)("IVoiceAssistantCallback.Stub | onFailure rlt = " + (Object)intent + ", errorCode = " + n2));
            aad.this.f.sendEmptyMessage(n2);
            aad.this.a(aad.this.e, 5);
        }
    }

    public class b
    implements ServiceConnection {
        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d((String)"MzAssistantHelper", (String)"onServiceConnected");
            aad.this.a = ano.a.b(iBinder);
            try {
                aad.this.a.a("com.android.mms", 4);
                aad.this.c = new a();
                aad.this.a.a("com.android.mms", aad.this.c);
            }
            catch (RemoteException var1_2) {
                Log.d((String)"MzAssistantHelper", (String)("onServiceConnected | remote exception = " + (Object)var1_2));
            }
            catch (Exception var1_3) {
                Log.d((String)"MzAssistantHelper", (String)("onServiceConnected | exception = " + (Object)((Object)var1_3)));
            }
            Log.d((String)"MzAssistantHelper", (String)("onServiceConnected | mVoiceAssistantService = " + aad.this.a + ", mVoiceAssistantCallback = " + aad.this.c));
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d((String)"MzAssistantHelper", (String)"onServiceDisconnected");
            try {
                aad.this.a.a("com.android.mms");
                aad.this.a = null;
                return;
            }
            catch (RemoteException var1_2) {
                Log.d((String)"MzAssistantHelper", (String)("onServiceDisconnected | remote exception = " + (Object)var1_2));
                return;
            }
            catch (Exception var1_3) {
                Log.d((String)"MzAssistantHelper", (String)("onServiceDisconnected | exception = " + (Object)((Object)var1_3)));
                return;
            }
        }
    }

}

