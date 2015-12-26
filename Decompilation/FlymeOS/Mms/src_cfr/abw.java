/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Intent
 *  android.database.Cursor
 *  android.database.DatabaseUtils
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;

class abw
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ abu c;

    abw(abu abu2, String string2, int n2) {
        this.c = abu2;
        this.a = string2;
        this.b = n2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    @Override
    public void run() {
        block11 : {
            block12 : {
                var3_1 = Uri.parse((String)"content://mms-sms/thread_id").buildUpon();
                var3_1.appendQueryParameter("recipient", this.a);
                var4_4 = var3_1.build();
                var3_1 = abu.c(this.c).getContentResolver().query(var4_4, abu.b(), null, null, null);
                Log.d((String)abu.c(), (String)("uri = " + (Object)var4_4 + ", cursor = " + DatabaseUtils.dumpCursorToString((Cursor)var3_1)));
                if (var3_1 == null) break block11;
                if (!var3_1.moveToFirst()) break block12;
                var1_6 = var3_1.getLong(0);
lbl11: // 2 sources:
                do {
                    var3_1.close();
lbl14: // 3 sources:
                    do {
                        Log.d((String)abu.c(), (String)("thread_id = " + var1_6));
                        var3_1 = new Intent("android.intent.action.SENDTO", Uri.fromParts((String)"smsto", (String)this.a, (String)null));
                        var3_1.putExtra("thread_id", var1_6);
                        var3_1.putExtra("conversation_last_imsi", zv.c(this.b));
                        abu.c(this.c).startActivity((Intent)var3_1);
                        return;
                        break;
                    } while (true);
                    break;
                } while (true);
            }
            try {
                Log.d((String)abu.c(), (String)"getThreadId returned no rows!");
                var1_6 = -1;
                ** continue;
            }
            catch (Throwable var4_5) {
                try {
                    var3_1.close();
                    throw var4_5;
                }
                catch (IllegalArgumentException var3_2) {
                    var1_6 = -1;
lbl32: // 2 sources:
                    do {
                        var3_1.printStackTrace();
                        ** GOTO lbl14
                        break;
                    } while (true);
                }
            }
            catch (IllegalArgumentException var3_3) {
                ** continue;
            }
        }
        var1_6 = -1;
        ** while (true)
    }
}

