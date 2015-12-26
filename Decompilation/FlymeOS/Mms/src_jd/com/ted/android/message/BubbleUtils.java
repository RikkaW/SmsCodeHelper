package com.ted.android.message;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.Toast;
import ant;
import asa;
import asb;
import asc;
import ase;
import com.ted.android.utils.TedSDKLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONException;
import org.json.JSONObject;

public class BubbleUtils
{
  public static OpenUrlController a;
  
  public static ArrayList<String> MatchingDigital(String paramString)
  {
    localArrayList = new ArrayList();
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return localArrayList;
      }
      paramString = Pattern.compile("(\\d+)", 106).matcher(paramString);
      while (paramString.find())
      {
        String str = paramString.group();
        if (str.length() > 3) {
          localArrayList.add(str);
        }
      }
      return localArrayList;
    }
    catch (PatternSyntaxException paramString) {}
  }
  
  public static void call(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramString)));
  }
  
  private static long changeDateString2Long(String paramString1, String paramString2, Date paramDate, boolean paramBoolean)
  {
    long l1 = 0L;
    Object localObject = "";
    long l3 = l1;
    long l2;
    if (paramString1 != null) {
      l2 = l1;
    }
    for (;;)
    {
      try
      {
        if (paramString1.length() < 3) {
          return 0L;
        }
        l2 = l1;
        String[] arrayOfString1 = paramString1.split("[^0-9]{1}");
        if (arrayOfString1 == null) {
          break label760;
        }
        l2 = l1;
        if (arrayOfString1.length < 2) {
          break label760;
        }
        l2 = l1;
        if (arrayOfString1.length > 3) {
          break label760;
        }
        l2 = l1;
        if (arrayOfString1.length <= 2) {
          break label786;
        }
        l2 = l1;
        if (arrayOfString1[0].length() == 2)
        {
          l2 = l1;
          paramString1 = "20" + arrayOfString1[0];
          i = 1;
          if (i == 0)
          {
            l2 = l1;
            if (paramDate.getMonth() > 10)
            {
              l2 = l1;
              paramString1 = paramDate.getYear() + 1900 + 1;
              l2 = l1;
              if (arrayOfString1.length != 2) {
                break label754;
              }
              i = 0;
              localObject = arrayOfString1[i];
              paramDate = (Date)localObject;
              l2 = l1;
              if (((String)localObject).length() == 1)
              {
                l2 = l1;
                paramDate = "0" + (String)localObject;
              }
              l2 = l1;
              l3 = l1;
              if (Integer.parseInt(paramDate) > 12) {
                break label774;
              }
              str = arrayOfString1[(i + 1)];
              localObject = str;
              l2 = l1;
              if (str.length() == 1)
              {
                l2 = l1;
                localObject = "0" + str;
              }
              l2 = l1;
              l3 = l1;
              if (Integer.parseInt((String)localObject) > 31) {
                break label774;
              }
              l2 = l1;
              l3 = l1;
              if (((String)localObject).length() == 0) {
                break label774;
              }
              l2 = l1;
              l3 = l1;
              if (paramDate.length() == 0) {
                break label774;
              }
              l2 = l1;
              l3 = l1;
              if (paramString1.length() == 0) {
                break label774;
              }
              l2 = l1;
              l1 = new SimpleDateFormat("yyyyMMdd").parse(paramString1 + paramDate + (String)localObject).getTime();
              str = paramString1;
              paramString1 = (String)localObject;
              localObject = paramDate;
              paramDate = paramString1;
              l3 = l1;
              if (paramString2 == null) {
                break label774;
              }
              l2 = l1;
              l3 = l1;
              if (paramString2.length() < 1) {
                break label774;
              }
              l2 = l1;
              String[] arrayOfString2 = paramString2.split("[^0-9]{1}");
              l3 = l1;
              if (arrayOfString1 == null) {
                break label774;
              }
              l2 = l1;
              l3 = l1;
              if (arrayOfString2.length != 2) {
                break label774;
              }
              paramString2 = arrayOfString2[0];
              paramString1 = paramString2;
              if (paramBoolean)
              {
                l2 = l1;
                i = Integer.parseInt(paramString2);
                paramString1 = paramString2;
                if (i < 12)
                {
                  l2 = l1;
                  paramString1 = i + 12;
                }
              }
              l2 = l1;
              if (paramString1.length() != 1) {
                break label751;
              }
              l2 = l1;
              paramString1 = "0" + paramString1;
              arrayOfString1 = arrayOfString2[1];
              paramString2 = arrayOfString1;
              l2 = l1;
              if (arrayOfString1.length() == 1)
              {
                l2 = l1;
                paramString2 = "0" + arrayOfString1;
              }
              l2 = l1;
              l3 = l1;
              if (paramString1.length() == 0) {
                break label774;
              }
              l2 = l1;
              l3 = l1;
              if (paramString2.length() == 0) {
                break label774;
              }
              l2 = l1;
              return new SimpleDateFormat("yyyyMMddHHmm").parse(str + (String)localObject + paramDate + paramString1 + paramString2).getTime();
            }
          }
        }
        else
        {
          l2 = l1;
          if (arrayOfString1[0].length() != 4) {
            break label777;
          }
          paramString1 = arrayOfString1[0];
          i = 1;
          continue;
        }
        if (i != 0) {
          continue;
        }
        l2 = l1;
        paramString1 = paramDate.getYear() + 1900;
        continue;
        continue;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        return l2;
      }
      label751:
      label754:
      int i = 1;
      continue;
      label760:
      localObject = "";
      String str = "";
      paramDate = "";
      continue;
      label774:
      return l3;
      label777:
      i = 0;
      paramString1 = (String)localObject;
      continue;
      label786:
      i = 0;
      paramString1 = (String)localObject;
    }
  }
  
  private static boolean checkPackage(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static List<String> getAddressFromSmsBodyRegex(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = MessageUtils.removePhoneNumber(MessageUtils.removeUrl(paramString));
    paramString = Pattern.compile("(?<!及|下载|时间|评价)地[点址](?:(?:\\S?\\s*[:： ]\\s*)|(?:[为是在]))((?:(?:[^,，\\.。·●\\*;；!！\\(（\\[【\\)）\\]】<>《》:：、\\-—日\\s厅馆室店旁号]{5,50})(?=$|[,，\\.。·●\\*;；!！\\(（\\[【\\)）\\]】<>《》:：、\\-—日\\s]))|(?:[^,，\\.。·●\\*;；!！\\(（\\[【\\)）\\]】<>《》:：、\\-—日\\s厅馆室店旁号]{4,49}[厅馆室店旁号]))").matcher(paramString);
    for (;;)
    {
      if (!paramString.find()) {
        return localArrayList;
      }
      localArrayList.add(paramString.group(1));
    }
  }
  
  public static boolean getBooleanWithIgnore(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean(paramString);
      return bool;
    }
    catch (JSONException paramJSONObject) {}
    return true;
  }
  
  public static String getCalenderEventUrl()
  {
    String str = "";
    if (TextUtils.isEmpty(""))
    {
      if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
        str = "content://com.android.calendar/events";
      }
    }
    else {
      return str;
    }
    return "content://calendar/events";
  }
  
  public static int getIntWithIgnore(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int i = paramJSONObject.getInt(paramString);
      return i;
    }
    catch (JSONException paramJSONObject) {}
    return 0;
  }
  
  public static String getStringWithIgnore(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString(paramString);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject) {}
    return null;
  }
  
  public static long getTimeLongFromBody(String paramString)
  {
    return getTimeLongFromBody(paramString, System.currentTimeMillis());
  }
  
  public static long getTimeLongFromBody(String paramString, long paramLong)
  {
    long l = System.currentTimeMillis();
    Date localDate = new Date(paramLong);
    Object localObject2 = "";
    String str = "";
    int k = 0;
    int i = 0;
    for (;;)
    {
      Object localObject3;
      try
      {
        localObject3 = Pattern.compile("((\\d{2,4}年)?\\d{1,2}月\\d{1,2}日)", 10).matcher(paramString);
        j = i;
        localObject1 = localObject2;
        if (!((Matcher)localObject3).find()) {
          break label629;
        }
        j = i;
        localObject1 = localObject2;
        if (((Matcher)localObject3).groupCount() < 1) {
          break label629;
        }
        localObject2 = ((Matcher)localObject3).group(0);
        j = i;
        localObject1 = localObject2;
        if (localObject2 == null) {
          break label629;
        }
        j = i;
        localObject1 = localObject2;
        if (((String)localObject2).length() <= 3) {
          break label629;
        }
        localObject1 = ((String)localObject2).split("[^0-9]{1}");
        if (localObject1.length != 3) {
          break label649;
        }
        j = Integer.valueOf(localObject1[0]).intValue();
        if ((localObject1[0].length() == 4) && (j > 2000) && (j <= 2030))
        {
          i = 1;
          break label657;
          if (j != 0) {
            break label643;
          }
          localObject3 = new String[localObject1.length + 1];
          localObject3[0] = (localDate.getYear() + 1900);
          if (localDate.getMonth() > 10)
          {
            localObject3[0] = (localDate.getYear() + 1900 + 1);
            break label665;
            if (i < localObject1.length) {
              break label605;
            }
            j = 1;
            localObject1 = localObject3;
            k = 1;
            i = j;
            if (j != 0)
            {
              i = Integer.valueOf(localObject1[(k + 0)]).intValue();
              j = Integer.valueOf(localObject1[(k + 1)]).intValue();
              if ((i < 1) || (i > 12) || (j < 1) || (j > 31)) {
                break label622;
              }
              i = 1;
            }
            j = i;
            localObject1 = localObject2;
            if (i == 0) {
              break label629;
            }
            localObject3 = Pattern.compile("(\\d{1,2}(点|时|:)\\d{1,2})", 10).matcher(paramString);
            j = i;
            localObject1 = localObject2;
            if (!((Matcher)localObject3).find()) {
              break label629;
            }
            j = i;
            localObject1 = localObject2;
            if (((Matcher)localObject3).groupCount() < 1) {
              break label629;
            }
            localObject1 = ((Matcher)localObject3).group(0);
            boolean bool2 = false;
            bool1 = bool2;
            if (!TextUtils.isEmpty((CharSequence)localObject1))
            {
              j = paramString.indexOf((String)localObject1);
              bool1 = bool2;
              if (j >= 2)
              {
                bool1 = bool2;
                if (j <= paramString.length() - 2)
                {
                  str = paramString.substring(j - 2, j);
                  paramString = paramString.substring(((String)localObject1).length() + j, j + ((String)localObject1).length() + 2);
                  if ((str.equalsIgnoreCase("晚上")) || (str.equalsIgnoreCase("下午"))) {
                    break label670;
                  }
                  bool1 = bool2;
                  if (paramString.equalsIgnoreCase("pm")) {
                    break label670;
                  }
                }
              }
            }
            paramLong = changeDateString2Long((String)localObject2, (String)localObject1, localDate, bool1);
            if ((paramLong <= l) || (i == 0)) {
              break label627;
            }
            return paramLong;
          }
        }
        else
        {
          i = k;
          if (localObject1[0].length() != 2) {
            break label657;
          }
          i = k;
          if (j > 30) {
            break label657;
          }
          i = 1;
          break label657;
        }
        localObject3[0] = (localDate.getYear() + 1900);
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return 0L;
      }
      label605:
      localObject3[(i + 1)] = localObject1[i];
      i += 1;
      continue;
      label622:
      i = 0;
      continue;
      label627:
      return 0L;
      label629:
      localObject2 = localObject1;
      i = j;
      Object localObject1 = str;
      continue;
      label643:
      k = i;
      continue;
      label649:
      int j = 0;
      i = 0;
      continue;
      label657:
      j = i;
      i = 1;
      continue;
      label665:
      i = 0;
      continue;
      label670:
      boolean bool1 = true;
    }
  }
  
  private static boolean isPackageInstalled(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static List<String> matcherEmail(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(paramString);
    for (;;)
    {
      if (!paramString.find()) {
        return localArrayList;
      }
      localArrayList.add(paramString.group());
    }
  }
  
  public static void openApp(Context paramContext, String paramString1, String paramString2)
  {
    new Intent();
    if (checkPackage(paramContext, paramString1))
    {
      if (TextUtils.isEmpty(paramString1)) {
        return;
      }
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString1));
      return;
    }
    openDownlaodDialog(paramContext, paramString2, paramString1);
  }
  
  public static void openApp(Context paramContext, JSONObject paramJSONObject)
  {
    try
    {
      String str2 = paramJSONObject.getString("packageName");
      String str1 = "";
      if (paramJSONObject.has("appName")) {
        str1 = paramJSONObject.getString("appName");
      }
      openApp(paramContext, str2, str1);
      return;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void openDownlaodDialog(Context paramContext, String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(String.format("您尚未安装 %s ", new Object[] { paramString1 }));
    localBuilder.setMessage(String.format("是否立即下载最新版 %s ?", new Object[] { paramString1 }));
    localBuilder.setNegativeButton("取消", new asa());
    localBuilder.setPositiveButton("下载", new asb(paramString2, paramContext));
    localBuilder.show();
  }
  
  public static void openMapAppWithAddress(Context paramContext, String paramString)
  {
    TedSDKLog.d("openMapAppWithAddress", "Address: " + paramString);
    String str = paramString;
    if (!TextUtils.isEmpty(paramString)) {
      str = paramString.replaceAll("[（\\({\\[].*?[\\)}\\]）]", "");
    }
    TedSDKLog.d("openMapAppWithAddress", "Address After replace: " + str);
    try
    {
      paramString = new Intent("android.intent.action.VIEW");
      paramString.setData(Uri.parse("geo:0,0?q=" + str));
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString = new StringBuilder();
      paramString.append("http://map.baidu.com/mobile/webapp/index/index#search/search/qt=s&wd=");
      paramString.append(str);
      paramString.append("&c=301&searchFlag=bigBox&version=5&exptype=dep&src_from=webapp_all_bigbox/vt=map");
      openUrl(paramContext, paramString.toString());
    }
  }
  
  public static void openUrl(Context paramContext, String paramString)
  {
    try
    {
      String str = ant.a(paramString, "0", "0");
      if (a == null)
      {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.VIEW");
        Uri localUri = Uri.parse(str);
        paramString = localUri;
        if (TextUtils.isEmpty(localUri.getScheme())) {
          paramString = Uri.parse("http://" + str);
        }
        localIntent.setData(paramString);
        localIntent.setFlags(268435456);
        paramContext.startActivity(localIntent);
        return;
      }
      a.openUrl(paramContext, str);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static TextMessageParser.BusinessCardItem parseBusinessCard(String paramString, Pattern paramPattern)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = paramPattern.matcher(paramString);
        if (paramString.find())
        {
          paramPattern = new TextMessageParser.BusinessCardItem();
          if (TextUtils.isEmpty(paramString.group(2))) {
            name = paramString.group(6).trim();
          }
          for (phone = paramString.group(8).trim(); (!TextUtils.isEmpty(name)) && (!TextUtils.isEmpty(phone)) && (ase.a(phone)); phone = paramString.group(4).trim())
          {
            paramString = paramPattern;
            if (phone.length() >= 5) {
              return paramString;
            }
            break;
            name = paramString.group(2).trim();
          }
        }
      }
      return null;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    paramString = null;
    return paramString;
  }
  
  public static void remindEvent(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject2 = getCalenderEventUrl();
    Object localObject1 = getAddressFromSmsBodyRegex(paramString1);
    if ((localObject1 != null) && (((List)localObject1).size() > 0)) {
      localObject1 = (String)((List)localObject1).get(0);
    }
    for (;;)
    {
      long l2;
      try
      {
        l2 = getTimeLongFromBody(paramString1);
        if (l2 > 0L)
        {
          if (l2 % 86400000L != 57600000L) {
            break label279;
          }
          bool = true;
          l1 = l2;
          if ((!TextUtils.isEmpty(paramString2)) && (!TextUtils.isDigitsOnly(paramString2)))
          {
            paramString2 = paramString2 + "提醒";
            if (isPackageInstalled("com.android.calendar", paramContext))
            {
              localObject2 = new Intent("android.intent.action.INSERT").setData(Uri.parse((String)localObject2));
              ((Intent)localObject2).putExtra("beginTime", l2).putExtra("endTime", l1).putExtra("title", paramString2).putExtra("description", paramString1).putExtra("hasAlarm", 1).putExtra("minutes", "60").putExtra("allDay", bool).putExtra("eventLocation", (String)localObject1);
              ((Intent)localObject2).addFlags(268435456);
              paramContext.startActivity((Intent)localObject2);
              return;
            }
            localObject2 = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event");
            continue;
          }
        }
        else
        {
          Toast.makeText(paramContext, "提醒时间无效", 0).show();
          return;
        }
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        Toast.makeText(paramContext, "提醒设置失败", 0).show();
        return;
      }
      paramString2 = "短信提醒";
      continue;
      localObject1 = null;
      continue;
      label279:
      boolean bool = false;
      long l1 = 7200000L + l2;
    }
  }
  
  public static void sendMail(Context paramContext, String paramString)
  {
    paramString = matcherEmail(paramString);
    Object localObject;
    if (paramString.size() > 0) {
      localObject = paramString.iterator();
    }
    String str;
    for (paramString = "";; paramString = paramString + str + ";")
    {
      if (!((Iterator)localObject).hasNext())
      {
        paramString = paramString.substring(0, paramString.length() - 1);
        localObject = new Intent("android.intent.action.SENDTO");
        ((Intent)localObject).setType("text/plain");
        ((Intent)localObject).setData(Uri.parse("mailto:" + paramString));
        ((Intent)localObject).putExtra("android.intent.extra.EMAIL", "");
        ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", "");
        ((Intent)localObject).putExtra("android.intent.extra.TEXT", "");
        paramContext.startActivity(Intent.createChooser((Intent)localObject, "请选择"));
        return;
      }
      str = (String)((Iterator)localObject).next();
    }
  }
  
  public static void sendMailTo(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = "" + paramString + ";";
      paramString = paramString.substring(0, paramString.length() - 1);
      Intent localIntent = new Intent("android.intent.action.SENDTO");
      localIntent.setType("text/plain");
      localIntent.setData(Uri.parse("mailto:" + paramString));
      localIntent.putExtra("android.intent.extra.EMAIL", "");
      localIntent.putExtra("android.intent.extra.SUBJECT", "");
      localIntent.putExtra("android.intent.extra.TEXT", "");
      paramContext.startActivity(Intent.createChooser(localIntent, "请选择"));
    }
  }
  
  public static void setClipboard(Context paramContext, String paramString)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, paramString));
  }
  
  public static void setController(OpenUrlController paramOpenUrlController)
  {
    a = paramOpenUrlController;
  }
  
  private static void showSelectDialog(Activity paramActivity, String[] paramArrayOfString)
  {
    new AlertDialog.Builder(paramActivity).setTitle("请选择").setItems(paramArrayOfString, new asc(paramArrayOfString, paramActivity)).show();
  }
  
  private static void showToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }
  
  public boolean isRemindExpire(long paramLong1, long paramLong2)
  {
    boolean bool = true;
    long l = System.currentTimeMillis();
    if ((paramLong2 < paramLong1) || (paramLong2 < l + 3600L)) {
      bool = false;
    }
    return bool;
  }
  
  public static abstract interface OpenUrlController
  {
    public abstract void openUrl(Context paramContext, String paramString);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.message.BubbleUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */