package com.android.mms.ui;

import aau;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import arj;
import com.android.mms.MmsApp;
import com.android.mms.view.MessageListItemBase;
import ga;
import lx;
import vv;
import vx;
import vz;
import wa;
import wb;
import wc;
import wd;
import zl;
import zv;

public class MessageListView
  extends ListView
  implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener
{
  private static int b = 0;
  private static int c = 25;
  private static int d = 30;
  private static final float j;
  public boolean a = true;
  private Context e;
  private lx f;
  private a g = a.a;
  private boolean h;
  private c i;
  private final int k;
  private boolean l = false;
  private int m = -1;
  private int n = 0;
  private int o = 0;
  private Runnable p = new wa(this);
  private boolean q = false;
  private b r;
  private MessageListItemBase s;
  private long t = 0L;
  private arj u;
  private e v;
  private d w;
  private int x;
  private boolean y = false;
  
  static
  {
    if (ga.a()) {}
    for (float f1 = 3.5F;; f1 = 2.0F)
    {
      j = f1;
      return;
    }
  }
  
  public MessageListView(Context paramContext)
  {
    super(paramContext);
    e = paramContext;
    k = getOverScrollMode();
  }
  
  public MessageListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    e = paramContext;
    h = ga.C();
    k = getOverScrollMode();
    b();
  }
  
  private boolean a(vv paramvv)
  {
    return (!y) || ((paramvv.m()) && (!wd.c(e, Z)));
  }
  
  private int b(int paramInt)
  {
    return (int)(Math.abs(paramInt) * j + 0.5F);
  }
  
  private boolean b(Cursor paramCursor)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramCursor == null) || (paramCursor.isClosed()) || (v == null)) {
      bool1 = false;
    }
    long l1;
    do
    {
      return bool1;
      l1 = v.c();
      bool1 = bool2;
    } while (l1 > 0L);
    paramCursor.moveToLast();
    do
    {
      bool1 = bool2;
      if (l1 == vx.a(paramCursor.getString(0).equals("mms"), paramCursor.getLong(1))) {
        break;
      }
    } while (paramCursor.moveToPrevious());
    return false;
  }
  
  private void s()
  {
    l = false;
    m = -1;
    n = 0;
    o = 0;
  }
  
  private void t()
  {
    if (w == null) {}
    do
    {
      return;
      if (!m()) {
        setEditMode(a.a);
      }
    } while (v == null);
    v.e();
  }
  
  public void a()
  {
    f.d();
    setChoiceMode(1);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void a(Cursor paramCursor)
  {
    if ((!g()) || (r == null) || (paramCursor == null)) {}
    do
    {
      return;
      if (!a(0)) {
        break;
      }
    } while (b(paramCursor));
    k();
    return;
    r.a(paramCursor);
  }
  
  public void a(MessageListItemBase paramMessageListItemBase, int paramInt, long paramLong)
  {
    paramMessageListItemBase.setSelected(true);
    if (u == null)
    {
      u = new arj(e);
      u.setOutsideTouchable(true);
      u.setAnimationStyle(aau.e("Animation_DropDownUp"));
      u.setOnDismissListener(new wc(this));
    }
    if ((u.isShowing()) && (paramMessageListItemBase != null)) {
      if ((paramMessageListItemBase != v.a()) || (paramInt != v.b()))
      {
        v.a(paramMessageListItemBase, paramInt, paramLong);
        localObject = new RectF(0.0F, 0.0F, paramMessageListItemBase.getMessageBody().getWidth(), paramMessageListItemBase.getMessageBody().getHeight());
        if (((RectF)localObject).height() > b * 2) {
          ((RectF)localObject).inset(0.0F, b);
        }
        u.a(paramMessageListItemBase.getMessageBody(), (RectF)localObject);
      }
    }
    while (paramMessageListItemBase == null)
    {
      if ((w != null) && (w.a(paramMessageListItemBase))) {
        setEditMode(a.b);
      }
      return;
    }
    if (v == null)
    {
      v = new e(paramMessageListItemBase, paramInt, paramLong);
      label228:
      u.a(paramMessageListItemBase.getMessageBody(), v);
      if (!paramMessageListItemBase.getMessageItem().ak()) {
        break label341;
      }
      localObject = paramMessageListItemBase.getMessageItem().al();
    }
    label341:
    for (Object localObject = new RectF(0.0F, 0.0F, ((View)localObject).getWidth(), ((View)localObject).getHeight());; localObject = new RectF(0.0F, 0.0F, paramMessageListItemBase.getMessageBody().getWidth(), paramMessageListItemBase.getMessageBody().getHeight()))
    {
      if (((RectF)localObject).height() > b * 2) {
        ((RectF)localObject).inset(0.0F, b);
      }
      u.a(paramMessageListItemBase.getMessageBody(), (RectF)localObject);
      break;
      v.a(paramMessageListItemBase, paramInt, paramLong);
      break label228;
    }
  }
  
  public void a(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean a(int paramInt)
  {
    return (getChoiceMode() == 1) && (g == a.b);
  }
  
  public boolean a(View paramView, int paramInt, long paramLong)
  {
    return super.performItemClick(paramView, paramInt, paramLong);
  }
  
  public boolean a(View paramView, long paramLong)
  {
    return onItemLongClick(this, paramView, getFirstVisiblePosition() + indexOfChild(paramView), paramLong);
  }
  
  public boolean a(boolean paramBoolean)
  {
    if (a == paramBoolean) {
      return false;
    }
    a = paramBoolean;
    return true;
  }
  
  public void b()
  {
    setOnItemLongClickListener(this);
    setOnItemClickListener(this);
    if (b == 0)
    {
      b = getResources().getDimensionPixelSize(2131558819);
      c = getResources().getDimensionPixelSize(2131558821);
      d = getResources().getDimensionPixelSize(2131558820);
    }
    setOnScrollListener(new vz(this));
  }
  
  public void c()
  {
    setEditMode(a.c);
    setOnItemLongClickListener(null);
    setOnItemClickListener(null);
  }
  
  public void d()
  {
    setEditMode(a.a);
    setOnItemLongClickListener(this);
    setOnItemClickListener(this);
    j();
    post(new wb(this));
  }
  
  public boolean e()
  {
    return false;
  }
  
  public void f()
  {
    f.d();
  }
  
  public boolean g()
  {
    return g != a.a;
  }
  
  public ActionMode getActionMode()
  {
    if (r != null) {
      return r.a();
    }
    return null;
  }
  
  public ComposeMessageActivity getCompose()
  {
    return (ComposeMessageActivity)e;
  }
  
  public boolean h()
  {
    boolean bool = false;
    if (q)
    {
      q = false;
      bool = true;
    }
    return bool;
  }
  
  public boolean i()
  {
    return a;
  }
  
  public void j()
  {
    if (s == null) {}
    do
    {
      return;
      s.c();
      s.setPressed(false);
    } while (s.isShown());
    s.setVisibility(0);
    s = null;
    t = 0L;
  }
  
  public void k()
  {
    if ((u != null) && (u.isShowing())) {
      u.dismiss();
    }
  }
  
  public boolean l()
  {
    return (u != null) && (u.isShowing());
  }
  
  public boolean m()
  {
    return getChoiceMode() == 4;
  }
  
  public boolean n()
  {
    boolean bool = false;
    if (a(0))
    {
      k();
      bool = true;
    }
    return bool;
  }
  
  public void o()
  {
    if ((a(0)) && (v.b() != -1))
    {
      setChoiceMode(4);
      f.b(false);
      if ((v != null) && (!v.d())) {
        setItemChecked(v.b(), true);
      }
    }
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    int i1 = paramInt - getFirstVisiblePosition();
    if ((i1 < 0) || (i1 > getChildCount())) {
      return;
    }
    if (a(paramInt))
    {
      n();
      return;
    }
    ((MessageListItemBase)getChildAt(i1)).E();
  }
  
  public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    int i1 = paramInt - getFirstVisiblePosition();
    if ((i1 < 0) || (i1 > getChildCount())) {
      return false;
    }
    if (getChoiceMode() == 1)
    {
      a((MessageListItemBase)getChildAt(i1), paramInt, paramLong);
      return true;
    }
    return f.a(getChildAt(i1), paramInt, paramLong);
  }
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    }
    Object localObject;
    do
    {
      do
      {
        return super.onKeyShortcut(paramInt, paramKeyEvent);
        localObject = (MessageListItemBase)getSelectedView();
      } while (localObject == null);
      localObject = ((MessageListItemBase)localObject).getMessageItem();
    } while ((localObject == null) || (!((vv)localObject).q()));
    ((ClipboardManager)getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, o));
    return true;
  }
  
  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((g()) || (!l) || (e()) || (h())) {
      return;
    }
    l = false;
    removeCallbacks(p);
    paramInt1 = getLastVisiblePosition();
    if (paramInt1 < 0)
    {
      setOverScrollMode(k);
      return;
    }
    setOverScrollMode(2);
    if (n < 0)
    {
      paramInt1 = b(n);
      smoothScrollBy(-n, paramInt1);
      postDelayed(p, paramInt1);
    }
    for (;;)
    {
      s();
      return;
      if (paramInt1 == m)
      {
        paramInt1 = b(o);
        smoothScrollBy(-o, paramInt1);
        postDelayed(p, paramInt1);
      }
      else
      {
        View localView = getChildAt(m - getFirstVisiblePosition());
        if (localView == null)
        {
          Log.e("MessageListView", "onLayout(), the last View is null when changeSize(), position is " + m);
          setOverScrollMode(k);
          s();
          return;
        }
        paramInt1 = localView.getBottom();
        paramInt1 = o + paramInt4 - paramInt1;
        paramInt2 = b(paramInt1);
        smoothScrollBy(-paramInt1, paramInt2);
        postDelayed(p, paramInt2);
      }
    }
  }
  
  public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((g()) || (e())) {
      return;
    }
    if ((paramInt2 != paramInt4) && (paramInt1 == paramInt3)) {}
    for (boolean bool = true;; bool = false)
    {
      l = bool;
      if (!l) {
        break;
      }
      m = getLastVisiblePosition();
      if (m < 0) {
        break;
      }
      n = (paramInt2 - paramInt4);
      View localView = getChildAt(m - getFirstVisiblePosition());
      if (localView == null) {
        break;
      }
      o = (localView.getBottom() - paramInt4);
      return;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!m()) {
      switch (paramMotionEvent.getActionMasked())
      {
      }
    }
    while ((MmsApp.a) && (zl.a(paramMotionEvent)))
    {
      invalidateViews();
      return true;
      x = ((int)paramMotionEvent.getY());
      Log.d("MessageListView", "MotionEvent.ACTION_DOWN mTouchPositionY = " + x);
      continue;
      if (((int)paramMotionEvent.getY() > x) && (i != null)) {
        i.a();
      }
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performItemClick(View paramView, int paramInt, long paramLong)
  {
    boolean bool2 = true;
    boolean bool1;
    if (((getChoiceMode() == 4) && (getActionMode() != null)) || ((getChoiceMode() == 1) && (g()))) {
      bool1 = super.performItemClick(paramView, paramInt, paramLong);
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (getChoiceMode() != 1);
      bool1 = bool2;
    } while (g());
    return ((MessageListItemBase)getChildAt(paramInt - getFirstVisiblePosition())).E();
  }
  
  public void setDragView(MessageListItemBase paramMessageListItemBase)
  {
    s = paramMessageListItemBase;
    paramMessageListItemBase = paramMessageListItemBase.getMessageItem();
    if (paramMessageListItemBase == null)
    {
      t = 0L;
      return;
    }
    t = vx.a(paramMessageListItemBase.j(), e);
  }
  
  public void setEditMode(a parama)
  {
    g = parama;
  }
  
  public void setFinisMuiltTransientState(boolean paramBoolean)
  {
    q = paramBoolean;
  }
  
  public void setListViewProxy(lx paramlx)
  {
    f = paramlx;
  }
  
  public void setMultiChoiceModeListener(AbsListView.MultiChoiceModeListener paramMultiChoiceModeListener)
  {
    super.setMultiChoiceModeListener(paramMultiChoiceModeListener);
    if ((paramMultiChoiceModeListener instanceof b)) {
      r = ((b)paramMultiChoiceModeListener);
    }
  }
  
  public void setOnListviewMoveCallback(c paramc)
  {
    i = paramc;
  }
  
  public void setPopupActionModeCallback(d paramd)
  {
    w = paramd;
  }
  
  public static enum a
  {
    private a() {}
  }
  
  public static abstract interface b
    extends AbsListView.MultiChoiceModeListener
  {
    public abstract ActionMode a();
    
    public abstract void a(Cursor paramCursor);
  }
  
  public static abstract interface c
  {
    public abstract void a();
  }
  
  public static abstract interface d
  {
    public abstract boolean a(int paramInt, MessageListItemBase paramMessageListItemBase);
    
    public abstract boolean a(View paramView);
  }
  
  public class e
    implements ActionMode.Callback
  {
    private MessageListItemBase b;
    private int c = -1;
    private long d = 0L;
    
    public e(MessageListItemBase paramMessageListItemBase, int paramInt, long paramLong)
    {
      b = paramMessageListItemBase;
      c = paramInt;
      d = paramLong;
    }
    
    public MessageListItemBase a()
    {
      return b;
    }
    
    public void a(MessageListItemBase paramMessageListItemBase, int paramInt, long paramLong)
    {
      b = paramMessageListItemBase;
      c = paramInt;
      d = paramLong;
    }
    
    public int b()
    {
      return c;
    }
    
    public long c()
    {
      return d;
    }
    
    public boolean d()
    {
      return (b == null) || (c == -1) || (d == 0L);
    }
    
    public void e()
    {
      b.setSelected(false);
      b = null;
      c = -1;
      d = 0L;
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      if (MessageListView.e(MessageListView.this) == null) {
        return false;
      }
      MessageListView.e(MessageListView.this).a(paramMenuItem.getItemId(), b);
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if (MessageListView.d(MessageListView.this)) {
        paramMenu.add(4, 4, 1, 2131493358);
      }
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode) {}
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      paramActionMode = b.getMessageItem();
      if (paramActionMode != null)
      {
        Log.d("MessageListView", "msgItem.mMessageType = " + u + ", uri = " + paramActionMode.K() + ", TextUtils.isEmpty(msgItem.mBody) = " + TextUtils.isEmpty(o));
        if ((paramActionMode != null) && (!MessageListView.a(MessageListView.this, paramActionMode)) && (u != 130) && (M != vv.a) && (!paramActionMode.o()) && (!paramActionMode.F()) && (MessageListView.d(MessageListView.this))) {
          break label347;
        }
        paramMenu.removeItem(1);
        label133:
        if ((paramActionMode != null) && (u != 130) && (!paramActionMode.o()) && (!paramActionMode.F()) && (!TextUtils.isEmpty(o))) {
          break label362;
        }
        paramMenu.removeItem(2);
        paramMenu.removeItem(3);
        label185:
        if ((MmsApp.a) || (MmsApp.b)) {
          paramMenu.add(8, 8, 0, 2131493163);
        }
        if ((!MmsApp.d) && (MessageListView.d(MessageListView.this)) && (paramActionMode != null))
        {
          if (!paramActionMode.ad()) {
            break label396;
          }
          paramMenu.add(6, 6, 3, 2131493463);
        }
      }
      for (;;)
      {
        if ((MmsApp.a) && (paramActionMode.q()) && (zv.e >= 1)) {
          paramMenu.add(7, 7, 3, 2131493277);
        }
        if ((MmsApp.b) && (paramActionMode.q()) && (zv.e >= 1)) {
          paramMenu.add(7, 7, 3, 2131493703);
        }
        paramMenu.add(9, 9, 2, 2131493718);
        return true;
        Log.e("MessageListView", "null, null, null");
        break;
        label347:
        paramMenu.add(1, 1, 0, 2131493359);
        break label133;
        label362:
        paramMenu.add(2, 2, 0, 2131493357);
        if (paramActionMode.ak()) {
          break label185;
        }
        paramMenu.add(3, 3, 0, 2131493362);
        break label185;
        label396:
        if (u != 130) {
          paramMenu.add(5, 5, 3, 2131493455);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */