package android.support.v4.speech.tts;

import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;

final class TextToSpeechICSMR1$2
  implements TextToSpeech.OnUtteranceCompletedListener
{
  TextToSpeechICSMR1$2(TextToSpeechICSMR1.UtteranceProgressListenerICSMR1 paramUtteranceProgressListenerICSMR1) {}
  
  public void onUtteranceCompleted(String paramString)
  {
    val$listener.onStart(paramString);
    val$listener.onDone(paramString);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.speech.tts.TextToSpeechICSMR1.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */