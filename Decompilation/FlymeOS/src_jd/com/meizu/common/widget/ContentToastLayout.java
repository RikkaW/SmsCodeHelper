package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meizu.common.R.color;
import com.meizu.common.R.drawable;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;

public class ContentToastLayout
  extends FrameLayout
{
  public static final int TOAST_TYPE_ERROR = 1;
  public static final int TOAST_TYPE_NORMAL = 0;
  private Drawable mActionIcon;
  private ImageView mActionView;
  private LinearLayout mContainerLayout;
  private Drawable mDefaultActionIcon;
  private Drawable mDefaultWarningIcon;
  private LinearLayout mParentLayout;
  private View mSeparatorView;
  private String mText;
  private TextView mTitleView;
  private Drawable mWarningIcon;
  private ImageView mWarningView;
  private LinearLayout mWidgetLayout;
  private View mWidgetView;
  
  public ContentToastLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ContentToastLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ContentToastLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    LayoutInflater.from(paramContext).inflate(R.layout.mc_content_toast_layout, this);
    mParentLayout = ((LinearLayout)findViewById(R.id.mc_content_toast_parent));
    mWidgetLayout = ((LinearLayout)findViewById(16908312));
    mTitleView = ((TextView)findViewById(16908310));
    mWarningView = ((ImageView)findViewById(16908294));
    mSeparatorView = findViewById(R.id.mc_toast_separator);
    mContainerLayout = ((LinearLayout)findViewById(R.id.mc_content_toast_container));
    mDefaultWarningIcon = getResources().getDrawable(R.drawable.mz_ic_content_toast_warning);
    setToastType(0);
  }
  
  private void setWidget(View paramView)
  {
    if (mWidgetView != null) {
      mWidgetLayout.removeView(mWidgetView);
    }
    if (paramView != null) {
      mWidgetLayout.addView(paramView);
    }
  }
  
  public String getText()
  {
    return mText;
  }
  
  public void setActionIcon(Drawable paramDrawable)
  {
    mActionIcon = paramDrawable;
    if (mActionView == null)
    {
      mActionView = new ImageView(getContext());
      mActionView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      setWidget(mActionView);
    }
    mActionView.setImageDrawable(paramDrawable);
  }
  
  public void setBackground(Drawable paramDrawable)
  {
    mParentLayout.setBackgroundDrawable(paramDrawable);
  }
  
  public void setContainerLayoutPadding(int paramInt)
  {
    mContainerLayout.setPadding(paramInt, 0, paramInt, 0);
  }
  
  public void setIsShowSeparator(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      mSeparatorView.setVisibility(0);
      return;
    }
    mSeparatorView.setVisibility(8);
  }
  
  public void setParentLayoutPadding(int paramInt)
  {
    mParentLayout.setPadding(paramInt, 0, paramInt, 0);
  }
  
  public void setText(String paramString)
  {
    mText = paramString;
    mTitleView.setText(paramString);
  }
  
  public void setTextColor(int paramInt)
  {
    mTitleView.setTextColor(paramInt);
  }
  
  public void setToastType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      setWarningIcon(null);
      mDefaultActionIcon = getResources().getDrawable(R.drawable.mz_arrow_next_right_normal);
      setActionIcon(mDefaultActionIcon);
      mTitleView.setTextColor(getResources().getColor(R.color.mc_content_toast_text_color_normal));
      mParentLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.mc_content_toast_bg_normal));
      mWarningView.setVisibility(8);
      return;
    }
    setWarningIcon(mDefaultWarningIcon);
    mDefaultActionIcon = getResources().getDrawable(R.drawable.mz_arrow_next_right_warning);
    setActionIcon(mDefaultActionIcon);
    mTitleView.setTextColor(getResources().getColor(R.color.mc_content_toast_text_color_error));
    mParentLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.mc_content_toast_bg_error));
    mWarningView.setVisibility(0);
  }
  
  public void setWarningIcon(Drawable paramDrawable)
  {
    mWarningIcon = paramDrawable;
    mWarningView.setImageDrawable(mWarningIcon);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ContentToastLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */