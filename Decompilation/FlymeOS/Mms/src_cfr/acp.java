/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.MediaRecorder
 *  android.media.MediaRecorder$OnErrorListener
 *  java.lang.Object
 */
import android.media.MediaRecorder;

class acp
implements MediaRecorder.OnErrorListener {
    final /* synthetic */ aco a;

    acp(aco aco2) {
        this.a = aco2;
    }

    public void onError(MediaRecorder mediaRecorder, int n2, int n3) {
        this.a.a(2131493555);
    }
}

