package com.android.mms.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import ze;

public class TwoTextViewListItem
  extends LinearLayout
{
  private TextView a;
  private TextView b;
  
  public TwoTextViewListItem(Context paramContext)
  {
    super(paramContext);
  }
  
  public TwoTextViewListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public TwoTextViewListItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void a(ze paramze)
  {
    a.setText(paramze.a());
    b.setText(paramze.b());
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    a = ((TextView)findViewById(2131886481));
    b = ((TextView)findViewById(2131886482));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.TwoTextViewListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */