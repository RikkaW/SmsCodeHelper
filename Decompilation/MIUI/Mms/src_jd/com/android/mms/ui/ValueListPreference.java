package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import miui.R.id;
import miui.R.layout;

public class ValueListPreference
  extends ListPreference
{
  private int mListTitleId;
  private String mRightString;
  
  public ValueListPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ValueListPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setLayoutResource(R.layout.preference_value);
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    Object localObject = (TextView)paramView.findViewById(R.id.value_right);
    if (localObject != null)
    {
      if (!TextUtils.isEmpty(mRightString))
      {
        ((TextView)localObject).setText(mRightString);
        ((TextView)localObject).setVisibility(0);
      }
    }
    else
    {
      localObject = (TextView)paramView.findViewById(16908304);
      CharSequence localCharSequence = getSummary();
      if (localObject != null)
      {
        if (TextUtils.isEmpty(localCharSequence)) {
          break label128;
        }
        ((TextView)localObject).setVisibility(0);
      }
    }
    for (;;)
    {
      localObject = (ImageView)paramView.findViewById(R.id.arrow_right);
      if (localObject != null) {
        ((ImageView)localObject).setVisibility(0);
      }
      paramView = (TextView)paramView.findViewById(16908310);
      if (paramView != null)
      {
        paramView.setSingleLine(false);
        paramView.setMaxLines(2);
      }
      return;
      ((TextView)localObject).setVisibility(8);
      break;
      label128:
      ((TextView)localObject).setVisibility(8);
    }
  }
  
  protected void onPrepareDialogBuilder(AlertDialog.Builder paramBuilder)
  {
    super.onPrepareDialogBuilder(paramBuilder);
    if (mListTitleId > 0) {
      paramBuilder.setTitle(mListTitleId);
    }
    paramBuilder.setNegativeButton(null, null);
  }
  
  public void setListTitleId(int paramInt)
  {
    mListTitleId = paramInt;
  }
  
  public void setRightValue(String paramString)
  {
    mRightString = paramString;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ValueListPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */