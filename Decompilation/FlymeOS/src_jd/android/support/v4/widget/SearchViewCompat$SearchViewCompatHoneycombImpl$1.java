package android.support.v4.widget;

class SearchViewCompat$SearchViewCompatHoneycombImpl$1
  implements SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge
{
  SearchViewCompat$SearchViewCompatHoneycombImpl$1(SearchViewCompat.SearchViewCompatHoneycombImpl paramSearchViewCompatHoneycombImpl, SearchViewCompat.OnQueryTextListenerCompat paramOnQueryTextListenerCompat) {}
  
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
 * Qualified Name:     android.support.v4.widget.SearchViewCompat.SearchViewCompatHoneycombImpl.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */