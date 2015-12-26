package com.android.mms.ui;

import android.database.Cursor;
import android.view.ActionMode;
import android.widget.AbsListView.MultiChoiceModeListener;

public abstract interface MessageListView$b
  extends AbsListView.MultiChoiceModeListener
{
  public abstract ActionMode a();
  
  public abstract void a(Cursor paramCursor);
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListView.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */