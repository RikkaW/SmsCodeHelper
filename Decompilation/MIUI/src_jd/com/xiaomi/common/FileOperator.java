package com.xiaomi.common;

import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileOperator
{
  public static BufferedReader createBufferedReaderByFileName(String paramString)
    throws IOException
  {
    if (SMSUnderstand.isResourceInJar()) {
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(FileOperator.class.getClassLoader().getResourceAsStream(paramString), Encoding.utf8));
        return localBufferedReader;
      }
      catch (Exception localException)
      {
        throw new IOException(paramString + " Read Error!!!");
      }
    }
    return new BufferedReader(new InputStreamReader(new FileInputStream(paramString), Encoding.utf8));
  }
  
  public static boolean fileExist(String paramString)
  {
    if (SMSUnderstand.isResourceInJar()) {
      return FileOperator.class.getClassLoader().getResource(paramString) != null;
    }
    return new File(paramString).exists();
  }
  
  public static String getFileNameWithoutExtend(String paramString)
  {
    String str = paramString.replace("\\", "/").replaceAll("^[/\" ]", "").replaceAll("[/\" ]$", "");
    int i = str.lastIndexOf('.');
    paramString = str;
    if (i >= 0) {
      paramString = str.substring(0, i);
    }
    i = paramString.lastIndexOf("/");
    str = paramString;
    if (i >= 0) {
      str = paramString.substring(i + 1);
    }
    return str;
  }
  
  public static List<List> readDic2Form(String paramString, List<String> paramList)
    throws IOException
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = paramList.iterator();
    if (!((Iterator)localObject2).hasNext()) {
      localObject2 = createBufferedReaderByFileName(paramString);
    }
    Object localObject3;
    label254:
    do
    {
      for (;;)
      {
        localObject3 = ((BufferedReader)localObject2).readLine();
        if (localObject3 == null)
        {
          ((BufferedReader)localObject2).close();
          return (List<List>)localObject1;
          localObject3 = (String)((Iterator)localObject2).next();
          if (((String)localObject3).equalsIgnoreCase("String")) {
            ((List)localObject1).add(new ArrayList());
          }
          if ((((String)localObject3).equalsIgnoreCase("int")) || (((String)localObject3).equalsIgnoreCase("integer"))) {
            ((List)localObject1).add(new ArrayList());
          }
          if (!((String)localObject3).equalsIgnoreCase("double")) {
            break;
          }
          ((List)localObject1).add(new ArrayList());
          break;
        }
        localObject3 = ((String)localObject3).trim();
        if ((!((String)localObject3).equals("")) && (!((String)localObject3).startsWith("//")))
        {
          if (!((String)localObject3).startsWith("#include")) {
            break label254;
          }
          i = ((String)localObject3).indexOf(" \"");
          int j = ((String)localObject3).indexOf("\"", i + 2);
          if ((i > 0) && (j > 0))
          {
            localObject1 = ((String)localObject3).substring(i + 2, j);
            localObject1 = readDic2Form(paramString.replace(new File(paramString).getName(), (CharSequence)localObject1), paramList);
          }
        }
      }
      localObject3 = ((String)localObject3).trim().split("\\t");
    } while ((localObject3.length <= 0) || (localObject3[0].length() <= 0));
    int i = 0;
    label284:
    if (i < localObject3.length)
    {
      if (!((String)paramList.get(i)).equalsIgnoreCase("String")) {
        break label337;
      }
      ((List)((List)localObject1).get(i)).add(localObject3[i]);
    }
    for (;;)
    {
      i += 1;
      break label284;
      break;
      label337:
      if ((((String)paramList.get(i)).equalsIgnoreCase("int")) || (((String)paramList.get(i)).equalsIgnoreCase("integer"))) {
        ((List)((List)localObject1).get(i)).add(Integer.valueOf(localObject3[i]));
      } else if (((String)paramList.get(i)).equalsIgnoreCase("double")) {
        ((List)((List)localObject1).get(i)).add(Double.valueOf(localObject3[i]));
      }
    }
  }
  
  public static int readDic2Map(String paramString, Map<String, Double> paramMap)
    throws IOException
  {
    BufferedReader localBufferedReader = createBufferedReaderByFileName(paramString);
    int i = -1;
    for (;;)
    {
      Object localObject = localBufferedReader.readLine();
      if (localObject == null)
      {
        localBufferedReader.close();
        return i;
      }
      localObject = ((String)localObject).trim();
      if ((!((String)localObject).equals("")) && (!((String)localObject).startsWith("//"))) {
        if (((String)localObject).startsWith("#include"))
        {
          int j = ((String)localObject).indexOf(" \"");
          int k = ((String)localObject).indexOf("\"", j + 2);
          if ((j > 0) && (k > 0))
          {
            localObject = ((String)localObject).substring(j + 2, k);
            j = readDic2Map(paramString.replace(new File(paramString).getName(), (CharSequence)localObject), paramMap);
            if (i < j) {
              i = j;
            }
          }
        }
        else
        {
          localObject = ((String)localObject).trim().toLowerCase().split("\\t");
          if ((localObject.length == 2) && (localObject[0].length() > 0))
          {
            paramMap.put(localObject[0], Double.valueOf(localObject[1]));
            if (i < localObject[0].length()) {
              i = localObject[0].length();
            }
          }
        }
      }
    }
  }
  
  public static int readDic2Set(String paramString, Set<String> paramSet)
    throws IOException
  {
    BufferedReader localBufferedReader = createBufferedReaderByFileName(paramString);
    int i = -1;
    Object localObject;
    int j;
    int k;
    do
    {
      do
      {
        for (;;)
        {
          localObject = localBufferedReader.readLine();
          if (localObject == null)
          {
            localBufferedReader.close();
            return i;
          }
          localObject = ((String)localObject).trim();
          if ((!((String)localObject).equals("")) && (!((String)localObject).startsWith("//")))
          {
            if (!((String)localObject).startsWith("#include")) {
              break;
            }
            j = ((String)localObject).indexOf(" \"");
            k = ((String)localObject).indexOf("\"", j + 2);
            if ((j > 0) && (k > 0))
            {
              localObject = ((String)localObject).substring(j + 2, k);
              j = readDic2Set(paramString.replace(new File(paramString).getName(), (CharSequence)localObject), paramSet);
              if (i < j) {
                i = j;
              }
            }
          }
        }
        localObject = ((String)localObject).split("\\t", 0);
      } while (localObject.length < 1);
      k = 0;
      j = i;
      i = j;
    } while (k >= localObject.length);
    localObject[k] = localObject[k].trim().toLowerCase();
    if ((localObject[k].length() >= 1) && (!localObject[k].contains("---")))
    {
      paramSet.add(localObject[k]);
      if (j >= localObject[k].length()) {}
    }
    for (i = localObject[k].length();; i = j)
    {
      k += 1;
      j = i;
      break;
    }
  }
  
  public static ArrayList<String> readFile(String paramString)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    paramString = createBufferedReaderByFileName(paramString);
    for (;;)
    {
      String str = paramString.readLine();
      if (str == null)
      {
        paramString.close();
        return localArrayList;
      }
      if (!str.equals("")) {
        localArrayList.add(str);
      }
    }
  }
  
  public static void readFile(String paramString, ArrayList<String> paramArrayList)
    throws IOException
  {
    paramString = createBufferedReaderByFileName(paramString);
    for (;;)
    {
      String str = paramString.readLine();
      if (str == null)
      {
        paramString.close();
        return;
      }
      if (!str.equals("")) {
        paramArrayList.add(str);
      }
    }
  }
  
  public static HashMap<String, String> readToMapChangeCol(String paramString)
    throws IOException
  {
    HashMap localHashMap = new HashMap();
    paramString = createBufferedReaderByFileName(paramString);
    for (;;)
    {
      String str = paramString.readLine();
      if (str == null)
      {
        paramString.close();
        return localHashMap;
      }
      if (!str.trim().equals(""))
      {
        int i = str.indexOf("\t");
        if ((i > 0) && (i < str.length() - 1)) {
          localHashMap.put(str.substring(i + 1), str.substring(0, i));
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.FileOperator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */