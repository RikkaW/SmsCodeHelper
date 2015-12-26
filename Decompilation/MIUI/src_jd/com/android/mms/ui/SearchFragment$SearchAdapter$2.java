package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.data.Conversation;
import java.util.HashSet;

class SearchFragment$SearchAdapter$2
  implements View.OnClickListener
{
  SearchFragment$SearchAdapter$2(SearchFragment.SearchAdapter paramSearchAdapter, Conversation paramConversation) {}
  
  public void onClick(View paramView)
  {
    SearchFragment.SearchAdapter.access$1200(this$1).add(val$c);
    this$1.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchFragment.SearchAdapter.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */