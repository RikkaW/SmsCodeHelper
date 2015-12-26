package com.android.mms.view;

import com.meizu.common.widget.ScrollTextView;
import com.meizu.common.widget.ScrollTextView.OnScrollTextViewScrollListener;

class CustomTimePicker$a
  implements ScrollTextView.OnScrollTextViewScrollListener
{
  private ScrollTextView b;
  private int c;
  private int d;
  private int e;
  
  public CustomTimePicker$a(CustomTimePicker paramCustomTimePicker, ScrollTextView paramScrollTextView)
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
    b.setData(paramc, paramInt1, paramInt2 + CustomTimePicker.a(a) / 2, paramInt3 + CustomTimePicker.a(a), paramInt4, paramInt5, paramInt6, paramBoolean);
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

/* Location:
 * Qualified Name:     com.android.mms.view.CustomTimePicker.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */