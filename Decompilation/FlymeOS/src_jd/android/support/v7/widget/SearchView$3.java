package android.support.v7.widget;

import android.support.v4.widget.CursorAdapter;

class SearchView$3
  implements Runnable
{
  SearchView$3(SearchView paramSearchView) {}
  
  public void run()
  {
    if ((SearchView.access$100(this$0) != null) && ((SearchView.access$100(this$0) instanceof SuggestionsAdapter))) {
      SearchView.access$100(this$0).changeCursor(null);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.SearchView.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */