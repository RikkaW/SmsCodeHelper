package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.bool;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.widget.ToastUtils;
import android.support.v7.widget.ActionMenuView.ActionMenuChildView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewParent;
import android.widget.Toast;

public class ActionMenuItemView
  extends AppCompatTextView
  implements MenuView.ItemView, ActionMenuView.ActionMenuChildView, View.OnClickListener, View.OnLongClickListener
{
  private static final int MAX_ICON_SIZE = 32;
  private static final int NO_ALPHA = 255;
  private static final String TAG = "ActionMenuItemView";
  private boolean mAllowTextWithIcon;
  private float mDisabledAlpha;
  private boolean mExpandedFormat;
  private ListPopupWindow.ForwardingListener mForwardingListener;
  private Drawable mIcon;
  private boolean mIsInSplit;
  private boolean mIsOverflowActor;
  private MenuItemImpl mItemData;
  private MenuBuilder.ItemInvoker mItemInvoker;
  private int mMaxIconSize;
  private int mMinWidth;
  private PopupCallback mPopupCallback;
  private int mSavedPaddingLeft;
  private CharSequence mTitle;
  
  public ActionMenuItemView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionMenuItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ActionMenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = paramContext.getResources();
    mAllowTextWithIcon = localResources.getBoolean(R.bool.abc_config_allowActionMenuItemTextWithIcon);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ActionMenuItemView, paramInt, 0);
    mMinWidth = localTypedArray.getDimensionPixelSize(R.styleable.ActionMenuItemView_android_minWidth, 0);
    localTypedArray.recycle();
    mMaxIconSize = ((int)(getDisplayMetricsdensity * 32.0F + 0.5F));
    setOnClickListener(this);
    mSavedPaddingLeft = -1;
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MenuView, paramInt, 0);
    mDisabledAlpha = paramContext.getFloat(R.styleable.MenuView_android_itemIconDisabledAlpha, 1.0F);
    paramContext.recycle();
  }
  
  private void setCompoundDrawables(boolean paramBoolean)
  {
    Drawable localDrawable1;
    if (paramBoolean)
    {
      localDrawable1 = null;
      if (!paramBoolean) {
        break label32;
      }
    }
    label32:
    for (Drawable localDrawable2 = mIcon;; localDrawable2 = null)
    {
      setCompoundDrawables(localDrawable1, localDrawable2, null, null);
      return;
      localDrawable1 = mIcon;
      break;
    }
  }
  
  private void updateTextButtonVisibility()
  {
    int k = 1;
    int i;
    int j;
    label51:
    boolean bool;
    if (!TextUtils.isEmpty(mTitle))
    {
      i = 1;
      j = k;
      if (mIcon != null)
      {
        if (!mItemData.showsTextAsAction()) {
          break label126;
        }
        j = k;
        if (!mAllowTextWithIcon)
        {
          if (!mExpandedFormat) {
            break label126;
          }
          j = k;
        }
      }
      bool = j & i;
      if (!bool) {
        break label131;
      }
    }
    label126:
    label131:
    for (CharSequence localCharSequence = mTitle;; localCharSequence = null)
    {
      setText(localCharSequence);
      uptateTextAppearance(bool);
      setCompoundDrawables(bool);
      if ((!bool) || (mIcon == null)) {
        break label137;
      }
      setPadding(getPaddingLeft(), getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_padding_top_icon_with_text), getPaddingRight(), 0);
      return;
      i = 0;
      break;
      j = 0;
      break label51;
    }
    label137:
    setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
  }
  
  private void uptateTextAppearance(boolean paramBoolean)
  {
    int[] arrayOfInt;
    if ((mIcon != null) && (paramBoolean)) {
      if (mIsInSplit)
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = R.attr.mzActionMenuTextAppearanceWithIconSplit;
      }
    }
    for (;;)
    {
      int i = getContext().getTheme().obtainStyledAttributes(arrayOfInt).getResourceId(0, -1);
      if (i > 0) {
        setTextAppearance(getContext(), i);
      }
      return;
      arrayOfInt = new int[1];
      arrayOfInt[0] = R.attr.mzActionMenuTextAppearanceWithIcon;
      continue;
      if (mIsInSplit)
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = R.attr.mzActionMenuTextAppearanceSplit;
      }
      else
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = R.attr.actionMenuTextAppearance;
      }
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Drawable localDrawable;
    if ((mItemData != null) && (mIcon != null))
    {
      if ((mItemData.isEnabled()) || ((!isPressed()) && (isFocused()))) {
        break label68;
      }
      i = 1;
      localDrawable = mIcon;
      if (i == 0) {
        break label73;
      }
    }
    label68:
    label73:
    for (int i = (int)(mDisabledAlpha * 255.0F);; i = 255)
    {
      localDrawable.setAlpha(i);
      return;
      i = 0;
      break;
    }
  }
  
  public MenuItemImpl getItemData()
  {
    return mItemData;
  }
  
  public boolean hasText()
  {
    return !TextUtils.isEmpty(getText());
  }
  
  public void initialize(MenuItemImpl paramMenuItemImpl, int paramInt)
  {
    int i = 0;
    mItemData = paramMenuItemImpl;
    setIcon(paramMenuItemImpl.getIcon());
    setTitle(paramMenuItemImpl.getTitleForItemView(this));
    setId(paramMenuItemImpl.getItemId());
    if (paramMenuItemImpl.isVisible())
    {
      paramInt = 0;
      setVisibility(paramInt);
      setEnabled(paramMenuItemImpl.isEnabled());
      if ((paramMenuItemImpl.hasSubMenu()) && (mForwardingListener == null)) {
        mForwardingListener = new ActionMenuItemForwardingListener();
      }
      if (paramMenuItemImpl.getTitleColor() != null) {
        setTextColor(paramMenuItemImpl.getTitleColor());
      }
      setIsOverflowActor(paramMenuItemImpl.isOverflowActor());
      if (mIcon != null)
      {
        paramInt = i;
        if (!mItemData.isEnabled()) {
          if (!isPressed())
          {
            paramInt = i;
            if (isFocused()) {}
          }
          else
          {
            paramInt = 1;
          }
        }
        paramMenuItemImpl = mIcon;
        if (paramInt == 0) {
          break label170;
        }
      }
    }
    label170:
    for (paramInt = (int)(mDisabledAlpha * 255.0F);; paramInt = 255)
    {
      paramMenuItemImpl.setAlpha(paramInt);
      return;
      paramInt = 8;
      break;
    }
  }
  
  public boolean isOverflowActor()
  {
    return mIsOverflowActor;
  }
  
  public boolean needsDividerAfter()
  {
    return hasText();
  }
  
  public boolean needsDividerBefore()
  {
    return (hasText()) && (mItemData.getIcon() == null);
  }
  
  public void onClick(View paramView)
  {
    if (mItemInvoker != null) {
      mItemInvoker.invokeItem(mItemData);
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (Build.VERSION.SDK_INT >= 8) {
      super.onConfigurationChanged(paramConfiguration);
    }
    mAllowTextWithIcon = getContext().getResources().getBoolean(R.bool.abc_config_allowActionMenuItemTextWithIcon);
    updateTextButtonVisibility();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    Log.d("ActionMenuItemView", "onDraw:text = " + getText() + "; left = " + getLeft() + "; right = " + getRight() + "; measureWidth = " + getMeasuredWidth());
    if ((getRight() - getLeft() == 0) && (getMeasuredWidth() > 0))
    {
      getParent().requestLayout();
      return;
    }
    super.onDraw(paramCanvas);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Log.d("ActionMenuItemView", "onLayout:text = " + getText() + "; left = " + getLeft() + "; right = " + getRight());
  }
  
  public boolean onLongClick(View paramView)
  {
    if (hasText()) {
      return false;
    }
    if (TextUtils.isEmpty(mTitle)) {
      return false;
    }
    if (ToastUtils.showToast(this, mItemData.getTitle()) != null) {
      return true;
    }
    int[] arrayOfInt = new int[2];
    Rect localRect = new Rect();
    getLocationOnScreen(arrayOfInt);
    getWindowVisibleDisplayFrame(localRect);
    Context localContext = getContext();
    int i = getWidth();
    int k = getHeight();
    int m = arrayOfInt[1];
    int n = k / 2;
    int j = arrayOfInt[0] + i / 2;
    i = j;
    if (ViewCompat.getLayoutDirection(paramView) == 0) {
      i = getResourcesgetDisplayMetricswidthPixels - j;
    }
    paramView = Toast.makeText(localContext, mItemData.getTitle(), 0);
    if (m + n < localRect.height()) {
      paramView.setGravity(8388661, i, k);
    }
    for (;;)
    {
      paramView.show();
      return true;
      paramView.setGravity(81, 0, k);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    boolean bool = hasText();
    if ((bool) && (mSavedPaddingLeft >= 0)) {
      super.setPadding(mSavedPaddingLeft, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = getMeasuredWidth();
    if (i == Integer.MIN_VALUE) {}
    for (paramInt1 = Math.min(j, mMinWidth);; paramInt1 = mMinWidth)
    {
      Log.d("ActionMenuItemView", "onMeasure:widthMode = " + i + "; widthSize = " + j + "; oldMeasuredWidth = " + k + "; targetWidth = " + paramInt1);
      if ((i != 1073741824) && (mMinWidth > 0) && (k < paramInt1)) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), paramInt2);
      }
      if ((!bool) && (mIcon != null)) {
        super.setPadding((getMeasuredWidth() - mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
      }
      Log.d("ActionMenuItemView", "onMeasure:getMeasuredWidth = " + getMeasuredWidth() + "; getMeasuredHeight = " + getMeasuredHeight());
      return;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((mItemData.hasSubMenu()) && (mForwardingListener != null) && (mForwardingListener.onTouch(this, paramMotionEvent))) {
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean prefersCondensedTitle()
  {
    return true;
  }
  
  public void setCheckable(boolean paramBoolean) {}
  
  public void setChecked(boolean paramBoolean) {}
  
  public void setExpandedFormat(boolean paramBoolean)
  {
    if (mExpandedFormat != paramBoolean)
    {
      mExpandedFormat = paramBoolean;
      if (mItemData != null) {
        mItemData.actionFormatChanged();
      }
    }
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    mIcon = paramDrawable;
    if (paramDrawable != null)
    {
      int m = paramDrawable.getIntrinsicWidth();
      int k = paramDrawable.getIntrinsicHeight();
      int j = k;
      int i = m;
      float f;
      if (m > mMaxIconSize)
      {
        f = mMaxIconSize / m;
        i = mMaxIconSize;
        j = (int)(k * f);
      }
      m = j;
      k = i;
      if (j > mMaxIconSize)
      {
        f = mMaxIconSize / j;
        m = mMaxIconSize;
        k = (int)(i * f);
      }
      paramDrawable.setBounds(0, 0, k, m);
    }
    updateTextButtonVisibility();
  }
  
  public void setIsInSplit(boolean paramBoolean)
  {
    mIsInSplit = paramBoolean;
  }
  
  public void setIsOverflowActor(boolean paramBoolean)
  {
    mIsOverflowActor = paramBoolean;
  }
  
  public void setItemInvoker(MenuBuilder.ItemInvoker paramItemInvoker)
  {
    mItemInvoker = paramItemInvoker;
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mSavedPaddingLeft = paramInt1;
    super.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setPopupCallback(PopupCallback paramPopupCallback)
  {
    mPopupCallback = paramPopupCallback;
  }
  
  public void setShortcut(boolean paramBoolean, char paramChar) {}
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    setContentDescription(mTitle);
    updateTextButtonVisibility();
  }
  
  public boolean showsIcon()
  {
    return true;
  }
  
  class ActionMenuItemForwardingListener
    extends ListPopupWindow.ForwardingListener
  {
    public ActionMenuItemForwardingListener()
    {
      super();
    }
    
    public ListPopupWindow getPopup()
    {
      if (mPopupCallback != null) {
        return mPopupCallback.getPopup();
      }
      return null;
    }
    
    protected boolean onForwardingStarted()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (mItemInvoker != null)
      {
        bool1 = bool2;
        if (mItemInvoker.invokeItem(mItemData))
        {
          ListPopupWindow localListPopupWindow = getPopup();
          bool1 = bool2;
          if (localListPopupWindow != null)
          {
            bool1 = bool2;
            if (localListPopupWindow.isShowing()) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    
    protected boolean onForwardingStopped()
    {
      ListPopupWindow localListPopupWindow = getPopup();
      if (localListPopupWindow != null)
      {
        localListPopupWindow.dismiss();
        return true;
      }
      return false;
    }
  }
  
  public static abstract class PopupCallback
  {
    public abstract ListPopupWindow getPopup();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.ActionMenuItemView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */