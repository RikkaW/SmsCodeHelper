package cn.com.xy.sms.sdk.util;

import android.content.Context;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PopupMsgManager
{
  public static LinkedList<BusinessSmsMessage> businessSmsList = new LinkedList();
  public static HashSet<String> hasPhoneThird = new HashSet();
  public static boolean hasRemoveData = false;
  public static List<String> removePhoneNumList = new ArrayList();
  
  public static int addAllToFirst(LinkedList<BusinessSmsMessage> paramLinkedList)
  {
    int i = -1;
    label190:
    label204:
    for (;;)
    {
      int k;
      int j;
      int m;
      try
      {
        k = paramLinkedList.size() - 1;
        j = i;
        if (k >= 0)
        {
          m = businessSmsList.size();
          new StringBuilder("size: ").append(k).append(" size2: ").append(m);
          j = k;
          break label190;
        }
        removePhoneNumList.clear();
        paramLinkedList.clear();
        new StringBuilder("next addAllToFirst size: ").append(businessSmsList.size());
        return j;
      }
      finally {}
      BusinessSmsMessage localBusinessSmsMessage = (BusinessSmsMessage)paramLinkedList.get(j);
      Object localObject = localBusinessSmsMessage.getValue("opensms_enable");
      String str = StringUtils.getPhoneNumberNo86(localBusinessSmsMessage.getOriginatingAddress());
      if (((localObject == null) || (!"false".equalsIgnoreCase(localObject.toString()))) && (!removePhoneNumList.contains(str)))
      {
        businessSmsList.addFirst(localBusinessSmsMessage);
      }
      else
      {
        if ((m == 0) && (k == 0)) {
          businessSmsList.addFirst(localBusinessSmsMessage);
        }
        i = 1;
      }
      for (;;)
      {
        if (j >= 0) {
          break label204;
        }
        j = i;
        break;
        j -= 1;
      }
    }
  }
  
  public static boolean addThirdPopupMsgData(Map<String, Object> paramMap)
  {
    String str1 = StringUtils.getPhoneNumberNo86((String)paramMap.get("phoneNumber"));
    Iterator localIterator;
    Object localObject;
    int i;
    if ((businessSmsList != null) && (!businessSmsList.isEmpty()))
    {
      localIterator = businessSmsList.iterator();
      localObject = null;
      if ((localIterator == null) || (!localIterator.hasNext())) {
        i = 0;
      }
    }
    for (;;)
    {
      label58:
      if ((i != 0) && (localObject != null))
      {
        valueMap = paramMap;
        originatingAddress = str1;
        businessSmsList.remove(localObject);
        paramMap = (Map<String, Object>)localObject;
        if (!hasPhoneThird.contains(str1)) {
          hasPhoneThird.add(str1);
        }
      }
      for (paramMap = (Map<String, Object>)localObject;; paramMap = (Map<String, Object>)localObject)
      {
        if (paramMap != null) {
          businessSmsList.addLast(paramMap);
        }
        new StringBuilder("addThirdPopupMsgData phoneNumber").append(str1).append(" size :").append(businessSmsList.size());
        return true;
        BusinessSmsMessage localBusinessSmsMessage = (BusinessSmsMessage)localIterator.next();
        localObject = localBusinessSmsMessage;
        if (localBusinessSmsMessage == null) {
          break;
        }
        localObject = localBusinessSmsMessage;
        if (messageBody != null) {
          break;
        }
        String str2 = StringUtils.getPhoneNumberNo86(localBusinessSmsMessage.getOriginatingAddress());
        localObject = localBusinessSmsMessage;
        if (StringUtils.isNull(str1)) {
          break;
        }
        localObject = localBusinessSmsMessage;
        if (StringUtils.isNull(str2)) {
          break;
        }
        localObject = localBusinessSmsMessage;
        if (!str1.equals(str2)) {
          break;
        }
        i = 1;
        localObject = localBusinessSmsMessage;
        break label58;
        localObject = new BusinessSmsMessage();
        valueMap = paramMap;
        originatingAddress = str1;
      }
      localObject = null;
      i = 0;
    }
  }
  
  public static void clearBusinessMessage(List<BusinessSmsMessage> paramList)
  {
    int i = 0;
    if (paramList != null) {}
    try
    {
      businessSmsList.removeAll(paramList);
      i = paramList.size();
      new StringBuilder("清除所有短信 数: ").append(i).append(" 剩余: ").append(businessSmsList.size());
      return;
    }
    finally {}
  }
  
  public static void clearPhoneThird()
  {
    hasPhoneThird.clear();
  }
  
  /* Error */
  public static int clearUserClickBusinessMessage()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 24	cn/com/xy/sms/sdk/util/PopupMsgManager:businessSmsList	Ljava/util/LinkedList;
    //   6: invokevirtual 44	java/util/LinkedList:size	()I
    //   9: istore_1
    //   10: new 28	java/util/ArrayList
    //   13: dup
    //   14: invokespecial 29	java/util/ArrayList:<init>	()V
    //   17: astore_2
    //   18: iconst_0
    //   19: istore_0
    //   20: iload_0
    //   21: iload_1
    //   22: if_icmplt +33 -> 55
    //   25: aload_2
    //   26: invokeinterface 186 1 0
    //   31: ifne +80 -> 111
    //   34: getstatic 24	cn/com/xy/sms/sdk/util/PopupMsgManager:businessSmsList	Ljava/util/LinkedList;
    //   37: aload_2
    //   38: invokevirtual 176	java/util/LinkedList:removeAll	(Ljava/util/Collection;)Z
    //   41: pop
    //   42: aload_2
    //   43: invokeinterface 65 1 0
    //   48: iconst_1
    //   49: istore_0
    //   50: ldc 2
    //   52: monitorexit
    //   53: iload_0
    //   54: ireturn
    //   55: getstatic 24	cn/com/xy/sms/sdk/util/PopupMsgManager:businessSmsList	Ljava/util/LinkedList;
    //   58: iload_0
    //   59: invokevirtual 72	java/util/LinkedList:get	(I)Ljava/lang/Object;
    //   62: checkcast 74	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage
    //   65: ldc 76
    //   67: invokevirtual 80	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:getValue	(Ljava/lang/String;)Ljava/lang/Object;
    //   70: astore_3
    //   71: aload_3
    //   72: ifnull +32 -> 104
    //   75: ldc 92
    //   77: aload_3
    //   78: invokevirtual 95	java/lang/Object:toString	()Ljava/lang/String;
    //   81: invokevirtual 101	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   84: ifeq +20 -> 104
    //   87: aload_2
    //   88: getstatic 24	cn/com/xy/sms/sdk/util/PopupMsgManager:businessSmsList	Ljava/util/LinkedList;
    //   91: iload_0
    //   92: invokevirtual 72	java/util/LinkedList:get	(I)Ljava/lang/Object;
    //   95: checkcast 74	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage
    //   98: invokeinterface 187 2 0
    //   103: pop
    //   104: iload_0
    //   105: iconst_1
    //   106: iadd
    //   107: istore_0
    //   108: goto -88 -> 20
    //   111: iconst_m1
    //   112: istore_0
    //   113: goto -63 -> 50
    //   116: astore_2
    //   117: ldc 2
    //   119: monitorexit
    //   120: aload_2
    //   121: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   19	94	0	i	int
    //   9	14	1	j	int
    //   17	71	2	localArrayList	ArrayList
    //   116	5	2	localObject1	Object
    //   70	8	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   3	18	116	finally
    //   25	48	116	finally
    //   55	71	116	finally
    //   75	104	116	finally
  }
  
  public static boolean containsPhoneThird(String paramString)
  {
    return hasPhoneThird.contains(paramString);
  }
  
  public static int getBusinessMessageSize()
  {
    try
    {
      int i = businessSmsList.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static BusinessSmsMessage getBussinessMessageByIndex(int paramInt)
  {
    if (paramInt >= 0) {}
    try
    {
      if (paramInt < businessSmsList.size())
      {
        BusinessSmsMessage localBusinessSmsMessage = (BusinessSmsMessage)businessSmsList.get(paramInt);
        return localBusinessSmsMessage;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
        Object localObject1 = null;
      }
    }
    finally {}
  }
  
  public static boolean getHasRemoveData()
  {
    return hasRemoveData;
  }
  
  public static boolean removeBusinessMessage(BusinessSmsMessage paramBusinessSmsMessage)
  {
    try
    {
      new StringBuilder("清除短信: phone ").append(originatingAddress);
      bool = businessSmsList.remove(paramBusinessSmsMessage);
      return bool;
    }
    catch (Throwable paramBusinessSmsMessage)
    {
      for (;;)
      {
        paramBusinessSmsMessage.printStackTrace();
        boolean bool = false;
      }
    }
    finally {}
  }
  
  public static BusinessSmsMessage removeBusinessMessageByIndex(int paramInt)
  {
    try
    {
      BusinessSmsMessage localBusinessSmsMessage = (BusinessSmsMessage)businessSmsList.remove(paramInt);
      return localBusinessSmsMessage;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
        Object localObject1 = null;
      }
    }
    finally {}
  }
  
  public static void removeBusinessMessageByNum(Context paramContext, String paramString, boolean paramBoolean, Map<String, String> paramMap)
  {
    for (;;)
    {
      try
      {
        if (StringUtils.isNull(paramString)) {
          return;
        }
        if ((businessSmsList == null) || (businessSmsList.isEmpty())) {
          break;
        }
        paramContext = businessSmsList.iterator();
        if ((paramContext == null) || (!paramContext.hasNext())) {
          break;
        }
        paramMap = (BusinessSmsMessage)paramContext.next();
        if (paramMap == null) {
          continue;
        }
        String str1 = StringUtils.getPhoneNumberNo86(paramString);
        String str2 = StringUtils.getPhoneNumberNo86(paramMap.getOriginatingAddress());
        if ((StringUtils.isNull(str1)) || (StringUtils.isNull(str2)) || (!str1.equals(str2))) {
          continue;
        }
        if (!paramBoolean)
        {
          if (!removePhoneNumList.contains(str1)) {
            removePhoneNumList.add(str1);
          }
          paramContext.remove();
          hasRemoveData = true;
          continue;
        }
        if (!paramBoolean) {
          continue;
        }
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      if (messageBody == null) {
        paramContext.remove();
      }
    }
  }
  
  public static void removePhoneThird(String paramString)
  {
    hasPhoneThird.remove(paramString);
  }
  
  public static void setHasRemoveData(boolean paramBoolean)
  {
    hasRemoveData = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.PopupMsgManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */