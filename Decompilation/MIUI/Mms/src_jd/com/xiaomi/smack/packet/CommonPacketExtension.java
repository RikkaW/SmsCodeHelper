package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smack.util.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CommonPacketExtension
  implements PacketExtension
{
  private String[] mAttributeNames = null;
  private String[] mAttributeValues = null;
  private List<CommonPacketExtension> mChildrenEles = null;
  private String mExtensionElementName;
  private String mNamespace;
  private String mText;
  
  public CommonPacketExtension(String paramString1, String paramString2, List<String> paramList1, List<String> paramList2)
  {
    mExtensionElementName = paramString1;
    mNamespace = paramString2;
    mAttributeNames = ((String[])paramList1.toArray(new String[paramList1.size()]));
    mAttributeValues = ((String[])paramList2.toArray(new String[paramList2.size()]));
  }
  
  public CommonPacketExtension(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    mExtensionElementName = paramString1;
    mNamespace = paramString2;
    mAttributeNames = paramArrayOfString1;
    mAttributeValues = paramArrayOfString2;
  }
  
  public CommonPacketExtension(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString3, List<CommonPacketExtension> paramList)
  {
    mExtensionElementName = paramString1;
    mNamespace = paramString2;
    mAttributeNames = paramArrayOfString1;
    mAttributeValues = paramArrayOfString2;
    mText = paramString3;
    mChildrenEles = paramList;
  }
  
  public static CommonPacketExtension parseFromBundle(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("ext_ele_name");
    String str2 = paramBundle.getString("ext_ns");
    String str3 = paramBundle.getString("ext_text");
    Object localObject1 = paramBundle.getBundle("attributes");
    Object localObject2 = ((Bundle)localObject1).keySet();
    String[] arrayOfString1 = new String[((Set)localObject2).size()];
    String[] arrayOfString2 = new String[((Set)localObject2).size()];
    Bundle localBundle = null;
    int i = 0;
    localObject2 = ((Set)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      String str4 = (String)((Iterator)localObject2).next();
      arrayOfString1[i] = str4;
      arrayOfString2[i] = ((Bundle)localObject1).getString(str4);
      i += 1;
    }
    if (paramBundle.containsKey("children"))
    {
      localObject1 = paramBundle.getParcelableArray("children");
      paramBundle = new ArrayList(localObject1.length);
      int j = localObject1.length;
      i = 0;
      for (;;)
      {
        localBundle = paramBundle;
        if (i >= j) {
          break;
        }
        paramBundle.add(parseFromBundle((Bundle)localObject1[i]));
        i += 1;
      }
    }
    return new CommonPacketExtension(str1, str2, arrayOfString1, arrayOfString2, str3, localBundle);
  }
  
  public static Parcelable[] toParcelableArray(List<CommonPacketExtension> paramList)
  {
    return toParcelableArray((CommonPacketExtension[])paramList.toArray(new CommonPacketExtension[paramList.size()]));
  }
  
  public static Parcelable[] toParcelableArray(CommonPacketExtension[] paramArrayOfCommonPacketExtension)
  {
    Object localObject;
    if (paramArrayOfCommonPacketExtension == null)
    {
      localObject = null;
      return (Parcelable[])localObject;
    }
    Parcelable[] arrayOfParcelable = new Parcelable[paramArrayOfCommonPacketExtension.length];
    int i = 0;
    for (;;)
    {
      localObject = arrayOfParcelable;
      if (i >= paramArrayOfCommonPacketExtension.length) {
        break;
      }
      arrayOfParcelable[i] = paramArrayOfCommonPacketExtension[i].toParcelable();
      i += 1;
    }
  }
  
  public void appendChild(CommonPacketExtension paramCommonPacketExtension)
  {
    if (mChildrenEles == null) {
      mChildrenEles = new ArrayList();
    }
    if (!mChildrenEles.contains(paramCommonPacketExtension)) {
      mChildrenEles.add(paramCommonPacketExtension);
    }
  }
  
  public String getAttributeValue(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    if (mAttributeNames != null)
    {
      int i = 0;
      while (i < mAttributeNames.length)
      {
        if (paramString.equals(mAttributeNames[i])) {
          return mAttributeValues[i];
        }
        i += 1;
      }
    }
    return null;
  }
  
  public CommonPacketExtension getChildByName(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (mChildrenEles == null)) {
      return null;
    }
    Iterator localIterator = mChildrenEles.iterator();
    while (localIterator.hasNext())
    {
      CommonPacketExtension localCommonPacketExtension = (CommonPacketExtension)localIterator.next();
      if (mExtensionElementName.equals(paramString)) {
        return localCommonPacketExtension;
      }
    }
    return null;
  }
  
  public String getElementName()
  {
    return mExtensionElementName;
  }
  
  public String getNamespace()
  {
    return mNamespace;
  }
  
  public String getText()
  {
    if (!TextUtils.isEmpty(mText)) {
      return StringUtils.unescapeFromXML(mText);
    }
    return mText;
  }
  
  public void setText(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      mText = StringUtils.escapeForXML(paramString);
      return;
    }
    mText = paramString;
  }
  
  public Bundle toBundle()
  {
    Bundle localBundle1 = new Bundle();
    localBundle1.putString("ext_ele_name", mExtensionElementName);
    localBundle1.putString("ext_ns", mNamespace);
    localBundle1.putString("ext_text", mText);
    Bundle localBundle2 = new Bundle();
    if ((mAttributeNames != null) && (mAttributeNames.length > 0))
    {
      int i = 0;
      while (i < mAttributeNames.length)
      {
        localBundle2.putString(mAttributeNames[i], mAttributeValues[i]);
        i += 1;
      }
    }
    localBundle1.putBundle("attributes", localBundle2);
    if ((mChildrenEles != null) && (mChildrenEles.size() > 0)) {
      localBundle1.putParcelableArray("children", toParcelableArray(mChildrenEles));
    }
    return localBundle1;
  }
  
  public Parcelable toParcelable()
  {
    return toBundle();
  }
  
  public String toString()
  {
    return toXML();
  }
  
  public String toXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<").append(mExtensionElementName);
    if (!TextUtils.isEmpty(mNamespace)) {
      localStringBuilder.append(" ").append("xmlns=").append("\"").append(mNamespace).append("\"");
    }
    if ((mAttributeNames != null) && (mAttributeNames.length > 0))
    {
      int i = 0;
      while (i < mAttributeNames.length)
      {
        if (!TextUtils.isEmpty(mAttributeValues[i])) {
          localStringBuilder.append(" ").append(mAttributeNames[i]).append("=\"").append(StringUtils.escapeForXML(mAttributeValues[i])).append("\"");
        }
        i += 1;
      }
    }
    if (!TextUtils.isEmpty(mText)) {
      localStringBuilder.append(">").append(mText).append("</").append(mExtensionElementName).append(">");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      if ((mChildrenEles != null) && (mChildrenEles.size() > 0))
      {
        localStringBuilder.append(">");
        Iterator localIterator = mChildrenEles.iterator();
        while (localIterator.hasNext()) {
          localStringBuilder.append(((CommonPacketExtension)localIterator.next()).toXML());
        }
        localStringBuilder.append("</").append(mExtensionElementName).append(">");
      }
      else
      {
        localStringBuilder.append("/>");
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.packet.CommonPacketExtension
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */