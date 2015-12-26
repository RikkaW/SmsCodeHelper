package com.meizu.common.app;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;
import com.meizu.common.util.PermissionUtils;
import java.lang.reflect.Method;

public class PermissionDialogBuilder
  extends AlertDialog.Builder
{
  private CheckBox mCheckBox;
  private Context mContext;
  private String mFormatString;
  private OnPermissionClickListener mOnPermissionClickListener;
  private TextView mTextView;
  
  public PermissionDialogBuilder(Context paramContext)
  {
    this(paramContext, 0);
  }
  
  public PermissionDialogBuilder(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    mContext = paramContext;
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.mc_permission_dialog_view, null);
    setView(localView);
    setCancelable(false);
    mCheckBox = ((CheckBox)localView.findViewById(R.id.mc_pm_check));
    mTextView = ((TextView)localView.findViewById(R.id.mc_pm_textView));
    mFormatString = paramContext.getResources().getString(R.string.mc_permission_message_content);
    setPositiveButton(R.string.mc_allow, new PermissionDialogBuilder.1(this));
    setNegativeButton(R.string.mc_reject, new PermissionDialogBuilder.2(this));
    setOnCancelListener(new PermissionDialogBuilder.3(this));
  }
  
  private String join(String[] paramArrayOfString, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      if (i == paramArrayOfString.length - 1) {
        localStringBuffer.append(paramArrayOfString[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuffer.append(paramArrayOfString[i]).append(paramString);
      }
    }
    return new String(localStringBuffer);
  }
  
  public AlertDialog create()
  {
    if (isProductInternational()) {
      return new HideAlertDialog(mContext);
    }
    return super.create();
  }
  
  public boolean isProductInternational()
  {
    try
    {
      Class localClass = Class.forName("android.os.BuildExt");
      boolean bool = ((Boolean)localClass.getDeclaredMethod("isProductInternational", new Class[0]).invoke(localClass, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public void setMessage(String paramString)
  {
    mTextView.setText(paramString);
  }
  
  public void setMessage(String paramString, String[] paramArrayOfString)
  {
    String str = "";
    String[] arrayOfString = new PermissionUtils(mContext).loadPemissionLables(paramArrayOfString);
    paramArrayOfString = str;
    if (arrayOfString != null)
    {
      paramArrayOfString = str;
      if (arrayOfString.length > 0) {
        paramArrayOfString = String.format(mFormatString, new Object[] { paramString, join(arrayOfString, ", "), Integer.valueOf(arrayOfString.length) });
      }
    }
    setMessage(paramArrayOfString);
  }
  
  public void setOnPermissonListener(OnPermissionClickListener paramOnPermissionClickListener)
  {
    mOnPermissionClickListener = paramOnPermissionClickListener;
  }
  
  public AlertDialog show()
  {
    if (isProductInternational())
    {
      if (mOnPermissionClickListener != null) {
        mOnPermissionClickListener.onPerMisssionClick(null, mCheckBox.isChecked(), true);
      }
      return null;
    }
    return super.show();
  }
  
  public class HideAlertDialog
    extends AlertDialog
  {
    protected HideAlertDialog(Context paramContext)
    {
      super();
    }
    
    public void show()
    {
      if (mOnPermissionClickListener != null) {
        mOnPermissionClickListener.onPerMisssionClick(this, mCheckBox.isChecked(), true);
      }
    }
  }
  
  public static abstract interface OnPermissionClickListener
  {
    public abstract void onPerMisssionClick(DialogInterface paramDialogInterface, boolean paramBoolean1, boolean paramBoolean2);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.PermissionDialogBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */