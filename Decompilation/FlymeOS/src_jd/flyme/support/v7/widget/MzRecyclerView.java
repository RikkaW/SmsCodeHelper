package flyme.support.v7.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.LongSparseArray;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.StateSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.Checkable;
import aqq.a;
import ard;
import arg;
import arh;

public class MzRecyclerView
  extends RecyclerView
{
  private a A;
  private Runnable B;
  private boolean C;
  private boolean D;
  private d E;
  private boolean F;
  private boolean G;
  int a;
  ActionMode b;
  c c;
  int d;
  SparseBooleanArray e;
  LongSparseArray<Integer> f;
  protected Rect g;
  protected int h;
  boolean i;
  Drawable j;
  Rect k;
  int l;
  int m;
  int n;
  int o;
  private boolean w;
  private e x;
  private f y;
  private GestureDetector z;
  
  private void a(MotionEvent paramMotionEvent)
  {
    RecyclerView.h localh = getLayoutManager();
    if (((localh instanceof LinearLayoutManager)) && (((LinearLayoutManager)localh).e() == 1))
    {
      if (getScrollY() != 0) {
        a(true);
      }
      for (;;)
      {
        int i1 = paramMotionEvent.getActionMasked();
        if ((i1 == 0) || (i1 == 2)) {
          f((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
        }
        if (i1 == 3) {
          C = false;
        }
        return;
        a(false);
      }
    }
    C = false;
    D = false;
    E = null;
  }
  
  private int f(View paramView)
  {
    if (paramView == null) {
      return -1;
    }
    return c(paramView);
  }
  
  private void f(int paramInt1, int paramInt2)
  {
    if ((g != null) && (g.contains(paramInt1, paramInt2)))
    {
      C = true;
      return;
    }
    C = false;
  }
  
  private int getChildCountExt()
  {
    if (r != null) {
      return r.b();
    }
    return 0;
  }
  
  private int getFirstPosition()
  {
    View localView = a(0);
    if (localView == null) {
      return -1;
    }
    return f(localView);
  }
  
  private int getItemCount()
  {
    RecyclerView.a locala = getAdapter();
    if (locala != null) {
      return locala.a();
    }
    return 0;
  }
  
  private int getLastPosition()
  {
    View localView = a(getChildCountExt() - 1);
    if (localView == null) {
      return -1;
    }
    return f(localView);
  }
  
  private void v()
  {
    int i2 = getChildCountExt();
    int i1 = 0;
    while (i1 < i2)
    {
      View localView = a(i1);
      b(localView, f(localView));
      i1 += 1;
    }
  }
  
  private void w()
  {
    if ((a == 5) && (b == null) && (B == null))
    {
      B = new arg(this);
      post(B);
    }
  }
  
  private void x()
  {
    setSelector(getResources().getDrawable(aqq.a.mz_recyclerview_selector));
  }
  
  public View a(int paramInt)
  {
    if (r != null) {
      return r.b(paramInt);
    }
    return null;
  }
  
  void a()
  {
    if (t.e())
    {
      c();
      setPressed(false);
      if (j != null) {
        j.jumpToCurrentState();
      }
    }
    super.a();
    w();
  }
  
  protected void a(Canvas paramCanvas)
  {
    if (!k.isEmpty())
    {
      Drawable localDrawable = j;
      localDrawable.setBounds(k);
      localDrawable.draw(paramCanvas);
    }
  }
  
  void a(View paramView, int paramInt)
  {
    b(paramView, paramInt);
  }
  
  void a(boolean paramBoolean)
  {
    if (g == null)
    {
      g = new Rect();
      g.setEmpty();
    }
    if ((a != 4) && (a != 5)) {
      g.setEmpty();
    }
    while ((!g.isEmpty()) && (!paramBoolean)) {
      return;
    }
    g.right = (getWidth() - getPaddingRight());
    g.left = (g.right - h);
    g.top = getPaddingTop();
    g.bottom = (getHeight() - getPaddingBottom());
  }
  
  public void b()
  {
    if (e != null) {
      e.clear();
    }
    if (f != null) {
      f.clear();
    }
    d = 0;
  }
  
  public void b(View paramView, int paramInt)
  {
    if (paramView == null) {}
    boolean bool2;
    do
    {
      do
      {
        return;
      } while ((a == 0) || (e == null));
      bool2 = e.get(paramInt);
      if ((paramView instanceof Checkable))
      {
        ((Checkable)paramView).setChecked(bool2);
        return;
      }
    } while (getContextgetApplicationInfotargetSdkVersion < 11);
    View localView;
    if ((a == 4) || (a == 5))
    {
      localView = paramView.findViewById(16908289);
      if ((localView != null) && ((localView instanceof Checkable))) {
        if (b == null) {
          break label173;
        }
      }
    }
    label173:
    for (boolean bool1 = true;; bool1 = false)
    {
      ((Checkable)localView).setChecked(bool1);
      if (w) {
        Log.i("MzRecyclerView", "setViewChecked position = " + paramInt + " checked = " + bool2);
      }
      paramView.setActivated(bool2);
      return;
    }
  }
  
  void c()
  {
    RecyclerView.a locala = getAdapter();
    if ((a == 0) || (locala == null) || (!locala.b())) {
      return;
    }
    int i5 = getItemCount();
    e.clear();
    int i1 = 0;
    int i2 = 0;
    long l1;
    int i6;
    int i3;
    if (i1 < f.size())
    {
      l1 = f.keyAt(i1);
      i6 = ((Integer)f.valueAt(i1)).intValue();
      if (l1 != locala.b(i6))
      {
        i3 = Math.max(0, i6 - 20);
        i4 = Math.min(i6 + 20, i5);
        label116:
        if (i3 >= i4) {
          break label274;
        }
        if (l1 == locala.b(i3))
        {
          e.put(i3, true);
          f.setValueAt(i1, Integer.valueOf(i3));
        }
      }
    }
    label274:
    for (int i4 = 1;; i4 = 0)
    {
      i3 = i1;
      if (i4 == 0)
      {
        f.delete(l1);
        d -= 1;
        if ((b != null) && (c != null)) {
          c.a(b, i6, l1, false);
        }
        i3 = i1 - 1;
        i2 = 1;
      }
      for (i1 = i2;; i1 = i2)
      {
        i2 = i1;
        i1 = i3 + 1;
        break;
        i3 += 1;
        break label116;
        e.put(i6, true);
        i3 = i1;
      }
      if ((i2 == 0) || (b == null)) {
        break;
      }
      b.invalidate();
      return;
    }
  }
  
  public boolean d()
  {
    if (((a == 5) || (a == 4)) && (b == null)) {
      b = startActionMode(c);
    }
    return b != null;
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    boolean bool = i;
    if (!bool) {
      a(paramCanvas);
    }
    super.dispatchDraw(paramCanvas);
    if (bool) {
      a(paramCanvas);
    }
  }
  
  protected void dispatchSetPressed(boolean paramBoolean) {}
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    e();
  }
  
  void e()
  {
    if (j != null)
    {
      if (f()) {
        j.setState(getDrawableState());
      }
    }
    else {
      return;
    }
    j.setState(StateSet.NOTHING);
  }
  
  boolean f()
  {
    return ((isFocused()) && (!isInTouchMode())) || (isPressed());
  }
  
  public int getCheckedItemCount()
  {
    return d;
  }
  
  public long[] getCheckedItemIds()
  {
    int i1 = 0;
    Object localObject;
    if ((a == 0) || (f == null) || (getAdapter() == null))
    {
      localObject = new long[0];
      return (long[])localObject;
    }
    LongSparseArray localLongSparseArray = f;
    int i2 = localLongSparseArray.size();
    long[] arrayOfLong = new long[i2];
    for (;;)
    {
      localObject = arrayOfLong;
      if (i1 >= i2) {
        break;
      }
      arrayOfLong[i1] = localLongSparseArray.keyAt(i1);
      i1 += 1;
    }
  }
  
  public SparseBooleanArray getCheckedItemPositions()
  {
    if (a != 0) {
      return e;
    }
    return null;
  }
  
  public int getChoiceMode()
  {
    return a;
  }
  
  public final e getOnItemClickListener()
  {
    return x;
  }
  
  public final f getOnItemLongClickListener()
  {
    return y;
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    if (j != null) {
      j.jumpToCurrentState();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (j == null) {
      x();
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    a(true);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    a(paramMotionEvent);
    if ((z.onTouchEvent(paramMotionEvent)) || (F)) {
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setAdapter(RecyclerView.a parama)
  {
    super.setAdapter(parama);
    if (parama != null)
    {
      boolean bool = getAdapter().b();
      if ((a != 0) && (bool) && (f == null)) {
        f = new LongSparseArray();
      }
    }
    b();
  }
  
  public void setCheckBoxIsAnimation(boolean paramBoolean)
  {
    if (G != paramBoolean) {
      G = paramBoolean;
    }
  }
  
  public void setChoiceMode(int paramInt)
  {
    a = paramInt;
    if (b != null)
    {
      b.finish();
      b = null;
    }
    if (a != 0)
    {
      if (e == null) {
        e = new SparseBooleanArray(0);
      }
      RecyclerView.a locala = getAdapter();
      if ((f == null) && (locala != null) && (locala.b())) {
        f = new LongSparseArray(0);
      }
      if (a == 4)
      {
        b();
        setLongClickable(true);
      }
    }
  }
  
  public void setDelayTopOverScrollEnabled(boolean paramBoolean) {}
  
  public void setDelayTopOverScrollOffset(int paramInt) {}
  
  public void setDrawSelectorOnTop(boolean paramBoolean)
  {
    i = paramBoolean;
  }
  
  public void setEnableDragSelection(d paramd)
  {
    setEnableDragSelection(true);
    E = paramd;
  }
  
  public void setEnableDragSelection(boolean paramBoolean)
  {
    D = paramBoolean;
  }
  
  public void setItenFilter(a parama)
  {
    A = parama;
  }
  
  public void setMultiChoiceModeListener(b paramb)
  {
    if (c == null) {
      c = new c();
    }
    c.a(paramb);
  }
  
  public void setOnItemClickListener(e parame)
  {
    x = parame;
  }
  
  public void setOnItemLongClickListener(f paramf)
  {
    if (!isLongClickable()) {
      setLongClickable(true);
    }
    y = paramf;
  }
  
  public void setSelector(int paramInt)
  {
    setSelector(getResources().getDrawable(paramInt));
  }
  
  public void setSelector(Drawable paramDrawable)
  {
    if (j != null)
    {
      j.setCallback(null);
      unscheduleDrawable(j);
    }
    j = paramDrawable;
    Rect localRect = new Rect();
    paramDrawable.getPadding(localRect);
    l = left;
    m = top;
    n = right;
    o = bottom;
    paramDrawable.setCallback(this);
    e();
  }
  
  public boolean verifyDrawable(Drawable paramDrawable)
  {
    return (j == paramDrawable) || (super.verifyDrawable(paramDrawable));
  }
  
  public static class MZSavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<MZSavedState> CREATOR = new arh();
    boolean a;
    int b;
    SparseBooleanArray c;
    LongSparseArray<Integer> d;
    
    private MZSavedState(Parcel paramParcel)
    {
      super();
      if (paramParcel.readByte() != 0) {}
      for (boolean bool = true;; bool = false)
      {
        a = bool;
        b = paramParcel.readInt();
        c = paramParcel.readSparseBooleanArray();
        int j = paramParcel.readInt();
        if (j <= 0) {
          break;
        }
        d = new LongSparseArray();
        while (i < j)
        {
          long l = paramParcel.readLong();
          int k = paramParcel.readInt();
          d.put(l, Integer.valueOf(k));
          i += 1;
        }
      }
    }
    
    public String toString()
    {
      return "MzRecyclerView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checkState=" + c + "}";
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      int i = 0;
      super.writeToParcel(paramParcel, paramInt);
      if (a)
      {
        paramInt = 1;
        paramParcel.writeByte((byte)paramInt);
        paramParcel.writeInt(b);
        paramParcel.writeSparseBooleanArray(c);
        if (d == null) {
          break label106;
        }
      }
      label106:
      for (paramInt = d.size();; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        while (i < paramInt)
        {
          paramParcel.writeLong(d.keyAt(i));
          paramParcel.writeInt(((Integer)d.valueAt(i)).intValue());
          i += 1;
        }
        paramInt = 0;
        break;
      }
    }
  }
  
  public static abstract interface a {}
  
  public static abstract interface b
    extends ActionMode.Callback
  {
    public abstract void a(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean);
  }
  
  class c
    implements MzRecyclerView.b
  {
    private MzRecyclerView.b b;
    
    c() {}
    
    public void a(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      b.a(paramActionMode, paramInt, paramLong, paramBoolean);
      if ((getCheckedItemCount() == 0) && (a == 5)) {}
    }
    
    public void a(MzRecyclerView.b paramb)
    {
      b = paramb;
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return b.onActionItemClicked(paramActionMode, paramMenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if (b.onCreateActionMode(paramActionMode, paramMenu))
      {
        if ((a == 4) || (a == 5))
        {
          setLongClickable(true);
          return true;
        }
        setLongClickable(false);
        return true;
      }
      return false;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      b.onDestroyActionMode(paramActionMode);
      b = null;
      b();
      MzRecyclerView.a(MzRecyclerView.this);
      setLongClickable(true);
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return b.onPrepareActionMode(paramActionMode, paramMenu);
    }
  }
  
  public static abstract interface d {}
  
  public static abstract interface e {}
  
  public static abstract interface f {}
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.MzRecyclerView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */