package com.xiaomi.smsunderstand;

import com.xiaomi.common.ACAutomation;
import com.xiaomi.common.FileOperator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class SMSDic2Pattern
{
  private List<SMSBodyKeywordInfo> bodyKeyword2Pattern;
  private List<StringAndIntArray> phoneNumberPrefixsAndPatIndex;
  private ACAutomation phoneNumberPrefixsAutomation;
  private List<StringAndIntArray> phoneNumberSuffixsAndPatIndex;
  private ACAutomation phoneNumberSuffixsAutomation;
  private List<StringAndIntArray> startKeywordsAndPatIndex;
  private ACAutomation startKeywordsAutomation;
  
  public SMSDic2Pattern(String paramString)
    throws IOException
  {
    paramString = FileOperator.readFile(paramString);
    bodyKeyword2Pattern = new ArrayList();
    HashMap localHashMap3 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap1 = new HashMap();
    Iterator localIterator = paramString.iterator();
    if (!localIterator.hasNext())
    {
      startKeywordsAndPatIndex = new ArrayList();
      paramString = localHashMap3.entrySet().iterator();
      label87:
      if (paramString.hasNext()) {
        break label806;
      }
      phoneNumberPrefixsAndPatIndex = new ArrayList();
      paramString = localHashMap2.entrySet().iterator();
      label118:
      if (paramString.hasNext()) {
        break label857;
      }
      phoneNumberSuffixsAndPatIndex = new ArrayList();
      paramString = localHashMap1.entrySet().iterator();
    }
    for (;;)
    {
      if (!paramString.hasNext())
      {
        startKeywordsAutomation = new ACAutomation(startKeywordsAndPatIndex, false);
        phoneNumberPrefixsAutomation = new ACAutomation(phoneNumberPrefixsAndPatIndex, false);
        phoneNumberSuffixsAutomation = new ACAutomation(phoneNumberSuffixsAndPatIndex, false);
        return;
        localObject1 = (String)localIterator.next();
        int i = ((String)localObject1).indexOf("//");
        paramString = (String)localObject1;
        if (i >= 0) {
          paramString = ((String)localObject1).substring(0, i);
        }
        Object localObject2 = paramString.split("[\\s\\t]+");
        if (localObject2.length < 2) {
          break;
        }
        String str1 = localObject2[(localObject2.length - 1)];
        paramString = null;
        String[] arrayOfString1 = null;
        label310:
        label319:
        BitSet localBitSet1;
        BitSet localBitSet2;
        int j;
        String[] arrayOfString2;
        if ((localObject2.length != 3) || (!localObject2[0].toLowerCase().equals("all")))
        {
          localObject1 = new ArrayList(localObject2.length - 2);
          i = 0;
          if (i >= localObject2.length - 2)
          {
            if (localObject2[0].toLowerCase().equals("all")) {
              localObject1 = null;
            }
            arrayOfString1 = null;
            if (paramString != null) {
              arrayOfString1 = paramString.split("[\\|]+");
            }
            paramString = null;
            localBitSet1 = null;
            localBitSet2 = null;
            if (!localObject2[(localObject2.length - 2)].toLowerCase().equals("all"))
            {
              paramString = localObject2[(localObject2.length - 2)].split("[\\|]+");
              localBitSet1 = new BitSet(paramString.length);
              localBitSet2 = new BitSet(paramString.length);
              analysisPhoneNumberPart(paramString, localBitSet1, localBitSet2);
            }
            j = bodyKeyword2Pattern.size();
            if ((localObject1 == null) || (((List)localObject1).size() <= 0)) {
              break label653;
            }
            arrayOfString2 = (String[])((List)localObject1).get(0);
            int k = arrayOfString2.length;
            i = 0;
            label470:
            if (i < k) {
              break label587;
            }
          }
        }
        for (;;)
        {
          bodyKeyword2Pattern.add(new SMSBodyKeywordInfo((List)localObject1, paramString, localBitSet1, localBitSet2, arrayOfString1, str1));
          break;
          if (localObject2[i].startsWith("area:"))
          {
            paramString = localObject2[i].substring(5);
            break label319;
          }
          ((List)localObject1).add(localObject2[i].split("[\\|]+"));
          i += 1;
          break label310;
          localObject1 = arrayOfString1;
          if (!localObject2[0].startsWith("area:")) {
            break label319;
          }
          paramString = localObject2[0].substring(5);
          localObject1 = arrayOfString1;
          break label319;
          label587:
          String str2 = arrayOfString2[i];
          List localList = (List)localHashMap3.get(str2);
          localObject2 = localList;
          if (localList == null)
          {
            localObject2 = new ArrayList(3);
            localHashMap3.put(str2, localObject2);
          }
          ((List)localObject2).add(Integer.valueOf(j));
          i += 1;
          break label470;
          label653:
          if ((paramString == null) || (paramString.length <= 0)) {
            break;
          }
          i = 0;
          while (i < paramString.length)
          {
            arrayOfString2 = paramString[i];
            if (localBitSet1.get(i))
            {
              localList = (List)localHashMap2.get(arrayOfString2);
              localObject2 = localList;
              if (localList == null)
              {
                localObject2 = new ArrayList(3);
                localHashMap2.put(arrayOfString2, localObject2);
              }
              ((List)localObject2).add(Integer.valueOf(j));
            }
            if (localBitSet2.get(i))
            {
              localList = (List)localHashMap1.get(arrayOfString2);
              localObject2 = localList;
              if (localList == null)
              {
                localObject2 = new ArrayList(3);
                localHashMap1.put(arrayOfString2, localObject2);
              }
              ((List)localObject2).add(Integer.valueOf(j));
            }
            i += 1;
          }
        }
        label806:
        localObject1 = (Map.Entry)paramString.next();
        startKeywordsAndPatIndex.add(new StringAndIntArray((String)((Map.Entry)localObject1).getKey(), (List)((Map.Entry)localObject1).getValue()));
        break label87;
        label857:
        localObject1 = (Map.Entry)paramString.next();
        phoneNumberPrefixsAndPatIndex.add(new StringAndIntArray((String)((Map.Entry)localObject1).getKey(), (List)((Map.Entry)localObject1).getValue()));
        break label118;
      }
      Object localObject1 = (Map.Entry)paramString.next();
      phoneNumberSuffixsAndPatIndex.add(new StringAndIntArray((String)((Map.Entry)localObject1).getKey(), (List)((Map.Entry)localObject1).getValue()));
    }
  }
  
  private int analysisPhoneNumberPart(String[] paramArrayOfString, BitSet paramBitSet1, BitSet paramBitSet2)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return 0;
    }
    int i = 0;
    if (i >= paramArrayOfString.length) {
      return paramArrayOfString.length;
    }
    String str = paramArrayOfString[i];
    int j = 0;
    if (str.endsWith("$"))
    {
      paramBitSet2.set(i, true);
      j = 1;
    }
    int k = 0;
    if (str.startsWith("^"))
    {
      paramBitSet1.set(i, true);
      k = 1;
    }
    if ((k != 0) && (j != 0)) {
      paramArrayOfString[i] = paramArrayOfString[i].substring(1, paramArrayOfString[i].length() - 1);
    }
    for (;;)
    {
      i += 1;
      break;
      if (k != 0)
      {
        paramArrayOfString[i] = paramArrayOfString[i].substring(1);
      }
      else if (j != 0)
      {
        paramArrayOfString[i] = paramArrayOfString[i].substring(0, paramArrayOfString[i].length() - 1);
      }
      else
      {
        paramBitSet1.set(i, true);
        paramArrayOfString[i] = paramArrayOfString[i];
      }
    }
  }
  
  private HashSet<Integer> getCandPatIndex(String paramString1, String paramString2)
  {
    HashSet localHashSet = new HashSet();
    paramString2 = startKeywordsAutomation.find(paramString2).iterator();
    if (!paramString2.hasNext())
    {
      paramString2 = phoneNumberPrefixsAutomation.startWith(paramString1, 0).iterator();
      label46:
      if (paramString2.hasNext()) {
        break label119;
      }
      paramString1 = phoneNumberSuffixsAutomation.startWith(paramString1, 0).iterator();
    }
    for (;;)
    {
      if (!paramString1.hasNext())
      {
        return localHashSet;
        int[] arrayOfInt = (int[])paramString2.next();
        localHashSet.addAll(((StringAndIntArray)startKeywordsAndPatIndex.get(arrayOfInt[0])).getIndexs());
        break;
        label119:
        arrayOfInt = (int[])paramString2.next();
        localHashSet.addAll(((StringAndIntArray)phoneNumberPrefixsAndPatIndex.get(arrayOfInt[0])).getIndexs());
        break label46;
      }
      paramString2 = (int[])paramString1.next();
      localHashSet.addAll(((StringAndIntArray)phoneNumberSuffixsAndPatIndex.get(paramString2[0])).getIndexs());
    }
  }
  
  public int getPatternSize()
  {
    if (bodyKeyword2Pattern == null) {
      return 0;
    }
    return bodyKeyword2Pattern.size();
  }
  
  public List<String> match(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = getCandPatIndex(paramString1, paramString3);
    ArrayList localArrayList = new ArrayList();
    localObject = ((HashSet)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return localArrayList;
      }
      Integer localInteger = (Integer)((Iterator)localObject).next();
      if (((SMSBodyKeywordInfo)bodyKeyword2Pattern.get(localInteger.intValue())).match(paramString1, paramString2, paramString3)) {
        localArrayList.add(((SMSBodyKeywordInfo)bodyKeyword2Pattern.get(localInteger.intValue())).getPatternName());
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.SMSDic2Pattern
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */