package com.android.mms.view;

import aaa;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.meizu.common.widget.AnimCheckBox;
import com.meizu.common.widget.AnimCheckBox.UpdateListener;
import gb.a;
import gr;
import zv;

public class MmsGridLayout
  extends ViewGroup
  implements AnimCheckBox.UpdateListener
{
  private int A = 0;
  private int B = 0;
  private int C;
  private int D;
  private int E;
  private int F;
  private int G;
  private int H;
  private int I;
  private int J;
  private int K;
  private int L;
  private int M;
  private int N;
  private boolean O = true;
  private float P = 0.0F;
  private View a;
  private int b = 0;
  protected MmsTextViewSnippet c;
  protected MmsTextViewSnippet d;
  protected TextView e;
  protected TextView f;
  protected AnimCheckBox g;
  protected ImageView h;
  protected ViewGroup i;
  protected ViewGroup j;
  protected TextView k;
  protected ImageView l;
  protected int m = 0;
  protected int n = 30;
  protected int o = 10;
  protected int p = 2;
  protected int q = 0;
  protected int r = 0;
  protected boolean s = true;
  protected int t = 0;
  protected int u = 0;
  protected int v = 0;
  protected int w = 0;
  protected int x;
  protected Handler y = new Handler();
  private int z = 0;
  
  public MmsGridLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public MmsGridLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0, 0);
  }
  
  public MmsGridLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1);
    paramContext = getContext().obtainStyledAttributes(paramAttributeSet, gb.a.MmsGridLayout, paramInt1, 0);
    n = paramContext.getDimensionPixelSize(1, 0);
    o = paramContext.getDimensionPixelSize(2, 0);
    p = paramContext.getInteger(3, 0);
    q = paramContext.getDimensionPixelSize(4, 0);
    r = 0;
    s = paramContext.getBoolean(6, true);
    t = paramContext.getDimensionPixelSize(7, 0);
    u = paramContext.getDimensionPixelSize(8, 0);
    v = paramContext.getDimensionPixelSize(9, 0);
    w = paramContext.getDimensionPixelSize(11, 0);
    A = getResources().getDimensionPixelSize(2131558828);
    B = getResources().getDimensionPixelSize(2131558833);
    m = getResources().getDimensionPixelSize(2131559047);
    x = getResources().getDimensionPixelSize(2131558985);
    F = getResources().getDimensionPixelSize(2131558829);
    G = getResources().getDimensionPixelSize(2131558835);
    H = getResources().getDimensionPixelSize(2131558830);
    J = getResources().getDimensionPixelSize(2131558834);
    K = getResources().getDimensionPixelSize(2131558849);
    L = getResources().getDimensionPixelSize(2131558831);
    M = getResources().getDimensionPixelSize(2131558853);
    paramContext.recycle();
    paramContext = getContext().obtainStyledAttributes(paramAttributeSet, gb.a.fontstyle, paramInt1, 0);
    E = paramContext.getDimensionPixelSize(20, 0);
    b = paramContext.getDimensionPixelSize(18, 0);
    z = paramContext.getDimensionPixelSize(19, 0);
    I = paramContext.getDimensionPixelSize(21, 0);
    C = paramContext.getDimensionPixelOffset(16, 0);
    D = paramContext.getDimensionPixelSize(17, 0);
    N = paramContext.getDimensionPixelSize(22, 0);
    O = paramContext.getBoolean(15, true);
    paramContext.recycle();
  }
  
  private int a(boolean paramBoolean)
  {
    if (P > 0.0F)
    {
      int i2 = g.getMeasuredWidth() + H;
      int i1 = i2;
      if (paramBoolean)
      {
        float f1 = P;
        i1 = (int)(i2 * f1);
      }
      return i1;
    }
    return 0;
  }
  
  protected void a(int paramInt)
  {
    if (paramInt <= 0) {
      j.setVisibility(4);
    }
    if (paramInt <= 9)
    {
      j.findViewById(2131886634).setBackgroundResource(2130837756);
      ((TextView)j.findViewById(2131886213)).setText(paramInt + "");
    }
    for (;;)
    {
      ViewGroup localViewGroup1 = j;
      ViewGroup localViewGroup2 = j;
      localViewGroup1.setVisibility(0);
      return;
      if (paramInt <= 99)
      {
        j.findViewById(2131886634).setBackgroundResource(2130837757);
        ((TextView)j.findViewById(2131886213)).setText(paramInt + "");
      }
      else
      {
        j.findViewById(2131886634).setBackgroundResource(2130837758);
        ((TextView)j.findViewById(2131886213)).setText("");
      }
    }
  }
  
  protected void a(gr paramgr)
  {
    if ((paramgr.a()) || (paramgr.z())) {}
    for (boolean bool = true;; bool = false)
    {
      zv.a(l, 0, paramgr.w(), bool);
      return;
    }
  }
  
  protected void a(CharSequence paramCharSequence)
  {
    if (d())
    {
      e.setVisibility(8);
      c.setMaxLines(1);
      c.setVisibility(0);
      String str = getResources().getString(2131493847);
      paramCharSequence = new SpannableString(str + paramCharSequence);
      paramCharSequence.setSpan(new ForegroundColorSpan(e.getTextColors().getDefaultColor()), 0, str.length(), 33);
      c.setText(paramCharSequence);
      return;
    }
    e.setVisibility(0);
    e.setText(getResources().getString(2131493837));
    c.setVisibility(0);
    c.setMaxLines(1);
    c.setText(paramCharSequence);
  }
  
  protected void a(boolean paramBoolean, CharSequence paramCharSequence)
  {
    if (paramBoolean)
    {
      e.setVisibility(0);
      e.setText(getResources().getString(2131493700));
      c.setVisibility(8);
      return;
    }
    e.setVisibility(8);
    c.setText(paramCharSequence);
    c.setVisibility(0);
    paramCharSequence = c;
    if (d()) {}
    for (int i1 = 1;; i1 = 2)
    {
      paramCharSequence.setMaxLines(i1);
      return;
    }
  }
  
  protected boolean a(View paramView)
  {
    return (paramView != null) && (paramView.getVisibility() == 0);
  }
  
  protected boolean d()
  {
    return MmsApp.n;
  }
  
  public void getUpdateTransition(float paramFloat)
  {
    P = paramFloat;
    requestLayout();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(getContext(), 2130968762, this);
    h = ((ImageView)findViewById(2131886621));
    i = ((ViewGroup)findViewById(2131886632));
    d = ((MmsTextViewSnippet)findViewById(16908308));
    if (t > 0) {
      d.setTextSize(0, t);
    }
    j = ((ViewGroup)findViewById(2131886633));
    c = ((MmsTextViewSnippet)findViewById(16908309));
    if (p > 0) {
      c.setMaxLines(p);
    }
    if (u > 0) {
      c.setTextSize(0, u);
    }
    f = ((TextView)findViewById(2131886451));
    if (v > 0) {
      f.setTextSize(0, v);
    }
    g = ((AnimCheckBox)findViewById(16908289));
    g.setUpdateListner(this);
    k = ((TextView)findViewById(2131886635));
    aaa.a(k);
    l = ((ImageView)findViewById(2131886624));
    a = findViewById(2131886255);
    e = ((TextView)findViewById(2131886636));
    ViewGroup localViewGroup = i;
    if (O) {}
    for (int i1 = 0;; i1 = 8)
    {
      localViewGroup.setVisibility(i1);
      setPadding(0, 0, 0, 0);
      return;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i6 = getPaddingTop();
    int i7 = getPaddingBottom();
    int i1 = getPaddingLeft();
    i1 = b + i1;
    int i3 = paramInt3 - paramInt1 - getPaddingRight() - z;
    int i8 = getPaddingTop();
    int i4 = paramInt4 - paramInt2 - getPaddingBottom();
    int i5 = i3 - a(true);
    if (a(i))
    {
      paramInt1 = F + i8;
      i.layout(i1, paramInt1, h.getMeasuredWidth() + i1, h.getMeasuredHeight() + paramInt1);
    }
    int i2;
    if (a(d))
    {
      if (a(i))
      {
        paramInt1 = i.getRight() + A;
        i2 = G + i8;
        if (!d())
        {
          paramInt3 = i2;
          if (!a(c)) {
            break label308;
          }
          if (c.getLineCount() <= 1)
          {
            paramInt3 = i2;
            if (!a(e)) {
              break label308;
            }
          }
        }
        double d1 = i8;
        int i9 = d.getMeasuredHeight();
        int i10 = J;
        if (!a(c)) {
          break label1023;
        }
        paramInt3 = c.getMeasuredHeight() + I;
        label255:
        if (!a(e)) {
          break label1029;
        }
        i2 = e.getMeasuredHeight() + I;
        label280:
        paramInt3 = (int)(d1 + 0.5D * (i4 - i8 - i9 - i10 - paramInt3 - i2));
        label308:
        d.layout(paramInt1, paramInt3, d.getMeasuredWidth() + paramInt1, d.getMeasuredHeight() + paramInt3);
      }
    }
    else
    {
      paramInt1 = 0;
      paramInt3 = (d.getMeasuredHeight() - k.getMeasuredHeight()) / 2;
      if (a(k))
      {
        paramInt1 = d.getRight() + B;
        paramInt3 += d.getTop();
        k.layout(paramInt1, paramInt3, k.getMeasuredWidth() + paramInt1, k.getMeasuredHeight() + paramInt3);
        paramInt1 = B + k.getMeasuredWidth();
      }
      if (a(l))
      {
        paramInt3 = (d.getMeasuredHeight() - l.getMeasuredHeight()) / 2;
        paramInt1 = paramInt1 + d.getRight() + B;
        paramInt3 += d.getTop();
        l.layout(paramInt1, paramInt3, l.getMeasuredWidth() + paramInt1, l.getMeasuredHeight() + paramInt3);
      }
      if (a(g))
      {
        paramInt1 = i3 - g.getMeasuredWidth();
        paramInt2 = 0 + (paramInt4 - paramInt2 - i6 - i7 - 0 - g.getMeasuredHeight()) / 2;
        g.layout(paramInt1, paramInt2, g.getMeasuredWidth() + paramInt1, g.getMeasuredHeight() + paramInt2);
      }
      if (a(f))
      {
        paramInt1 = i5 - f.getMeasuredWidth();
        paramInt2 = d.getTop() + (d.getMeasuredHeight() - f.getMeasuredHeight()) / 2;
        f.layout(paramInt1, paramInt2, f.getMeasuredWidth() + paramInt1, f.getMeasuredHeight() + paramInt2);
      }
      if (a(e)) {
        if (!a(i)) {
          break label1041;
        }
      }
    }
    label1023:
    label1029:
    label1035:
    label1041:
    for (paramInt1 = i.getRight() + A;; paramInt1 = i1)
    {
      paramInt2 = d.getBottom() + I;
      e.layout(paramInt1, paramInt2, e.getMeasuredWidth() + paramInt1, e.getMeasuredHeight() + paramInt2);
      if (a(c))
      {
        if (a(i))
        {
          paramInt1 = i.getRight();
          i1 = A + paramInt1;
        }
        paramInt1 = d.getBottom() + I;
        if (a(e)) {
          paramInt1 = e.getBottom() + M;
        }
        c.layout(i1, paramInt1, c.getMeasuredWidth() + i1, c.getMeasuredHeight() + paramInt1);
      }
      if (a(j))
      {
        paramInt2 = i5 - j.getMeasuredWidth();
        paramInt1 = f.getBottom() + K;
        if (d()) {
          paramInt1 = (int)(c.getTop() + 0.5D * (c.getMeasuredHeight() - j.getMeasuredHeight()));
        }
        j.layout(paramInt2, paramInt1, j.getMeasuredWidth() + paramInt2, j.getMeasuredHeight() + paramInt1);
      }
      if (a(a))
      {
        paramInt1 = d.getLeft();
        paramInt3 = J;
        if (!d()) {
          break label1035;
        }
        paramInt1 = N;
      }
      for (paramInt2 = z + i3 - N;; paramInt2 = i3)
      {
        a.layout(paramInt1, i4 - paramInt3, paramInt2, i4);
        return;
        paramInt1 = i1;
        break;
        paramInt3 = 0;
        break label255;
        i2 = 0;
        break label280;
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i2 = resolveSize(0, paramInt1);
    int i3 = i2 - getPaddingLeft() - getPaddingRight() - b - z;
    if (a(j))
    {
      j.measure(View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(E, Integer.MIN_VALUE));
      j.getMeasuredWidth();
      paramInt1 = L;
    }
    if (a(i)) {
      h.measure(View.MeasureSpec.makeMeasureSpec(x, 1073741824), View.MeasureSpec.makeMeasureSpec(x, 1073741824));
    }
    if (a(f)) {
      f.measure(View.MeasureSpec.makeMeasureSpec(i3, 0), View.MeasureSpec.makeMeasureSpec(E, Integer.MIN_VALUE));
    }
    for (paramInt1 = f.getMeasuredWidth() + L;; paramInt1 = 0)
    {
      if (a(k)) {
        k.measure(View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(E, Integer.MIN_VALUE));
      }
      for (paramInt2 = k.getMeasuredWidth() + B;; paramInt2 = 0)
      {
        if (a(l)) {
          l.measure(View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(E, Integer.MIN_VALUE));
        }
        for (int i1 = l.getMeasuredWidth() + B;; i1 = 0)
        {
          g.measure(View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(E, Integer.MIN_VALUE));
          i3 = D - a(false);
          if (a(c)) {
            c.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(E, 0));
          }
          if (a(e)) {
            e.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(E, 0));
          }
          i3 = C;
          int i4 = a(false);
          d.measure(View.MeasureSpec.makeMeasureSpec(i3 - paramInt2 - i1 - paramInt1 - i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(E, Integer.MIN_VALUE));
          a.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(J, Integer.MIN_VALUE));
          setMeasuredDimension(i2, E);
          return;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsGridLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */