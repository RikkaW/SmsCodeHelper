package com.android.mms.ui;

import android.os.Handler;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class ConversationBase$3
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  ConversationBase$3(ConversationBase paramConversationBase) {}
  
  public void onGlobalLayout()
  {
    if (this$0.mIsSoftInputEnabled)
    {
      Log.v("ConversationBase", "start querying msg onGlobalLayout");
      this$0.mHandler.removeCallbacks(ConversationBase.access$000(this$0));
      this$0.mHandler.post(ConversationBase.access$000(this$0));
      this$0.mMsgListView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      ConversationBase.access$102(this$0, false);
      return;
    }
    Log.w("ConversationBase", "soft keyboard is not enabled");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */