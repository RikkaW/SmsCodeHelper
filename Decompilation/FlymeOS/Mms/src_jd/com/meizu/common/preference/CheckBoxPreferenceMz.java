package com.meizu.common.preference;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceScreen;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import java.lang.reflect.Method;

public class CheckBoxPreferenceMz
  extends CheckBoxPreference
{
  private static Method sPerformClick;
  private View mTextLayout;
  private View.OnClickListener mTextLayoutClickListener;
  private boolean mTextLayoutEnabled = true;
  private View.OnLongClickListener mTextLayoutLongClickListener;
  private View mWidgetFrame;
  private boolean mWidgetFrameEnabled = true;
  
  public CheckBoxPreferenceMz(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CheckBoxPreferenceMz(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setLayoutResource(R.layout.mz_preference_checkbox);
  }
  
  public CheckBoxPreferenceMz(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setLayoutResource(R.layout.mz_preference_checkbox);
  }
  
  private void performClick()
  {
    try
    {
      if (sPerformClick == null)
      {
        sPerformClick = CheckBoxPreference.class.getMethod("performClick", new Class[] { PreferenceScreen.class });
        sPerformClick.setAccessible(true);
      }
      sPerformClick.invoke(this, new Object[] { null });
      return;
    }
    catch (Exception localException) {}
  }
  
  private void setEnabledStateOnViews(View paramView, boolean paramBoolean)
  {
    paramView.setEnabled(paramBoolean);
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int i = paramView.getChildCount() - 1;
      while (i >= 0)
      {
        setEnabledStateOnViews(paramView.getChildAt(i), paramBoolean);
        i -= 1;
      }
    }
  }
  
  public boolean isTextLayoutEnabled()
  {
    return mTextLayoutEnabled;
  }
  
  public boolean isWidgetFrameEnabled()
  {
    return mWidgetFrameEnabled;
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    if ((isEnabled()) && (!mTextLayoutEnabled)) {
      setEnabledStateOnViews(mTextLayout, mTextLayoutEnabled);
    }
    if ((isEnabled()) && (!mWidgetFrameEnabled)) {
      setEnabledStateOnViews(mWidgetFrame, mWidgetFrameEnabled);
    }
  }
  
  protected View onCreateView(ViewGroup paramViewGroup)
  {
    paramViewGroup = super.onCreateView(paramViewGroup);
    mTextLayout = paramViewGroup.findViewById(R.id.mz_preference_text_layout);
    if (mTextLayoutClickListener != null) {
      mTextLayout.setOnClickListener(mTextLayoutClickListener);
    }
    if (mTextLayoutLongClickListener != null) {
      mTextLayout.setOnLongClickListener(mTextLayoutLongClickListener);
    }
    mWidgetFrame = paramViewGroup.findViewById(16908312);
    mWidgetFrame.setOnClickListener(new CheckBoxPreferenceMz.1(this));
    return paramViewGroup;
  }
  
  public void setTextLayoutClickListener(View.OnClickListener paramOnClickListener)
  {
    mTextLayoutClickListener = paramOnClickListener;
    if (mTextLayout != null) {
      mTextLayout.setOnClickListener(paramOnClickListener);
    }
  }
  
  public void setTextLayoutEnabled(boolean paramBoolean)
  {
    if (mTextLayoutEnabled != paramBoolean)
    {
      mTextLayoutEnabled = paramBoolean;
      if (mTextLayout != null) {
        setEnabledStateOnViews(mTextLayout, paramBoolean);
      }
    }
  }
  
  public void setTextLayoutLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    mTextLayoutLongClickListener = paramOnLongClickListener;
    if (mTextLayout != null) {
      mTextLayout.setOnLongClickListener(paramOnLongClickListener);
    }
  }
  
  public void setWidgetFrameEnabled(boolean paramBoolean)
  {
    if (mWidgetFrameEnabled != paramBoolean)
    {
      mWidgetFrameEnabled = paramBoolean;
      if (mWidgetFrame != null) {
        setEnabledStateOnViews(mWidgetFrame, paramBoolean);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.CheckBoxPreferenceMz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */