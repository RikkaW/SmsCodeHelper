package com.ted.sdk.ivr;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import anv;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.contacts.common.util.NovoFileUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class IvrParser
{
  private static final char[] ALL_KEYS = { 49, 50, 51, 52, 53, 54, 55, 56, 57, 42, 48, 35 };
  private static final String DES_HOME = "返回主菜单";
  private static final String DES_PREVIOUS = "返回上一级";
  private static final String IVR_FILE = "ivr.dat";
  private static final String M_T_CMCC = "CMCC";
  private static final String M_T_CTCC = "CTCC";
  private static final String M_T_CUCC = "CUCC";
  
  private static String decodeFile(Context paramContext)
  {
    try
    {
      InputStream localInputStream = NovoFileUtil.openLatestInputFile(paramContext, "ivr.dat");
      paramContext = paramContext.getCacheDir().getAbsolutePath() + "/" + "ivr.dat";
      FileOutputStream localFileOutputStream;
      localException1.printStackTrace();
    }
    catch (Exception localException1)
    {
      try
      {
        localFileOutputStream = new FileOutputStream(new File(paramContext));
        anv.a(localInputStream, localFileOutputStream, DataBus.FILE_MASK);
        localFileOutputStream.close();
        localInputStream.close();
        return paramContext;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localException1 = localException1;
      paramContext = "";
    }
    return paramContext;
  }
  
  public static void load(Context paramContext)
  {
    Object localObject = decodeFile(paramContext);
    if (TextUtils.isEmpty((CharSequence)localObject)) {}
    for (;;)
    {
      return;
      try
      {
        paramContext = new FileInputStream((String)localObject);
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramContext));
        paramContext = new HashSet();
        try
        {
          str = localBufferedReader.readLine();
          if (str != null) {
            break label121;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            String str;
            localException.printStackTrace();
          }
        }
        localObject = new File((String)localObject);
        if ((localObject != null) && (((File)localObject).exists())) {
          ((File)localObject).delete();
        }
        paramContext = paramContext.iterator();
        while (paramContext.hasNext()) {
          parse((String)paramContext.next());
        }
      }
      catch (FileNotFoundException paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
          paramContext = null;
          continue;
          label121:
          paramContext.add(str);
        }
      }
    }
  }
  
  private static void parse(String paramString)
  {
    int i = 2;
    try
    {
      Object localObject1 = new JSONObject(paramString);
      paramString = ((JSONObject)localObject1).getString("city");
      ((JSONObject)localObject1).getString("intro_flag");
      String str2 = ((JSONObject)localObject1).getString("mobile_type");
      Object localObject2 = ((JSONObject)localObject1).getString("name");
      String str1 = ((JSONObject)localObject1).getString("phone");
      localObject2 = new HotNumber(str1, (String)localObject2);
      parseService(((JSONObject)localObject1).getJSONObject("service"), ((HotNumber)localObject2).getActions());
      if (!TextUtils.isEmpty(str2))
      {
        localObject1 = str2.split("|");
        if ((localObject1 != null) && (localObject1.length >= 3)) {
          if ("CMCC".equals(localObject1[2])) {
            i = 0;
          }
        }
      }
      for (;;)
      {
        IvrCache.INSTANCE.put(i, Pair.create(paramString, str1), (HotNumber)localObject2);
        return;
        if ("CUCC".equals(localObject1[2]))
        {
          i = 1;
        }
        else
        {
          boolean bool = "CTCC".equals(localObject1[2]);
          if (!bool) {
            i = -1;
          }
        }
      }
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static int parseKeyType(String paramString)
  {
    if ("返回上一级".equals(paramString)) {
      return 1;
    }
    if ("返回主菜单".equals(paramString)) {
      return 3;
    }
    return 0;
  }
  
  private static boolean parseService(JSONObject paramJSONObject, HashMap<Character, DialpadAction> paramHashMap)
  {
    boolean bool = false;
    char[] arrayOfChar = ALL_KEYS;
    int j = arrayOfChar.length;
    int i = 0;
    char c;
    for (;;)
    {
      if (i >= j) {
        return bool;
      }
      c = arrayOfChar[i];
      if (paramJSONObject.has(Character.toString(c))) {
        break;
      }
      i += 1;
    }
    JSONObject localJSONObject = paramJSONObject.getJSONObject(Character.toString(c));
    if (localJSONObject.has("des")) {}
    for (Object localObject = localJSONObject.getString("des");; localObject = "")
    {
      localObject = new DialpadAction(c, parseKeyType((String)localObject), (String)localObject);
      if (parseService(localJSONObject, ((DialpadAction)localObject).getSubAction())) {
        ((DialpadAction)localObject).setKeyType(2);
      }
      putInActions(c, (DialpadAction)localObject, paramHashMap);
      bool = true;
      break;
    }
  }
  
  private static void putInActions(char paramChar, DialpadAction paramDialpadAction, HashMap<Character, DialpadAction> paramHashMap)
  {
    DialpadAction localDialpadAction = (DialpadAction)paramHashMap.get(Character.valueOf(paramChar));
    if (localDialpadAction != null)
    {
      localDialpadAction.refresh(paramDialpadAction);
      return;
    }
    paramHashMap.put(Character.valueOf(paramChar), paramDialpadAction);
  }
  
  static abstract interface JsonKey
  {
    public static final String KEY_CITY = "city";
    public static final String KEY_DESC = "des";
    public static final String KEY_INTRO_FLAG = "intro_flag";
    public static final String KEY_MOBILE_TYPE = "mobile_type";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_SERVICE = "service";
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.ivr.IvrParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */