/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.speech.tts.TextToSpeech
 *  android.speech.tts.TextToSpeech$OnUtteranceCompletedListener
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.speech.tts;

import android.speech.tts.TextToSpeech;
import android.support.v4.speech.tts.TextToSpeechICSMR1;

final class TextToSpeechICSMR1$2
implements TextToSpeech.OnUtteranceCompletedListener {
    final /* synthetic */ TextToSpeechICSMR1.UtteranceProgressListenerICSMR1 val$listener;

    TextToSpeechICSMR1$2(TextToSpeechICSMR1.UtteranceProgressListenerICSMR1 utteranceProgressListenerICSMR1) {
        this.val$listener = utteranceProgressListenerICSMR1;
    }

    public void onUtteranceCompleted(String string2) {
        this.val$listener.onStart(string2);
        this.val$listener.onDone(string2);
    }
}

