package com.android.mms.fragmentstyle;

import aat;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.android.mms.ui.ConversationList;
import fl;
import ge;
import gr;
import gr.b;
import gx;
import java.util.Collection;
import lx;
import wd;

public final class ConversationListFragment$g
  extends gr.b
{
  private boolean b = false;
  
  public ConversationListFragment$g(ConversationListFragment paramConversationListFragment, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  public void a(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  public void handleMessage(Message paramMessage)
  {
    if (b) {
      return;
    }
    switch (what)
    {
    default: 
      super.handleMessage(paramMessage);
      return;
    }
    ConversationListFragment.c(a, true);
  }
  
  protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    super.onDeleteComplete(paramInt1, paramObject, paramInt2);
    switch (paramInt1)
    {
    case 1802: 
    default: 
    case 1801: 
      do
      {
        return;
        gr.a(MmsApp.c(), (Collection)paramObject);
        if (paramInt2 > 0) {
          ConversationListFragment.c(a, false);
        }
      } while (ConversationListFragment.q(a) == null);
      ConversationListFragment.q(a).d();
      return;
    }
    fl.a("onQueryComplete finished DELETE_OBSOLETE_THREADS_TOKEN", new Object[0]);
    MmsApp.c().a(false);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    int i = 1;
    int k = 0;
    int j = 0;
    if (ConversationListFragment.g(a))
    {
      a.a();
      ConversationListFragment.b(a, false);
    }
    if ((!ConversationListFragment.h(a)) || ((paramCursor == null) && (paramInt != 1702))) {
      if (paramCursor != null) {
        paramCursor.close();
      }
    }
    do
    {
      return;
      switch (paramInt)
      {
      default: 
        if (paramCursor != null) {
          paramCursor.close();
        }
        Log.e("ConversationList", "onQueryComplete called with unknown token " + paramInt);
      }
    } while (!ConversationListFragment.p(a));
    ConversationListFragment.b(a).invalidateViews();
    return;
    if (((!ConversationList.b) || (!ConversationListFragment.i(a))) && (aat.a)) {
      aat.a(a.a, 1809);
    }
    j = paramCursor.getCount();
    if (j < 1) {
      gr.t();
    }
    for (paramInt = i;; paramInt = 0)
    {
      if (paramInt != 0) {
        gx.a().a(false);
      }
      ConversationListFragment.a(a).changeCursor(paramCursor);
      boolean bool = ConversationListFragment.a(a, j);
      if ((ConversationListFragment.b(a) != null) && ((ConversationListFragment.j(a)) || (-1 == ConversationListFragment.k(a)) || (bool))) {}
      for (paramInt = ConversationListFragment.a(a, paramCursor);; paramInt = 0)
      {
        if ((ConversationListFragment.b(a) != null) && ((ConversationListFragment.j(a)) || (-1 == ConversationListFragment.k(a)) || (bool)))
        {
          if (paramInt > ConversationListFragment.k(a)) {
            ConversationListFragment.b(a).setSelection(0);
          }
          ConversationListFragment.b(a, paramInt);
          ConversationListFragment.a(a, false);
        }
        ConversationListFragment.l(a);
        break;
        paramInt = k;
        if (paramCursor != null)
        {
          paramInt = j;
          if (paramCursor.moveToNext()) {
            paramInt = paramCursor.getInt(0);
          }
          paramCursor.close();
        }
        if (!ConversationList.b) {
          break;
        }
        if (ConversationListFragment.i(a)) {
          ConversationListFragment.c(a, paramInt);
        }
        if (ConversationListFragment.m(a) == null) {
          break;
        }
        ConversationListFragment.m(a).a(paramInt, ConversationListFragment.n(a));
        break;
        aat.a(paramCursor);
        if (!aat.c()) {
          break;
        }
        wd.q(ConversationListFragment.o(a));
        break;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.ConversationListFragment.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */