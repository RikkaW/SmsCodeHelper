package com.android.mms.fragmentstyle;

import aaa;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.MultiChoiceView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import ge;
import iz;
import java.util.ArrayList;
import java.util.HashSet;
import jl;
import jm;
import lx;

public class ConversationListFragment$e
  extends iz
{
  private MenuItem e;
  private MenuItem f;
  private MenuItem g;
  private MenuItem h;
  private MenuItem i;
  private boolean j = true;
  private boolean k = false;
  private MultiChoiceView l;
  
  private ConversationListFragment$e(ConversationListFragment paramConversationListFragment) {}
  
  private void a(boolean paramBoolean)
  {
    boolean bool;
    if ((ConversationListFragment.s(d)) && (ConversationListFragment.u(d).size() > 0))
    {
      bool = true;
      if ((paramBoolean) || (!bool)) {
        break label78;
      }
      g.setTitle(d.getString(2131493240));
      g.setIcon(2130837803);
    }
    for (;;)
    {
      aaa.a(g, bool);
      return;
      bool = false;
      break;
      label78:
      g.setTitle(d.getString(2131493601));
      g.setIcon(2130837805);
    }
  }
  
  private void d()
  {
    boolean bool4 = false;
    ConversationListFragment.d(d, false);
    if (ConversationListFragment.s(d))
    {
      localObject = ConversationListFragment.u(d);
      int n = ConversationListFragment.b(d).getHeaderViewsCount();
      int i1 = ConversationListFragment.b(d).getCount();
      int i2 = ConversationListFragment.b(d).getFooterViewsCount();
      int m = 0;
      boolean bool2 = false;
      bool1 = bool2;
      if (m < i1 - n - i2)
      {
        long l1 = ConversationListFragment.a(d).getItemId(m);
        boolean bool3;
        if (l1 == 0L) {
          bool3 = bool2;
        }
        label155:
        Cursor localCursor;
        label206:
        do
        {
          do
          {
            do
            {
              do
              {
                m += 1;
                bool2 = bool3;
                break;
                bool3 = bool2;
              } while (!((HashSet)localObject).contains(Long.valueOf(l1)));
              if (!bool2) {
                break label155;
              }
              bool3 = bool2;
            } while (ConversationListFragment.w(d));
            localCursor = (Cursor)ConversationListFragment.b(d).getItemAtPosition(m + n);
            bool3 = bool2;
          } while (localCursor == null);
          bool1 = bool2;
          if (!bool2)
          {
            if (localCursor.getInt(6) != 0) {
              break label257;
            }
            bool1 = true;
          }
          bool3 = bool1;
        } while (ConversationListFragment.w(d));
        ConversationListFragment localConversationListFragment = d;
        if (localCursor.getInt(11) != 1) {}
        for (bool2 = true;; bool2 = false)
        {
          ConversationListFragment.d(localConversationListFragment, bool2);
          bool3 = bool1;
          break;
          label257:
          bool1 = false;
          break label206;
        }
      }
    }
    else
    {
      bool1 = false;
    }
    k = bool1;
    Object localObject = f;
    if ((ConversationListFragment.s(d)) && (k))
    {
      bool1 = true;
      ((MenuItem)localObject).setEnabled(bool1);
      localObject = h;
      if ((!ConversationListFragment.s(d)) || (ConversationListFragment.u(d).size() <= 0)) {
        break label430;
      }
    }
    label430:
    for (boolean bool1 = true;; bool1 = false)
    {
      aaa.a((MenuItem)localObject, bool1);
      if (!ConversationListFragment.w(d))
      {
        bool1 = bool4;
        if (ConversationListFragment.u(d).size() != 0) {}
      }
      else
      {
        bool1 = true;
      }
      a(bool1);
      aaa.a(d.getActivity(), l, ConversationListFragment.b(d).getCheckedItemCount(), ConversationListFragment.a(d).getCount());
      return;
      bool1 = false;
      break;
    }
  }
  
  public int a(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    case 2131886158: 
    case 2131886804: 
    case 2131886805: 
    default: 
      return 0;
    }
    return 1;
  }
  
  public boolean a(int paramInt)
  {
    if ((!b) || (paramInt == 0)) {}
    Cursor localCursor;
    do
    {
      do
      {
        return false;
      } while (ConversationListFragment.u(d).size() == 0);
      localCursor = ConversationListFragment.a(d).getCursor();
    } while ((localCursor == null) || (localCursor.getCount() == 0));
    d();
    return true;
  }
  
  public void c()
  {
    if (ConversationListFragment.b(d).getCheckedItemCount() == ConversationListFragment.a(d).getCount()) {
      ConversationListFragment.q(d).f();
    }
    for (;;)
    {
      d();
      return;
      ConversationListFragment.q(d).b();
    }
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    if (!ConversationListFragment.s(d)) {}
    do
    {
      return true;
      switch (paramMenuItem.getItemId())
      {
      default: 
        return true;
      case 2131886158: 
        paramActionMode = ConversationListFragment.u(d);
      }
    } while (paramActionMode.size() == 0);
    d.a(paramActionMode, ConversationListFragment.w(d));
    d.c();
    return true;
    paramActionMode = ConversationListFragment.b(d).getCheckedItemIds();
    paramMenuItem = new ArrayList();
    int m = 0;
    while (m < paramActionMode.length)
    {
      paramMenuItem.add(Long.valueOf(paramActionMode[m]));
      m += 1;
    }
    d.a(paramMenuItem, d.a);
    return true;
    ConversationListFragment.v(d);
    paramActionMode.finish();
    return true;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if ((ConversationListFragment.b(d) == null) || (ConversationListFragment.a(d) == null) || (ConversationListFragment.a(d).getCursor() == null) || (ConversationListFragment.a(d).getCount() == 0)) {
      return false;
    }
    super.onCreateActionMode(paramActionMode, paramMenu);
    ConversationListFragment.m(d).a();
    a(2131951620, paramMenu, d.getActivity());
    f = paramMenu.findItem(2131886804);
    h = paramMenu.findItem(2131886798);
    i = paramMenu.findItem(2131886810);
    e = paramMenu.findItem(2131886805);
    g = paramMenu.findItem(2131886158);
    if (!ConversationListFragment.s(d)) {
      g.setEnabled(false);
    }
    paramMenu = g.getIcon();
    if (ConversationListFragment.s(d)) {}
    for (int m = 255;; m = 127)
    {
      paramMenu.setAlpha(m);
      l = new MultiChoiceView(d.getActivity());
      l.setOnCloseItemClickListener(new jl(this));
      l.setOnSelectAllItemClickListener(new jm(this));
      paramActionMode.setCustomView(l);
      a();
      aaa.a(d.getActivity(), ConversationListFragment.b(d), true);
      ConversationListFragment.a(d).a(false);
      if (ConversationListFragment.b(d).getCheckedItemCount() == 0) {
        d();
      }
      if (ConversationListFragment.t(d) != null) {
        ConversationListFragment.b(d).removeFooterView(ConversationListFragment.t(d));
      }
      return true;
    }
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    super.onDestroyActionMode(paramActionMode);
    j = false;
    k = false;
    aaa.a(d.getActivity(), ConversationListFragment.b(d), false);
    ConversationListFragment.m(d).b();
    ConversationListFragment.a(d).a(true);
    l.setOnCloseItemClickListener(null);
    l.setOnSelectAllItemClickListener(null);
    if (ConversationListFragment.t(d) != null) {
      ConversationListFragment.b(d).addFooterView(ConversationListFragment.t(d));
    }
  }
  
  public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
  {
    d();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    d();
    if (ConversationListFragment.a(d).getCount() > 0) {
      a(ConversationListFragment.b(d).getCheckedItemCount(), ConversationListFragment.a(d).getCount());
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.ConversationListFragment.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */