package com.android.mms.view;

import acy;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.meizu.common.util.LunarCalendar;
import com.meizu.common.widget.ScrollTextView;
import com.meizu.common.widget.ScrollTextView.IDataAdapter;
import com.meizu.common.widget.ScrollTextView.OnScrollTextViewScrollListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CustomTimePicker
  extends FrameLayout
{
  private FrameLayout A;
  private Date B;
  private Date C;
  private Date D;
  private Date E;
  private Date F;
  private Paint G = new Paint();
  private SimpleDateFormat H = new SimpleDateFormat("yyyy-MM-dd");
  private int I;
  private int J;
  private SimpleDateFormat K = new SimpleDateFormat(getContext().getString(2131493708));
  private b L = null;
  private int a = 0;
  private int b = 0;
  private Boolean c = Boolean.valueOf(false);
  private boolean d = true;
  private a e;
  private ScrollTextView f;
  private ScrollTextView g;
  private ScrollTextView h;
  private TextView i;
  private TextView j;
  private final String k;
  private final String l;
  private boolean m = false;
  private boolean n = false;
  private int o = 5;
  private int p;
  private int q;
  private int r;
  private final Calendar s = Calendar.getInstance();
  private int t;
  private int u;
  private int v;
  private int w;
  private int x;
  private int y;
  private int z;
  
  public CustomTimePicker(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CustomTimePicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CustomTimePicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    try
    {
      a = s.get(11);
      b = s.get(12);
      c = Boolean.valueOf(DateFormat.is24HourFormat(paramContext));
      if ((!c.booleanValue()) && (a >= 12))
      {
        a -= 12;
        d = false;
      }
      paramAttributeSet = new DateFormatSymbols().getAmPmStrings();
      k = paramAttributeSet[0];
      l = paramAttributeSet[1];
      u = paramContext.getResources().getDimensionPixelOffset(2131558900);
      t = paramContext.getResources().getDimensionPixelOffset(2131558901);
      w = paramContext.getResources().getDimensionPixelOffset(2131559797);
      v = paramContext.getResources().getDimensionPixelOffset(2131559804);
      b();
      setBackgroundColor(-1);
      return;
    }
    catch (Exception paramAttributeSet)
    {
      for (;;)
      {
        a = 12;
        b = 30;
        c = Boolean.valueOf(true);
      }
    }
  }
  
  private String a(int paramInt)
  {
    String[] arrayOfString = getResources().getStringArray(2131361802);
    if (paramInt > arrayOfString.length - 1) {
      return null;
    }
    return arrayOfString[paramInt];
  }
  
  private void a(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    getContext().getResources().getColor(2131820576);
    if (paramBoolean1) {}
  }
  
  private int b(Date paramDate1, Date paramDate2)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar1.setTime(paramDate1);
    localCalendar2.setTime(paramDate2);
    localCalendar1.set(11, 0);
    localCalendar1.set(12, 0);
    localCalendar1.set(13, 0);
    localCalendar2.set(11, 0);
    localCalendar2.set(12, 0);
    localCalendar2.set(13, 0);
    return ((int)(localCalendar2.getTime().getTime() / 1000L) - (int)(localCalendar1.getTime().getTime() / 1000L)) / 3600 / 24;
  }
  
  private void b()
  {
    if (getChildCount() > 0) {
      removeAllViews();
    }
    getResources().getDisplayMetrics();
    o = 3;
    int i3 = getResources().getDimensionPixelOffset(2131558889);
    int i4 = getResources().getDimensionPixelOffset(2131558881);
    inflate(getContext(), 2130968618, this);
    A = ((FrameLayout)findViewById(2131886242));
    f = ((ScrollTextView)findViewById(2131886249));
    Object localObject1 = f;
    Object localObject2 = new c(1);
    int i5 = a;
    int i1;
    int i2;
    label137:
    label280:
    Object localObject3;
    Object localObject5;
    Object localObject4;
    if (c.booleanValue())
    {
      i1 = 24;
      int i6 = o;
      if (!c.booleanValue()) {
        break label933;
      }
      i2 = 23;
      ((ScrollTextView)localObject1).setData((ScrollTextView.IDataAdapter)localObject2, -1.0F, i5, i1, i6, 0, i2, true);
      f.setSelectItemHeight(i3);
      f.setNormalItemHeight(i4);
      g = ((ScrollTextView)findViewById(2131886252));
      g.setData(new c(2), -1.0F, b, 60, o, 0, 59, true);
      g.setSelectItemHeight(i3);
      g.setNormalItemHeight(i4);
      h = ((ScrollTextView)findViewById(2131886247));
      localObject1 = h;
      localObject2 = new c(3);
      if (!d) {
        break label939;
      }
      i1 = 0;
      ((ScrollTextView)localObject1).setData((ScrollTextView.IDataAdapter)localObject2, -1.0F, i1, 2, o, 0, 1, false);
      h.setTextSize(getContext().getResources().getDimension(2131559803), getContext().getResources().getDimension(2131559807));
      h.setSelectItemHeight(i3);
      h.setNormalItemHeight(i4);
      i = ((TextView)findViewById(2131886250));
      if (i != null) {
        i.setText(2131493375);
      }
      j = ((TextView)findViewById(2131886253));
      if (j != null) {
        j.setText(2131493376);
      }
      e = new a((ScrollTextView)findViewById(2131886245));
      localObject1 = Calendar.getInstance();
      r = ((Calendar)localObject1).get(1);
      q = ((Calendar)localObject1).get(2);
      p = ((Calendar)localObject1).get(5);
      localObject2 = new Date();
      D = ((Date)localObject2);
      ((Calendar)localObject1).setTime((Date)localObject2);
      ((Calendar)localObject1).add(5, 0);
      localObject3 = ((Calendar)localObject1).getTime();
      ((Calendar)localObject1).setTime((Date)localObject2);
      ((Calendar)localObject1).add(1, 1);
      a((Date)localObject3, ((Calendar)localObject1).getTime());
      e.a(i3);
      e.b(i4);
      localObject5 = (LinearLayout)findViewById(2131886243);
      localObject3 = (FrameLayout)findViewById(2131886246);
      localObject4 = (FrameLayout)findViewById(2131886244);
      localObject1 = (FrameLayout)findViewById(2131886248);
      localObject2 = (FrameLayout)findViewById(2131886251);
      if (!c.booleanValue()) {
        break label944;
      }
      ((FrameLayout)localObject3).setVisibility(8);
      localObject3 = (FrameLayout.LayoutParams)((LinearLayout)localObject5).getLayoutParams();
      leftMargin = getResources().getDimensionPixelSize(2131558883);
      rightMargin = getResources().getDimensionPixelSize(2131558885);
      ((LinearLayout)localObject5).setLayoutParams((ViewGroup.LayoutParams)localObject3);
      localObject3 = (LinearLayout.LayoutParams)((FrameLayout)localObject4).getLayoutParams();
      weight = 3.0F;
      leftMargin = getResources().getDimensionPixelSize(2131558871);
      ((FrameLayout)localObject4).setLayoutParams((ViewGroup.LayoutParams)localObject3);
      localObject3 = (LinearLayout.LayoutParams)((FrameLayout)localObject2).getLayoutParams();
      rightMargin = getResources().getDimensionPixelSize(2131558880);
      weight = 4.0F;
      ((FrameLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
      localObject2 = (LinearLayout.LayoutParams)((FrameLayout)localObject1).getLayoutParams();
      rightMargin = getResources().getDimensionPixelSize(2131558874);
      weight = 4.0F;
      ((FrameLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    }
    for (;;)
    {
      d();
      a(y, z, y);
      e.b(v, w);
      g.setTextSize(v, w);
      f.setTextSize(v, w);
      I = getResources().getDimensionPixelSize(2131558876);
      J = getResources().getDimensionPixelSize(2131558878);
      G.setColor(y);
      G.setAntiAlias(true);
      G.setStrokeWidth(getResources().getDimensionPixelSize(2131558877));
      return;
      i1 = 12;
      break;
      label933:
      i2 = 11;
      break label137;
      label939:
      i1 = 1;
      break label280;
      label944:
      ((FrameLayout)localObject3).setVisibility(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)((LinearLayout)localObject5).getLayoutParams();
      leftMargin = getResources().getDimensionPixelSize(2131558882);
      rightMargin = getResources().getDimensionPixelSize(2131558884);
      ((LinearLayout)localObject5).setLayoutParams(localLayoutParams);
      localObject5 = (LinearLayout.LayoutParams)((FrameLayout)localObject4).getLayoutParams();
      weight = 5.0F;
      leftMargin = getResources().getDimensionPixelSize(2131558870);
      ((FrameLayout)localObject4).setLayoutParams((ViewGroup.LayoutParams)localObject5);
      localObject4 = (LinearLayout.LayoutParams)((FrameLayout)localObject2).getLayoutParams();
      rightMargin = getResources().getDimensionPixelSize(2131558879);
      weight = 6.0F;
      ((FrameLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject4);
      localObject2 = (LinearLayout.LayoutParams)((FrameLayout)localObject3).getLayoutParams();
      weight = 6.0F;
      leftMargin = getResources().getDimensionPixelSize(2131558869);
      ((FrameLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject2 = (LinearLayout.LayoutParams)((FrameLayout)localObject1).getLayoutParams();
      rightMargin = getResources().getDimensionPixelSize(2131558873);
      weight = 6.0F;
      ((FrameLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    }
  }
  
  private int[] b(int paramInt)
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).setTime(E);
    ((Calendar)localObject).add(5, paramInt);
    localObject = ((Calendar)localObject).getTime();
    localObject = H.format((Date)localObject).split("-");
    return new int[] { Integer.parseInt(localObject[0]), Integer.parseInt(localObject[1]), Integer.parseInt(localObject[2]) };
  }
  
  private void c()
  {
    int i1;
    if (m)
    {
      i1 = y;
      if (m) {
        break label47;
      }
    }
    label47:
    for (boolean bool = true;; bool = false)
    {
      a(i1, bool, true);
      a(i1, z, i1);
      return;
      i1 = x;
      break;
    }
  }
  
  private void d()
  {
    x = getResources().getColor(2131820575);
    y = getResources().getColor(2131820578);
    z = getResources().getColor(2131820580);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3)
  {
    e.a(paramInt1, paramInt2);
    f.setTextColor(paramInt1, paramInt2);
    g.setTextColor(paramInt1, paramInt2);
    if (h != null) {
      h.setTextColor(paramInt1, paramInt2);
    }
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2, true);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    m = paramBoolean1;
    r = paramInt1;
    q = paramInt2;
    p = paramInt3;
    D = new GregorianCalendar(r, q - 1, p, 0, 0, 0).getTime();
    paramInt1 = b(E, D);
    e.a(paramInt1 + 1, paramBoolean3);
    if (m)
    {
      e.b(t, u);
      return;
    }
    e.b(v, w);
  }
  
  public void a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i2 = 0;
    if (paramBoolean)
    {
      if (a == paramInt1) {
        break label191;
      }
      a = paramInt1;
      paramInt1 = 1;
    }
    for (;;)
    {
      if (b != paramInt2)
      {
        b = paramInt2;
        paramInt2 = 1;
        label37:
        if (paramBoolean != c.booleanValue())
        {
          c = Boolean.valueOf(paramBoolean);
          b();
        }
        if (paramInt1 != 0) {
          f.refreshCurrent(a);
        }
        if (paramInt2 != 0) {
          g.refreshCurrent(b);
        }
        if (h != null)
        {
          ScrollTextView localScrollTextView = h;
          if (d)
          {
            paramInt1 = i2;
            label113:
            localScrollTextView.refreshCurrent(paramInt1);
          }
        }
        else
        {
          return;
          d = true;
          if (a == paramInt1) {
            break label185;
          }
          a = paramInt1;
        }
      }
      label185:
      for (int i1 = 1;; i1 = 0)
      {
        paramInt1 = i1;
        if (a < 12) {
          break;
        }
        a -= 12;
        d = false;
        paramInt1 = i1;
        break;
        paramInt1 = 1;
        break label113;
        paramInt2 = 0;
        break label37;
      }
      label191:
      paramInt1 = 0;
    }
  }
  
  public void a(long paramLong, boolean paramBoolean)
  {
    s.setTimeInMillis(paramLong);
    a(s.get(11), s.get(12), c.booleanValue());
    a(s.get(1), s.get(2) + 1, s.get(5), false, false, paramBoolean);
  }
  
  public void a(Date paramDate1, Date paramDate2)
  {
    B = paramDate1;
    C = paramDate2;
    paramDate1 = Calendar.getInstance();
    paramDate1.setTime(B);
    paramDate1.add(2, -1);
    E = paramDate1.getTime();
    paramDate1.setTime(C);
    paramDate1.add(2, 1);
    F = paramDate1.getTime();
    int i1 = b(E, D);
    int i2 = b(E, F);
    int i3 = b(E, B);
    int i4 = b(E, C);
    e.a(new c(5), -1, i1, i2 + 1, o, i3 + 1, i4 + 1, false);
  }
  
  public void a(int[] paramArrayOfInt)
  {
    int i1 = 1;
    if (m)
    {
      int[] arrayOfInt = new int[3];
      arrayOfInt = LunarCalendar.lunarToSolar(r, q, p, false);
      paramArrayOfInt[0] = arrayOfInt[0];
      paramArrayOfInt[1] = arrayOfInt[1];
      paramArrayOfInt[2] = arrayOfInt[2];
      paramArrayOfInt[3] = getCurrentHour();
      paramArrayOfInt[4] = getCurrentMinute().intValue();
      if (!m) {
        break label101;
      }
    }
    for (;;)
    {
      paramArrayOfInt[5] = i1;
      return;
      paramArrayOfInt[0] = r;
      paramArrayOfInt[1] = q;
      paramArrayOfInt[2] = p;
      break;
      label101:
      i1 = 0;
    }
  }
  
  public boolean a()
  {
    return c.booleanValue();
  }
  
  public int getCurrentHour()
  {
    if (c.booleanValue()) {
      return a;
    }
    if (d) {
      return a;
    }
    return a + 12;
  }
  
  public Integer getCurrentMinute()
  {
    return Integer.valueOf(b);
  }
  
  public long getTimeMillis()
  {
    int[] arrayOfInt = new int[6];
    a(arrayOfInt);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(arrayOfInt[0], arrayOfInt[1] - 1, arrayOfInt[2], arrayOfInt[3], arrayOfInt[4], 0);
    return localCalendar.getTimeInMillis();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i2 = getWidth();
    int i1 = A.getWidth();
    i2 = (i2 - i1) / 2;
    int i3 = getContext().getResources().getDimensionPixelOffset(2131559878);
    paramCanvas.drawLine(i2 + i3, I, i2 + i1 - i3, I, G);
    paramCanvas.drawLine(i2 + i3, J, i2 + i1 - i3, J, G);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    boolean bool3 = true;
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    a(SavedState.a(paramParcelable), SavedState.b(paramParcelable), c.booleanValue());
    int i1 = SavedState.c(paramParcelable);
    int i2 = SavedState.d(paramParcelable);
    int i3 = SavedState.e(paramParcelable);
    boolean bool2;
    if (SavedState.f(paramParcelable) == 1)
    {
      bool1 = true;
      if (SavedState.g(paramParcelable) != 1) {
        break label126;
      }
      bool2 = true;
      label73:
      a(i1, i2, i3, bool1, bool2, false);
      if (SavedState.f(paramParcelable) != 1) {
        break label132;
      }
      i1 = x;
      label99:
      if (SavedState.f(paramParcelable) != 1) {
        break label140;
      }
    }
    label126:
    label132:
    label140:
    for (boolean bool1 = bool3;; bool1 = false)
    {
      a(i1, bool1, false);
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label73;
      i1 = y;
      break label99;
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    return new SavedState(super.onSaveInstanceState(), getCurrentHour(), b, r, q, p, m, n, null);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (!paramBoolean) {
      return;
    }
    paramBoolean = c.booleanValue();
    try
    {
      boolean bool = DateFormat.is24HourFormat(getContext());
      paramBoolean = bool;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        continue;
        e.b(v, w);
        int i1 = y;
      }
    }
    if (paramBoolean != c.booleanValue()) {
      a(getCurrentHour(), b, paramBoolean);
    }
    if (m)
    {
      e.b(t, u);
      i1 = x;
      a(i1, m, false);
      e.a(i1, z);
      f.setTextColor(i1, z);
      g.setTextColor(i1, z);
      h.setTextColor(i1, z);
      return;
    }
  }
  
  public void setCurrentHour(Integer paramInteger)
  {
    if ((paramInteger == null) || (paramInteger.intValue() == getCurrentHour())) {
      return;
    }
    a(paramInteger.intValue(), b, c.booleanValue());
  }
  
  public void setCurrentMinute(Integer paramInteger)
  {
    a(getCurrentHour(), paramInteger.intValue(), c.booleanValue());
  }
  
  public void setLunar(boolean paramBoolean)
  {
    boolean bool2 = false;
    c();
    int[] arrayOfInt1 = new int[4];
    int[] arrayOfInt2 = new int[2];
    arrayOfInt1[0] = r;
    arrayOfInt1[1] = q;
    arrayOfInt1[2] = p;
    arrayOfInt1[3] = 0;
    if (paramBoolean)
    {
      arrayOfInt1 = LunarCalendar.solarToLunar(arrayOfInt1[0], arrayOfInt1[1], arrayOfInt1[2]);
      i1 = arrayOfInt1[0];
      i2 = arrayOfInt1[1];
      i3 = arrayOfInt1[2];
      bool1 = bool2;
      if (arrayOfInt2[1] == 1) {
        bool1 = true;
      }
      a(i1, i2, i3, paramBoolean, bool1);
      return;
    }
    int i1 = arrayOfInt1[0];
    int i2 = arrayOfInt1[1];
    int i3 = arrayOfInt1[2];
    if (arrayOfInt2[1] == 1) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      arrayOfInt1 = LunarCalendar.lunarToSolar(i1, i2, i3, bool1);
      break;
    }
  }
  
  public void setOnScrollTextViewChangedListener(b paramb)
  {
    L = paramb;
  }
  
  public static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new acy();
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      a = paramParcel.readInt();
      b = paramParcel.readInt();
      c = paramParcel.readInt();
      d = paramParcel.readInt();
      e = paramParcel.readInt();
      f = paramParcel.readInt();
      g = paramParcel.readInt();
    }
    
    private SavedState(Parcelable paramParcelable, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean1, boolean paramBoolean2)
    {
      super();
      a = paramInt1;
      b = paramInt2;
      c = paramInt3;
      d = paramInt4;
      e = paramInt5;
      if (paramBoolean1)
      {
        paramInt1 = 1;
        f = paramInt1;
        if (!paramBoolean2) {
          break label67;
        }
      }
      label67:
      for (paramInt1 = i;; paramInt1 = 0)
      {
        g = paramInt1;
        return;
        paramInt1 = 0;
        break;
      }
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(a);
      paramParcel.writeInt(b);
      paramParcel.writeInt(c);
      paramParcel.writeInt(d);
      paramParcel.writeInt(e);
      paramParcel.writeInt(f);
      paramParcel.writeInt(g);
    }
  }
  
  class a
    implements ScrollTextView.OnScrollTextViewScrollListener
  {
    private ScrollTextView b;
    private int c;
    private int d;
    private int e;
    
    public a(ScrollTextView paramScrollTextView)
    {
      b = paramScrollTextView;
    }
    
    public int a()
    {
      return e;
    }
    
    public void a(int paramInt)
    {
      b.setSelectItemHeight(paramInt);
    }
    
    public void a(int paramInt1, int paramInt2)
    {
      b.setTextColor(paramInt1, paramInt2);
    }
    
    public void a(int paramInt, boolean paramBoolean)
    {
      b.setCurrentItem(paramInt, paramBoolean);
    }
    
    public void a(CustomTimePicker.c paramc, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
    {
      c(paramInt3);
      d(paramInt6);
      e(paramInt5);
      b.setData(paramc, paramInt1, paramInt2 + CustomTimePicker.a(CustomTimePicker.this) / 2, paramInt3 + CustomTimePicker.a(CustomTimePicker.this), paramInt4, paramInt5, paramInt6, paramBoolean);
      b.addScrollingListener(this);
    }
    
    public int b()
    {
      return c;
    }
    
    public void b(int paramInt)
    {
      b.setNormalItemHeight(paramInt);
    }
    
    public void b(int paramInt1, int paramInt2)
    {
      b.setTextSize(paramInt1, paramInt2);
    }
    
    public int c()
    {
      return d;
    }
    
    public void c(int paramInt)
    {
      e = paramInt;
    }
    
    public void d(int paramInt)
    {
      c = paramInt;
    }
    
    public void e(int paramInt)
    {
      d = paramInt;
    }
    
    public void onScrollingFinished(ScrollTextView paramScrollTextView)
    {
      int i = Math.max(Math.min(b.getCurrentItem(), b()), c());
      b.setCurrentItem(i, true);
    }
    
    public void onScrollingStarted(ScrollTextView paramScrollTextView) {}
  }
  
  public static abstract interface b
  {
    public abstract void a(int[] paramArrayOfInt);
  }
  
  class c
    implements ScrollTextView.IDataAdapter
  {
    int a = 0;
    
    c(int paramInt)
    {
      a = paramInt;
    }
    
    public String getItemText(int paramInt)
    {
      switch (a)
      {
      case 4: 
      default: 
      case 1: 
      case 2: 
      case 3: 
        do
        {
          return null;
          if (a()) {
            return String.valueOf(paramInt);
          }
          int i = paramInt;
          if (paramInt == 0) {
            i = 12;
          }
          return String.valueOf(i);
          return String.valueOf(paramInt);
          if (paramInt == 0) {
            return CustomTimePicker.c(CustomTimePicker.this);
          }
        } while (paramInt != 1);
        return CustomTimePicker.d(CustomTimePicker.this);
      }
      if (CustomTimePicker.e(CustomTimePicker.this)) {
        return CustomTimePicker.g(CustomTimePicker.this, paramInt);
      }
      paramInt -= CustomTimePicker.a(CustomTimePicker.this) / 2;
      if ((paramInt < 0) || (paramInt >= CustomTimePicker.f(CustomTimePicker.this).a())) {
        return "";
      }
      int[] arrayOfInt = CustomTimePicker.c(CustomTimePicker.this, paramInt);
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(2, arrayOfInt[1] - 1);
      localCalendar.set(5, arrayOfInt[2]);
      localCalendar.set(1, arrayOfInt[0]);
      return CustomTimePicker.g(CustomTimePicker.this).format(localCalendar.getTime());
    }
    
    public void onChanged(View paramView, int paramInt1, int paramInt2)
    {
      boolean bool = true;
      switch (a)
      {
      case 4: 
      default: 
        return;
      case 1: 
        CustomTimePicker.a(CustomTimePicker.this, paramInt2);
      }
      for (;;)
      {
        paramView = new int[6];
        a(paramView);
        if (CustomTimePicker.b(CustomTimePicker.this) == null) {
          break;
        }
        CustomTimePicker.b(CustomTimePicker.this).a(paramView);
        return;
        CustomTimePicker.b(CustomTimePicker.this, paramInt2);
        continue;
        paramView = CustomTimePicker.this;
        if (paramInt2 == 0) {}
        for (;;)
        {
          CustomTimePicker.a(paramView, bool);
          break;
          bool = false;
        }
        paramView = CustomTimePicker.c(CustomTimePicker.this, paramInt2 - CustomTimePicker.a(CustomTimePicker.this) / 2);
        CustomTimePicker.d(CustomTimePicker.this, paramView[0]);
        CustomTimePicker.e(CustomTimePicker.this, paramView[1]);
        CustomTimePicker.f(CustomTimePicker.this, paramView[2]);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.CustomTimePicker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */