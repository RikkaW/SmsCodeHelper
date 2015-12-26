package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CreditCardHuankuanRecognition
{
  private static Map<String, String> configMap;
  private static boolean ifInitial = false;
  private static Map<String, SoftReference<List<String>>> index = new HashMap();
  private static String resouceDirectory;
  
  static
  {
    configMap = new HashMap();
    resouceDirectory = null;
  }
  
  public static boolean checkMes(String paramString1, String paramString2)
    throws IOException
  {
    paramString1 = (String)configMap.get(paramString1);
    if (paramString1 == null) {
      return false;
    }
    Log.i("CreditCardHuankuanRecognition", "ID\t" + paramString1);
    if ((index.containsKey(paramString1)) && (((SoftReference)index.get(paramString1)).get() != null)) {
      return match(paramString2, (List)((SoftReference)index.get(paramString1)).get());
    }
    List localList = loadContent(resouceDirectory + paramString1);
    index.put(paramString1, new SoftReference(localList));
    return match(paramString2, localList);
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    index.clear();
    configMap.clear();
    ifInitial = false;
    return true;
  }
  
  public static boolean initial()
  {
    if (ifInitial) {
      return true;
    }
    index.clear();
    configMap.clear();
    resouceDirectory = SMSUnderstand.dictionaryPath + "/creditCardHuankuanResource/";
    try
    {
      loadConfig(resouceDirectory + "config");
      ifInitial = true;
      return true;
    }
    catch (Exception localException) {}
    return false;
  }
  
  private static void loadConfig(String paramString)
    throws IOException
  {
    paramString = FileOperator.createBufferedReaderByFileName(paramString);
    for (;;)
    {
      Object localObject = paramString.readLine();
      if (localObject == null)
      {
        paramString.close();
        return;
      }
      localObject = ((String)localObject).split(" ");
      if (localObject.length > 1)
      {
        int i = 1;
        while (i < localObject.length)
        {
          configMap.put(localObject[i], localObject[0]);
          i += 1;
        }
      }
    }
  }
  
  private static List<String> loadContent(String paramString)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    paramString = FileOperator.createBufferedReaderByFileName(paramString);
    for (;;)
    {
      String str = paramString.readLine();
      if (str == null)
      {
        paramString.close();
        return localArrayList;
      }
      localArrayList.add(str);
    }
  }
  
  private static boolean match(String paramString, List<String> paramList)
  {
    paramList = paramList.iterator();
    do
    {
      if (!paramList.hasNext()) {
        return false;
      }
    } while (!paramString.matches((String)paramList.next()));
    return true;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.CreditCardHuankuanRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */