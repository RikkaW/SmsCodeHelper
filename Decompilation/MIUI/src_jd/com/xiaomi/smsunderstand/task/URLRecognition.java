package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.StringProcess;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLRecognition
{
  private static boolean ifInitial;
  private static Pattern patURL = Pattern.compile("[\\da-zA-Z/\\.?:%&=\\-_]{4,}", 2);
  private static Pattern patURLCheck;
  private static HashSet<String> urlContainWords = new HashSet();
  private static String urlContainWordsFileName;
  private static int urlContainWordsMaxLength = -1;
  
  static
  {
    urlContainWordsFileName = null;
    ifInitial = false;
    patURLCheck = Pattern.compile("\\.[0-9]+$");
  }
  
  public static boolean checkURL(String paramString)
    throws IOException
  {
    if (StringProcess.getEngCharCount(paramString) <= 0) {}
    for (;;)
    {
      return false;
      if ((getPointCount(paramString) > 0) && (!paramString.endsWith(".")) && (!FlowRecognition.check(paramString)) && (!paramString.contains("..")) && (!patURLCheck.matcher(paramString).find())) {
        try
        {
          if (!ifInitial) {
            SMSUnderstand.initial();
          }
          initial();
          if (StringProcess.findAllResultsFromList(paramString.toLowerCase(), urlContainWords, urlContainWordsMaxLength).size() > 0) {
            return true;
          }
        }
        catch (IOException paramString) {}
      }
    }
    return false;
  }
  
  public static String formatUrl(String paramString)
  {
    paramString = StringProcess.trimEnd(StringProcess.trimStart(paramString, new Character[] { Character.valueOf(':'), Character.valueOf('/'), Character.valueOf(' '), Character.valueOf('\t') }), new Character[] { Character.valueOf(' '), Character.valueOf('\t') });
    String str = paramString.toLowerCase();
    if ((str.startsWith("http://")) || (str.startsWith("https://")) || (str.startsWith("ftp://"))) {
      return paramString;
    }
    return "http://" + paramString;
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    urlContainWords.clear();
    urlContainWordsMaxLength = -1;
    ifInitial = false;
    return true;
  }
  
  private static int getPointCount(String paramString)
  {
    int j = 0;
    int i = 0;
    for (;;)
    {
      if (i >= paramString.length()) {
        return j;
      }
      int k = j;
      if (paramString.charAt(i) == '.') {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
  }
  
  public static boolean initial()
    throws IOException
  {
    if (ifInitial) {
      return true;
    }
    urlContainWordsFileName = SMSUnderstand.dictionaryPath + "/urlContainWords.txt";
    Iterator localIterator = FileOperator.readFile(urlContainWordsFileName).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        ifInitial = true;
        return true;
      }
      String[] arrayOfString = ((String)localIterator.next()).split("\\t");
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        if (!str.equals(""))
        {
          urlContainWords.add(str.toLowerCase());
          if (urlContainWordsMaxLength < str.length()) {
            urlContainWordsMaxLength = str.length();
          }
        }
        i += 1;
      }
    }
  }
  
  public static double isRightNumber(EntityInfo paramEntityInfo, String paramString)
  {
    if (!ifInitial) {}
    int i;
    do
    {
      return 0.0D;
      i = StringProcess.findAllResultsFromList(paramEntityInfo.getTarget().toLowerCase(), urlContainWords, urlContainWordsMaxLength).size();
    } while (i <= 0);
    return 1.0D - Math.pow(0.1D, i);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.URLRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */