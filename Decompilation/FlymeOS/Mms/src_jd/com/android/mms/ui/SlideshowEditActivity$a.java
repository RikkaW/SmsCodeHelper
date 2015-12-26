package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import lq;
import lr;
import xf;
import xg;

class SlideshowEditActivity$a
  extends ArrayAdapter<lq>
{
  private final Context a;
  private final int b;
  private final LayoutInflater c;
  private final lr d;
  
  public SlideshowEditActivity$a(Context paramContext, int paramInt, lr paramlr)
  {
    super(paramContext, paramInt, paramlr);
    a = paramContext;
    b = paramInt;
    c = LayoutInflater.from(paramContext);
    d = paramlr;
  }
  
  private View a(int paramInt1, View paramView, int paramInt2)
  {
    paramView = (SlideListItemView)c.inflate(paramInt2, null);
    ((TextView)paramView.findViewById(2131886742)).setText(a.getString(2131493121, new Object[] { Integer.valueOf(paramInt1 + 1) }));
    paramInt2 = ((lq)getItem(paramInt1)).a() / 1000;
    ((TextView)paramView.findViewById(2131886743)).setText(a.getResources().getQuantityString(2131427332, paramInt2, new Object[] { Integer.valueOf(paramInt2) }));
    xf localxf = xg.a("SlideshowPresenter", a, paramView, d);
    ((SlideshowPresenter)localxf).setLocation(paramInt1);
    localxf.present(null);
    return paramView;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, b);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowEditActivity.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */