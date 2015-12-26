package com.android.mms.view;

import acv;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.mms.MmsApp;

public class AttachmentItem
  extends LinearLayout
{
  private static acv[] c = null;
  private ImageView a;
  private TextView b;
  
  public AttachmentItem(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AttachmentItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public AttachmentItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public static void a()
  {
    if (c == null)
    {
      if (MmsApp.a)
      {
        c = new acv[8];
        c[0] = new acv(2131493224, 2130837701, 0);
        c[1] = new acv(2131493225, 2130837709, 1);
        c[2] = new acv(2131493223, 2130837700, 2);
        c[3] = new acv(2131493222, 2130837694, 3);
        c[4] = new acv(2131493322, 2130837718, 4);
        c[5] = new acv(2131493542, 2130837766, 5);
        c[6] = new acv(2131492892, 2130837807, 8);
        c[7] = new acv(2131492890, 2130837767, 6);
      }
    }
    else {
      return;
    }
    if (MmsApp.d)
    {
      c = new acv[5];
      c[0] = new acv(2131493542, 2130837766, 5);
      c[1] = new acv(2131493225, 2130837709, 1);
      c[2] = new acv(2131493223, 2130837700, 2);
      c[3] = new acv(2131493222, 2130837694, 3);
      c[4] = new acv(2131493692, 2130837692, 7);
      return;
    }
    c = new acv[6];
    c[0] = new acv(2131493542, 2130837766, 5);
    c[1] = new acv(2131493225, 2130837709, 1);
    c[2] = new acv(2131493223, 2130837700, 2);
    c[3] = new acv(2131493222, 2130837694, 3);
    c[4] = new acv(2131493322, 2130837718, 4);
    c[5] = new acv(2131493692, 2130837692, 7);
  }
  
  public static int getItemCount()
  {
    return c.length;
  }
  
  public void a(int paramInt)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 1;
    switch (paramInt)
    {
    default: 
      m = 0;
      i = 0;
      j = 0;
    case 0: 
    case 3: 
      for (;;)
      {
        setPadding(j, i, k, m);
        setTag(Integer.valueOf(c[paramInt].c()));
        a.setImageResource(c[paramInt].b());
        b.setText(c[paramInt].a());
        return;
        i = 1;
        j = 1;
        k = 1;
      }
    }
    for (i = 1;; i = 0)
    {
      j = 0;
      break;
      j = 1;
      i = j;
      j = 1;
      k = 1;
      break;
    }
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    a = ((ImageView)findViewById(2131886172));
    b = ((TextView)findViewById(2131886213));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.AttachmentItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */