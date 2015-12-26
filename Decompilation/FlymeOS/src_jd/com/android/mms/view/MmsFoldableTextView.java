package com.android.mms.view;

import aau;
import android.content.Context;
import android.content.res.Resources;
import android.text.DynamicLayout;
import android.text.Layout.Alignment;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MmsFoldableTextView
  extends TextView
{
  private String a = null;
  public int b;
  public int c;
  public int d;
  public int e;
  private String f = null;
  private String g;
  private String h;
  private int i = 2;
  private a j;
  private boolean k = false;
  private int l = 0;
  private float m = 0.0F;
  private float n = 0.0F;
  
  public MmsFoldableTextView(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }
  
  public MmsFoldableTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext);
  }
  
  public MmsFoldableTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext);
  }
  
  private void a(Context paramContext)
  {
    h = "‥";
    b = getResources().getDimensionPixelSize(2131559855);
    c = getResources().getDimensionPixelSize(2131559856);
    d = getResources().getDimensionPixelSize(2131559039);
    e = getResources().getDimensionPixelSize(2131559040);
  }
  
  public final int a(boolean paramBoolean)
  {
    if (paramBoolean) {
      return e;
    }
    return c;
  }
  
  public final SpannableStringBuilder a(SpannableStringBuilder paramSpannableStringBuilder, boolean paramBoolean)
  {
    if (l < 3) {}
    for (;;)
    {
      return paramSpannableStringBuilder;
      if ((paramSpannableStringBuilder != null) && (paramSpannableStringBuilder.length() != 0) && ((l > 3) || (paramSpannableStringBuilder.length() > i + 18)))
      {
        int i3 = a(paramBoolean);
        Object localObject;
        Class localClass1;
        Class localClass2;
        Class localClass3;
        Class localClass4;
        Class localClass5;
        Class localClass6;
        Class localClass7;
        int i1;
        TextPaint localTextPaint;
        Layout.Alignment localAlignment;
        TextDirectionHeuristic localTextDirectionHeuristic;
        int i2;
        switch (l)
        {
        default: 
          return paramSpannableStringBuilder;
        case 3: 
          localObject = Integer.TYPE;
          localClass1 = Integer.TYPE;
          localClass2 = Integer.TYPE;
          localClass3 = Float.TYPE;
          localClass4 = Float.TYPE;
          localClass5 = Boolean.TYPE;
          localClass6 = Integer.TYPE;
          localClass7 = Integer.TYPE;
          i1 = paramSpannableStringBuilder.length();
          localTextPaint = getPaint();
          localAlignment = Layout.Alignment.ALIGN_NORMAL;
          localTextDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
          i2 = i;
          localObject = (StaticLayout)aau.a(StaticLayout.class, new Class[] { CharSequence.class, localObject, localClass1, TextPaint.class, localClass2, Layout.Alignment.class, TextDirectionHeuristic.class, localClass3, localClass4, localClass5, TextUtils.TruncateAt.class, localClass6, localClass7 }, new Object[] { paramSpannableStringBuilder, Integer.valueOf(0), Integer.valueOf(i1), localTextPaint, Integer.valueOf(i3), localAlignment, localTextDirectionHeuristic, Float.valueOf(1.0F), Integer.valueOf(0), Boolean.valueOf(false), null, Integer.valueOf(0), Integer.valueOf(i2 + 18 + 1) });
          i1 = ((StaticLayout)localObject).getLineCount();
          if (i1 <= i + 18) {}
          break;
        case 4: 
          int i4;
          do
          {
            i4 = Math.min(i, i1);
            if (i4 <= 0) {
              break;
            }
            i1 = ((StaticLayout)localObject).getLineVisibleEnd(i4 - 1);
            paramSpannableStringBuilder.delete(i1, paramSpannableStringBuilder.length());
            if (!TextUtils.isEmpty(g)) {
              paramSpannableStringBuilder.append(g);
            }
            paramSpannableStringBuilder.append(' ');
            i2 = paramSpannableStringBuilder.length();
            paramSpannableStringBuilder.append(h);
            paramSpannableStringBuilder.setSpan(new b(null), i2, paramSpannableStringBuilder.length(), 33);
            localObject = new DynamicLayout(paramSpannableStringBuilder, getPaint(), i3, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
            if ((i1 <= 0) || (((DynamicLayout)localObject).getLineCount() <= i4)) {
              break label766;
            }
            do
            {
              i1 -= 1;
              paramSpannableStringBuilder.delete(i1, i1 + 1);
              if (i1 <= 0) {
                break;
              }
            } while (((DynamicLayout)localObject).getLineCount() > i4);
            return paramSpannableStringBuilder;
            localObject = Integer.TYPE;
            localClass1 = Integer.TYPE;
            localClass2 = Integer.TYPE;
            localClass3 = Float.TYPE;
            localClass4 = Float.TYPE;
            localClass5 = Boolean.TYPE;
            localClass6 = Integer.TYPE;
            localClass7 = Integer.TYPE;
            i1 = paramSpannableStringBuilder.length();
            localTextPaint = getPaint();
            localAlignment = Layout.Alignment.ALIGN_NORMAL;
            localTextDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            localObject = (StaticLayout)aau.a(StaticLayout.class, new Class[] { CharSequence.class, localObject, localClass1, TextPaint.class, localClass2, Layout.Alignment.class, TextDirectionHeuristic.class, localClass3, localClass4, localClass5, TextUtils.TruncateAt.class, localClass6, localClass7 }, new Object[] { paramSpannableStringBuilder, Integer.valueOf(0), Integer.valueOf(i1), localTextPaint, Integer.valueOf(i3), localAlignment, localTextDirectionHeuristic, Float.valueOf(1.0F), Integer.valueOf(0), Boolean.valueOf(false), null, Integer.valueOf(0), Integer.valueOf(3) });
            i2 = ((StaticLayout)localObject).getLineCount();
            i1 = i2;
          } while (i2 != 0);
          return paramSpannableStringBuilder;
          label766:
          if (k)
          {
            i1 = i2;
            while (((DynamicLayout)localObject).getLineCount() == i4)
            {
              paramSpannableStringBuilder.replace(i1, i1, " ");
              if (((DynamicLayout)localObject).getLineCount() > i4)
              {
                paramSpannableStringBuilder.delete(i1, i1 + 1);
                return paramSpannableStringBuilder;
              }
              i1 += 1;
            }
          }
          break;
        }
      }
    }
  }
  
  public final void a()
  {
    l = 0;
    i = 0;
    h = "‥";
    setFocusable(false);
  }
  
  public void a(int paramInt, a parama)
  {
    l = paramInt;
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
    case 1: 
    case 2: 
      b(-1, null);
      return;
    case 4: 
      a(f, false);
      b(2, parama);
      return;
    }
    a(a, false);
    b(2, parama);
  }
  
  public void a(CharSequence paramCharSequence, boolean paramBoolean)
  {
    if (!paramBoolean) {
      a();
    }
    setText(paramCharSequence);
  }
  
  public void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    k = paramBoolean;
    g = paramString1;
    if (paramString2 != null) {
      h = paramString2;
    }
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    a("‥", paramString, paramBoolean);
  }
  
  public void b(int paramInt, a parama)
  {
    i = paramInt;
    j = parama;
  }
  
  public float getTouchX()
  {
    return m;
  }
  
  public float getTouchY()
  {
    return n;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    if (a == null) {
      a = getResources().getString(2131493491);
    }
    if (f == null) {
      f = getResources().getString(2131493490);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
    {
      m = paramMotionEvent.getX();
      n = paramMotionEvent.getY();
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setTouchX(float paramFloat)
  {
    m = paramFloat;
  }
  
  public void setTouchY(float paramFloat)
  {
    n = paramFloat;
  }
  
  public static abstract interface a
  {
    public abstract boolean a(MmsFoldableTextView paramMmsFoldableTextView);
  }
  
  class b
    extends ClickableSpan
  {
    private b() {}
    
    public void onClick(View paramView)
    {
      if (MmsFoldableTextView.a(MmsFoldableTextView.this) == null) {}
      do
      {
        return;
        MmsFoldableTextView.a(MmsFoldableTextView.this).a(MmsFoldableTextView.this);
      } while (!(getText() instanceof Spannable));
      Selection.setSelection((Spannable)getText(), getText().length());
    }
    
    public void updateDrawState(TextPaint paramTextPaint)
    {
      paramTextPaint.setColor(linkColor);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsFoldableTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */