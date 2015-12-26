package com.android.mms.ui;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.android.mms.data.Contact;

class NewMessageActivity$7
  implements AbsListView.OnScrollListener
{
  NewMessageActivity$7(NewMessageActivity paramNewMessageActivity) {}
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (paramInt == 2)
    {
      Contact.pauseCaching();
      return;
    }
    Contact.resumeCaching();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */