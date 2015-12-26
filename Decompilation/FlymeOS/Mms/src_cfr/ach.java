/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.media.MediaPlayer$OnErrorListener
 *  android.net.Uri
 *  android.os.PowerManager
 *  android.os.PowerManager$WakeLock
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import java.io.IOException;
import java.util.HashMap;

public class ach {
    private static final String c = MmsApp.c().getPackageName();
    private static ach d = null;
    private static final HashMap<Uri, a> o = new HashMap();
    AudioManager a = null;
    AudioManager.OnAudioFocusChangeListener b;
    private MediaPlayer e;
    private b f;
    private boolean g;
    private int h;
    private aaq i;
    private final MmsApp.f j;
    private final PowerManager.WakeLock k;
    private PowerManager.WakeLock l;
    private int m;
    private final MmsApp.e n;

    /*
     * Enabled aggressive block sorting
     */
    private ach() {
        this.f = new b(null);
        this.h = 0;
        this.m = 0;
        this.n = new aci(this);
        this.b = new acl(this);
        this.a = (AudioManager)MmsApp.c().getSystemService("audio");
        this.j = MmsApp.c().q();
        this.j.a(this.n);
        this.i = new aaq((Context)MmsApp.c(), this.j);
        Log.d((String)"VoicePlayer", (String)("VoicePlayer()-->mCurrentAudioMode = " + this.h));
        PowerManager powerManager = (PowerManager)MmsApp.c().getSystemService("power");
        this.k = powerManager.isWakeLockLevelSupported(32) ? powerManager.newWakeLock(32, c) : null;
        this.l = powerManager.newWakeLock(536870913, c);
    }

    static /* synthetic */ int a(ach ach2) {
        return ach2.h;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ach a() {
        if (d == null) {
            synchronized (ach.class) {
                if (d == null) {
                    d = new ach();
                }
            }
        }
        return d;
    }

    static /* synthetic */ void a(ach ach2, boolean bl2) {
        ach2.c(bl2);
    }

    private final boolean a(int n2) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (this.e != null) {
            bl3 = bl2;
            if (this.e.isPlaying()) {
                this.e.seekTo(Math.max((int)0, (int)(this.m - n2)));
                bl3 = true;
            }
        }
        return bl3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(Context context) {
        Log.d((String)"VoicePlayer", (String)"doPlay()~~~~");
        this.e = MediaPlayer.create((Context)context, (Uri)wd.m(this.f.b));
        if (this.e != null) {
            this.a(context);
            this.e.setAudioStreamType(3);
            this.e.setOnCompletionListener((MediaPlayer.OnCompletionListener)new acj(this));
            this.e.setOnErrorListener((MediaPlayer.OnErrorListener)new ack(this));
            int n2 = this.a.requestAudioFocus(this.b, 3, 2);
            if (n2 == 1) {
                this.g = true;
                Log.d((String)"VoicePlayer", (String)"doPlay():mAudioPlayer.start()~~~~");
                this.e.start();
                this.b(false);
            } else if (n2 == 0) {
                Log.d((String)"VoicePlayer", (String)"request Audio focus fail!");
            }
            this.e.getCurrentPosition();
        }
        if (this.l != null) {
            this.l.acquire();
            Log.i((String)"VoicePlayer", (String)("acquire partial wake lock: mPartialWakeLock = " + (Object)this.l));
        }
        if (this.k != null && !this.k.isHeld()) {
            this.k.acquire();
            Log.i((String)"VoicePlayer", (String)("acquire Proximity wake lock: mProximityWakeLock = " + (Object)this.k));
        }
    }

    static /* synthetic */ boolean b(ach ach2) {
        return ach2.g;
    }

    static /* synthetic */ void c(ach ach2) {
        ach2.h();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void c(boolean bl2) {
        this.i();
        int n2 = bl2 ? 0 : 3;
        try {
            if (this.e != null) {
                Log.d((String)"VoicePlayer", (String)("filePath = " + this.f.b));
                this.e.setDataSource(this.f.b);
                this.e.setAudioStreamType(n2);
                this.e.setOnCompletionListener((MediaPlayer.OnCompletionListener)new acm(this));
                this.e.setOnErrorListener((MediaPlayer.OnErrorListener)new acn(this));
                this.e.prepare();
                this.e.start();
                this.a(1000);
            }
            return;
        }
        catch (IOException var3_3) {
            var3_3.printStackTrace();
            return;
        }
    }

    static /* synthetic */ void d(ach ach2) {
        ach2.g();
    }

    static /* synthetic */ MediaPlayer e(ach ach2) {
        return ach2.e;
    }

    static /* synthetic */ b f(ach ach2) {
        return ach2.f;
    }

    static /* synthetic */ HashMap f() {
        return o;
    }

    private void g() {
        Log.e((String)"VoicePlayer", (String)"Error occurred while playing audio.");
        this.h();
    }

    private final void h() {
        try {
            this.a(false);
            if (this.e != null) {
                this.e.stop();
                this.e.release();
            }
            if (this.k != null && this.k.isHeld()) {
                this.k.release();
                Log.i((String)"VoicePlayer", (String)"release Proximity wake lock");
            }
            if (this.l != null && this.l.isHeld()) {
                this.l.release();
                Log.i((String)"VoicePlayer", (String)"release Partial wake lock");
            }
            return;
        }
        finally {
            this.b(true);
            this.h = 0;
            this.e = null;
            this.f.a();
            this.g = false;
            this.a.abandonAudioFocus(this.b);
        }
    }

    private void i() {
        if (this.e == null) {
            this.e = new MediaPlayer();
            return;
        }
        this.m = this.e.getCurrentPosition();
        this.e.setOnCompletionListener(null);
        this.e.stop();
        this.e.reset();
        this.e = null;
        this.e = new MediaPlayer();
    }

    public void a(Context context) {
        this.i.a();
    }

    public final void a(Context context, String string2, Uri uri) {
        if (this.g) {
            if (this.f == null) {
                this.g();
                return;
            }
            if (this.f.a(string2, uri)) {
                this.h();
                return;
            }
            this.h();
            this.f.b(string2, uri);
            this.b(context);
            return;
        }
        this.f.b(string2, uri);
        this.b(context);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Uri uri) {
        Log.d((String)"VoicePlayer", (String)"removePlayerCallback()~~~~");
        HashMap<Uri, a> hashMap = o;
        synchronized (hashMap) {
            o.remove((Object)uri);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Uri uri, a a2) {
        Log.d((String)"VoicePlayer", (String)"addPlayerCallback()~~~~");
        HashMap<Uri, a> hashMap = o;
        synchronized (hashMap) {
            o.put((Object)uri, (Object)a2);
            return;
        }
    }

    public void a(boolean bl2) {
        this.i.a(bl2);
        if (this.j != null) {
            this.j.c();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean a(String string2, Uri uri) {
        if (this.f == null || !this.g || !this.f.a(string2, uri)) {
            return false;
        }
        return true;
    }

    public final void b() {
        Log.i((String)"VoicePlayer", (String)("flushPlayer():MmsApp.getIsScreenOffByPhoneCell() = " + MmsApp.r()));
        if (MmsApp.r()) {
            return;
        }
        this.h();
        this.c();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void b(boolean bl2) {
        if (!o.containsKey((Object)this.f.c)) return;
        if (!bl2) {
            ((a)o.get((Object)this.f.c)).a(this.f.c);
            return;
        }
        ((a)o.get((Object)this.f.c)).c(this.f.c);
        this.a(this.f.c);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void c() {
        Log.d((String)"VoicePlayer", (String)"removeAllCallback()~~~~");
        HashMap<Uri, a> hashMap = o;
        synchronized (hashMap) {
            o.clear();
            return;
        }
    }

    public int d() {
        if (this.e != null) {
            return this.e.getCurrentPosition();
        }
        return 0;
    }

    public int e() {
        if (this.e != null) {
            return this.e.getDuration();
        }
        return 0;
    }

    public static interface a {
        public void a(Uri var1);

        public void b(Uri var1);

        public void c(Uri var1);
    }

    final class b {
        private String b;
        private Uri c;

        private b() {
            this.b = null;
            this.c = null;
        }

        /* synthetic */ b(aci aci2) {
            this();
        }

        private void b(String string2, Uri uri) {
            this.b = string2;
            this.c = uri;
        }

        public void a() {
            this.b = null;
            this.c = null;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean a(String string2, Uri uri) {
            if (TextUtils.isEmpty((CharSequence)string2) || uri == null || TextUtils.isEmpty((CharSequence)uri.toString()) || !string2.equals((Object)this.b) || !uri.equals((Object)this.c)) {
                return false;
            }
            return true;
        }
    }

}

