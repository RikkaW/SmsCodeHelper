package com.android.mms.ui;

import android.database.Cursor;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.meizu.common.widget.SelectionButton;
import gf;
import gq;
import gr;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import ka;
import lx;
import wd;

public class MeizuSearchActivity$b
  extends ka
{
  private MenuItem f;
  private MenuItem g;
  private MenuItem h;
  private MenuItem i;
  private HashMap<String, String> j;
  private boolean k;
  private boolean l;
  
  public int a(int paramInt, long paramLong)
  {
    boolean bool2 = true;
    paramInt = MeizuSearchActivity.i(e).e() - MeizuSearchActivity.c(e).getHeaderViewsCount();
    Cursor localCursor;
    if ((MeizuSearchActivity.g(e) != null) && (paramInt >= 0))
    {
      localCursor = (Cursor)MeizuSearchActivity.g(e).getItem(paramInt);
      if ((localCursor != null) && (!localCursor.isClosed())) {}
    }
    else
    {
      return 0;
    }
    MenuItem localMenuItem = f;
    if ((MeizuSearchActivity.a(e, localCursor)) && (MeizuSearchActivity.B(e)))
    {
      bool1 = true;
      localMenuItem.setEnabled(bool1);
      localMenuItem = g;
      if ((localCursor.getInt(6) != 0) || (!MeizuSearchActivity.B(e))) {
        break label163;
      }
    }
    label163:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      localMenuItem.setEnabled(bool1);
      return 0;
      bool1 = false;
      break;
    }
  }
  
  public int a(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    case 2131886804: 
    case 2131886805: 
    default: 
      return 0;
    }
    return 1;
  }
  
  protected void a(int paramInt1, int paramInt2)
  {
    int m = 0;
    boolean bool;
    SelectionButton localSelectionButton;
    if (d != null)
    {
      d.setCurrentCount(paramInt1);
      d.setTotalCount(paramInt2);
      if (!c)
      {
        if (paramInt1 <= 0) {
          break label65;
        }
        bool = true;
        c = bool;
        localSelectionButton = d;
        if (!bool) {
          break label71;
        }
      }
    }
    label65:
    label71:
    for (paramInt1 = m;; paramInt1 = 4)
    {
      localSelectionButton.setVisibility(paramInt1);
      return;
      bool = false;
      break;
    }
  }
  
  public void a(MenuItem paramMenuItem, int paramInt, long paramLong)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      MeizuSearchActivity.C(e);
      return;
      paramMenuItem = new HashMap();
      Cursor localCursor = (Cursor)MeizuSearchActivity.g(e).getItem(paramInt);
      if ((localCursor != null) && (!localCursor.isClosed()))
      {
        paramMenuItem.put(MeizuSearchActivity.b(localCursor), MeizuSearchActivity.a(localCursor));
        MeizuSearchActivity.c(e).setItemChecked(paramInt, false);
        MeizuSearchActivity.f(e, false);
        MeizuSearchActivity.a(e, paramMenuItem, false);
        continue;
        paramMenuItem = new HashMap();
        localCursor = (Cursor)MeizuSearchActivity.g(e).getItem(paramInt);
        if ((localCursor != null) && (!localCursor.isClosed()))
        {
          paramMenuItem.put(MeizuSearchActivity.b(localCursor), MeizuSearchActivity.a(localCursor));
          MeizuSearchActivity.c(e).setItemChecked(paramInt, false);
          MeizuSearchActivity.c(e, paramMenuItem);
        }
      }
    }
  }
  
  public boolean a(int paramInt)
  {
    if ((!b) || (j == null) || (paramInt == 0) || (MeizuSearchActivity.c(e).getCheckedItemIds().length == 0)) {
      return false;
    }
    if (d != null)
    {
      d.setCurrentCount(j.size());
      d.setTotalCount(paramInt);
    }
    return true;
  }
  
  public void b()
  {
    boolean bool3 = true;
    if (d.isAllSelected())
    {
      MeizuSearchActivity.i(e).d();
      return;
    }
    MeizuSearchActivity.i(e).b();
    int m = MeizuSearchActivity.g(e).getCount();
    d.setCurrentCount(m);
    Object localObject1 = MeizuSearchActivity.g(e).getCursor();
    ((Cursor)localObject1).moveToPosition(-1);
    m = 0;
    boolean bool1 = true;
    while (((Cursor)localObject1).moveToNext())
    {
      long l1 = ((Cursor)localObject1).getLong(1);
      Object localObject2 = MeizuSearchActivity.b((Cursor)localObject1);
      if (!j.containsKey(localObject2)) {
        j.put(localObject2, MeizuSearchActivity.a((Cursor)localObject1));
      }
      if ((bool1) || (m == 0))
      {
        localObject2 = gr.a(MmsApp.c(), l1, true);
        if (localObject2 != null)
        {
          boolean bool2 = bool1;
          if (bool1 == true)
          {
            bool2 = bool1;
            if (((gr)localObject2).h().size() != 1) {
              bool2 = false;
            }
          }
          bool1 = bool2;
          if (m == 0) {
            if (((Cursor)localObject1).getLong(6) == 0L)
            {
              m = 1;
              bool1 = bool2;
            }
            else
            {
              m = 0;
              bool1 = bool2;
            }
          }
        }
      }
    }
    k = bool1;
    localObject1 = f;
    if ((bool1) && (MeizuSearchActivity.B(e)))
    {
      bool1 = true;
      ((MenuItem)localObject1).setEnabled(bool1);
      localObject1 = g;
      if ((m == 0) || (!MeizuSearchActivity.B(e))) {
        break label289;
      }
    }
    label289:
    for (bool1 = bool3;; bool1 = false)
    {
      ((MenuItem)localObject1).setEnabled(bool1);
      return;
      bool1 = false;
      break;
    }
  }
  
  public void b(int paramInt, long paramLong)
  {
    MeizuSearchActivity.C(e);
    if (f != null) {
      f.setEnabled(k);
    }
    if (g != null) {
      g.setEnabled(l);
    }
  }
  
  protected void b(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      MeizuSearchActivity.A(e).setVisibility(0);
      return;
    }
    MeizuSearchActivity.A(e).setVisibility(4);
  }
  
  public void c()
  {
    Object localObject1 = MeizuSearchActivity.g(e).getCursor();
    if ((localObject1 == null) || (((Cursor)localObject1).getCount() == 0) || (j.size() == 0)) {}
    for (;;)
    {
      return;
      Object localObject2 = (HashMap)j.clone();
      ((Cursor)localObject1).moveToFirst();
      do
      {
        ((HashMap)localObject2).remove(MeizuSearchActivity.b((Cursor)localObject1));
      } while ((((Cursor)localObject1).moveToNext()) && (((HashMap)localObject2).size() > 0));
      if (((HashMap)localObject2).size() != 0)
      {
        localObject1 = ((HashMap)localObject2).keySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          j.remove(localObject2);
        }
      }
    }
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    if (!MeizuSearchActivity.B(e)) {}
    do
    {
      do
      {
        return true;
        switch (paramMenuItem.getItemId())
        {
        default: 
          return true;
        case 2131886798: 
          i.setTitle(wd.b(e, j.size(), 2131493281));
          return true;
        }
      } while (j.size() <= 0);
      e.a(j);
      return true;
    } while (j.size() <= 0);
    MeizuSearchActivity.f(e, false);
    MeizuSearchActivity.a(e, (HashMap)j.clone(), false);
    paramActionMode.finish();
    return true;
    MeizuSearchActivity.c(e, (HashMap)j.clone());
    paramActionMode.finish();
    return true;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    MeizuSearchActivity.a(e, false);
    if ((MeizuSearchActivity.g(e) == null) || (MeizuSearchActivity.g(e).getCursor() == null) || (MeizuSearchActivity.g(e).getCount() == 0)) {
      return false;
    }
    if (j == null) {
      j = new HashMap();
    }
    a(2131951625, paramMenu, e);
    g = paramMenu.findItem(2131886804);
    h = paramMenu.findItem(2131886798);
    i = paramMenu.findItem(2131886810);
    f = paramMenu.findItem(2131886805);
    a(paramMenu, MeizuSearchActivity.g(e).getCount(), MeizuSearchActivity.A(e));
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    super.onDestroyActionMode(paramActionMode);
    l = false;
    if (j != null) {
      j.clear();
    }
    if ((MeizuSearchActivity.A(e) != null) && (MeizuSearchActivity.A(e).getVisibility() == 0)) {
      MeizuSearchActivity.A(e).setVisibility(4);
    }
  }
  
  public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
  {
    boolean bool = true;
    paramActionMode = (Cursor)MeizuSearchActivity.g(e).getItem(paramInt);
    if ((paramActionMode == null) || (paramActionMode.isClosed())) {}
    while (MeizuSearchActivity.g(e).getItemId(paramInt) != paramLong) {
      return;
    }
    String str = MeizuSearchActivity.b(paramActionMode);
    if (paramBoolean)
    {
      j.put(str, MeizuSearchActivity.a(paramActionMode));
      if (j.size() > 0)
      {
        d.setCurrentCount(j.size());
        if (j.size() <= 0) {
          break label229;
        }
        paramBoolean = true;
        label110:
        a(paramBoolean);
      }
      k = MeizuSearchActivity.a(e, j);
      paramActionMode = f;
      if ((!k) || (!MeizuSearchActivity.B(e))) {
        break label235;
      }
      paramBoolean = true;
      label156:
      paramActionMode.setEnabled(paramBoolean);
      l = MeizuSearchActivity.b(e, j);
      paramActionMode = g;
      if ((!l) || (!MeizuSearchActivity.B(e))) {
        break label241;
      }
    }
    label229:
    label235:
    label241:
    for (paramBoolean = bool;; paramBoolean = false)
    {
      paramActionMode.setEnabled(paramBoolean);
      return;
      j.remove(str);
      break;
      paramBoolean = false;
      break label110;
      paramBoolean = false;
      break label156;
    }
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    boolean bool2 = false;
    b(true);
    if (j != null) {
      a(j.size(), MeizuSearchActivity.g(e).getCount());
    }
    k = MeizuSearchActivity.a(e, j);
    paramActionMode = f;
    if ((k) && (MeizuSearchActivity.B(e))) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      paramActionMode.setEnabled(bool1);
      l = MeizuSearchActivity.b(e, j);
      paramActionMode = g;
      bool1 = bool2;
      if (l)
      {
        bool1 = bool2;
        if (MeizuSearchActivity.B(e)) {
          bool1 = true;
        }
      }
      paramActionMode.setEnabled(bool1);
      h.setEnabled(MeizuSearchActivity.B(e));
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MeizuSearchActivity.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */