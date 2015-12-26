package com.android.mms.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import gm;

public class DeliveryReportListItem
  extends LinearLayout
{
  private TextView a;
  private TextView b;
  private TextView c;
  private ImageView d;
  
  DeliveryReportListItem(Context paramContext)
  {
    super(paramContext);
  }
  
  public DeliveryReportListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public final void a(String paramString1, String paramString2, String paramString3)
  {
    Object localObject;
    String str1;
    String str2;
    if (!TextUtils.isEmpty(paramString1))
    {
      a.setText(gm.a(paramString1, false).g());
      b.setText(paramString2);
      localObject = getContext();
      paramString1 = ((Context)localObject).getString(2131493134);
      str1 = ((Context)localObject).getString(2131493129);
      str2 = ((Context)localObject).getString(2131493132);
      localObject = ((Context)localObject).getString(2131493135);
      if (paramString2.compareTo(paramString1) != 0) {
        break label117;
      }
      d.setImageResource(2130837808);
    }
    for (;;)
    {
      if (!TextUtils.isEmpty(paramString3)) {
        break label180;
      }
      c.setVisibility(8);
      return;
      a.setText("");
      break;
      label117:
      if (paramString2.compareTo(str1) == 0) {
        d.setImageResource(2130837810);
      } else if (paramString2.compareTo(str2) == 0) {
        d.setImageResource(2130837811);
      } else if (paramString2.compareTo((String)localObject) == 0) {
        d.setImageResource(2130837810);
      }
    }
    label180:
    c.setVisibility(0);
    c.setText(paramString3);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    a = ((TextView)findViewById(2131886263));
    b = ((TextView)findViewById(2131886264));
    c = ((TextView)findViewById(2131886265));
    d = ((ImageView)findViewById(2131886172));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.DeliveryReportListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */