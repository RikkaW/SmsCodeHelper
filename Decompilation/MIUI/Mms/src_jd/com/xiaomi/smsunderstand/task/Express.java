package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Express
  implements Comparable<Express>
{
  private static HashMap<String, String> name2BizCode;
  private static Pattern pat = Pattern.compile("^.+?(?=(快递|快运|速运|速递|物流)+$)");
  private String name;
  private String number;
  
  public static void clear()
  {
    name2BizCode = null;
  }
  
  public static String getBizCodeByName(String paramString)
  {
    if ((name2BizCode == null) && (!loadBizCode())) {
      paramString = "";
    }
    String str1;
    do
    {
      String str2;
      do
      {
        return paramString;
        str2 = paramString.toLowerCase();
        str1 = (String)name2BizCode.get(str2);
        paramString = str1;
      } while (str1 != null);
      paramString = trimSuffix(str2);
      if (paramString.equals(str2)) {
        break;
      }
      str1 = (String)name2BizCode.get(paramString);
      paramString = str1;
    } while (str1 != null);
    return "";
  }
  
  private static boolean loadBizCode()
  {
    Object localObject = SMSUnderstand.dictionaryPath + "/expressBizCode.txt";
    name2BizCode = new HashMap();
    try
    {
      localObject = FileOperator.createBufferedReaderByFileName((String)localObject);
      for (;;)
      {
        String str1 = ((BufferedReader)localObject).readLine();
        if (str1 == null)
        {
          ((BufferedReader)localObject).close();
          return true;
        }
        String str2 = str1.trim();
        if (!str2.equals(""))
        {
          int i = str2.indexOf("\t");
          if ((i > 0) && (i < str2.length() - 1))
          {
            str1 = str2.substring(0, i).toLowerCase();
            str2 = str2.substring(i + 1);
            name2BizCode.put(str1, str2);
            String str3 = trimSuffix(str1);
            if ((str3 != null) && (!str3.equals("")) && (!str3.equals(str1))) {
              name2BizCode.put(str3, str2);
            }
          }
        }
      }
      return false;
    }
    catch (IOException localIOException) {}
  }
  
  private static String trimSuffix(String paramString)
  {
    Matcher localMatcher = pat.matcher(paramString);
    if (localMatcher.find()) {
      paramString = localMatcher.group();
    }
    return paramString;
  }
  
  public int compareTo(Express paramExpress)
  {
    return name.compareTo(name);
  }
  
  public String toString()
  {
    return name + "\t" + number;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.Express
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */