package com.android.mms.changeslot;

import aab;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.mms.MmsApp;
import java.util.HashMap;
import zv;

public class ChangeSlotItem
  extends LinearLayout
  implements View.OnClickListener
{
  private static int d = 0;
  private static int e = 0;
  private static HashMap<Integer, Integer[]> f = null;
  private ImageView a;
  private TextView b;
  private boolean c;
  private int g;
  private int h;
  private a i;
  private Context j;
  
  public ChangeSlotItem(Context paramContext)
  {
    super(paramContext);
    j = paramContext;
  }
  
  public ChangeSlotItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    j = paramContext;
  }
  
  public ChangeSlotItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    j = paramContext;
  }
  
  public ChangeSlotItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    j = paramContext;
  }
  
  private int b(int paramInt)
  {
    Integer[] arrayOfInteger = (Integer[])f.get(Integer.valueOf(paramInt));
    Object localObject = arrayOfInteger;
    if (arrayOfInteger == null) {
      localObject = new Integer[4];
    }
    switch (paramInt)
    {
    default: 
      localObject[0] = Integer.valueOf(2130838670);
      localObject[1] = Integer.valueOf(2130838667);
      localObject[2] = Integer.valueOf(2130838671);
      localObject[3] = Integer.valueOf(2130838668);
      f.put(Integer.valueOf(paramInt), localObject);
      if (MmsApp.n) {
        break label238;
      }
      if (!c) {
        break;
      }
    }
    for (localObject = localObject[1];; localObject = localObject[0])
    {
      return ((Integer)localObject).intValue();
      localObject[0] = Integer.valueOf(2130838639);
      localObject[1] = Integer.valueOf(2130838636);
      localObject[2] = Integer.valueOf(2130838640);
      localObject[3] = Integer.valueOf(2130838637);
      break;
      localObject[0] = Integer.valueOf(2130838648);
      localObject[1] = Integer.valueOf(2130838645);
      localObject[2] = Integer.valueOf(2130838649);
      localObject[3] = Integer.valueOf(2130838646);
      break;
      localObject[0] = Integer.valueOf(2130838660);
      localObject[1] = Integer.valueOf(2130838657);
      localObject[2] = Integer.valueOf(2130838661);
      localObject[3] = Integer.valueOf(2130838658);
      break;
    }
    label238:
    if (c) {}
    for (localObject = localObject[3];; localObject = localObject[2]) {
      return ((Integer)localObject).intValue();
    }
  }
  
  private void b()
  {
    if (d == 0)
    {
      d = j.getResources().getColor(2131820727);
      e = j.getResources().getColor(2131820568);
      f = new HashMap(4);
    }
  }
  
  private String c(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return j.getString(2131493766);
    case -11: 
      return j.getString(2131493805);
    case -10: 
      return j.getString(2131493804);
    case 0: 
      return zv.b(j, 0);
    }
    return zv.b(j, 1);
  }
  
  public void a()
  {
    TextView localTextView = b;
    if (c) {}
    for (int k = d;; k = e)
    {
      localTextView.setTextColor(k);
      return;
    }
  }
  
  public void a(int paramInt)
  {
    h = paramInt;
    if (paramInt == -11) {}
    for (g = zv.h();; g = paramInt)
    {
      a.setImageResource(b(h));
      b.setText(c(h));
      return;
    }
  }
  
  public void onClick(View paramView)
  {
    Log.i("ChangeSlotItem", "onClick mSlot = " + g);
    if (g == -10) {
      aab.a(j, "change_flyme_into_normal_click", "ComposeMessageActivity");
    }
    i.a(g);
    c = true;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    a = ((ImageView)findViewById(2131886222));
    b = ((TextView)findViewById(2131886223));
    b();
    setOnClickListener(this);
  }
  
  public void setChangeSlotItemClickCallback(a parama)
  {
    i = parama;
  }
  
  public void setIsInChoose(boolean paramBoolean)
  {
    c = paramBoolean;
  }
  
  public void setSlot(int paramInt)
  {
    h = paramInt;
    if (paramInt == -11)
    {
      g = zv.h();
      return;
    }
    g = paramInt;
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.changeslot.ChangeSlotItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */