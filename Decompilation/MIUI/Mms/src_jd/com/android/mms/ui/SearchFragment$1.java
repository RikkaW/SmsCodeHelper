package com.android.mms.ui;

import android.os.Handler;
import com.android.mms.data.Contact;
import com.android.mms.data.Contact.UpdateListener;

class SearchFragment$1
  implements Contact.UpdateListener
{
  SearchFragment$1(SearchFragment paramSearchFragment) {}
  
  public void onUpdate(Contact paramContact)
  {
    this$0.mHandler.removeCallbacks(this$0.mRequestRequery);
    this$0.mHandler.postDelayed(this$0.mRequestRequery, 500L);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchFragment.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */