package com.meizu.common.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meizu.common.R.drawable;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.style;
import com.meizu.common.widget.LoadingView;

public class LoadingDialog
  extends Dialog
{
  private float dimAmount;
  private boolean isCancelable;
  private Drawable mBackgrond = mContext.getResources().getDrawable(R.drawable.mz_toast_frame);
  private Context mContext = getContext();
  private LoadingView mLoadingView;
  private CharSequence mMessage;
  private TextView mMessageView;
  private LinearLayout mParentPanel;
  private int mTextColor = -1;
  private Window mWindow;
  
  public LoadingDialog(Context paramContext)
  {
    this(paramContext, R.style.LoadingDialogTheme);
  }
  
  public LoadingDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  private void initView()
  {
    if ((mMessageView != null) && (!TextUtils.isEmpty(mMessage)))
    {
      mMessageView.setVisibility(0);
      mMessageView.setText(mMessage);
      if (mTextColor != -1) {
        mMessageView.setTextColor(mTextColor);
      }
    }
    for (;;)
    {
      mWindow.setDimAmount(dimAmount);
      if (mBackgrond != null) {
        mParentPanel.setBackgroundDrawable(mBackgrond);
      }
      return;
      if (mParentPanel != null) {
        mParentPanel.setPadding(0, 0, 0, 0);
      }
    }
  }
  
  public static LoadingDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    return show(paramContext, paramCharSequence1, paramCharSequence2, false);
  }
  
  public static LoadingDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean)
  {
    return show(paramContext, paramCharSequence1, paramCharSequence2, paramBoolean, null);
  }
  
  public static LoadingDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    paramContext = new LoadingDialog(paramContext);
    paramContext.setTitle(paramCharSequence1);
    paramContext.setMessage(paramCharSequence2);
    paramContext.setCancelable(paramBoolean);
    paramContext.setOnCancelListener(paramOnCancelListener);
    paramContext.show();
    return paramContext;
  }
  
  public boolean isCancelable()
  {
    return isCancelable;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mWindow = getWindow();
    mWindow.requestFeature(1);
    setContentView(R.layout.mc_loading_dialog);
    mLoadingView = ((LoadingView)findViewById(R.id.loadingView));
    mMessageView = ((TextView)findViewById(R.id.message));
    mParentPanel = ((LinearLayout)findViewById(R.id.parentPanel));
    initView();
  }
  
  protected void onStart()
  {
    super.onStart();
    mLoadingView.startAnimator();
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    mBackgrond = paramDrawable;
    if (mParentPanel != null) {
      mParentPanel.setBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setBackgroundDrawableResource(int paramInt)
  {
    setBackgroundDrawable(mContext.getResources().getDrawable(paramInt));
  }
  
  public void setCancelable(boolean paramBoolean)
  {
    super.setCancelable(paramBoolean);
    isCancelable = paramBoolean;
  }
  
  public void setDimAmount(float paramFloat)
  {
    dimAmount = paramFloat;
    if (mWindow != null) {
      mWindow.setDimAmount(paramFloat);
    }
  }
  
  public void setMessage(int paramInt)
  {
    setMessage(mContext.getResources().getString(paramInt));
  }
  
  public void setMessage(CharSequence paramCharSequence)
  {
    mMessage = paramCharSequence;
    if ((mMessageView != null) && (!TextUtils.isEmpty(mMessage)))
    {
      mMessageView.setVisibility(0);
      mMessageView.setText(mMessage);
    }
  }
  
  public void setMessageTextColor(int paramInt)
  {
    mTextColor = paramInt;
    if (mMessageView != null) {
      mMessageView.setTextColor(mTextColor);
    }
  }
  
  public void setMessageTextColorResource(int paramInt)
  {
    setMessageTextColor(mContext.getResources().getColor(paramInt));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.LoadingDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */