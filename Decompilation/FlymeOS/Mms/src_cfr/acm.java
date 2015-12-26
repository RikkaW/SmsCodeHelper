/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
import android.media.MediaPlayer;
import android.util.Log;

class acm
implements MediaPlayer.OnCompletionListener {
    final /* synthetic */ ach a;

    acm(ach ach2) {
        this.a = ach2;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d((String)"VoicePlayer", (String)"setTreamType():stop()~~~~");
        ach.c(this.a);
    }
}

