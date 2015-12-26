package com.android.mms.ui;

import android.util.Log;
import com.android.mms.transaction.MmsSystemEventReceiver.SimStateChangedListener;

class MessagingAdvancedPreferenceActivity$2
  implements MmsSystemEventReceiver.SimStateChangedListener
{
  MessagingAdvancedPreferenceActivity$2(MessagingAdvancedPreferenceActivity paramMessagingAdvancedPreferenceActivity) {}
  
  public void onSimStateChanged(String paramString)
  {
    Log.d("MessagingAdvancedPreferenceActivity", "update sim info change");
    MessagingAdvancedPreferenceActivity.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingAdvancedPreferenceActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */