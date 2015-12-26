package com.meizu.common.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;
import java.lang.ref.WeakReference;

class SearchEditText$SearchHandler
  extends Handler
{
  WeakReference<SearchEditText> mWeakReference = null;
  
  public SearchEditText$SearchHandler(WeakReference<SearchEditText> paramWeakReference)
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

/* Location:
 * Qualified Name:     com.meizu.common.widget.SearchEditText.SearchHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */