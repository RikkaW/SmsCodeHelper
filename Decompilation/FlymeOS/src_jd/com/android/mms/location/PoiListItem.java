package com.android.mms.location;

import aaa;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import kn;
import kr;

public class PoiListItem
  extends LinearLayout
{
  private TextView a;
  private ImageView b;
  private TextView c;
  private TextView d;
  private View e;
  private Context f;
  
  public PoiListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    f = paramContext;
  }
  
  public void a(kr paramkr)
  {
    a.setText(paramkr.d());
    c.setText(paramkr.b());
    if (paramkr.g()) {
      b.setVisibility(0);
    }
    for (;;)
    {
      Log.i("Mms/location", "PoiListItem bind suggestLocation.getDistance() = " + paramkr.e());
      if (!paramkr.f()) {
        break;
      }
      d.setText(f.getString(2131493345, new Object[] { Integer.valueOf(0) }));
      e.setVisibility(0);
      return;
      b.setVisibility(4);
    }
    e.setVisibility(8);
    if ((paramkr.e() >= 0.0D) && (paramkr.e() < 500000.0D))
    {
      d.setText(String.format("%s", new Object[] { kn.a(f, paramkr.e()) }));
      return;
    }
    d.setText("");
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    a = ((TextView)findViewById(2131886586));
    b = ((ImageView)findViewById(2131886587));
    c = ((TextView)findViewById(2131886588));
    d = ((TextView)findViewById(2131886589));
    e = findViewById(2131886590);
    aaa.a((TextView)e);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.location.PoiListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */