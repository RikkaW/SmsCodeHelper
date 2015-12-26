package com.android.mms.location;

import aau;
import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AutoCompleteTextView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import kf;
import ks;

public class CustomeAutoCompleteTextView
  extends AutoCompleteTextView
{
  private int a = 1;
  private kf b;
  private View.OnTouchListener c = new ks(this);
  
  public CustomeAutoCompleteTextView(Context paramContext)
  {
    super(paramContext);
    setThreshold(0);
  }
  
  public CustomeAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setThreshold(0);
  }
  
  public CustomeAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setThreshold(0);
  }
  
  private PopupWindow getPopupWindow()
  {
    return (PopupWindow)aau.b(ListPopupWindow.class, (ListPopupWindow)aau.b(AutoCompleteTextView.class, this, "getPopup"), "getPopupWindow");
  }
  
  public boolean enoughToFilter()
  {
    return getText().length() >= a;
  }
  
  public int getThreshold()
  {
    return a;
  }
  
  protected void performFiltering(CharSequence paramCharSequence, int paramInt)
  {
    if ((b != null) && (b.c())) {
      super.performFiltering(paramCharSequence, paramInt);
    }
  }
  
  public void setActivity(kf paramkf)
  {
    b = paramkf;
  }
  
  public void setThreshold(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    a = i;
  }
  
  public void showDropDown()
  {
    super.showDropDown();
    try
    {
      ((View)getPopupWindow().getContentView().getParent()).setOnTouchListener(c);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      Log.e("location/CustomeAutoCompleteTextView", "showDropDown()--> null contentView");
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.location.CustomeAutoCompleteTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */