package com.android.mms.fragmentstyle;

import aaa;
import android.database.Cursor;
import android.support.v7.widget.MultiChoiceView;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import ga;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jt;
import ju;
import jv;
import jy;
import lx;
import vv;
import vx;

public class FavoriteActivity$b
  implements AbsListView.MultiChoiceModeListener
{
  private MenuItem b;
  private MenuItem c;
  private MenuItem d;
  private HashMap<Long, FavoriteActivity.c> e;
  private int f = 0;
  private int g = 0;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  
  public FavoriteActivity$b(FavoriteActivity paramFavoriteActivity) {}
  
  private void a(int paramInt)
  {
    boolean bool2 = true;
    MenuItem localMenuItem;
    if ((paramInt == 0) || (!ga.C()))
    {
      aaa.a(b, false);
      localMenuItem = c;
      if ((paramInt <= 0) || (!ga.C())) {
        break label185;
      }
    }
    label185:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      aaa.a(localMenuItem, bool1);
      aaa.a(a, FavoriteActivity.e(a), FavoriteActivity.b(a).getCheckedItemCount(), FavoriteActivity.a(a).getCount());
      return;
      if ((i > 0) || (h > 0) || ((paramInt > 1) && (f > 0)) || (g > 0))
      {
        aaa.a(b, false);
        break;
      }
      if ((f == 1) && (paramInt == 1))
      {
        localMenuItem = b;
        if ((ga.C()) && (j == 1)) {}
        for (bool1 = true;; bool1 = false)
        {
          aaa.a(localMenuItem, bool1);
          break;
        }
      }
      aaa.a(b, ga.C());
      break;
    }
  }
  
  private void a(HashMap<Long, FavoriteActivity.c> paramHashMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int m = paramHashMap.size();
    if (m == 0) {
      return;
    }
    List localList = jy.a(paramHashMap);
    Object localObject = null;
    int k = 0;
    if (k < m)
    {
      vv localvv = getc;
      if (k == 0) {
        localObject = localvv;
      }
      if (k < m - 1) {
        localStringBuilder.append(o).append("\n");
      }
      for (;;)
      {
        k += 1;
        break;
        localStringBuilder.append(o);
      }
    }
    paramHashMap.clear();
    FavoriteActivity.b(a).a();
    if ((m == 1) && (((vv)localObject).j()))
    {
      jy.a((vv)localObject, a, a);
      return;
    }
    jy.a(localStringBuilder.toString(), a, a);
  }
  
  private void a(vv paramvv, long paramLong)
  {
    e.remove(Long.valueOf(paramLong));
    if (u == 130) {
      i -= 1;
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
          g -= 1;
          return;
        }
        f -= 1;
      } while (!y);
      j -= 1;
      return;
    } while (M != vv.a);
    h -= 1;
  }
  
  private void a(vv paramvv, boolean paramBoolean)
  {
    long l = vx.a(paramvv.j(), e);
    boolean bool = e.containsKey(Long.valueOf(l));
    if ((!bool) && (paramBoolean)) {
      b(paramvv, l);
    }
    while ((!bool) || (paramBoolean)) {
      return;
    }
    a(paramvv, l);
  }
  
  private void a(boolean paramBoolean)
  {
    if (paramBoolean) {
      e = null;
    }
    f = 0;
    g = 0;
    h = 0;
    i = 0;
    j = 0;
  }
  
  private void b(Cursor paramCursor)
  {
    long[] arrayOfLong = FavoriteActivity.b(a).getCheckedItemIds();
    int m = arrayOfLong.length;
    vv localvv = null;
    int k = 0;
    if (k < m)
    {
      long l = arrayOfLong[k];
      Log.i("FavoriteActivity", "restoreSelectedItem id = " + l);
      if (l > 0L) {
        localvv = FavoriteActivity.a(a).a("sms", l, paramCursor);
      }
      for (;;)
      {
        Log.i("FavoriteActivity", "restoreSelectedItem msgItem = " + localvv);
        if (localvv != null) {
          b(localvv, l);
        }
        k += 1;
        break;
        if (l < 0L) {
          localvv = FavoriteActivity.a(a).a("mms", -l, paramCursor);
        }
      }
    }
  }
  
  private void b(HashMap<Long, FavoriteActivity.c> paramHashMap)
  {
    int k = paramHashMap.size();
    if (k == 0) {
      return;
    }
    vv[] arrayOfvv = new vv[k];
    Iterator localIterator = paramHashMap.values().iterator();
    k = 0;
    boolean bool = false;
    while (localIterator.hasNext())
    {
      arrayOfvv[k] = nextc;
      bool = arrayOfvv[k].j();
      k += 1;
    }
    FavoriteActivity.a(a, paramHashMap.size(), bool);
    a.a(false, arrayOfvv);
  }
  
  private void b(vv paramvv, long paramLong)
  {
    if (u == 130) {
      i += 1;
    }
    for (;;)
    {
      e.put(Long.valueOf(paramLong), new FavoriteActivity.c(a, paramLong, paramvv));
      return;
      if (paramvv.j())
      {
        if (paramvv.o())
        {
          g += 1;
        }
        else
        {
          f += 1;
          if (y) {
            j += 1;
          }
        }
      }
      else if (M == vv.a) {
        h += 1;
      }
    }
  }
  
  public void a()
  {
    int k = FavoriteActivity.b(a).getCheckedItemCount();
    int m = FavoriteActivity.a(a).getCount();
    if (k < m) {}
    for (k = 1; k == 0; k = 0)
    {
      e.clear();
      FavoriteActivity.f(a).f();
      a(false);
      a(e.size());
      return;
    }
    FavoriteActivity.f(a).b();
    k = 0;
    label85:
    Object localObject;
    if (k < m)
    {
      localObject = (Cursor)FavoriteActivity.a(a).getItem(k);
      if (localObject != null) {
        break label118;
      }
    }
    for (;;)
    {
      k += 1;
      break label85;
      break;
      label118:
      localObject = FavoriteActivity.a(a).a((Cursor)localObject);
      if (localObject != null)
      {
        long l = vx.a(((vv)localObject).j(), e);
        if (!e.containsKey(Long.valueOf(l))) {
          b((vv)localObject, l);
        }
      }
    }
  }
  
  public void a(Cursor paramCursor)
  {
    if ((e == null) || (FavoriteActivity.a(a).getCount() == 0)) {}
    do
    {
      HashMap localHashMap;
      do
      {
        return;
        if (e.size() <= 0) {
          break;
        }
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
      if (FavoriteActivity.b(a).getCheckedItemCount() > 0)
      {
        Log.i("FavoriteActivity", "refreshSelectedStatus mListView.getCheckedItemCount() = " + FavoriteActivity.b(a).getCheckedItemCount());
        paramCursor.moveToFirst();
        b(paramCursor);
      }
    } while (!FavoriteActivity.b(a).m());
    a(e.size());
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      if (e.size() > 0)
      {
        try
        {
          a(e);
        }
        catch (Exception paramActionMode)
        {
          Log.e("FavoriteActivity", "forward exception : ", paramActionMode);
        }
        continue;
        if (e.size() > 0)
        {
          b(e);
          paramActionMode.finish();
        }
      }
    }
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if ((FavoriteActivity.a(a) == null) || (FavoriteActivity.a(a).getCursor() == null) || (FavoriteActivity.a(a).getCount() <= 0)) {
      return false;
    }
    if (e == null) {
      e = new HashMap();
    }
    a.getMenuInflater().inflate(2131951623, paramMenu);
    b = paramMenu.findItem(2131886797);
    c = paramMenu.findItem(2131886798);
    d = paramMenu.findItem(2131886809);
    FavoriteActivity.a(a, new MultiChoiceView(a));
    FavoriteActivity.e(a).setOnCloseItemClickListener(new jt(this, paramActionMode));
    FavoriteActivity.e(a).setOnSelectAllItemClickListener(new ju(this));
    paramActionMode.setCustomView(FavoriteActivity.e(a));
    aaa.a(a, FavoriteActivity.b(a), true);
    FavoriteActivity.b(a).c();
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    if (e != null)
    {
      paramActionMode = e.values().iterator();
      int k = 0;
      while (paramActionMode.hasNext())
      {
        vv localvv = nextc;
        if (localvv != null) {
          localvv.b(false);
        }
        k += 1;
      }
    }
    a(true);
    aaa.a(a, FavoriteActivity.b(a), false);
    FavoriteActivity.b(a).d();
  }
  
  public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
  {
    paramActionMode = (Cursor)FavoriteActivity.a(a).getItem(paramInt);
    if ((paramActionMode == null) || (paramInt >= FavoriteActivity.a(a).getCount())) {}
    do
    {
      return;
      paramActionMode = FavoriteActivity.a(a).a(paramActionMode);
    } while (paramActionMode == null);
    a(paramActionMode, paramBoolean);
    if (e == null) {}
    for (paramInt = 0;; paramInt = e.size())
    {
      a(paramInt);
      return;
    }
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if ((e != null) && (FavoriteActivity.a(a).getCount() > 0)) {
      a(e.size());
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteActivity.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */