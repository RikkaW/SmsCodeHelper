package com.android.mms.model;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import miui.os.Build;

public class ContactParser
{
  private static Pattern CONTACT_INFO_PTN = Pattern.compile("\\[(.+?)\\]((.|\n)*?);");
  private static ContactParser mParser;
  private static final String[] sContactEntiesCN;
  private static final String[] sContactEntiesEN = { "Name", "Nickname", "Phone", "Email", "Organization", "IM", "SIP Phone", "Birthday", "Website", "Address", "Note" };
  private static final String[] sContactEntiesTW;
  private HashMap<String, String> mLabelTypeMap = new HashMap();
  private HashMap<String, String> mTypeLabelMap = new HashMap();
  private ArrayList<String> mTypes;
  
  static
  {
    sContactEntiesCN = new String[] { "姓名", "昵称", "电话", "邮件", "公司", "即时消息", "网络电话", "生日", "网址", "地址", "备注" };
    sContactEntiesTW = new String[] { "姓名", "匿稱", "電話", "郵件", "機構", "即時消息", "網絡電話", "生日", "網址", "位址", "備註" };
  }
  
  private ContactParser(Context paramContext)
  {
    String[] arrayOfString1 = paramContext.getResources().getStringArray(2131230724);
    String[] arrayOfString2 = paramContext.getResources().getStringArray(2131230725);
    mTypes = new ArrayList(Arrays.asList(arrayOfString2));
    int m = Math.min(arrayOfString1.length, arrayOfString2.length);
    paramContext = getResourcesgetConfigurationlocale.getLanguage();
    int j;
    if (Build.IS_INTERNATIONAL_BUILD)
    {
      j = 0;
      if (!"en".equals(paramContext)) {}
      for (i = 1;; i = 0)
      {
        int k = 0;
        while (k < m)
        {
          mLabelTypeMap.put(sContactEntiesEN[k], arrayOfString2[k]);
          if (j != 0)
          {
            mLabelTypeMap.put(sContactEntiesCN[k], arrayOfString2[k]);
            mLabelTypeMap.put(sContactEntiesTW[k], arrayOfString2[k]);
          }
          if (i != 0) {
            mLabelTypeMap.put(arrayOfString1[k], arrayOfString2[k]);
          }
          mTypeLabelMap.put(arrayOfString2[k], arrayOfString1[k]);
          k += 1;
        }
      }
    }
    if ((!"zh".equals(paramContext)) && (!"en".equals(paramContext))) {}
    for (int i = 1;; i = 0)
    {
      j = 1;
      break;
    }
  }
  
  public static ContactParser getContactParser(Context paramContext)
  {
    if (mParser == null) {
      mParser = new ContactParser(paramContext);
    }
    return mParser;
  }
  
  public String getContactFromIntent(Intent paramIntent)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = mTypes.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      Object localObject = paramIntent.getStringArrayListExtra(str1);
      if (localObject != null)
      {
        localObject = ((ArrayList)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str2 = (String)((Iterator)localObject).next();
          if (!TextUtils.isEmpty(str2)) {
            localStringBuilder.append('[' + (String)mTypeLabelMap.get(str1) + ']' + str2.replace(';', ',') + ";\n");
          }
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public String getTypeLabel(Context paramContext, String paramString)
  {
    paramContext = paramContext.getResources().getStringArray(2131230724);
    int i = mTypes.indexOf(paramString);
    if (i == 0) {
      return "type_name";
    }
    if ((i > 0) && (i < paramContext.length)) {
      return paramContext[i];
    }
    return "";
  }
  
  public ContactMessage parseFromMessage(String paramString)
  {
    Object localObject2;
    if (TextUtils.isEmpty(paramString))
    {
      localObject2 = null;
      return (ContactMessage)localObject2;
    }
    Matcher localMatcher = CONTACT_INFO_PTN.matcher(paramString);
    Object localObject1 = null;
    for (;;)
    {
      localObject2 = localObject1;
      if (!localMatcher.find()) {
        break;
      }
      int i = localMatcher.start();
      int j = localMatcher.end();
      Object localObject3 = localMatcher.group(1).trim();
      String str = localMatcher.group(2).trim();
      if (mLabelTypeMap.containsKey(localObject3)) {
        localObject2 = (String)mLabelTypeMap.get(localObject3);
      }
      do
      {
        localObject3 = localObject1;
        if (localObject1 == null) {
          localObject3 = new ContactMessage(paramString, mTypeLabelMap);
        }
        ((ContactMessage)localObject3).addRecord((String)localObject2, str, i, j);
        localObject1 = localObject3;
        break;
        localObject2 = localObject3;
      } while (mTypeLabelMap.containsKey(localObject3));
    }
  }
  
  public Intent putContactToIntent(ContactMessage.ContactRecord paramContactRecord, Intent paramIntent)
  {
    if (paramContactRecord == null) {}
    for (;;)
    {
      return paramIntent;
      HashMap localHashMap = new HashMap();
      Iterator localIterator = paramContactRecord.getContactRecord().iterator();
      Object localObject;
      while (localIterator.hasNext())
      {
        Pair localPair = (Pair)localIterator.next();
        localObject = (ArrayList)localHashMap.get(first);
        paramContactRecord = (ContactMessage.ContactRecord)localObject;
        if (localObject == null)
        {
          paramContactRecord = new ArrayList();
          localHashMap.put(first, paramContactRecord);
        }
        paramContactRecord.add(second);
      }
      paramContactRecord = localHashMap.keySet().iterator();
      while (paramContactRecord.hasNext())
      {
        localObject = (String)paramContactRecord.next();
        paramIntent.putStringArrayListExtra((String)localObject, (ArrayList)localHashMap.get(localObject));
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.ContactParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */