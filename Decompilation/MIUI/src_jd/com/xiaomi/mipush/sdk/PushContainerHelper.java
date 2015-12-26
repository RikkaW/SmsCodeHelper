package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.Target;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionCommandResult;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendFeedbackResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import com.xiaomi.xmpush.thrift.XmPushActionSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.nio.ByteBuffer;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

public class PushContainerHelper
{
  private static final byte[] DEFAULT_IV = { 100, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, 52, 84, 102, 18, 32 };
  
  public static byte[] MIPushDecrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    return createCipher(paramArrayOfByte1, 2).doFinal(paramArrayOfByte2);
  }
  
  public static byte[] MIPushEncrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    return createCipher(paramArrayOfByte1, 1).doFinal(paramArrayOfByte2);
  }
  
  private static Cipher createCipher(byte[] paramArrayOfByte, int paramInt)
    throws Exception
  {
    paramArrayOfByte = new SecretKeySpec(paramArrayOfByte, "AES");
    IvParameterSpec localIvParameterSpec = new IvParameterSpec(DEFAULT_IV);
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(paramInt, paramArrayOfByte, localIvParameterSpec);
    return localCipher;
  }
  
  private static TBase createRespMessageFromAction(ActionType paramActionType)
  {
    switch (paramActionType)
    {
    default: 
      return null;
    case ???: 
      return new XmPushActionRegistrationResult();
    case ???: 
      return new XmPushActionUnRegistrationResult();
    case ???: 
      return new XmPushActionSubscriptionResult();
    case ???: 
      return new XmPushActionUnSubscriptionResult();
    case ???: 
      return new XmPushActionSendMessage();
    case ???: 
      return new XmPushActionAckMessage();
    case ???: 
      return new XmPushActionCommandResult();
    case ???: 
      return new XmPushActionSendFeedbackResult();
    case ???: 
      return new XmPushActionNotification();
    }
    return new XmPushActionCommandResult();
  }
  
  protected static <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(Context paramContext, T paramT, ActionType paramActionType)
  {
    if (!paramActionType.equals(ActionType.Registration)) {}
    for (boolean bool = true;; bool = false) {
      return generateRequestContainer(paramContext, paramT, paramActionType, bool, paramContext.getPackageName(), AppInfoHolder.getInstance(paramContext).getAppID());
    }
  }
  
  protected static <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(Context paramContext, T paramT, ActionType paramActionType, boolean paramBoolean, String paramString1, String paramString2)
  {
    byte[] arrayOfByte = XmPushThriftSerializeUtils.convertThriftObjectToBytes(paramT);
    if (arrayOfByte == null)
    {
      MyLog.warn("invoke convertThriftObjectToBytes method, return null.");
      return null;
    }
    XmPushActionContainer localXmPushActionContainer = new XmPushActionContainer();
    paramT = arrayOfByte;
    if (paramBoolean)
    {
      paramContext = AppInfoHolder.getInstance(paramContext).getRegSecret();
      if (TextUtils.isEmpty(paramContext))
      {
        MyLog.warn("regSecret is empty, return null");
        return null;
      }
      paramContext = Base64Coder.decode(paramContext);
    }
    try
    {
      paramT = MIPushEncrypt(paramContext, arrayOfByte);
      paramContext = new Target();
      channelId = 5L;
      userId = "fakeid";
      localXmPushActionContainer.setTarget(paramContext);
      localXmPushActionContainer.setPushAction(ByteBuffer.wrap(paramT));
      localXmPushActionContainer.setAction(paramActionType);
      localXmPushActionContainer.setIsRequest(true);
      localXmPushActionContainer.setPackageName(paramString1);
      localXmPushActionContainer.setEncryptAction(paramBoolean);
      localXmPushActionContainer.setAppid(paramString2);
      return localXmPushActionContainer;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        MyLog.e("encryption error. ");
        paramT = arrayOfByte;
      }
    }
  }
  
  protected static TBase getResponseMessageBodyFromContainer(Context paramContext, XmPushActionContainer paramXmPushActionContainer)
    throws TException
  {
    if (paramXmPushActionContainer.isEncryptAction()) {}
    for (paramContext = Base64Coder.decode(AppInfoHolder.getInstance(paramContext).getRegSecret());; paramContext = paramXmPushActionContainer.getPushAction()) {
      try
      {
        paramContext = MIPushDecrypt(paramContext, paramXmPushActionContainer.getPushAction());
        paramXmPushActionContainer = createRespMessageFromAction(paramXmPushActionContainer.getAction());
        if (paramXmPushActionContainer != null) {
          XmPushThriftSerializeUtils.convertByteArrayToThriftObject(paramXmPushActionContainer, paramContext);
        }
        return paramXmPushActionContainer;
      }
      catch (Exception paramContext)
      {
        throw new TException("the aes decrypt failed.", paramContext);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushContainerHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */