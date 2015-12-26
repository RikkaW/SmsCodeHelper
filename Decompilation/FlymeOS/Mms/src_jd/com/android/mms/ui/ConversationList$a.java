package com.android.mms.ui;

import android.util.Log;
import com.android.mms.fragmentstyle.ConversationListFragment.c;
import com.android.mms.fragmentstyle.CustomeViewPager;
import ir;
import ir.b;

class ConversationList$a
  implements ConversationListFragment.c
{
  private ConversationList$a(ConversationList paramConversationList) {}
  
  public void a()
  {
    a.a(ir.b.a, true);
    a.a(false);
    if (ConversationList.c(a) != null) {
      ConversationList.c(a).setScrollEnable(false);
    }
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if (a.a != null) {
      a.a.a(paramInt1, paramInt2);
    }
  }
  
  public void a(long paramLong, String paramString)
  {
    aab.b = true;
    a.startActivity(ComposeMessageActivity.a(a, paramLong, paramString));
    ConversationList.a(a, true);
    Log.d("ConversationList", "beginSearch(), openThread( " + paramLong + "),  isWatingSubPage = " + ConversationList.d(a));
  }
  
  public void b()
  {
    a.a(true);
    if (ConversationList.c(a) != null) {
      ConversationList.c(a).setScrollEnable(true);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationList.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */