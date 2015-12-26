package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;

public class AlertDialog$Builder
{
  private final AlertController.AlertParams P;
  private int mTheme;
  
  public AlertDialog$Builder(Context paramContext)
  {
    this(paramContext, AlertDialog.resolveDialogTheme(paramContext, 0));
  }
  
  public AlertDialog$Builder(Context paramContext, int paramInt)
  {
    P = new AlertController.AlertParams(new ContextThemeWrapper(paramContext, AlertDialog.resolveDialogTheme(paramContext, paramInt)));
    mTheme = paramInt;
  }
  
  public AlertDialog create()
  {
    AlertDialog localAlertDialog = new AlertDialog(P.mContext, mTheme, false);
    P.apply(AlertDialog.access$000(localAlertDialog));
    localAlertDialog.setCancelable(P.mCancelable);
    if (P.mCancelable) {
      localAlertDialog.setCanceledOnTouchOutside(true);
    }
    localAlertDialog.setOnCancelListener(P.mOnCancelListener);
    localAlertDialog.setOnDismissListener(P.mOnDismissListener);
    if (P.mOnKeyListener != null) {
      localAlertDialog.setOnKeyListener(P.mOnKeyListener);
    }
    return localAlertDialog;
  }
  
  public Context getContext()
  {
    return P.mContext;
  }
  
  public Builder setAdapter(ListAdapter paramListAdapter, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mAdapter = paramListAdapter;
    P.mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public Builder setCancelable(boolean paramBoolean)
  {
    P.mCancelable = paramBoolean;
    return this;
  }
  
  public Builder setCursor(Cursor paramCursor, DialogInterface.OnClickListener paramOnClickListener, String paramString)
  {
    P.mCursor = paramCursor;
    P.mLabelColumn = paramString;
    P.mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public Builder setCustomTitle(View paramView)
  {
    P.mCustomTitleView = paramView;
    return this;
  }
  
  public Builder setIcon(int paramInt)
  {
    P.mIconId = paramInt;
    return this;
  }
  
  public Builder setIcon(Drawable paramDrawable)
  {
    P.mIcon = paramDrawable;
    return this;
  }
  
  public Builder setIconAttribute(int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    P.mContext.getTheme().resolveAttribute(paramInt, localTypedValue, true);
    P.mIconId = resourceId;
    return this;
  }
  
  public Builder setInverseBackgroundForced(boolean paramBoolean)
  {
    P.mForceInverseBackground = paramBoolean;
    return this;
  }
  
  public Builder setItems(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mItems = P.mContext.getResources().getTextArray(paramInt);
    P.mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public Builder setItems(int paramInt, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean)
  {
    P.mItems = P.mContext.getResources().getTextArray(paramInt);
    P.mOnClickListener = paramOnClickListener;
    P.mIslistItem_centre = paramBoolean;
    return this;
  }
  
  public Builder setItems(int paramInt, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean, ColorStateList paramColorStateList)
  {
    P.mItems = P.mContext.getResources().getTextArray(paramInt);
    P.mOnClickListener = paramOnClickListener;
    P.mIslistItem_centre = paramBoolean;
    P.listItemColor = paramColorStateList;
    return this;
  }
  
  public Builder setItems(int paramInt, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean, ColorStateList[] paramArrayOfColorStateList)
  {
    P.mItems = P.mContext.getResources().getTextArray(paramInt);
    P.mOnClickListener = paramOnClickListener;
    P.mIslistItem_centre = paramBoolean;
    P.listItemColors = paramArrayOfColorStateList;
    return this;
  }
  
  public Builder setItems(CharSequence[] paramArrayOfCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mItems = paramArrayOfCharSequence;
    P.mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public Builder setItems(CharSequence[] paramArrayOfCharSequence, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean)
  {
    P.mItems = paramArrayOfCharSequence;
    P.mOnClickListener = paramOnClickListener;
    P.mIslistItem_centre = paramBoolean;
    return this;
  }
  
  public Builder setItems(CharSequence[] paramArrayOfCharSequence, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean, ColorStateList paramColorStateList)
  {
    P.mItems = paramArrayOfCharSequence;
    P.mOnClickListener = paramOnClickListener;
    P.mIslistItem_centre = paramBoolean;
    P.listItemColor = paramColorStateList;
    return this;
  }
  
  public Builder setItems(CharSequence[] paramArrayOfCharSequence, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean, ColorStateList[] paramArrayOfColorStateList)
  {
    P.mItems = paramArrayOfCharSequence;
    P.mOnClickListener = paramOnClickListener;
    P.mIslistItem_centre = paramBoolean;
    P.listItemColors = paramArrayOfColorStateList;
    return this;
  }
  
  public Builder setMessage(int paramInt)
  {
    P.mMessage = P.mContext.getText(paramInt);
    return this;
  }
  
  public Builder setMessage(CharSequence paramCharSequence)
  {
    P.mMessage = paramCharSequence;
    return this;
  }
  
  public Builder setMultiChoiceItems(int paramInt, boolean[] paramArrayOfBoolean, DialogInterface.OnMultiChoiceClickListener paramOnMultiChoiceClickListener)
  {
    P.mItems = P.mContext.getResources().getTextArray(paramInt);
    P.mOnCheckboxClickListener = paramOnMultiChoiceClickListener;
    P.mCheckedItems = paramArrayOfBoolean;
    P.mIsMultiChoice = true;
    return this;
  }
  
  public Builder setMultiChoiceItems(Cursor paramCursor, String paramString1, String paramString2, DialogInterface.OnMultiChoiceClickListener paramOnMultiChoiceClickListener)
  {
    P.mCursor = paramCursor;
    P.mOnCheckboxClickListener = paramOnMultiChoiceClickListener;
    P.mIsCheckedColumn = paramString1;
    P.mLabelColumn = paramString2;
    P.mIsMultiChoice = true;
    return this;
  }
  
  public Builder setMultiChoiceItems(CharSequence[] paramArrayOfCharSequence, boolean[] paramArrayOfBoolean, DialogInterface.OnMultiChoiceClickListener paramOnMultiChoiceClickListener)
  {
    P.mItems = paramArrayOfCharSequence;
    P.mOnCheckboxClickListener = paramOnMultiChoiceClickListener;
    P.mCheckedItems = paramArrayOfBoolean;
    P.mIsMultiChoice = true;
    return this;
  }
  
  public Builder setNegativeButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mNegativeButtonText = P.mContext.getText(paramInt);
    P.mNegativeButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setNegativeButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mNegativeButtonText = paramCharSequence;
    P.mNegativeButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setNeutralButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mNeutralButtonText = P.mContext.getText(paramInt);
    P.mNeutralButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setNeutralButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mNeutralButtonText = paramCharSequence;
    P.mNeutralButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setOnCancelListener(DialogInterface.OnCancelListener paramOnCancelListener)
  {
    P.mOnCancelListener = paramOnCancelListener;
    return this;
  }
  
  public Builder setOnDismissListener(DialogInterface.OnDismissListener paramOnDismissListener)
  {
    P.mOnDismissListener = paramOnDismissListener;
    return this;
  }
  
  public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener)
  {
    P.mOnItemSelectedListener = paramOnItemSelectedListener;
    return this;
  }
  
  public Builder setOnKeyListener(DialogInterface.OnKeyListener paramOnKeyListener)
  {
    P.mOnKeyListener = paramOnKeyListener;
    return this;
  }
  
  public Builder setPositiveButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mPositiveButtonText = P.mContext.getText(paramInt);
    P.mPositiveButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setPositiveButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mPositiveButtonText = paramCharSequence;
    P.mPositiveButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setRecycleOnMeasureEnabled(boolean paramBoolean)
  {
    P.mRecycleOnMeasure = paramBoolean;
    return this;
  }
  
  public Builder setSingleChoiceItems(int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mItems = P.mContext.getResources().getTextArray(paramInt1);
    P.mOnClickListener = paramOnClickListener;
    P.mCheckedItem = paramInt2;
    P.mIsSingleChoice = true;
    return this;
  }
  
  public Builder setSingleChoiceItems(Cursor paramCursor, int paramInt, String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mCursor = paramCursor;
    P.mOnClickListener = paramOnClickListener;
    P.mCheckedItem = paramInt;
    P.mLabelColumn = paramString;
    P.mIsSingleChoice = true;
    return this;
  }
  
  public Builder setSingleChoiceItems(ListAdapter paramListAdapter, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mAdapter = paramListAdapter;
    P.mOnClickListener = paramOnClickListener;
    P.mCheckedItem = paramInt;
    P.mIsSingleChoice = true;
    return this;
  }
  
  public Builder setSingleChoiceItems(CharSequence[] paramArrayOfCharSequence, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    P.mItems = paramArrayOfCharSequence;
    P.mOnClickListener = paramOnClickListener;
    P.mCheckedItem = paramInt;
    P.mIsSingleChoice = true;
    return this;
  }
  
  public Builder setTitle(int paramInt)
  {
    P.mTitle = P.mContext.getText(paramInt);
    return this;
  }
  
  public Builder setTitle(CharSequence paramCharSequence)
  {
    P.mTitle = paramCharSequence;
    return this;
  }
  
  public Builder setView(int paramInt)
  {
    P.mView = null;
    P.mViewLayoutResId = paramInt;
    P.mViewSpacingSpecified = false;
    return this;
  }
  
  public Builder setView(View paramView)
  {
    P.mView = paramView;
    P.mViewLayoutResId = 0;
    P.mViewSpacingSpecified = false;
    return this;
  }
  
  public Builder setView(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    P.mView = paramView;
    P.mViewLayoutResId = 0;
    P.mViewSpacingSpecified = true;
    P.mViewSpacingLeft = paramInt1;
    P.mViewSpacingTop = paramInt2;
    P.mViewSpacingRight = paramInt3;
    P.mViewSpacingBottom = paramInt4;
    return this;
  }
  
  public AlertDialog show()
  {
    AlertDialog localAlertDialog = create();
    localAlertDialog.show();
    return localAlertDialog;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AlertDialog.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */