/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.pm.ApplicationInfo
 *  android.content.res.Resources
 *  android.database.DataSetObserver
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.Adapter
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  android.widget.SpinnerAdapter
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.AbsSpinnerCompat;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.SpinnerCompat$1;
import android.support.v7.internal.widget.SpinnerCompat$2;
import android.support.v7.internal.widget.SpinnerCompat$DropdownPopup$1;
import android.support.v7.internal.widget.SpinnerCompat$DropdownPopup$2;
import android.support.v7.internal.widget.SpinnerCompat$DropdownPopup$3;
import android.support.v7.internal.widget.SpinnerCompat$SavedState$1;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SpinnerAdapter;

class SpinnerCompat
extends AbsSpinnerCompat
implements DialogInterface.OnClickListener {
    private static final int MAX_ITEMS_MEASURED = 15;
    public static final int MODE_DIALOG = 0;
    public static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "Spinner";
    private boolean mDisableChildrenWhenDisabled;
    int mDropDownWidth;
    private ListPopupWindow.ForwardingListener mForwardingListener;
    private int mGravity;
    private SpinnerPopup mPopup;
    private DropDownAdapter mTempAdapter;
    private Rect mTempRect = new Rect();
    private final TintManager mTintManager;

    SpinnerCompat(Context context) {
        this(context, null);
    }

    SpinnerCompat(Context context, int n2) {
        this(context, null, R.attr.spinnerStyle, n2);
    }

    SpinnerCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.spinnerStyle);
    }

    SpinnerCompat(Context context, AttributeSet attributeSet, int n2) {
        this(context, attributeSet, n2, -1);
    }

    /*
     * Enabled aggressive block sorting
     */
    SpinnerCompat(Context object, AttributeSet attributeSet, int n2, int n3) {
        super((Context)object, attributeSet, n2);
        TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes((Context)object, attributeSet, R.styleable.Spinner, n2, 0);
        if (tintTypedArray.hasValue(R.styleable.Spinner_android_background)) {
            this.setBackgroundDrawable(tintTypedArray.getDrawable(R.styleable.Spinner_android_background));
        }
        int n4 = n3;
        if (n3 == -1) {
            n4 = tintTypedArray.getInt(R.styleable.Spinner_spinnerMode, 0);
        }
        switch (n4) {
            case 0: {
                this.mPopup = new DialogPopup(null);
            }
            default: {
                break;
            }
            case 1: {
                object = new DropdownPopup((Context)object, attributeSet, n2);
                this.mDropDownWidth = tintTypedArray.getLayoutDimension(R.styleable.Spinner_android_dropDownWidth, -2);
                object.setBackgroundDrawable(tintTypedArray.getDrawable(R.styleable.Spinner_android_popupBackground));
                this.mPopup = object;
                this.mForwardingListener = new SpinnerCompat$1(this, (View)this, (DropdownPopup)object);
            }
        }
        this.mGravity = tintTypedArray.getInt(R.styleable.Spinner_android_gravity, 17);
        this.mPopup.setPromptText(tintTypedArray.getString(R.styleable.Spinner_prompt));
        this.mDisableChildrenWhenDisabled = tintTypedArray.getBoolean(R.styleable.Spinner_disableChildrenWhenDisabled, false);
        tintTypedArray.recycle();
        if (this.mTempAdapter != null) {
            this.mPopup.setAdapter(this.mTempAdapter);
            this.mTempAdapter = null;
        }
        this.mTintManager = tintTypedArray.getTintManager();
    }

    static /* synthetic */ SpinnerPopup access$100(SpinnerCompat spinnerCompat) {
        return spinnerCompat.mPopup;
    }

    private View makeView(int n2, boolean bl2) {
        View view;
        if (!this.mDataChanged && (view = this.mRecycler.get(n2)) != null) {
            this.setUpChild(view, bl2);
            return view;
        }
        view = this.mAdapter.getView(n2, null, (ViewGroup)this);
        this.setUpChild(view, bl2);
        return view;
    }

    private void setUpChild(View view, boolean bl2) {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams2 = this.generateDefaultLayoutParams();
        }
        if (bl2) {
            this.addViewInLayout(view, 0, layoutParams2);
        }
        view.setSelected(this.hasFocus());
        if (this.mDisableChildrenWhenDisabled) {
            view.setEnabled(this.isEnabled());
        }
        int n2 = ViewGroup.getChildMeasureSpec((int)this.mHeightMeasureSpec, (int)(this.mSpinnerPadding.top + this.mSpinnerPadding.bottom), (int)layoutParams2.height);
        view.measure(ViewGroup.getChildMeasureSpec((int)this.mWidthMeasureSpec, (int)(this.mSpinnerPadding.left + this.mSpinnerPadding.right), (int)layoutParams2.width), n2);
        n2 = this.mSpinnerPadding.top + (this.getMeasuredHeight() - this.mSpinnerPadding.bottom - this.mSpinnerPadding.top - view.getMeasuredHeight()) / 2;
        int n3 = view.getMeasuredHeight();
        view.layout(0, n2, view.getMeasuredWidth() + 0, n3 + n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public int getBaseline() {
        View view;
        int n2 = -1;
        View view2 = null;
        if (this.getChildCount() > 0) {
            view = this.getChildAt(0);
        } else {
            view = view2;
            if (this.mAdapter != null) {
                view = view2;
                if (this.mAdapter.getCount() > 0) {
                    view = this.makeView(0, false);
                    this.mRecycler.put(0, view);
                }
            }
        }
        int n3 = n2;
        if (view == null) return n3;
        int n4 = view.getBaseline();
        n3 = n2;
        if (n4 < 0) return n3;
        return view.getTop() + n4;
    }

    public int getDropDownHorizontalOffset() {
        return this.mPopup.getHorizontalOffset();
    }

    public int getDropDownVerticalOffset() {
        return this.mPopup.getVerticalOffset();
    }

    public int getDropDownWidth() {
        return this.mDropDownWidth;
    }

    public Drawable getPopupBackground() {
        return this.mPopup.getBackground();
    }

    public CharSequence getPrompt() {
        return this.mPopup.getHintText();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    void layout(int n2, boolean bl2) {
        n2 = this.mSpinnerPadding.left;
        int n3 = this.getRight() - this.getLeft() - this.mSpinnerPadding.left - this.mSpinnerPadding.right;
        if (this.mDataChanged) {
            this.handleDataChanged();
        }
        if (this.mItemCount == 0) {
            this.resetList();
            return;
        }
        if (this.mNextSelectedPosition >= 0) {
            this.setSelectedPositionInt(this.mNextSelectedPosition);
        }
        this.recycleAllViews();
        this.removeAllViewsInLayout();
        this.mFirstPosition = this.mSelectedPosition;
        if (this.mAdapter != null) {
            View view = this.makeView(this.mSelectedPosition, true);
            int n4 = view.getMeasuredWidth();
            int n5 = ViewCompat.getLayoutDirection((View)this);
            switch (GravityCompat.getAbsoluteGravity(this.mGravity, n5) & 7) {
                case 1: {
                    n2 = n2 + n3 / 2 - n4 / 2;
                }
                default: {
                    break;
                }
                case 5: {
                    n2 = n2 + n3 - n4;
                }
            }
            view.offsetLeftAndRight(n2);
        }
        this.mRecycler.clear();
        this.invalidate();
        this.checkSelectionChanged();
        this.mDataChanged = false;
        this.mNeedSync = false;
        this.setNextSelectedPositionInt(this.mSelectedPosition);
    }

    int measureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable2) {
        if (spinnerAdapter == null) {
            return 0;
        }
        int n2 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
        int n3 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
        int n4 = Math.max((int)0, (int)this.getSelectedItemPosition());
        int n5 = Math.min((int)spinnerAdapter.getCount(), (int)(n4 + 15));
        int n6 = Math.max((int)0, (int)(n4 - (15 - (n5 - n4))));
        View view = null;
        int n7 = 0;
        n4 = 0;
        while (n6 < n5) {
            int n8 = spinnerAdapter.getItemViewType(n6);
            if (n8 != n4) {
                view = null;
                n4 = n8;
            }
            if ((view = spinnerAdapter.getView(n6, view, (ViewGroup)this)).getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(n2, n3);
            n7 = Math.max((int)n7, (int)view.getMeasuredWidth());
            ++n6;
        }
        if (drawable2 != null) {
            drawable2.getPadding(this.mTempRect);
            return this.mTempRect.left + this.mTempRect.right + n7;
        }
        return n7;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        this.setSelection(n2);
        dialogInterface.dismiss();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPopup != null && this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    @Override
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        super.onLayout(bl2, n2, n3, n4, n5);
        this.mInLayout = true;
        this.layout(0, false);
        this.mInLayout = false;
    }

    @Override
    protected void onMeasure(int n2, int n3) {
        super.onMeasure(n2, n3);
        if (this.mPopup != null && View.MeasureSpec.getMode((int)n2) == Integer.MIN_VALUE) {
            this.setMeasuredDimension(Math.min((int)Math.max((int)this.getMeasuredWidth(), (int)this.measureContentWidth(this.getAdapter(), this.getBackground())), (int)View.MeasureSpec.getSize((int)n2)), this.getMeasuredHeight());
        }
    }

    @Override
    public void onRestoreInstanceState(Parcelable object) {
        object = (SavedState)((Object)object);
        super.onRestoreInstanceState(object.getSuperState());
        if (object.showDropdown && (object = this.getViewTreeObserver()) != null) {
            object.addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)new SpinnerCompat$2(this));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        boolean bl2 = this.mPopup != null && this.mPopup.isShowing();
        savedState.showDropdown = bl2;
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mForwardingListener != null && this.mForwardingListener.onTouch((View)this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean performClick() {
        boolean bl2;
        boolean bl3 = bl2 = super.performClick();
        if (!bl2) {
            bl3 = bl2 = true;
            if (!this.mPopup.isShowing()) {
                this.mPopup.show();
                bl3 = bl2;
            }
        }
        return bl3;
    }

    @Override
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        super.setAdapter(spinnerAdapter);
        this.mRecycler.clear();
        if (this.getContext().getApplicationInfo().targetSdkVersion >= 21 && spinnerAdapter != null && spinnerAdapter.getViewTypeCount() != 1) {
            throw new IllegalArgumentException("Spinner adapter view type count must be 1");
        }
        if (this.mPopup != null) {
            this.mPopup.setAdapter(new DropDownAdapter(spinnerAdapter));
            return;
        }
        this.mTempAdapter = new DropDownAdapter(spinnerAdapter);
    }

    public void setDropDownHorizontalOffset(int n2) {
        this.mPopup.setHorizontalOffset(n2);
    }

    public void setDropDownVerticalOffset(int n2) {
        this.mPopup.setVerticalOffset(n2);
    }

    public void setDropDownWidth(int n2) {
        if (!(this.mPopup instanceof DropdownPopup)) {
            Log.e((String)"Spinner", (String)"Cannot set dropdown width for MODE_DIALOG, ignoring");
            return;
        }
        this.mDropDownWidth = n2;
    }

    public void setEnabled(boolean bl2) {
        super.setEnabled(bl2);
        if (this.mDisableChildrenWhenDisabled) {
            int n2 = this.getChildCount();
            for (int i2 = 0; i2 < n2; ++i2) {
                this.getChildAt(i2).setEnabled(bl2);
            }
        }
    }

    public void setGravity(int n2) {
        if (this.mGravity != n2) {
            int n3 = n2;
            if ((n2 & 7) == 0) {
                n3 = n2 | 8388611;
            }
            this.mGravity = n3;
            this.requestLayout();
        }
    }

    @Override
    public void setOnItemClickListener(AdapterViewCompat.OnItemClickListener onItemClickListener) {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }

    void setOnItemClickListenerInt(AdapterViewCompat.OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    public void setPopupBackgroundDrawable(Drawable drawable2) {
        if (!(this.mPopup instanceof DropdownPopup)) {
            Log.e((String)"Spinner", (String)"setPopupBackgroundDrawable: incompatible spinner mode; ignoring...");
            return;
        }
        ((DropdownPopup)this.mPopup).setBackgroundDrawable(drawable2);
    }

    public void setPopupBackgroundResource(int n2) {
        this.setPopupBackgroundDrawable(this.mTintManager.getDrawable(n2));
    }

    public void setPrompt(CharSequence charSequence) {
        this.mPopup.setPromptText(charSequence);
    }

    public void setPromptId(int n2) {
        this.setPrompt(this.getContext().getText(n2));
    }

    class DialogPopup
    implements DialogInterface.OnClickListener,
    SpinnerPopup {
        private ListAdapter mListAdapter;
        private AlertDialog mPopup;
        private CharSequence mPrompt;

        private DialogPopup() {
        }

        /* synthetic */ DialogPopup(SpinnerCompat$1 spinnerCompat$1) {
            this();
        }

        @Override
        public void dismiss() {
            if (this.mPopup != null) {
                this.mPopup.dismiss();
                this.mPopup = null;
            }
        }

        @Override
        public Drawable getBackground() {
            return null;
        }

        @Override
        public CharSequence getHintText() {
            return this.mPrompt;
        }

        @Override
        public int getHorizontalOffset() {
            return 0;
        }

        @Override
        public int getVerticalOffset() {
            return 0;
        }

        @Override
        public boolean isShowing() {
            if (this.mPopup != null) {
                return this.mPopup.isShowing();
            }
            return false;
        }

        public void onClick(DialogInterface dialogInterface, int n2) {
            SpinnerCompat.this.setSelection(n2);
            if (SpinnerCompat.this.mOnItemClickListener != null) {
                SpinnerCompat.this.performItemClick(null, n2, this.mListAdapter.getItemId(n2));
            }
            this.dismiss();
        }

        @Override
        public void setAdapter(ListAdapter listAdapter) {
            this.mListAdapter = listAdapter;
        }

        @Override
        public void setBackgroundDrawable(Drawable drawable2) {
            Log.e((String)"Spinner", (String)"Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override
        public void setHorizontalOffset(int n2) {
            Log.e((String)"Spinner", (String)"Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override
        public void setPromptText(CharSequence charSequence) {
            this.mPrompt = charSequence;
        }

        @Override
        public void setVerticalOffset(int n2) {
            Log.e((String)"Spinner", (String)"Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override
        public void show() {
            if (this.mListAdapter == null) {
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(SpinnerCompat.this.getContext());
            if (this.mPrompt != null) {
                builder.setTitle(this.mPrompt);
            }
            this.mPopup = builder.setSingleChoiceItems(this.mListAdapter, SpinnerCompat.this.getSelectedItemPosition(), (DialogInterface.OnClickListener)this).create();
            this.mPopup.show();
        }
    }

    static class DropDownAdapter
    implements ListAdapter,
    SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(SpinnerAdapter spinnerAdapter) {
            this.mAdapter = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter)spinnerAdapter;
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public int getCount() {
            if (this.mAdapter == null) {
                return 0;
            }
            return this.mAdapter.getCount();
        }

        public View getDropDownView(int n2, View view, ViewGroup viewGroup) {
            if (this.mAdapter == null) {
                return null;
            }
            return this.mAdapter.getDropDownView(n2, view, viewGroup);
        }

        public Object getItem(int n2) {
            if (this.mAdapter == null) {
                return null;
            }
            return this.mAdapter.getItem(n2);
        }

        public long getItemId(int n2) {
            if (this.mAdapter == null) {
                return -1;
            }
            return this.mAdapter.getItemId(n2);
        }

        public int getItemViewType(int n2) {
            return 0;
        }

        public View getView(int n2, View view, ViewGroup viewGroup) {
            return this.getDropDownView(n2, view, viewGroup);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
                return true;
            }
            return false;
        }

        public boolean isEmpty() {
            if (this.getCount() == 0) {
                return true;
            }
            return false;
        }

        public boolean isEnabled(int n2) {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.isEnabled(n2);
            }
            return true;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    class DropdownPopup
    extends ListPopupWindow
    implements SpinnerPopup {
        private ListAdapter mAdapter;
        private CharSequence mHintText;

        public DropdownPopup(Context context, AttributeSet attributeSet, int n2) {
            super(context, attributeSet, n2);
            this.setAnchorView((View)SpinnerCompat.this);
            this.setModal(true);
            this.setPromptPosition(0);
            this.setOnItemClickListener(new SpinnerCompat$DropdownPopup$1(this, SpinnerCompat.this));
        }

        static /* synthetic */ ListAdapter access$300(DropdownPopup dropdownPopup) {
            return dropdownPopup.mAdapter;
        }

        static /* synthetic */ void access$501(DropdownPopup dropdownPopup) {
            dropdownPopup.show();
        }

        /*
         * Enabled aggressive block sorting
         */
        void computeContentWidth() {
            int n2;
            Drawable drawable2 = this.getBackground();
            if (drawable2 != null) {
                drawable2.getPadding(SpinnerCompat.this.mTempRect);
                n2 = ViewUtils.isLayoutRtl((View)SpinnerCompat.this) ? SpinnerCompat.access$400((SpinnerCompat)SpinnerCompat.this).right : - SpinnerCompat.access$400((SpinnerCompat)SpinnerCompat.this).left;
            } else {
                drawable2 = SpinnerCompat.this.mTempRect;
                SpinnerCompat.access$400((SpinnerCompat)SpinnerCompat.this).right = 0;
                drawable2.left = 0;
                n2 = 0;
            }
            int n3 = SpinnerCompat.this.getPaddingLeft();
            int n4 = SpinnerCompat.this.getPaddingRight();
            int n5 = SpinnerCompat.this.getWidth();
            if (SpinnerCompat.this.mDropDownWidth == -2) {
                int n6;
                int n7 = SpinnerCompat.this.measureContentWidth((SpinnerAdapter)this.mAdapter, this.getBackground());
                if (n7 > (n6 = SpinnerCompat.this.getContext().getResources().getDisplayMetrics().widthPixels - SpinnerCompat.access$400((SpinnerCompat)SpinnerCompat.this).left - SpinnerCompat.access$400((SpinnerCompat)SpinnerCompat.this).right)) {
                    n7 = n6;
                }
                this.setContentWidth(Math.max((int)n7, (int)(n5 - n3 - n4)));
            } else if (SpinnerCompat.this.mDropDownWidth == -1) {
                this.setContentWidth(n5 - n3 - n4);
            } else {
                this.setContentWidth(SpinnerCompat.this.mDropDownWidth);
            }
            n2 = ViewUtils.isLayoutRtl((View)SpinnerCompat.this) ? n5 - n4 - this.getWidth() + n2 : (n2 += n3);
            this.setHorizontalOffset(n2);
        }

        @Override
        public CharSequence getHintText() {
            return this.mHintText;
        }

        @Override
        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }

        @Override
        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void show(int n2, int n3) {
            ViewTreeObserver viewTreeObserver;
            boolean bl2 = this.isShowing();
            this.computeContentWidth();
            this.setInputMethodMode(2);
            super.show();
            this.getListView().setChoiceMode(1);
            this.setSelection(SpinnerCompat.this.getSelectedItemPosition());
            if (bl2 || (viewTreeObserver = SpinnerCompat.this.getViewTreeObserver()) == null) {
                return;
            }
            SpinnerCompat$DropdownPopup$2 spinnerCompat$DropdownPopup$2 = new SpinnerCompat$DropdownPopup$2(this);
            viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)spinnerCompat$DropdownPopup$2);
            this.setOnDismissListener(new SpinnerCompat$DropdownPopup$3(this, spinnerCompat$DropdownPopup$2));
        }
    }

    static class SavedState
    extends AbsSpinnerCompat.SavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new SpinnerCompat$SavedState$1();
        boolean showDropdown;

        /*
         * Enabled aggressive block sorting
         */
        private SavedState(Parcel parcel) {
            super(parcel);
            boolean bl2 = parcel.readByte() != 0;
            this.showDropdown = bl2;
        }

        /* synthetic */ SavedState(Parcel parcel, SpinnerCompat$1 spinnerCompat$1) {
            this(parcel);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void writeToParcel(Parcel parcel, int n2) {
            super.writeToParcel(parcel, n2);
            n2 = this.showDropdown ? 1 : 0;
            parcel.writeByte((byte)n2);
        }
    }

    static interface SpinnerPopup {
        public void dismiss();

        public Drawable getBackground();

        public CharSequence getHintText();

        public int getHorizontalOffset();

        public int getVerticalOffset();

        public boolean isShowing();

        public void setAdapter(ListAdapter var1);

        public void setBackgroundDrawable(Drawable var1);

        public void setHorizontalOffset(int var1);

        public void setPromptText(CharSequence var1);

        public void setVerticalOffset(int var1);

        public void show();
    }

}

