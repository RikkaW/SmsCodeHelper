package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhraseListItem
  extends LinearLayout
{
  private TextView mPhraseBodyView;
  private String mPhraseSmsItem;
  
  public PhraseListItem(Context paramContext)
  {
    super(paramContext);
  }
  
  public PhraseListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void bind(String paramString)
  {
    mPhraseSmsItem = paramString;
    mPhraseBodyView.setText(paramString);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    mPhraseBodyView = ((TextView)findViewById(2131820841));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PhraseListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */