package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.appcompat.R.attr;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.inputmethod.InputMethodManager;

public class SearchView$SearchAutoComplete
  extends AppCompatAutoCompleteTextView
{
  private SearchView mSearchView;
  private int mThreshold = getThreshold();
  
  public SearchView$SearchAutoComplete(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SearchView$SearchAutoComplete(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.autoCompleteTextViewStyle);
  }
  
  public SearchView$SearchAutoComplete(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private boolean isEmpty()
  {
    return TextUtils.getTrimmedLength(getText()) == 0;
  }
  
  public boolean enoughToFilter()
  {
    return (mThreshold <= 0) || (super.enoughToFilter());
  }
  
  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    super.onFocusChanged(paramBoolean, paramInt, paramRect);
    mSearchView.onTextFocusChanged();
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      KeyEvent.DispatcherState localDispatcherState;
      if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getRepeatCount() == 0))
      {
        localDispatcherState = getKeyDispatcherState();
        if (localDispatcherState != null) {
          localDispatcherState.startTracking(paramKeyEvent, this);
        }
        return true;
      }
      if (paramKeyEvent.getAction() == 1)
      {
        localDispatcherState = getKeyDispatcherState();
        if (localDispatcherState != null) {
          localDispatcherState.handleUpEvent(paramKeyEvent);
        }
        if ((paramKeyEvent.isTracking()) && (!paramKeyEvent.isCanceled()))
        {
          mSearchView.clearFocus();
          SearchView.access$2100(mSearchView, false);
          return true;
        }
      }
    }
    return super.onKeyPreIme(paramInt, paramKeyEvent);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((paramBoolean) && (mSearchView.hasFocus()) && (getVisibility() == 0))
    {
      ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this, 0);
      if (SearchView.isLandscapeMode(getContext())) {
        SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true);
      }
    }
  }
  
  public void performCompletion() {}
  
  protected void replaceText(CharSequence paramCharSequence) {}
  
  void setSearchView(SearchView paramSearchView)
  {
    mSearchView = paramSearchView;
  }
  
  public void setThreshold(int paramInt)
  {
    super.setThreshold(paramInt);
    mThreshold = paramInt;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.SearchView.SearchAutoComplete
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */