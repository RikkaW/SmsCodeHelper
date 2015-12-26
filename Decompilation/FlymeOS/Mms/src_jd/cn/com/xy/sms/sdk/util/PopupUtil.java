package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.Intent;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseRichBubbleManager;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PopupUtil
{
  public static int getActionCode(String paramString)
  {
    int j = 3;
    int i;
    try
    {
      if (!StringUtils.isNull(paramString))
      {
        if (paramString.equalsIgnoreCase("url")) {
          return 3;
        }
        if (paramString.equalsIgnoreCase("reply_sms")) {
          return 1;
        }
        if (paramString.equalsIgnoreCase("reply_sms_fwd")) {
          return 1;
        }
        if ((paramString.equalsIgnoreCase("call_phone")) || ("call".equalsIgnoreCase(paramString))) {
          break label458;
        }
        if (paramString.equalsIgnoreCase("reply_sms_open")) {
          return 1;
        }
        i = j;
        if (paramString.equalsIgnoreCase("access_url")) {
          break label456;
        }
        i = j;
        if (paramString.equalsIgnoreCase("down_url")) {
          break label456;
        }
        i = j;
        if ("download".equalsIgnoreCase(paramString)) {
          break label456;
        }
        i = j;
        if (paramString.equalsIgnoreCase("send_email")) {
          break label456;
        }
        i = j;
        if (paramString.equalsIgnoreCase("weibo_url")) {
          break label456;
        }
        if ((paramString.equalsIgnoreCase("map_site")) || ("open_map".equalsIgnoreCase(paramString)) || ("open_map_list".equalsIgnoreCase(paramString)) || ("open_map_browser".equalsIgnoreCase(paramString))) {
          break label460;
        }
        if ((paramString.equalsIgnoreCase("chong_zhi")) || (paramString.equalsIgnoreCase("recharge")) || ("zfb_recharge".equalsIgnoreCase(paramString)) || ("WEB_CHONG_ZHI".equalsIgnoreCase(paramString)) || ("WEB_RECHARGE_CHOOSE".equalsIgnoreCase(paramString))) {
          break label462;
        }
        if (("repayment".equalsIgnoreCase(paramString)) || ("zfb_repayment".equals(paramString)) || ("WEB_REPAYMENT".equalsIgnoreCase(paramString)) || ("WEB_REPAYMENT_CHOOSE".equalsIgnoreCase(paramString))) {
          break label464;
        }
        if (paramString.equalsIgnoreCase("copy_code")) {
          return 8;
        }
        if ("open_app".equalsIgnoreCase(paramString)) {
          return 9;
        }
        if (("time_remind".equalsIgnoreCase(paramString)) || ("sdk_time_remind".equalsIgnoreCase(paramString))) {
          break label467;
        }
        if ("pay_water_gas".equalsIgnoreCase(paramString)) {
          return 11;
        }
        if (("WEB_TRAFFIC_ORDER".equalsIgnoreCase(paramString)) || ("WEB_TRAFFIC_CHOOSE".equalsIgnoreCase(paramString)) || ("WEB_PURCHASE".equalsIgnoreCase(paramString))) {
          break label470;
        }
        if ("WEB_QUERY_EXPRESS_FLOW".equalsIgnoreCase(paramString)) {
          return 13;
        }
        if ("WEB_QUERY_FLIGHT_TREND".equalsIgnoreCase(paramString)) {
          return 14;
        }
        if ("WEB_INSTALMENT_PLAN".equalsIgnoreCase(paramString)) {
          return 15;
        }
        if ("WEB_TRAIN_STATION".equalsIgnoreCase(paramString)) {
          return 16;
        }
        if (("WEB_NEAR_SITE".equalsIgnoreCase(paramString)) || ("near_site".equalsIgnoreCase(paramString))) {
          break label473;
        }
        boolean bool = "WEB_LIVE_CHOOSE".equalsIgnoreCase(paramString);
        if (bool) {
          return 18;
        }
        return 7;
      }
    }
    catch (Throwable paramString)
    {
      LogManager.e("XIAOYUAN", "PopupUtil getActionCode error: " + paramString.getMessage(), paramString);
      i = -1;
    }
    label456:
    return i;
    label458:
    return 2;
    label460:
    return 4;
    label462:
    return 5;
    label464:
    return 6;
    label467:
    return 10;
    label470:
    return 12;
    label473:
    return 17;
  }
  
  public static BusinessSmsMessage getMsg(String paramString1, String paramString2)
  {
    if ((!StringUtils.isNull(paramString1)) && (!StringUtils.isNull(paramString2)))
    {
      BusinessSmsMessage localBusinessSmsMessage = new BusinessSmsMessage();
      localBusinessSmsMessage.setOriginatingAddress(paramString1);
      localBusinessSmsMessage.setMessageBody(paramString2);
      isBgVis = true;
      return localBusinessSmsMessage;
    }
    return null;
  }
  
  public static Map<String, Object> getResultMap(Map<String, Object> paramMap1, String paramString1, String paramString2, Map<String, Object> paramMap2, Context paramContext)
  {
    if ((ViewUtil.getChannelType() == 3) && (!((Boolean)paramMap1.get("Result")).booleanValue()) && (paramString1 != null) && (paramMap2 != null))
    {
      Object localObject1 = paramMap2.get("msgId");
      Object localObject2 = paramMap2.get("simIndex");
      Object localObject3 = paramMap2.get("simName");
      Object localObject4 = paramMap2.get("msgTime");
      Object localObject5 = paramMap2.get("uri");
      paramMap2.clear();
      paramMap2.put("msgId", localObject1);
      paramMap2.put("simIndex", localObject2);
      paramMap2.put("simName", localObject3);
      paramMap2.put("msgTime", localObject4);
      paramMap2.put("phoneNumber", paramString1);
      paramMap2.put("smsContent", paramString2);
      paramMap2.put("uri", localObject5);
      startBusinessReceiveSmsActivity(paramContext, null, paramMap2);
      paramMap1.put("Result", Boolean.valueOf(true));
    }
    return paramMap1;
  }
  
  public static Map<String, Object> getResultMap(boolean paramBoolean1, boolean paramBoolean2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Result", Boolean.valueOf(paramBoolean1));
    localHashMap.put("recogResult", Boolean.valueOf(paramBoolean2));
    return localHashMap;
  }
  
  public static String getValue(int paramInt)
  {
    try
    {
      StringBuffer localStringBuffer = new StringBuffer();
      int i = 1;
      for (;;)
      {
        if (i >= 5) {
          return StringUtils.decode(StringUtils.handlerAssemble2(a.a(localStringBuffer.toString())));
        }
        localStringBuffer.append(DuoquUtils.getCode(i, paramInt));
        i += 1;
      }
      return "";
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static boolean isEnterpriseSms(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap)
  {
    paramContext = StringUtils.getPhoneNumberNo86(paramString1);
    if (StringUtils.isNull(paramContext)) {}
    label474:
    do
    {
      return false;
      paramContext = paramContext.replace(" ", "").replace("-", "");
      int i = 0;
      if (i >= 43) {
        i = 0;
      }
      for (;;)
      {
        if (i >= 9)
        {
          if ((!paramContext.startsWith("12520030")) || (paramContext.length() > 10)) {
            break label474;
          }
          return true;
          if (new String[] { "10088", "10198", "101901", "123662", "12306", "12110110", "121100020", "11888", "11868", "1186666", "118388", "118200", "118114", "118100", "118067", "11803080", "11185", "11183", "13800138000", "095583", "1252004411", "12520", "12520029", "12520035", "125200353", "125200352", "125200304", "125200351", "12520010", "12520021", "125200303", "1252003300000", "12520032", "125200302", "12520028", "12520038", "12520024", "12520036", "125200301", "12520027", "125200354", "1252003300000", "053287003810" }[i].equals(paramContext)) {
            return true;
          }
          i += 1;
          break;
        }
        if (paramContext.startsWith(new String[] { "96", "95", "106", "10178", "10086", "1006", "1001", "1000", "116114" }[i])) {
          return true;
        }
        i += 1;
      }
    } while ((!paramContext.startsWith("12520036")) || (paramContext.length() != 19));
    return StringUtils.sj(paramContext.replace("12520036", ""));
  }
  
  public static boolean isPopupAble(Map<String, Object> paramMap, String paramString)
  {
    try
    {
      Class localClass = Class.forName("cn.com.xy.sms.sdk.ui.popu.util.ViewManger");
      Method localMethod = localClass.getMethod("isPopupAble", new Class[] { Map.class, String.class });
      if (localMethod != null)
      {
        boolean bool = ((Boolean)localMethod.invoke(localClass, new Object[] { paramMap, paramString })).booleanValue();
        return bool;
      }
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return false;
  }
  
  public static void isUseDefaultPopup(BusinessSmsMessage paramBusinessSmsMessage, Map<String, Object> paramMap, String paramString)
  {
    if ((paramBusinessSmsMessage != null) && (paramMap != null)) {}
    try
    {
      if ((Constant.isDefaultImageExist()) && (paramMap.containsKey("view_forceToDefault_popup")))
      {
        Constant.getContext();
        paramMap = cn.com.xy.sms.sdk.b.a.a(paramString);
        if (paramMap != null) {
          imagePathMap = paramMap;
        }
      }
      return;
    }
    catch (Throwable paramBusinessSmsMessage)
    {
      paramBusinessSmsMessage.printStackTrace();
    }
  }
  
  public static Map<String, Object> parseMsgToBubbleCardResult(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, byte paramByte, Map<String, Object> paramMap, boolean paramBoolean)
  {
    if (paramMap == null) {}
    try
    {
      LogManager.e("xiaoyuan_test", "valueMap null .msgId:  " + paramString1, null);
      return null;
    }
    catch (Throwable paramContext)
    {
      long l2;
      Iterator localIterator;
      paramContext.printStackTrace();
      return null;
      LogManager.e("xiaoyuan_test", "valueMap isPopupAble faild.msgId: " + paramString1, null);
      paramByte = -3;
      if (MatchCacheManager.queryDataCount(paramString1, MatchCacheManager.getMD5(paramString2, paramString4)) != 0) {
        break label873;
      }
      long l1 = System.currentTimeMillis() - (DexUtil.getUpdateCycleByType(14, 21600000L) - 120000L);
      LogManager.e("xiaoyuan_test", "脚本已识别但是找不到资源valueMap res faild.msgId: " + paramString1 + "bubbleLasttime=" + l1 + "time=" + new Date(l1).toLocaleString(), null);
      for (;;)
      {
        paramString3 = MatchCacheManager.getMD5(paramString2, paramString4);
        if (!StringUtils.isNull(paramString3)) {
          MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, new String[] { "msg_num_md5", paramString3, "phonenum", StringUtils.getPhoneNumberNo86(paramString2), "scene_id", "", "msg_id", paramString1, "bubble_result", "", "save_time", String.valueOf(paramLong), "bubble_lasttime", String.valueOf(l1) }), 2);
        }
        if (paramBoolean) {
          ParseRichBubbleManager.addInvalidBubbleData(paramString2, paramString1);
        }
        if ((paramByte == 0) || (paramContext == null)) {
          break label966;
        }
        paramContext.clear();
        paramContext.put("parseStatu", Integer.valueOf(paramByte));
        return paramContext;
        LogManager.e("xiaoyuan_test", "valueMap BubleSmsTitle faild.msgId: " + paramString1, null);
        paramByte = -2;
        break;
        l1 = System.currentTimeMillis() - (DexUtil.getUpdateCycleByType(14, 21600000L) - 420000L);
        LogManager.e("xiaoyuan_test", "脚本已识别隔2分钟后仍然查不到资源valueMap res faild.msgId: " + paramString1 + "bubbleLasttime=" + l1, null);
      }
    }
    finally
    {
      q.a();
    }
    paramMap.remove("viewPartParam");
    l2 = System.currentTimeMillis();
    paramContext = DexUtil.handerBubbleValueMap(paramMap);
    l1 = System.currentTimeMillis();
    new StringBuilder("handerBubbleValueMap time:").append(l1 - l2);
    l1 = System.currentTimeMillis();
    byte b = 0;
    if (paramContext != null)
    {
      paramString3 = (String)paramContext.get("title_num");
      paramMap = cn.com.xy.sms.sdk.b.a.c(paramString3);
      l1 = System.currentTimeMillis();
      new StringBuilder("getBubleSmsTitle time:").append(l1 - l2);
      if ((paramMap != null) && (!paramMap.isEmpty()))
      {
        b = 1;
        if ((ViewUtil.getChannelType() != 7) && (ViewUtil.getChannelType() != 17) && (b == 0)) {
          break label842;
        }
        if (b != 0) {
          paramContext.putAll(paramMap);
        }
        if (!isPopupAble(paramContext, paramString3)) {
          break label563;
        }
        paramString2 = StringUtils.getPhoneNumberNo86(paramString2);
        paramContext.remove("viewPartParam");
        paramMap = new JSONObject();
        localIterator = paramContext.entrySet().iterator();
        if (localIterator.hasNext()) {
          break label487;
        }
        paramString4 = MatchCacheManager.getMD5(paramString2, paramString4);
        MatchCacheManager.removeUselessKey(paramMap);
        if (StringUtils.isNull(paramString4)) {
          break label972;
        }
        paramLong = MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, new String[] { "msg_num_md5", paramString4, "phonenum", paramString2, "scene_id", paramString3, "msg_id", paramString1, "bubble_result", paramMap.toString(), "save_time", String.valueOf(paramLong), "bubble_lasttime", String.valueOf(System.currentTimeMillis()) }), 2);
        LogManager.e("xiaoyuan_test", "MatchCacheManager insertOrUpdate sdkMsgId: " + paramLong, null);
      }
    }
    for (;;)
    {
      paramContext.clear();
      if (paramByte == 1) {
        paramContext.put("CACHE_SDK_MSG_ID", Long.valueOf(paramLong));
      }
      for (;;)
      {
        paramContext.put("View_fdes", JsonUtil.getValueFromJsonObject(paramMap, "View_fdes"));
        if (paramBoolean) {
          ParseRichBubbleManager.addEffectiveBubbleData(paramString2, paramString1, paramMap);
        }
        paramContext.put("BUBBLE_JSON_RESULT", paramMap);
        paramContext.put("parseStatu", Integer.valueOf(1));
        q.a();
        return paramContext;
        b = 0;
        break;
        label487:
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        try
        {
          paramMap.put((String)localEntry.getKey(), localEntry.getValue());
        }
        catch (JSONException paramContext)
        {
          paramContext.printStackTrace();
          q.a();
          return null;
        }
        paramContext.put("CACHE_SDK_MSG_RESULT", paramMap.toString());
      }
      for (;;)
      {
        label563:
        label842:
        label873:
        LogManager.e("xiaoyuan_test", "valueMap res faild.msgId: " + paramString1, null);
        paramByte = b;
        continue;
        label966:
        q.a();
      }
      label972:
      paramLong = 0L;
    }
  }
  
  /* Error */
  public static Map<String, Object> parseMsgToPopupWindow(Context paramContext, String paramString1, String paramString2, Map<String, Object> paramMap)
  {
    // Byte code:
    //   0: aload_3
    //   1: ifnull +706 -> 707
    //   4: aload_3
    //   5: ldc_w 450
    //   8: invokeinterface 163 2 0
    //   13: checkcast 23	java/lang/String
    //   16: astore 7
    //   18: aload 7
    //   20: invokestatic 19	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   23: ifne +673 -> 696
    //   26: iconst_0
    //   27: putstatic 615	cn/com/xy/sms/sdk/constant/Constant:popupDefault	Z
    //   30: aload 7
    //   32: iconst_0
    //   33: invokestatic 621	cn/com/xy/sms/sdk/util/SceneconfigUtil:getSceneRule	(Ljava/lang/String;I)Lcn/com/xy/sms/sdk/db/entity/SceneRule;
    //   36: astore 5
    //   38: new 110	java/lang/StringBuilder
    //   41: dup
    //   42: ldc_w 623
    //   45: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: aload 5
    //   50: invokevirtual 626	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: getstatic 629	cn/com/xy/sms/sdk/constant/Constant:Test	Z
    //   57: ifeq +353 -> 410
    //   60: invokestatic 404	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   63: pop
    //   64: aload 7
    //   66: invokestatic 632	cn/com/xy/sms/sdk/b/a:b	(Ljava/lang/String;)Ljava/util/HashMap;
    //   69: astore 6
    //   71: new 110	java/lang/StringBuilder
    //   74: dup
    //   75: ldc_w 634
    //   78: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   81: aload 6
    //   83: invokevirtual 626	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload 6
    //   89: astore 5
    //   91: aload 6
    //   93: ifnonnull +41 -> 134
    //   96: new 110	java/lang/StringBuilder
    //   99: dup
    //   100: ldc_w 636
    //   103: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   106: getstatic 629	cn/com/xy/sms/sdk/constant/Constant:Test	Z
    //   109: invokevirtual 639	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   112: ldc_w 641
    //   115: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: invokestatic 404	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   122: pop
    //   123: aload 7
    //   125: invokestatic 409	cn/com/xy/sms/sdk/b/a:a	(Ljava/lang/String;)Ljava/util/HashMap;
    //   128: astore 5
    //   130: iconst_1
    //   131: putstatic 615	cn/com/xy/sms/sdk/constant/Constant:popupDefault	Z
    //   134: new 110	java/lang/StringBuilder
    //   137: dup
    //   138: ldc_w 643
    //   141: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   144: aload 5
    //   146: invokevirtual 626	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   149: pop
    //   150: aload 5
    //   152: ifnull +533 -> 685
    //   155: new 136	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage
    //   158: dup
    //   159: invokespecial 137	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:<init>	()V
    //   162: astore 6
    //   164: getstatic 615	cn/com/xy/sms/sdk/constant/Constant:popupDefault	Z
    //   167: ifeq +22 -> 189
    //   170: invokestatic 395	cn/com/xy/sms/sdk/constant/Constant:isDefaultImageExist	()Z
    //   173: ifeq +341 -> 514
    //   176: aload_3
    //   177: ldc_w 645
    //   180: ldc_w 647
    //   183: invokeinterface 186 3 0
    //   188: pop
    //   189: aload 6
    //   191: aload_3
    //   192: putfield 651	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:valueMap	Ljava/util/Map;
    //   195: aload 6
    //   197: getfield 654	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:extendParamMap	Ljava/util/HashMap;
    //   200: ifnonnull +15 -> 215
    //   203: aload 6
    //   205: new 203	java/util/HashMap
    //   208: dup
    //   209: invokespecial 204	java/util/HashMap:<init>	()V
    //   212: putfield 654	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:extendParamMap	Ljava/util/HashMap;
    //   215: aload 6
    //   217: getfield 654	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:extendParamMap	Ljava/util/HashMap;
    //   220: aload_3
    //   221: invokevirtual 655	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   224: aload 6
    //   226: aload_1
    //   227: invokevirtual 140	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:setOriginatingAddress	(Ljava/lang/String;)V
    //   230: aload 6
    //   232: aload_2
    //   233: invokevirtual 143	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:setMessageBody	(Ljava/lang/String;)V
    //   236: aload 6
    //   238: iconst_1
    //   239: putfield 147	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:isBgVis	Z
    //   242: aload 6
    //   244: aload 5
    //   246: putfield 413	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:imagePathMap	Ljava/util/HashMap;
    //   249: aload 6
    //   251: aload 7
    //   253: invokevirtual 658	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:setTitleNo	(Ljava/lang/String;)V
    //   256: aload_3
    //   257: ldc -83
    //   259: invokeinterface 163 2 0
    //   264: checkcast 23	java/lang/String
    //   267: astore_1
    //   268: aload_1
    //   269: invokestatic 19	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   272: istore 4
    //   274: iload 4
    //   276: ifne +15 -> 291
    //   279: aload 6
    //   281: aload_1
    //   282: invokestatic 661	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   285: invokevirtual 664	java/lang/Integer:intValue	()I
    //   288: putfield 667	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:simIndex	I
    //   291: aload_3
    //   292: ldc -79
    //   294: invokeinterface 163 2 0
    //   299: checkcast 23	java/lang/String
    //   302: astore_1
    //   303: aload_1
    //   304: invokestatic 19	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   307: istore 4
    //   309: iload 4
    //   311: ifne +15 -> 326
    //   314: aload 6
    //   316: aload_1
    //   317: invokestatic 670	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   320: invokevirtual 673	java/lang/Long:longValue	()J
    //   323: putfield 676	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:msgTime	J
    //   326: aload 6
    //   328: aload_3
    //   329: ldc -81
    //   331: invokeinterface 163 2 0
    //   336: checkcast 23	java/lang/String
    //   339: putfield 679	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:simName	Ljava/lang/String;
    //   342: aload_3
    //   343: aload 7
    //   345: invokestatic 683	cn/com/xy/sms/sdk/dex/DexUtil:handerValueMap	(Ljava/util/Map;Ljava/lang/String;)Ljava/util/Map;
    //   348: pop
    //   349: aload 6
    //   351: aload_3
    //   352: aload 7
    //   354: invokestatic 685	cn/com/xy/sms/sdk/util/PopupUtil:isUseDefaultPopup	(Lcn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage;Ljava/util/Map;Ljava/lang/String;)V
    //   357: aload_3
    //   358: aload 7
    //   360: invokestatic 464	cn/com/xy/sms/sdk/util/PopupUtil:isPopupAble	(Ljava/util/Map;Ljava/lang/String;)Z
    //   363: ifne +242 -> 605
    //   366: aload 6
    //   368: aload_3
    //   369: aload 7
    //   371: invokestatic 687	cn/com/xy/sms/sdk/util/PopupUtil:popupDefault	(Lcn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage;Ljava/util/Map;Ljava/lang/String;)V
    //   374: invokestatic 155	cn/com/xy/sms/sdk/ui/popu/util/ViewUtil:getChannelType	()I
    //   377: iconst_5
    //   378: if_icmpne +163 -> 541
    //   381: getstatic 615	cn/com/xy/sms/sdk/constant/Constant:popupDefault	Z
    //   384: ifne +15 -> 399
    //   387: aload_3
    //   388: ldc_w 397
    //   391: invokeinterface 400 2 0
    //   396: ifeq +145 -> 541
    //   399: iconst_0
    //   400: iconst_1
    //   401: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   404: astore_0
    //   405: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   408: aload_0
    //   409: areturn
    //   410: aload 5
    //   412: ifnull +78 -> 490
    //   415: invokestatic 404	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   418: pop
    //   419: aload 7
    //   421: aload 5
    //   423: getfield 694	cn/com/xy/sms/sdk/db/entity/SceneRule:Scene_page_config	Ljava/lang/String;
    //   426: invokestatic 697	cn/com/xy/sms/sdk/b/a:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/util/HashMap;
    //   429: astore 6
    //   431: aload 6
    //   433: astore 5
    //   435: aload 6
    //   437: ifnonnull -303 -> 134
    //   440: invokestatic 404	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   443: pop
    //   444: aload 7
    //   446: invokestatic 409	cn/com/xy/sms/sdk/b/a:a	(Ljava/lang/String;)Ljava/util/HashMap;
    //   449: astore 5
    //   451: iconst_1
    //   452: putstatic 615	cn/com/xy/sms/sdk/constant/Constant:popupDefault	Z
    //   455: goto -321 -> 134
    //   458: astore_0
    //   459: aload_0
    //   460: invokevirtual 237	java/lang/Throwable:printStackTrace	()V
    //   463: new 110	java/lang/StringBuilder
    //   466: dup
    //   467: ldc_w 699
    //   470: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   473: aload_0
    //   474: invokevirtual 702	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   477: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   480: pop
    //   481: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   484: iconst_0
    //   485: iconst_1
    //   486: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   489: areturn
    //   490: invokestatic 404	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   493: pop
    //   494: aload 7
    //   496: invokestatic 409	cn/com/xy/sms/sdk/b/a:a	(Ljava/lang/String;)Ljava/util/HashMap;
    //   499: astore 5
    //   501: iconst_1
    //   502: putstatic 615	cn/com/xy/sms/sdk/constant/Constant:popupDefault	Z
    //   505: goto -371 -> 134
    //   508: astore_0
    //   509: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   512: aload_0
    //   513: athrow
    //   514: iconst_0
    //   515: iconst_0
    //   516: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   519: astore_0
    //   520: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   523: aload_0
    //   524: areturn
    //   525: astore_1
    //   526: aload_1
    //   527: invokevirtual 237	java/lang/Throwable:printStackTrace	()V
    //   530: goto -239 -> 291
    //   533: astore_1
    //   534: aload_1
    //   535: invokevirtual 237	java/lang/Throwable:printStackTrace	()V
    //   538: goto -212 -> 326
    //   541: aload_3
    //   542: aload 7
    //   544: invokestatic 464	cn/com/xy/sms/sdk/util/PopupUtil:isPopupAble	(Ljava/util/Map;Ljava/lang/String;)Z
    //   547: ifne +14 -> 561
    //   550: iconst_0
    //   551: iconst_1
    //   552: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   555: astore_0
    //   556: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   559: aload_0
    //   560: areturn
    //   561: new 704	cn/com/xy/sms/sdk/queue/i
    //   564: dup
    //   565: iconst_4
    //   566: iconst_2
    //   567: anewarray 23	java/lang/String
    //   570: dup
    //   571: iconst_0
    //   572: ldc_w 706
    //   575: aastore
    //   576: dup
    //   577: iconst_1
    //   578: aload 7
    //   580: aastore
    //   581: invokespecial 709	cn/com/xy/sms/sdk/queue/i:<init>	(I[Ljava/lang/String;)V
    //   584: invokestatic 714	cn/com/xy/sms/sdk/queue/g:a	(Lcn/com/xy/sms/sdk/queue/i;)V
    //   587: aload_0
    //   588: aload 6
    //   590: aload_3
    //   591: invokestatic 194	cn/com/xy/sms/sdk/util/PopupUtil:startBusinessReceiveSmsActivity	(Landroid/content/Context;Lcn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage;Ljava/util/Map;)V
    //   594: iconst_1
    //   595: iconst_1
    //   596: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   599: astore_0
    //   600: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   603: aload_0
    //   604: areturn
    //   605: invokestatic 155	cn/com/xy/sms/sdk/ui/popu/util/ViewUtil:getChannelType	()I
    //   608: iconst_5
    //   609: if_icmpne +32 -> 641
    //   612: getstatic 615	cn/com/xy/sms/sdk/constant/Constant:popupDefault	Z
    //   615: ifne +15 -> 630
    //   618: aload_3
    //   619: ldc_w 397
    //   622: invokeinterface 400 2 0
    //   627: ifeq +14 -> 641
    //   630: iconst_0
    //   631: iconst_1
    //   632: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   635: astore_0
    //   636: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   639: aload_0
    //   640: areturn
    //   641: new 704	cn/com/xy/sms/sdk/queue/i
    //   644: dup
    //   645: iconst_4
    //   646: iconst_2
    //   647: anewarray 23	java/lang/String
    //   650: dup
    //   651: iconst_0
    //   652: ldc_w 706
    //   655: aastore
    //   656: dup
    //   657: iconst_1
    //   658: aload 7
    //   660: aastore
    //   661: invokespecial 709	cn/com/xy/sms/sdk/queue/i:<init>	(I[Ljava/lang/String;)V
    //   664: invokestatic 714	cn/com/xy/sms/sdk/queue/g:a	(Lcn/com/xy/sms/sdk/queue/i;)V
    //   667: aload_0
    //   668: aload 6
    //   670: aload_3
    //   671: invokestatic 194	cn/com/xy/sms/sdk/util/PopupUtil:startBusinessReceiveSmsActivity	(Landroid/content/Context;Lcn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage;Ljava/util/Map;)V
    //   674: iconst_1
    //   675: iconst_1
    //   676: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   679: astore_0
    //   680: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   683: aload_0
    //   684: areturn
    //   685: iconst_0
    //   686: iconst_1
    //   687: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   690: astore_0
    //   691: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   694: aload_0
    //   695: areturn
    //   696: iconst_0
    //   697: iconst_1
    //   698: invokestatic 689	cn/com/xy/sms/sdk/util/PopupUtil:getResultMap	(ZZ)Ljava/util/Map;
    //   701: astore_0
    //   702: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   705: aload_0
    //   706: areturn
    //   707: invokestatic 426	cn/com/xy/sms/sdk/util/q:a	()V
    //   710: goto -226 -> 484
    //   713: astore 8
    //   715: goto -491 -> 224
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	718	0	paramContext	Context
    //   0	718	1	paramString1	String
    //   0	718	2	paramString2	String
    //   0	718	3	paramMap	Map<String, Object>
    //   272	38	4	bool	boolean
    //   36	464	5	localObject1	Object
    //   69	600	6	localObject2	Object
    //   16	643	7	str	String
    //   713	1	8	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   4	87	458	java/lang/Throwable
    //   96	134	458	java/lang/Throwable
    //   134	150	458	java/lang/Throwable
    //   155	189	458	java/lang/Throwable
    //   189	195	458	java/lang/Throwable
    //   224	274	458	java/lang/Throwable
    //   291	309	458	java/lang/Throwable
    //   326	399	458	java/lang/Throwable
    //   399	405	458	java/lang/Throwable
    //   415	431	458	java/lang/Throwable
    //   440	455	458	java/lang/Throwable
    //   490	505	458	java/lang/Throwable
    //   514	520	458	java/lang/Throwable
    //   526	530	458	java/lang/Throwable
    //   534	538	458	java/lang/Throwable
    //   541	556	458	java/lang/Throwable
    //   561	600	458	java/lang/Throwable
    //   605	630	458	java/lang/Throwable
    //   630	636	458	java/lang/Throwable
    //   641	680	458	java/lang/Throwable
    //   685	691	458	java/lang/Throwable
    //   696	702	458	java/lang/Throwable
    //   4	87	508	finally
    //   96	134	508	finally
    //   134	150	508	finally
    //   155	189	508	finally
    //   189	195	508	finally
    //   195	215	508	finally
    //   215	224	508	finally
    //   224	274	508	finally
    //   279	291	508	finally
    //   291	309	508	finally
    //   314	326	508	finally
    //   326	399	508	finally
    //   399	405	508	finally
    //   415	431	508	finally
    //   440	455	508	finally
    //   459	481	508	finally
    //   490	505	508	finally
    //   514	520	508	finally
    //   526	530	508	finally
    //   534	538	508	finally
    //   541	556	508	finally
    //   561	600	508	finally
    //   605	630	508	finally
    //   630	636	508	finally
    //   641	680	508	finally
    //   685	691	508	finally
    //   696	702	508	finally
    //   279	291	525	java/lang/Throwable
    //   314	326	533	java/lang/Throwable
    //   195	215	713	java/lang/Throwable
    //   215	224	713	java/lang/Throwable
  }
  
  public static Map<String, Object> parseMsgToSimpleBubbleResult(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, byte paramByte, Map<String, Object> paramMap, boolean paramBoolean)
  {
    if (paramMap == null) {
      return null;
    }
    paramString3 = (String)paramMap.get("ADACTION");
    if (paramByte == 1)
    {
      long l = -1L;
      paramString4 = MatchCacheManager.getMD5(paramString2, paramString4);
      if (!StringUtils.isNull(paramString4))
      {
        paramMap = StringUtils.getPhoneNumberNo86(paramString2);
        if (paramString3 == null)
        {
          paramContext = "";
          l = MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, new String[] { "msg_num_md5", paramString4, "phonenum", paramMap, "msg_id", paramString1, "session_reuslt", paramContext, "save_time", String.valueOf(paramLong), "session_lasttime", String.valueOf(System.currentTimeMillis()) }), 1);
        }
      }
      else
      {
        if (paramString3 == null) {
          break label274;
        }
        paramContext = new HashMap();
        paramContext.put("CACHE_SDK_MSG_ID", Long.valueOf(l));
        paramContext.put("CACHE_SDK_MSG_SIMPLE_RESULT", paramString3);
      }
    }
    for (;;)
    {
      label191:
      if ((!paramBoolean) || (!ParseBubbleManager.equalPhoneNum(paramString2)) || (paramString3 != null)) {}
      try
      {
        ParseBubbleManager.addEffectiveBubbleData(paramString2, paramString1, new JSONArray(paramString3));
        for (;;)
        {
          return paramContext;
          paramContext = paramString3;
          break;
          if (paramString3 == null) {
            break label274;
          }
          paramContext = new HashMap();
          paramContext.put("CACHE_SDK_MSG_SIMPLE_RESULT", paramString3);
          break label191;
          ParseBubbleManager.addInvalidBubbleData(paramString2, paramString1);
        }
      }
      catch (Throwable paramString3)
      {
        for (;;)
        {
          ParseBubbleManager.addInvalidBubbleData(paramString2, paramString1);
          paramString3.printStackTrace();
        }
      }
      label274:
      paramContext = null;
    }
  }
  
  public static void popupDefault(BusinessSmsMessage paramBusinessSmsMessage, Map<String, Object> paramMap, String paramString)
  {
    if ((paramBusinessSmsMessage != null) && (paramMap != null)) {
      for (;;)
      {
        try
        {
          paramMap.put("view_forceToDefault_popup", "true");
          if ((ViewUtil.getChannelType() == 2) || (ViewUtil.getChannelType() == 8))
          {
            paramMap.put("View_fdes", "H103102;B502513,10236113;F904");
            paramMap.put("view_title_name", paramMap.get("title_name"));
            paramMap.put("View_viewid", "001");
            if (!Constant.isDefaultImageExist()) {
              break;
            }
            Constant.getContext();
            paramMap = cn.com.xy.sms.sdk.b.a.a(paramString);
            if (paramMap == null) {
              break;
            }
            imagePathMap = paramMap;
            return;
          }
          if (ViewUtil.getChannelType() == 5) {
            paramMap.put("View_fdes", "H113;B502,10340013;F908906");
          } else {
            paramMap.put("View_fdes", "H101;B502,11125213;F901");
          }
        }
        catch (Throwable paramBusinessSmsMessage)
        {
          paramBusinessSmsMessage.printStackTrace();
          return;
        }
      }
    }
  }
  
  public static void startBusinessReceiveSmsActivity(Context paramContext, BusinessSmsMessage paramBusinessSmsMessage, Map<String, Object> paramMap)
  {
    try
    {
      new StringBuilder("可以弹窗了").append(System.currentTimeMillis());
      if (paramBusinessSmsMessage != null)
      {
        valueMap = paramMap;
        PopupMsgManager.businessSmsList.addLast(paramBusinessSmsMessage);
      }
      for (;;)
      {
        paramBusinessSmsMessage = new Intent();
        paramBusinessSmsMessage.setClassName(paramContext, "cn.com.xy.sms.sdk.ui.popu.BusinessReceiveSmsActivity");
        paramBusinessSmsMessage.setFlags(268435456);
        paramContext.startActivity(paramBusinessSmsMessage);
        return;
        PopupMsgManager.addThirdPopupMsgData(paramMap);
      }
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean startWebActivity(Context paramContext, JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("actionType", paramString1);
      if (paramJSONObject != null) {
        localIntent.putExtra("JSONDATA", paramJSONObject.toString());
      }
      if (!StringUtils.isNull(KeyManager.channel)) {
        localIntent.putExtra("channel", KeyManager.channel);
      }
      if (!StringUtils.isNull(NetUtil.APPVERSION)) {
        localIntent.putExtra("appVersion", NetUtil.APPVERSION);
      }
      localIntent.setClassName(paramContext, "cn.com.xy.sms.sdk.ui.popu.web.SdkWebActivity");
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      if (!StringUtils.isNull(paramString2)) {
        ParseManager.clearHistorySmsByNum(paramContext, paramString2, null);
      }
      return true;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.PopupUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */