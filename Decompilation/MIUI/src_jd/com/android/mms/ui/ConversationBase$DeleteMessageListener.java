package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.provider.Telephony.Sms;
import android.view.ActionMode;
import com.android.mms.audio.Mx2DeleteHelper;
import com.android.mms.data.Conversation;

class ConversationBase$DeleteMessageListener
  implements DialogInterface.OnClickListener
{
  private final ActionMode mActionMode;
  private final boolean mDeleteLocked;
  private final Uri mDeleteUri;
  private final boolean mIsSms;
  private final long mTimeStamp;
  
  public ConversationBase$DeleteMessageListener(ConversationBase paramConversationBase, Uri paramUri, boolean paramBoolean1, long paramLong, boolean paramBoolean2, ActionMode paramActionMode)
  {
    mDeleteUri = paramUri;
    mDeleteLocked = paramBoolean1;
    mTimeStamp = paramLong;
    mIsSms = paramBoolean2;
    mActionMode = paramActionMode;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this$0.isGroup())
    {
      if (mIsSms)
      {
        str = String.format("%s=%s AND %s=%s", new Object[] { "thread_id", Long.valueOf(this$0.mConversation.getThreadId()), "date", Long.valueOf(mTimeStamp) });
        this$0.mBackgroundQueryHandler.startDelete(9700, null, Telephony.Sms.CONTENT_URI, str, null);
      }
      for (;;)
      {
        paramDialogInterface.dismiss();
        mActionMode.finish();
        return;
        this$0.mBackgroundQueryHandler.startDelete(9700, null, mDeleteUri, null, null);
      }
    }
    Uri localUri = mDeleteUri;
    if (mDeleteLocked) {}
    for (String str = null;; str = "locked=0")
    {
      Mx2DeleteHelper.deleteMms(9700, null, localUri, str, null, ConversationBase.access$400(this$0), this$0.mMsgListAdapter.getAudioItemCache());
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.DeleteMessageListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */