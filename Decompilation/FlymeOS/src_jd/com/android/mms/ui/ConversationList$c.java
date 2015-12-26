package com.android.mms.ui;

import aab;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import ir;

class ConversationList$c
  implements ViewPager.OnPageChangeListener
{
  private int b;
  
  private ConversationList$c(ConversationList paramConversationList) {}
  
  public void onPageScrollStateChanged(int paramInt)
  {
    b = paramInt;
  }
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (a.a != null) {
      a.a.a(paramInt1, paramFloat, b);
    }
  }
  
  public void onPageSelected(int paramInt)
  {
    if (a.a == null) {}
    do
    {
      return;
      a.a.a(paramInt);
    } while (paramInt != 1);
    aab.a(a, "notice_conversation_show", "ConversationList");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationList.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */