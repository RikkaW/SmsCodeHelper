package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.meizu.common.R.dimen;
import com.meizu.common.R.drawable;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.style;
import com.meizu.common.R.styleable;
import com.meizu.common.util.ResourceUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class EmptyView
  extends FrameLayout
{
  private boolean ignoreSoftInput;
  private LimitedWHLinearLayout mContentPanel;
  private Context mContext;
  private View mDividerView;
  private ImageView mImageView;
  private boolean mIsShowDot;
  private CharSequence mSummary;
  private TextView mSummaryView;
  private int mThemeColor;
  private ArrayList<String> mTips;
  private LinearLayout mTipsPanle;
  private CharSequence mTitle;
  private TextView mTitleView;
  
  public EmptyView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public EmptyView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public EmptyView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mContext = paramContext;
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.mc_empty_view, null);
    mImageView = ((ImageView)localView.findViewById(R.id.empty_image));
    mTitleView = ((TextView)localView.findViewById(R.id.empty_title));
    mSummaryView = ((TextView)localView.findViewById(R.id.empty_summary));
    mTipsPanle = ((LinearLayout)localView.findViewById(R.id.empty_tips_panel));
    mDividerView = localView.findViewById(R.id.titleDivider);
    mContentPanel = ((LimitedWHLinearLayout)localView.findViewById(R.id.content_panel));
    RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)mImageView.getLayoutParams();
    RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)mContentPanel.getLayoutParams();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.EmptyView, paramInt, 0);
    topMargin = paramContext.getDimensionPixelSize(R.styleable.EmptyView_mcTopMarginOfImage, 0);
    topMargin = paramContext.getDimensionPixelSize(R.styleable.EmptyView_mcTopMarginOfTips, topMargin);
    mContentPanel.setMaxWidth(mContext.getResources().getDimensionPixelSize(R.dimen.mc_empty_content_panel_max_width));
    paramAttributeSet = paramContext.getDrawable(R.styleable.EmptyView_mcSrcOfImage);
    if (paramAttributeSet != null) {
      mImageView.setImageDrawable(paramAttributeSet);
    }
    mTitle = paramContext.getString(R.styleable.EmptyView_mcTitle);
    mSummary = paramContext.getString(R.styleable.EmptyView_mcSummary);
    paramAttributeSet = paramContext.getString(R.styleable.EmptyView_mcTextOfTips);
    mTips = ResourceUtils.getStringArray(getContext(), paramContext, R.styleable.EmptyView_mcTips);
    mIsShowDot = paramContext.getBoolean(R.styleable.EmptyView_mcIsShowDot, true);
    paramContext.recycle();
    paramContext = mContext.obtainStyledAttributes(R.styleable.MZTheme);
    mThemeColor = paramContext.getInt(R.styleable.MZTheme_mzThemeColor, -16777216);
    mTitleView.setTextAppearance(getContext(), paramContext.getResourceId(R.styleable.EmptyView_mcTitleTextAppearance, R.style.TextAppearance_Small_EmptyView_Title));
    setTitle(mTitle);
    setSummary(mSummary);
    if (paramAttributeSet == null) {
      setTextOfTips(mTips);
    }
    for (;;)
    {
      paramContext.recycle();
      addView(localView);
      viewControl();
      return;
      setTextOfTips(paramAttributeSet);
    }
  }
  
  private void viewControl()
  {
    if ((!TextUtils.isEmpty(mTitle)) && (TextUtils.isEmpty(mSummary)) && ((mTips == null) || (mTips.size() == 0))) {
      mTitleView.setPadding(mTitleView.getPaddingLeft(), getResources().getDimensionPixelSize(R.dimen.mc_empty_tip_line_space), mTitleView.getPaddingRight(), mTitleView.getPaddingBottom());
    }
    while ((TextUtils.isEmpty(mTitle)) || (mTips == null) || (mTips.size() <= 0)) {
      return;
    }
    mTitleView.getLayoutParams()).topMargin = getResources().getDimensionPixelSize(R.dimen.mc_empty_title_margin_top);
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void setContentPanelMaxWidth(int paramInt)
  {
    mContentPanel.setMaxWidth(paramInt);
  }
  
  public void setIgnoreSoftInput(boolean paramBoolean)
  {
    ignoreSoftInput = paramBoolean;
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)mContentPanel.getLayoutParams();
    if (!paramBoolean)
    {
      bottomMargin = mContext.getResources().getDimensionPixelSize(R.dimen.mc_keyboard_approximate_height);
      return;
    }
    bottomMargin = 0;
  }
  
  public void setImageDrawable(Drawable paramDrawable)
  {
    if (mImageView != null) {
      mImageView.setImageDrawable(paramDrawable);
    }
  }
  
  public void setImageResource(int paramInt)
  {
    if (mImageView != null) {
      mImageView.setImageResource(paramInt);
    }
  }
  
  public void setIsShowDot(boolean paramBoolean)
  {
    if (mIsShowDot == paramBoolean) {}
    for (int i = 0;; i = 1)
    {
      if (i != 0)
      {
        mIsShowDot = paramBoolean;
        setTextOfTips(mTips);
      }
      return;
    }
  }
  
  public void setSummary(CharSequence paramCharSequence)
  {
    if (TextUtils.isEmpty(paramCharSequence)) {
      mSummaryView.setVisibility(8);
    }
    do
    {
      return;
      mSummary = paramCharSequence;
      mSummaryView.setText(paramCharSequence);
      mSummaryView.setAutoLinkMask(15);
      mSummaryView.setMovementMethod(LinkMovementMethod.getInstance());
      mSummaryView.setTextColor(mThemeColor);
      mSummaryView.setLinkTextColor(mThemeColor);
      mSummaryView.setVisibility(0);
    } while ((mTips == null) || (mTips.size() <= 0));
    mDividerView.setVisibility(0);
  }
  
  public void setTextOfTips(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (!TextUtils.isEmpty(paramString)) {
      localArrayList.add(paramString);
    }
    setTextOfTips(localArrayList);
  }
  
  public void setTextOfTips(ArrayList<String> paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {}
    do
    {
      return;
      mTips = paramArrayList;
      mTipsPanle.removeAllViews();
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-2, -2);
      bottomMargin = mContext.getResources().getDimensionPixelSize(R.dimen.mc_empty_tip_margin_Bottom);
      topMargin = mContext.getResources().getDimensionPixelSize(R.dimen.mc_empty_dot_margin_top);
      rightMargin = mContext.getResources().getDimensionPixelSize(R.dimen.mc_empty_dot_margin_right);
      float f = mContext.getResources().getDimensionPixelSize(R.dimen.mc_empty_tip_line_space);
      Drawable localDrawable = getContext().getResources().getDrawable(R.drawable.mc_default_word_point);
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        String str = (String)paramArrayList.next();
        LinearLayout localLinearLayout = new LinearLayout(mContext);
        localLinearLayout.setLayoutParams(localLayoutParams1);
        localLinearLayout.setOrientation(0);
        localLinearLayout.setGravity(48);
        if (mIsShowDot)
        {
          localObject = new ImageView(mContext);
          ((ImageView)localObject).setLayoutParams(localLayoutParams2);
          ((ImageView)localObject).setImageDrawable(localDrawable);
          localLinearLayout.addView((View)localObject);
        }
        Object localObject = new TextView(mContext);
        ((TextView)localObject).setLayoutParams(localLayoutParams3);
        ((TextView)localObject).setTextAppearance(getContext(), R.style.TextAppearance_Small_EmptyView);
        ((TextView)localObject).setText(str);
        ((TextView)localObject).setLineSpacing(f, 1.0F);
        localLinearLayout.addView((View)localObject);
        mTipsPanle.addView(localLinearLayout);
      }
    } while ((TextUtils.isEmpty(mTitle)) && (TextUtils.isEmpty(mSummary)));
    mDividerView.setVisibility(0);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (TextUtils.isEmpty(paramCharSequence)) {
      mTitleView.setVisibility(8);
    }
    do
    {
      return;
      mTitle = paramCharSequence;
      mTitleView.setText(paramCharSequence);
      mTitleView.setVisibility(0);
    } while ((mTips == null) || (mTips.size() <= 0));
    mDividerView.setVisibility(0);
  }
  
  public void setTitleColor(int paramInt)
  {
    mTitleView.setTextColor(paramInt);
  }
  
  public void setTitleTextSize(float paramFloat)
  {
    mTitleView.setTextSize(paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EmptyView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */