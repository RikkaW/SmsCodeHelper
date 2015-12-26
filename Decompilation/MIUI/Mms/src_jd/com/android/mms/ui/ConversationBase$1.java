package com.android.mms.ui;

import com.android.mms.audio.DeleteCallback;
import miui.app.ProgressDialog;

class ConversationBase$1
  implements DeleteCallback
{
  ConversationBase$1(ConversationBase paramConversationBase) {}
  
  public void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    paramObject = this$0;
    mBatchDeleteTaskCount -= 1;
    if ((this$0.mBatchDeleteTaskCount <= 0) && (ConversationBase.mBatchDeleteProgressDialog != null))
    {
      ConversationBase.mBatchDeleteProgressDialog.dismiss();
      ConversationBase.mBatchDeleteProgressDialog = null;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */