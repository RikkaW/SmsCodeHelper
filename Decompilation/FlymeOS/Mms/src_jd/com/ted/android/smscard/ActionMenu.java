package com.ted.android.smscard;

import android.view.View.OnClickListener;
import java.io.Serializable;
import java.util.HashMap;

public class ActionMenu
  implements Serializable
{
  public static final int ACTION_CALL = 8;
  public static final int ACTION_START_APP = 6;
  public static final int ACTION_URL = 3;
  public static final String KEY_ACTION = "action";
  public static final String KEY_APP_NAME = "appName";
  public static final String KEY_BUTTONS = "{group0}";
  public static final String KEY_BUTTON_TEXT = "buttonText";
  public static final String KEY_NUMBER = "number";
  public static final String KEY_PACKAGE_NAME = "packageName";
  public static final String KEY_URL = "url";
  private static final long serialVersionUID = 11L;
  private int actionType;
  private HashMap<String, String> extendData;
  private View.OnClickListener onClickListener;
  private String text;
  
  public void addExtendData(String paramString1, String paramString2)
  {
    if (extendData == null) {
      extendData = new HashMap();
    }
    extendData.put(paramString1, paramString2);
  }
  
  public int getActionType()
  {
    return actionType;
  }
  
  public View.OnClickListener getOnClickListener()
  {
    return onClickListener;
  }
  
  public String getText()
  {
    return text;
  }
  
  public String getValueFromExtendData(String paramString)
  {
    if (extendData != null) {
      return (String)extendData.get(paramString);
    }
    return null;
  }
  
  public void setActionType(int paramInt)
  {
    actionType = paramInt;
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    onClickListener = paramOnClickListener;
  }
  
  public void setText(String paramString)
  {
    text = paramString;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.smscard.ActionMenu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */