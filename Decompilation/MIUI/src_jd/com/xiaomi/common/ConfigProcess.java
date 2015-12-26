package com.xiaomi.common;

import java.io.BufferedReader;
import java.util.HashMap;

public class ConfigProcess
{
  public static HashMap<String, String> readConfig(String paramString)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      paramString = FileOperator.createBufferedReaderByFileName(paramString);
      for (;;)
      {
        Object localObject = paramString.readLine();
        if (localObject == null)
        {
          paramString.close();
          return localHashMap;
        }
        localObject = ((String)localObject).trim();
        if ((!((String)localObject).equals("")) && (!((String)localObject).startsWith("//")))
        {
          localObject = ((String)localObject).split("::=", -1);
          if (localObject.length == 2) {
            localHashMap.put(localObject[0].trim(), StringProcess.trim(localObject[1], new Character[] { Character.valueOf('"'), Character.valueOf(';'), Character.valueOf(' '), Character.valueOf('\t'), Character.valueOf('“'), Character.valueOf('”'), Character.valueOf(65307) }));
          }
        }
      }
      return null;
    }
    catch (Exception paramString) {}
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.ConfigProcess
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */