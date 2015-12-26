package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;

class ConversationBase$11
  implements Runnable
{
  ConversationBase$11(ConversationBase paramConversationBase, StringBuilder paramStringBuilder1, ContentValues paramContentValues, StringBuilder paramStringBuilder2) {}
  
  public void run()
  {
    if (val$smsSelectionBuilder.length() > 0) {
      this$0.getContentResolver().update(Telephony.Sms.CONTENT_URI, val$values, val$smsSelectionBuilder.toString(), null);
    }
    if (val$mmsSelectionBuilder.length() > 0) {
      this$0.getContentResolver().update(Telephony.Mms.CONTENT_URI, val$values, val$mmsSelectionBuilder.toString(), null);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */