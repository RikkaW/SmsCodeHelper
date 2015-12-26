package com.android.mms.ui;

import aaa;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.MultiChoiceView;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;
import ga;
import gg;
import uv;
import uw;
import ux;
import vv;
import wd;

public class ManageSimMessages$b
  implements AbsListView.MultiChoiceModeListener
{
  private MenuItem b;
  private MenuItem c;
  
  public ManageSimMessages$b(ManageSimMessages paramManageSimMessages) {}
  
  public int a(int paramInt, long paramLong)
  {
    Object localObject = (Cursor)ManageSimMessages.c(a).getItemAtPosition(paramInt);
    if ((localObject == null) || (paramInt >= ManageSimMessages.c(a).getCount())) {}
    do
    {
      return 0;
      localObject = ManageSimMessages.b(a).a((Cursor)localObject);
    } while ((((vv)localObject).W() != null) && (!((vv)localObject).W().equals("")));
    c.setEnabled(false);
    return 0;
  }
  
  public int a(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    case 2131886811: 
    default: 
      return 0;
    }
    return 1;
  }
  
  public void a(MenuItem paramMenuItem, int paramInt, long paramLong) {}
  
  public boolean a()
  {
    if (ManageSimMessages.c(a) == null) {}
    while (ManageSimMessages.c(a).getCheckedItemCount() <= 0) {
      return false;
    }
    return true;
  }
  
  public void b(int paramInt, long paramLong) {}
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    int i = 0;
    switch (paramMenuItem.getItemId())
    {
    default: 
      paramActionMode.finish();
      return true;
    case 2131886798: 
      i = ManageSimMessages.c(a).getCheckedItemIds().length;
      paramMenuItem = a.getResources().getQuantityString(2131427333, i, new Object[] { Integer.valueOf(i) });
      String str = a.getResources().getString(17039360);
      ManageSimMessages localManageSimMessages = a;
      paramActionMode = new ux(this, paramActionMode);
      wd.a(localManageSimMessages, new CharSequence[] { paramMenuItem, str }, paramActionMode).show();
      return true;
    }
    if (ManageSimMessages.c(a).getCheckedItemIds() != null) {
      while (i < ManageSimMessages.c(a).getCheckedItemIds().length)
      {
        Log.i("ManageSimMessages", "MessageSimListAdapter getCachedMessageItem mSimList.getCheckedItemIds[" + i + "] = " + ManageSimMessages.c(a).getCheckedItemIds()[i]);
        i += 1;
      }
    }
    ManageSimMessages.a(a, ManageSimMessages.c(a).getCheckedItemIds(), 112);
    paramActionMode.finish();
    return true;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if ((ManageSimMessages.b(a) == null) || (ManageSimMessages.b(a).getCursor() == null) || (ManageSimMessages.b(a).getCount() == 0)) {
      return false;
    }
    ManageSimMessages.c(a).setPadding(ManageSimMessages.c(a).getPaddingLeft(), aaa.a(), ManageSimMessages.c(a).getPaddingRight(), ManageSimMessages.c(a).getPaddingBottom() + a.getResources().getDimensionPixelSize(2131558606));
    a.getMenuInflater().inflate(2131951628, paramMenu);
    b = paramMenu.findItem(2131886798);
    c = paramMenu.findItem(2131886811);
    ManageSimMessages.a(a, new MultiChoiceView(a));
    ManageSimMessages.d(a).setOnCloseItemClickListener(new uv(this, paramActionMode));
    ManageSimMessages.d(a).setOnSelectAllItemClickListener(new uw(this));
    paramActionMode.setCustomView(ManageSimMessages.d(a));
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    ManageSimMessages.c(a).setPadding(ManageSimMessages.c(a).getPaddingLeft(), aaa.a(), ManageSimMessages.c(a).getPaddingRight(), ManageSimMessages.c(a).getPaddingBottom() - a.getResources().getDimensionPixelSize(2131558606));
  }
  
  public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
  {
    boolean bool = true;
    Log.i("ManageSimMessages", "ManageSimMessages onItemCheckedStateChanged id = " + paramLong + ", position = " + paramInt);
    aaa.a(a, ManageSimMessages.d(a), ManageSimMessages.c(a).getCheckedItemCount(), ManageSimMessages.b(a).getCount());
    paramActionMode = c;
    if ((ManageSimMessages.c(a).getCheckedItemCount() > 0) && (ManageSimMessages.f(a)))
    {
      paramBoolean = true;
      aaa.a(paramActionMode, paramBoolean);
      paramActionMode = b;
      if ((ManageSimMessages.c(a).getCheckedItemCount() <= 0) || (!ga.C())) {
        break label149;
      }
    }
    label149:
    for (paramBoolean = bool;; paramBoolean = false)
    {
      aaa.a(paramActionMode, paramBoolean);
      return;
      paramBoolean = false;
      break;
    }
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ManageSimMessages.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */