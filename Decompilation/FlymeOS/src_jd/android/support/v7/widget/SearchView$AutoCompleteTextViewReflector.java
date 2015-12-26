package android.support.v7.widget;

import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import java.lang.reflect.Method;

class SearchView$AutoCompleteTextViewReflector
{
  private Method doAfterTextChanged;
  private Method doBeforeTextChanged;
  private Method ensureImeVisible;
  private Method showSoftInputUnchecked;
  
  SearchView$AutoCompleteTextViewReflector()
  {
    try
    {
      doBeforeTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
      doBeforeTextChanged.setAccessible(true);
      try
      {
        doAfterTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
        doAfterTextChanged.setAccessible(true);
        try
        {
          ensureImeVisible = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[] { Boolean.TYPE });
          ensureImeVisible.setAccessible(true);
          try
          {
            showSoftInputUnchecked = InputMethodManager.class.getMethod("showSoftInputUnchecked", new Class[] { Integer.TYPE, ResultReceiver.class });
            showSoftInputUnchecked.setAccessible(true);
            return;
          }
          catch (NoSuchMethodException localNoSuchMethodException1) {}
        }
        catch (NoSuchMethodException localNoSuchMethodException2)
        {
          for (;;) {}
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException3)
      {
        for (;;) {}
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException4)
    {
      for (;;) {}
    }
  }
  
  void doAfterTextChanged(AutoCompleteTextView paramAutoCompleteTextView)
  {
    if (doAfterTextChanged != null) {}
    try
    {
      doAfterTextChanged.invoke(paramAutoCompleteTextView, new Object[0]);
      return;
    }
    catch (Exception paramAutoCompleteTextView) {}
  }
  
  void doBeforeTextChanged(AutoCompleteTextView paramAutoCompleteTextView)
  {
    if (doBeforeTextChanged != null) {}
    try
    {
      doBeforeTextChanged.invoke(paramAutoCompleteTextView, new Object[0]);
      return;
    }
    catch (Exception paramAutoCompleteTextView) {}
  }
  
  void ensureImeVisible(AutoCompleteTextView paramAutoCompleteTextView, boolean paramBoolean)
  {
    if (ensureImeVisible != null) {}
    try
    {
      ensureImeVisible.invoke(paramAutoCompleteTextView, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception paramAutoCompleteTextView) {}
  }
  
  void showSoftInputUnchecked(InputMethodManager paramInputMethodManager, View paramView, int paramInt)
  {
    if (showSoftInputUnchecked != null) {
      try
      {
        showSoftInputUnchecked.invoke(paramInputMethodManager, new Object[] { Integer.valueOf(paramInt), null });
        return;
      }
      catch (Exception localException) {}
    }
    paramInputMethodManager.showSoftInput(paramView, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.SearchView.AutoCompleteTextViewReflector
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */