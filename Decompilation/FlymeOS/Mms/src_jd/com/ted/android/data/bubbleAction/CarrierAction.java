package com.ted.android.data.bubbleAction;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.ted.android.data.BubbleEntity;
import com.ted.android.message.BubbleUtils;
import org.json.JSONObject;

public class CarrierAction
  extends CommonAction
{
  private static String CARRIERNAME_KEY = "CarrierName";
  private static String CARRIERNAME_NUMBER = "CarrierNumber";
  private static final String COURIER_ICON = "http://img.teddymobile.cn/2015/02/01/a64aa8985a86c569e03798b07baaf347_60X60.png";
  private String carrierName = "";
  private String carrierNumber = "";
  
  public CarrierAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
    action = 12;
    buttonText = "快递查询";
    icon = "http://img.teddymobile.cn/2015/02/01/a64aa8985a86c569e03798b07baaf347_60X60.png";
  }
  
  public CarrierAction(BubbleEntity paramBubbleEntity, String paramString)
  {
    super(paramBubbleEntity, paramString);
    action = 12;
    buttonText = "快递查询";
    icon = "http://img.teddymobile.cn/2015/02/01/a64aa8985a86c569e03798b07baaf347_60X60.png";
  }
  
  public static CommonAction fromJSON(BubbleEntity paramBubbleEntity, String paramString)
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    paramBubbleEntity = new CarrierAction(paramBubbleEntity, paramString);
    if (localJSONObject.has(CARRIERNAME_KEY)) {
      paramBubbleEntity.setCarrierName(localJSONObject.getString(CARRIERNAME_KEY));
    }
    if (localJSONObject.has(CARRIERNAME_NUMBER)) {
      paramBubbleEntity.setCariierNumber(localJSONObject.getString(CARRIERNAME_NUMBER));
    }
    return paramBubbleEntity;
  }
  
  public boolean doAction(Context paramContext)
  {
    if (!TextUtils.isEmpty(carrierNumber))
    {
      String str2 = String.format("http://m.kuaidi100.com/result.jsp?nu=%s", new Object[] { carrierNumber });
      String str1 = str2;
      if (!TextUtils.isEmpty(carrierName)) {
        str1 = String.format(str2 + "&com=%s", new Object[] { carrierName });
      }
      BubbleUtils.openUrl(paramContext, str1);
      return true;
    }
    Toast.makeText(paramContext, "未发现快递单号", 0).show();
    return true;
  }
  
  public String getCarrierName()
  {
    return carrierName;
  }
  
  public void setCariierNumber(String paramString)
  {
    carrierNumber = paramString;
  }
  
  public void setCarrierName(String paramString)
  {
    carrierName = paramString;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = super.toJSON();
    if (!TextUtils.isEmpty(carrierName)) {
      localJSONObject.put(CARRIERNAME_KEY, carrierName);
    }
    if (!TextUtils.isEmpty(carrierNumber)) {
      localJSONObject.put(CARRIERNAME_NUMBER, carrierNumber);
    }
    return localJSONObject;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.CarrierAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */