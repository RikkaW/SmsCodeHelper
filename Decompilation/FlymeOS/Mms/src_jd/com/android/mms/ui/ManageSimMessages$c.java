package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import com.meizu.common.widget.EmptyView;
import gg;

class ManageSimMessages$c
  extends AsyncQueryHandler
{
  public ManageSimMessages$c(ManageSimMessages paramManageSimMessages, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    if (ManageSimMessages.g(a))
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
      ManageSimMessages.h(a);
      return;
    }
    if (paramCursor != null)
    {
      paramInt = paramCursor.getCount();
      if (ManageSimMessages.i(a) != null)
      {
        paramObject = ManageSimMessages.i(a);
        if (paramInt != 0) {
          break label94;
        }
        paramInt = 0;
        ((EmptyView)paramObject).setVisibility(paramInt);
      }
      if (ManageSimMessages.b(a) != null) {
        break label100;
      }
      ManageSimMessages.a(a, paramCursor);
    }
    for (;;)
    {
      ManageSimMessages.h(a);
      return;
      label94:
      paramInt = 8;
      break;
      label100:
      ManageSimMessages.b(a).changeCursor(paramCursor);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ManageSimMessages.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */