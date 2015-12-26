/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.Environment
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Random
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.ted.android.contacts.block.SpamMsgEngine;
import com.ted.android.contacts.netparser.NumManager;
import com.ted.android.contacts.updatesdk.IParsedDownload;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class apu
extends auy {
    private Context a;
    private boolean b;
    private IParsedDownload c;

    public apu(Context context, boolean bl2) {
        super(context);
        this.a = context;
        this.b = bl2;
        super.a(new bp(context.getApplicationContext()).a("update", "url"));
    }

    @Override
    public void a(be be2) {
    }

    public void a(IParsedDownload iParsedDownload) {
        this.c = iParsedDownload;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(List<be> object) {
        Iterator<be> iterator = object.iterator();
        do {
            String string2;
            block9 : {
                if (!iterator.hasNext()) {
                    return;
                }
                object = iterator.next();
                try {
                    string2 = object.f();
                    object = object.b();
                    object = object.startsWith("/") ? new File((String)object, string2).getAbsolutePath() : new File(this.a.getFilesDir(), (String)object).getAbsolutePath();
                    if ("html.zip".equals((Object)string2) || "num_segment.dat".equals((Object)string2)) {
                        anz.a((String)object, this.a.getFilesDir());
                    }
                    break block9;
                }
                catch (Exception var1_2) {
                    var1_2.printStackTrace();
                }
                continue;
            }
            if ("offline.dat".equals((Object)string2)) {
                string2 = "mounted".equals((Object)Environment.getExternalStorageState()) ? Environment.getExternalStorageDirectory() : this.a.getFilesDir();
                anz.a((String)object, new File((File)string2, "yellowpage/offline/"));
                if (this.c == null) continue;
                this.c.onParsed();
                continue;
            }
            if ("vcard.dat".equals((Object)string2)) {
                app.a(this.a).a(true, true);
                continue;
            }
            if ("config.ini".equals((Object)string2)) {
                NumManager.getInstnace().b();
                continue;
            }
            if (!string2.equals((Object)"sms.model")) continue;
            SpamMsgEngine.a();
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean a() {
        boolean bl2 = false;
        if (this.b) {
            return this.b;
        }
        long l2 = this.a.getSharedPreferences("key_update_seciton", 0).getLong("local_current_time", 0);
        if (System.currentTimeMillis() - l2 < 28800000) return bl2;
        return true;
    }

    @Override
    public boolean b() {
        long l2 = (long)((float)new Random().nextInt(60) * 60.0f * 1000.0f + (float)System.currentTimeMillis());
        SharedPreferences.Editor editor = this.a.getSharedPreferences("key_update_seciton", 0).edit();
        editor.putLong("local_current_time", l2);
        editor.commit();
        return true;
    }
}

