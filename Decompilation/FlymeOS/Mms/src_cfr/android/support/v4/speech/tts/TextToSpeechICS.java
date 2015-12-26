/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.speech.tts.TextToSpeech
 *  android.speech.tts.TextToSpeech$OnInitListener
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.speech.tts;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

class TextToSpeechICS {
    private static final String TAG = "android.support.v4.speech.tts";

    TextToSpeechICS() {
    }

    static TextToSpeech construct(Context context, TextToSpeech.OnInitListener onInitListener, String string2) {
        if (Build.VERSION.SDK_INT < 14) {
            if (string2 == null) {
                return new TextToSpeech(context, onInitListener);
            }
            Log.w((String)"android.support.v4.speech.tts", (String)"Can't specify tts engine on this device");
            return new TextToSpeech(context, onInitListener);
        }
        return new TextToSpeech(context, onInitListener, string2);
    }
}

