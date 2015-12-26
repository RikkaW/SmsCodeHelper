/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.Layout
 *  android.text.TextUtils
 *  android.text.TextUtils$TruncateAt
 *  android.util.AttributeSet
 *  android.view.ContextThemeWrapper
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.TouchDelegate
 *  android.view.View
 *  android.view.View$BaseSavedState
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.View$OnLayoutChangeListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewParent
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar$1;
import android.support.v7.widget.Toolbar$2;
import android.support.v7.widget.Toolbar$3;
import android.support.v7.widget.Toolbar$4;
import android.support.v7.widget.Toolbar$SavedState$1;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Toolbar
extends ViewGroup {
    private static final String TAG = "Toolbar";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private int mButtonGravity;
    private ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private final RtlSpacingHelper mContentInsets = new RtlSpacingHelper();
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity = 8388627;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private MenuBuilder.Callback mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    protected ViewPropertyAnimatorCompat mMenuViewVisibilityAnim;
    private ImageButton mNavButtonView;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    protected boolean mSplitActionBar;
    protected ViewGroup mSplitView;
    protected boolean mSplitWhenNarrow;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins = new int[2];
    private final ArrayList<View> mTempViews = new ArrayList();
    private final TintManager mTintManager;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    protected final VisibilityAnimListener mVisAnimListener;
    private ToolbarWidgetWrapper mWrapper;

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public Toolbar(Context object, @Nullable AttributeSet object2, int n2) {
        super((Context)object, (AttributeSet)object2, n2);
        this.mMenuViewItemClickListener = new Toolbar$1(this);
        this.mShowOverflowMenuRunnable = new Toolbar$2(this);
        this.mVisAnimListener = new VisibilityAnimListener();
        object = TintTypedArray.obtainStyledAttributes(this.getContext(), (AttributeSet)object2, R.styleable.Toolbar, n2, 0);
        this.mTitleTextAppearance = object.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = object.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = object.getInteger(R.styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = object.getInteger(R.styleable.Toolbar_mzButtonGravity, 48);
        this.mTitleMarginBottom = n2 = object.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, 0);
        this.mTitleMarginTop = n2;
        this.mTitleMarginEnd = n2;
        this.mTitleMarginStart = n2;
        n2 = object.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
        if (n2 >= 0) {
            this.mTitleMarginStart = n2;
        }
        if ((n2 = object.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginEnd, -1)) >= 0) {
            this.mTitleMarginEnd = n2;
        }
        if ((n2 = object.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginTop, -1)) >= 0) {
            this.mTitleMarginTop = n2;
        }
        if ((n2 = object.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginBottom, -1)) >= 0) {
            this.mTitleMarginBottom = n2;
        }
        this.mMaxButtonHeight = object.getDimensionPixelSize(R.styleable.Toolbar_maxButtonHeight, -1);
        n2 = object.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int n3 = object.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int n4 = object.getDimensionPixelSize(R.styleable.Toolbar_contentInsetLeft, 0);
        int n5 = object.getDimensionPixelSize(R.styleable.Toolbar_contentInsetRight, 0);
        this.mContentInsets.setAbsolute(n4, n5);
        if (n2 != Integer.MIN_VALUE || n3 != Integer.MIN_VALUE) {
            this.mContentInsets.setRelative(n2, n3);
        }
        this.mCollapseIcon = object.getDrawable(R.styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = object.getText(R.styleable.Toolbar_collapseContentDescription);
        object2 = object.getText(R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty((CharSequence)object2)) {
            this.setTitle((CharSequence)object2);
        }
        if (!TextUtils.isEmpty((CharSequence)(object2 = object.getText(R.styleable.Toolbar_subtitle)))) {
            this.setSubtitle((CharSequence)object2);
        }
        this.mPopupContext = this.getContext();
        this.setPopupTheme(object.getResourceId(R.styleable.Toolbar_popupTheme, 0));
        object2 = object.getDrawable(R.styleable.Toolbar_navigationIcon);
        if (object2 != null) {
            this.setNavigationIcon((Drawable)object2);
        }
        if (!TextUtils.isEmpty((CharSequence)(object2 = object.getText(R.styleable.Toolbar_navigationContentDescription)))) {
            this.setNavigationContentDescription((CharSequence)object2);
        }
        object.recycle();
        this.mTintManager = object.getTintManager();
    }

    static /* synthetic */ OnMenuItemClickListener access$000(Toolbar toolbar) {
        return toolbar.mOnMenuItemClickListener;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void addCustomViewsWithGravity(List<View> list, int n2) {
        boolean bl2 = true;
        int n3 = 0;
        if (ViewCompat.getLayoutDirection((View)this) != 1) {
            bl2 = false;
        }
        int n4 = this.getChildCount();
        int n5 = GravityCompat.getAbsoluteGravity(n2, ViewCompat.getLayoutDirection((View)this));
        list.clear();
        if (bl2) {
            for (n2 = n4 - 1; n2 >= 0; --n2) {
                View view = this.getChildAt(n2);
                LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                if (layoutParams.mViewType != 0 || !this.shouldLayout(view) || this.getChildHorizontalGravity(layoutParams.gravity) != n5) continue;
                list.add(view);
            }
            return;
        } else {
            for (n2 = n3; n2 < n4; ++n2) {
                View view = this.getChildAt(n2);
                LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                if (layoutParams.mViewType != 0 || !this.shouldLayout(view) || this.getChildHorizontalGravity(layoutParams.gravity) != n5) continue;
                list.add(view);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void addSystemView(View view) {
        Object object = view.getLayoutParams();
        object = object == null ? this.generateDefaultLayoutParams() : (!this.checkLayoutParams((ViewGroup.LayoutParams)object) ? this.generateLayoutParams((ViewGroup.LayoutParams)object) : (LayoutParams)((Object)object));
        object.mViewType = 1;
        this.addView(view, (ViewGroup.LayoutParams)object);
    }

    private void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            this.mCollapseButtonView = new ImageButton(this.getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            LayoutParams layoutParams = this.generateDefaultLayoutParams();
            layoutParams.gravity = 8388611 | this.mButtonGravity & 112;
            layoutParams.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            this.mCollapseButtonView.setOnClickListener((View.OnClickListener)new Toolbar$4(this));
        }
    }

    private void ensureLogoView() {
        if (this.mLogoView == null) {
            this.mLogoView = new ImageView(this.getContext());
        }
    }

    private void ensureMenu() {
        this.ensureMenuView();
        if (this.mMenuView.peekMenu() == null) {
            MenuBuilder menuBuilder = (MenuBuilder)this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void ensureMenuView() {
        if (this.mMenuView != null) return;
        this.mMenuView = new ActionMenuView(this.getContext());
        this.mMenuView.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
        this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
        LayoutParams layoutParams = this.generateDefaultLayoutParams();
        layoutParams.gravity = 8388613 | this.mButtonGravity & 112;
        this.mMenuView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.mMenuView.setId(R.id.mz_action_menu_view);
        if (this.mSplitActionBar) {
            layoutParams.width = -1;
            this.mSplitView.addView((View)this.mMenuView, 0, (ViewGroup.LayoutParams)layoutParams);
            return;
        }
        this.addSystemView((View)this.mMenuView);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ensureNavButtonView() {
        boolean bl2 = true;
        if (this.mNavButtonView != null) return;
        this.mNavButtonView = new ImageButton(this.getContext(), null, R.attr.toolbarNavigationButtonStyle);
        Object object = this.generateDefaultLayoutParams();
        object.gravity = 8388611 | this.mButtonGravity & 112;
        this.mNavButtonView.setLayoutParams((ViewGroup.LayoutParams)object);
        this.mNavButtonView.setId(R.id.mz_toolbar_nav_button);
        if (ViewCompat.getLayoutDirection((View)this) != 1) {
            bl2 = false;
        }
        if (bl2) {
            object.rightMargin = this.getResources().getDimensionPixelOffset(R.dimen.mz_toolbar_nav_btn_margin_left);
        } else {
            object.leftMargin = this.getResources().getDimensionPixelOffset(R.dimen.mz_toolbar_nav_btn_margin_left);
        }
        this.mNavButtonView.addOnLayoutChangeListener((View.OnLayoutChangeListener)new Toolbar$3(this));
        if (Build.VERSION.SDK_INT < 21) {
            object = new aqt((View)this.mNavButtonView, R.attr.mzActionButtonRippleSplitStyle);
            this.mNavButtonView.setBackgroundDrawable((Drawable)object);
            this.setClipChildren(false);
        }
    }

    private int getChildHorizontalGravity(int n2) {
        int n3;
        int n4 = ViewCompat.getLayoutDirection((View)this);
        n2 = n3 = GravityCompat.getAbsoluteGravity(n2, n4) & 7;
        switch (n3) {
            default: {
                if (n4 != 1) break;
                n2 = 5;
            }
            case 1: 
            case 3: 
            case 5: {
                return n2;
            }
        }
        return 3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int getChildTop(View view, int n2) {
        int n3;
        int n4;
        int n5;
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        int n6 = view.getMeasuredHeight();
        if (n2 > 0) {
            n2 = (n6 - n2) / 2;
        }
        switch (this.getChildVerticalGravity(layoutParams.gravity)) {
            default: {
                n5 = this.getPaddingTop();
                n4 = this.getPaddingBottom();
                n3 = this.getHeight();
                n2 = (n3 - n5 - n4 - n6) / 2;
                if (n2 >= layoutParams.topMargin) break;
                n2 = layoutParams.topMargin;
                return n2 + n5;
            }
            case 48: {
                return this.getPaddingTop();
            }
            case 80: {
                return this.getHeight() - this.getPaddingBottom() - n6 - layoutParams.bottomMargin;
            }
        }
        if ((n6 = n3 - n4 - n6 - n2 - n5) >= layoutParams.bottomMargin) return n2 + n5;
        n2 = Math.max((int)0, (int)(n2 - (layoutParams.bottomMargin - n6)));
        return n2 + n5;
    }

    private int getChildVerticalGravity(int n2) {
        int n3;
        n2 = n3 = n2 & 112;
        switch (n3) {
            default: {
                n2 = this.mGravity & 112;
            }
            case 16: 
            case 48: 
            case 80: 
        }
        return n2;
    }

    private int getHorizontalMargins(View view) {
        view = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int n2 = MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams)view);
        return MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams)view) + n2;
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.getContext());
    }

    private int getVerticalMargins(View view) {
        view = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int n2 = view.topMargin;
        return view.bottomMargin + n2;
    }

    private int getViewListMeasuredWidth(List<View> list, int[] object) {
        int n2 = object[0];
        int n3 = object[1];
        int n4 = list.size();
        int n5 = 0;
        int n6 = 0;
        while (n5 < n4) {
            object = (Object)list.get(n5);
            LayoutParams layoutParams = (LayoutParams)object.getLayoutParams();
            n2 = layoutParams.leftMargin - n2;
            n3 = layoutParams.rightMargin - n3;
            int n7 = Math.max((int)0, (int)n2);
            int n8 = Math.max((int)0, (int)n3);
            n2 = Math.max((int)0, (int)(- n2));
            n3 = Math.max((int)0, (int)(- n3));
            int n9 = object.getMeasuredWidth();
            ++n5;
            n6 += n9 + n7 + n8;
        }
        return n6;
    }

    private static boolean isCustomView(View view) {
        if (((LayoutParams)view.getLayoutParams()).mViewType == 0) {
            return true;
        }
        return false;
    }

    private int layoutChildLeft(View view, int n2, int[] arrn, int n3) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        int n4 = layoutParams.leftMargin - arrn[0];
        n2 = Math.max((int)0, (int)n4) + n2;
        arrn[0] = Math.max((int)0, (int)(- n4));
        n3 = this.getChildTop(view, n3);
        n4 = view.getMeasuredWidth();
        view.layout(n2, n3, n2 + n4, view.getMeasuredHeight() + n3);
        return layoutParams.rightMargin + n4 + n2;
    }

    private int layoutChildRight(View view, int n2, int[] arrn, int n3) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        int n4 = layoutParams.rightMargin - arrn[1];
        arrn[1] = Math.max((int)0, (int)(- n4));
        n3 = this.getChildTop(view, n3);
        n4 = view.getMeasuredWidth();
        view.layout(n2 - n4, n3, n2 -= Math.max((int)0, (int)n4), view.getMeasuredHeight() + n3);
        return n2 - (layoutParams.leftMargin + n4);
    }

    private int measureChildCollapseMargins(View view, int n2, int n3, int n4, int n5, int[] arrn) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int n6 = marginLayoutParams.leftMargin - arrn[0];
        int n7 = marginLayoutParams.rightMargin - arrn[1];
        int n8 = Math.max((int)0, (int)n6) + Math.max((int)0, (int)n7);
        arrn[0] = Math.max((int)0, (int)(- n6));
        arrn[1] = Math.max((int)0, (int)(- n7));
        view.measure(Toolbar.getChildMeasureSpec((int)n2, (int)(this.getPaddingLeft() + this.getPaddingRight() + n8 + n3), (int)marginLayoutParams.width), Toolbar.getChildMeasureSpec((int)n4, (int)(this.getPaddingTop() + this.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + n5), (int)marginLayoutParams.height));
        return view.getMeasuredWidth() + n8;
    }

    private void measureChildConstrained(View view, int n2, int n3, int n4, int n5, int n6) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int n7 = Toolbar.getChildMeasureSpec((int)n2, (int)(this.getPaddingLeft() + this.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + n3), (int)marginLayoutParams.width);
        n3 = Toolbar.getChildMeasureSpec((int)n4, (int)(this.getPaddingTop() + this.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + n5), (int)marginLayoutParams.height);
        n4 = View.MeasureSpec.getMode((int)n3);
        n2 = n3;
        if (n4 != 1073741824) {
            n2 = n3;
            if (n6 >= 0) {
                n2 = n6;
                if (n4 != 0) {
                    n2 = Math.min((int)View.MeasureSpec.getSize((int)n3), (int)n6);
                }
                n2 = View.MeasureSpec.makeMeasureSpec((int)n2, (int)1073741824);
            }
        }
        view.measure(n7, n2);
    }

    /*
     * Exception decompiling
     */
    private void onLayoutForFlyme(boolean var1_1, int var2_2, int var3_3, int var4_4, int var5_5) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.rebuildSwitches(SwitchReplacer.java:334)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:527)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onMeasureForFlyme(int n2, int n3) {
        int n4;
        int n5;
        int n6 = 0;
        int n7 = 0;
        int[] arrn = this.mTempMargins;
        if (ViewUtils.isLayoutRtl((View)this)) {
            n5 = 0;
            n4 = 1;
        } else {
            n5 = 1;
            n4 = 0;
        }
        int n8 = 0;
        if (this.shouldLayout((View)this.mNavButtonView)) {
            this.measureChildConstrained((View)this.mNavButtonView, n2, 0, n3, 0, this.mMaxButtonHeight);
            n8 = this.mNavButtonView.getMeasuredWidth() + this.getHorizontalMargins((View)this.mNavButtonView);
            n6 = Math.max((int)0, (int)(this.mNavButtonView.getMeasuredHeight() + this.getVerticalMargins((View)this.mNavButtonView)));
            n7 = ViewUtils.combineMeasuredStates(0, ViewCompat.getMeasuredState((View)this.mNavButtonView));
        }
        int n9 = n8;
        int n10 = n7;
        n8 = n6;
        if (this.shouldLayout((View)this.mCollapseButtonView)) {
            this.measureChildConstrained((View)this.mCollapseButtonView, n2, 0, n3, 0, this.mMaxButtonHeight);
            n9 = this.mCollapseButtonView.getMeasuredWidth() + this.getHorizontalMargins((View)this.mCollapseButtonView);
            n8 = Math.max((int)n6, (int)(this.mCollapseButtonView.getMeasuredHeight() + this.getVerticalMargins((View)this.mCollapseButtonView)));
            n10 = ViewUtils.combineMeasuredStates(n7, ViewCompat.getMeasuredState((View)this.mCollapseButtonView));
        }
        n6 = this.getContentInsetStart();
        n7 = 0 + Math.max((int)n6, (int)n9);
        arrn[n4] = Math.max((int)0, (int)(n6 - n9));
        if (this.shouldLayout((View)this.mMenuView)) {
            this.measureChildConstrained((View)this.mMenuView, n2, n7, n3, 0, this.mMaxButtonHeight);
            n6 = this.mMenuView.getMeasuredWidth();
            n4 = this.getHorizontalMargins((View)this.mMenuView);
            n8 = Math.max((int)n8, (int)(this.mMenuView.getMeasuredHeight() + this.getVerticalMargins((View)this.mMenuView)));
            n10 = ViewUtils.combineMeasuredStates(n10, ViewCompat.getMeasuredState((View)this.mMenuView));
            n9 = n6 + n4;
        } else {
            n9 = 0;
        }
        n6 = this.getContentInsetEnd();
        arrn[n5] = Math.max((int)0, (int)(n6 - n9));
        n4 = n7 += Math.max((int)n6, (int)n9);
        n6 = n10;
        n5 = n8;
        if (this.shouldLayout(this.mExpandedActionView)) {
            n4 = n7 + this.measureChildCollapseMargins(this.mExpandedActionView, n2, n7, n3, 0, arrn);
            n5 = Math.max((int)n8, (int)(this.mExpandedActionView.getMeasuredHeight() + this.getVerticalMargins(this.mExpandedActionView)));
            n6 = ViewUtils.combineMeasuredStates(n10, ViewCompat.getMeasuredState(this.mExpandedActionView));
        }
        n7 = n4;
        n8 = n6;
        n10 = n5;
        if (this.shouldLayout((View)this.mLogoView)) {
            n7 = n4 + this.measureChildCollapseMargins((View)this.mLogoView, n2, n4, n3, 0, arrn);
            n10 = Math.max((int)n5, (int)(this.mLogoView.getMeasuredHeight() + this.getVerticalMargins((View)this.mLogoView)));
            n8 = ViewUtils.combineMeasuredStates(n6, ViewCompat.getMeasuredState((View)this.mLogoView));
        }
        n5 = this.getChildCount();
        for (n6 = 0; n6 < n5; ++n6) {
            View view = this.getChildAt(n6);
            if (((LayoutParams)view.getLayoutParams()).mViewType != 0 || !this.shouldLayout(view)) continue;
            n7 += this.measureChildCollapseMargins(view, n2, n7, n3, 0, arrn);
            n10 = Math.max((int)n10, (int)(view.getMeasuredHeight() + this.getVerticalMargins(view)));
            n8 = ViewUtils.combineMeasuredStates(n8, ViewCompat.getMeasuredState(view));
        }
        n4 = 0;
        n5 = 0;
        int n11 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int n12 = this.mTitleMarginStart + this.mTitleMarginEnd;
        switch (this.mGravity & 7) {
            default: {
                n9 = n7;
                break;
            }
            case 1: {
                n9 *= 2;
            }
        }
        n6 = n8;
        if (this.shouldLayout((View)this.mTitleTextView)) {
            this.measureChildCollapseMargins((View)this.mTitleTextView, n2, n9 + n12, n3, n11, arrn);
            n6 = this.mTitleTextView.getMeasuredWidth();
            n4 = this.getHorizontalMargins((View)this.mTitleTextView) + n6;
            n5 = this.mTitleTextView.getMeasuredHeight() + this.getVerticalMargins((View)this.mTitleTextView);
            n6 = ViewUtils.combineMeasuredStates(n8, ViewCompat.getMeasuredState((View)this.mTitleTextView));
        }
        int n13 = n5;
        int n14 = n4;
        n8 = n6;
        if (this.shouldLayout((View)this.mSubtitleTextView)) {
            n14 = Math.max((int)n4, (int)this.measureChildCollapseMargins((View)this.mSubtitleTextView, n2, n9 + n12, n3, n11 + n5, arrn));
            n13 = n5 + (this.mSubtitleTextView.getMeasuredHeight() + this.getVerticalMargins((View)this.mSubtitleTextView));
            n8 = ViewUtils.combineMeasuredStates(n6, ViewCompat.getMeasuredState((View)this.mSubtitleTextView));
        }
        n10 = Math.max((int)n10, (int)n13);
        n4 = this.getPaddingLeft();
        n9 = this.getPaddingRight();
        n6 = this.getPaddingTop();
        n5 = this.getPaddingBottom();
        n7 = ViewCompat.resolveSizeAndState(Math.max((int)(n7 + n14 + (n4 + n9)), (int)this.getSuggestedMinimumWidth()), n2, -16777216 & n8);
        n2 = ViewCompat.resolveSizeAndState(Math.max((int)(n10 + (n6 + n5)), (int)this.getSuggestedMinimumHeight()), n3, n8 << 16);
        if (this.shouldCollapse()) {
            n2 = 0;
        }
        this.setMeasuredDimension(n7, n2);
    }

    private void postShowOverflowMenu() {
        this.removeCallbacks(this.mShowOverflowMenuRunnable);
        this.post(this.mShowOverflowMenuRunnable);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setChildVisibilityForExpandedActionView(boolean bl2) {
        int n2 = this.getChildCount();
        int n3 = 0;
        while (n3 < n2) {
            View view = this.getChildAt(n3);
            if (((LayoutParams)view.getLayoutParams()).mViewType != 2 && view != this.mMenuView) {
                int n4 = bl2 ? 8 : 0;
                view.setVisibility(n4);
            }
            ++n3;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean shouldCollapse() {
        if (!this.mCollapsible) {
            return false;
        }
        int n2 = this.getChildCount();
        int n3 = 0;
        while (n3 < n2) {
            View view = this.getChildAt(n3);
            if (this.shouldLayout(view) && view.getMeasuredWidth() > 0) {
                if (view.getMeasuredHeight() > 0) return false;
            }
            ++n3;
        }
        return true;
    }

    private boolean shouldLayout(View view) {
        if (view != null && view.getParent() == this && view.getVisibility() != 8) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateChildVisibilityForExpandedActionView(View view) {
        if (((LayoutParams)view.getLayoutParams()).mViewType != 2 && view != this.mMenuView) {
            int n2 = this.mExpandedActionView != null ? 8 : 0;
            view.setVisibility(n2);
        }
    }

    public boolean canShowOverflowMenu() {
        if (this.getVisibility() == 0 && this.mMenuView != null && this.mMenuView.isOverflowReserved()) {
            return true;
        }
        return false;
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (super.checkLayoutParams(layoutParams) && layoutParams instanceof LayoutParams) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void collapseActionView() {
        if (this.mExpandedMenuPresenter == null) {
            return;
        }
        MenuItemImpl menuItemImpl = this.mExpandedMenuPresenter.mCurrentExpandedItem;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public void dismissPopupMenus() {
        if (this.mMenuView != null) {
            this.mMenuView.dismissPopupMenus();
        }
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(this.getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams)layoutParams);
        }
        if (layoutParams instanceof ActionBar.LayoutParams) {
            return new LayoutParams((ActionBar.LayoutParams)layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams)layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public int getContentInsetEnd() {
        return this.mContentInsets.getEnd();
    }

    public int getContentInsetLeft() {
        return this.mContentInsets.getLeft();
    }

    public int getContentInsetRight() {
        return this.mContentInsets.getRight();
    }

    public int getContentInsetStart() {
        return this.mContentInsets.getStart();
    }

    public Drawable getLogo() {
        if (this.mLogoView != null) {
            return this.mLogoView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        if (this.mLogoView != null) {
            return this.mLogoView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        this.ensureMenu();
        return this.mMenuView.getMenu();
    }

    @Nullable
    public CharSequence getNavigationContentDescription() {
        if (this.mNavButtonView != null) {
            return this.mNavButtonView.getContentDescription();
        }
        return null;
    }

    @Nullable
    public Drawable getNavigationIcon() {
        if (this.mNavButtonView != null) {
            return this.mNavButtonView.getDrawable();
        }
        return null;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public DecorToolbar getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }
        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        if (this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null) {
            return true;
        }
        return false;
    }

    public boolean hideOverflowMenu() {
        if (this.mMenuView != null && this.mMenuView.hideOverflowMenu()) {
            return true;
        }
        return false;
    }

    public void inflateMenu(int n2) {
        this.getMenuInflater().inflate(n2, this.getMenu());
    }

    public boolean isOverflowMenuShowPending() {
        if (this.mMenuView != null && this.mMenuView.isOverflowMenuShowPending()) {
            return true;
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mMenuView != null && this.mMenuView.isOverflowMenuShowing()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isTitleTruncated() {
        Layout layout2;
        if (this.mTitleTextView == null || (layout2 = this.mTitleTextView.getLayout()) == null) {
            return false;
        }
        int n2 = layout2.getLineCount();
        int n3 = 0;
        while (n3 < n2) {
            if (layout2.getEllipsisCount(n3) > 0) {
                return true;
            }
            ++n3;
        }
        return false;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (this.mSplitWhenNarrow) {
            this.setSplitToolbar(this.getContext().getResources().getBoolean(R.bool.mz_split_action_bar_is_narrow));
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int n2 = MotionEventCompat.getActionMasked(motionEvent);
        if (n2 == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean bl2 = super.onHoverEvent(motionEvent);
            if (n2 == 9 && !bl2) {
                this.mEatingHover = true;
            }
        }
        if (n2 == 10 || n2 == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        this.onLayoutForFlyme(bl2, n2, n3, n4, n5);
    }

    protected void onMeasure(int n2, int n3) {
        this.onMeasureForFlyme(n2, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onRestoreInstanceState(Parcelable object) {
        SavedState savedState = (SavedState)((Object)object);
        super.onRestoreInstanceState(savedState.getSuperState());
        object = this.mMenuView != null ? this.mMenuView.peekMenu() : null;
        if (savedState.expandedMenuItemId != 0 && this.mExpandedMenuPresenter != null && object != null && (object = object.findItem(savedState.expandedMenuItemId)) != null) {
            MenuItemCompat.expandActionView((MenuItem)object);
        }
        if (savedState.isOverflowOpen) {
            this.postShowOverflowMenu();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onRtlPropertiesChanged(int n2) {
        boolean bl2 = true;
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(n2);
        }
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (n2 != 1) {
            bl2 = false;
        }
        rtlSpacingHelper.setDirection(bl2);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null) {
            savedState.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        savedState.isOverflowOpen = this.isOverflowMenuShowing();
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int n2 = MotionEventCompat.getActionMasked(motionEvent);
        if (n2 == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean bl2 = super.onTouchEvent(motionEvent);
            if (n2 == 0 && !bl2) {
                this.mEatingTouch = true;
            }
        }
        if (n2 == 1 || n2 == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    public void setCollapsible(boolean bl2) {
        this.mCollapsible = bl2;
        this.requestLayout();
    }

    public void setContentInsetsAbsolute(int n2, int n3) {
        this.mContentInsets.setAbsolute(n2, n3);
    }

    public void setContentInsetsRelative(int n2, int n3) {
        this.mContentInsets.setRelative(n2, n3);
    }

    public void setLogo(int n2) {
        this.setLogo(this.mTintManager.getDrawable(n2));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setLogo(Drawable drawable2) {
        if (drawable2 != null) {
            this.ensureLogoView();
            if (this.mLogoView.getParent() == null) {
                this.addSystemView((View)this.mLogoView);
                this.updateChildVisibilityForExpandedActionView((View)this.mLogoView);
            }
        } else if (this.mLogoView != null && this.mLogoView.getParent() != null) {
            this.removeView((View)this.mLogoView);
        }
        if (this.mLogoView != null) {
            this.mLogoView.setImageDrawable(drawable2);
        }
    }

    public void setLogoDescription(int n2) {
        this.setLogoDescription(this.getContext().getText(n2));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty((CharSequence)charSequence)) {
            this.ensureLogoView();
        }
        if (this.mLogoView != null) {
            this.mLogoView.setContentDescription(charSequence);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void setMenu(MenuBuilder menuBuilder, ActionMenuPresenter actionMenuPresenter) {
        if (menuBuilder == null && this.mMenuView == null) {
            return;
        }
        this.ensureMenuView();
        MenuBuilder menuBuilder2 = this.mMenuView.peekMenu();
        if (menuBuilder2 == menuBuilder) return;
        if (menuBuilder2 != null) {
            menuBuilder2.removeMenuPresenter(this.mOuterActionMenuPresenter);
            menuBuilder2.removeMenuPresenter(this.mExpandedMenuPresenter);
        }
        if (this.mExpandedMenuPresenter == null) {
            this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
        }
        actionMenuPresenter.setExpandedActionViewsExclusive(true);
        if (menuBuilder != null) {
            menuBuilder.addMenuPresenter(actionMenuPresenter, this.mPopupContext);
            menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        } else {
            actionMenuPresenter.initForMenu(this.mPopupContext, null);
            this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
            actionMenuPresenter.updateMenuView(true);
            this.mExpandedMenuPresenter.updateMenuView(true);
        }
        this.mMenuView.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setPresenter(actionMenuPresenter);
        this.mOuterActionMenuPresenter = actionMenuPresenter;
    }

    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
    }

    public void setMenuViewAnimateToVisibility(int n2) {
        if (!this.mSplitActionBar || this.mMenuView == null) {
            return;
        }
        if (n2 == 0) {
            ViewCompat.setAlpha((View)this.mMenuView, 0.0f);
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this.mMenuView).alpha(1.0f);
            viewPropertyAnimatorCompat.setDuration(160);
            viewPropertyAnimatorCompat.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, n2));
            viewPropertyAnimatorCompat.start();
            return;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this.mMenuView).alpha(0.0f);
        viewPropertyAnimatorCompat.setDuration(160);
        viewPropertyAnimatorCompat.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, n2));
        viewPropertyAnimatorCompat.start();
    }

    public void setMenuVisibility(int n2) {
        if (this.mMenuView != null && this.mSplitActionBar) {
            this.mMenuView.setVisibility(n2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setNavigationContentDescription(int n2) {
        CharSequence charSequence = n2 != 0 ? this.getContext().getText(n2) : null;
        this.setNavigationContentDescription(charSequence);
    }

    public void setNavigationContentDescription(@Nullable CharSequence charSequence) {
        if (!TextUtils.isEmpty((CharSequence)charSequence)) {
            this.ensureNavButtonView();
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int n2) {
        this.setNavigationIcon(this.mTintManager.getDrawable(n2));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setNavigationIcon(@Nullable Drawable drawable2) {
        if (drawable2 != null) {
            this.ensureNavButtonView();
            if (this.mNavButtonView.getParent() == null) {
                this.addSystemView((View)this.mNavButtonView);
                this.updateChildVisibilityForExpandedActionView((View)this.mNavButtonView);
            }
        } else if (this.mNavButtonView != null && this.mNavButtonView.getParent() != null) {
            this.removeView((View)this.mNavButtonView);
            this.setTouchDelegate(null);
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setImageDrawable(drawable2);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        this.ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setPopupTheme(int n2) {
        if (this.mPopupTheme == n2) return;
        this.mPopupTheme = n2;
        if (n2 == 0) {
            this.mPopupContext = this.getContext();
            return;
        }
        this.mPopupContext = new ContextThemeWrapper(this.getContext(), n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSplitToolbar(boolean bl2) {
        if (this.mSplitActionBar != bl2) {
            ViewGroup viewGroup;
            this.mSplitActionBar = bl2;
            if (this.mMenuView != null) {
                viewGroup = (ViewGroup)this.mMenuView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView((View)this.mMenuView);
                }
                if (bl2) {
                    if (this.mSplitView != null) {
                        this.mSplitView.addView((View)this.mMenuView);
                    }
                    this.mMenuView.getLayoutParams().width = -1;
                } else {
                    this.addSystemView((View)this.mMenuView);
                    this.mMenuView.getLayoutParams().width = -2;
                }
                this.mMenuView.requestLayout();
            }
            if (this.mSplitView != null) {
                viewGroup = this.mSplitView;
                int n2 = bl2 ? 0 : 8;
                viewGroup.setVisibility(n2);
            }
        }
    }

    public void setSplitView(ViewGroup viewGroup) {
        this.mSplitView = viewGroup;
    }

    public void setSubtitle(int n2) {
        this.setSubtitle(this.getContext().getText(n2));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty((CharSequence)charSequence)) {
            if (this.mSubtitleTextView == null) {
                Context context = this.getContext();
                this.mSubtitleTextView = new TextView(context);
                this.mSubtitleTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                if (this.mSubtitleTextAppearance != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, this.mSubtitleTextAppearance);
                }
                if (this.mSubtitleTextColor != 0) {
                    this.mSubtitleTextView.setTextColor(this.mSubtitleTextColor);
                }
            }
            if (this.mSubtitleTextView.getParent() == null) {
                this.addSystemView((View)this.mSubtitleTextView);
                this.updateChildVisibilityForExpandedActionView((View)this.mSubtitleTextView);
            }
        } else if (this.mSubtitleTextView != null && this.mSubtitleTextView.getParent() != null) {
            this.removeView((View)this.mSubtitleTextView);
        }
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setSubtitleTextAppearance(Context context, int n2) {
        this.mSubtitleTextAppearance = n2;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextAppearance(context, n2);
        }
    }

    public void setSubtitleTextColor(int n2) {
        this.mSubtitleTextColor = n2;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextColor(n2);
        }
    }

    public void setTitle(int n2) {
        this.setTitle(this.getContext().getText(n2));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty((CharSequence)charSequence)) {
            if (this.mTitleTextView == null) {
                Context context = this.getContext();
                this.mTitleTextView = new TextView(context);
                this.mTitleTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                this.mTitleTextView.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen.mz_toolbar_title_max_width));
                if (this.mTitleTextAppearance != 0) {
                    this.mTitleTextView.setTextAppearance(context, this.mTitleTextAppearance);
                }
                if (this.mTitleTextColor != 0) {
                    this.mTitleTextView.setTextColor(this.mTitleTextColor);
                }
            }
            if (this.mTitleTextView.getParent() == null) {
                this.addSystemView((View)this.mTitleTextView);
                this.updateChildVisibilityForExpandedActionView((View)this.mTitleTextView);
            }
        } else if (this.mTitleTextView != null && this.mTitleTextView.getParent() != null) {
            this.removeView((View)this.mTitleTextView);
        }
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    public void setTitleTextAppearance(Context context, int n2) {
        this.mTitleTextAppearance = n2;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextAppearance(context, n2);
        }
    }

    public void setTitleTextColor(int n2) {
        this.mTitleTextColor = n2;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextColor(n2);
        }
    }

    public boolean showOverflowMenu() {
        if (this.mMenuView != null && this.mMenuView.showOverflowMenu()) {
            return true;
        }
        return false;
    }

    class ExpandedActionViewMenuPresenter
    implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;

        private ExpandedActionViewMenuPresenter() {
        }

        /* synthetic */ ExpandedActionViewMenuPresenter(Toolbar$1 toolbar$1) {
            this();
        }

        @Override
        public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewCollapsed();
            }
            Toolbar.this.removeView(Toolbar.this.mExpandedActionView);
            Toolbar.this.removeView((View)Toolbar.this.mCollapseButtonView);
            Toolbar.this.mExpandedActionView = null;
            Toolbar.this.setChildVisibilityForExpandedActionView(false);
            this.mCurrentExpandedItem = null;
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(false);
            return true;
        }

        @Override
        public boolean expandItemActionView(MenuBuilder object, MenuItemImpl menuItemImpl) {
            Toolbar.this.ensureCollapseButtonView();
            if (Toolbar.this.mCollapseButtonView.getParent() != Toolbar.this) {
                Toolbar.this.addView((View)Toolbar.this.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = menuItemImpl.getActionView();
            this.mCurrentExpandedItem = menuItemImpl;
            if (Toolbar.this.mExpandedActionView.getParent() != Toolbar.this) {
                object = Toolbar.this.generateDefaultLayoutParams();
                object.gravity = 8388611 | Toolbar.this.mButtonGravity & 112;
                object.mViewType = 2;
                Toolbar.this.mExpandedActionView.setLayoutParams((ViewGroup.LayoutParams)object);
                Toolbar.this.addView(Toolbar.this.mExpandedActionView);
            }
            Toolbar.this.setChildVisibilityForExpandedActionView(true);
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(true);
            if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }

        @Override
        public boolean flagActionItems() {
            return false;
        }

        @Override
        public int getId() {
            return 0;
        }

        @Override
        public MenuView getMenuView(ViewGroup viewGroup) {
            return null;
        }

        @Override
        public void initForMenu(Context context, MenuBuilder menuBuilder) {
            if (this.mMenu != null && this.mCurrentExpandedItem != null) {
                this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
            }
            this.mMenu = menuBuilder;
        }

        @Override
        public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
        }

        @Override
        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        @Override
        public Parcelable onSaveInstanceState() {
            return null;
        }

        @Override
        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        @Override
        public void setCallback(MenuPresenter.Callback callback) {
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void updateMenuView(boolean bl2) {
            boolean bl3 = false;
            if (this.mCurrentExpandedItem == null) return;
            boolean bl4 = bl3;
            if (this.mMenu != null) {
                int n2 = this.mMenu.size();
                int n3 = 0;
                do {
                    bl4 = bl3;
                    if (n3 >= n2) break;
                    if (this.mMenu.getItem(n3) == this.mCurrentExpandedItem) {
                        return;
                    }
                    ++n3;
                } while (true);
            }
            if (!bl4) {
                this.collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
            }
        }
    }

    public static class LayoutParams
    extends ActionBar.LayoutParams {
        static final int CUSTOM = 0;
        static final int EXPANDED = 2;
        static final int SYSTEM = 1;
        int mViewType = 0;

        public LayoutParams(int n2) {
            this(-2, -1, n2);
        }

        public LayoutParams(int n2, int n3) {
            super(n2, n3);
            this.gravity = 8388627;
        }

        public LayoutParams(int n2, int n3, int n4) {
            super(n2, n3);
            this.gravity = n4;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.mViewType = layoutParams.mViewType;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super((ViewGroup.LayoutParams)marginLayoutParams);
            this.copyMarginsFromCompat(marginLayoutParams);
        }

        void copyMarginsFromCompat(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }
    }

    public static interface OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem var1);
    }

    static class SavedState
    extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Toolbar$SavedState$1();
        public int expandedMenuItemId;
        public boolean isOverflowOpen;

        /*
         * Enabled aggressive block sorting
         */
        public SavedState(Parcel parcel) {
            super(parcel);
            this.expandedMenuItemId = parcel.readInt();
            boolean bl2 = parcel.readInt() != 0;
            this.isOverflowOpen = bl2;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void writeToParcel(Parcel parcel, int n2) {
            super.writeToParcel(parcel, n2);
            parcel.writeInt(this.expandedMenuItemId);
            n2 = this.isOverflowOpen ? 1 : 0;
            parcel.writeInt(n2);
        }
    }

    public class VisibilityAnimListener
    implements ViewPropertyAnimatorListener {
        private boolean mCanceled;
        int mFinalVisibility;

        protected VisibilityAnimListener() {
            this.mCanceled = false;
        }

        @Override
        public void onAnimationCancel(View view) {
            this.mCanceled = true;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        @Override
        public void onAnimationEnd(View view) {
            if (this.mCanceled) {
                return;
            }
            Toolbar.this.mMenuViewVisibilityAnim = null;
            if (Toolbar.this.mMenuView == null) return;
            Toolbar.this.mMenuView.setVisibility(this.mFinalVisibility);
        }

        @Override
        public void onAnimationStart(View view) {
            if (Toolbar.this.mMenuView != null) {
                Toolbar.this.mMenuView.setVisibility(0);
            }
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, int n2) {
            Toolbar.this.mMenuViewVisibilityAnim = viewPropertyAnimatorCompat;
            this.mFinalVisibility = n2;
            return this;
        }
    }

}

