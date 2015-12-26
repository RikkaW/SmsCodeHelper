package com.ted.android.message;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;

public class MessageUtils
{
  private static final String prefix_cn = "【";
  private static final String prefix_en = "[";
  private static final String suffix_cn = "】";
  private static final String suffix_en = "]";
  
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
  
  public static boolean containsHanScript(String paramString)
  {
    int i = 0;
    int j;
    do
    {
      if (i >= paramString.length()) {
        return false;
      }
      j = paramString.codePointAt(i);
      i += Character.charCount(j);
    } while (Character.UnicodeBlock.of(j) != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
    return true;
  }
  
  public static String getMessageSign(String paramString)
  {
    Object localObject1 = "";
    Object localObject2 = "";
    String str = paramString.trim();
    int i;
    if (str.startsWith("["))
    {
      i = str.indexOf("]");
      paramString = (String)localObject1;
      if (i > 0) {
        paramString = str.substring(1, i);
      }
      if (!str.endsWith("]")) {
        break label178;
      }
      i = str.lastIndexOf("[");
      localObject1 = localObject2;
      if (i > 0) {
        localObject1 = str.substring(i + 1, str.length() - 1);
      }
    }
    for (;;)
    {
      localObject2 = new JSONArray();
      if ((paramString.length() > 0) && (paramString.length() < 9)) {
        ((JSONArray)localObject2).put(paramString);
      }
      if ((((String)localObject1).length() > 0) && (((String)localObject1).length() < 9)) {
        ((JSONArray)localObject2).put(localObject1);
      }
      return ((JSONArray)localObject2).toString();
      paramString = (String)localObject1;
      if (!str.startsWith("【")) {
        break;
      }
      i = str.indexOf("】");
      paramString = (String)localObject1;
      if (i <= 0) {
        break;
      }
      paramString = str.substring(1, i);
      break;
      label178:
      localObject1 = localObject2;
      if (str.endsWith("】"))
      {
        i = str.lastIndexOf("【");
        localObject1 = localObject2;
        if (i > 0) {
          localObject1 = str.substring(i + 1, str.length() - 1);
        }
      }
    }
  }
  
  public static String removeMessageSign(String paramString)
  {
    String str = paramString.trim();
    int i = str.length();
    int j;
    if (str.startsWith("["))
    {
      j = str.indexOf("]");
      paramString = str;
      if (j > 0)
      {
        paramString = str;
        if (j < i - 1) {
          paramString = str.substring(j + 1);
        }
      }
      paramString.length();
      if (!paramString.endsWith("]")) {
        break label129;
      }
      i = paramString.lastIndexOf("[");
      str = paramString;
      if (i > 0) {
        str = paramString.substring(0, i);
      }
    }
    label129:
    do
    {
      do
      {
        return str;
        paramString = str;
        if (!str.startsWith("【")) {
          break;
        }
        j = str.indexOf("】");
        paramString = str;
        if (j <= 0) {
          break;
        }
        paramString = str;
        if (j >= i - 1) {
          break;
        }
        paramString = str.substring(j + 1);
        break;
        str = paramString;
      } while (!paramString.endsWith("】"));
      i = paramString.lastIndexOf("【");
      str = paramString;
    } while (i <= 0);
    return paramString.substring(0, i);
  }
  
  public static String removePhoneNumber(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (!"".equals(paramString.trim())) {
        str = paramString.replaceAll("(?:(?<=^|[^0-9\\-\\.])(114|10086|10010\\d?|10000)(?=$|[^0-9\\-\\.]))|((?<=^|[^A-Za-z0-9\\+\\-])(?:86|86-|\\+86|\\+86-)?1[356789]\\d-?\\d{4}-?\\d{4}(?=$|[^A-Za-z0-9\\+\\-]))|((?<=^|[^A-Za-z0-9\\+\\-]|[^A-Za-z0-9\\+]-)0(?:10|2(?:0|1|2|3|4|5|6|7|8|9)|3(?:1(?:0|1|2|3|4|5|6|7|8|9)|35|49|5(?:0|1|2|3|4|5|6|7|8|9)|7(?:0|1|2|3|4|5|6|7|9)|9(?:1|2|3|4|5|6|8))|4(?:1(?:1|2|5|6|7|8|9)|2(?:1|7|9)|3(?:1|2|3|4|5|6|7|8|9)|5(?:1|2|3|4|5|6|7|8|9)|6(?:4|7|8|9)|7(?:0|1|2|3|4|5|6|7|8|9)|8(?:2|3))|5(?:1(?:0|1|2|3|4|5|6|7|8|9)|2(?:3|7)|3(?:0|1|2|3|4|5|6|7|8|9)|4(?:3|6)|5(?:0|1|2|3|4|5|6|7|8|9)|6(?:1|2|3|4|6)|7(?:0|1|2|3|4|5|6|7|8|9)|80|9(?:1|2|3|4|5|6|7|8|9))|6(?:3(?:1|2|3|4|5)|6(?:0|2|3|8)|9(?:1|2))|7(?:01|1(?:0|1|2|3|4|5|6|7|8|9)|2(?:2|4|8)|3(?:0|1|4|5|6|7|8|9)|4(?:3|4|5|6)|5(?:0|1|2|3|4|5|6|7|8|9)|6(?:0|2|3|6|8|9)|7(?:0|1|2|3|4|5|6|7|8|9)|9(?:0|1|2|3|4|5|6|7|8|9))|8(?:1(?:2|3|6|7|8)|2(?:5|6|7)|3(?:0|1|2|3|4|5|6|7|8|9)|5(?:1|4|5|6|7|8|9)|7(?:0|1|2|3|4|5|6|7|8|9)|8(?:3|6|7|8)|9(?:1|2|3|4|5|6|7|8))|9(?:0(?:1|2|3|6|8|9)|1(?:1|2|3|4|5|6|7|9)|3(?:0|1|2|3|4|5|6|7|8|9)|4(?:1|3)|5(?:1|2|3|4|5)|7(?:0|1|2|3|4|5|6|7|9)|9(?:0|1|2|3|4|5|6|7|8|9)))-?\\d{7,8}(?=$|[^A-Za-z0-9\\+\\-]))|(?:(?<=(?:电|电话|询|拨|拨打|热线|客服|联系|联系方式|专线)(?:(?:\\s{0,10}[:：\"“\\(（\\<《]{1,10}\\s{0,10})|(?:\\s{0,10})))([\\d-]{5,20})[\\s,，/、或]{0,10}([\\d-]{5,20}){0,1}[\\s,，/、或]{0,10}([\\d-]{5,20}){0,1}(?!\\s*(?:分钟))(?=$|[^A-Za-z0-9\\+\\-]))|((?<=^|[^A-Za-z0-9\\+\\-])[\\d-]{5,20}(?=(?:\\s*)(?:转[^发]|询|查询|曾|专线|拨打)))", " ");
      }
    }
    return str;
  }
  
  public static String removeUrl(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (!"".equals(paramString.trim())) {
        str = paramString.replaceAll("(?<=^|[^0-9a-zA-Z\\-_/\\?=&@\\*%\\.]|(?:^|[^0-9a-zA-Z\\-_/\\?=&@\\*%])\\.)(?:(?i:https?)://)?(?:(?:(?:[0-9a-zA-Z\\-_]+)(?:(?:(?:\\.[0-9a-zA-Z\\-_]+)+(?::\\d{1,5})?/[0-9a-zA-Z\\-_/\\?=&\\.%]*[0-9a-zA-Z\\-_/\\?=&])|(?:(?:\\.[0-9a-zA-Z\\-_]+)*\\.(?i:com|cn|gov|net|org|edu|cc)/?(?=$|[^0-9a-zA-Z\\-_/\\?=&\\.%]|\\.(?:$|[^0-9a-zA-Z\\-_/\\?=&])))))|(?:(?i:www)\\.[0-9a-zA-Z\\-_/\\?=&\\.%]*[0-9a-zA-Z\\-_/\\?=&]))", "http");
      }
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.message.MessageUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */