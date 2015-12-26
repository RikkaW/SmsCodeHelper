package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.support.v7.internal.widget.ListViewCompat;
import android.support.v7.internal.widget.MzSlidePopupWindow;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;

public class ListPopupWindow
{
  private static final boolean DEBUG = false;
  private static final int EXPAND_LIST_TIMEOUT = 250;
  public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
  public static final int INPUT_METHOD_NEEDED = 1;
  public static final int INPUT_METHOD_NOT_NEEDED = 2;
  public static final int MATCH_PARENT = -1;
  public static final int POSITION_PROMPT_ABOVE = 0;
  public static final int POSITION_PROMPT_BELOW = 1;
  private static final String TAG = "ListPopupWindow";
  public static final int WRAP_CONTENT = -2;
  private static Method sClipToWindowEnabledMethod;
  private ListAdapter mAdapter;
  private Context mContext;
  private boolean mDropDownAlwaysVisible = false;
  private View mDropDownAnchorView;
  private int mDropDownGravity = 0;
  private int mDropDownHeight = -2;
  private int mDropDownHorizontalOffset;
  private DropDownListView mDropDownList;
  private Drawable mDropDownListHighlight;
  private int mDropDownVerticalOffset;
  private boolean mDropDownVerticalOffsetSet;
  private int mDropDownWidth = -2;
  private boolean mForceIgnoreOutsideTouch = false;
  private Handler mHandler = new Handler();
  private final ListSelectorHider mHideSelector = new ListSelectorHider(null);
  private AdapterView.OnItemClickListener mItemClickListener;
  private AdapterView.OnItemSelectedListener mItemSelectedListener;
  private int mLayoutDirection;
  int mListItemExpandMaximum = Integer.MAX_VALUE;
  private boolean mModal;
  private DataSetObserver mObserver;
  private PopupWindow mPopup;
  private int mPromptPosition = 0;
  private View mPromptView;
  private final ResizePopupRunnable mResizePopupRunnable = new ResizePopupRunnable(null);
  private final PopupScrollListener mScrollListener = new PopupScrollListener(null);
  private Runnable mShowDropDownRunnable;
  private Rect mTempRect = new Rect();
  private final PopupTouchInterceptor mTouchInterceptor = new PopupTouchInterceptor(null);
  
  static
  {
    try
    {
      sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[] { Boolean.TYPE });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
    }
  }
  
  public ListPopupWindow(Context paramContext)
  {
    this(paramContext, null, R.attr.listPopupWindowStyle);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.listPopupWindowStyle);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this(paramContext, paramAttributeSet, paramInt1, paramInt2, false);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    mContext = paramContext;
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ListPopupWindow, paramInt1, paramInt2);
    mDropDownHorizontalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
    mDropDownVerticalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
    if (mDropDownVerticalOffset != 0) {
      mDropDownVerticalOffsetSet = true;
    }
    localTypedArray.recycle();
    if (paramBoolean) {}
    for (paramContext = new MzSlidePopupWindow(paramContext, paramAttributeSet, paramInt1);; paramContext = new AppCompatPopupWindow(paramContext, paramAttributeSet, paramInt1))
    {
      mPopup = paramContext;
      mPopup.setInputMethodMode(1);
      mLayoutDirection = TextUtilsCompat.getLayoutDirectionFromLocale(mContext.getResources().getConfiguration().locale);
      return;
    }
  }
  
  private int buildDropDown()
  {
    Object localObject2;
    boolean bool;
    Object localObject1;
    View localView;
    LinearLayout.LayoutParams localLayoutParams;
    label253:
    int i;
    int j;
    if (mDropDownList == null)
    {
      localObject2 = mContext;
      mShowDropDownRunnable = new ListPopupWindow.2(this);
      if (!mModal)
      {
        bool = true;
        mDropDownList = new DropDownListView((Context)localObject2, bool);
        if (mDropDownListHighlight != null) {
          mDropDownList.setSelector(mDropDownListHighlight);
        }
        mDropDownList.setAdapter(mAdapter);
        mDropDownList.setOnItemClickListener(mItemClickListener);
        mDropDownList.setFocusable(true);
        mDropDownList.setFocusableInTouchMode(true);
        mDropDownList.setOnItemSelectedListener(new ListPopupWindow.3(this));
        mDropDownList.setOnScrollListener(mScrollListener);
        if (mItemSelectedListener != null) {
          mDropDownList.setOnItemSelectedListener(mItemSelectedListener);
        }
        localObject1 = mDropDownList;
        localView = mPromptView;
        if (localView == null) {
          break label690;
        }
        localObject2 = new LinearLayout((Context)localObject2);
        ((LinearLayout)localObject2).setOrientation(1);
        localLayoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0F);
        switch (mPromptPosition)
        {
        default: 
          Log.e("ListPopupWindow", "Invalid hint position " + mPromptPosition);
          localView.measure(View.MeasureSpec.makeMeasureSpec(mDropDownWidth, Integer.MIN_VALUE), 0);
          localObject1 = (LinearLayout.LayoutParams)localView.getLayoutParams();
          i = localView.getMeasuredHeight();
          j = topMargin;
          i = bottomMargin + (i + j);
          localObject1 = localObject2;
        }
      }
    }
    for (;;)
    {
      mPopup.setContentView((View)localObject1);
      for (;;)
      {
        label314:
        localObject1 = mPopup.getBackground();
        if (localObject1 != null)
        {
          ((Drawable)localObject1).getPadding(mTempRect);
          j = mTempRect.top + mTempRect.bottom;
          if (mDropDownVerticalOffsetSet) {
            break label682;
          }
          mDropDownVerticalOffset = (-mTempRect.top);
        }
        label682:
        for (;;)
        {
          if (mPopup.getInputMethodMode() == 2) {}
          int m = mPopup.getMaxAvailableHeight(getAnchorView(), mDropDownVerticalOffset);
          if ((mDropDownAlwaysVisible) || (mDropDownHeight == -1))
          {
            return m + j;
            bool = false;
            break;
            ((LinearLayout)localObject2).addView((View)localObject1, localLayoutParams);
            ((LinearLayout)localObject2).addView(localView);
            break label253;
            ((LinearLayout)localObject2).addView(localView);
            ((LinearLayout)localObject2).addView((View)localObject1, localLayoutParams);
            break label253;
            localObject1 = (ViewGroup)mPopup.getContentView();
            localObject1 = mPromptView;
            if (localObject1 == null) {
              break label685;
            }
            localObject2 = (LinearLayout.LayoutParams)((View)localObject1).getLayoutParams();
            i = ((View)localObject1).getMeasuredHeight();
            j = topMargin;
            i = bottomMargin + (i + j);
            break label314;
            mTempRect.setEmpty();
            j = 0;
            continue;
          }
          int k;
          switch (mDropDownWidth)
          {
          default: 
            k = View.MeasureSpec.makeMeasureSpec(mDropDownWidth, 1073741824);
          }
          for (;;)
          {
            m = mDropDownList.measureHeightOfChildrenCompat(k, 0, -1, m - i, -1);
            k = i;
            if (m > 0) {
              k = i + j;
            }
            return m + k;
            k = View.MeasureSpec.makeMeasureSpec(mContext.getResources().getDisplayMetrics().widthPixels - (mTempRect.left + mTempRect.right), Integer.MIN_VALUE);
            continue;
            k = View.MeasureSpec.makeMeasureSpec(mContext.getResources().getDisplayMetrics().widthPixels - (mTempRect.left + mTempRect.right), 1073741824);
          }
        }
        label685:
        i = 0;
      }
      label690:
      i = 0;
    }
  }
  
  private static boolean isConfirmKey(int paramInt)
  {
    return (paramInt == 66) || (paramInt == 23);
  }
  
  private void removePromptView()
  {
    if (mPromptView != null)
    {
      ViewParent localViewParent = mPromptView.getParent();
      if ((localViewParent instanceof ViewGroup)) {
        ((ViewGroup)localViewParent).removeView(mPromptView);
      }
    }
  }
  
  private void setPopupClipToScreenEnabled(boolean paramBoolean)
  {
    if (sClipToWindowEnabledMethod != null) {}
    try
    {
      sClipToWindowEnabledMethod.invoke(mPopup, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception localException)
    {
      Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
    }
  }
  
  public void clearListSelection()
  {
    DropDownListView localDropDownListView = mDropDownList;
    if (localDropDownListView != null)
    {
      DropDownListView.access$502(localDropDownListView, true);
      localDropDownListView.requestLayout();
    }
  }
  
  public View.OnTouchListener createDragToOpenListener(View paramView)
  {
    return new ListPopupWindow.1(this, paramView);
  }
  
  public void dismiss()
  {
    mPopup.dismiss();
    removePromptView();
    mPopup.setContentView(null);
    mDropDownList = null;
    mHandler.removeCallbacks(mResizePopupRunnable);
  }
  
  public void dismiss(boolean paramBoolean)
  {
    if ((mPopup instanceof MzSlidePopupWindow))
    {
      ((MzSlidePopupWindow)mPopup).dismiss(paramBoolean);
      return;
    }
    dismiss();
  }
  
  public View getAnchorView()
  {
    return mDropDownAnchorView;
  }
  
  public int getAnimationStyle()
  {
    return mPopup.getAnimationStyle();
  }
  
  public Drawable getBackground()
  {
    return mPopup.getBackground();
  }
  
  public int getHeight()
  {
    return mDropDownHeight;
  }
  
  public int getHorizontalOffset()
  {
    return mDropDownHorizontalOffset;
  }
  
  public int getInputMethodMode()
  {
    return mPopup.getInputMethodMode();
  }
  
  public ListView getListView()
  {
    return mDropDownList;
  }
  
  public int getPromptPosition()
  {
    return mPromptPosition;
  }
  
  public Object getSelectedItem()
  {
    if (!isShowing()) {
      return null;
    }
    return mDropDownList.getSelectedItem();
  }
  
  public long getSelectedItemId()
  {
    if (!isShowing()) {
      return Long.MIN_VALUE;
    }
    return mDropDownList.getSelectedItemId();
  }
  
  public int getSelectedItemPosition()
  {
    if (!isShowing()) {
      return -1;
    }
    return mDropDownList.getSelectedItemPosition();
  }
  
  public View getSelectedView()
  {
    if (!isShowing()) {
      return null;
    }
    return mDropDownList.getSelectedView();
  }
  
  public int getSoftInputMode()
  {
    return mPopup.getSoftInputMode();
  }
  
  public int getVerticalOffset()
  {
    if (!mDropDownVerticalOffsetSet) {
      return 0;
    }
    return mDropDownVerticalOffset;
  }
  
  public int getWidth()
  {
    return mDropDownWidth;
  }
  
  public boolean isDropDownAlwaysVisible()
  {
    return mDropDownAlwaysVisible;
  }
  
  public boolean isInputMethodNotNeeded()
  {
    return mPopup.getInputMethodMode() == 2;
  }
  
  public boolean isModal()
  {
    return mModal;
  }
  
  public boolean isShowing()
  {
    return mPopup.isShowing();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    int m;
    int k;
    int i;
    int j;
    if ((isShowing()) && (paramInt != 62) && ((mDropDownList.getSelectedItemPosition() >= 0) || (!isConfirmKey(paramInt))))
    {
      m = mDropDownList.getSelectedItemPosition();
      ListAdapter localListAdapter;
      if (!mPopup.isAboveAnchor())
      {
        k = 1;
        localListAdapter = mAdapter;
        i = Integer.MAX_VALUE;
        j = Integer.MIN_VALUE;
        if (localListAdapter != null)
        {
          boolean bool = localListAdapter.areAllItemsEnabled();
          if (!bool) {
            break label162;
          }
          i = 0;
          label87:
          if (!bool) {
            break label175;
          }
        }
      }
      label162:
      label175:
      for (j = localListAdapter.getCount() - 1;; j = mDropDownList.lookForSelectablePosition(localListAdapter.getCount() - 1, false))
      {
        if (((k == 0) || (paramInt != 19) || (m > i)) && ((k != 0) || (paramInt != 20) || (m < j))) {
          break label197;
        }
        clearListSelection();
        mPopup.setInputMethodMode(1);
        show();
        return true;
        k = 0;
        break;
        i = mDropDownList.lookForSelectablePosition(0, true);
        break label87;
      }
      label197:
      DropDownListView.access$502(mDropDownList, false);
      if (!mDropDownList.onKeyDown(paramInt, paramKeyEvent)) {
        break label282;
      }
      mPopup.setInputMethodMode(2);
      mDropDownList.requestFocusFromTouch();
      show();
      switch (paramInt)
      {
      }
    }
    label282:
    do
    {
      do
      {
        return false;
        if ((k == 0) || (paramInt != 20)) {
          break;
        }
      } while (m != j);
      return true;
    } while ((k != 0) || (paramInt != 19) || (m != i));
    return true;
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (isShowing()))
    {
      Object localObject = mDropDownAnchorView;
      if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getRepeatCount() == 0))
      {
        localObject = ((View)localObject).getKeyDispatcherState();
        if (localObject != null) {
          ((KeyEvent.DispatcherState)localObject).startTracking(paramKeyEvent, this);
        }
        return true;
      }
      if (paramKeyEvent.getAction() == 1)
      {
        localObject = ((View)localObject).getKeyDispatcherState();
        if (localObject != null) {
          ((KeyEvent.DispatcherState)localObject).handleUpEvent(paramKeyEvent);
        }
        if ((paramKeyEvent.isTracking()) && (!paramKeyEvent.isCanceled()))
        {
          dismiss();
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((isShowing()) && (mDropDownList.getSelectedItemPosition() >= 0))
    {
      boolean bool = mDropDownList.onKeyUp(paramInt, paramKeyEvent);
      if ((bool) && (isConfirmKey(paramInt))) {
        dismiss();
      }
      return bool;
    }
    return false;
  }
  
  public boolean performItemClick(int paramInt)
  {
    if (isShowing())
    {
      if (mItemClickListener != null)
      {
        DropDownListView localDropDownListView = mDropDownList;
        View localView = localDropDownListView.getChildAt(paramInt - localDropDownListView.getFirstVisiblePosition());
        ListAdapter localListAdapter = localDropDownListView.getAdapter();
        mItemClickListener.onItemClick(localDropDownListView, localView, paramInt, localListAdapter.getItemId(paramInt));
      }
      return true;
    }
    return false;
  }
  
  public void postShow()
  {
    mHandler.post(mShowDropDownRunnable);
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (mObserver == null) {
      mObserver = new PopupDataSetObserver(null);
    }
    for (;;)
    {
      mAdapter = paramListAdapter;
      if (mAdapter != null) {
        paramListAdapter.registerDataSetObserver(mObserver);
      }
      if (mDropDownList != null) {
        mDropDownList.setAdapter(mAdapter);
      }
      return;
      if (mAdapter != null) {
        mAdapter.unregisterDataSetObserver(mObserver);
      }
    }
  }
  
  public void setAnchorView(View paramView)
  {
    mDropDownAnchorView = paramView;
  }
  
  public void setAnimationStyle(int paramInt)
  {
    mPopup.setAnimationStyle(paramInt);
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    mPopup.setBackgroundDrawable(paramDrawable);
  }
  
  public void setClipToScreenEnabled(boolean paramBoolean)
  {
    setPopupClipToScreenEnabled(paramBoolean);
  }
  
  public void setClippingEnabled(boolean paramBoolean)
  {
    mPopup.setClippingEnabled(paramBoolean);
  }
  
  public void setContentWidth(int paramInt)
  {
    Drawable localDrawable = mPopup.getBackground();
    if (localDrawable != null)
    {
      localDrawable.getPadding(mTempRect);
      mDropDownWidth = (mTempRect.left + mTempRect.right + paramInt);
      return;
    }
    setWidth(paramInt);
  }
  
  public void setDropDownAlwaysVisible(boolean paramBoolean)
  {
    mDropDownAlwaysVisible = paramBoolean;
  }
  
  public void setDropDownGravity(int paramInt)
  {
    mDropDownGravity = paramInt;
  }
  
  public void setForceIgnoreOutsideTouch(boolean paramBoolean)
  {
    mForceIgnoreOutsideTouch = paramBoolean;
  }
  
  public void setHeight(int paramInt)
  {
    mDropDownHeight = paramInt;
  }
  
  public void setHorizontalOffset(int paramInt)
  {
    mDropDownHorizontalOffset = paramInt;
  }
  
  public void setInputMethodMode(int paramInt)
  {
    mPopup.setInputMethodMode(paramInt);
  }
  
  void setListItemExpandMax(int paramInt)
  {
    mListItemExpandMaximum = paramInt;
  }
  
  public void setListSelector(Drawable paramDrawable)
  {
    mDropDownListHighlight = paramDrawable;
  }
  
  public void setModal(boolean paramBoolean)
  {
    mModal = paramBoolean;
    mPopup.setFocusable(paramBoolean);
  }
  
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    mPopup.setOnDismissListener(paramOnDismissListener);
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    mItemClickListener = paramOnItemClickListener;
  }
  
  public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener)
  {
    mItemSelectedListener = paramOnItemSelectedListener;
  }
  
  public void setPromptPosition(int paramInt)
  {
    mPromptPosition = paramInt;
  }
  
  public void setPromptView(View paramView)
  {
    boolean bool = isShowing();
    if (bool) {
      removePromptView();
    }
    mPromptView = paramView;
    if (bool) {
      show();
    }
  }
  
  public void setSelection(int paramInt)
  {
    DropDownListView localDropDownListView = mDropDownList;
    if ((isShowing()) && (localDropDownListView != null))
    {
      DropDownListView.access$502(localDropDownListView, false);
      localDropDownListView.setSelection(paramInt);
      if ((Build.VERSION.SDK_INT >= 11) && (localDropDownListView.getChoiceMode() != 0)) {
        localDropDownListView.setItemChecked(paramInt, true);
      }
    }
  }
  
  public void setSoftInputMode(int paramInt)
  {
    mPopup.setSoftInputMode(paramInt);
  }
  
  public void setVerticalOffset(int paramInt)
  {
    mDropDownVerticalOffset = paramInt;
    mDropDownVerticalOffsetSet = true;
  }
  
  public void setWidth(int paramInt)
  {
    mDropDownWidth = paramInt;
  }
  
  public void show()
  {
    boolean bool1 = true;
    boolean bool2 = false;
    int k = -1;
    int j = buildDropDown();
    boolean bool3 = isInputMethodNotNeeded();
    int i;
    label52:
    PopupWindow localPopupWindow;
    if (mPopup.isShowing())
    {
      if (mDropDownWidth == -1)
      {
        i = -1;
        if (mDropDownHeight != -1) {
          break label207;
        }
        if (!bool3) {
          break label166;
        }
        if (!bool3) {
          break label176;
        }
        localPopupWindow = mPopup;
        if (mDropDownWidth != -1) {
          break label171;
        }
        label71:
        localPopupWindow.setWindowLayoutMode(k, 0);
      }
      for (;;)
      {
        localPopupWindow = mPopup;
        bool1 = bool2;
        if (!mForceIgnoreOutsideTouch)
        {
          bool1 = bool2;
          if (!mDropDownAlwaysVisible) {
            bool1 = true;
          }
        }
        localPopupWindow.setOutsideTouchable(bool1);
        mPopup.update(getAnchorView(), mDropDownHorizontalOffset, mDropDownVerticalOffset, i, j);
        return;
        if (mDropDownWidth == -2)
        {
          i = getAnchorView().getWidth();
          break;
        }
        i = mDropDownWidth;
        break;
        label166:
        j = -1;
        break label52;
        label171:
        k = 0;
        break label71;
        label176:
        localPopupWindow = mPopup;
        if (mDropDownWidth == -1) {}
        for (k = -1;; k = 0)
        {
          localPopupWindow.setWindowLayoutMode(k, -1);
          break;
        }
        label207:
        if (mDropDownHeight != -2) {
          j = mDropDownHeight;
        }
      }
    }
    if (mDropDownWidth == -1)
    {
      i = -1;
      label234:
      if (mDropDownHeight != -1) {
        break label412;
      }
      j = -1;
      label244:
      mPopup.setWindowLayoutMode(i, j);
      setPopupClipToScreenEnabled(true);
      localPopupWindow = mPopup;
      if ((mForceIgnoreOutsideTouch) || (mDropDownAlwaysVisible)) {
        break label450;
      }
    }
    for (;;)
    {
      localPopupWindow.setOutsideTouchable(bool1);
      mPopup.setTouchInterceptor(mTouchInterceptor);
      PopupWindowCompat.showAsDropDown(mPopup, getAnchorView(), mDropDownHorizontalOffset, mDropDownVerticalOffset, mDropDownGravity);
      mDropDownList.setSelection(-1);
      if ((!mModal) || (mDropDownList.isInTouchMode())) {
        clearListSelection();
      }
      if (mModal) {
        break;
      }
      mHandler.post(mHideSelector);
      return;
      if (mDropDownWidth == -2)
      {
        mPopup.setWidth(getAnchorView().getWidth());
        i = 0;
        break label234;
      }
      mPopup.setWidth(mDropDownWidth);
      i = 0;
      break label234;
      label412:
      if (mDropDownHeight == -2)
      {
        mPopup.setHeight(j);
        j = 0;
        break label244;
      }
      mPopup.setHeight(mDropDownHeight);
      j = 0;
      break label244;
      label450:
      bool1 = false;
    }
  }
  
  static class DropDownListView
    extends ListViewCompat
  {
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private ListViewAutoScrollHelper mScrollHelper;
    
    public DropDownListView(Context paramContext, boolean paramBoolean)
    {
      super(null, R.attr.dropDownListViewStyle);
      mHijackFocus = paramBoolean;
      setCacheColorHint(0);
    }
    
    private void clearPressedItem()
    {
      mDrawsInPressedState = false;
      setPressed(false);
      drawableStateChanged();
      if (mClickAnimation != null)
      {
        mClickAnimation.cancel();
        mClickAnimation = null;
      }
    }
    
    private void clickPressedItem(View paramView, int paramInt)
    {
      performItemClick(paramView, paramInt, getItemIdAtPosition(paramInt));
    }
    
    private void setPressedItem(View paramView, int paramInt, float paramFloat1, float paramFloat2)
    {
      mDrawsInPressedState = true;
      setPressed(true);
      layoutChildren();
      setSelection(paramInt);
      positionSelectorLikeTouchCompat(paramInt, paramView, paramFloat1, paramFloat2);
      setSelectorEnabled(false);
      refreshDrawableState();
    }
    
    public boolean hasFocus()
    {
      return (mHijackFocus) || (super.hasFocus());
    }
    
    public boolean hasWindowFocus()
    {
      return (mHijackFocus) || (super.hasWindowFocus());
    }
    
    public boolean isFocused()
    {
      return (mHijackFocus) || (super.isFocused());
    }
    
    public boolean isInTouchMode()
    {
      return ((mHijackFocus) && (mListSelectionHidden)) || (super.isInTouchMode());
    }
    
    public boolean onForwardedEvent(MotionEvent paramMotionEvent, int paramInt)
    {
      int i = MotionEventCompat.getActionMasked(paramMotionEvent);
      switch (i)
      {
      default: 
        paramInt = 0;
      case 3: 
        for (bool = true;; bool = false)
        {
          label37:
          if ((!bool) || (paramInt != 0)) {
            clearPressedItem();
          }
          if (!bool) {
            break;
          }
          if (mScrollHelper == null) {
            mScrollHelper = new ListViewAutoScrollHelper(this);
          }
          mScrollHelper.setEnabled(true);
          mScrollHelper.onTouch(this, paramMotionEvent);
          label93:
          return bool;
          paramInt = 0;
        }
      }
      for (boolean bool = false;; bool = true)
      {
        int j = paramMotionEvent.findPointerIndex(paramInt);
        if (j < 0)
        {
          paramInt = 0;
          bool = false;
          break label37;
        }
        paramInt = (int)paramMotionEvent.getX(j);
        j = (int)paramMotionEvent.getY(j);
        int k = pointToPosition(paramInt, j);
        if (k == -1)
        {
          paramInt = 1;
          break label37;
        }
        View localView = getChildAt(k - getFirstVisiblePosition());
        setPressedItem(localView, k, paramInt, j);
        if (i != 1) {
          break;
        }
        clickPressedItem(localView, k);
        break;
        if (mScrollHelper == null) {
          break label93;
        }
        mScrollHelper.setEnabled(false);
        return bool;
      }
    }
    
    protected boolean touchModeDrawsInPressedStateCompat()
    {
      return (mDrawsInPressedState) || (super.touchModeDrawsInPressedStateCompat());
    }
  }
  
  public static abstract class ForwardingListener
    implements View.OnTouchListener
  {
    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    private final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation = new int[2];
    private Runnable mTriggerLongPress;
    private boolean mWasLongPress;
    
    public ForwardingListener(View paramView)
    {
      mSrc = paramView;
      mScaledTouchSlop = ViewConfiguration.get(paramView.getContext()).getScaledTouchSlop();
      mTapTimeout = ViewConfiguration.getTapTimeout();
      mLongPressTimeout = ((mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2);
    }
    
    private void clearCallbacks()
    {
      if (mTriggerLongPress != null) {
        mSrc.removeCallbacks(mTriggerLongPress);
      }
      if (mDisallowIntercept != null) {
        mSrc.removeCallbacks(mDisallowIntercept);
      }
    }
    
    private void onLongPress()
    {
      clearCallbacks();
      View localView = mSrc;
      if ((!localView.isEnabled()) || (localView.isLongClickable())) {}
      while (!onForwardingStarted()) {
        return;
      }
      if (localView.getParent() != null) {
        localView.getParent().requestDisallowInterceptTouchEvent(true);
      }
      long l = SystemClock.uptimeMillis();
      MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
      localView.onTouchEvent(localMotionEvent);
      localMotionEvent.recycle();
      mForwarding = true;
      mWasLongPress = true;
    }
    
    private boolean onTouchForwarded(MotionEvent paramMotionEvent)
    {
      boolean bool1 = true;
      View localView = mSrc;
      Object localObject = getPopup();
      if ((localObject == null) || (!((ListPopupWindow)localObject).isShowing())) {}
      do
      {
        return false;
        localObject = mDropDownList;
      } while ((localObject == null) || (!((ListPopupWindow.DropDownListView)localObject).isShown()));
      MotionEvent localMotionEvent = MotionEvent.obtainNoHistory(paramMotionEvent);
      toGlobalMotionEvent(localView, localMotionEvent);
      toLocalMotionEvent((View)localObject, localMotionEvent);
      boolean bool2 = ((ListPopupWindow.DropDownListView)localObject).onForwardedEvent(localMotionEvent, mActivePointerId);
      localMotionEvent.recycle();
      int i = MotionEventCompat.getActionMasked(paramMotionEvent);
      if ((i != 1) && (i != 3))
      {
        i = 1;
        if ((!bool2) || (i == 0)) {
          break label124;
        }
      }
      for (;;)
      {
        return bool1;
        i = 0;
        break;
        label124:
        bool1 = false;
      }
    }
    
    private boolean onTouchObserved(MotionEvent paramMotionEvent)
    {
      View localView = mSrc;
      if (!localView.isEnabled()) {}
      int i;
      do
      {
        return false;
        switch (MotionEventCompat.getActionMasked(paramMotionEvent))
        {
        default: 
          return false;
        case 0: 
          mActivePointerId = paramMotionEvent.getPointerId(0);
          mWasLongPress = false;
          if (mDisallowIntercept == null) {
            mDisallowIntercept = new DisallowIntercept(null);
          }
          localView.postDelayed(mDisallowIntercept, mTapTimeout);
          if (mTriggerLongPress == null) {
            mTriggerLongPress = new TriggerLongPress(null);
          }
          localView.postDelayed(mTriggerLongPress, mLongPressTimeout);
          return false;
        case 2: 
          i = paramMotionEvent.findPointerIndex(mActivePointerId);
        }
      } while ((i < 0) || (pointInView(localView, paramMotionEvent.getX(i), paramMotionEvent.getY(i), mScaledTouchSlop)));
      clearCallbacks();
      if (localView.getParent() != null) {
        localView.getParent().requestDisallowInterceptTouchEvent(true);
      }
      return true;
      clearCallbacks();
      return false;
    }
    
    private static boolean pointInView(View paramView, float paramFloat1, float paramFloat2, float paramFloat3)
    {
      return (paramFloat1 >= -paramFloat3) && (paramFloat2 >= -paramFloat3) && (paramFloat1 < paramView.getRight() - paramView.getLeft() + paramFloat3) && (paramFloat2 < paramView.getBottom() - paramView.getTop() + paramFloat3);
    }
    
    private boolean toGlobalMotionEvent(View paramView, MotionEvent paramMotionEvent)
    {
      int[] arrayOfInt = mTmpLocation;
      paramView.getLocationOnScreen(arrayOfInt);
      paramMotionEvent.offsetLocation(arrayOfInt[0], arrayOfInt[1]);
      return true;
    }
    
    private boolean toLocalMotionEvent(View paramView, MotionEvent paramMotionEvent)
    {
      int[] arrayOfInt = mTmpLocation;
      paramView.getLocationOnScreen(arrayOfInt);
      paramMotionEvent.offsetLocation(-arrayOfInt[0], -arrayOfInt[1]);
      return true;
    }
    
    public abstract ListPopupWindow getPopup();
    
    public boolean onForwardingStarted()
    {
      ListPopupWindow localListPopupWindow = getPopup();
      if ((localListPopupWindow != null) && (!localListPopupWindow.isShowing())) {
        localListPopupWindow.show();
      }
      return true;
    }
    
    public boolean onForwardingStopped()
    {
      ListPopupWindow localListPopupWindow = getPopup();
      if ((localListPopupWindow != null) && (localListPopupWindow.isShowing())) {
        localListPopupWindow.dismiss();
      }
      return true;
    }
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      boolean bool2 = false;
      boolean bool3 = mForwarding;
      if (bool3)
      {
        if (mWasLongPress) {
          bool1 = onTouchForwarded(paramMotionEvent);
        }
        for (;;)
        {
          mForwarding = bool1;
          if (!bool1)
          {
            bool1 = bool2;
            if (!bool3) {}
          }
          else
          {
            bool1 = true;
          }
          return bool1;
          if ((onTouchForwarded(paramMotionEvent)) || (!onForwardingStopped())) {
            bool1 = true;
          } else {
            bool1 = false;
          }
        }
      }
      if ((onTouchObserved(paramMotionEvent)) && (onForwardingStarted())) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        if (bool1)
        {
          long l = SystemClock.uptimeMillis();
          paramView = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
          mSrc.onTouchEvent(paramView);
          paramView.recycle();
        }
        break;
      }
    }
    
    class DisallowIntercept
      implements Runnable
    {
      private DisallowIntercept() {}
      
      public void run()
      {
        ViewParent localViewParent = mSrc.getParent();
        if (localViewParent != null) {
          localViewParent.requestDisallowInterceptTouchEvent(true);
        }
      }
    }
    
    class TriggerLongPress
      implements Runnable
    {
      private TriggerLongPress() {}
      
      public void run()
      {
        ListPopupWindow.ForwardingListener.this.onLongPress();
      }
    }
  }
  
  class ListSelectorHider
    implements Runnable
  {
    private ListSelectorHider() {}
    
    public void run()
    {
      clearListSelection();
    }
  }
  
  class PopupDataSetObserver
    extends DataSetObserver
  {
    private PopupDataSetObserver() {}
    
    public void onChanged()
    {
      if (isShowing()) {
        show();
      }
    }
    
    public void onInvalidated()
    {
      dismiss();
    }
  }
  
  class PopupScrollListener
    implements AbsListView.OnScrollListener
  {
    private PopupScrollListener() {}
    
    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      if ((paramInt == 1) && (!isInputMethodNotNeeded()) && (mPopup.getContentView() != null))
      {
        mHandler.removeCallbacks(mResizePopupRunnable);
        mResizePopupRunnable.run();
      }
    }
  }
  
  class PopupTouchInterceptor
    implements View.OnTouchListener
  {
    private PopupTouchInterceptor() {}
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      int i = paramMotionEvent.getAction();
      int j = (int)paramMotionEvent.getX();
      int k = (int)paramMotionEvent.getY();
      if ((i == 0) && (mPopup != null) && (mPopup.isShowing()) && (j >= 0) && (j < mPopup.getWidth()) && (k >= 0) && (k < mPopup.getHeight())) {
        mHandler.postDelayed(mResizePopupRunnable, 250L);
      }
      for (;;)
      {
        return false;
        if (i == 1) {
          mHandler.removeCallbacks(mResizePopupRunnable);
        }
      }
    }
  }
  
  class ResizePopupRunnable
    implements Runnable
  {
    private ResizePopupRunnable() {}
    
    public void run()
    {
      if ((mDropDownList != null) && (mDropDownList.getCount() > mDropDownList.getChildCount()) && (mDropDownList.getChildCount() <= mListItemExpandMaximum))
      {
        mPopup.setInputMethodMode(2);
        show();
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */