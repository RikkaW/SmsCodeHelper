package com.ted.android.data.bubbleAction;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.ted.android.data.BubbleEntity;
import com.ted.android.message.BubbleUtils;
import org.json.JSONObject;

public class VerificationCodeAction
  extends CommonAction
{
  private static final String CODE_ICON = "http://img.teddymobile.cn/2015/02/02/0778d5f6b39602762f6af1bbcf865512_60X60.png";
  private static final String VERIFICATION_CODE_KEY = "verification_code";
  private String verificationCode;
  
  public VerificationCodeAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
    action = 16;
    buttonText = "复制验证码";
    icon = "http://img.teddymobile.cn/2015/02/02/0778d5f6b39602762f6af1bbcf865512_60X60.png";
  }
  
  public VerificationCodeAction(BubbleEntity paramBubbleEntity, String paramString)
  {
    super(paramBubbleEntity, paramString);
    action = 16;
    buttonText = "复制验证码";
    icon = "http://img.teddymobile.cn/2015/02/02/0778d5f6b39602762f6af1bbcf865512_60X60.png";
  }
  
  public static CommonAction fromJSON(BubbleEntity paramBubbleEntity, String paramString)
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    paramBubbleEntity = new VerificationCodeAction(paramBubbleEntity, paramString);
    if (localJSONObject.has("verification_code")) {
      paramBubbleEntity.setVerificationCode(localJSONObject.getString("verification_code"));
    }
    return paramBubbleEntity;
  }
  
  public boolean doAction(Context paramContext)
  {
    if (!TextUtils.isEmpty(verificationCode))
    {
      BubbleUtils.setClipboard(paramContext, verificationCode);
      Toast.makeText(paramContext, "验证码已复制", 0).show();
    }
    for (;;)
    {
      return true;
      Toast.makeText(paramContext, "未发现验证码", 0).show();
    }
  }
  
  public void setVerificationCode(String paramString)
  {
    verificationCode = paramString;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = super.toJSON();
    if (!TextUtils.isEmpty(verificationCode)) {
      localJSONObject.put("verification_code", verificationCode);
    }
    return localJSONObject;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.VerificationCodeAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */