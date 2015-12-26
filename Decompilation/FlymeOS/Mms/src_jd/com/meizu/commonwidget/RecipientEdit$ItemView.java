package com.meizu.commonwidget;

import aih.d;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecipientEdit$ItemView
  extends LinearLayout
{
  int a;
  int b;
  private TextView c;
  
  public RecipientEdit$ItemView(Context paramContext)
  {
    super(paramContext);
  }
  
  public RecipientEdit$ItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public RecipientEdit$ItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public TextView a()
  {
    return c;
  }
  
  public void a(CharSequence paramCharSequence)
  {
    c.setText(paramCharSequence);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    c = ((TextView)findViewById(aih.d.text));
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
    {
      a = ((int)paramMotionEvent.getX());
      b = ((int)paramMotionEvent.getY());
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setSelected(boolean paramBoolean)
  {
    c.setSelected(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.RecipientEdit.ItemView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */