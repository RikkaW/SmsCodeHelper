package com.android.mms.fragmentstyle;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.meizu.common.util.TabScroller;
import com.meizu.common.widget.SelectionButton;

public class CustomeViewTab
  extends RelativeLayout
  implements View.OnClickListener
{
  private TabLayout a;
  private TabScroller b;
  private TextView c;
  private TextView d;
  private TextView e;
  private TextView f;
  private SelectionButton g;
  private a h;
  private int i;
  private int j;
  
  public CustomeViewTab(Context paramContext)
  {
    super(paramContext);
  }
  
  public CustomeViewTab(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CustomeViewTab(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public SelectionButton getSelectCountBtn()
  {
    return g;
  }
  
  public void onClick(View paramView)
  {
    int m = 0;
    int k = m;
    switch (paramView.getId())
    {
    }
    for (k = m;; k = 1)
    {
      h.a(k, true);
      return;
    }
  }
  
  protected void onFinishInflate()
  {
    a = ((TabLayout)findViewById(2131886762));
    b = a.getTabScroller();
    View localView1 = findViewById(2131886763);
    localView1.setOnClickListener(this);
    c = ((TextView)findViewById(2131886764));
    d = ((TextView)findViewById(2131886765));
    View localView2 = findViewById(2131886766);
    localView2.setOnClickListener(this);
    e = ((TextView)findViewById(2131886767));
    f = ((TextView)findViewById(2131886768));
    b.addTabView(0, localView1);
    b.addTabView(1, localView2);
    g = ((SelectionButton)findViewById(2131886769));
    i = getResources().getColor(2131820727);
    j = getResources().getColor(2131820761);
  }
  
  public void setCallback(a parama)
  {
    h = parama;
  }
  
  public void setCurrentTab(int paramInt)
  {
    b.setCurrentTab(paramInt);
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt, boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.CustomeViewTab
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */