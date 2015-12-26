package com.meizu.commonwidget;

import aih.b;
import aih.c;
import aii;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

public class RecipientEdit$RecipientAutoCompleteTextView
  extends AutoCompleteTextView
{
  private a a;
  private RecipientEdit.d b;
  private View c;
  private b d;
  private ListAdapter e;
  private AbsListView.OnScrollListener f;
  private Drawable g;
  private Drawable h;
  private Drawable i;
  
  public RecipientEdit$RecipientAutoCompleteTextView(Context paramContext)
  {
    super(paramContext);
    b();
  }
  
  public RecipientEdit$RecipientAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    b();
  }
  
  public RecipientEdit$RecipientAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    b();
  }
  
  private void b()
  {
    h = getResources().getDrawable(aih.c.mw_list_history_background_noshadow);
    i = getResources().getDrawable(aih.c.mw_list_history_background);
  }
  
  public void a()
  {
    ListPopupWindow localListPopupWindow = getPopup();
    Object localObject2 = localListPopupWindow.getAnchorView();
    Object localObject1 = localObject2;
    int j;
    if (localObject2 == null)
    {
      if (getDropDownAnchor() != -1) {
        localObject1 = getRootView().findViewById(getDropDownAnchor());
      }
    }
    else
    {
      localObject2 = c.a(localListPopupWindow);
      if (((PopupWindow)localObject2).getMaxAvailableHeight((View)localObject1, getDropDownVerticalOffset()) >= getResources().getDimensionPixelSize(aih.b.mw_recipient_list_item_height) * getAdapter().getCount() + 0) {
        break label126;
      }
      if (g == h) {
        break label159;
      }
      g = h;
      j = 1;
    }
    for (;;)
    {
      if (j != 0)
      {
        localObject1 = c.a((PopupWindow)localObject2);
        if (localObject1 == null) {
          setDropDownBackgroundDrawable(g);
        }
      }
      else
      {
        return;
        localObject1 = this;
        break;
        label126:
        if (g == i) {
          break label159;
        }
        g = i;
        j = 1;
        continue;
      }
      ((View)localObject1).setBackground(g);
      return;
      label159:
      j = 0;
    }
  }
  
  public void a(View paramView, RecipientEdit.d paramd)
  {
    c = paramView;
    b = paramd;
  }
  
  public void a(boolean paramBoolean)
  {
    boolean bool = isPopupShowing();
    super.dismissDropDown();
    if (paramBoolean) {
      ((aii)getAdapter()).d();
    }
    if ((b != null) && (bool)) {
      b.a(c, false);
    }
  }
  
  public void dismissDropDown()
  {
    a(true);
  }
  
  public ListPopupWindow getPopup()
  {
    return c.a(this);
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((a != null) && (a.a(this, paramInt, paramKeyEvent))) {
      return true;
    }
    return super.onKeyPreIme(paramInt, paramKeyEvent);
  }
  
  protected void performFiltering(CharSequence paramCharSequence, int paramInt)
  {
    if (((ParcelableImageSpan[])((Editable)paramCharSequence).getSpans(0, paramCharSequence.length(), ParcelableImageSpan.class)).length == 0)
    {
      Object localObject1 = paramCharSequence;
      if ((paramCharSequence instanceof Spannable))
      {
        Object localObject2 = (Spannable)paramCharSequence;
        int j = BaseInputConnection.getComposingSpanStart((Spannable)localObject2);
        int k = BaseInputConnection.getComposingSpanEnd((Spannable)localObject2);
        localObject1 = paramCharSequence;
        if (j >= 0)
        {
          localObject1 = paramCharSequence;
          if (k >= 0)
          {
            localObject1 = ((Spannable)localObject2).subSequence(j, k);
            localObject2 = ((CharSequence)localObject1).toString().replace("'", "");
            localObject1 = paramCharSequence.toString().replace((CharSequence)localObject1, (CharSequence)localObject2);
          }
        }
      }
      super.performFiltering((CharSequence)localObject1, paramInt);
    }
  }
  
  public <T extends ListAdapter,  extends Filterable> void setAdapter(T paramT)
  {
    super.setAdapter(paramT);
    if (d == null) {
      d = new b(null);
    }
    for (;;)
    {
      e = paramT;
      if (e != null) {
        paramT.registerDataSetObserver(d);
      }
      return;
      if (e != null) {
        e.unregisterDataSetObserver(d);
      }
    }
  }
  
  public void setOnKeyPreImeListener(a parama)
  {
    a = parama;
  }
  
  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    f = paramOnScrollListener;
  }
  
  public void showDropDown()
  {
    boolean bool = isPopupShowing();
    super.showDropDown();
    if ((b != null) && (!bool)) {
      b.a(c, true);
    }
    if ((f != null) && (!bool)) {
      getPopup().getListView().setOnScrollListener(f);
    }
  }
  
  public static abstract interface a
  {
    public abstract boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent);
  }
  
  class b
    extends DataSetObserver
  {
    private b() {}
    
    public void onChanged()
    {
      a();
    }
  }
  
  static class c
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
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.RecipientEdit.RecipientAutoCompleteTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */