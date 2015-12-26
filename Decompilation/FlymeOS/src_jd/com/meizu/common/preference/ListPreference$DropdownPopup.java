package com.meizu.common.preference;

import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import java.lang.reflect.Method;

class ListPreference$DropdownPopup
  extends ListPopupWindow
{
  private ListAdapter mAdapter;
  
  public ListPreference$DropdownPopup(ListPreference paramListPreference, Context paramContext)
  {
    super(paramContext);
    setModal(true);
    setPromptPosition(0);
    setOnItemClickListener(new ListPreference.DropdownPopup.1(this, paramListPreference));
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    super.setAdapter(paramListAdapter);
    mAdapter = paramListAdapter;
  }
  
  public void show()
  {
    int j = 0;
    int i = ListPreference.access$100(this$0).getPaddingLeft();
    int k = ListPreference.access$100(this$0).getPaddingRight();
    int m = ListPreference.access$100(this$0).getWidth();
    if ((ListPreference.access$200(this$0) <= 0) || (ListPreference.access$200(this$0) > m - i - k)) {
      ListPreference.access$202(this$0, m - i - k);
    }
    setContentWidth(ListPreference.access$200(this$0));
    try
    {
      if (ListPreference.access$300() == null) {
        ListPreference.access$302(getClass().getMethod("setLayoutMode", new Class[] { Integer.TYPE }));
      }
      ListPreference.access$300().invoke(this, new Object[] { Integer.valueOf(4) });
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    if (ListPreference.access$400(this$0) > 0)
    {
      i = j;
      if (mAdapter != null)
      {
        i = j;
        if (mAdapter.getCount() > 0)
        {
          j = 0;
          k = 0;
          do
          {
            View localView = mAdapter.getView(j, null, getListView());
            i = k;
            if (localView != null)
            {
              localView.measure(0, 0);
              i = k + localView.getMeasuredHeight();
            }
            m = j + 1;
            j = m;
            k = i;
          } while (m < mAdapter.getCount());
        }
      }
      if (i > ListPreference.access$400(this$0)) {
        setHeight(ListPreference.access$400(this$0));
      }
    }
    ListPreference.access$002(this$0, this$0.findIndexOfValue(this$0.getValue()));
    setInputMethodMode(2);
    super.show();
    getListView().setChoiceMode(1);
    setSelection(ListPreference.access$000(this$0));
    setOnDismissListener(this$0);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ListPreference.DropdownPopup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */