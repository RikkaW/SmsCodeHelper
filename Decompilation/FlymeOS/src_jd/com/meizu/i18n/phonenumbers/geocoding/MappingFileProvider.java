package com.meizu.i18n.phonenumbers.geocoding;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class MappingFileProvider
  implements Externalizable
{
  private static final Map<String, String> LOCALE_NORMALIZATION_MAP;
  private List<Set<String>> availableLanguages;
  private int[] countryCallingCodes;
  private int numOfEntries = 0;
  
  static
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("zh_TW", "zh_Hant");
    localHashMap.put("zh_HK", "zh_Hant");
    localHashMap.put("zh_MO", "zh_Hant");
    LOCALE_NORMALIZATION_MAP = Collections.unmodifiableMap(localHashMap);
  }
  
  private void appendSubsequentLocalePart(String paramString, StringBuilder paramStringBuilder)
  {
    if (paramString.length() > 0) {
      paramStringBuilder.append('_').append(paramString);
    }
  }
  
  private StringBuilder constructFullLocale(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new StringBuilder(paramString1);
    appendSubsequentLocalePart(paramString2, paramString1);
    appendSubsequentLocalePart(paramString3, paramString1);
    return paramString1;
  }
  
  private String findBestMatchingLanguageCode(Set<String> paramSet, String paramString1, String paramString2, String paramString3)
  {
    String str2 = constructFullLocale(paramString1, paramString2, paramString3).toString();
    String str1 = (String)LOCALE_NORMALIZATION_MAP.get(str2);
    if ((str1 != null) && (paramSet.contains(str1))) {
      paramString2 = str1;
    }
    do
    {
      return paramString2;
      if (paramSet.contains(str2)) {
        return str2;
      }
      if (!onlyOneOfScriptOrRegionIsEmpty(paramString2, paramString3)) {
        break;
      }
      paramString2 = paramString1;
    } while (paramSet.contains(paramString1));
    do
    {
      do
      {
        return "";
      } while ((paramString2.length() <= 0) || (paramString3.length() <= 0));
      paramString2 = paramString1 + '_' + paramString2;
      if (paramSet.contains(paramString2)) {
        return paramString2;
      }
      paramString2 = paramString1 + '_' + paramString3;
      if (paramSet.contains(paramString2)) {
        return paramString2;
      }
    } while (!paramSet.contains(paramString1));
    return paramString1;
  }
  
  private boolean onlyOneOfScriptOrRegionIsEmpty(String paramString1, String paramString2)
  {
    return ((paramString1.length() == 0) && (paramString2.length() > 0)) || ((paramString2.length() == 0) && (paramString1.length() > 0));
  }
  
  String getFileName(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    if (paramString1.length() == 0) {
      return "";
    }
    int i = Arrays.binarySearch(countryCallingCodes, paramInt);
    if (i < 0) {
      return "";
    }
    Set localSet = (Set)availableLanguages.get(i);
    if (localSet.size() > 0)
    {
      paramString1 = findBestMatchingLanguageCode(localSet, paramString1, paramString2, paramString3);
      if (paramString1.length() > 0)
      {
        paramString2 = new StringBuilder();
        paramString2.append(paramInt).append('_').append(paramString1);
        return paramString2.toString();
      }
    }
    return "";
  }
  
  public void readExternal(ObjectInput paramObjectInput)
  {
    numOfEntries = paramObjectInput.readInt();
    if ((countryCallingCodes == null) || (countryCallingCodes.length < numOfEntries)) {
      countryCallingCodes = new int[numOfEntries];
    }
    if (availableLanguages == null) {
      availableLanguages = new ArrayList();
    }
    int i = 0;
    while (i < numOfEntries)
    {
      countryCallingCodes[i] = paramObjectInput.readInt();
      int k = paramObjectInput.readInt();
      HashSet localHashSet = new HashSet();
      int j = 0;
      while (j < k)
      {
        localHashSet.add(paramObjectInput.readUTF());
        j += 1;
      }
      availableLanguages.add(localHashSet);
      i += 1;
    }
  }
  
  public void readFileConfigs(SortedMap<Integer, Set<String>> paramSortedMap)
  {
    numOfEntries = paramSortedMap.size();
    countryCallingCodes = new int[numOfEntries];
    availableLanguages = new ArrayList(numOfEntries);
    Iterator localIterator = paramSortedMap.keySet().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      int j = ((Integer)localIterator.next()).intValue();
      countryCallingCodes[i] = j;
      availableLanguages.add(new HashSet((Collection)paramSortedMap.get(Integer.valueOf(j))));
      i += 1;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < numOfEntries)
    {
      localStringBuilder.append(countryCallingCodes[i]);
      localStringBuilder.append('|');
      Iterator localIterator = new TreeSet((Collection)availableLanguages.get(i)).iterator();
      while (localIterator.hasNext())
      {
        localStringBuilder.append((String)localIterator.next());
        localStringBuilder.append(',');
      }
      localStringBuilder.append('\n');
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
  {
    paramObjectOutput.writeInt(numOfEntries);
    int i = 0;
    while (i < numOfEntries)
    {
      paramObjectOutput.writeInt(countryCallingCodes[i]);
      Object localObject = (Set)availableLanguages.get(i);
      paramObjectOutput.writeInt(((Set)localObject).size());
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramObjectOutput.writeUTF((String)((Iterator)localObject).next());
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.geocoding.MappingFileProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */