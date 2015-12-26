package cn.com.xy.sms.sdk.iccid;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IccidLocationUtil
{
  private static final String a = "iccid";
  private static long b;
  private static HashMap<String, String[]> c = new HashMap();
  private static boolean d = false;
  public static final ExecutorService iccidPool = Executors.newFixedThreadPool(1);
  public static final ExecutorService pubInfoPool = Executors.newFixedThreadPool(2);
  
  private static IccidInfo a(IccidInfo paramIccidInfo, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!paramBoolean1) {
      return paramIccidInfo;
    }
    Object localObject2 = "";
    String str;
    if (paramIccidInfo != null)
    {
      localObject1 = localObject2;
      if (!StringUtils.isNull(cnum)) {
        if ((!"10000".equals(num.trim())) && (!"10010".equals(num.trim())))
        {
          localObject1 = localObject2;
          if (!"10086".equals(num.trim())) {}
        }
        else
        {
          localObject1 = cnum;
        }
      }
      localObject2 = iccid;
      str = num;
      paramIccidInfo = (IccidInfo)localObject2;
      localObject2 = localObject1;
    }
    for (Object localObject1 = str;; localObject1 = null)
    {
      queryIccid((String)localObject2, paramIccidInfo, (String)localObject1, paramBoolean2, true);
      return null;
      paramIccidInfo = null;
      localObject2 = "";
    }
  }
  
  private static String a(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    changeIccidAreaCode(false);
    String[] arrayOfString = (String[])c.get(paramString1);
    if (arrayOfString != null)
    {
      if ((StringUtils.isNull(arrayOfString[0])) && ((paramBoolean2) || (System.currentTimeMillis() - Long.valueOf(arrayOfString[1]).longValue() > DexUtil.getUpdateCycleByType(4, 30000L))))
      {
        arrayOfString[1] = String.valueOf(System.currentTimeMillis());
        c.put(paramString1, arrayOfString);
        queryIccid(null, paramString1, null, paramBoolean1, false);
        paramString1 = ((String[])c.get(paramString1))[0];
        paramString2 = paramString1;
        if (StringUtils.isNull(paramString1)) {
          paramString2 = "CN";
        }
        return paramString2;
      }
      if (!StringUtils.isNull(arrayOfString[0])) {
        return arrayOfString[0];
      }
    }
    paramString1 = c.entrySet();
    int j = getOperatorByNum(paramString2);
    paramString2 = paramString1.iterator();
    int i = 0;
    paramString1 = null;
    label155:
    label401:
    for (;;)
    {
      if (!paramString2.hasNext())
      {
        paramString2 = paramString1;
        if (!StringUtils.isNull(paramString1)) {
          break;
        }
        return "CN";
      }
      Object localObject = (Map.Entry)paramString2.next();
      arrayOfString = (String[])((Map.Entry)localObject).getValue();
      if ((StringUtils.isNull(arrayOfString[0])) && ((paramBoolean2) || (System.currentTimeMillis() - Long.valueOf(arrayOfString[1]).longValue() > DexUtil.getUpdateCycleByType(4, 30000L))))
      {
        localObject = (String)((Map.Entry)localObject).getKey();
        arrayOfString[1] = String.valueOf(System.currentTimeMillis());
        c.put(localObject, arrayOfString);
        queryIccid(null, (String)localObject, null, paramBoolean1, false);
        arrayOfString = (String[])c.get(localObject);
      }
      for (;;)
      {
        if (paramString1 != null)
        {
          if (paramString1.equals(arrayOfString[0]))
          {
            paramString2 = paramString1;
            if (i != 0) {
              break;
            }
          }
          if (Integer.valueOf(arrayOfString[2]).intValue() == j) {}
          for (j = 1; (i != 0) && (j != 0); j = 0) {
            return "CN";
          }
          paramString2 = paramString1;
          if (i != 0) {
            break;
          }
          if (j != 0) {
            return arrayOfString[0];
          }
          return "CN";
        }
        paramString1 = arrayOfString[0];
        if (Integer.valueOf(arrayOfString[2]).intValue() != j) {
          break label401;
        }
        i = 1;
        break label155;
      }
    }
  }
  
  static void a(Context paramContext, IccidInfo paramIccidInfo, boolean paramBoolean)
  {
    Object localObject = null;
    long l;
    boolean bool;
    if ((paramIccidInfo != null) && (!StringUtils.isNull(iccid)))
    {
      l = DexUtil.getUpdateCycleByType(16, 4838400000L);
      if ((paramIccidInfo == null) || (StringUtils.isNull(provinces)) || (!provinces.equals("未知")) || (updateTime + l >= System.currentTimeMillis())) {
        break label160;
      }
      bool = true;
    }
    for (;;)
    {
      new StringBuilder("loadIccidLocate-------: ").append(areaCode).append(" ").append(provinces).append(" ").append(operator).append(" ").append(city).append(" locateEnable: ").append(bool);
      new StringBuilder("info.updateTime < System.currentTimeMillis()-weekTime: ").append(System.currentTimeMillis() - l - updateTime);
      if (bool) {
        break;
      }
      return;
      label160:
      if (((StringUtils.isNull(provinces)) && (updateTime == 0L)) || ((!StringUtils.isNull(provinces)) && (updateTime < System.currentTimeMillis() - l))) {
        bool = true;
      } else {
        bool = false;
      }
    }
    String str = "";
    if (paramIccidInfo != null)
    {
      paramContext = str;
      if (!StringUtils.isNull(cnum)) {
        if ((!"10000".equals(num.trim())) && (!"10010".equals(num.trim())))
        {
          paramContext = str;
          if (!"10086".equals(num.trim())) {}
        }
        else
        {
          paramContext = cnum;
        }
      }
      localObject = iccid;
      str = num;
      paramIccidInfo = (IccidInfo)localObject;
      localObject = paramContext;
      paramContext = str;
    }
    for (;;)
    {
      queryIccid((String)localObject, paramIccidInfo, paramContext, paramBoolean, true);
      return;
      str = "";
      paramContext = null;
      paramIccidInfo = (IccidInfo)localObject;
      localObject = str;
    }
  }
  
  private static void a(String paramString)
  {
    IccidInfo localIccidInfo = IccidInfoManager.queryIccidInfo(paramString, Constant.getContext());
    if (!StringUtils.isNull(paramString))
    {
      if (localIccidInfo != null) {
        break label52;
      }
      queryIccid(null, paramString, null, true, true);
    }
    while (localIccidInfo != null)
    {
      putIccidAreaCodeToCache(paramString, areaCode, operator, userAreacode, userOperator);
      return;
      label52:
      iccidPool.execute(new a(localIccidInfo));
    }
    putIccidAreaCodeToCache(paramString, null, null, null, null);
  }
  
  private static void a(String paramString, IccidInfo paramIccidInfo)
  {
    if (StringUtils.isNull(paramString)) {
      return;
    }
    if (paramIccidInfo == null)
    {
      queryIccid(null, paramString, null, true, true);
      return;
    }
    iccidPool.execute(new a(paramIccidInfo));
  }
  
  private static void b(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    try
    {
      NetUtil.requestTokenIfNeed(paramString2);
      b localb = new b(paramString2, paramString1);
      NetUtil.executeAllNetHttpRequest(i.a(paramString1, paramString2, paramString3), "990005", localb, paramBoolean, false, "location", false);
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void changeIccidAreaCode(boolean paramBoolean)
  {
    if ((paramBoolean) || (!d))
    {
      d = true;
      c.clear();
      String str1 = DuoquUtils.getSdkDoAction().getIccidBySimIndex(0);
      String str2 = DuoquUtils.getSdkDoAction().getIccidBySimIndex(1);
      if (!StringUtils.isNull(str1)) {
        a(str1);
      }
      if (!StringUtils.isNull(str2)) {
        a(str2);
      }
    }
  }
  
  public static String getAreaCodeByCnumOrIccid(String paramString1, int paramInt, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      String str1 = getUserAreaCode(paramString2, paramInt);
      if (!StringUtils.isNull(str1)) {
        return str1;
      }
      str1 = null;
      String str2 = StringUtils.getPhoneNumberNo86(paramString1);
      paramString1 = str1;
      if (!StringUtils.isNull(str2)) {
        paramString1 = cn.com.xy.sms.sdk.db.entity.a.a.a(str2);
      }
      if ((paramString1 != null) && (!StringUtils.isNull(c))) {
        return c;
      }
      if ((!StringUtils.isNull(str2)) && ((paramString1 == null) || (g == 0L) || (System.currentTimeMillis() - DexUtil.getUpdateCycleByType(0, 7776000000L) > g))) {
        queryIccid(str2, paramString2, paramString3, paramBoolean1, true);
      }
      paramString1 = a(paramString2, paramString3, paramBoolean1, paramBoolean2);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return "CN";
  }
  
  public static String getICCID(Context paramContext)
  {
    IccidInfo localIccidInfo = IccidInfoManager.queryDeftIccidInfo(paramContext);
    if ((localIccidInfo != null) && (!StringUtils.isNull(iccid))) {
      return iccid;
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (!StringUtils.isNull(paramContext.getSimSerialNumber())) {
      return paramContext.getSimSerialNumber();
    }
    return "";
  }
  
  public static HashMap<String, String[]> getIccidAreaCodeMap()
  {
    return c;
  }
  
  public static String getIccidAreaCodeMapValueByIndex(String paramString, int paramInt)
  {
    if ((StringUtils.isNull(paramString)) || (c == null) || (c.isEmpty())) {
      return null;
    }
    paramString = (String[])c.get(paramString);
    if ((paramString != null) && (paramString.length > paramInt)) {
      return paramString[paramInt];
    }
    return null;
  }
  
  public static String[] getIccidInfoArr(String paramString)
  {
    return (String[])c.get(paramString);
  }
  
  public static int getOperatorByICCID(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 6))
    {
      paramString = paramString.substring(4, 6);
      if ((paramString.equals("00")) || (paramString.equals("02")) || (paramString.equals("05")) || (paramString.equals("08"))) {
        return 1;
      }
      if (paramString.equals("01")) {
        return 2;
      }
      if (paramString.equals("03")) {
        return 3;
      }
    }
    return -2;
  }
  
  public static int getOperatorByNum(String paramString)
  {
    int i = -1;
    if ("10086,1008611,1008601".indexOf(paramString) != -1) {
      i = 1;
    }
    do
    {
      return i;
      if ("10010,10011".indexOf(paramString) != -1) {
        return 2;
      }
    } while ("10000,10001".indexOf(paramString) == -1);
    return 3;
  }
  
  public static String getOperatorNum(String paramString1, String paramString2)
  {
    if (StringUtils.isNull(paramString2)) {
      return String.valueOf(getOperatorByICCID(paramString1));
    }
    if ("移动".equals(paramString2)) {
      return "1";
    }
    if ("联通".equals(paramString2)) {
      return "2";
    }
    if ("电信".equals(paramString2)) {
      return "3";
    }
    return "-2";
  }
  
  public static String getProvince()
  {
    IccidInfo localIccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
    if (localIccidInfo != null) {
      return provinces;
    }
    return "";
  }
  
  public static String getUserAreaCode(String paramString, int paramInt)
  {
    paramString = getIccidAreaCodeMapValueByIndex(paramString, 3);
    if (!StringUtils.isNull(paramString)) {
      return paramString;
    }
    if (paramInt >= 0)
    {
      paramString = IccidInfoManager.queryIccidInfo(null, paramInt);
      if ((paramString != null) && (!StringUtils.isNull(userAreacode)))
      {
        new StringBuilder("通过卡位获取用户设置的区域编码 areaCode=").append(userAreacode);
        return userAreacode;
      }
    }
    return null;
  }
  
  public static int getUserOperatorNum(String[] paramArrayOfString)
  {
    int j = -2;
    int i = j;
    if (paramArrayOfString.length > 4)
    {
      i = j;
      if (StringUtils.isNull(paramArrayOfString[4])) {}
    }
    try
    {
      i = Integer.parseInt(paramArrayOfString[4]);
      return i;
    }
    catch (Throwable paramArrayOfString) {}
    return -2;
  }
  
  public static void putIccidAreaCodeToCache(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (paramString1 == null) {
      return;
    }
    String[] arrayOfString = (String[])c.get(paramString1);
    if (arrayOfString == null)
    {
      arrayOfString = new String[5];
      arrayOfString[0] = paramString2;
      arrayOfString[1] = String.valueOf(System.currentTimeMillis());
      arrayOfString[2] = getOperatorNum(paramString1, paramString3);
      arrayOfString[3] = paramString4;
      arrayOfString[4] = getOperatorNum(null, paramString5);
    }
    for (paramString2 = arrayOfString;; paramString2 = arrayOfString)
    {
      c.put(paramString1, paramString2);
      return;
      arrayOfString[0] = paramString2;
      arrayOfString[1] = String.valueOf(System.currentTimeMillis());
      arrayOfString[2] = getOperatorNum(paramString1, paramString3);
      arrayOfString[3] = paramString4;
      arrayOfString[4] = getOperatorNum(null, paramString5);
    }
  }
  
  public static void queryIccid(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!NetUtil.isEnhance()) {
      return;
    }
    if (!paramBoolean1)
    {
      b(paramString1, paramString2, paramString3, false);
      return;
    }
    iccidPool.execute(new c(paramString1, paramString2, paramString3));
  }
  
  public static void startQueryIccidLocation(HashMap<String, String> paramHashMap, boolean paramBoolean)
  {
    String str2;
    Object localObject;
    String str1;
    if (paramHashMap != null)
    {
      str2 = (String)paramHashMap.get("simIccid");
      localObject = (String)paramHashMap.get("receiveNum");
      str1 = (String)paramHashMap.get("centerNum");
      paramHashMap.get("sceneId");
      paramHashMap.get("sms");
      paramHashMap.get("smsLocate");
      if (str2 != null) {
        break label135;
      }
      paramHashMap = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
      if (paramHashMap != null) {
        break label147;
      }
      paramHashMap = new IccidInfo();
      cnum = str1;
      iccid = str2;
    }
    for (num = ((String)localObject);; num = ((String)localObject))
    {
      IccidInfoManager.updateIccidCnum(iccid, str1, (String)localObject, Constant.getContext());
      localObject = Constant.getContext();
      SysParamEntityManager.getBooleanParam(Constant.getContext(), "SMSLOCATEENABLE");
      a((Context)localObject, paramHashMap, paramBoolean);
      return;
      label135:
      paramHashMap = IccidInfoManager.queryIccidInfo(str2, Constant.getContext());
      break;
      label147:
      if ((str1 != null) && (str1.length() > 0) && (cnum == null)) {
        cnum = str1;
      }
      if ((localObject == null) || (((String)localObject).length() <= 0) || (num != null)) {}
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.iccid.IccidLocationUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */