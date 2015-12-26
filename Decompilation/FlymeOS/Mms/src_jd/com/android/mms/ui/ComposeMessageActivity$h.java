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
import android.view.View;
import ga;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import to;
import tp;
import tq;
import vv;
import vx;
import wd;

public class ComposeMessageActivity$h
  implements MessageListView.b
{
  private MenuItem b;
  private MenuItem c;
  private MenuItem d;
  private HashMap<Long, ComposeMessageActivity.j> e;
  private int f = 0;
  private int g = 0;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  private ActionMode k;
  private MultiChoiceView l;
  private int m = 0;
  
  private ComposeMessageActivity$h(ComposeMessageActivity paramComposeMessageActivity) {}
  
  private void a(vv paramvv, long paramLong)
  {
    e.remove(Long.valueOf(paramLong));
    if (u == 130) {
      j -= 1;
    }
    do
    {
      do
      {
        return;
        if (!paramvv.j()) {
          break;
        }
        if (paramvv.o())
        {
          h -= 1;
          return;
        }
        f -= 1;
        if (!paramvv.ae()) {
          g -= 1;
        }
      } while (!y);
      m -= 1;
      return;
    } while (M != vv.a);
    i -= 1;
  }
  
  private void a(boolean paramBoolean)
  {
    f = 0;
    g = 0;
    h = 0;
    i = 0;
    j = 0;
    m = 0;
    if (e != null) {
      e.clear();
    }
    if (paramBoolean) {
      e = null;
    }
  }
  
  private void b()
  {
    l = new MultiChoiceView(a);
    l.setOnCloseItemClickListener(new tp(this));
    l.setOnSelectAllItemClickListener(new tq(this));
    k.setCustomView(l);
  }
  
  private void b(vv paramvv, long paramLong)
  {
    if (u == 130) {
      j += 1;
    }
    for (;;)
    {
      e.put(Long.valueOf(paramLong), new ComposeMessageActivity.j(a, paramLong, paramvv));
      return;
      if (paramvv.j())
      {
        if (paramvv.o())
        {
          h += 1;
        }
        else
        {
          f += 1;
          if (!paramvv.ae()) {
            g += 1;
          }
          if (y) {
            m += 1;
          }
        }
      }
      else if (M == vv.a) {
        i += 1;
      }
    }
  }
  
  public ActionMode a()
  {
    return k;
  }
  
  public void a(int paramInt)
  {
    if (paramInt == 0)
    {
      aaa.a(b, false);
      aaa.a(c, false);
      aaa.a(d, false);
      if (!ga.C())
      {
        aaa.a(d, false);
        aaa.a(c, false);
      }
      aaa.a(a, l, ComposeMessageActivity.R(a).getCheckedItemCount(), a.b.getCount());
      return;
    }
    if ((j > 0) || (i > 0) || (g > 0) || (h > 0))
    {
      aaa.a(b, false);
      label118:
      if ((j <= 0) && (i <= 0) && ((paramInt <= 1) || (f <= 0)) && (h <= 0)) {
        break label181;
      }
      aaa.a(c, false);
    }
    for (;;)
    {
      aaa.a(d, true);
      break;
      aaa.a(b, true);
      break label118;
      label181:
      if ((f == 1) && (paramInt == 1))
      {
        MenuItem localMenuItem = c;
        if ((ga.C()) && (m == 1)) {}
        for (boolean bool = true;; bool = false)
        {
          aaa.a(localMenuItem, bool);
          break;
        }
      }
      aaa.a(c, ga.C());
    }
  }
  
  public void a(Cursor paramCursor)
  {
    if ((e == null) || (e.size() == 0) || (a.b.getCount() == 0)) {}
    HashMap localHashMap;
    do
    {
      return;
      localHashMap = (HashMap)e.clone();
      paramCursor.moveToFirst();
      do
      {
        localHashMap.remove(Long.valueOf(vx.a(paramCursor.getString(0).equals("mms"), paramCursor.getLong(1))));
      } while ((paramCursor.moveToNext()) && (localHashMap.size() > 0));
    } while (localHashMap.size() == 0);
    paramCursor = localHashMap.values().iterator();
    while (paramCursor.hasNext()) {
      a(nextc, false);
    }
    if (e.size() == 0)
    {
      ComposeMessageActivity.R(a).a();
      return;
    }
    a(e.size());
  }
  
  public void a(Menu paramMenu)
  {
    a.getMenuInflater().inflate(2131951618, paramMenu);
    b = paramMenu.findItem(2131886796);
    c = paramMenu.findItem(2131886797);
    d = paramMenu.findItem(2131886798);
  }
  
  public void a(vv paramvv, boolean paramBoolean)
  {
    long l1 = vx.a(paramvv.j(), e);
    boolean bool = e.containsKey(Long.valueOf(l1));
    if ((bool) && (!paramBoolean)) {
      a(paramvv, l1);
    }
    while ((bool) || (!paramBoolean)) {
      return;
    }
    b(paramvv, l1);
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      paramActionMode.finish();
    case 2131886796: 
    case 2131886797: 
      do
      {
        do
        {
          return true;
        } while (e.size() <= 0);
        try
        {
          ComposeMessageActivity.a(a, e);
          return true;
        }
        catch (Exception paramMenuItem)
        {
          Log.e("Mms/compose", "exception : ", paramMenuItem);
          return true;
        }
        finally
        {
          paramActionMode.finish();
        }
      } while (e.size() <= 0);
      try
      {
        ComposeMessageActivity.a(a, e, true);
        return true;
      }
      catch (Exception paramActionMode)
      {
        Log.e("Mms/compose", "exception : ", paramActionMode);
        return true;
      }
    }
    paramMenuItem = a.getResources().getQuantityString(2131427333, e.size(), new Object[] { Integer.valueOf(e.size()) });
    String str = a.getResources().getString(17039360);
    ComposeMessageActivity localComposeMessageActivity = a;
    paramActionMode = new to(this, paramActionMode);
    wd.a(localComposeMessageActivity, new CharSequence[] { paramMenuItem, str }, paramActionMode).show();
    return true;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    k = paramActionMode;
    if (e == null) {
      e = new HashMap();
    }
    b();
    ComposeMessageActivity.R(a).c();
    a(paramMenu);
    onPrepareActionMode(paramActionMode, paramMenu);
    a.b.c(true);
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    if (e != null)
    {
      paramActionMode = e.values().iterator();
      n = 0;
      while (paramActionMode.hasNext())
      {
        ComposeMessageActivity.j localj = (ComposeMessageActivity.j)paramActionMode.next();
        if (c != null) {
          c.b(false);
        }
        n += 1;
      }
    }
    ComposeMessageActivity.R(a).d();
    if (!ga.a()) {
      ComposeMessageActivity.g(a, 0);
    }
    ComposeMessageActivity.s(a, true);
    a(true);
    k = null;
    ComposeMessageActivity.R(a).setFinisMuiltTransientState(true);
    paramActionMode = a;
    if (a.h) {}
    for (int n = 1;; n = 2)
    {
      ComposeMessageActivity.e(paramActionMode, n);
      ComposeMessageActivity.t(a, false);
      a.b.c(false);
      return;
    }
  }
  
  public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
  {
    if (a.b.getItemId(paramInt) != paramLong) {
      return;
    }
    paramActionMode = (Cursor)a.b.getItem(paramInt);
    if ((paramActionMode == null) || (paramInt >= a.b.getCount()))
    {
      localObject = new StringBuilder().append("onItemCheckedStateChanged(1)--> position = ").append(paramInt).append(", cursor.getCount() = ");
      if (paramActionMode != null) {}
      for (paramInt = paramActionMode.getCount();; paramInt = 0)
      {
        Log.e("ComposeModecallback", paramInt);
        return;
      }
    }
    Object localObject = a.b.a(paramActionMode);
    if (localObject == null)
    {
      Log.e("ComposeModecallback", "onItemCheckedStateChanged(2)--> position = " + paramInt + ", cursor.getCount() = " + paramActionMode.getCount());
      return;
    }
    ((vv)localObject).b(paramBoolean);
    a((vv)localObject, paramBoolean);
    a(e.size());
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if (!ComposeMessageActivity.aa(a))
    {
      c.setEnabled(false);
      d.setEnabled(false);
    }
    ComposeMessageActivity.s(a, false);
    if (e != null) {
      a(e.size());
    }
    if (!ga.a()) {
      ComposeMessageActivity.g(a, ComposeMessageActivity.ab(a).getHeight());
    }
    ComposeMessageActivity.e(a, 4);
    ComposeMessageActivity.t(a, true);
    ComposeMessageActivity.a(a, ComposeMessageActivity.f.e, 0);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */