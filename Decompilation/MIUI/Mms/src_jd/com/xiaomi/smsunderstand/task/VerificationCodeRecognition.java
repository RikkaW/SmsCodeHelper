package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringIntInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.ParseResult;
import com.xiaomi.nlp.Parser;
import com.xiaomi.nlp.PatternForNER;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class VerificationCodeRecognition
{
  private static boolean ifInitial = false;
  private static Parser parser;
  private static HashSet<String> verificationCodeGuideWords = new HashSet();
  private static String verificationCodeGuideWordsFileName = null;
  private static int verificationCodeGuideWordsMaxLength = -1;
  
  public static List<EntityInfo> findKnowledge(String paramString)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    if (!ifInitial) {}
    Iterator localIterator1;
    do
    {
      return localArrayList;
      localIterator1 = parser.findKnowledge(paramString, 0.3D).iterator();
    } while (!localIterator1.hasNext());
    ParseResult localParseResult = (ParseResult)localIterator1.next();
    Iterator localIterator2 = knowledge.entrySet().iterator();
    label67:
    Map.Entry localEntry;
    if (localIterator2.hasNext())
    {
      localEntry = (Map.Entry)localIterator2.next();
      if (((StringIntInt)localEntry.getValue()).getName() != null) {
        break label256;
      }
    }
    label256:
    for (String str = paramString.substring(((StringIntInt)localEntry.getValue()).getStartIndex(), ((StringIntInt)localEntry.getValue()).getEndIndex());; str = ((StringIntInt)localEntry.getValue()).getName())
    {
      EntityInfo localEntityInfo = new EntityInfo();
      localEntityInfo.setTarget(str);
      localEntityInfo.noNomal();
      if (!((String)localEntry.getKey()).equals("verificationCode")) {
        break label67;
      }
      localEntityInfo.setEntityType(EntityType.VERIFICATIONCODE);
      localEntityInfo.setRemark(localParseResult.getPattern().toString());
      localEntityInfo.setConfidence(getPatternscore);
      localEntityInfo.setStartPosition(((StringIntInt)localEntry.getValue()).getStartIndex());
      localEntityInfo.setEndPosition(((StringIntInt)localEntry.getValue()).getEndIndex());
      localArrayList.add(localEntityInfo);
      break label67;
      break;
    }
  }
  
  public static int findSegPunctuation(String paramString, int paramInt1, int paramInt2)
  {
    for (;;)
    {
      if (paramInt1 >= paramInt2)
      {
        i = -1;
        return i;
      }
      int i = paramInt1;
      switch (paramString.charAt(paramInt1))
      {
      }
      paramInt1 += 1;
    }
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    verificationCodeGuideWords.clear();
    verificationCodeGuideWordsMaxLength = -1;
    parser = null;
    ifInitial = false;
    return true;
  }
  
  public static boolean initial()
    throws IOException
  {
    if (ifInitial) {
      return true;
    }
    verificationCodeGuideWordsFileName = SMSUnderstand.dictionaryPath + "/verificationCodeGuideWords.txt";
    Iterator localIterator = FileOperator.readFile(verificationCodeGuideWordsFileName).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        parser = new Parser(SMSUnderstand.dictionaryPath + "/patterns/VerificationCode.pattern");
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
          verificationCodeGuideWords.add(str.toLowerCase());
          if (verificationCodeGuideWordsMaxLength < str.length()) {
            verificationCodeGuideWordsMaxLength = str.length();
          }
        }
        i += 1;
      }
    }
  }
  
  public static double isRightNumber(EntityInfo paramEntityInfo, String paramString, int paramInt)
  {
    double d1;
    if (!ifInitial) {
      d1 = 0.0D;
    }
    double d2;
    label198:
    label208:
    label226:
    label392:
    do
    {
      return d1;
      int i = paramEntityInfo.getTarget().length();
      if (i != paramEntityInfo.getTarget_nomal().length()) {
        return 0.0D;
      }
      if ((i < 4) || (i > 18)) {
        return 0.0D;
      }
      if (paramEntityInfo.getNumberCount() + paramEntityInfo.getEngCharCount() != i) {
        return 0.0D;
      }
      Object localObject1 = StringProcess.findAllResultsFromList(paramString, verificationCodeGuideWords, verificationCodeGuideWordsMaxLength);
      if (((List)localObject1).size() <= 0) {
        return 0.0D;
      }
      Object localObject2 = paramString.substring(0, paramEntityInfo.getStartPosition());
      if ((((String)localObject2).endsWith("尾号")) || (((String)localObject2).endsWith("号为"))) {
        return 0.0D;
      }
      d2 = 1.0D / (0.1D + paramInt) + paramEntityInfo.getNumberCount() / (i * 5);
      d1 = d2;
      if (i != 4)
      {
        d1 = d2;
        if (i != 6)
        {
          if (i >= 4) {
            break label226;
          }
          paramInt = 3;
          d1 = d2 * (4.0D / (paramInt * i));
        }
      }
      int j = 1;
      paramInt = 0;
      localObject1 = ((List)localObject1).iterator();
      if (!((Iterator)localObject1).hasNext())
      {
        int k = Integer.MAX_VALUE;
        i = paramInt;
        paramInt = k;
      }
      for (;;)
      {
        if (i != 0) {
          break label392;
        }
        return 0.0D;
        paramInt = 1;
        break;
        localObject2 = (StringInt)((Iterator)localObject1).next();
        if (((StringInt)localObject2).getNum() < paramEntityInfo.getStartPosition())
        {
          if (findSegPunctuation(paramString, ((StringInt)localObject2).getNum() + ((StringInt)localObject2).getName().length(), paramEntityInfo.getStartPosition()) >= 0) {
            break label198;
          }
          i = 1;
          paramInt = 1;
          if (Integer.MAX_VALUE <= paramEntityInfo.getStartPosition() - ((StringInt)localObject2).getNum() - ((StringInt)localObject2).getName().length()) {
            break label208;
          }
          paramInt = paramEntityInfo.getStartPosition() - ((StringInt)localObject2).getNum() - ((StringInt)localObject2).getName().length();
          j = 1;
          continue;
        }
        if (findSegPunctuation(paramString, paramEntityInfo.getEndPosition() - 1, ((StringInt)localObject2).getNum()) >= 0) {
          break label198;
        }
        i = 1;
        paramInt = 1;
        if (Integer.MAX_VALUE <= ((StringInt)localObject2).getNum() - paramEntityInfo.getEndPosition()) {
          break label208;
        }
        paramInt = ((StringInt)localObject2).getNum() - paramEntityInfo.getEndPosition();
        j = -1;
      }
      if (paramInt < 0) {
        Log.i("VerificationCodeRecognition", "VerificationCodeRecognition.isRightNumber Error：" + paramString);
      }
      i = paramInt;
      if (j < 0) {
        i = paramInt * 4;
      }
      d2 = d1 + 0.5D / (i + 0.1D);
      d1 = d2;
    } while (d2 <= 1.0D);
    return 1.0D;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.VerificationCodeRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */