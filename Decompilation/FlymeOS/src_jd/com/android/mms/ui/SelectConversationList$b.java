package com.android.mms.ui;

import aaa;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MzContactsContract.MzNetContacts;
import android.support.v7.widget.MultiChoiceView;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.android.mms.fragmentstyle.ConversationListFragment;
import gm;
import gq;
import gr;
import iz;
import java.util.ArrayList;
import xq;
import xr;
import xs;
import xt;

public class SelectConversationList$b
  extends iz
{
  MenuItem d;
  private MultiChoiceView f;
  
  private SelectConversationList$b(SelectConversationList paramSelectConversationList) {}
  
  private void d()
  {
    aaa.a(e, f, SelectConversationList.a(e).getCheckedItemCount(), SelectConversationList.a(e).getAdapter().getCount());
  }
  
  public void c()
  {
    MenuItem localMenuItem;
    if ((SelectConversationList.d(e)) && (d != null))
    {
      localMenuItem = d;
      if (SelectConversationList.a(e).getCheckedItemCount() <= 0) {
        break label46;
      }
    }
    label46:
    for (boolean bool = true;; bool = false)
    {
      localMenuItem.setEnabled(bool);
      return;
    }
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return false;
      paramMenuItem = new Intent();
      SparseBooleanArray localSparseBooleanArray = SelectConversationList.a(e).getCheckedItemPositions();
      ArrayList localArrayList = new ArrayList();
      Cursor localCursor = ((CursorAdapter)SelectConversationList.c(e).getListAdapter()).getCursor();
      int k = localCursor.getPosition();
      int i = 0;
      while (i < localCursor.getCount())
      {
        gq localgq;
        if (localSparseBooleanArray.get(i))
        {
          localCursor.moveToPosition(i);
          localgq = gr.a(MmsApp.c(), localCursor).h();
          if (localgq != null) {}
        }
        else
        {
          i += 1;
          continue;
        }
        int j = 0;
        label141:
        ContentValues localContentValues;
        if (j < localgq.size())
        {
          paramActionMode = (gm)localgq.get(j);
          String str = paramActionMode.d();
          localContentValues = new ContentValues();
          localContentValues.put("data1", str);
          long l = paramActionMode.n();
          boolean bool = MzContactsContract.MzNetContacts.isYPContact(l);
          if ((l <= 0L) && (!bool)) {
            break label241;
          }
        }
        label241:
        for (paramActionMode = paramActionMode.g();; paramActionMode = "")
        {
          localContentValues.put("display_name", paramActionMode);
          localArrayList.add(localContentValues);
          j += 1;
          break label141;
          break;
        }
      }
      localCursor.moveToPosition(k);
      paramActionMode = new ContentValues[localArrayList.size()];
      i = 0;
      while (i < localArrayList.size())
      {
        paramActionMode[i] = ((ContentValues)localArrayList.get(i));
        i += 1;
      }
      paramMenuItem.putExtra("com.android.contacts.extra.MULTIPLE_PICK_DATAS", paramActionMode);
      e.setResult(-1, paramMenuItem);
      e.finish();
    }
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    e.getMenuInflater().inflate(2131951627, paramMenu);
    d = paramMenu.findItem(2131886118);
    d.setEnabled(false);
    a();
    c();
    f = new MultiChoiceView(e);
    f.setOnCloseItemClickListener(new xq(this, paramActionMode));
    f.setOnSelectAllItemClickListener(new xr(this));
    f.setOnCloseItemClickListener(new xs(this));
    paramActionMode.setCustomView(f);
    d();
    SelectConversationList.a(e).getAdapter().registerDataSetObserver(new xt(this));
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    e.onBackPressed();
  }
  
  public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
  {
    d();
    c();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if (SelectConversationList.a(e).getAdapter().getCount() > 0) {
      a(SelectConversationList.a(e).getCheckedItemCount(), SelectConversationList.a(e).getAdapter().getCount());
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SelectConversationList.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */