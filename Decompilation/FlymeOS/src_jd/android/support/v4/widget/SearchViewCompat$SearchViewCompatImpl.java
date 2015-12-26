package android.support.v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.view.View;

abstract interface SearchViewCompat$SearchViewCompatImpl
{
  public abstract CharSequence getQuery(View paramView);
  
  public abstract boolean isIconified(View paramView);
  
  public abstract boolean isQueryRefinementEnabled(View paramView);
  
  public abstract boolean isSubmitButtonEnabled(View paramView);
  
  public abstract Object newOnCloseListener(SearchViewCompat.OnCloseListenerCompat paramOnCloseListenerCompat);
  
  public abstract Object newOnQueryTextListener(SearchViewCompat.OnQueryTextListenerCompat paramOnQueryTextListenerCompat);
  
  public abstract View newSearchView(Context paramContext);
  
  public abstract void setIconified(View paramView, boolean paramBoolean);
  
  public abstract void setImeOptions(View paramView, int paramInt);
  
  public abstract void setInputType(View paramView, int paramInt);
  
  public abstract void setMaxWidth(View paramView, int paramInt);
  
  public abstract void setOnCloseListener(Object paramObject1, Object paramObject2);
  
  public abstract void setOnQueryTextListener(Object paramObject1, Object paramObject2);
  
  public abstract void setQuery(View paramView, CharSequence paramCharSequence, boolean paramBoolean);
  
  public abstract void setQueryHint(View paramView, CharSequence paramCharSequence);
  
  public abstract void setQueryRefinementEnabled(View paramView, boolean paramBoolean);
  
  public abstract void setSearchableInfo(View paramView, ComponentName paramComponentName);
  
  public abstract void setSubmitButtonEnabled(View paramView, boolean paramBoolean);
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */