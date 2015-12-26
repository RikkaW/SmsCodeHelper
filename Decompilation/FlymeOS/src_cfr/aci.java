/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
import android.util.Log;
import com.android.mms.MmsApp;

class aci
implements MmsApp.e {
    final /* synthetic */ ach a;

    aci(ach ach2) {
        this.a = ach2;
    }

    @Override
    public int a() {
        Log.d((String)"VoicePlayer", (String)("ProximityCallback:mCurrentAudioMode = " + ach.a(this.a)));
        return ach.a(this.a);
    }

    @Override
    public boolean b() {
        return ach.b(this.a);
    }

    @Override
    public void c() {
        Log.d((String)"VoicePlayer", (String)"ProximityCallback:Set to the pthoneCall model");
        ach.a(this.a, true);
    }

    @Override
    public void d() {
        Log.d((String)"VoicePlayer", (String)"ProximityCallback:Set to the speaker model");
        ach.a(this.a, false);
    }
}

