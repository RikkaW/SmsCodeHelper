package com.android.mms.view;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

class MmsRichContainer$c
  implements View.OnClickListener
{
  private int b;
  
  public MmsRichContainer$c(MmsRichContainer paramMmsRichContainer, int paramInt)
  {
    b = paramInt;
  }
  
  public void onClick(View paramView)
  {
    if (paramView.isSelected())
    {
      a.a(false, 0);
      return;
    }
    Message.obtain(MmsRichContainer.b(a), b).sendToTarget();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsRichContainer.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */