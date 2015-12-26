package miui.external;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SdkErrorActivity
  extends Activity
  implements SdkConstants
{
  private String f;
  private DialogInterface.OnClickListener g = new a(this);
  private DialogInterface.OnClickListener h = new c(this);
  
  private Dialog a()
  {
    String str2;
    if (Locale.CHINESE.getLanguage().equals(f)) {
      str2 = "MIUI SDK发生错误";
    }
    for (String str1 = "请重新安装MIUI SDK再运行本程序。";; str1 = "Please re-install MIUI SDK and then re-run this application.")
    {
      return a(str2, str1, g);
      str2 = "MIUI SDK encounter errors";
    }
  }
  
  private Dialog a(String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener)
  {
    return new AlertDialog.Builder(this).setTitle(paramString1).setMessage(paramString2).setPositiveButton(17039370, paramOnClickListener).setIcon(17301543).setCancelable(false).create();
  }
  
  private Dialog a(String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    return new AlertDialog.Builder(this).setTitle(paramString1).setMessage(paramString2).setPositiveButton(17039370, paramOnClickListener1).setNegativeButton(17039360, paramOnClickListener2).setIcon(17301543).setCancelable(false).create();
  }
  
  private Dialog b()
  {
    String str2;
    if (Locale.CHINESE.getLanguage().equals(f)) {
      str2 = "没有找到MIUI SDK";
    }
    for (String str1 = "请先安装MIUI SDK再运行本程序。";; str1 = "Please install MIUI SDK and then re-run this application.")
    {
      return a(str2, str1, g);
      str2 = "MIUI SDK not found";
    }
  }
  
  private Dialog c()
  {
    String str2;
    if (!g())
    {
      if (Locale.CHINESE.getLanguage().equals(f)) {
        str2 = "MIUI SDK版本过低";
      }
      for (str1 = "请先升级MIUI SDK再运行本程序。";; str1 = "Please upgrade MIUI SDK and then re-run this application.")
      {
        return a(str2, str1, g);
        str2 = "MIUI SDK too old";
      }
    }
    if (Locale.CHINESE.getLanguage().equals(f)) {
      str2 = "MIUI SDK版本过低";
    }
    for (String str1 = "请先升级MIUI SDK再运行本程序。是否现在升级？";; str1 = "Please upgrade MIUI SDK and then re-run this application. Upgrade now?")
    {
      return a(str2, str1, h, g);
      str2 = "MIUI SDK too old";
    }
  }
  
  private Dialog d()
  {
    String str2;
    if (Locale.CHINESE.getLanguage().equals(f)) {
      str2 = "MIUI SDK正在更新";
    }
    for (String str1 = "请稍候...";; str1 = "Please wait...")
    {
      return ProgressDialog.show(this, str2, str1, true, false);
      str2 = "MIUI SDK updating";
    }
  }
  
  private Dialog e()
  {
    String str2;
    if (Locale.CHINESE.getLanguage().equals(f)) {
      str2 = "MIUI SDK更新完成";
    }
    for (String str1 = "请重新运行本程序。";; str1 = "Please re-run this application.")
    {
      return a(str2, str1, g);
      str2 = "MIUI SDK updated";
    }
  }
  
  private Dialog f()
  {
    String str2;
    if (Locale.CHINESE.getLanguage().equals(f)) {
      str2 = "MIUI SDK更新失败";
    }
    for (String str1 = "请稍后重试。";; str1 = "Please try it later.")
    {
      return a(str2, str1, g);
      str2 = "MIUI SDK update failed";
    }
  }
  
  private boolean g()
  {
    try
    {
      boolean bool = ((Boolean)f.o().getMethod("supportUpdate", new Class[] { Map.class }).invoke(null, new Object[] { null })).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean h()
  {
    try
    {
      HashMap localHashMap = new HashMap();
      boolean bool = ((Boolean)f.o().getMethod("update", new Class[] { Map.class }).invoke(null, new Object[] { localHashMap })).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(16973909);
    super.onCreate(paramBundle);
    f = Locale.getDefault().getLanguage();
    paramBundle = null;
    Object localObject = getIntent();
    if (localObject != null) {
      paramBundle = (SdkConstants.SdkError)((Intent)localObject).getSerializableExtra("com.miui.sdk.error");
    }
    localObject = paramBundle;
    if (paramBundle == null) {
      localObject = SdkConstants.SdkError.GENERIC;
    }
    switch (1.i[localObject.ordinal()])
    {
    default: 
      paramBundle = a();
    }
    for (;;)
    {
      new a(paramBundle).show(getFragmentManager(), "SdkErrorPromptDialog");
      return;
      paramBundle = b();
      continue;
      paramBundle = c();
    }
  }
  
  class a
    extends DialogFragment
  {
    private Dialog r;
    
    public a(Dialog paramDialog)
    {
      r = paramDialog;
    }
    
    public Dialog onCreateDialog(Bundle paramBundle)
    {
      return r;
    }
  }
}

/* Location:
 * Qualified Name:     miui.external.SdkErrorActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */