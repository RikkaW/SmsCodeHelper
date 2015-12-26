package com.meizu.common.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.lang.ref.WeakReference;

public class SearchEditText
  extends EditText
{
  private static final int HIDE = 2;
  private static final int SHOW = 1;
  private SearchHandler mHandler;
  
  public SearchEditText(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public SearchEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public SearchEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  public void init()
  {
    mHandler = new SearchHandler(new WeakReference(this));
  }
  
  public boolean isInputComplete()
  {
    return BaseInputConnection.getComposingSpanStart(getEditableText()) == -1;
  }
  
  public void showIme(boolean paramBoolean)
  {
    int i = 1;
    mHandler.removeMessages(1);
    mHandler.removeMessages(2);
    SearchHandler localSearchHandler = mHandler;
    if (paramBoolean) {}
    for (;;)
    {
      localSearchHandler.sendEmptyMessageDelayed(i, 20L);
      return;
      i = 2;
    }
  }
  
  static class SearchHandler
    extends Handler
  {
    WeakReference<SearchEditText> mWeakReference = null;
    
    public SearchHandler(WeakReference<SearchEditText> paramWeakReference)
    {
      mWeakReference = paramWeakReference;
    }
    
    public void handleMessage(Message paramMessage)
    {
      SearchEditText localSearchEditText = (SearchEditText)mWeakReference.get();
      switch (what)
      {
      default: 
        return;
      case 1: 
        localSearchEditText.requestFocus();
        paramMessage = (InputMethodManager)localSearchEditText.getContext().getSystemService("input_method");
        paramMessage.restartInput(localSearchEditText);
        paramMessage.showSoftInput(localSearchEditText, 1);
        return;
      }
      localSearchEditText.clearFocus();
      ((InputMethodManager)localSearchEditText.getContext().getSystemService("input_method")).hideSoftInputFromWindow(localSearchEditText.getWindowToken(), 0);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SearchEditText
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */