/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.DialogInterface$OnKeyListener
 *  android.content.DialogInterface$OnMultiChoiceClickListener
 *  android.content.res.ColorStateList
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.database.Cursor
 *  android.graphics.drawable.Drawable
 *  android.os.Handler
 *  android.os.Message
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.util.TypedValue
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.AdapterView$OnItemSelectedListener
 *  android.widget.ArrayAdapter
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.ScrollView
 *  android.widget.SimpleCursorAdapter
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertController$1;
import android.support.v7.app.AlertController$AlertParams$1;
import android.support.v7.app.AlertController$AlertParams$2;
import android.support.v7.app.AlertController$AlertParams$3;
import android.support.v7.app.AlertController$AlertParams$4;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.LimitedWHLinearLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;

class AlertController {
    public static boolean mShowAtBottom;
    private ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private aqr mAlertExt;
    private final View.OnClickListener mButtonHandler;
    private Button mButtonNegative;
    private Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    private Button mButtonNeutral;
    private Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint = 0;
    private int mButtonPanelSideLayout;
    private Button mButtonPositive;
    private Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    private int mCheckedItem = -1;
    private Context mContext;
    private View mCustomTitleView;
    private final AppCompatDialog mDialog;
    private Handler mHandler;
    private boolean mHasListView = false;
    private Drawable mIcon;
    private int mIconId = 0;
    private ImageView mIconView;
    private int mListItemLayout;
    private int mListItem_ShowCentreLayout;
    private int mListLayout;
    private ListView mListView;
    private int mMaxHeight;
    private CharSequence mMessage;
    private TextView mMessageView;
    private int mMultiChoiceItemLayout;
    private int mMzButtonBarOrientation;
    private ScrollView mScrollView;
    private int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified = false;
    private int mViewSpacingTop;
    private final Window mWindow;

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        this.mButtonHandler = new AlertController$1(this);
        this.mContext = context;
        this.mDialog = appCompatDialog;
        this.mWindow = window;
        this.mHandler = new ButtonHandler((DialogInterface)appCompatDialog);
        TypedArray typedArray = context.obtainStyledAttributes(null, R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = typedArray.getResourceId(R.styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = typedArray.getResourceId(R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = typedArray.getResourceId(R.styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = typedArray.getResourceId(R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = typedArray.getResourceId(R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = typedArray.getResourceId(R.styleable.AlertDialog_listItemLayout, 0);
        this.mListItem_ShowCentreLayout = typedArray.getResourceId(R.styleable.AlertDialog_listItem_showCentreLayout, 0);
        mShowAtBottom = typedArray.getBoolean(R.styleable.AlertDialog_mzShowAtBottom, false);
        this.mMzButtonBarOrientation = typedArray.getInt(R.styleable.AlertDialog_mzButtonBarOrientation, 0);
        typedArray.recycle();
        this.mAlertExt = new aqr(context, (DialogInterface)appCompatDialog, window);
    }

    static /* synthetic */ Button access$000(AlertController alertController) {
        return alertController.mButtonPositive;
    }

    static /* synthetic */ Message access$100(AlertController alertController) {
        return alertController.mButtonPositiveMessage;
    }

    static /* synthetic */ Button access$200(AlertController alertController) {
        return alertController.mButtonNegative;
    }

    static /* synthetic */ Message access$300(AlertController alertController) {
        return alertController.mButtonNegativeMessage;
    }

    static /* synthetic */ Button access$400(AlertController alertController) {
        return alertController.mButtonNeutral;
    }

    static /* synthetic */ Message access$500(AlertController alertController) {
        return alertController.mButtonNeutralMessage;
    }

    static /* synthetic */ AppCompatDialog access$600(AlertController alertController) {
        return alertController.mDialog;
    }

    static /* synthetic */ Handler access$700(AlertController alertController) {
        return alertController.mHandler;
    }

    static boolean canTextInput(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        view = (ViewGroup)view;
        int n2 = view.getChildCount();
        while (n2 > 0) {
            int n3;
            n2 = n3 = n2 - 1;
            if (!AlertController.canTextInput(view.getChildAt(n3))) continue;
            return true;
        }
        return false;
    }

    private void centerButton(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean isTextEditable(View view) {
        int n2;
        if (view.getVisibility() != 0) {
            return false;
        }
        if (view.onCheckIsTextEditor() && view.isEnabled()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) return false;
        view = (ViewGroup)view;
        int n3 = view.getChildCount();
        do {
            if (n3 <= 0) return false;
            n3 = n2 = n3 - 1;
        } while (!this.isTextEditable(view.getChildAt(n2)));
        return true;
    }

    private int selectContentView() {
        if (this.mButtonPanelSideLayout == 0) {
            return this.mAlertDialogLayout;
        }
        if (this.mButtonPanelLayoutHint == 1) {
            return this.mButtonPanelSideLayout;
        }
        return this.mAlertDialogLayout;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean setupButtons() {
        int n2;
        LinearLayout linearLayout = (LinearLayout)this.mWindow.findViewById(R.id.buttonBarPanel);
        this.mButtonPositive = (Button)this.mWindow.findViewById(16908313);
        this.mButtonPositive.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty((CharSequence)this.mButtonPositiveText)) {
            this.mButtonPositive.setVisibility(8);
            n2 = 0;
        } else {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            this.mButtonPositive.setVisibility(0);
            n2 = 1;
        }
        this.mButtonNegative = (Button)this.mWindow.findViewById(16908314);
        this.mButtonNegative.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty((CharSequence)this.mButtonNegativeText)) {
            this.mButtonNegative.setVisibility(8);
        } else {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            this.mButtonNegative.setVisibility(0);
            n2 |= 2;
        }
        this.mButtonNeutral = (Button)this.mWindow.findViewById(16908315);
        this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty((CharSequence)this.mButtonNeutralText)) {
            this.mButtonNeutral.setVisibility(8);
        } else {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            this.mButtonNeutral.setVisibility(0);
            n2 |= 4;
        }
        if (AlertController.shouldCenterSingleButton(this.mContext)) {
            if (n2 == 1) {
                this.centerButton(this.mButtonPositive);
            } else if (n2 == 2) {
                this.centerButton(this.mButtonNegative);
            } else if (n2 == 4) {
                this.centerButton(this.mButtonNeutral);
            }
        }
        if (this.mMzButtonBarOrientation > 0) {
            linearLayout.setOrientation(1);
            linearLayout = (LinearLayout.LayoutParams)this.mButtonNegative.getLayoutParams();
            linearLayout.width = -1;
            this.mButtonNegative.setLayoutParams((ViewGroup.LayoutParams)linearLayout);
            linearLayout = (LinearLayout.LayoutParams)this.mButtonPositive.getLayoutParams();
            linearLayout.width = -1;
            this.mButtonPositive.setLayoutParams((ViewGroup.LayoutParams)linearLayout);
            linearLayout = (LinearLayout.LayoutParams)this.mButtonNeutral.getLayoutParams();
            linearLayout.width = -1;
            this.mButtonNeutral.setLayoutParams((ViewGroup.LayoutParams)linearLayout);
        }
        if (n2 != 0) {
            return true;
        }
        return false;
    }

    private void setupContent(ViewGroup viewGroup) {
        this.mScrollView = (ScrollView)this.mWindow.findViewById(R.id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mMessageView = (TextView)this.mWindow.findViewById(16908299);
        if (this.mMessageView == null) {
            return;
        }
        if (this.mMessage != null) {
            this.mMessageView.setText(this.mMessage);
            this.mAlertExt.a(this.mMessage);
            return;
        }
        this.mMessageView.setVisibility(8);
        this.mScrollView.removeView((View)this.mMessageView);
        if (this.mListView != null) {
            viewGroup = (ViewGroup)this.mScrollView.getParent();
            int n2 = viewGroup.indexOfChild((View)this.mScrollView);
            viewGroup.removeViewAt(n2);
            viewGroup.addView((View)this.mListView, n2, new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        viewGroup.setVisibility(8);
        this.adjustPaddingForButtonPanel();
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean setupTitle(ViewGroup viewGroup) {
        if (this.mCustomTitleView != null) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
            viewGroup.addView(this.mCustomTitleView, 0, layoutParams);
            this.mWindow.findViewById(R.id.title_template).setVisibility(8);
            return true;
        }
        this.mIconView = (ImageView)this.mWindow.findViewById(16908294);
        boolean bl2 = !TextUtils.isEmpty((CharSequence)this.mTitle);
        if (!bl2) {
            this.mWindow.findViewById(R.id.title_template).setVisibility(8);
            this.mIconView.setVisibility(8);
            viewGroup.setVisibility(8);
            return false;
        }
        this.mTitleView = (TextView)this.mWindow.findViewById(R.id.alertTitle);
        this.mTitleView.setText(this.mTitle);
        if (this.mIconId != 0) {
            this.mIconView.setImageResource(this.mIconId);
            return true;
        }
        if (this.mIcon != null) {
            this.mIconView.setImageDrawable(this.mIcon);
            return true;
        }
        this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
        this.mIconView.setVisibility(8);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setupView() {
        this.mWindow.setDimAmount(0.5f);
        ViewGroup viewGroup = (ViewGroup)this.mWindow.findViewById(R.id.contentPanel);
        this.setupContent(viewGroup);
        boolean bl2 = this.setupButtons();
        ViewGroup viewGroup2 = (ViewGroup)this.mWindow.findViewById(R.id.topPanel);
        TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(this.mContext, null, R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
        boolean bl3 = this.setupTitle(viewGroup2);
        if (this.mHasListView && !bl3) {
            ((LinearLayout.LayoutParams)viewGroup.getLayoutParams()).topMargin = 0;
        }
        viewGroup = this.mWindow.findViewById(R.id.buttonPanel);
        if (!bl2) {
            viewGroup.setVisibility(8);
            viewGroup = this.mWindow.findViewById(R.id.textSpacerNoButtons);
            if (viewGroup != null) {
                viewGroup.setVisibility(0);
            }
        } else if (this.mHasListView) {
            ((LinearLayout.LayoutParams)viewGroup.getLayoutParams()).topMargin = 0;
        }
        viewGroup2 = (FrameLayout)this.mWindow.findViewById(R.id.customPanel);
        viewGroup = this.mView != null ? this.mView : (this.mViewLayoutResId != 0 ? LayoutInflater.from((Context)this.mContext).inflate(this.mViewLayoutResId, viewGroup2, false) : null);
        int n2 = viewGroup != null ? 1 : 0;
        if (n2 == 0 || !AlertController.canTextInput((View)viewGroup)) {
            this.mWindow.setFlags(131072, 131072);
        }
        if (this.mView != null && this.isTextEditable(this.mView)) {
            this.mWindow.setSoftInputMode(37);
        } else {
            this.mWindow.clearFlags(131072);
        }
        if (n2 != 0) {
            FrameLayout frameLayout = (FrameLayout)this.mWindow.findViewById(R.id.custom);
            frameLayout.addView((View)viewGroup, new ViewGroup.LayoutParams(-1, -1));
            if (this.mViewSpacingSpecified) {
                frameLayout.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if (this.mListView != null) {
                ((LinearLayout.LayoutParams)viewGroup2.getLayoutParams()).weight = 0.0f;
            }
        } else {
            viewGroup2.setVisibility(8);
        }
        if ((viewGroup = this.mListView) != null && this.mAdapter != null) {
            viewGroup.setAdapter(this.mAdapter);
            n2 = this.mCheckedItem;
            if (n2 > -1) {
                viewGroup.setItemChecked(n2, true);
                viewGroup.setSelection(n2);
            }
        }
        tintTypedArray.recycle();
    }

    private static boolean shouldCenterSingleButton(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            return true;
        }
        return false;
    }

    public void adjustPaddingForButtonPanel() {
        ((LinearLayout.LayoutParams)((LinearLayout)this.mWindow.findViewById((int)R.id.buttonPanel)).getLayoutParams()).topMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.mz_alert_dialog_button_margin_top);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void applyMeizuStyle() {
        WindowManager.LayoutParams layoutParams = this.mWindow.getAttributes();
        if (this.mWindow.findViewById(R.id.mask) == null) {
            return;
        }
        LimitedWHLinearLayout limitedWHLinearLayout = (LimitedWHLinearLayout)this.mWindow.findViewById(R.id.parentPanel);
        int n2 = limitedWHLinearLayout.getPaddingTop();
        int n3 = limitedWHLinearLayout.getPaddingBottom();
        n2 = this.mMaxHeight > 0 ? n2 + this.mMaxHeight + n3 : n2 + this.mContext.getResources().getDimensionPixelSize(R.dimen.mz_alert_dialog_max_height) + n3;
        limitedWHLinearLayout.setMaxHeight(n2);
        limitedWHLinearLayout = (FrameLayout.LayoutParams)limitedWHLinearLayout.getLayoutParams();
        if (mShowAtBottom) {
            layoutParams.gravity = 80;
        } else {
            limitedWHLinearLayout.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.mz_alert_dialog_width);
        }
        this.mAlertExt.a();
    }

    public Button getButton(int n2) {
        switch (n2) {
            default: {
                return null;
            }
            case -1: {
                return this.mButtonPositive;
            }
            case -2: {
                return this.mButtonNegative;
            }
            case -3: 
        }
        return this.mButtonNeutral;
    }

    public int getIconAttributeResId(int n2) {
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(n2, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public void installContent() {
        this.mDialog.supportRequestWindowFeature(1);
        int n2 = this.selectContentView();
        this.mDialog.setContentView(n2);
        this.setupView();
    }

    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        if (this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean onKeyUp(int n2, KeyEvent keyEvent) {
        if (this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public void setButton(int n2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        Message message2 = message;
        if (message == null) {
            message2 = message;
            if (onClickListener != null) {
                message2 = this.mHandler.obtainMessage(n2, (Object)onClickListener);
            }
        }
        switch (n2) {
            default: {
                throw new IllegalArgumentException("Button does not exist");
            }
            case -1: {
                this.mButtonPositiveText = charSequence;
                this.mButtonPositiveMessage = message2;
                return;
            }
            case -2: {
                this.mButtonNegativeText = charSequence;
                this.mButtonNegativeMessage = message2;
                return;
            }
            case -3: 
        }
        this.mButtonNeutralText = charSequence;
        this.mButtonNeutralMessage = message2;
    }

    public void setButtonPanelLayoutHint(int n2) {
        this.mButtonPanelLayoutHint = n2;
    }

    public void setCustomTitle(View view) {
        this.mCustomTitleView = view;
    }

    public void setHasListView() {
        this.mHasListView = true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setIcon(int n2) {
        this.mIcon = null;
        this.mIconId = n2;
        if (this.mIconView == null) return;
        if (n2 != 0) {
            this.mIconView.setImageResource(this.mIconId);
            return;
        }
        this.mIconView.setVisibility(8);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setIcon(Drawable drawable2) {
        this.mIcon = drawable2;
        this.mIconId = 0;
        if (this.mIconView == null) return;
        if (drawable2 != null) {
            this.mIconView.setImageDrawable(drawable2);
            return;
        }
        this.mIconView.setVisibility(8);
    }

    public void setMaxHeight(int n2) {
        this.mMaxHeight = n2;
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        if (this.mMessageView != null) {
            this.mMessageView.setText(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mTitleView != null) {
            this.mTitleView.setText(charSequence);
        }
    }

    public void setView(int n2) {
        this.mView = null;
        this.mViewLayoutResId = n2;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view, int n2, int n3, int n4, int n5) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = n2;
        this.mViewSpacingTop = n3;
        this.mViewSpacingRight = n4;
        this.mViewSpacingBottom = n5;
    }

    public static class AlertParams {
        public ColorStateList listItemColor;
        public ColorStateList[] listItemColors;
        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem = -1;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId = 0;
        public int mIconId = 0;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public boolean mIslistItem_centre;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure = true;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified = false;
        public int mViewSpacingTop;

        public AlertParams(Context context) {
            this.mContext = context;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        }

        /*
         * Enabled aggressive block sorting
         */
        public void apply(AlertController alertController) {
            if (this.mCustomTitleView != null) {
                alertController.setCustomTitle(this.mCustomTitleView);
            } else {
                if (this.mTitle != null) {
                    alertController.setTitle(this.mTitle);
                }
                if (this.mIcon != null) {
                    alertController.setIcon(this.mIcon);
                }
                if (this.mIconId != 0) {
                    alertController.setIcon(this.mIconId);
                }
                if (this.mIconAttrId != 0) {
                    alertController.setIcon(alertController.getIconAttributeResId(this.mIconAttrId));
                }
            }
            if (this.mMessage != null) {
                alertController.setMessage(this.mMessage);
            }
            if (this.mPositiveButtonText != null) {
                alertController.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, null);
            }
            if (this.mNegativeButtonText != null) {
                alertController.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, null);
            }
            if (this.mNeutralButtonText != null) {
                alertController.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, null);
            }
            if (this.mItems != null || this.mCursor != null || this.mAdapter != null) {
                this.createListView(alertController);
                alertController.setHasListView();
            }
            if (this.mView != null) {
                if (!this.mViewSpacingSpecified) {
                    alertController.setView(this.mView);
                    return;
                }
                alertController.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                return;
            } else {
                if (this.mViewLayoutResId == 0) return;
                {
                    alertController.setView(this.mViewLayoutResId);
                    return;
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void createListView(AlertController alertController) {
            Object object;
            ListView listView = (ListView)this.mInflater.inflate(alertController.mListLayout, null);
            if (this.mIsMultiChoice) {
                object = this.mCursor == null ? new AlertController$AlertParams$1(this, this.mContext, alertController.mMultiChoiceItemLayout, 16908308, this.mItems, listView) : new Object(this, this.mContext, this.mCursor, false, listView, alertController);
            } else if (this.mIslistItem_centre) {
                object = this.mCursor == null ? (this.mAdapter != null ? this.mAdapter : (this.listItemColors != null ? new ListItemCenterAdapter(this.mContext, alertController.mListItem_ShowCentreLayout, 16908308, this.mItems, this.listItemColors) : (this.listItemColor != null ? new ListItemCenterAdapter(this.mContext, alertController.mListItem_ShowCentreLayout, 16908308, this.mItems, this.listItemColor) : new ListItemCenterAdapter(this.mContext, alertController.mListItem_ShowCentreLayout, 16908308, this.mItems)))) : new SimpleCursorAdapter(this.mContext, alertController.mListItem_ShowCentreLayout, this.mCursor, new String[]{this.mLabelColumn}, new int[]{16908308});
            } else {
                int n2 = this.mIsSingleChoice ? alertController.mSingleChoiceItemLayout : alertController.mListItemLayout;
                object = this.mCursor == null ? (this.mAdapter != null ? this.mAdapter : new CheckedItemAdapter(this.mContext, n2, 16908308, this.mItems)) : new SimpleCursorAdapter(this.mContext, n2, this.mCursor, new String[]{this.mLabelColumn}, new int[]{16908308});
            }
            if (this.mOnPrepareListViewListener != null) {
                this.mOnPrepareListViewListener.onPrepareListView(listView);
            }
            alertController.mAdapter = (ListAdapter)object;
            alertController.mCheckedItem = this.mCheckedItem;
            if (this.mOnClickListener != null) {
                listView.setOnItemClickListener((AdapterView.OnItemClickListener)new AlertController$AlertParams$3(this, alertController));
            } else if (this.mOnCheckboxClickListener != null) {
                listView.setOnItemClickListener((AdapterView.OnItemClickListener)new AlertController$AlertParams$4(this, listView, alertController));
            }
            if (this.mOnItemSelectedListener != null) {
                listView.setOnItemSelectedListener(this.mOnItemSelectedListener);
            }
            if (this.mIsSingleChoice) {
                listView.setChoiceMode(1);
            } else if (this.mIsMultiChoice) {
                listView.setChoiceMode(2);
            }
            alertController.mListView = listView;
        }

        public static interface OnPrepareListViewListener {
            public void onPrepareListView(ListView var1);
        }

    }

    static final class ButtonHandler
    extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.mDialog = new WeakReference<DialogInterface>(dialogInterface);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                default: {
                    return;
                }
                case -3: 
                case -2: 
                case -1: {
                    ((DialogInterface.OnClickListener)message.obj).onClick(this.mDialog.get(), message.what);
                    return;
                }
                case 1: 
            }
            ((DialogInterface)message.obj).dismiss();
        }
    }

    static class CheckedItemAdapter
    extends ArrayAdapter<CharSequence> {
        public CheckedItemAdapter(Context context, int n2, int n3, CharSequence[] arrcharSequence) {
            super(context, n2, n3, (Object[])arrcharSequence);
        }

        public long getItemId(int n2) {
            return n2;
        }

        public boolean hasStableIds() {
            return true;
        }
    }

    static class ListItemCenterAdapter
    extends ArrayAdapter {
        Context context;
        private int resourceId;
        CharSequence[] strings;
        ColorStateList textColor;
        ColorStateList[] textColors;
        private int textViewResourceId;

        public ListItemCenterAdapter(Context context, int n2, int n3, CharSequence[] arrcharSequence) {
            super(context, n2, n3, (Object[])arrcharSequence);
            this.context = context;
            this.resourceId = n2;
            this.textViewResourceId = n3;
            this.strings = arrcharSequence;
        }

        public ListItemCenterAdapter(Context context, int n2, int n3, CharSequence[] arrcharSequence, ColorStateList colorStateList) {
            super(context, n2, n3, (Object[])arrcharSequence);
            this.context = context;
            this.resourceId = n2;
            this.textViewResourceId = n3;
            this.strings = arrcharSequence;
            this.textColor = colorStateList;
        }

        public ListItemCenterAdapter(Context context, int n2, int n3, CharSequence[] arrcharSequence, ColorStateList[] arrcolorStateList) {
            super(context, n2, n3, (Object[])arrcharSequence);
            this.context = context;
            this.resourceId = n2;
            this.textViewResourceId = n3;
            this.strings = arrcharSequence;
            this.textColors = arrcolorStateList;
        }

        public long getItemId(int n2) {
            return n2;
        }

        /*
         * Enabled aggressive block sorting
         */
        public View getView(int n2, View object, ViewGroup object2) {
            Object object3;
            if (object == null) {
                object3 = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.resourceId, null);
                object = new ViewHolder();
                object.item = (TextView)object3.findViewById(this.textViewResourceId);
                object3.setTag(object);
                object2 = object;
            } else {
                object2 = (ViewHolder)object.getTag();
                object3 = object;
            }
            object2.item.setText(this.strings[n2]);
            if (this.textColors != null) {
                object2.item.setTextColor(this.textColors[n2]);
                return object3;
            } else {
                if (this.textColor == null) return object3;
                {
                    object2.item.setTextColor(this.textColor);
                    return object3;
                }
            }
        }

        public boolean hasStableIds() {
            return true;
        }
    }

    public static class ViewHolder {
        public TextView item;
    }

}

