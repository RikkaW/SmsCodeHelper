package com.android.mms.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.android.mms.MmsApp;

public class AttachmentGroupView
  extends GridView
{
  public Context a;
  private boolean b = false;
  private boolean c = true;
  private boolean d = false;
  private b e;
  
  public AttachmentGroupView(Context paramContext)
  {
    this(paramContext, null);
    a = paramContext;
  }
  
  public AttachmentGroupView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
    a = paramContext;
  }
  
  public AttachmentGroupView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a = paramContext;
  }
  
  private void a(int paramInt, Canvas paramCanvas, Paint paramPaint)
  {
    int i = 0;
    View localView;
    if (getNumColumns() == 4)
    {
      i = 0;
      if (i < paramInt)
      {
        localView = getChildAt(i);
        switch (i)
        {
        }
        for (;;)
        {
          i += 1;
          break;
          a(localView, paramCanvas, paramPaint, 3);
          continue;
          a(localView, paramCanvas, paramPaint, 3);
          a(localView, paramCanvas, paramPaint, 0);
          a(localView, paramCanvas, paramPaint, 2);
        }
      }
    }
    else if (i < paramInt)
    {
      localView = getChildAt(i);
      switch (i)
      {
      }
      for (;;)
      {
        i += 1;
        break;
        a(localView, paramCanvas, paramPaint, 2);
      }
    }
  }
  
  private void a(View paramView, Canvas paramCanvas, Paint paramPaint, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      paramCanvas.drawLine(paramView.getLeft(), paramView.getTop(), paramView.getLeft(), paramView.getBottom(), paramPaint);
      return;
    case 1: 
      paramCanvas.drawLine(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getTop(), paramPaint);
      return;
    case 2: 
      paramCanvas.drawLine(paramView.getRight(), paramView.getTop(), paramView.getRight(), paramView.getBottom(), paramPaint);
      return;
    }
    paramCanvas.drawLine(paramView.getLeft(), paramView.getBottom(), paramView.getRight(), paramView.getBottom(), paramPaint);
  }
  
  private void b(int paramInt, Canvas paramCanvas, Paint paramPaint)
  {
    int i = 0;
    View localView;
    if (getNumColumns() == 3)
    {
      i = 0;
      if (i < paramInt)
      {
        localView = getChildAt(i);
        switch (i)
        {
        }
        for (;;)
        {
          i += 1;
          break;
          a(localView, paramCanvas, paramPaint, 3);
          continue;
          a(localView, paramCanvas, paramPaint, 3);
          a(localView, paramCanvas, paramPaint, 0);
          a(localView, paramCanvas, paramPaint, 2);
          continue;
          a(localView, paramCanvas, paramPaint, 3);
          a(localView, paramCanvas, paramPaint, 2);
        }
      }
    }
    else if (i < paramInt)
    {
      localView = getChildAt(i);
      switch (i)
      {
      }
      for (;;)
      {
        i += 1;
        break;
        a(localView, paramCanvas, paramPaint, 2);
      }
    }
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    int i = getChildCount();
    Paint localPaint = new Paint();
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setColor(getContext().getResources().getColor(2131820552));
    if (MmsApp.a)
    {
      a(i, paramCanvas, localPaint);
      return;
    }
    b(i, paramCanvas, localPaint);
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    if (MmsApp.a) {}
    for (int i = 4;; i = 3)
    {
      setNumColumns(i);
      setAdapter(new a());
      return;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (b)
    {
      setVisibility(8);
      paramInt1 = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
      paramInt2 = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setAttachmentGroupViewVisibilityListener(b paramb)
  {
    e = paramb;
  }
  
  public void setIsHide(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  public void setIsKeybordShowing(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  public void setIsShow(boolean paramBoolean)
  {
    c = paramBoolean;
    if (c)
    {
      super.setVisibility(0);
      if (e != null) {
        e.a(0);
      }
    }
  }
  
  public void setSuperVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
  }
  
  public void setVisibility(int paramInt)
  {
    if (paramInt == 0) {
      b = false;
    }
    if (paramInt == getVisibility()) {}
    do
    {
      do
      {
        return;
      } while ((d) && (paramInt == 0));
      super.setVisibility(paramInt);
    } while (e == null);
    e.a(paramInt);
  }
  
  class a
    extends BaseAdapter
  {
    public a()
    {
      AttachmentItem.a();
    }
    
    public int getCount()
    {
      return AttachmentItem.getItemCount();
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null) {}
      for (paramView = (AttachmentItem)LayoutInflater.from(a).inflate(2130968604, null);; paramView = (AttachmentItem)paramView)
      {
        paramView.a(paramInt);
        paramView.setLayoutParams(new AbsListView.LayoutParams(-1, getResources().getDimensionPixelSize(2131558629)));
        return paramView;
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.AttachmentGroupView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */