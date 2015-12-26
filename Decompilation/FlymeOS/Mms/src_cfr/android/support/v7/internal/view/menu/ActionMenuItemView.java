/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.ColorStateList
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.ViewParent
 *  android.widget.Toast
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.widget.ToastUtils;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.Toast;

public class ActionMenuItemView
extends AppCompatTextView
implements MenuView.ItemView,
ActionMenuView.ActionMenuChildView,
View.OnClickListener,
View.OnLongClickListener {
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

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        Resources resources = context.getResources();
        this.mAllowTextWithIcon = resources.getBoolean(R.bool.abc_config_allowActionMenuItemTextWithIcon);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ActionMenuItemView, n2, 0);
        this.mMinWidth = typedArray.getDimensionPixelSize(R.styleable.ActionMenuItemView_android_minWidth, 0);
        typedArray.recycle();
        this.mMaxIconSize = (int)(resources.getDisplayMetrics().density * 32.0f + 0.5f);
        this.setOnClickListener((View.OnClickListener)this);
        this.mSavedPaddingLeft = -1;
        context = context.obtainStyledAttributes(attributeSet, R.styleable.MenuView, n2, 0);
        this.mDisabledAlpha = context.getFloat(R.styleable.MenuView_android_itemIconDisabledAlpha, 1.0f);
        context.recycle();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setCompoundDrawables(boolean bl2) {
        Drawable drawable2 = bl2 ? null : this.mIcon;
        Drawable drawable3 = bl2 ? this.mIcon : null;
        this.setCompoundDrawables(drawable2, drawable3, null, null);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void updateTextButtonVisibility() {
        block8 : {
            block9 : {
                block7 : {
                    var3_1 = true;
                    if (!TextUtils.isEmpty((CharSequence)this.mTitle)) {
                        var1_2 = true;
lbl4: // 2 sources:
                        do {
                            var2_3 = var3_1;
                            if (this.mIcon != null) {
                                if (!this.mItemData.showsTextAsAction()) break block7;
                                var2_3 = var3_1;
                                if (!this.mAllowTextWithIcon) {
                                    if (!this.mExpandedFormat) break block7;
                                    var2_3 = var3_1;
                                }
                            }
lbl12: // 6 sources:
                            while (var4_4 = var2_3 & var1_2) {
                                var5_5 = this.mTitle;
lbl14: // 2 sources:
                                do {
                                    this.setText(var5_5);
                                    this.uptateTextAppearance(var4_4);
                                    this.setCompoundDrawables(var4_4);
                                    if (var4_4 && this.mIcon != null) {
                                        this.setPadding(this.getPaddingLeft(), this.getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_padding_top_icon_with_text), this.getPaddingRight(), 0);
                                        return;
                                    }
                                    break block8;
                                    break;
                                } while (true);
                            }
                            break block9;
                            break;
                        } while (true);
                    }
                    var1_2 = false;
                    ** while (true)
                }
                var2_3 = false;
                ** GOTO lbl12
            }
            var5_5 = null;
            ** while (true)
        }
        this.setPadding(this.getPaddingLeft(), 0, this.getPaddingRight(), 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void uptateTextAppearance(boolean bl2) {
        int[] arrn = this.mIcon != null && bl2 ? (this.mIsInSplit ? new int[]{R.attr.mzActionMenuTextAppearanceWithIconSplit} : new int[]{R.attr.mzActionMenuTextAppearanceWithIcon}) : (this.mIsInSplit ? new int[]{R.attr.mzActionMenuTextAppearanceSplit} : new int[]{R.attr.actionMenuTextAppearance});
        int n2 = this.getContext().getTheme().obtainStyledAttributes(arrn).getResourceId(0, -1);
        if (n2 > 0) {
            this.setTextAppearance(this.getContext(), n2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mItemData != null && this.mIcon != null) {
            int n2 = !this.mItemData.isEnabled() && (this.isPressed() || !this.isFocused()) ? 1 : 0;
            Drawable drawable2 = this.mIcon;
            n2 = n2 != 0 ? (int)(this.mDisabledAlpha * 255.0f) : 255;
            drawable2.setAlpha(n2);
        }
    }

    @Override
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public boolean hasText() {
        if (!TextUtils.isEmpty((CharSequence)this.getText())) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void initialize(MenuItemImpl var1_1, int var2_2) {
        var3_3 = 0;
        this.mItemData = var1_1;
        this.setIcon(var1_1.getIcon());
        this.setTitle(var1_1.getTitleForItemView(this));
        this.setId(var1_1.getItemId());
        var2_2 = var1_1.isVisible() != false ? 0 : 8;
        this.setVisibility(var2_2);
        this.setEnabled(var1_1.isEnabled());
        if (var1_1.hasSubMenu() && this.mForwardingListener == null) {
            this.mForwardingListener = new ActionMenuItemForwardingListener();
        }
        if (var1_1.getTitleColor() != null) {
            this.setTextColor(var1_1.getTitleColor());
        }
        this.setIsOverflowActor(var1_1.isOverflowActor());
        if (this.mIcon == null) return;
        var2_2 = var3_3;
        if (this.mItemData.isEnabled()) ** GOTO lbl21
        if (this.isPressed()) ** GOTO lbl-1000
        var2_2 = var3_3;
        if (!this.isFocused()) lbl-1000: // 2 sources:
        {
            var2_2 = 1;
        }
lbl21: // 4 sources:
        var1_1 = this.mIcon;
        var2_2 = var2_2 != 0 ? (int)(this.mDisabledAlpha * 255.0f) : 255;
        var1_1.setAlpha(var2_2);
    }

    public boolean isOverflowActor() {
        return this.mIsOverflowActor;
    }

    @Override
    public boolean needsDividerAfter() {
        return this.hasText();
    }

    @Override
    public boolean needsDividerBefore() {
        if (this.hasText() && this.mItemData.getIcon() == null) {
            return true;
        }
        return false;
    }

    public void onClick(View view) {
        if (this.mItemInvoker != null) {
            this.mItemInvoker.invokeItem(this.mItemData);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        this.mAllowTextWithIcon = this.getContext().getResources().getBoolean(R.bool.abc_config_allowActionMenuItemTextWithIcon);
        this.updateTextButtonVisibility();
    }

    protected void onDraw(Canvas canvas) {
        Log.d((String)"ActionMenuItemView", (String)("onDraw:text = " + this.getText() + "; left = " + this.getLeft() + "; right = " + this.getRight() + "; measureWidth = " + this.getMeasuredWidth()));
        if (this.getRight() - this.getLeft() == 0 && this.getMeasuredWidth() > 0) {
            this.getParent().requestLayout();
            return;
        }
        super.onDraw(canvas);
    }

    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        super.onLayout(bl2, n2, n3, n4, n5);
        Log.d((String)"ActionMenuItemView", (String)("onLayout:text = " + this.getText() + "; left = " + this.getLeft() + "; right = " + this.getRight()));
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onLongClick(View view) {
        int n2;
        if (this.hasText()) {
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)this.mTitle)) {
            return false;
        }
        if (ToastUtils.showToast((View)this, this.mItemData.getTitle()) != null) {
            return true;
        }
        int[] arrn = new int[2];
        Rect rect = new Rect();
        this.getLocationOnScreen(arrn);
        this.getWindowVisibleDisplayFrame(rect);
        Context context = this.getContext();
        int n3 = this.getWidth();
        int n4 = this.getHeight();
        int n5 = arrn[1];
        int n6 = n4 / 2;
        n3 = n2 = arrn[0] + n3 / 2;
        if (ViewCompat.getLayoutDirection(view) == 0) {
            n3 = context.getResources().getDisplayMetrics().widthPixels - n2;
        }
        view = Toast.makeText((Context)context, (CharSequence)this.mItemData.getTitle(), (int)0);
        if (n5 + n6 < rect.height()) {
            view.setGravity(8388661, n3, n4);
        } else {
            view.setGravity(81, 0, n4);
        }
        view.show();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onMeasure(int n2, int n3) {
        boolean bl2 = this.hasText();
        if (bl2 && this.mSavedPaddingLeft >= 0) {
            super.setPadding(this.mSavedPaddingLeft, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
        super.onMeasure(n2, n3);
        int n4 = View.MeasureSpec.getMode((int)n2);
        int n5 = View.MeasureSpec.getSize((int)n2);
        int n6 = this.getMeasuredWidth();
        n2 = n4 == Integer.MIN_VALUE ? Math.min((int)n5, (int)this.mMinWidth) : this.mMinWidth;
        Log.d((String)"ActionMenuItemView", (String)("onMeasure:widthMode = " + n4 + "; widthSize = " + n5 + "; oldMeasuredWidth = " + n6 + "; targetWidth = " + n2));
        if (n4 != 1073741824 && this.mMinWidth > 0 && n6 < n2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((int)n2, (int)1073741824), n3);
        }
        if (!bl2 && this.mIcon != null) {
            super.setPadding((this.getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
        Log.d((String)"ActionMenuItemView", (String)("onMeasure:getMeasuredWidth = " + this.getMeasuredWidth() + "; getMeasuredHeight = " + this.getMeasuredHeight()));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mItemData.hasSubMenu() && this.mForwardingListener != null && this.mForwardingListener.onTouch((View)this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override
    public boolean prefersCondensedTitle() {
        return true;
    }

    @Override
    public void setCheckable(boolean bl2) {
    }

    @Override
    public void setChecked(boolean bl2) {
    }

    public void setExpandedFormat(boolean bl2) {
        if (this.mExpandedFormat != bl2) {
            this.mExpandedFormat = bl2;
            if (this.mItemData != null) {
                this.mItemData.actionFormatChanged();
            }
        }
    }

    @Override
    public void setIcon(Drawable drawable2) {
        this.mIcon = drawable2;
        if (drawable2 != null) {
            int n2;
            float f2;
            int n3 = drawable2.getIntrinsicWidth();
            int n4 = n2 = drawable2.getIntrinsicHeight();
            int n5 = n3;
            if (n3 > this.mMaxIconSize) {
                f2 = (float)this.mMaxIconSize / (float)n3;
                n5 = this.mMaxIconSize;
                n4 = (int)((float)n2 * f2);
            }
            n3 = n4;
            n2 = n5;
            if (n4 > this.mMaxIconSize) {
                f2 = (float)this.mMaxIconSize / (float)n4;
                n3 = this.mMaxIconSize;
                n2 = (int)((float)n5 * f2);
            }
            drawable2.setBounds(0, 0, n2, n3);
        }
        this.updateTextButtonVisibility();
    }

    public void setIsInSplit(boolean bl2) {
        this.mIsInSplit = bl2;
    }

    public void setIsOverflowActor(boolean bl2) {
        this.mIsOverflowActor = bl2;
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker itemInvoker) {
        this.mItemInvoker = itemInvoker;
    }

    public void setPadding(int n2, int n3, int n4, int n5) {
        this.mSavedPaddingLeft = n2;
        super.setPadding(n2, n3, n4, n5);
    }

    public void setPopupCallback(PopupCallback popupCallback) {
        this.mPopupCallback = popupCallback;
    }

    @Override
    public void setShortcut(boolean bl2, char c2) {
    }

    @Override
    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.setContentDescription(this.mTitle);
        this.updateTextButtonVisibility();
    }

    @Override
    public boolean showsIcon() {
        return true;
    }

    class ActionMenuItemForwardingListener
    extends ListPopupWindow.ForwardingListener {
        public ActionMenuItemForwardingListener() {
            super((View)ActionMenuItemView.this);
        }

        @Override
        public ListPopupWindow getPopup() {
            if (ActionMenuItemView.this.mPopupCallback != null) {
                return ActionMenuItemView.this.mPopupCallback.getPopup();
            }
            return null;
        }

        @Override
        protected boolean onForwardingStarted() {
            boolean bl2;
            boolean bl3 = bl2 = false;
            if (ActionMenuItemView.this.mItemInvoker != null) {
                bl3 = bl2;
                if (ActionMenuItemView.this.mItemInvoker.invokeItem(ActionMenuItemView.this.mItemData)) {
                    ListPopupWindow listPopupWindow = this.getPopup();
                    bl3 = bl2;
                    if (listPopupWindow != null) {
                        bl3 = bl2;
                        if (listPopupWindow.isShowing()) {
                            bl3 = true;
                        }
                    }
                }
            }
            return bl3;
        }

        @Override
        protected boolean onForwardingStopped() {
            ListPopupWindow listPopupWindow = this.getPopup();
            if (listPopupWindow != null) {
                listPopupWindow.dismiss();
                return true;
            }
            return false;
        }
    }

    public static abstract class PopupCallback {
        public abstract ListPopupWindow getPopup();
    }

}

