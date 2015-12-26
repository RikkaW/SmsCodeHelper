package com.android.mms.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagePopupService.b;
import gm;
import gq;
import gr;

public class MessagePopupVeriCodeView
  extends FrameLayout
  implements View.OnClickListener
{
  private TextView a;
  private TextView b;
  private TextView c;
  private MessagePopupGroupView.b d;
  private final int e = 2000;
  private Context f;
  
  public MessagePopupVeriCodeView(Context paramContext)
  {
    super(paramContext);
    f = paramContext;
  }
  
  public MessagePopupVeriCodeView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    f = paramContext;
  }
  
  public MessagePopupVeriCodeView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    f = paramContext;
  }
  
  public void a(MessagePopupService.b paramb)
  {
    gm localgm = (gm)gr.a(MmsApp.c(), b, false).h().get(0);
    a.setText(localgm.g());
    b.setText(k);
    paramb = f;
    c.setText(paramb);
    c.setSelected(true);
  }
  
  public void onClick(View paramView)
  {
    if (d == null) {
      return;
    }
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131886619: 
      d.d();
      return;
    case 2131886621: 
      d.c();
      return;
    case 2131886630: 
      ((ClipboardManager)MmsApp.c().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, c.getText()));
    }
    d.e();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(getContext(), 2130968755, this);
    a = ((TextView)findViewById(2131886623));
    b = ((TextView)findViewById(2131886628));
    c = ((TextView)findViewById(2131886625));
    findViewById(2131886619).setOnClickListener(this);
    findViewById(2131886629).setOnClickListener(this);
    findViewById(2131886630).setOnClickListener(this);
  }
  
  public void setGroupCallback(MessagePopupGroupView.b paramb)
  {
    d = paramb;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessagePopupVeriCodeView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */