package com.android.mms.ui;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

class MessageEditableActivityBase$26$1$1
  implements ViewTreeObserver.OnPreDrawListener
{
  MessageEditableActivityBase$26$1$1(MessageEditableActivityBase.26.1 param1) {}
  
  public boolean onPreDraw()
  {
    this$2.this$1.this$0.onResetMessageAnimationEnd();
    this$2.this$1.this$0.mMessageContentPanel.getViewTreeObserver().removeOnPreDrawListener(this);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.26.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */