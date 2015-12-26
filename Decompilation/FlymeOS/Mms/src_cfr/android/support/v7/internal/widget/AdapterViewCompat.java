/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.DataSetObserver
 *  android.os.Parcelable
 *  android.os.SystemClock
 *  android.util.AttributeSet
 *  android.util.SparseArray
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewDebug
 *  android.view.ViewDebug$CapturedViewProperty
 *  android.view.ViewDebug$ExportedProperty
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.accessibility.AccessibilityEvent
 *  android.widget.Adapter
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;
import android.widget.AdapterView;

public abstract class AdapterViewCompat<T extends Adapter>
extends ViewGroup {
    public static final int INVALID_POSITION = -1;
    public static final long INVALID_ROW_ID = Long.MIN_VALUE;
    static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;
    static final int ITEM_VIEW_TYPE_IGNORE = -1;
    static final int SYNC_FIRST_POSITION = 1;
    static final int SYNC_MAX_DURATION_MILLIS = 100;
    static final int SYNC_SELECTED_POSITION = 0;
    boolean mBlockLayoutRequests = false;
    boolean mDataChanged;
    private boolean mDesiredFocusableInTouchModeState;
    private boolean mDesiredFocusableState;
    private View mEmptyView;
    @ViewDebug.ExportedProperty(category="scrolling")
    int mFirstPosition = 0;
    boolean mInLayout = false;
    @ViewDebug.ExportedProperty(category="list")
    int mItemCount;
    private int mLayoutHeight;
    boolean mNeedSync = false;
    @ViewDebug.ExportedProperty(category="list")
    int mNextSelectedPosition = -1;
    long mNextSelectedRowId = Long.MIN_VALUE;
    int mOldItemCount;
    int mOldSelectedPosition = -1;
    long mOldSelectedRowId = Long.MIN_VALUE;
    OnItemClickListener mOnItemClickListener;
    OnItemLongClickListener mOnItemLongClickListener;
    OnItemSelectedListener mOnItemSelectedListener;
    @ViewDebug.ExportedProperty(category="list")
    int mSelectedPosition = -1;
    long mSelectedRowId = Long.MIN_VALUE;
    private AdapterViewCompat<T> mSelectionNotifier;
    int mSpecificTop;
    long mSyncHeight;
    int mSyncMode;
    int mSyncPosition;
    long mSyncRowId = Long.MIN_VALUE;

    AdapterViewCompat(Context context) {
        super(context);
    }

    AdapterViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    AdapterViewCompat(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
    }

    private void fireOnSelected() {
        if (this.mOnItemSelectedListener == null) {
            return;
        }
        int n2 = this.getSelectedItemPosition();
        if (n2 >= 0) {
            View view = this.getSelectedView();
            this.mOnItemSelectedListener.onItemSelected(this, view, n2, this.getAdapter().getItemId(n2));
            return;
        }
        this.mOnItemSelectedListener.onNothingSelected(this);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateEmptyStatus(boolean bl2) {
        if (this.isInFilterMode()) {
            bl2 = false;
        }
        if (bl2) {
            if (this.mEmptyView != null) {
                this.mEmptyView.setVisibility(0);
                this.setVisibility(8);
            } else {
                this.setVisibility(0);
            }
            if (this.mDataChanged) {
                this.onLayout(false, this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
            }
            return;
        }
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        this.setVisibility(0);
    }

    public void addView(View view) {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    public void addView(View view, int n2) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    public void addView(View view, int n2, ViewGroup.LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
    }

    protected boolean canAnimate() {
        if (super.canAnimate() && this.mItemCount > 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    void checkFocus() {
        boolean bl2 = false;
        T t2 = this.getAdapter();
        boolean bl3 = t2 == null || t2.getCount() == 0;
        bl3 = !bl3 || this.isInFilterMode();
        boolean bl4 = bl3 && this.mDesiredFocusableInTouchModeState;
        super.setFocusableInTouchMode(bl4);
        bl4 = bl3 && this.mDesiredFocusableState;
        super.setFocusable(bl4);
        if (this.mEmptyView != null) {
            block3 : {
                if (t2 != null) {
                    bl4 = bl2;
                    if (!t2.isEmpty()) break block3;
                }
                bl4 = true;
            }
            this.updateEmptyStatus(bl4);
        }
    }

    void checkSelectionChanged() {
        if (this.mSelectedPosition != this.mOldSelectedPosition || this.mSelectedRowId != this.mOldSelectedRowId) {
            this.selectionChanged();
            this.mOldSelectedPosition = this.mSelectedPosition;
            this.mOldSelectedRowId = this.mSelectedRowId;
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        View view = this.getSelectedView();
        if (view != null && view.getVisibility() == 0 && view.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
            return true;
        }
        return false;
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.dispatchThawSelfOnly(sparseArray);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        this.dispatchFreezeSelfOnly(sparseArray);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    int findSyncPosition() {
        var7_1 = this.mItemCount;
        if (var7_1 == 0) {
            return -1;
        }
        var8_3 = this.mSyncRowId;
        var1_4 = this.mSyncPosition;
        if (var8_3 == Long.MIN_VALUE) {
            return -1;
        }
        var1_4 = Math.min((int)(var7_1 - 1), (int)Math.max((int)0, (int)var1_4));
        var10_5 = SystemClock.uptimeMillis();
        var2_6 = false;
        var12_7 = this.getAdapter();
        if (var12_7 == null) {
            return -1;
        }
        var4_9 = var1_4;
        var5_2 = var1_4;
        var3_8 = var1_4;
        var1_4 = var5_2;
        ** GOTO lbl28
lbl-1000: // 1 sources:
        {
            if (var6_10) ** GOTO lbl-1000
            do {
                if (var2_6 && var5_2 == 0) lbl-1000: // 2 sources:
                {
                    var2_6 = false;
                    var1_4 = ++var3_8;
                } else if (var5_2 != 0 || !var2_6 && !var6_10) {
                    var2_6 = true;
                    var1_4 = --var4_9;
                }
lbl28: // 5 sources:
                if (SystemClock.uptimeMillis() > var10_5 + 100) return -1;
                var5_2 = var1_4;
                if (var12_7.getItemId(var1_4) == var8_3) return var5_2;
                var5_2 = var3_8 == var7_1 - 1 ? 1 : 0;
                var6_10 = var4_9 == 0;
            } while (var5_2 == 0);
            ** while (!var6_10)
        }
lbl35: // 1 sources:
        return -1;
    }

    public abstract T getAdapter();

    @ViewDebug.CapturedViewProperty
    public int getCount() {
        return this.mItemCount;
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    public int getFirstVisiblePosition() {
        return this.mFirstPosition;
    }

    public Object getItemAtPosition(int n2) {
        T t2 = this.getAdapter();
        if (t2 == null || n2 < 0) {
            return null;
        }
        return t2.getItem(n2);
    }

    public long getItemIdAtPosition(int n2) {
        T t2 = this.getAdapter();
        if (t2 == null || n2 < 0) {
            return Long.MIN_VALUE;
        }
        return t2.getItemId(n2);
    }

    public int getLastVisiblePosition() {
        return this.mFirstPosition + this.getChildCount() - 1;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    public final OnItemSelectedListener getOnItemSelectedListener() {
        return this.mOnItemSelectedListener;
    }

    public int getPositionForView(View view) {
        do {
            try {
                View view2 = (View)view.getParent();
                boolean bl2 = view2.equals((Object)((Object)this));
                if (bl2) break;
                view = view2;
                continue;
            }
            catch (ClassCastException var1_2) {
                return -1;
            }
        } while (true);
        int n2 = this.getChildCount();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (!this.getChildAt(i2).equals((Object)view)) continue;
            return i2 + this.mFirstPosition;
        }
        return -1;
    }

    public Object getSelectedItem() {
        T t2 = this.getAdapter();
        int n2 = this.getSelectedItemPosition();
        if (t2 != null && t2.getCount() > 0 && n2 >= 0) {
            return t2.getItem(n2);
        }
        return null;
    }

    @ViewDebug.CapturedViewProperty
    public long getSelectedItemId() {
        return this.mNextSelectedRowId;
    }

    @ViewDebug.CapturedViewProperty
    public int getSelectedItemPosition() {
        return this.mNextSelectedPosition;
    }

    public abstract View getSelectedView();

    /*
     * Enabled aggressive block sorting
     */
    void handleDataChanged() {
        int n2;
        int n3 = this.mItemCount;
        if (n3 > 0) {
            if (this.mNeedSync) {
                this.mNeedSync = false;
                n2 = this.findSyncPosition();
                if (n2 >= 0 && this.lookForSelectablePosition(n2, true) == n2) {
                    this.setNextSelectedPositionInt(n2);
                    return;
                }
            }
            if ((n2 = 0) == 0) {
                int n4;
                int n5 = n4 = this.getSelectedItemPosition();
                if (n4 >= n3) {
                    n5 = n3 - 1;
                }
                n4 = n5;
                if (n5 < 0) {
                    n4 = 0;
                }
                if ((n5 = this.lookForSelectablePosition(n4, true)) < 0) {
                    n5 = this.lookForSelectablePosition(n4, false);
                }
                if (n5 >= 0) {
                    this.setNextSelectedPositionInt(n5);
                    this.checkSelectionChanged();
                    return;
                }
            }
        } else {
            n2 = 0;
        }
        if (n2 == 0) {
            this.mSelectedPosition = -1;
            this.mSelectedRowId = Long.MIN_VALUE;
            this.mNextSelectedPosition = -1;
            this.mNextSelectedRowId = Long.MIN_VALUE;
            this.mNeedSync = false;
            this.checkSelectionChanged();
        }
    }

    boolean isInFilterMode() {
        return false;
    }

    int lookForSelectablePosition(int n2, boolean bl2) {
        return n2;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.mSelectionNotifier);
    }

    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        this.mLayoutHeight = this.getHeight();
    }

    public boolean performItemClick(View view, int n2, long l2) {
        boolean bl2 = false;
        if (this.mOnItemClickListener != null) {
            this.playSoundEffect(0);
            if (view != null) {
                view.sendAccessibilityEvent(1);
            }
            this.mOnItemClickListener.onItemClick(this, view, n2, l2);
            bl2 = true;
        }
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void rememberSyncState() {
        if (this.getChildCount() <= 0) return;
        this.mNeedSync = true;
        this.mSyncHeight = this.mLayoutHeight;
        if (this.mSelectedPosition >= 0) {
            View view = this.getChildAt(this.mSelectedPosition - this.mFirstPosition);
            this.mSyncRowId = this.mNextSelectedRowId;
            this.mSyncPosition = this.mNextSelectedPosition;
            if (view != null) {
                this.mSpecificTop = view.getTop();
            }
            this.mSyncMode = 0;
            return;
        }
        View view = this.getChildAt(0);
        T t2 = this.getAdapter();
        this.mSyncRowId = this.mFirstPosition >= 0 && this.mFirstPosition < t2.getCount() ? t2.getItemId(this.mFirstPosition) : -1;
        this.mSyncPosition = this.mFirstPosition;
        if (view != null) {
            this.mSpecificTop = view.getTop();
        }
        this.mSyncMode = 1;
    }

    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    public void removeView(View view) {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    public void removeViewAt(int n2) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    /*
     * Enabled aggressive block sorting
     */
    void selectionChanged() {
        if (this.mOnItemSelectedListener != null) {
            if (this.mInLayout || this.mBlockLayoutRequests) {
                if (this.mSelectionNotifier == null) {
                    this.mSelectionNotifier = new SelectionNotifier();
                }
                this.post(this.mSelectionNotifier);
            } else {
                this.fireOnSelected();
            }
        }
        if (this.mSelectedPosition != -1 && this.isShown() && !this.isInTouchMode()) {
            this.sendAccessibilityEvent(4);
        }
    }

    public abstract void setAdapter(T var1);

    /*
     * Enabled aggressive block sorting
     */
    public void setEmptyView(View object) {
        this.mEmptyView = object;
        object = this.getAdapter();
        boolean bl2 = object == null || object.isEmpty();
        this.updateEmptyStatus(bl2);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setFocusable(boolean var1_1) {
        block4 : {
            var3_2 = true;
            var4_3 = this.getAdapter();
            if (var4_3 != null && var4_3.getCount() != 0) ** GOTO lbl18
            var2_4 = true;
lbl5: // 2 sources:
            do {
                this.mDesiredFocusableState = var1_1;
                if (!var1_1) {
                    this.mDesiredFocusableInTouchModeState = false;
                }
                if (!var1_1) break block4;
                var1_1 = var3_2;
                if (!var2_4) ** GOTO lbl14
                if (this.isInFilterMode()) {
                    var1_1 = var3_2;
lbl14: // 3 sources:
                    do {
                        super.setFocusable(var1_1);
                        return;
                        break;
                    } while (true);
                }
                break block4;
                break;
            } while (true);
lbl18: // 1 sources:
            var2_4 = false;
            ** while (true)
        }
        var1_1 = false;
        ** while (true)
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setFocusableInTouchMode(boolean var1_1) {
        block4 : {
            var3_2 = true;
            var4_3 = this.getAdapter();
            if (var4_3 != null && var4_3.getCount() != 0) ** GOTO lbl18
            var2_4 = true;
lbl5: // 2 sources:
            do {
                this.mDesiredFocusableInTouchModeState = var1_1;
                if (var1_1) {
                    this.mDesiredFocusableState = true;
                }
                if (!var1_1) break block4;
                var1_1 = var3_2;
                if (!var2_4) ** GOTO lbl14
                if (this.isInFilterMode()) {
                    var1_1 = var3_2;
lbl14: // 3 sources:
                    do {
                        super.setFocusableInTouchMode(var1_1);
                        return;
                        break;
                    } while (true);
                }
                break block4;
                break;
            } while (true);
lbl18: // 1 sources:
            var2_4 = false;
            ** while (true)
        }
        var1_1 = false;
        ** while (true)
    }

    void setNextSelectedPositionInt(int n2) {
        this.mNextSelectedPosition = n2;
        this.mNextSelectedRowId = this.getItemIdAtPosition(n2);
        if (this.mNeedSync && this.mSyncMode == 0 && n2 >= 0) {
            this.mSyncPosition = n2;
            this.mSyncRowId = this.mNextSelectedRowId;
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        if (!this.isLongClickable()) {
            this.setLongClickable(true);
        }
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    void setSelectedPositionInt(int n2) {
        this.mSelectedPosition = n2;
        this.mSelectedRowId = this.getItemIdAtPosition(n2);
    }

    public abstract void setSelection(int var1);

    public static class AdapterContextMenuInfo
    implements ContextMenu.ContextMenuInfo {
        public long id;
        public int position;
        public View targetView;

        public AdapterContextMenuInfo(View view, int n2, long l2) {
            this.targetView = view;
            this.position = n2;
            this.id = l2;
        }
    }

    class AdapterDataSetObserver
    extends DataSetObserver {
        private Parcelable mInstanceState;

        AdapterDataSetObserver() {
            this.mInstanceState = null;
        }

        public void clearSavedState() {
            this.mInstanceState = null;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onChanged() {
            AdapterViewCompat.this.mDataChanged = true;
            AdapterViewCompat.this.mOldItemCount = AdapterViewCompat.this.mItemCount;
            AdapterViewCompat.this.mItemCount = AdapterViewCompat.this.getAdapter().getCount();
            if (AdapterViewCompat.this.getAdapter().hasStableIds() && this.mInstanceState != null && AdapterViewCompat.this.mOldItemCount == 0 && AdapterViewCompat.this.mItemCount > 0) {
                AdapterViewCompat.this.onRestoreInstanceState(this.mInstanceState);
                this.mInstanceState = null;
            } else {
                AdapterViewCompat.this.rememberSyncState();
            }
            AdapterViewCompat.this.checkFocus();
            AdapterViewCompat.this.requestLayout();
        }

        public void onInvalidated() {
            AdapterViewCompat.this.mDataChanged = true;
            if (AdapterViewCompat.this.getAdapter().hasStableIds()) {
                this.mInstanceState = AdapterViewCompat.this.onSaveInstanceState();
            }
            AdapterViewCompat.this.mOldItemCount = AdapterViewCompat.this.mItemCount;
            AdapterViewCompat.this.mItemCount = 0;
            AdapterViewCompat.this.mSelectedPosition = -1;
            AdapterViewCompat.this.mSelectedRowId = Long.MIN_VALUE;
            AdapterViewCompat.this.mNextSelectedPosition = -1;
            AdapterViewCompat.this.mNextSelectedRowId = Long.MIN_VALUE;
            AdapterViewCompat.this.mNeedSync = false;
            AdapterViewCompat.this.checkFocus();
            AdapterViewCompat.this.requestLayout();
        }
    }

    public static interface OnItemClickListener {
        public void onItemClick(AdapterViewCompat<?> var1, View var2, int var3, long var4);
    }

    class OnItemClickListenerWrapper
    implements AdapterView.OnItemClickListener {
        private final OnItemClickListener mWrappedListener;

        public OnItemClickListenerWrapper(OnItemClickListener onItemClickListener) {
            this.mWrappedListener = onItemClickListener;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int n2, long l2) {
            this.mWrappedListener.onItemClick(AdapterViewCompat.this, view, n2, l2);
        }
    }

    public static interface OnItemLongClickListener {
        public boolean onItemLongClick(AdapterViewCompat<?> var1, View var2, int var3, long var4);
    }

    public static interface OnItemSelectedListener {
        public void onItemSelected(AdapterViewCompat<?> var1, View var2, int var3, long var4);

        public void onNothingSelected(AdapterViewCompat<?> var1);
    }

    class SelectionNotifier
    implements Runnable {
        private SelectionNotifier() {
        }

        @Override
        public void run() {
            if (AdapterViewCompat.this.mDataChanged) {
                if (AdapterViewCompat.this.getAdapter() != null) {
                    AdapterViewCompat.this.post((Runnable)this);
                }
                return;
            }
            AdapterViewCompat.this.fireOnSelected();
        }
    }

}

