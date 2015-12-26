/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.speech.tts.TextToSpeech
 *  android.speech.tts.TextToSpeech$OnUtteranceCompletedListener
 *  android.speech.tts.UtteranceProgressListener
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 */
package android.support.v4.speech.tts;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v4.speech.tts.TextToSpeechICSMR1$1;
import android.support.v4.speech.tts.TextToSpeechICSMR1$2;
import java.util.Locale;
import java.util.Set;

class TextToSpeechICSMR1 {
    public static final String KEY_FEATURE_EMBEDDED_SYNTHESIS = "embeddedTts";
    public static final String KEY_FEATURE_NETWORK_SYNTHESIS = "networkTts";

    TextToSpeechICSMR1() {
    }

    static Set<String> getFeatures(TextToSpeech textToSpeech, Locale locale) {
        if (Build.VERSION.SDK_INT >= 15) {
            return textToSpeech.getFeatures(locale);
        }
        return null;
    }

    static void setUtteranceProgressListener(TextToSpeech textToSpeech, UtteranceProgressListenerICSMR1 utteranceProgressListenerICSMR1) {
        if (Build.VERSION.SDK_INT >= 15) {
            textToSpeech.setOnUtteranceProgressListener((UtteranceProgressListener)new TextToSpeechICSMR1$1(utteranceProgressListenerICSMR1));
            return;
        }
        textToSpeech.setOnUtteranceCompletedListener((TextToSpeech.OnUtteranceCompletedListener)new TextToSpeechICSMR1$2(utteranceProgressListenerICSMR1));
    }

    static interface UtteranceProgressListenerICSMR1 {
        public void onDone(String var1);

        public void onError(String var1);

        public void onStart(String var1);
    }

}

