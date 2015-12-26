/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.speech.tts.UtteranceProgressListener
 *  java.lang.String
 */
package android.support.v4.speech.tts;

import android.speech.tts.UtteranceProgressListener;
import android.support.v4.speech.tts.TextToSpeechICSMR1;

final class TextToSpeechICSMR1$1
extends UtteranceProgressListener {
    final /* synthetic */ TextToSpeechICSMR1.UtteranceProgressListenerICSMR1 val$listener;

    TextToSpeechICSMR1$1(TextToSpeechICSMR1.UtteranceProgressListenerICSMR1 utteranceProgressListenerICSMR1) {
        this.val$listener = utteranceProgressListenerICSMR1;
    }

    public void onDone(String string2) {
        this.val$listener.onDone(string2);
    }

    public void onError(String string2) {
        this.val$listener.onError(string2);
    }

    public void onStart(String string2) {
        this.val$listener.onStart(string2);
    }
}

