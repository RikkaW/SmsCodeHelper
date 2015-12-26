package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import gr.b;

public final class SelectConversationList$c
  extends gr.b
{
  public SelectConversationList$c(SelectConversationList paramSelectConversationList, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    new SelectConversationList.a(a, paramCursor).execute(new Parcelable[][] { a.getIntent().getParcelableArrayExtra("com.android.contacts.extra.MULTIPLE_PICK_DATAS") });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SelectConversationList.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */