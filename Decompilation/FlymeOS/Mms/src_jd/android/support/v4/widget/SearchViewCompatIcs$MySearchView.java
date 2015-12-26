package android.support.v4.widget;

import android.content.Context;
import android.widget.SearchView;

public class SearchViewCompatIcs$MySearchView
  extends SearchView
{
  public SearchViewCompatIcs$MySearchView(Context paramContext)
  {
    super(paramContext);
  }
  
  public void onActionViewCollapsed()
  {
    setQuery("", false);
    super.onActionViewCollapsed();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SearchViewCompatIcs.MySearchView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */