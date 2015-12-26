package com.android.mms.view;

import aau;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.util.HashMap;
import java.util.Map;

public class EditTextEx
  extends EditText
{
  private static final String[] c = { "com.meizu.input.cover.SMILE", "com.meizu.input.cover.VOICE" };
  private a a;
  private Map<String, String> b;
  
  public EditTextEx(Context paramContext)
  {
    super(paramContext);
  }
  
  public EditTextEx(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public EditTextEx(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public EditTextEx(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
  }
  
  public final void a(String paramString, Bundle paramBundle)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)aau.b("android.view.inputmethod.InputMethodManager", "peekInstance");
    if (localInputMethodManager != null) {
      localInputMethodManager.sendAppPrivateCommand(this, paramString, paramBundle);
    }
  }
  
  public final boolean a(String paramString1, String paramString2)
  {
    String str = getPrivateImeOptions();
    if (b == null) {
      b = new HashMap();
    }
    Object localObject1 = b;
    if (((Boolean)aau.a("android.view.inputmethod.EditorInfo", "splitPrivateImeOptions", new Class[] { String.class, Map.class }, new Object[] { str, localObject1 })).booleanValue())
    {
      localObject1 = c;
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        b.remove(localObject2);
        i += 1;
      }
    }
    if (!TextUtils.isEmpty(paramString1)) {
      b.put(paramString1, paramString2);
    }
    paramString1 = (String)aau.a("android.view.inputmethod.EditorInfo", "makePrivateImeOptions", Map.class, b);
    if (!TextUtils.equals(paramString1, str))
    {
      setPrivateImeOptions(paramString1);
      paramString1 = (InputMethodManager)aau.b("android.view.inputmethod.InputMethodManager", "peekInstance");
      if (paramString1 == null) {
        return false;
      }
      paramString1.restartInput(this);
    }
    return true;
  }
  
  public boolean onPrivateIMECommand(String paramString, Bundle paramBundle)
  {
    if ((a != null) && (a.a(paramString, paramBundle))) {
      return true;
    }
    return super.onPrivateIMECommand(paramString, paramBundle);
  }
  
  public final void setOnPrivateIMECommandListener(a parama)
  {
    a = parama;
  }
  
  public static abstract interface a
  {
    public abstract boolean a(String paramString, Bundle paramBundle);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.EditTextEx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */