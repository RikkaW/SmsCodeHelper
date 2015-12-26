package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.LimitedWHLinearLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import aqr;
import java.lang.ref.WeakReference;

class AlertController
{
  public static boolean mShowAtBottom;
  private ListAdapter mAdapter;
  private int mAlertDialogLayout;
  private aqr mAlertExt;
  private final View.OnClickListener mButtonHandler = new AlertController.1(this);
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
  
  public AlertController(Context paramContext, AppCompatDialog paramAppCompatDialog, Window paramWindow)
  {
    mContext = paramContext;
    mDialog = paramAppCompatDialog;
    mWindow = paramWindow;
    mHandler = new ButtonHandler(paramAppCompatDialog);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(null, R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
    mAlertDialogLayout = localTypedArray.getResourceId(R.styleable.AlertDialog_android_layout, 0);
    mButtonPanelSideLayout = localTypedArray.getResourceId(R.styleable.AlertDialog_buttonPanelSideLayout, 0);
    mListLayout = localTypedArray.getResourceId(R.styleable.AlertDialog_listLayout, 0);
    mMultiChoiceItemLayout = localTypedArray.getResourceId(R.styleable.AlertDialog_multiChoiceItemLayout, 0);
    mSingleChoiceItemLayout = localTypedArray.getResourceId(R.styleable.AlertDialog_singleChoiceItemLayout, 0);
    mListItemLayout = localTypedArray.getResourceId(R.styleable.AlertDialog_listItemLayout, 0);
    mListItem_ShowCentreLayout = localTypedArray.getResourceId(R.styleable.AlertDialog_listItem_showCentreLayout, 0);
    mShowAtBottom = localTypedArray.getBoolean(R.styleable.AlertDialog_mzShowAtBottom, false);
    mMzButtonBarOrientation = localTypedArray.getInt(R.styleable.AlertDialog_mzButtonBarOrientation, 0);
    localTypedArray.recycle();
    mAlertExt = new aqr(paramContext, paramAppCompatDialog, paramWindow);
  }
  
  static boolean canTextInput(View paramView)
  {
    if (paramView.onCheckIsTextEditor()) {
      return true;
    }
    if (!(paramView instanceof ViewGroup)) {
      return false;
    }
    paramView = (ViewGroup)paramView;
    int i = paramView.getChildCount();
    while (i > 0)
    {
      int j = i - 1;
      i = j;
      if (canTextInput(paramView.getChildAt(j))) {
        return true;
      }
    }
    return false;
  }
  
  private void centerButton(Button paramButton)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramButton.getLayoutParams();
    gravity = 1;
    weight = 0.5F;
    paramButton.setLayoutParams(localLayoutParams);
  }
  
  private boolean isTextEditable(View paramView)
  {
    if (paramView.getVisibility() != 0) {}
    int j;
    do
    {
      while (i <= 0)
      {
        do
        {
          return false;
          if ((paramView.onCheckIsTextEditor()) && (paramView.isEnabled())) {
            return true;
          }
        } while (!(paramView instanceof ViewGroup));
        paramView = (ViewGroup)paramView;
        i = paramView.getChildCount();
      }
      j = i - 1;
      int i = j;
    } while (!isTextEditable(paramView.getChildAt(j)));
    return true;
  }
  
  private int selectContentView()
  {
    if (mButtonPanelSideLayout == 0) {
      return mAlertDialogLayout;
    }
    if (mButtonPanelLayoutHint == 1) {
      return mButtonPanelSideLayout;
    }
    return mAlertDialogLayout;
  }
  
  private boolean setupButtons()
  {
    Object localObject = (LinearLayout)mWindow.findViewById(R.id.buttonBarPanel);
    mButtonPositive = ((Button)mWindow.findViewById(16908313));
    mButtonPositive.setOnClickListener(mButtonHandler);
    int i;
    if (TextUtils.isEmpty(mButtonPositiveText))
    {
      mButtonPositive.setVisibility(8);
      i = 0;
      mButtonNegative = ((Button)mWindow.findViewById(16908314));
      mButtonNegative.setOnClickListener(mButtonHandler);
      if (!TextUtils.isEmpty(mButtonNegativeText)) {
        break label294;
      }
      mButtonNegative.setVisibility(8);
      label110:
      mButtonNeutral = ((Button)mWindow.findViewById(16908315));
      mButtonNeutral.setOnClickListener(mButtonHandler);
      if (!TextUtils.isEmpty(mButtonNeutralText)) {
        break label320;
      }
      mButtonNeutral.setVisibility(8);
      label157:
      if (shouldCenterSingleButton(mContext))
      {
        if (i != 1) {
          break label346;
        }
        centerButton(mButtonPositive);
      }
    }
    for (;;)
    {
      if (mMzButtonBarOrientation > 0)
      {
        ((LinearLayout)localObject).setOrientation(1);
        localObject = (LinearLayout.LayoutParams)mButtonNegative.getLayoutParams();
        width = -1;
        mButtonNegative.setLayoutParams((ViewGroup.LayoutParams)localObject);
        localObject = (LinearLayout.LayoutParams)mButtonPositive.getLayoutParams();
        width = -1;
        mButtonPositive.setLayoutParams((ViewGroup.LayoutParams)localObject);
        localObject = (LinearLayout.LayoutParams)mButtonNeutral.getLayoutParams();
        width = -1;
        mButtonNeutral.setLayoutParams((ViewGroup.LayoutParams)localObject);
      }
      if (i == 0) {
        break label378;
      }
      return true;
      mButtonPositive.setText(mButtonPositiveText);
      mButtonPositive.setVisibility(0);
      i = 1;
      break;
      label294:
      mButtonNegative.setText(mButtonNegativeText);
      mButtonNegative.setVisibility(0);
      i |= 0x2;
      break label110;
      label320:
      mButtonNeutral.setText(mButtonNeutralText);
      mButtonNeutral.setVisibility(0);
      i |= 0x4;
      break label157;
      label346:
      if (i == 2) {
        centerButton(mButtonNegative);
      } else if (i == 4) {
        centerButton(mButtonNeutral);
      }
    }
    label378:
    return false;
  }
  
  private void setupContent(ViewGroup paramViewGroup)
  {
    mScrollView = ((ScrollView)mWindow.findViewById(R.id.scrollView));
    mScrollView.setFocusable(false);
    mMessageView = ((TextView)mWindow.findViewById(16908299));
    if (mMessageView == null) {
      return;
    }
    if (mMessage != null)
    {
      mMessageView.setText(mMessage);
      mAlertExt.a(mMessage);
      return;
    }
    mMessageView.setVisibility(8);
    mScrollView.removeView(mMessageView);
    if (mListView != null)
    {
      paramViewGroup = (ViewGroup)mScrollView.getParent();
      int i = paramViewGroup.indexOfChild(mScrollView);
      paramViewGroup.removeViewAt(i);
      paramViewGroup.addView(mListView, i, new ViewGroup.LayoutParams(-1, -1));
      return;
    }
    paramViewGroup.setVisibility(8);
    adjustPaddingForButtonPanel();
  }
  
  private boolean setupTitle(ViewGroup paramViewGroup)
  {
    if (mCustomTitleView != null)
    {
      ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -2);
      paramViewGroup.addView(mCustomTitleView, 0, localLayoutParams);
      mWindow.findViewById(R.id.title_template).setVisibility(8);
      return true;
    }
    mIconView = ((ImageView)mWindow.findViewById(16908294));
    int i;
    if (!TextUtils.isEmpty(mTitle)) {
      i = 1;
    }
    while (i != 0)
    {
      mTitleView = ((TextView)mWindow.findViewById(R.id.alertTitle));
      mTitleView.setText(mTitle);
      if (mIconId != 0)
      {
        mIconView.setImageResource(mIconId);
        return true;
        i = 0;
      }
      else
      {
        if (mIcon != null)
        {
          mIconView.setImageDrawable(mIcon);
          return true;
        }
        mTitleView.setPadding(mIconView.getPaddingLeft(), mIconView.getPaddingTop(), mIconView.getPaddingRight(), mIconView.getPaddingBottom());
        mIconView.setVisibility(8);
        return true;
      }
    }
    mWindow.findViewById(R.id.title_template).setVisibility(8);
    mIconView.setVisibility(8);
    paramViewGroup.setVisibility(8);
    return false;
  }
  
  private void setupView()
  {
    mWindow.setDimAmount(0.5F);
    Object localObject1 = (ViewGroup)mWindow.findViewById(R.id.contentPanel);
    setupContent((ViewGroup)localObject1);
    boolean bool1 = setupButtons();
    Object localObject2 = (ViewGroup)mWindow.findViewById(R.id.topPanel);
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(mContext, null, R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
    boolean bool2 = setupTitle((ViewGroup)localObject2);
    if ((mHasListView) && (!bool2)) {
      getLayoutParamstopMargin = 0;
    }
    localObject1 = mWindow.findViewById(R.id.buttonPanel);
    label172:
    int i;
    if (!bool1)
    {
      ((View)localObject1).setVisibility(8);
      localObject1 = mWindow.findViewById(R.id.textSpacerNoButtons);
      if (localObject1 != null) {
        ((View)localObject1).setVisibility(0);
      }
      localObject2 = (FrameLayout)mWindow.findViewById(R.id.customPanel);
      if (mView == null) {
        break label391;
      }
      localObject1 = mView;
      if (localObject1 == null) {
        break label426;
      }
      i = 1;
      label179:
      if ((i == 0) || (!canTextInput((View)localObject1))) {
        mWindow.setFlags(131072, 131072);
      }
      if ((mView == null) || (!isTextEditable(mView))) {
        break label431;
      }
      mWindow.setSoftInputMode(37);
      label231:
      if (i == 0) {
        break label444;
      }
      FrameLayout localFrameLayout = (FrameLayout)mWindow.findViewById(R.id.custom);
      localFrameLayout.addView((View)localObject1, new ViewGroup.LayoutParams(-1, -1));
      if (mViewSpacingSpecified) {
        localFrameLayout.setPadding(mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
      }
      if (mListView != null) {
        getLayoutParamsweight = 0.0F;
      }
    }
    for (;;)
    {
      localObject1 = mListView;
      if ((localObject1 != null) && (mAdapter != null))
      {
        ((ListView)localObject1).setAdapter(mAdapter);
        i = mCheckedItem;
        if (i > -1)
        {
          ((ListView)localObject1).setItemChecked(i, true);
          ((ListView)localObject1).setSelection(i);
        }
      }
      localTintTypedArray.recycle();
      return;
      if (!mHasListView) {
        break;
      }
      getLayoutParamstopMargin = 0;
      break;
      label391:
      if (mViewLayoutResId != 0)
      {
        localObject1 = LayoutInflater.from(mContext).inflate(mViewLayoutResId, (ViewGroup)localObject2, false);
        break label172;
      }
      localObject1 = null;
      break label172;
      label426:
      i = 0;
      break label179;
      label431:
      mWindow.clearFlags(131072);
      break label231;
      label444:
      ((FrameLayout)localObject2).setVisibility(8);
    }
  }
  
  private static boolean shouldCenterSingleButton(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, localTypedValue, true);
    return data != 0;
  }
  
  public void adjustPaddingForButtonPanel()
  {
    mWindow.findViewById(R.id.buttonPanel)).getLayoutParams()).topMargin = mContext.getResources().getDimensionPixelSize(R.dimen.mz_alert_dialog_button_margin_top);
  }
  
  public void applyMeizuStyle()
  {
    WindowManager.LayoutParams localLayoutParams = mWindow.getAttributes();
    if (mWindow.findViewById(R.id.mask) == null) {
      return;
    }
    Object localObject = (LimitedWHLinearLayout)mWindow.findViewById(R.id.parentPanel);
    int i = ((LimitedWHLinearLayout)localObject).getPaddingTop();
    int j = ((LimitedWHLinearLayout)localObject).getPaddingBottom();
    if (mMaxHeight > 0)
    {
      i = i + mMaxHeight + j;
      ((LimitedWHLinearLayout)localObject).setMaxHeight(i);
      localObject = (FrameLayout.LayoutParams)((LimitedWHLinearLayout)localObject).getLayoutParams();
      if (!mShowAtBottom) {
        break label122;
      }
      gravity = 80;
    }
    for (;;)
    {
      mAlertExt.a();
      return;
      i = i + mContext.getResources().getDimensionPixelSize(R.dimen.mz_alert_dialog_max_height) + j;
      break;
      label122:
      width = mContext.getResources().getDimensionPixelSize(R.dimen.mz_alert_dialog_width);
    }
  }
  
  public Button getButton(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case -1: 
      return mButtonPositive;
    case -2: 
      return mButtonNegative;
    }
    return mButtonNeutral;
  }
  
  public int getIconAttributeResId(int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    mContext.getTheme().resolveAttribute(paramInt, localTypedValue, true);
    return resourceId;
  }
  
  public ListView getListView()
  {
    return mListView;
  }
  
  public void installContent()
  {
    mDialog.supportRequestWindowFeature(1);
    int i = selectContentView();
    mDialog.setContentView(i);
    setupView();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return (mScrollView != null) && (mScrollView.executeKeyEvent(paramKeyEvent));
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return (mScrollView != null) && (mScrollView.executeKeyEvent(paramKeyEvent));
  }
  
  public void setButton(int paramInt, CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener, Message paramMessage)
  {
    Message localMessage = paramMessage;
    if (paramMessage == null)
    {
      localMessage = paramMessage;
      if (paramOnClickListener != null) {
        localMessage = mHandler.obtainMessage(paramInt, paramOnClickListener);
      }
    }
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Button does not exist");
    case -1: 
      mButtonPositiveText = paramCharSequence;
      mButtonPositiveMessage = localMessage;
      return;
    case -2: 
      mButtonNegativeText = paramCharSequence;
      mButtonNegativeMessage = localMessage;
      return;
    }
    mButtonNeutralText = paramCharSequence;
    mButtonNeutralMessage = localMessage;
  }
  
  public void setButtonPanelLayoutHint(int paramInt)
  {
    mButtonPanelLayoutHint = paramInt;
  }
  
  public void setCustomTitle(View paramView)
  {
    mCustomTitleView = paramView;
  }
  
  public void setHasListView()
  {
    mHasListView = true;
  }
  
  public void setIcon(int paramInt)
  {
    mIcon = null;
    mIconId = paramInt;
    if (mIconView != null)
    {
      if (paramInt != 0) {
        mIconView.setImageResource(mIconId);
      }
    }
    else {
      return;
    }
    mIconView.setVisibility(8);
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    mIcon = paramDrawable;
    mIconId = 0;
    if (mIconView != null)
    {
      if (paramDrawable != null) {
        mIconView.setImageDrawable(paramDrawable);
      }
    }
    else {
      return;
    }
    mIconView.setVisibility(8);
  }
  
  public void setMaxHeight(int paramInt)
  {
    mMaxHeight = paramInt;
  }
  
  public void setMessage(CharSequence paramCharSequence)
  {
    mMessage = paramCharSequence;
    if (mMessageView != null) {
      mMessageView.setText(paramCharSequence);
    }
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    if (mTitleView != null) {
      mTitleView.setText(paramCharSequence);
    }
  }
  
  public void setView(int paramInt)
  {
    mView = null;
    mViewLayoutResId = paramInt;
    mViewSpacingSpecified = false;
  }
  
  public void setView(View paramView)
  {
    mView = paramView;
    mViewLayoutResId = 0;
    mViewSpacingSpecified = false;
  }
  
  public void setView(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mView = paramView;
    mViewLayoutResId = 0;
    mViewSpacingSpecified = true;
    mViewSpacingLeft = paramInt1;
    mViewSpacingTop = paramInt2;
    mViewSpacingRight = paramInt3;
    mViewSpacingBottom = paramInt4;
  }
  
  public static class AlertParams
  {
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
    
    public AlertParams(Context paramContext)
    {
      mContext = paramContext;
      mCancelable = true;
      mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    }
    
    public void apply(AlertController paramAlertController)
    {
      if (mCustomTitleView != null)
      {
        paramAlertController.setCustomTitle(mCustomTitleView);
        if (mMessage != null) {
          paramAlertController.setMessage(mMessage);
        }
        if (mPositiveButtonText != null) {
          paramAlertController.setButton(-1, mPositiveButtonText, mPositiveButtonListener, null);
        }
        if (mNegativeButtonText != null) {
          paramAlertController.setButton(-2, mNegativeButtonText, mNegativeButtonListener, null);
        }
        if (mNeutralButtonText != null) {
          paramAlertController.setButton(-3, mNeutralButtonText, mNeutralButtonListener, null);
        }
        if ((mItems != null) || (mCursor != null) || (mAdapter != null))
        {
          createListView(paramAlertController);
          paramAlertController.setHasListView();
        }
        if (mView == null) {
          break label240;
        }
        if (!mViewSpacingSpecified) {
          break label231;
        }
        paramAlertController.setView(mView, mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
      }
      label231:
      label240:
      while (mViewLayoutResId == 0)
      {
        return;
        if (mTitle != null) {
          paramAlertController.setTitle(mTitle);
        }
        if (mIcon != null) {
          paramAlertController.setIcon(mIcon);
        }
        if (mIconId != 0) {
          paramAlertController.setIcon(mIconId);
        }
        if (mIconAttrId == 0) {
          break;
        }
        paramAlertController.setIcon(paramAlertController.getIconAttributeResId(mIconAttrId));
        break;
        paramAlertController.setView(mView);
        return;
      }
      paramAlertController.setView(mViewLayoutResId);
    }
    
    public void createListView(AlertController paramAlertController)
    {
      ListView localListView = (ListView)mInflater.inflate(mListLayout, null);
      Object localObject;
      if (mIsMultiChoice) {
        if (mCursor == null)
        {
          localObject = new AlertController.AlertParams.1(this, mContext, mMultiChoiceItemLayout, 16908308, mItems, localListView);
          if (mOnPrepareListViewListener != null) {
            mOnPrepareListViewListener.onPrepareListView(localListView);
          }
          AlertController.access$1302(paramAlertController, (ListAdapter)localObject);
          AlertController.access$1402(paramAlertController, mCheckedItem);
          if (mOnClickListener == null) {
            break label442;
          }
          localListView.setOnItemClickListener(new AlertController.AlertParams.3(this, paramAlertController));
          label110:
          if (mOnItemSelectedListener != null) {
            localListView.setOnItemSelectedListener(mOnItemSelectedListener);
          }
          if (!mIsSingleChoice) {
            break label468;
          }
          localListView.setChoiceMode(1);
        }
      }
      for (;;)
      {
        AlertController.access$1502(paramAlertController, localListView);
        return;
        localObject = new AlertController.AlertParams.2(this, mContext, mCursor, false, localListView, paramAlertController);
        break;
        if (mIslistItem_centre)
        {
          if (mCursor == null)
          {
            if (mAdapter != null)
            {
              localObject = mAdapter;
              break;
            }
            if (listItemColors != null)
            {
              localObject = new AlertController.ListItemCenterAdapter(mContext, mListItem_ShowCentreLayout, 16908308, mItems, listItemColors);
              break;
            }
            if (listItemColor != null)
            {
              localObject = new AlertController.ListItemCenterAdapter(mContext, mListItem_ShowCentreLayout, 16908308, mItems, listItemColor);
              break;
            }
            localObject = new AlertController.ListItemCenterAdapter(mContext, mListItem_ShowCentreLayout, 16908308, mItems);
            break;
          }
          localObject = new SimpleCursorAdapter(mContext, mListItem_ShowCentreLayout, mCursor, new String[] { mLabelColumn }, new int[] { 16908308 });
          break;
        }
        int i;
        if (mIsSingleChoice) {
          i = mSingleChoiceItemLayout;
        }
        for (;;)
        {
          if (mCursor == null)
          {
            if (mAdapter != null)
            {
              localObject = mAdapter;
              break;
              i = mListItemLayout;
              continue;
            }
            localObject = new AlertController.CheckedItemAdapter(mContext, i, 16908308, mItems);
            break;
          }
        }
        localObject = new SimpleCursorAdapter(mContext, i, mCursor, new String[] { mLabelColumn }, new int[] { 16908308 });
        break;
        label442:
        if (mOnCheckboxClickListener == null) {
          break label110;
        }
        localListView.setOnItemClickListener(new AlertController.AlertParams.4(this, localListView, paramAlertController));
        break label110;
        label468:
        if (mIsMultiChoice) {
          localListView.setChoiceMode(2);
        }
      }
    }
    
    public static abstract interface OnPrepareListViewListener
    {
      public abstract void onPrepareListView(ListView paramListView);
    }
  }
  
  static final class ButtonHandler
    extends Handler
  {
    private static final int MSG_DISMISS_DIALOG = 1;
    private WeakReference<DialogInterface> mDialog;
    
    public ButtonHandler(DialogInterface paramDialogInterface)
    {
      mDialog = new WeakReference(paramDialogInterface);
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      case 0: 
      default: 
        return;
      case -3: 
      case -2: 
      case -1: 
        ((DialogInterface.OnClickListener)obj).onClick((DialogInterface)mDialog.get(), what);
        return;
      }
      ((DialogInterface)obj).dismiss();
    }
  }
  
  static class CheckedItemAdapter
    extends ArrayAdapter<CharSequence>
  {
    public CheckedItemAdapter(Context paramContext, int paramInt1, int paramInt2, CharSequence[] paramArrayOfCharSequence)
    {
      super(paramInt1, paramInt2, paramArrayOfCharSequence);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public boolean hasStableIds()
    {
      return true;
    }
  }
  
  static class ListItemCenterAdapter
    extends ArrayAdapter
  {
    Context context;
    private int resourceId;
    CharSequence[] strings;
    ColorStateList textColor;
    ColorStateList[] textColors;
    private int textViewResourceId;
    
    public ListItemCenterAdapter(Context paramContext, int paramInt1, int paramInt2, CharSequence[] paramArrayOfCharSequence)
    {
      super(paramInt1, paramInt2, paramArrayOfCharSequence);
      context = paramContext;
      resourceId = paramInt1;
      textViewResourceId = paramInt2;
      strings = paramArrayOfCharSequence;
    }
    
    public ListItemCenterAdapter(Context paramContext, int paramInt1, int paramInt2, CharSequence[] paramArrayOfCharSequence, ColorStateList paramColorStateList)
    {
      super(paramInt1, paramInt2, paramArrayOfCharSequence);
      context = paramContext;
      resourceId = paramInt1;
      textViewResourceId = paramInt2;
      strings = paramArrayOfCharSequence;
      textColor = paramColorStateList;
    }
    
    public ListItemCenterAdapter(Context paramContext, int paramInt1, int paramInt2, CharSequence[] paramArrayOfCharSequence, ColorStateList[] paramArrayOfColorStateList)
    {
      super(paramInt1, paramInt2, paramArrayOfCharSequence);
      context = paramContext;
      resourceId = paramInt1;
      textViewResourceId = paramInt2;
      strings = paramArrayOfCharSequence;
      textColors = paramArrayOfColorStateList;
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView;
      if (paramView == null)
      {
        localView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(resourceId, null);
        paramView = new AlertController.ViewHolder();
        item = ((TextView)localView.findViewById(textViewResourceId));
        localView.setTag(paramView);
        paramViewGroup = paramView;
        item.setText(strings[paramInt]);
        if (textColors == null) {
          break label108;
        }
        item.setTextColor(textColors[paramInt]);
      }
      label108:
      while (textColor == null)
      {
        return localView;
        paramViewGroup = (AlertController.ViewHolder)paramView.getTag();
        localView = paramView;
        break;
      }
      item.setTextColor(textColor);
      return localView;
    }
    
    public boolean hasStableIds()
    {
      return true;
    }
  }
  
  public static class ViewHolder
  {
    public TextView item;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AlertController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */