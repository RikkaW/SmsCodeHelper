package com.android.mms.view;

import aaa;
import android.content.Context;
import android.content.res.Resources;
import android.telephony.MzTelephony.MmsExt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagePopupService.b;
import gm;
import gq;
import gr;
import wd;
import zm;
import zv;

public class MessagePopupTipView
  extends FrameLayout
  implements View.OnClickListener
{
  ViewGroup a;
  TextView b;
  TextView c;
  View d;
  String e;
  ImageView f;
  ImageView g;
  View h;
  private a i;
  private Context j;
  
  public MessagePopupTipView(Context paramContext)
  {
    super(paramContext);
    j = paramContext;
  }
  
  public MessagePopupTipView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    j = paramContext;
  }
  
  public MessagePopupTipView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    j = paramContext;
  }
  
  public void a(MessagePopupService.b paramb)
  {
    Object localObject = gr.a(MmsApp.c(), b, false).h();
    gm localgm = (gm)((gq)localObject).get(0);
    e = localgm.d();
    b.setText(localgm.g());
    f.setImageBitmap(aaa.a(j, (gq)localObject, aaa.a, aaa.b));
    localObject = f;
    c.setText((CharSequence)localObject);
    c.setSelected(true);
    if ((!wd.h(e)) && (d != null)) {
      d.setVisibility(8);
    }
    zv.a(g, 4, zv.a(p), MzTelephony.MmsExt.isSnsRecipient(e));
  }
  
  public void a(MessagePopupService.b paramb, boolean paramBoolean, String paramString) {}
  
  public void onClick(View paramView)
  {
    if (i == null) {}
    do
    {
      return;
      switch (paramView.getId())
      {
      case 2131886620: 
      case 2131886622: 
      case 2131886623: 
      case 2131886624: 
      case 2131886625: 
      default: 
        return;
      case 2131886619: 
        i.d();
        return;
      }
    } while (!wd.h(e));
    zm.a(paramView.getContext(), e);
    i.e();
    return;
    i.c();
    i.e();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(getContext(), 2130968754, this);
    a = ((ViewGroup)findViewById(2131886619));
    b = ((TextView)findViewById(2131886623));
    c = ((TextView)findViewById(2131886625));
    d = findViewById(2131886626);
    f = ((ImageView)findViewById(2131886621));
    g = ((ImageView)findViewById(2131886624));
    h = findViewById(2131886627);
    a.setOnClickListener(this);
    f.setOnClickListener(this);
    if (d != null) {
      d.setOnClickListener(this);
    }
    h.setOnClickListener(this);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    ViewGroup localViewGroup = (ViewGroup)findViewById(2131886622);
    TextView localTextView = (TextView)localViewGroup.findViewById(2131886623);
    View localView = localViewGroup.findViewById(2131886624);
    paramInt1 = getResources().getDimensionPixelOffset(2131558763);
    localView.measure(View.MeasureSpec.makeMeasureSpec(localViewGroup.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(localViewGroup.getMeasuredHeight(), Integer.MIN_VALUE));
    if (localTextView.getMeasuredWidth() + paramInt1 + localView.getMeasuredWidth() > localViewGroup.getMeasuredWidth()) {
      localTextView.setMaxWidth(localViewGroup.getMeasuredWidth() - localView.getMeasuredWidth() - paramInt1);
    }
  }
  
  public void setTipCallback(a parama)
  {
    i = parama;
  }
  
  public static abstract interface a
  {
    public abstract void c();
    
    public abstract void d();
    
    public abstract void e();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessagePopupTipView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */