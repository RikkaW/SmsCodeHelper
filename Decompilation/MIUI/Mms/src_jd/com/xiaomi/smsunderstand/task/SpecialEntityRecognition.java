package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.Log;
import com.xiaomi.common.StringIntInt;
import com.xiaomi.nlp.ParseResult;
import com.xiaomi.nlp.Parser;
import com.xiaomi.nlp.PatternForNER;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class SpecialEntityRecognition
{
  private static boolean ifInitial = false;
  private static HashMap<String, Parser> parserFileName2Parser;
  private static Set<String> parserToFree;
  
  public static List<EntityInfo> findKnowledge(String paramString1, String paramString2, HashSet<String> paramHashSet)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    if (!ifInitial) {
      return localArrayList;
    }
    int i = 1;
    Iterator localIterator1 = paramHashSet.iterator();
    label26:
    Object localObject;
    int j;
    while (localIterator1.hasNext())
    {
      localObject = (String)localIterator1.next();
      paramHashSet = (Parser)parserFileName2Parser.get(localObject);
      paramString2 = paramHashSet;
      if (paramHashSet == null)
      {
        long l1 = System.currentTimeMillis();
        paramString2 = new Parser(SMSUnderstand.dictionaryPath + "/patterns/" + (String)localObject + ".pattern");
        long l2 = System.currentTimeMillis();
        Log.i("Time", localObject + " load time:" + (l2 - l1) + " ms.");
        parserFileName2Parser.put(localObject, paramString2);
        Log.println("no find:" + (String)localObject);
      }
      if (paramString2.matchVersion(SMSUnderstand.getPlatform(), SMSUnderstand.getSystemLevel(), SMSUnderstand.getVersion()) != null)
      {
        paramString2 = paramString2.findKnowledge(paramString1, 0.3D);
        j = 1;
        paramHashSet = paramString2.iterator();
      }
    }
    Iterator localIterator2;
    for (;;)
    {
      if (!paramHashSet.hasNext())
      {
        i += 1;
        break label26;
        break;
      }
      localObject = (ParseResult)paramHashSet.next();
      localIterator2 = knowledge.entrySet().iterator();
      if (localIterator2.hasNext()) {
        break label279;
      }
      j += 1;
    }
    label279:
    Map.Entry localEntry = (Map.Entry)localIterator2.next();
    if (((StringIntInt)localEntry.getValue()).getName() == null) {}
    for (paramString2 = paramString1.substring(((StringIntInt)localEntry.getValue()).getStartIndex(), ((StringIntInt)localEntry.getValue()).getEndIndex());; paramString2 = ((StringIntInt)localEntry.getValue()).getName())
    {
      EntityInfo localEntityInfo = new EntityInfo();
      localEntityInfo.setTarget(paramString2);
      localEntityInfo.noNomal();
      localEntityInfo.setEntityType(EntityType.SPECIALENTITY);
      localEntityInfo.setGroupEntity(String.valueOf(i) + "_" + ((ParseResult)localObject).getTask().toLowerCase());
      localEntityInfo.setRemark(String.valueOf(j) + "_" + ((String)localEntry.getKey()).toLowerCase());
      localEntityInfo.setConfidence(getPatternscore);
      localEntityInfo.setStartPosition(((StringIntInt)localEntry.getValue()).getStartIndex());
      localEntityInfo.setEndPosition(((StringIntInt)localEntry.getValue()).getEndIndex());
      localEntityInfo.setGroupStartPosition(((ParseResult)localObject).getStartPositionInSentence());
      localEntityInfo.setGroupEndPosition(((ParseResult)localObject).getEndPositionInSentence());
      localArrayList.add(localEntityInfo);
      break;
    }
  }
  
  public static boolean freeParseHard()
  {
    if (!ifInitial) {
      return false;
    }
    Iterator localIterator;
    if (parserToFree != null) {
      localIterator = parserToFree.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        parserToFree = null;
        return true;
      }
      String str = (String)localIterator.next();
      parserFileName2Parser.remove(str);
      Log.i("Time", str + " free finish.");
    }
  }
  
  public static boolean freeParseHard(String paramString)
  {
    if (!ifInitial) {
      return false;
    }
    parserFileName2Parser.remove(paramString);
    Log.i("Time", paramString + " free finish.");
    return true;
  }
  
  public static boolean freeParseSoft(String paramString)
  {
    if (!ifInitial) {
      return false;
    }
    if (parserToFree == null) {
      parserToFree = new HashSet(10);
    }
    parserToFree.add(paramString);
    Log.i("Time", paramString + " free finish.");
    return true;
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    parserFileName2Parser = null;
    ifInitial = false;
    return true;
  }
  
  public static boolean initial()
    throws Exception
  {
    if (ifInitial) {
      return true;
    }
    Log.i("SpecialEntityRecognition", "inital SpecialEntity!");
    parserFileName2Parser = new HashMap();
    ifInitial = true;
    return true;
  }
  
  public static boolean loadParse(String paramString)
  {
    if (!ifInitial) {
      return false;
    }
    if ((Parser)parserFileName2Parser.get(paramString) == null)
    {
      long l1 = System.currentTimeMillis();
      Parser localParser = new Parser(SMSUnderstand.dictionaryPath + "/patterns/" + paramString + ".pattern");
      long l2 = System.currentTimeMillis();
      Log.i("Time", paramString + " load time:" + (l2 - l1) + " ms.");
      parserFileName2Parser.put(paramString, localParser);
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.SpecialEntityRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */