package com.android.mms.ui;

import android.util.Log;
import com.android.mms.transaction.MmsSystemEventReceiver.SimStateChangedListener;

class MessagingPreferenceActivity$3
  implements MmsSystemEventReceiver.SimStateChangedListener
{
  MessagingPreferenceActivity$3(MessagingPreferenceActivity paramMessagingPreferenceActivity) {}
  
  public void onSimStateChanged(String paramString)
  {
    Log.d("MessagingPreferenceActivity", "update sim info change");
    MessagingPreferenceActivity.access$600(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingPreferenceActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */