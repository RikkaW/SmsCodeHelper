package com.meizu.commonwidget;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

class RecipientEdit$RecipientAutoCompleteTextView$c
{
  public static View a(PopupWindow paramPopupWindow)
  {
    return (View)a(paramPopupWindow, "android.widget.PopupWindow", "mPopupView");
  }
  
  public static ListPopupWindow a(AutoCompleteTextView paramAutoCompleteTextView)
  {
    return (ListPopupWindow)a(paramAutoCompleteTextView, "android.widget.AutoCompleteTextView", "mPopup");
  }
  
  public static PopupWindow a(ListPopupWindow paramListPopupWindow)
  {
    return (PopupWindow)a(paramListPopupWindow, "android.widget.ListPopupWindow", "mPopup");
  }
  
  public static Object a(Object paramObject, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Class.forName(paramString1).getDeclaredField(paramString2);
      paramString1.setAccessible(true);
      paramObject = paramString1.get(paramObject);
      return paramObject;
    }
    catch (Exception paramObject)
    {
      ((Exception)paramObject).printStackTrace();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.RecipientEdit.RecipientAutoCompleteTextView.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */