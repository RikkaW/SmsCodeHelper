/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.media.MediaRecorder
 *  android.media.MediaRecorder$OnErrorListener
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Environment
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.StatFs
 *  android.util.Log
 *  java.io.File
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.reflect.Method
 */
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StatFs;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.view.EditTextEx;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class aco
implements EditTextEx.a {
    private static aco e = null;
    private static int q = 16000;
    private int A;
    private int B;
    public Context a;
    public EditTextEx b;
    AudioManager c = null;
    AudioManager.OnAudioFocusChangeListener d;
    private boolean f = true;
    private MediaRecorder g;
    private String h = "";
    private boolean i = false;
    private long j = 0;
    private int k = 0;
    private a l;
    private Object m = new Object();
    private int n;
    private boolean o = false;
    private MediaRecorder.OnErrorListener p;
    private b r;
    private boolean s;
    private final Handler t;
    private Runnable u;
    private Runnable v;
    private boolean w;
    private long x;
    private int y;
    private int z;

    private aco() {
        this.p = new acp(this);
        this.t = new Handler();
        this.u = new acq(this);
        this.v = new acr(this);
        this.w = false;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.d = new acs(this);
        this.a("/sdcard/Download/FMessage/");
        this.r = new b();
        this.s = false;
        this.f = wd.f();
        this.n = 0;
        this.c = (AudioManager)MmsApp.c().getSystemService("audio");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aco a() {
        if (e == null) {
            synchronized (aco.class) {
                if (e == null) {
                    e = new aco();
                }
            }
        }
        return e;
    }

    static /* synthetic */ MediaRecorder a(aco aco2) {
        return aco2.g;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(long l2, long l3, int n2) {
        if (this.b != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(aco.d("KEY_CURRENT_RADIUX"), n2 / 4 * 3);
            bundle.putLong(aco.d("KEY_CURRENT_RECORD_TIME"), l2 / 1000);
            if (this.n != 2) {
                bundle.putInt(aco.d("KEY_CURRENT_COLOR"), aco.e("TIME_COLOR"));
            } else if (this.o) {
                this.o = false;
                bundle.putInt(aco.d("KEY_CURRENT_COLOR"), 0);
            } else {
                this.o = true;
                bundle.putInt(aco.d("KEY_CURRENT_COLOR"), aco.e("TIME_COLOR"));
            }
            this.b.a(aco.d("ACTION_AMP_REFRESH"), bundle);
        }
        this.j = l2;
    }

    static /* synthetic */ void a(aco aco2, String string2, MediaRecorder mediaRecorder) {
        aco2.a(string2, mediaRecorder);
    }

    private void a(String string2) {
        if ((string2 = new File(string2)).exists()) {
            return;
        }
        string2.mkdirs();
    }

    private void a(String string2, MediaRecorder mediaRecorder) {
        try {
            string2 = mediaRecorder.getClass().getDeclaredMethod(string2, new Class[0]);
            string2.setAccessible(true);
            string2.invoke((Object)mediaRecorder, new Object[0]);
            return;
        }
        catch (NoSuchMethodException var1_2) {
            Log.i((String)"VoiceRecorder", (String)("invokeMethod e1 = " + (Object)((Object)var1_2)));
            return;
        }
        catch (IllegalAccessException var1_3) {
            Log.i((String)"VoiceRecorder", (String)("invokeMethod e2 = " + (Object)((Object)var1_3)));
            return;
        }
        catch (InvocationTargetException var1_4) {
            Log.i((String)"VoiceRecorder", (String)("invokeMethod e3 = " + (Object)((Object)var1_4)));
            return;
        }
    }

    private void b(int n2) {
        if (n2 <= 0) {
            return;
        }
        wd.a(n2, this.a, 0, 1, true, 0);
    }

    private void b(String string2) {
        if ((string2 = new File(string2)).exists() && string2.isFile()) {
            string2.delete();
        }
    }

    static /* synthetic */ boolean b(aco aco2) {
        return aco2.s;
    }

    static /* synthetic */ boolean c(aco aco2) {
        return aco2.i;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private boolean c(String string2) {
        block9 : {
            this.r.a();
            this.r.a(16384);
            this.r.a(new File(string2), 102400);
            try {
                Object object = this.m;
                // MONITORENTER : object
                if (this.g != null) break block9;
                this.l();
            }
            catch (Exception var1_2) {
                Object object = this.m;
                // MONITORENTER : object
                this.g.reset();
                this.g.release();
                this.g = null;
                // MONITOREXIT : object
                this.b(2131493555);
                return false;
            }
        }
        this.g.setOutputFile(string2);
        this.g.prepare();
        this.g.start();
        Log.v((String)"VoiceRecorder", (String)"mRecorder is start~~~~~");
        this.n = 1;
        // MONITOREXIT : object
        this.t.removeCallbacks(this.u);
        this.r.a(60000, 200);
        this.s = true;
        this.t.post(this.u);
        return true;
    }

    private static String d(String string2) {
        return (String)aau.a("com.meizu.widget.inputmethod.VoiceView", string2);
    }

    static /* synthetic */ void d(aco aco2) {
        aco2.k();
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean d() {
        this.j = 0;
        this.i = true;
        this.w = false;
        this.h = this.j() + System.currentTimeMillis() + ".amr";
        if (Log.isLoggable((String)"VoiceRecorder", (int)3)) {
            Log.d((String)"VoiceRecorder", (String)("----startRecoder()--begin record\uff1a" + this.h));
        }
        if (this.l != null) {
            this.l.a();
        }
        return this.c(this.h);
    }

    private static int e(String string2) {
        return (Integer)aau.a("com.meizu.widget.inputmethod.VoiceView", string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void e() {
        if (Log.isLoggable((String)"VoiceRecorder", (int)3)) {
            Log.d((String)"VoiceRecorder", (String)("----stopRecoder()--save files\uff1a" + this.h + ", mCurrentRecordTime = " + this.j));
        }
        if (this.j < 200 && this.j > 0) {
            this.a(2131493554);
            return;
        } else {
            this.f();
            if (this.l != null) {
                if (MmsApp.a) {
                    this.l.b(wd.m(this.h));
                } else {
                    this.l.a(wd.m(this.h));
                }
            }
            this.i = false;
            if (this.w) {
                this.b.a(aco.d("ACTION_STOP_RECORD"), (Bundle)null);
            }
            if (this.c == null || this.d == null) return;
            {
                this.c.abandonAudioFocus(this.d);
                return;
            }
        }
    }

    static /* synthetic */ void e(aco aco2) {
        aco2.e();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void f() {
        if (this.g == null) {
            return;
        }
        try {
            Object object = this.m;
            // MONITORENTER : object
        }
        catch (Exception var1_2) {
            this.g = null;
            return;
        }
        this.g.stop();
        this.g.release();
        this.g = null;
        // MONITOREXIT : object
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void g() {
        if (this.g == null) {
            return;
        }
        try {
            if (this.n != 1) return;
            this.r.c();
            this.a("pause", this.g);
            this.n = 2;
            return;
        }
        catch (Exception var1_1) {
            Log.i((String)"VoiceRecorder", (String)("VoiceRecorder pauseRecorder e = " + (Object)((Object)var1_1)));
            this.i();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void h() {
        if (this.g == null) {
            return;
        }
        try {
            if (this.n != 2) return;
            this.r.e();
            this.a("resume", this.g);
            this.n = 1;
            return;
        }
        catch (Exception var1_1) {
            Log.i((String)"VoiceRecorder", (String)("VoiceRecorder resumeRecorder e = " + (Object)((Object)var1_1)));
            this.i();
            return;
        }
    }

    private void i() {
        this.g.reset();
        this.g.release();
        this.g = null;
    }

    private final String j() {
        return "/sdcard/Download/FMessage/";
    }

    /*
     * Enabled aggressive block sorting
     */
    private void k() {
        int n2 = 10000;
        long l2 = System.currentTimeMillis();
        long l3 = this.n == 1 ? this.r.a(l2) : this.r.d();
        l2 = this.r.b(l2);
        int n3 = 0;
        if (this.g != null) {
            n3 = this.g.getMaxAmplitude();
        }
        if (n3 > 10000) {
            n3 = n2;
        }
        this.a(l3, l2, n3 / 50);
        Log.d((String)"VoiceView", (String)("amplitude = " + n3));
        if (l2 <= 0) {
            if (l3 != 60000) {
                this.f();
                return;
            }
            this.w = true;
            this.t.postDelayed(this.v, 0);
            return;
        }
        if (this.r.b() == 2) {
            // empty if block
        }
        if (this.g == null || !this.s || !this.i) return;
        {
            if (this.n != 2) {
                this.t.postDelayed(this.u, 90);
                return;
            }
        }
        this.t.removeCallbacks(this.u);
        this.t.postDelayed(this.u, 500);
    }

    private void l() {
        this.g = new MediaRecorder();
        this.g.setAudioSource(1);
        this.g.setAudioSamplingRate(q);
        this.g.setOutputFormat(3);
        this.g.setAudioEncoder(1);
        this.g.setOnErrorListener(this.p);
    }

    private boolean m() {
        Object object = aau.a("android.os.ServiceManager", "checkService", String.class, "phone", "com.android.internal.telephony.ITelephony$Stub", "asInterface", IBinder.class, "isIdle", null, null);
        if (object != null && object instanceof Boolean) {
            return (Boolean)object;
        }
        return false;
    }

    public void a(int n2) {
        this.f();
        this.i = false;
        if (this.l != null) {
            this.l.b();
        }
        this.b(this.h);
        if (!Log.isLoggable((String)"VoiceRecorder", (int)3)) {
            return;
        }
        Log.d((String)"VoiceRecorder", (String)("----cancelRecord()--delete files\uff1a" + this.h));
    }

    public void a(Context context, EditTextEx editTextEx, a a2) {
        this.a = context;
        this.b = editTextEx;
        this.b.setOnPrivateIMECommandListener(this);
        this.l = a2;
    }

    public void a(Uri uri) {
        if (uri == null) {
            return;
        }
        this.b(uri.getPath());
    }

    @Override
    public boolean a(String string2, Bundle bundle) {
        if (aco.d("ACTION_BEGIN_RECORD").equals((Object)string2)) {
            if (this.c.requestAudioFocus(this.d, 3, 2) != 1) {
                if (!this.m()) {
                    this.b(2131493818);
                }
                Log.d((String)"VoiceRecorder", (String)"request Audio focus fail!");
                return true;
            }
            if (this.d()) {
                this.y = bundle.getInt(aco.d("KEY_AMP_MIN_RADIUS"), 0);
                this.z = bundle.getInt(aco.d("KEY_AMP_MAX_RADIUS"), 0);
                this.x = bundle.getLong(aco.d("KEY_RECORD_TOKEN"), 0);
                this.b.a(aco.d("ACTION_BEGIN_RECORD"), bundle);
                return true;
            }
            this.a(0);
            this.b.a(aco.d("ACTION_CANCEL_RECORD"), (Bundle)null);
            return true;
        }
        if (aco.d("ACTION_STOP_RECORD").equals((Object)string2)) {
            this.e();
            this.b.a(aco.d("ACTION_STOP_RECORD"), (Bundle)null);
            return true;
        }
        if (aco.d("ACTION_CANCEL_RECORD").equals((Object)string2)) {
            this.a(0);
            this.b.a(aco.d("ACTION_CANCEL_RECORD"), (Bundle)null);
            return true;
        }
        if (aco.d("ACTION_PAUSE_RECORD").equals((Object)string2)) {
            this.g();
            return true;
        }
        if (aco.d("ACTION_RESUME_RECORD").equals((Object)string2)) {
            this.h();
            return true;
        }
        return false;
    }

    public boolean a(boolean bl2) {
        if (this.f == bl2) {
            return false;
        }
        this.f = bl2;
        return true;
    }

    public boolean b() {
        return this.i;
    }

    public void c() {
        this.l = null;
        this.b = null;
    }

    public static interface a {
        public void a();

        public void a(Uri var1);

        public void b();

        public void b(Uri var1);
    }

    public static final class b {
        private int a = 0;
        private File b;
        private long c;
        private int d = 1000;
        private long e;
        private long f;
        private long g;
        private long h;
        private long i = -1;
        private long j = -1;
        private long k = -1;
        private long l = 0;
        private long m = 0;
        private long n = 0;

        /*
         * Enabled aggressive block sorting
         */
        private long c(long l2) {
            long l3 = 0;
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            long l4 = statFs.getAvailableBlocks() - 32;
            long l5 = statFs.getBlockSize();
            if (l4 >= 0) {
                l3 = l4;
            }
            if (this.e == -1 || l3 != this.f) {
                this.e = l2;
                this.f = l3;
            }
            l3 = this.f * l5 / (long)this.d - (l2 - this.e) / 1000;
            if (this.b == null) {
                this.a = 2;
                return l3;
            }
            this.b = new File(this.b.getAbsolutePath());
            l4 = this.b.length();
            if (this.g == -1 || l4 != this.h) {
                this.g = l2;
                this.h = l4;
            }
            int n2 = l3 < (l2 = (this.c - l4) / (long)this.d - (l2 - this.g) / 1000 - 1) ? 2 : 1;
            this.a = n2;
            return Math.min((long)l3, (long)l2);
        }

        public long a(long l2) {
            this.n = l2 = l2 - this.i - this.m;
            return l2;
        }

        public void a() {
            this.a = 0;
            this.e = -1;
            this.g = -1;
            this.i = -1;
            this.k = -1;
            this.j = -1;
            this.l = 0;
            this.m = 0;
            this.n = 0;
        }

        public void a(int n2) {
            this.d = n2 / 8;
        }

        public void a(int n2, int n3) {
            this.i = System.currentTimeMillis();
            this.k = n2;
            this.j = n3;
            this.l = 0;
            this.m = 0;
            this.n = 0;
        }

        public void a(File file, long l2) {
            this.b = file;
            this.c = l2;
        }

        public int b() {
            return this.a;
        }

        /*
         * Enabled aggressive block sorting
         */
        public long b(long l2) {
            l2 = 1000 * this.c(l2);
            if (this.k == -1) {
                return l2;
            }
            long l3 = this.k - this.n;
            int n2 = l2 < l3 ? this.a : 3;
            this.a = n2;
            return Math.min((long)l2, (long)l3);
        }

        public void c() {
            this.l = System.currentTimeMillis();
            this.n = this.a(this.l);
        }

        public long d() {
            return this.n;
        }

        public void e() {
            this.m = this.m + System.currentTimeMillis() - this.l;
        }
    }

}

