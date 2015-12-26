package com.meizu.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.meizu.common.R.attr;
import com.meizu.common.R.dimen;
import com.meizu.common.R.drawable;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.styleable;

public class GuidePopupWindow
  extends PopupWindow
{
  public static final int CORAL = 3;
  public static final int DODGERBLUE = 0;
  public static final int FIREBRICK = 2;
  public static final int GUIDE_LAYOUT_MODE_AUTO = 6;
  public static final int GUIDE_LAYOUT_MODE_CENTER = 3;
  public static final int GUIDE_LAYOUT_MODE_CENTER_HORIZONTAL = 1;
  public static final int GUIDE_LAYOUT_MODE_CENTER_VERTICAL = 2;
  public static final int GUIDE_LAYOUT_MODE_DOWN = 5;
  public static final int GUIDE_LAYOUT_MODE_UP = 4;
  public static final int LIMEGREEN = 1;
  public static final int TOMATO = 4;
  private View mAnchorView;
  private int mArrowOffx;
  private Context mContext;
  private HandleView mHandleView;
  private int mLayoutMode = 6;
  private int mOffX;
  private int mOffY;
  private View mParentView;
  private int mPopX;
  private int mPopY;
  
  public GuidePopupWindow(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
    setTouchable(true);
    setOutsideTouchable(true);
    setClippingEnabled(false);
    setWindowLayoutMode(-2, -2);
    setInputMethodMode(2);
    setBackgroundDrawable(new ColorDrawable(0));
    Object localObject = paramContext.obtainStyledAttributes(null, R.styleable.GuidePopupWindow, R.attr.MeizuCommon_GuidePopupWindow, 0);
    Drawable localDrawable1 = ((TypedArray)localObject).getDrawable(R.styleable.GuidePopupWindow_mcGPWBackGroundLeft);
    Drawable localDrawable2 = ((TypedArray)localObject).getDrawable(R.styleable.GuidePopupWindow_mcGPWBackGroundMidArrowDown);
    Drawable localDrawable3 = ((TypedArray)localObject).getDrawable(R.styleable.GuidePopupWindow_mcGPWBackGroundMidArrowUp);
    Drawable localDrawable4 = ((TypedArray)localObject).getDrawable(R.styleable.GuidePopupWindow_mcGPWBackGroundRight);
    ((TypedArray)localObject).recycle();
    if ((localDrawable1 != null) && (localDrawable2 != null) && (localDrawable3 != null))
    {
      localObject = localDrawable4;
      if (localDrawable4 != null) {}
    }
    else
    {
      localDrawable1 = paramContext.getResources().getDrawable(R.drawable.mc_guide_left);
      localDrawable2 = paramContext.getResources().getDrawable(R.drawable.mc_guide_middle);
      localDrawable3 = paramContext.getResources().getDrawable(R.drawable.mc_guide_middle_up);
      localObject = paramContext.getResources().getDrawable(R.drawable.mc_guide_right);
    }
    mHandleView = new HandleView(mContext, localDrawable1, localDrawable2, localDrawable3, (Drawable)localObject);
    setContentView(mHandleView);
  }
  
  private void computeGuidePosition(int[] paramArrayOfInt, View paramView)
  {
    if (paramView == null) {
      return;
    }
    mHandleView.measure(0, 0);
    int j = mHandleView.mContentView.getMeasuredWidth();
    mPopX = ((paramView.getMeasuredWidth() - mHandleView.mContentView.getMeasuredWidth()) / 2);
    int i;
    int[] arrayOfInt;
    if (mHandleView.getPaddingLeft() < mHandleView.getPopMarging())
    {
      i = mHandleView.getPaddingLeft() - mHandleView.getPopMarging();
      arrayOfInt = new int[2];
      getAnchorPositon(paramView, arrayOfInt);
      if (j <= paramArrayOfInt[2] - paramArrayOfInt[0]) {
        break label429;
      }
      mPopX = (i - arrayOfInt[0] + paramArrayOfInt[0]);
      mHandleView.setMessageWidth(paramArrayOfInt[2] - paramArrayOfInt[0] - HandleView.access$200(mHandleView) * 2 - mHandleView.getCloseIconWidth());
      mHandleView.measure(0, 0);
      label159:
      switch (mLayoutMode)
      {
      default: 
        mPopY = (-mHandleView.getMeasuredHeight() - paramView.getMeasuredHeight() + mHandleView.getArrowPadding());
        if (arrayOfInt[1] - mHandleView.getMeasuredHeight() + mHandleView.getArrowPadding() < paramArrayOfInt[1])
        {
          mPopY = (-mHandleView.getArrowPadding());
          mHandleView.setArrowUp();
          label254:
          j = -mPopX + paramView.getMeasuredWidth() / 2 - mHandleView.getArrowWidth() / 2 + mArrowOffx;
          if (j <= mHandleView.getMeasuredWidth() - (mHandleView.getBackgroundMidMinWidth() + mHandleView.getBackgroundRightMinWidth())) {
            break label599;
          }
          i = mHandleView.getMeasuredWidth() - (mHandleView.getBackgroundMidMinWidth() + mHandleView.getBackgroundRightMinWidth());
          label335:
          mPopY += mOffY;
          mPopX += mOffX;
          if (mLayoutMode != 1) {
            break label625;
          }
          mPopX = (paramArrayOfInt[0] + (paramArrayOfInt[2] - paramArrayOfInt[0]) / 2 - mHandleView.getMeasuredWidth() / 2 - arrayOfInt[0]);
        }
        break;
      }
    }
    for (;;)
    {
      mHandleView.setArrowPosition(i);
      return;
      i = mHandleView.getPopMarging() - mHandleView.getPaddingLeft();
      break;
      label429:
      if (arrayOfInt[0] + (paramView.getMeasuredWidth() - j) / 2 < paramArrayOfInt[0] + i) {
        mPopX = (paramArrayOfInt[0] - arrayOfInt[0]);
      }
      if (arrayOfInt[0] + paramView.getMeasuredWidth() / 2 + j / 2 <= paramArrayOfInt[2] - i) {
        break label159;
      }
      mPopX = (paramArrayOfInt[2] - i - arrayOfInt[0] - j);
      break label159;
      mPopY = (-mHandleView.getMeasuredHeight() - paramView.getMeasuredHeight() + mHandleView.getArrowPadding());
      mHandleView.setArrowDown();
      break label254;
      mPopY = (-mHandleView.getArrowPadding());
      mHandleView.setArrowUp();
      break label254;
      mPopY = (-mHandleView.getMeasuredHeight() - paramView.getMeasuredHeight() + mHandleView.getArrowPadding());
      mHandleView.setArrowDown();
      break label254;
      label599:
      i = j;
      if (j >= mHandleView.getBackgroundLeftMinWidth()) {
        break label335;
      }
      i = mHandleView.getBackgroundLeftMinWidth();
      break label335;
      label625:
      if (mLayoutMode == 2)
      {
        mPopY = (paramArrayOfInt[1] + (paramArrayOfInt[3] - paramArrayOfInt[1]) / 2 - mHandleView.getMeasuredHeight() / 2 - arrayOfInt[1] - paramView.getMeasuredHeight());
      }
      else if (mLayoutMode == 3)
      {
        mPopX = (paramArrayOfInt[0] + (paramArrayOfInt[2] - paramArrayOfInt[0]) / 2 - mHandleView.getMeasuredWidth() / 2 - arrayOfInt[0]);
        mPopY = (paramArrayOfInt[1] + (paramArrayOfInt[3] - paramArrayOfInt[1]) / 2 - mHandleView.getMeasuredHeight() / 2 - arrayOfInt[1] - paramView.getMeasuredHeight());
      }
    }
  }
  
  private void getAnchorPositon(View paramView, int[] paramArrayOfInt)
  {
    if (paramView == null) {
      return;
    }
    paramView.getLocationOnScreen(paramArrayOfInt);
  }
  
  public void disableArrow(boolean paramBoolean)
  {
    mHandleView.disableArrow(paramBoolean);
  }
  
  public TextView getMessageTextView()
  {
    return mHandleView.getMessageTextView();
  }
  
  public void setArrowPosition(int paramInt)
  {
    mArrowOffx = paramInt;
  }
  
  public void setColorStyle(int paramInt) {}
  
  public void setHorizontalOffset(int paramInt)
  {
    mOffX = paramInt;
  }
  
  public void setLayoutMode(int paramInt)
  {
    mLayoutMode = paramInt;
  }
  
  public void setMessage(String paramString)
  {
    mHandleView.setText(paramString);
  }
  
  public void setMessageOnClickListener(View.OnClickListener paramOnClickListener)
  {
    mHandleView.setMessageOnClickListener(paramOnClickListener);
  }
  
  public void setTextSize(int paramInt)
  {
    mHandleView.setTextSize(paramInt);
  }
  
  public void setVerticalOffset(int paramInt)
  {
    mOffY = paramInt;
  }
  
  public void show(Rect paramRect, View paramView)
  {
    if (paramView == null) {
      return;
    }
    mAnchorView = paramView;
    show(paramRect, paramView, 0, 0);
  }
  
  public void show(Rect paramRect, View paramView, int paramInt1, int paramInt2)
  {
    if (paramView == null) {
      return;
    }
    mOffX = paramInt1;
    mOffY = paramInt2;
    mAnchorView = paramView;
    computeGuidePosition(new int[] { left, top, right, bottom }, paramView);
    showAsDropDown(paramView, mPopX, mPopY);
  }
  
  public void show(View paramView)
  {
    if (paramView == null) {
      return;
    }
    mAnchorView = paramView;
    int[] arrayOfInt = new int[4];
    mHandleView.getParentBound(null, arrayOfInt);
    computeGuidePosition(arrayOfInt, paramView);
    showAsDropDown(paramView, mPopX, mPopY);
  }
  
  public void show(View paramView, int paramInt1, int paramInt2)
  {
    if (paramView == null) {
      return;
    }
    mOffX = paramInt1;
    mOffY = paramInt2;
    mAnchorView = paramView;
    DisplayMetrics localDisplayMetrics = mContext.getResources().getDisplayMetrics();
    computeGuidePosition(new int[] { 0, 0, widthPixels, heightPixels }, paramView);
    showAsDropDown(paramView, mPopX, mPopY);
  }
  
  public void show(View paramView1, View paramView2)
  {
    if (paramView2 == null) {
      return;
    }
    mAnchorView = paramView2;
    mParentView = paramView1;
    show(paramView1, paramView2, mOffX, mOffY);
  }
  
  public void show(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    if (paramView2 == null) {
      return;
    }
    mOffX = paramInt1;
    mOffY = paramInt2;
    mAnchorView = paramView2;
    mParentView = paramView1;
    int[] arrayOfInt = new int[4];
    mHandleView.getParentBound(paramView1, arrayOfInt);
    computeGuidePosition(arrayOfInt, paramView2);
    showAsDropDown(paramView2, mPopX, mPopY);
  }
  
  @TargetApi(19)
  public void update(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    if ((Build.VERSION.SDK_INT >= 19) && (!mAnchorView.isAttachedToWindow())) {
      return;
    }
    super.update(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean);
  }
  
  public void updatePosition()
  {
    if (mAnchorView == null) {
      return;
    }
    int[] arrayOfInt = new int[4];
    mHandleView.getParentBound(mParentView, arrayOfInt);
    computeGuidePosition(arrayOfInt, mAnchorView);
    arrayOfInt = new int[2];
    getAnchorPositon(mAnchorView, arrayOfInt);
    int i = mPopX;
    int j = arrayOfInt[0];
    int k = mPopY;
    update(i + j, arrayOfInt[1] + k + mAnchorView.getMeasuredHeight(), -1, -1);
  }
  
  class HandleView
    extends FrameLayout
  {
    private int layoutResourceId = R.layout.mc_guide_popup_window;
    private int mArrowLeft = -1;
    private int mArrowPadding;
    private View mBGLeft;
    private View mBGMiddle;
    private Rect mBGPadding = new Rect();
    private View mBGRight;
    private LinearLayout mBGVertical;
    private ImageView mCloseIcon;
    private LinearLayout mContentView;
    private Drawable mLeftDrawable;
    private int mLeftMinWidth;
    private int mMarging;
    private TextView mMessageTextView;
    private int mMidMinWidth;
    private Drawable mMiddleDrawable;
    private Drawable mMiddleDrawableUp;
    private int mMinHeight;
    private int mMinWidth;
    private Resources mResources;
    private Drawable mRightDrawable;
    private int mRightMinWidth;
    private int mTextSize;
    private boolean mWithArrow = true;
    
    public HandleView(Context paramContext, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
    {
      super();
      View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(layoutResourceId, this, true);
      mResources = paramContext.getResources();
      mMessageTextView = ((TextView)localView.findViewById(R.id.guide_message));
      mCloseIcon = ((ImageView)localView.findViewById(R.id.guide_close));
      mContentView = ((LinearLayout)localView.findViewById(R.id.guide_content));
      mBGLeft = localView.findViewById(R.id.guide_bg_left);
      mBGMiddle = localView.findViewById(R.id.guide_bg_middle);
      mBGRight = localView.findViewById(R.id.guide_bg_right);
      mBGVertical = ((LinearLayout)localView.findViewById(R.id.guide_bg_vertical));
      mTextSize = mResources.getDimensionPixelSize(R.dimen.mc_guide_popup_text_size);
      mMinHeight = mResources.getDimensionPixelSize(R.dimen.mc_guide_popup_min_height);
      mLeftDrawable = paramDrawable1;
      mMiddleDrawable = paramDrawable2;
      mMiddleDrawableUp = paramDrawable3;
      mRightDrawable = paramDrawable4;
      mBGLeft.setBackground(mLeftDrawable);
      mBGMiddle.setBackground(mMiddleDrawable);
      mBGRight.setBackground(mRightDrawable);
      mArrowPadding = mResources.getDimensionPixelSize(R.dimen.mc_guide_popup_arrow_padding);
      mMarging = mResources.getDimensionPixelSize(R.dimen.mc_guide_popup_marging);
      paramContext = new Rect();
      mLeftDrawable.getPadding(paramContext);
      mBGPadding.left = Math.max(left, mBGPadding.left);
      mBGPadding.top = Math.max(top, mBGPadding.top);
      mBGPadding.bottom = Math.max(bottom, mBGPadding.bottom);
      mMiddleDrawable.getPadding(paramContext);
      mBGPadding.top = Math.max(top, mBGPadding.top);
      mBGPadding.bottom = Math.max(bottom, mBGPadding.bottom);
      mRightDrawable.getPadding(paramContext);
      mBGPadding.right = Math.max(right, mBGPadding.right);
      mBGPadding.top = Math.max(top, mBGPadding.top);
      mBGPadding.bottom = Math.max(bottom, mBGPadding.bottom);
      mLeftMinWidth = mLeftDrawable.getIntrinsicWidth();
      mMidMinWidth = mMiddleDrawable.getIntrinsicWidth();
      mRightMinWidth = mRightDrawable.getIntrinsicWidth();
      mMinWidth = (mLeftMinWidth + mMidMinWidth + mRightMinWidth);
      mContentView.setMinimumHeight(mMinHeight + mBGPadding.top + mBGPadding.bottom);
      mContentView.setMinimumWidth(mMinWidth);
      mContentView.setPadding(mBGPadding.left, mBGPadding.top, mBGPadding.right, mBGPadding.bottom);
      mCloseIcon.setOnClickListener(new GuidePopupWindow.HandleView.1(this, GuidePopupWindow.this));
    }
    
    private void getParentBound(View paramView, int[] paramArrayOfInt)
    {
      if (paramView == null)
      {
        paramView = getResources().getDisplayMetrics();
        int i = widthPixels;
        int j = heightPixels;
        paramArrayOfInt[0] = 0;
        paramArrayOfInt[1] = 0;
        paramArrayOfInt[2] = i;
        paramArrayOfInt[3] = j;
        return;
      }
      int[] arrayOfInt = new int[2];
      paramView.getLocationOnScreen(arrayOfInt);
      paramArrayOfInt[0] = arrayOfInt[0];
      paramArrayOfInt[1] = arrayOfInt[1];
      paramArrayOfInt[2] = (paramArrayOfInt[0] + paramView.getWidth());
      paramArrayOfInt[3] = (paramArrayOfInt[1] + paramView.getHeight());
    }
    
    private int getPopMarging()
    {
      return mMarging;
    }
    
    private void setMessageWidth(int paramInt)
    {
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)mMessageTextView.getLayoutParams();
      width = paramInt;
      mMessageTextView.setLayoutParams(localLayoutParams);
    }
    
    public void disableArrow(boolean paramBoolean)
    {
      if (!paramBoolean) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        mWithArrow = paramBoolean;
        return;
      }
    }
    
    public int getArrowPadding()
    {
      return mArrowPadding;
    }
    
    public int getArrowWidth()
    {
      return mMiddleDrawable.getMinimumWidth();
    }
    
    public int getBackgroundLeftMinWidth()
    {
      return mLeftMinWidth;
    }
    
    public int getBackgroundMidMinWidth()
    {
      return mMidMinWidth;
    }
    
    public int getBackgroundMinWidth()
    {
      return mMinWidth;
    }
    
    public int getBackgroundRightMinWidth()
    {
      return mRightMinWidth;
    }
    
    public int getCloseIconWidth()
    {
      return mCloseIcon.getMeasuredWidth();
    }
    
    public TextView getMessageTextView()
    {
      return mMessageTextView;
    }
    
    public int getPaddingLeft()
    {
      return mBGPadding.left;
    }
    
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      super.onMeasure(paramInt1, paramInt2);
      mContentView.measure(0, 0);
      paramInt1 = mContentView.getMeasuredHeight();
      paramInt2 = mContentView.getMeasuredWidth();
      setMeasuredDimension(paramInt2, paramInt1);
      mBGVertical.measure(paramInt2, paramInt1);
      if (!mWithArrow)
      {
        localLayoutParams = (LinearLayout.LayoutParams)mBGLeft.getLayoutParams();
        width = (paramInt2 - mRightDrawable.getMinimumWidth());
        height = paramInt1;
        mBGLeft.setLayoutParams(localLayoutParams);
        localLayoutParams = (LinearLayout.LayoutParams)mBGRight.getLayoutParams();
        width = mRightDrawable.getMinimumWidth();
        height = paramInt1;
        mBGRight.setLayoutParams(localLayoutParams);
        localLayoutParams = (LinearLayout.LayoutParams)mBGMiddle.getLayoutParams();
        width = 0;
        height = paramInt1;
        mBGMiddle.setLayoutParams(localLayoutParams);
        return;
      }
      if (mArrowLeft > 0)
      {
        localLayoutParams = (LinearLayout.LayoutParams)mBGLeft.getLayoutParams();
        width = mArrowLeft;
        height = paramInt1;
        mBGLeft.setLayoutParams(localLayoutParams);
        localLayoutParams = (LinearLayout.LayoutParams)mBGRight.getLayoutParams();
        width = (paramInt2 - mMiddleDrawable.getMinimumWidth() - mArrowLeft);
        height = paramInt1;
        mBGRight.setLayoutParams(localLayoutParams);
        localLayoutParams = (LinearLayout.LayoutParams)mBGMiddle.getLayoutParams();
        width = mMiddleDrawable.getMinimumWidth();
        height = paramInt1;
        mBGMiddle.setLayoutParams(localLayoutParams);
        return;
      }
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)mBGLeft.getLayoutParams();
      width = ((paramInt2 - mMiddleDrawable.getMinimumWidth()) / 2);
      height = paramInt1;
      mBGLeft.setLayoutParams(localLayoutParams);
      localLayoutParams = (LinearLayout.LayoutParams)mBGRight.getLayoutParams();
      width = ((paramInt2 - mMiddleDrawable.getMinimumWidth()) / 2);
      height = paramInt1;
      mBGRight.setLayoutParams(localLayoutParams);
      localLayoutParams = (LinearLayout.LayoutParams)mBGMiddle.getLayoutParams();
      width = mMiddleDrawable.getMinimumWidth();
      height = paramInt1;
      mBGMiddle.setLayoutParams(localLayoutParams);
    }
    
    public void setArrowDown()
    {
      mBGMiddle.setBackground(mMiddleDrawable);
    }
    
    public void setArrowPosition(int paramInt)
    {
      mArrowLeft = paramInt;
    }
    
    public void setArrowUp()
    {
      mBGMiddle.setBackground(mMiddleDrawableUp);
    }
    
    public void setMessageOnClickListener(View.OnClickListener paramOnClickListener)
    {
      mMessageTextView.setOnClickListener(paramOnClickListener);
    }
    
    public void setText(String paramString)
    {
      mMessageTextView.setText(paramString);
    }
    
    public void setTextSize(int paramInt)
    {
      mMessageTextView.setTextSize(1, paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.GuidePopupWindow
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */