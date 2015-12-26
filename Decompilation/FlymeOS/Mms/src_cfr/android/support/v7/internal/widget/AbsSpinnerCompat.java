/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.DataSetObserver
 *  android.graphics.Rect
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.util.AttributeSet
 *  android.util.SparseArray
 *  android.view.View
 *  android.view.View$BaseSavedState
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Adapter
 *  android.widget.SpinnerAdapter
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v7.internal.widget.AbsSpinnerCompat$SavedState$1;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.SpinnerAdapter;

abstract class AbsSpinnerCompat
extends AdapterViewCompat<SpinnerAdapter> {
    SpinnerAdapter mAdapter;
    private DataSetObserver mDataSetObserver;
    int mHeightMeasureSpec;
    final RecycleBin mRecycler;
    int mSelectionBottomPadding = 0;
    int mSelectionLeftPadding = 0;
    int mSelectionRightPadding = 0;
    int mSelectionTopPadding = 0;
    final Rect mSpinnerPadding = new Rect();
    private Rect mTouchFrame;
    int mWidthMeasureSpec;

    AbsSpinnerCompat(Context context) {
        super(context);
        this.mRecycler = new RecycleBin();
        this.initAbsSpinner();
    }

    AbsSpinnerCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    AbsSpinnerCompat(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        this.mRecycler = new RecycleBin();
        this.initAbsSpinner();
    }

    private void initAbsSpinner() {
        this.setFocusable(true);
        this.setWillNotDraw(false);
    }

    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.LayoutParams(-1, -2);
    }

    @Override
    public SpinnerAdapter getAdapter() {
        return this.mAdapter;
    }

    int getChildHeight(View view) {
        return view.getMeasuredHeight();
    }

    int getChildWidth(View view) {
        return view.getMeasuredWidth();
    }

    @Override
    public int getCount() {
        return this.mItemCount;
    }

    @Override
    public View getSelectedView() {
        if (this.mItemCount > 0 && this.mSelectedPosition >= 0) {
            return this.getChildAt(this.mSelectedPosition - this.mFirstPosition);
        }
        return null;
    }

    abstract void layout(int var1, boolean var2);

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onMeasure(int var1_1, int var2_2) {
        var7_3 = View.MeasureSpec.getMode((int)var1_1);
        var3_4 = this.getPaddingLeft();
        var6_5 = this.getPaddingTop();
        var5_6 = this.getPaddingRight();
        var4_7 = this.getPaddingBottom();
        var8_8 = this.mSpinnerPadding;
        if (var3_4 <= this.mSelectionLeftPadding) {
            var3_4 = this.mSelectionLeftPadding;
        }
        var8_8.left = var3_4;
        var8_8 = this.mSpinnerPadding;
        var3_4 = var6_5 > this.mSelectionTopPadding ? var6_5 : this.mSelectionTopPadding;
        var8_8.top = var3_4;
        var8_8 = this.mSpinnerPadding;
        var3_4 = var5_6 > this.mSelectionRightPadding ? var5_6 : this.mSelectionRightPadding;
        var8_8.right = var3_4;
        var8_8 = this.mSpinnerPadding;
        var3_4 = var4_7 > this.mSelectionBottomPadding ? var4_7 : this.mSelectionBottomPadding;
        var8_8.bottom = var3_4;
        if (this.mDataChanged) {
            this.handleDataChanged();
        }
        if ((var3_4 = this.getSelectedItemPosition()) < 0 || this.mAdapter == null || var3_4 >= this.mAdapter.getCount()) ** GOTO lbl-1000
        var8_8 = var9_9 = this.mRecycler.get(var3_4);
        if (var9_9 == null) {
            var8_8 = this.mAdapter.getView(var3_4, null, (ViewGroup)this);
        }
        if (var8_8 != null) {
            this.mRecycler.put(var3_4, (View)var8_8);
            if (var8_8.getLayoutParams() == null) {
                this.mBlockLayoutRequests = true;
                var8_8.setLayoutParams(this.generateDefaultLayoutParams());
                this.mBlockLayoutRequests = false;
            }
            this.measureChild((View)var8_8, var1_1, var2_2);
            var4_7 = this.getChildHeight((View)var8_8) + this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
            var3_4 = this.getChildWidth((View)var8_8) + this.mSpinnerPadding.left + this.mSpinnerPadding.right;
            var5_6 = 0;
        } else lbl-1000: // 2 sources:
        {
            var5_6 = 1;
            var3_4 = 0;
            var4_7 = 0;
        }
        var6_5 = var3_4;
        if (var5_6 != 0) {
            var5_6 = this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
            var6_5 = var3_4;
            var4_7 = var5_6;
            if (var7_3 == 0) {
                var6_5 = this.mSpinnerPadding.left + this.mSpinnerPadding.right;
                var4_7 = var5_6;
            }
        }
        var3_4 = Math.max((int)var4_7, (int)this.getSuggestedMinimumHeight());
        var4_7 = Math.max((int)var6_5, (int)this.getSuggestedMinimumWidth());
        var3_4 = ViewCompat.resolveSizeAndState(var3_4, var2_2, 0);
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(var4_7, var1_1, 0), var3_4);
        this.mHeightMeasureSpec = var2_2;
        this.mWidthMeasureSpec = var1_1;
    }

    public void onRestoreInstanceState(Parcelable object) {
        object = (SavedState)((Object)object);
        super.onRestoreInstanceState(object.getSuperState());
        if (object.selectedId >= 0) {
            this.mDataChanged = true;
            this.mNeedSync = true;
            this.mSyncRowId = object.selectedId;
            this.mSyncPosition = object.position;
            this.mSyncMode = 0;
            this.requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.selectedId = this.getSelectedItemId();
        if (savedState.selectedId >= 0) {
            savedState.position = this.getSelectedItemPosition();
            return savedState;
        }
        savedState.position = -1;
        return savedState;
    }

    public int pointToPosition(int n2, int n3) {
        Rect rect;
        Rect rect2 = rect = this.mTouchFrame;
        if (rect == null) {
            rect2 = this.mTouchFrame = new Rect();
        }
        for (int i2 = this.getChildCount() - 1; i2 >= 0; --i2) {
            rect = this.getChildAt(i2);
            if (rect.getVisibility() != 0) continue;
            rect.getHitRect(rect2);
            if (!rect2.contains(n2, n3)) continue;
            return this.mFirstPosition + i2;
        }
        return -1;
    }

    void recycleAllViews() {
        int n2 = this.getChildCount();
        RecycleBin recycleBin = this.mRecycler;
        int n3 = this.mFirstPosition;
        for (int i2 = 0; i2 < n2; ++i2) {
            recycleBin.put(n3 + i2, this.getChildAt(i2));
        }
    }

    public void requestLayout() {
        if (!this.mBlockLayoutRequests) {
            super.requestLayout();
        }
    }

    void resetList() {
        this.mDataChanged = false;
        this.mNeedSync = false;
        this.removeAllViewsInLayout();
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        this.setSelectedPositionInt(-1);
        this.setNextSelectedPositionInt(-1);
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        int n2 = -1;
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
            this.resetList();
        }
        this.mAdapter = spinnerAdapter;
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        if (this.mAdapter != null) {
            this.mOldItemCount = this.mItemCount;
            this.mItemCount = this.mAdapter.getCount();
            this.checkFocus();
            this.mDataSetObserver = new AdapterViewCompat.AdapterDataSetObserver();
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
            if (this.mItemCount > 0) {
                n2 = 0;
            }
            this.setSelectedPositionInt(n2);
            this.setNextSelectedPositionInt(n2);
            if (this.mItemCount == 0) {
                this.checkSelectionChanged();
            }
        } else {
            this.checkFocus();
            this.resetList();
            this.checkSelectionChanged();
        }
        this.requestLayout();
    }

    @Override
    public void setSelection(int n2) {
        this.setNextSelectedPositionInt(n2);
        this.requestLayout();
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSelection(int n2, boolean bl2) {
        bl2 = bl2 && this.mFirstPosition <= n2 && n2 <= this.mFirstPosition + this.getChildCount() - 1;
        this.setSelectionInt(n2, bl2);
    }

    void setSelectionInt(int n2, boolean bl2) {
        if (n2 != this.mOldSelectedPosition) {
            this.mBlockLayoutRequests = true;
            int n3 = this.mSelectedPosition;
            this.setNextSelectedPositionInt(n2);
            this.layout(n2 - n3, bl2);
            this.mBlockLayoutRequests = false;
        }
    }

    class RecycleBin {
        private final SparseArray<View> mScrapHeap;

        RecycleBin() {
            this.mScrapHeap = new SparseArray();
        }

        void clear() {
            SparseArray<View> sparseArray = this.mScrapHeap;
            int n2 = sparseArray.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                View view = (View)sparseArray.valueAt(i2);
                if (view == null) continue;
                AbsSpinnerCompat.this.removeDetachedView(view, true);
            }
            sparseArray.clear();
        }

        View get(int n2) {
            View view = (View)this.mScrapHeap.get(n2);
            if (view != null) {
                this.mScrapHeap.delete(n2);
            }
            return view;
        }

        public void put(int n2, View view) {
            this.mScrapHeap.put(n2, (Object)view);
        }
    }

    static class SavedState
    extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AbsSpinnerCompat$SavedState$1();
        int position;
        long selectedId;

        SavedState(Parcel parcel) {
            super(parcel);
            this.selectedId = parcel.readLong();
            this.position = parcel.readInt();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "AbsSpinner.SavedState{" + Integer.toHexString((int)System.identityHashCode((Object)((Object)this))) + " selectedId=" + this.selectedId + " position=" + this.position + "}";
        }

        public void writeToParcel(Parcel parcel, int n2) {
            super.writeToParcel(parcel, n2);
            parcel.writeLong(this.selectedId);
            parcel.writeInt(this.position);
        }
    }

}

