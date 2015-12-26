package com.meizu.common.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SpinnerAdapter;

public abstract class AbsSpinner
  extends AdapterView<SpinnerAdapter>
{
  SpinnerAdapter mAdapter;
  private DataSetObserver mDataSetObserver;
  int mHeightMeasureSpec;
  final RecycleBin mRecycler = new RecycleBin();
  int mSelectionBottomPadding = 0;
  int mSelectionLeftPadding = 0;
  int mSelectionRightPadding = 0;
  int mSelectionTopPadding = 0;
  final Rect mSpinnerPadding = new Rect();
  private Rect mTouchFrame;
  int mWidthMeasureSpec;
  
  public AbsSpinner(Context paramContext)
  {
    super(paramContext);
    initAbsSpinner();
  }
  
  public AbsSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public AbsSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initAbsSpinner();
  }
  
  private void initAbsSpinner()
  {
    setFocusable(true);
    setWillNotDraw(false);
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new ViewGroup.LayoutParams(-1, -2);
  }
  
  public SpinnerAdapter getAdapter()
  {
    return mAdapter;
  }
  
  int getChildHeight(View paramView)
  {
    return paramView.getMeasuredHeight();
  }
  
  int getChildWidth(View paramView)
  {
    return paramView.getMeasuredWidth();
  }
  
  public int getCount()
  {
    return mItemCount;
  }
  
  public View getSelectedView()
  {
    if ((mItemCount > 0) && (mSelectedPosition >= 0)) {
      return getChildAt(mSelectedPosition - mFirstPosition);
    }
    return null;
  }
  
  abstract void layout(int paramInt, boolean paramBoolean);
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(AbsSpinner.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(AbsSpinner.class.getName());
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int n = View.MeasureSpec.getMode(paramInt1);
    Object localObject = mSpinnerPadding;
    int i;
    label56:
    label84:
    label112:
    int j;
    int k;
    if (getPaddingLeft() > mSelectionLeftPadding)
    {
      i = getPaddingLeft();
      left = i;
      localObject = mSpinnerPadding;
      if (getPaddingTop() <= mSelectionTopPadding) {
        break label435;
      }
      i = getPaddingTop();
      top = i;
      localObject = mSpinnerPadding;
      if (getPaddingRight() <= mSelectionRightPadding) {
        break label443;
      }
      i = getPaddingRight();
      right = i;
      localObject = mSpinnerPadding;
      if (getPaddingBottom() <= mSelectionBottomPadding) {
        break label451;
      }
      i = getPaddingBottom();
      bottom = i;
      if (mDataChanged) {
        handleDataChanged();
      }
      i = getSelectedItemPosition();
      if ((i < 0) || (mAdapter == null) || (i >= mAdapter.getCount())) {
        break label459;
      }
      View localView = mRecycler.get(i);
      localObject = localView;
      if (localView == null)
      {
        localView = mAdapter.getView(i, null, this);
        localObject = localView;
        if (localView.getImportantForAccessibility() == 0)
        {
          localView.setImportantForAccessibility(1);
          localObject = localView;
        }
      }
      if (localObject != null) {
        mRecycler.put(i, (View)localObject);
      }
      if (localObject == null) {
        break label459;
      }
      if (((View)localObject).getLayoutParams() == null)
      {
        mBlockLayoutRequests = true;
        ((View)localObject).setLayoutParams(generateDefaultLayoutParams());
        mBlockLayoutRequests = false;
      }
      measureChild((View)localObject, paramInt1, paramInt2);
      j = getChildHeight((View)localObject) + mSpinnerPadding.top + mSpinnerPadding.bottom;
      i = getChildWidth((View)localObject) + mSpinnerPadding.left + mSpinnerPadding.right;
      k = 0;
    }
    for (;;)
    {
      int m = i;
      if (k != 0)
      {
        k = mSpinnerPadding.top + mSpinnerPadding.bottom;
        m = i;
        j = k;
        if (n == 0)
        {
          m = mSpinnerPadding.left + mSpinnerPadding.right;
          j = k;
        }
      }
      i = Math.max(j, getSuggestedMinimumHeight());
      j = Math.max(m, getSuggestedMinimumWidth());
      i = resolveSizeAndState(i, paramInt2, 0);
      setMeasuredDimension(resolveSizeAndState(j, paramInt1, 0), i);
      mHeightMeasureSpec = paramInt2;
      mWidthMeasureSpec = paramInt1;
      return;
      i = mSelectionLeftPadding;
      break;
      label435:
      i = mSelectionTopPadding;
      break label56;
      label443:
      i = mSelectionRightPadding;
      break label84;
      label451:
      i = mSelectionBottomPadding;
      break label112;
      label459:
      k = 1;
      i = 0;
      j = 0;
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (selectedId >= 0L)
    {
      mDataChanged = true;
      mNeedSync = true;
      mSyncRowId = selectedId;
      mSyncPosition = position;
      mSyncMode = 0;
      requestLayout();
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    selectedId = getSelectedItemId();
    if (selectedId >= 0L)
    {
      position = getSelectedItemPosition();
      return localSavedState;
    }
    position = -1;
    return localSavedState;
  }
  
  public int pointToPosition(int paramInt1, int paramInt2)
  {
    Object localObject2 = mTouchFrame;
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      mTouchFrame = new Rect();
      localObject1 = mTouchFrame;
    }
    int i = getChildCount() - 1;
    while (i >= 0)
    {
      localObject2 = getChildAt(i);
      if (((View)localObject2).getVisibility() == 0)
      {
        ((View)localObject2).getHitRect((Rect)localObject1);
        if (((Rect)localObject1).contains(paramInt1, paramInt2)) {
          return mFirstPosition + i;
        }
      }
      i -= 1;
    }
    return -1;
  }
  
  void recycleAllViews()
  {
    int j = getChildCount();
    RecycleBin localRecycleBin = mRecycler;
    int k = mFirstPosition;
    int i = 0;
    while (i < j)
    {
      localRecycleBin.put(k + i, getChildAt(i));
      i += 1;
    }
  }
  
  public void requestLayout()
  {
    if (!mBlockLayoutRequests) {
      super.requestLayout();
    }
  }
  
  void resetList()
  {
    mDataChanged = false;
    mNeedSync = false;
    removeAllViewsInLayout();
    mOldSelectedPosition = -1;
    mOldSelectedRowId = Long.MIN_VALUE;
    setSelectedPositionInt(-1);
    setNextSelectedPositionInt(-1);
    invalidate();
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    int i = -1;
    if (mAdapter != null)
    {
      mAdapter.unregisterDataSetObserver(mDataSetObserver);
      resetList();
    }
    mAdapter = paramSpinnerAdapter;
    mOldSelectedPosition = -1;
    mOldSelectedRowId = Long.MIN_VALUE;
    if (mAdapter != null)
    {
      mOldItemCount = mItemCount;
      mItemCount = mAdapter.getCount();
      checkFocus();
      mDataSetObserver = new AdapterView.AdapterDataSetObserver(this);
      mAdapter.registerDataSetObserver(mDataSetObserver);
      if (mItemCount > 0) {
        i = 0;
      }
      setSelectedPositionInt(i);
      setNextSelectedPositionInt(i);
      if (mItemCount == 0) {
        checkSelectionChanged();
      }
    }
    for (;;)
    {
      requestLayout();
      return;
      checkFocus();
      resetList();
      checkSelectionChanged();
    }
  }
  
  public void setSelection(int paramInt)
  {
    setNextSelectedPositionInt(paramInt);
    requestLayout();
    invalidate();
  }
  
  public void setSelection(int paramInt, boolean paramBoolean)
  {
    if ((paramBoolean) && (mFirstPosition <= paramInt) && (paramInt <= mFirstPosition + getChildCount() - 1)) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      setSelectionInt(paramInt, paramBoolean);
      return;
    }
  }
  
  void setSelectionInt(int paramInt, boolean paramBoolean)
  {
    if (paramInt != mOldSelectedPosition)
    {
      mBlockLayoutRequests = true;
      int i = mSelectedPosition;
      setNextSelectedPositionInt(paramInt);
      layout(paramInt - i, paramBoolean);
      mBlockLayoutRequests = false;
    }
  }
  
  class RecycleBin
  {
    private final SparseArray<View> mScrapHeap = new SparseArray();
    
    RecycleBin() {}
    
    void clear()
    {
      SparseArray localSparseArray = mScrapHeap;
      int j = localSparseArray.size();
      int i = 0;
      while (i < j)
      {
        View localView = (View)localSparseArray.valueAt(i);
        if (localView != null) {
          removeDetachedView(localView, true);
        }
        i += 1;
      }
      localSparseArray.clear();
    }
    
    View get(int paramInt)
    {
      View localView = (View)mScrapHeap.get(paramInt);
      if (localView != null) {
        mScrapHeap.delete(paramInt);
      }
      return localView;
    }
    
    public void put(int paramInt, View paramView)
    {
      mScrapHeap.put(paramInt, paramView);
    }
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new AbsSpinner.SavedState.1();
    int position;
    long selectedId;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      selectedId = paramParcel.readLong();
      position = paramParcel.readInt();
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + selectedId + " position=" + position + "}";
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeLong(selectedId);
      paramParcel.writeInt(position);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AbsSpinner
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */