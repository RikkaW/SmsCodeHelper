/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.database.DataSetObserver
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Handler
 *  android.os.SystemClock
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.KeyEvent$DispatcherState
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnTouchListener
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.AdapterView$OnItemSelectedListener
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 *  java.util.Locale
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.support.v7.internal.widget.ListViewCompat;
import android.support.v7.internal.widget.MzSlidePopupWindow;
import android.support.v7.widget.ListPopupWindow$1;
import android.support.v7.widget.ListPopupWindow$2;
import android.support.v7.widget.ListPopupWindow$3;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
import java.util.Locale;

public class ListPopupWindow {
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
    private Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    private int mLayoutDirection;
    int mListItemExpandMaximum = Integer.MAX_VALUE;
    private boolean mModal;
    private DataSetObserver mObserver;
    private PopupWindow mPopup;
    private int mPromptPosition = 0;
    private View mPromptView;
    private final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    static {
        try {
            sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        }
        catch (NoSuchMethodException var0) {
            Log.i((String)"ListPopupWindow", (String)"Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int n2) {
        this(context, attributeSet, n2, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int n2, int n3) {
        this(context, attributeSet, n2, n3, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public ListPopupWindow(Context object, AttributeSet attributeSet, int n2, int n3, boolean bl2) {
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mHandler = new Handler();
        this.mTempRect = new Rect();
        this.mContext = object;
        TypedArray typedArray = object.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, n2, n3);
        this.mDropDownHorizontalOffset = typedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = typedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        typedArray.recycle();
        object = bl2 ? new MzSlidePopupWindow((Context)object, attributeSet, n2) : new AppCompatPopupWindow((Context)object, attributeSet, n2);
        this.mPopup = object;
        this.mPopup.setInputMethodMode(1);
        this.mLayoutDirection = TextUtilsCompat.getLayoutDirectionFromLocale(this.mContext.getResources().getConfiguration().locale);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int buildDropDown() {
        if (this.mDropDownList != null) ** GOTO lbl41
        var7_1 = this.mContext;
        this.mShowDropDownRunnable = new ListPopupWindow$2(this);
        var5_3 = this.mModal == false;
        this.mDropDownList = new DropDownListView(var7_1, var5_3);
        if (this.mDropDownListHighlight != null) {
            this.mDropDownList.setSelector(this.mDropDownListHighlight);
        }
        this.mDropDownList.setAdapter(this.mAdapter);
        this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
        this.mDropDownList.setFocusable(true);
        this.mDropDownList.setFocusableInTouchMode(true);
        this.mDropDownList.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new ListPopupWindow$3(this));
        this.mDropDownList.setOnScrollListener((AbsListView.OnScrollListener)this.mScrollListener);
        if (this.mItemSelectedListener != null) {
            this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
        }
        var6_4 = this.mDropDownList;
        var8_5 = this.mPromptView;
        if (var8_5 == null) ** GOTO lbl51
        var7_1 = new LinearLayout(var7_1);
        var7_1.setOrientation(1);
        var9_6 = new LinearLayout.LayoutParams(-1, 0, 1.0f);
        switch (this.mPromptPosition) {
            default: {
                Log.e((String)"ListPopupWindow", (String)("Invalid hint position " + this.mPromptPosition));
                break;
            }
            case 1: {
                var7_1.addView((View)var6_4, (ViewGroup.LayoutParams)var9_6);
                var7_1.addView(var8_5);
                break;
            }
            case 0: {
                var7_1.addView(var8_5);
                var7_1.addView((View)var6_4, (ViewGroup.LayoutParams)var9_6);
                break;
            }
        }
        var8_5.measure(View.MeasureSpec.makeMeasureSpec((int)this.mDropDownWidth, (int)Integer.MIN_VALUE), 0);
        var6_4 = (LinearLayout.LayoutParams)var8_5.getLayoutParams();
        var1_7 = var8_5.getMeasuredHeight();
        var2_8 = var6_4.topMargin;
        var1_7 = var6_4.bottomMargin + (var1_7 + var2_8);
        var6_4 = var7_1;
        ** GOTO lbl52
lbl41: // 1 sources:
        var6_4 = (ViewGroup)this.mPopup.getContentView();
        var6_4 = this.mPromptView;
        if (var6_4 != null) {
            var7_2 = (LinearLayout.LayoutParams)var6_4.getLayoutParams();
            var1_7 = var6_4.getMeasuredHeight();
            var2_8 = var7_2.topMargin;
            var1_7 = var7_2.bottomMargin + (var1_7 + var2_8);
        } else {
            var1_7 = 0;
        }
        ** GOTO lbl53
lbl51: // 1 sources:
        var1_7 = 0;
lbl52: // 2 sources:
        this.mPopup.setContentView((View)var6_4);
lbl53: // 3 sources:
        if ((var6_4 = this.mPopup.getBackground()) != null) {
            var6_4.getPadding(this.mTempRect);
            var2_8 = this.mTempRect.top + this.mTempRect.bottom;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = - this.mTempRect.top;
            }
        } else {
            this.mTempRect.setEmpty();
            var2_8 = 0;
        }
        if (this.mPopup.getInputMethodMode() == 2) {
            // empty if block
        }
        var4_9 = this.mPopup.getMaxAvailableHeight(this.getAnchorView(), this.mDropDownVerticalOffset);
        if (this.mDropDownAlwaysVisible != false) return var4_9 + var2_8;
        if (this.mDropDownHeight == -1) {
            return var4_9 + var2_8;
        }
        switch (this.mDropDownWidth) {
            default: {
                var3_10 = View.MeasureSpec.makeMeasureSpec((int)this.mDropDownWidth, (int)1073741824);
                break;
            }
            case -2: {
                var3_10 = View.MeasureSpec.makeMeasureSpec((int)(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right)), (int)Integer.MIN_VALUE);
                break;
            }
            case -1: {
                var3_10 = View.MeasureSpec.makeMeasureSpec((int)(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right)), (int)1073741824);
            }
        }
        var4_9 = this.mDropDownList.measureHeightOfChildrenCompat(var3_10, 0, -1, var4_9 - var1_7, -1);
        var3_10 = var1_7;
        if (var4_9 <= 0) return var4_9 + var3_10;
        var3_10 = var1_7 + var2_8;
        return var4_9 + var3_10;
    }

    private static boolean isConfirmKey(int n2) {
        if (n2 == 66 || n2 == 23) {
            return true;
        }
        return false;
    }

    private void removePromptView() {
        ViewParent viewParent;
        if (this.mPromptView != null && (viewParent = this.mPromptView.getParent()) instanceof ViewGroup) {
            ((ViewGroup)viewParent).removeView(this.mPromptView);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void setPopupClipToScreenEnabled(boolean bl2) {
        if (sClipToWindowEnabledMethod == null) return;
        try {
            sClipToWindowEnabledMethod.invoke((Object)this.mPopup, new Object[]{bl2});
            return;
        }
        catch (Exception var2_2) {
            Log.i((String)"ListPopupWindow", (String)"Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            return;
        }
    }

    public void clearListSelection() {
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.mListSelectionHidden = true;
            dropDownListView.requestLayout();
        }
    }

    public View.OnTouchListener createDragToOpenListener(View view) {
        return new ListPopupWindow$1(this, view);
    }

    public void dismiss() {
        this.mPopup.dismiss();
        this.removePromptView();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks((Runnable)this.mResizePopupRunnable);
    }

    public void dismiss(boolean bl2) {
        if (this.mPopup instanceof MzSlidePopupWindow) {
            ((MzSlidePopupWindow)this.mPopup).dismiss(bl2);
            return;
        }
        this.dismiss();
    }

    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    public ListView getListView() {
        return this.mDropDownList;
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    public Object getSelectedItem() {
        if (!this.isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedItem();
    }

    public long getSelectedItemId() {
        if (!this.isShowing()) {
            return Long.MIN_VALUE;
        }
        return this.mDropDownList.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        if (!this.isShowing()) {
            return -1;
        }
        return this.mDropDownList.getSelectedItemPosition();
    }

    public View getSelectedView() {
        if (!this.isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedView();
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded() {
        if (this.mPopup.getInputMethodMode() == 2) {
            return true;
        }
        return false;
    }

    public boolean isModal() {
        return this.mModal;
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        if (!this.isShowing()) return false;
        if (n2 == 62) return false;
        if (this.mDropDownList.getSelectedItemPosition() < 0) {
            if (ListPopupWindow.isConfirmKey(n2)) return false;
        }
        int n3 = this.mDropDownList.getSelectedItemPosition();
        boolean bl2 = !this.mPopup.isAboveAnchor();
        ListAdapter listAdapter = this.mAdapter;
        int n4 = Integer.MAX_VALUE;
        int n5 = Integer.MIN_VALUE;
        if (listAdapter != null) {
            boolean bl3 = listAdapter.areAllItemsEnabled();
            n4 = bl3 ? 0 : this.mDropDownList.lookForSelectablePosition(0, true);
            n5 = bl3 ? listAdapter.getCount() - 1 : this.mDropDownList.lookForSelectablePosition(listAdapter.getCount() - 1, false);
        }
        if (bl2 && n2 == 19 && n3 <= n4 || !bl2 && n2 == 20 && n3 >= n5) {
            this.clearListSelection();
            this.mPopup.setInputMethodMode(1);
            this.show();
            return true;
        }
        this.mDropDownList.mListSelectionHidden = false;
        if (this.mDropDownList.onKeyDown(n2, keyEvent)) {
            this.mPopup.setInputMethodMode(2);
            this.mDropDownList.requestFocusFromTouch();
            this.show();
            switch (n2) {
                case 19: 
                case 20: 
                case 23: 
                case 66: {
                    return true;
                }
            }
            return false;
        }
        if (bl2 && n2 == 20) {
            if (n3 != n5) return false;
            return true;
        }
        if (bl2) return false;
        if (n2 != 19) return false;
        if (n3 != n4) return false;
        return true;
    }

    public boolean onKeyPreIme(int n2, KeyEvent keyEvent) {
        if (n2 == 4 && this.isShowing()) {
            View view = this.mDropDownAnchorView;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                if ((view = view.getKeyDispatcherState()) != null) {
                    view.startTracking(keyEvent, (Object)this);
                }
                return true;
            }
            if (keyEvent.getAction() == 1) {
                if ((view = view.getKeyDispatcherState()) != null) {
                    view.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    this.dismiss();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onKeyUp(int n2, KeyEvent keyEvent) {
        if (this.isShowing() && this.mDropDownList.getSelectedItemPosition() >= 0) {
            boolean bl2 = this.mDropDownList.onKeyUp(n2, keyEvent);
            if (bl2 && ListPopupWindow.isConfirmKey(n2)) {
                this.dismiss();
            }
            return bl2;
        }
        return false;
    }

    public boolean performItemClick(int n2) {
        if (this.isShowing()) {
            if (this.mItemClickListener != null) {
                DropDownListView dropDownListView = this.mDropDownList;
                View view = dropDownListView.getChildAt(n2 - dropDownListView.getFirstVisiblePosition());
                ListAdapter listAdapter = dropDownListView.getAdapter();
                this.mItemClickListener.onItemClick((AdapterView)dropDownListView, view, n2, listAdapter.getItemId(n2));
            }
            return true;
        }
        return false;
    }

    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setAdapter(ListAdapter listAdapter) {
        if (this.mObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = listAdapter;
        if (this.mAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        if (this.mDropDownList != null) {
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(View view) {
        this.mDropDownAnchorView = view;
    }

    public void setAnimationStyle(int n2) {
        this.mPopup.setAnimationStyle(n2);
    }

    public void setBackgroundDrawable(Drawable drawable2) {
        this.mPopup.setBackgroundDrawable(drawable2);
    }

    public void setClipToScreenEnabled(boolean bl2) {
        this.setPopupClipToScreenEnabled(bl2);
    }

    public void setClippingEnabled(boolean bl2) {
        this.mPopup.setClippingEnabled(bl2);
    }

    public void setContentWidth(int n2) {
        Drawable drawable2 = this.mPopup.getBackground();
        if (drawable2 != null) {
            drawable2.getPadding(this.mTempRect);
            this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + n2;
            return;
        }
        this.setWidth(n2);
    }

    public void setDropDownAlwaysVisible(boolean bl2) {
        this.mDropDownAlwaysVisible = bl2;
    }

    public void setDropDownGravity(int n2) {
        this.mDropDownGravity = n2;
    }

    public void setForceIgnoreOutsideTouch(boolean bl2) {
        this.mForceIgnoreOutsideTouch = bl2;
    }

    public void setHeight(int n2) {
        this.mDropDownHeight = n2;
    }

    public void setHorizontalOffset(int n2) {
        this.mDropDownHorizontalOffset = n2;
    }

    public void setInputMethodMode(int n2) {
        this.mPopup.setInputMethodMode(n2);
    }

    void setListItemExpandMax(int n2) {
        this.mListItemExpandMaximum = n2;
    }

    public void setListSelector(Drawable drawable2) {
        this.mDropDownListHighlight = drawable2;
    }

    public void setModal(boolean bl2) {
        this.mModal = bl2;
        this.mPopup.setFocusable(bl2);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    public void setPromptPosition(int n2) {
        this.mPromptPosition = n2;
    }

    public void setPromptView(View view) {
        boolean bl2 = this.isShowing();
        if (bl2) {
            this.removePromptView();
        }
        this.mPromptView = view;
        if (bl2) {
            this.show();
        }
    }

    public void setSelection(int n2) {
        DropDownListView dropDownListView = this.mDropDownList;
        if (this.isShowing() && dropDownListView != null) {
            dropDownListView.mListSelectionHidden = false;
            dropDownListView.setSelection(n2);
            if (Build.VERSION.SDK_INT >= 11 && dropDownListView.getChoiceMode() != 0) {
                dropDownListView.setItemChecked(n2, true);
            }
        }
    }

    public void setSoftInputMode(int n2) {
        this.mPopup.setSoftInputMode(n2);
    }

    public void setVerticalOffset(int n2) {
        this.mDropDownVerticalOffset = n2;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int n2) {
        this.mDropDownWidth = n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void show() {
        boolean bl2 = true;
        boolean bl3 = false;
        int n2 = -1;
        int n3 = this.buildDropDown();
        boolean bl4 = this.isInputMethodNotNeeded();
        if (this.mPopup.isShowing()) {
            PopupWindow popupWindow;
            int n4 = this.mDropDownWidth == -1 ? -1 : (this.mDropDownWidth == -2 ? this.getAnchorView().getWidth() : this.mDropDownWidth);
            if (this.mDropDownHeight == -1) {
                if (!bl4) {
                    n3 = -1;
                }
                if (bl4) {
                    popupWindow = this.mPopup;
                    if (this.mDropDownWidth != -1) {
                        n2 = 0;
                    }
                    popupWindow.setWindowLayoutMode(n2, 0);
                } else {
                    popupWindow = this.mPopup;
                    n2 = this.mDropDownWidth == -1 ? -1 : 0;
                    popupWindow.setWindowLayoutMode(n2, -1);
                }
            } else if (this.mDropDownHeight != -2) {
                n3 = this.mDropDownHeight;
            }
            popupWindow = this.mPopup;
            bl2 = bl3;
            if (!this.mForceIgnoreOutsideTouch) {
                bl2 = bl3;
                if (!this.mDropDownAlwaysVisible) {
                    bl2 = true;
                }
            }
            popupWindow.setOutsideTouchable(bl2);
            this.mPopup.update(this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, n4, n3);
            return;
        } else {
            int n5;
            if (this.mDropDownWidth == -1) {
                n5 = -1;
            } else if (this.mDropDownWidth == -2) {
                this.mPopup.setWidth(this.getAnchorView().getWidth());
                n5 = 0;
            } else {
                this.mPopup.setWidth(this.mDropDownWidth);
                n5 = 0;
            }
            if (this.mDropDownHeight == -1) {
                n3 = -1;
            } else if (this.mDropDownHeight == -2) {
                this.mPopup.setHeight(n3);
                n3 = 0;
            } else {
                this.mPopup.setHeight(this.mDropDownHeight);
                n3 = 0;
            }
            this.mPopup.setWindowLayoutMode(n5, n3);
            this.setPopupClipToScreenEnabled(true);
            PopupWindow popupWindow = this.mPopup;
            if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
                bl2 = false;
            }
            popupWindow.setOutsideTouchable(bl2);
            this.mPopup.setTouchInterceptor((View.OnTouchListener)this.mTouchInterceptor);
            PopupWindowCompat.showAsDropDown(this.mPopup, this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.mDropDownList.setSelection(-1);
            if (!this.mModal || this.mDropDownList.isInTouchMode()) {
                this.clearListSelection();
            }
            if (this.mModal) return;
            {
                this.mHandler.post((Runnable)this.mHideSelector);
                return;
            }
        }
    }

    static class DropDownListView
    extends ListViewCompat {
        private ViewPropertyAnimatorCompat mClickAnimation;
        private boolean mDrawsInPressedState;
        private boolean mHijackFocus;
        private boolean mListSelectionHidden;
        private ListViewAutoScrollHelper mScrollHelper;

        public DropDownListView(Context context, boolean bl2) {
            super(context, null, R.attr.dropDownListViewStyle);
            this.mHijackFocus = bl2;
            this.setCacheColorHint(0);
        }

        private void clearPressedItem() {
            this.mDrawsInPressedState = false;
            this.setPressed(false);
            this.drawableStateChanged();
            if (this.mClickAnimation != null) {
                this.mClickAnimation.cancel();
                this.mClickAnimation = null;
            }
        }

        private void clickPressedItem(View view, int n2) {
            this.performItemClick(view, n2, this.getItemIdAtPosition(n2));
        }

        private void setPressedItem(View view, int n2, float f2, float f3) {
            this.mDrawsInPressedState = true;
            this.setPressed(true);
            this.layoutChildren();
            this.setSelection(n2);
            this.positionSelectorLikeTouchCompat(n2, view, f2, f3);
            this.setSelectorEnabled(false);
            this.refreshDrawableState();
        }

        public boolean hasFocus() {
            if (this.mHijackFocus || super.hasFocus()) {
                return true;
            }
            return false;
        }

        public boolean hasWindowFocus() {
            if (this.mHijackFocus || super.hasWindowFocus()) {
                return true;
            }
            return false;
        }

        public boolean isFocused() {
            if (this.mHijackFocus || super.isFocused()) {
                return true;
            }
            return false;
        }

        public boolean isInTouchMode() {
            if (this.mHijackFocus && this.mListSelectionHidden || super.isInTouchMode()) {
                return true;
            }
            return false;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean onForwardedEvent(MotionEvent var1_1, int var2_2) {
            var3_3 = MotionEventCompat.getActionMasked(var1_1);
            switch (var3_3) {
                case 3: {
                    var2_2 = 0;
                    var6_4 = false;
                    ** GOTO lbl27
                }
                case 1: {
                    var6_4 = false;
                    ** GOTO lbl12
                }
                case 2: {
                    var6_4 = true;
lbl12: // 2 sources:
                    if ((var4_5 = var1_1.findPointerIndex(var2_2)) >= 0) ** GOTO lbl16
                    var2_2 = 0;
                    var6_4 = false;
                    ** GOTO lbl27
lbl16: // 1 sources:
                    var2_2 = (int)var1_1.getX(var4_5);
                    var5_6 = this.pointToPosition(var2_2, var4_5 = (int)var1_1.getY(var4_5));
                    if (var5_6 != -1) ** GOTO lbl21
                    var2_2 = 1;
                    ** GOTO lbl27
lbl21: // 1 sources:
                    var7_7 = this.getChildAt(var5_6 - this.getFirstVisiblePosition());
                    this.setPressedItem(var7_7, var5_6, var2_2, var4_5);
                    if (var3_3 != 1) break;
                    this.clickPressedItem(var7_7, var5_6);
                }
            }
            var2_2 = 0;
            var6_4 = true;
lbl27: // 4 sources:
            if (var6_4 && var2_2 == 0) ** GOTO lbl-1000
            this.clearPressedItem();
            if (var6_4) lbl-1000: // 2 sources:
            {
                if (this.mScrollHelper == null) {
                    this.mScrollHelper = new ListViewAutoScrollHelper(this);
                }
                this.mScrollHelper.setEnabled(true);
                this.mScrollHelper.onTouch((View)this, var1_1);
                return var6_4;
            }
            if (this.mScrollHelper == null) return var6_4;
            this.mScrollHelper.setEnabled(false);
            return var6_4;
        }

        @Override
        protected boolean touchModeDrawsInPressedStateCompat() {
            if (this.mDrawsInPressedState || super.touchModeDrawsInPressedStateCompat()) {
                return true;
            }
            return false;
        }
    }

    public static abstract class ForwardingListener
    implements View.OnTouchListener {
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

        public ForwardingListener(View view) {
            this.mSrc = view;
            this.mScaledTouchSlop = ViewConfiguration.get((Context)view.getContext()).getScaledTouchSlop();
            this.mTapTimeout = ViewConfiguration.getTapTimeout();
            this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
        }

        private void clearCallbacks() {
            if (this.mTriggerLongPress != null) {
                this.mSrc.removeCallbacks(this.mTriggerLongPress);
            }
            if (this.mDisallowIntercept != null) {
                this.mSrc.removeCallbacks(this.mDisallowIntercept);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void onLongPress() {
            this.clearCallbacks();
            View view = this.mSrc;
            if (!view.isEnabled() || view.isLongClickable() || !this.onForwardingStarted()) {
                return;
            }
            if (view.getParent() != null) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
            }
            long l2 = SystemClock.uptimeMillis();
            MotionEvent motionEvent = MotionEvent.obtain((long)l2, (long)l2, (int)3, (float)0.0f, (float)0.0f, (int)0);
            view.onTouchEvent(motionEvent);
            motionEvent.recycle();
            this.mForwarding = true;
            this.mWasLongPress = true;
        }

        /*
         * Enabled aggressive block sorting
         */
        private boolean onTouchForwarded(MotionEvent motionEvent) {
            boolean bl2 = true;
            View view = this.mSrc;
            Object object = this.getPopup();
            if (object == null) return false;
            if (!object.isShowing()) {
                return false;
            }
            if ((object = ((ListPopupWindow)object).mDropDownList) == null) return false;
            if (!object.isShown()) return false;
            MotionEvent motionEvent2 = MotionEvent.obtainNoHistory((MotionEvent)motionEvent);
            this.toGlobalMotionEvent(view, motionEvent2);
            this.toLocalMotionEvent((View)object, motionEvent2);
            boolean bl3 = object.onForwardedEvent(motionEvent2, this.mActivePointerId);
            motionEvent2.recycle();
            int n2 = MotionEventCompat.getActionMasked(motionEvent);
            n2 = n2 != 1 && n2 != 3 ? 1 : 0;
            if (!bl3) return false;
            if (n2 == 0) return false;
            return bl2;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        private boolean onTouchObserved(MotionEvent motionEvent) {
            View view = this.mSrc;
            if (!view.isEnabled()) {
                return false;
            }
            switch (MotionEventCompat.getActionMasked(motionEvent)) {
                default: {
                    return false;
                }
                case 0: {
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    this.mWasLongPress = false;
                    if (this.mDisallowIntercept == null) {
                        this.mDisallowIntercept = new DisallowIntercept();
                    }
                    view.postDelayed(this.mDisallowIntercept, (long)this.mTapTimeout);
                    if (this.mTriggerLongPress == null) {
                        this.mTriggerLongPress = new TriggerLongPress();
                    }
                    view.postDelayed(this.mTriggerLongPress, (long)this.mLongPressTimeout);
                    return false;
                }
                case 2: {
                    int n2 = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (n2 < 0) return false;
                    if (ForwardingListener.pointInView(view, motionEvent.getX(n2), motionEvent.getY(n2), this.mScaledTouchSlop)) return false;
                    this.clearCallbacks();
                    if (view.getParent() == null) return true;
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                }
                case 1: 
                case 3: 
            }
            this.clearCallbacks();
            return false;
        }

        private static boolean pointInView(View view, float f2, float f3, float f4) {
            if (f2 >= - f4 && f3 >= - f4 && f2 < (float)(view.getRight() - view.getLeft()) + f4 && f3 < (float)(view.getBottom() - view.getTop()) + f4) {
                return true;
            }
            return false;
        }

        private boolean toGlobalMotionEvent(View view, MotionEvent motionEvent) {
            int[] arrn = this.mTmpLocation;
            view.getLocationOnScreen(arrn);
            motionEvent.offsetLocation((float)arrn[0], (float)arrn[1]);
            return true;
        }

        private boolean toLocalMotionEvent(View view, MotionEvent motionEvent) {
            int[] arrn = this.mTmpLocation;
            view.getLocationOnScreen(arrn);
            motionEvent.offsetLocation((float)(- arrn[0]), (float)(- arrn[1]));
            return true;
        }

        public abstract ListPopupWindow getPopup();

        public boolean onForwardingStarted() {
            ListPopupWindow listPopupWindow = this.getPopup();
            if (listPopupWindow != null && !listPopupWindow.isShowing()) {
                listPopupWindow.show();
            }
            return true;
        }

        public boolean onForwardingStopped() {
            ListPopupWindow listPopupWindow = this.getPopup();
            if (listPopupWindow != null && listPopupWindow.isShowing()) {
                listPopupWindow.dismiss();
            }
            return true;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean bl2;
            boolean bl3 = false;
            boolean bl4 = this.mForwarding;
            if (bl4) {
                bl2 = this.mWasLongPress ? this.onTouchForwarded(motionEvent) : this.onTouchForwarded(motionEvent) || !this.onForwardingStopped();
            } else {
                bl2 = this.onTouchObserved(motionEvent) && this.onForwardingStarted();
                if (bl2) {
                    long l2 = SystemClock.uptimeMillis();
                    view = MotionEvent.obtain((long)l2, (long)l2, (int)3, (float)0.0f, (float)0.0f, (int)0);
                    this.mSrc.onTouchEvent((MotionEvent)view);
                    view.recycle();
                }
            }
            this.mForwarding = bl2;
            if (bl2) return true;
            bl2 = bl3;
            if (!bl4) return bl2;
            return true;
        }

        class DisallowIntercept
        implements Runnable {
            private DisallowIntercept() {
            }

            @Override
            public void run() {
                ViewParent viewParent = ForwardingListener.this.mSrc.getParent();
                if (viewParent != null) {
                    viewParent.requestDisallowInterceptTouchEvent(true);
                }
            }
        }

        class TriggerLongPress
        implements Runnable {
            private TriggerLongPress() {
            }

            @Override
            public void run() {
                ForwardingListener.this.onLongPress();
            }
        }

    }

    class ListSelectorHider
    implements Runnable {
        private ListSelectorHider() {
        }

        @Override
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    class PopupDataSetObserver
    extends DataSetObserver {
        private PopupDataSetObserver() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    class PopupScrollListener
    implements AbsListView.OnScrollListener {
        private PopupScrollListener() {
        }

        public void onScroll(AbsListView absListView, int n2, int n3, int n4) {
        }

        public void onScrollStateChanged(AbsListView absListView, int n2) {
            if (n2 == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow.this.mHandler.removeCallbacks((Runnable)ListPopupWindow.this.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }
    }

    class PopupTouchInterceptor
    implements View.OnTouchListener {
        private PopupTouchInterceptor() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int n2 = motionEvent.getAction();
            int n3 = (int)motionEvent.getX();
            int n4 = (int)motionEvent.getY();
            if (n2 == 0 && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && n3 >= 0 && n3 < ListPopupWindow.this.mPopup.getWidth() && n4 >= 0 && n4 < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow.this.mHandler.postDelayed((Runnable)ListPopupWindow.this.mResizePopupRunnable, 250);
                return false;
            }
            if (n2 != 1) return false;
            ListPopupWindow.this.mHandler.removeCallbacks((Runnable)ListPopupWindow.this.mResizePopupRunnable);
            return false;
        }
    }

    class ResizePopupRunnable
    implements Runnable {
        private ResizePopupRunnable() {
        }

        @Override
        public void run() {
            if (ListPopupWindow.this.mDropDownList != null && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
                ListPopupWindow.this.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

}

