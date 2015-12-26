package com.android.mms.ui;

class SearchFragment$2
  implements Runnable
{
  SearchFragment$2(SearchFragment paramSearchFragment) {}
  
  public void run()
  {
    if (!SearchFragment.access$000(this$0).hasRunningTask()) {
      SearchFragment.access$000(this$0).query();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchFragment.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */