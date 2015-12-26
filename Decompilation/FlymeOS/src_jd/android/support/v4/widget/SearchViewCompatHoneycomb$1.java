package android.support.v4.widget;

import android.widget.SearchView.OnQueryTextListener;

final class SearchViewCompatHoneycomb$1
  implements SearchView.OnQueryTextListener
{
  SearchViewCompatHoneycomb$1(SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge paramOnQueryTextListenerCompatBridge) {}
  
  public boolean onQueryTextChange(String paramString)
  {
    return val$listener.onQueryTextChange(paramString);
  }
  
  public boolean onQueryTextSubmit(String paramString)
  {
    return val$listener.onQueryTextSubmit(paramString);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SearchViewCompatHoneycomb.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */