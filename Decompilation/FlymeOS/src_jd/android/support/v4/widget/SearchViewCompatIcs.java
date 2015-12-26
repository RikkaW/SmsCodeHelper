package android.support.v4.widget;

import android.content.Context;
import android.view.View;
import android.widget.SearchView;

class SearchViewCompatIcs
{
  public static View newSearchView(Context paramContext)
  {
    return new MySearchView(paramContext);
  }
  
  public static void setImeOptions(View paramView, int paramInt)
  {
    ((SearchView)paramView).setImeOptions(paramInt);
  }
  
  public static void setInputType(View paramView, int paramInt)
  {
    ((SearchView)paramView).setInputType(paramInt);
  }
  
  public static class MySearchView
    extends SearchView
  {
    public MySearchView(Context paramContext)
    {
      super();
    }
    
    public void onActionViewCollapsed()
    {
      setQuery("", false);
      super.onActionViewCollapsed();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SearchViewCompatIcs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */