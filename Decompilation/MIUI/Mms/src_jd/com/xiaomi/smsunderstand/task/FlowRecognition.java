package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.OntologyTaskFrame;
import com.xiaomi.smsunderstand.AttrInfo;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.OntologyResults;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlowRecognition
{
  private static double flowFinish;
  private static List<List> flowForbidWordTable;
  private static String flowForbidWordTableFileName;
  private static Set<String> flowFrontGuideWords = new HashSet();
  private static String flowFrontGuideWordsFileName = null;
  private static int flowFrontGuideWordsMaxLength;
  private static List<List> flowKeyWordAndScoreTable;
  private static Map<String, Double> flowKeyWordsAndScore;
  private static String flowKeyWordsAndScoreFileName;
  private static int flowKeyWordsAndScoreMaxLength;
  private static Pattern flowPat = Pattern.compile("^[0-9]+((\\.)[0-9]+)?(mb|gb|kb|tb|m|g|k|t)$", 2);
  private static List<List> flowPredictWordTable;
  private static String[] flowTag;
  private static double flowThrAbs;
  private static double flowThrRate;
  private static boolean ifInitial = false;
  private static double parseError;
  private static String[] units;
  
  static
  {
    flowKeyWordsAndScore = new HashMap();
    flowKeyWordsAndScoreFileName = null;
    flowKeyWordAndScoreTable = null;
    flowPredictWordTable = null;
    flowForbidWordTableFileName = null;
    flowForbidWordTable = null;
    flowThrRate = 0.1D;
    flowThrAbs = 5.0D;
    flowTag = new String[] { "daily_total", "daily_used", "daily_remained", "daily_exceed", "add_total", "add_used", "add_remained", "add_exceed", "leisure_total", "leisure_used", "leisure_remained", "leisure_exceed" };
    flowFinish = Double.MAX_VALUE;
    parseError = -6000000.0D;
    units = new String[] { "G", "M", "K" };
  }
  
  private static List<EntityInfo> analysisFlowFromSimplePart(List<List<Integer>> paramList, int paramInt, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i1 = ((Integer)((List)paramList.get(0)).get(paramInt)).intValue();
    int i2 = ((Integer)((List)paramList.get(1)).get(paramInt)).intValue();
    int i = i1;
    label76:
    label209:
    label655:
    label848:
    label852:
    label1239:
    label1248:
    label1380:
    label1399:
    label1538:
    label1638:
    label1640:
    for (;;)
    {
      if (i > i2)
      {
        paramList = localArrayList.iterator();
        i = 0;
        if (paramList.hasNext()) {
          break label1248;
        }
        paramList = localArrayList.iterator();
      }
      for (;;)
      {
        Object localObject3;
        Object localObject4;
        if (!paramList.hasNext())
        {
          if (localArrayList != null) {
            filtForbidWorsd(localArrayList, paramString, i1, i2);
          }
          return localArrayList;
          int m = 0;
          for (;;)
          {
            if (m >= ((List)flowKeyWordAndScoreTable.get(0)).size()) {}
            int k;
            for (int j = 0;; j = 1)
            {
              if (j != 0) {
                break label1640;
              }
              i += 1;
              break;
              if (!paramString.startsWith((String)((List)flowKeyWordAndScoreTable.get(0)).get(m), i)) {
                break label1239;
              }
              k = 0;
              localObject3 = "";
              j = i - 1;
              if (j >= i1) {
                break label655;
              }
              Object localObject1 = localObject3;
              if (((String)localObject3).indexOf('.') != ((String)localObject3).lastIndexOf('.')) {
                localObject1 = ((String)localObject3).substring(((String)localObject3).indexOf('.') + 1);
              }
              localObject4 = localObject1;
              localObject3 = localObject1;
              try
              {
                if (((String)localObject1).contains("."))
                {
                  localObject4 = localObject1;
                  localObject3 = localObject1;
                  if (i - ((String)localObject1).length() == i1)
                  {
                    localObject3 = localObject1;
                    localObject4 = brotherFilt(paramList, paramInt, (String)localObject1, paramString);
                  }
                }
                localObject3 = localObject4;
                Double.valueOf((String)localObject4).doubleValue();
                localObject3 = localObject4;
                localObject1 = new EntityInfo();
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setStartPosition(i - ((String)localObject4).length() - k);
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setEndPosition(((String)((List)flowKeyWordAndScoreTable.get(0)).get(m)).length() + (i - 1));
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setConfidence(((Double)((List)flowKeyWordAndScoreTable.get(1)).get(m)).doubleValue());
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setTarget(localObject4 + (String)((List)flowKeyWordAndScoreTable.get(0)).get(m));
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setTarget_nomalFLOW(localObject4 + regularization((String)((List)flowKeyWordAndScoreTable.get(0)).get(m)));
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setEntityType(EntityType.FLOW);
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setRemark((String)((List)flowKeyWordAndScoreTable.get(0)).get(m));
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setNumberCount(((String)localObject4).length());
                localObject3 = localObject4;
                ((EntityInfo)localObject1).setEngCharCount(((String)((List)flowKeyWordAndScoreTable.get(0)).get(m)).length());
                localObject3 = localObject4;
                localArrayList.add(localObject1);
              }
              catch (Exception localException1)
              {
                while ((localObject3 == null) || (((String)localObject3).length() != 0) || (!((String)((List)flowKeyWordAndScoreTable.get(0)).get(m)).contains(")"))) {}
                k = ((String)((List)flowKeyWordAndScoreTable.get(0)).get(m)).length();
                j = 0;
                n = k + i;
              }
              i = ((String)((List)flowKeyWordAndScoreTable.get(0)).get(m)).length() + i;
            }
            if ((paramString.charAt(j) == ' ') || (paramString.charAt(j) == '　')) {
              k += 1;
            }
            for (;;)
            {
              j -= 1;
              break;
              if ((paramString.charAt(j) != '.') && ((paramString.charAt(j) < '0') || (paramString.charAt(j) > '9'))) {
                break label209;
              }
              localObject3 = paramString.charAt(j) + (String)localObject3;
            }
            int n;
            if (n > i2)
            {
              k = j;
              j = k;
            }
            for (;;)
            {
              try
              {
                Double.valueOf((String)localObject3).doubleValue();
                EntityInfo localEntityInfo = new EntityInfo();
                localEntityInfo.setStartPosition(i);
                localEntityInfo.setEndPosition(((String)((List)flowKeyWordAndScoreTable.get(0)).get(m)).length() + i + ((String)localObject3).length() + j - 1);
                localEntityInfo.setConfidence(((Double)((List)flowKeyWordAndScoreTable.get(1)).get(m)).doubleValue());
                localEntityInfo.setTarget(paramString.substring(localEntityInfo.getStartPosition(), localEntityInfo.getEndPosition() + 1));
                localEntityInfo.setTarget_nomalFLOW(localEntityInfo.getTarget());
                localEntityInfo.setEntityType(EntityType.FLOW);
                localEntityInfo.setRemark((String)((List)flowKeyWordAndScoreTable.get(0)).get(m));
                localEntityInfo.setNumberCount(((String)localObject3).length());
                localEntityInfo.setEngCharCount(((String)((List)flowKeyWordAndScoreTable.get(0)).get(m)).length());
                localArrayList.add(localEntityInfo);
              }
              catch (Exception localException2) {}
              break;
              if ((paramString.charAt(n) == '.') || ((paramString.charAt(n) >= '0') && (paramString.charAt(n) <= '9')))
              {
                localObject2 = localObject3 + paramString.charAt(n);
                k = j;
              }
              do
              {
                n += 1;
                j = k;
                localObject3 = localObject2;
                break;
                if (paramString.charAt(n) >= 'a')
                {
                  k = j;
                  if (paramString.charAt(n) <= 'z') {
                    break label848;
                  }
                }
                if ((paramString.charAt(n) >= 'A') && (paramString.charAt(n) <= 'Z')) {
                  break label852;
                }
                j += 1;
                k = j;
                if (j >= 4) {
                  break label848;
                }
                k = j;
                localObject2 = localObject3;
              } while (((String)localObject3).length() <= 0);
            }
            m += 1;
          }
          localObject2 = (EntityInfo)paramList.next();
          if ((!((EntityInfo)localObject2).getTarget().equalsIgnoreCase("2G")) && (!((EntityInfo)localObject2).getTarget().equalsIgnoreCase("3G")) && (!((EntityInfo)localObject2).getTarget().equalsIgnoreCase("4G"))) {
            break label76;
          }
          i += 1;
          break label76;
        }
        Object localObject2 = (EntityInfo)paramList.next();
        if ((((EntityInfo)localObject2).getTarget().equalsIgnoreCase("2G")) || (((EntityInfo)localObject2).getTarget().equalsIgnoreCase("3G")) || (((EntityInfo)localObject2).getTarget().equalsIgnoreCase("4G")))
        {
          if (i > 1) {
            ((EntityInfo)localObject2).setConfidence(((EntityInfo)localObject2).getConfidence() * 0.3D);
          }
        }
        else
        {
          localObject3 = ((List)flowPredictWordTable.get(0)).iterator();
          if (((Iterator)localObject3).hasNext()) {
            break label1538;
          }
          paramInt = 0;
        }
        for (;;)
        {
          if (paramInt != 0) {
            break label1638;
          }
          ((EntityInfo)localObject2).setConfidence(((EntityInfo)localObject2).getConfidence() * 0.85D);
          if (((EntityInfo)localObject2).getEndPosition() + 1 >= paramString.length()) {
            break;
          }
          paramInt = paramString.charAt(((EntityInfo)localObject2).getEndPosition() + 1);
          if (((paramInt < 48) || (paramInt > 57)) && ((paramInt < 97) || (paramInt > 122)) && ((paramInt < 65) || (paramInt > 90)) && (paramInt != 47) && (paramInt != 27599)) {
            break;
          }
          ((EntityInfo)localObject2).setConfidence(((EntityInfo)localObject2).getConfidence() * 0.3D);
          break;
          ((EntityInfo)localObject2).setConfidence(((EntityInfo)localObject2).getConfidence() * 0.88D);
          break label1380;
          localObject4 = ((Iterator)localObject3).next();
          String str = paramString.substring(i1, ((EntityInfo)localObject2).getStartPosition());
          paramInt = str.lastIndexOf((String)localObject4);
          if ((paramInt != -1) && (str.length() - paramInt - ((String)localObject4).length() < 4))
          {
            paramInt = 1;
          }
          else
          {
            paramInt = paramString.substring(((EntityInfo)localObject2).getEndPosition() + 1, i2 + 1).indexOf((String)localObject4);
            if ((paramInt == -1) || (paramInt >= 4)) {
              break label1399;
            }
            paramInt = 1;
          }
        }
      }
    }
  }
  
  private static String brotherFilt(List<List<Integer>> paramList, int paramInt, String paramString1, String paramString2)
  {
    int i;
    int j;
    Object localObject1;
    if (paramInt > 0)
    {
      i = ((Integer)((List)paramList.get(0)).get(paramInt - 1)).intValue();
      j = ((Integer)((List)paramList.get(1)).get(paramInt - 1)).intValue();
      Object localObject2;
      for (localObject1 = "";; localObject1 = localObject2)
      {
        if (i > j)
        {
          localObject1 = judge(paramString1, (String)localObject1);
          if (((String)localObject1).equals(paramString1)) {
            break;
          }
          return (String)localObject1;
        }
        if (paramString2.charAt(i) != '.')
        {
          localObject2 = localObject1;
          if (paramString2.charAt(i) >= '0')
          {
            localObject2 = localObject1;
            if (paramString2.charAt(i) > '9') {}
          }
        }
        else
        {
          localObject2 = localObject1 + paramString2.charAt(i);
        }
        i += 1;
      }
    }
    for (;;)
    {
      if (paramInt < ((List)paramList.get(0)).size() - 1)
      {
        i = ((Integer)((List)paramList.get(0)).get(paramInt + 1)).intValue();
        j = ((Integer)((List)paramList.get(1)).get(paramInt + 1)).intValue();
        paramList = "";
        paramInt = i;
        label246:
        if (paramInt <= j) {}
      }
      for (paramList = judge(paramString1, paramList);; paramList = (List<List<Integer>>)localObject1)
      {
        localObject1 = paramList;
        if (paramList != null) {
          break;
        }
        return paramString1;
        if (paramString2.charAt(paramInt) != '.')
        {
          localObject1 = paramList;
          if (paramString2.charAt(paramInt) >= '0')
          {
            localObject1 = paramList;
            if (paramString2.charAt(paramInt) > '9') {}
          }
        }
        else
        {
          localObject1 = paramList + paramString2.charAt(paramInt);
        }
        paramInt += 1;
        paramList = (List<List<Integer>>)localObject1;
        break label246;
      }
      localObject1 = null;
    }
  }
  
  public static boolean check(String paramString)
    throws IOException
  {
    return flowPat.matcher(paramString).find();
  }
  
  private static List<List<Integer>> divideWords(String paramString, int paramInt1, int paramInt2, List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new ArrayList());
    localArrayList.add(new ArrayList());
    int i = paramInt1;
    if ((i > paramInt2) || (paramInt1 > paramInt2)) {
      return localArrayList;
    }
    int k = 0;
    Iterator localIterator = paramList.iterator();
    label67:
    int m;
    if (!localIterator.hasNext()) {
      m = paramInt1;
    }
    for (int j = i;; j = m)
    {
      i = j;
      paramInt1 = m;
      if (k != 0) {
        break;
      }
      paramInt1 = m + 1;
      i = j;
      break;
      String str = (String)localIterator.next();
      if ((paramInt1 != paramInt2) && (!paramString.startsWith(str, paramInt1))) {
        break label67;
      }
      k = 1;
      if (paramInt1 - 1 > i)
      {
        ((List)localArrayList.get(0)).add(Integer.valueOf(i));
        ((List)localArrayList.get(1)).add(Integer.valueOf(paramInt1 - 1));
      }
      m = paramInt1 + str.length();
    }
  }
  
  private static void filtForbidWorsd(List<EntityInfo> paramList, String paramString, int paramInt1, int paramInt2)
  {
    paramList = paramList.iterator();
    if (!paramList.hasNext()) {
      return;
    }
    EntityInfo localEntityInfo = (EntityInfo)paramList.next();
    String str1 = paramString.substring(localEntityInfo.getStartPosition(), localEntityInfo.getEndPosition() + 1).toUpperCase();
    int j = 0;
    label52:
    String str2;
    String str3;
    String str4;
    double d;
    int i;
    if (j < ((List)flowForbidWordTable.get(0)).size())
    {
      str2 = (String)((List)flowForbidWordTable.get(0)).get(j);
      str3 = (String)((List)flowForbidWordTable.get(1)).get(j);
      str4 = (String)((List)flowForbidWordTable.get(2)).get(j);
      d = ((Double)((List)flowForbidWordTable.get(3)).get(j)).doubleValue();
      if (!str3.equals("end")) {
        break label270;
      }
      if (!str1.endsWith(str2)) {
        break label297;
      }
      i = 1;
    }
    for (;;)
    {
      if ((i != 0) && (paramString.substring(Math.max(localEntityInfo.getStartPosition() - str4.length() - 5, paramInt1), Math.min(paramInt2, localEntityInfo.getEndPosition() + str4.length() + 4)).contains(str4))) {
        localEntityInfo.setConfidence(localEntityInfo.getConfidence() * d);
      }
      j += 1;
      break label52;
      break;
      label270:
      if ((str3.equals("full")) && (str1.equals(str2))) {
        i = 1;
      } else {
        label297:
        i = 0;
      }
    }
  }
  
  private static boolean filtNotFullMsg(String paramString)
  {
    Iterator localIterator = getSplitTokens(3).iterator();
    do
    {
      if (!localIterator.hasNext()) {}
      try
      {
        if ((paramString.charAt(1) < '0') || (paramString.charAt(1) > '9') || (paramString.charAt(3) < '0') || (paramString.charAt(3) > '9')) {
          break;
        }
        if (paramString.charAt(1) >= '0')
        {
          int i = paramString.charAt(1);
          if (i <= 57) {
            break;
          }
        }
        return false;
      }
      catch (Exception localException)
      {
        Log.println(paramString);
      }
    } while (!paramString.startsWith((String)localIterator.next()));
    return false;
    return true;
  }
  
  public static List<EntityInfo> findKnowledge(String paramString)
    throws IOException
  {
    int j = 0;
    if (!ifInitial) {
      initial();
    }
    if (!filtNotFullMsg(paramString)) {
      return null;
    }
    Object localObject = paramString + "\n";
    ArrayList localArrayList = new ArrayList();
    int i;
    if ((((String)localObject).contains("；")) || (((String)localObject).contains(";")) || (StringProcess.count((String)localObject, "\r") > 2) || (StringProcess.count((String)localObject, "\n") > 2) || (StringProcess.count((String)localObject, "\\n") > 2) || (StringProcess.count((String)localObject, "\\r") > 2))
    {
      paramString = getSplitTokens(1);
      paramString = divideWords((String)localObject, 0, ((String)localObject).length() - 1, paramString);
      i = 0;
      label145:
      if (i < ((List)paramString.get(0)).size()) {
        break label212;
      }
      paramString = localArrayList.iterator();
      label172:
      if (paramString.hasNext()) {
        break label234;
      }
      i = j;
      label184:
      paramString = localArrayList.iterator();
    }
    for (;;)
    {
      if (!paramString.hasNext())
      {
        return localArrayList;
        paramString = getSplitTokens(2);
        break;
        label212:
        localArrayList.addAll(analysisFlowFromSimplePart(paramString, i, (String)localObject));
        i += 1;
        break label145;
        label234:
        localObject = (EntityInfo)paramString.next();
        if (!((EntityInfo)localObject).getRemark().equalsIgnoreCase("g")) {
          break label172;
        }
        double d = Double.valueOf(((EntityInfo)localObject).getTarget().substring(0, ((EntityInfo)localObject).getNumberCount())).doubleValue();
        if ((d == 2.0D) || (d == 3.0D) || (d == 4.0D)) {
          break label172;
        }
        i = 1;
        break label184;
      }
      localObject = (EntityInfo)paramString.next();
      if ((i == 0) && ((((EntityInfo)localObject).getTarget().equalsIgnoreCase("2G")) || (((EntityInfo)localObject).getTarget().equalsIgnoreCase("3G")) || (((EntityInfo)localObject).getTarget().equalsIgnoreCase("4G")))) {
        ((EntityInfo)localObject).setConfidence(((EntityInfo)localObject).getConfidence() * 0.7D);
      }
      ((EntityInfo)localObject).setEndPosition(((EntityInfo)localObject).getEndPosition() + 1);
    }
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    flowFrontGuideWords.clear();
    flowFrontGuideWordsMaxLength = -1;
    flowKeyWordsAndScore.clear();
    flowKeyWordAndScoreTable = null;
    flowPredictWordTable = null;
    flowForbidWordTable = null;
    ifInitial = false;
    return true;
  }
  
  public static List<String> getSplitTokens(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("\\r");
    localArrayList.add("\\n");
    localArrayList.add("\n");
    localArrayList.add(";");
    localArrayList.add("\r");
    localArrayList.add("。");
    localArrayList.add("；");
    localArrayList.add("！");
    localArrayList.add("？");
    localArrayList.add("!");
    localArrayList.add("?");
    if (paramInt == 1) {}
    do
    {
      return localArrayList;
      localArrayList.add(",");
      localArrayList.add("，");
    } while (paramInt == 2);
    localArrayList.add("/");
    localArrayList.add("、");
    localArrayList.add(".");
    localArrayList.add("\\");
    return localArrayList;
  }
  
  public static boolean initial()
    throws IOException
  {
    if (ifInitial) {
      return true;
    }
    flowFrontGuideWordsFileName = SMSUnderstand.dictionaryPath + "/flowFrontGuideWords.txt";
    flowFrontGuideWordsMaxLength = FileOperator.readDic2Set(flowFrontGuideWordsFileName, flowFrontGuideWords);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("String");
    flowPredictWordTable = FileOperator.readDic2Form(flowFrontGuideWordsFileName, localArrayList);
    flowKeyWordsAndScoreFileName = SMSUnderstand.dictionaryPath + "/flowKeyWordsAndScore.txt";
    flowForbidWordTableFileName = SMSUnderstand.dictionaryPath + "/flowForbidWordTable.txt";
    flowKeyWordsAndScoreMaxLength = FileOperator.readDic2Map(flowKeyWordsAndScoreFileName, flowKeyWordsAndScore);
    localArrayList = new ArrayList();
    localArrayList.add("String");
    localArrayList.add("double");
    flowKeyWordAndScoreTable = FileOperator.readDic2Form(flowKeyWordsAndScoreFileName, localArrayList);
    localArrayList = new ArrayList();
    localArrayList.add("String");
    localArrayList.add("String");
    localArrayList.add("String");
    localArrayList.add("Double");
    flowForbidWordTable = FileOperator.readDic2Form(flowForbidWordTableFileName, localArrayList);
    ifInitial = true;
    return true;
  }
  
  private static String judge(String paramString1, String paramString2)
  {
    if (paramString2.length() <= 1) {}
    while ((!paramString2.contains(".")) || (paramString1.indexOf(".") != paramString2.indexOf("."))) {
      return paramString1;
    }
    return paramString1.substring(paramString1.indexOf(".") + 1);
  }
  
  public static boolean lackOfFlow(OntologyResults paramOntologyResults, OntologyTaskFrame paramOntologyTaskFrame)
  {
    double[] arrayOfDouble = regularizationFlow(paramOntologyResults, paramOntologyTaskFrame);
    if (arrayOfDouble == null) {
      return false;
    }
    int i = 0;
    double d2;
    double d1;
    if (i >= flowTag.length)
    {
      d2 = 0.0D;
      d1 = 0.0D;
      i = 0;
    }
    for (;;)
    {
      if (i >= 3)
      {
        if ((d2 <= 0.0D) || (d1 > flowThrAbs)) {
          break label183;
        }
        Log.println("lackOfFlow!");
        return true;
        int j = paramOntologyTaskFrame.getAttrIndexByName(flowTag[i], i);
        if (j == -1) {
          arrayOfDouble[i] = 0.0D;
        }
        for (;;)
        {
          i += 1;
          break;
          ((AttrInfo)paramOntologyResults.getAttrInfos().get(j)).setValue(String.valueOf(arrayOfDouble[i]));
        }
      }
      double d4 = arrayOfDouble[(i * 4)];
      double d3 = arrayOfDouble[(i * 4 + 2)];
      if (arrayOfDouble[(i * 4 + 3)] > 0.0D) {
        return true;
      }
      d2 += d4;
      d1 += d3;
      i += 1;
    }
    label183:
    if ((d2 > 0.0D) && (d1 / d2 < flowThrRate))
    {
      Log.println("lackOfFlow!");
      return true;
    }
    return false;
  }
  
  private static String regularization(String paramString)
  {
    paramString = paramString.toLowerCase();
    if (paramString.contains("g")) {
      return "GB";
    }
    if (paramString.contains("m")) {
      return "MB";
    }
    if (paramString.contains("k")) {
      return "KB";
    }
    return null;
  }
  
  public static double regularizationFlow(String paramString)
  {
    if (paramString.length() == 1) {}
    String str1;
    double d2;
    int k;
    int m;
    switch (paramString.charAt(0))
    {
    default: 
      str1 = paramString.toUpperCase();
      d2 = 0.0D;
      k = 0;
      m = 0;
    }
    double d1;
    for (;;)
    {
      if (k >= paramString.length()) {}
      while (m >= units.length)
      {
        if (k < paramString.length())
        {
          if (StringProcess.isRealNumber(paramString)) {
            break;
          }
          d2 = parseError;
        }
        return d2;
        return flowFinish;
      }
      int n = str1.indexOf(units[m], k);
      if (n <= 0)
      {
        m += 1;
      }
      else
      {
        int i;
        int j;
        if (n < paramString.length() - 1)
        {
          i = paramString.charAt(n + 1);
          if (i == 98)
          {
            i = 1;
            j = 0;
            d1 = 1.0D;
            if (m != 0) {
              break label258;
            }
            d1 = 1024.0D;
          }
        }
        double d3;
        String str2;
        for (;;)
        {
          d3 = d1;
          if (j == 0) {
            d3 = d1 / 8.0D;
          }
          str2 = paramString.substring(k, n);
          if (StringProcess.isRealNumber(str2)) {
            break label271;
          }
          Log.i("wpz", paramString);
          return parseError;
          if (i == 66)
          {
            i = 1;
            j = 1;
            break;
          }
          i = 0;
          j = 1;
          break;
          i = 0;
          j = 1;
          break;
          label258:
          if (m == 2) {
            d1 = 9.765625E-4D;
          }
        }
        try
        {
          label271:
          d1 = Double.parseDouble(str2);
          d2 += d3 * d1;
          if (i != 0) {
            k = n + 2;
          }
        }
        catch (Exception localException)
        {
          Log.i("wpz", paramString);
          return parseError;
        }
        k = n + 1;
      }
    }
    try
    {
      d1 = Double.parseDouble(paramString);
      return d1;
    }
    catch (Exception paramString) {}
    return parseError;
  }
  
  public static double[] regularizationFlow(OntologyResults paramOntologyResults, OntologyTaskFrame paramOntologyTaskFrame)
  {
    double[] arrayOfDouble = new double[flowTag.length];
    HashMap localHashMap = new HashMap();
    int i = 0;
    if (i >= flowTag.length)
    {
      i = 0;
      label29:
      if (i < 3) {
        break label251;
      }
      i = 0;
      label36:
      if (i < arrayOfDouble.length) {
        break label274;
      }
      paramOntologyResults = localHashMap.values().iterator();
    }
    for (;;)
    {
      if (!paramOntologyResults.hasNext())
      {
        return arrayOfDouble;
        j = paramOntologyTaskFrame.getAttrIndexByName(flowTag[i], i);
        if (j == -1) {
          arrayOfDouble[i] = 0.0D;
        }
        for (;;)
        {
          i += 1;
          break;
          localObject = ((AttrInfo)paramOntologyResults.getAttrInfos().get(j)).getValue();
          if (localObject == null)
          {
            arrayOfDouble[i] = 0.0D;
          }
          else
          {
            String[] arrayOfString = ((String)localObject).split("[:#]");
            if (arrayOfString.length == 2)
            {
              arrayOfDouble[i] = Double.parseDouble(arrayOfString[1]);
            }
            else
            {
              j = 0;
              while (j < arrayOfString.length)
              {
                Integer localInteger = Integer.valueOf(arrayOfString[j]);
                Map localMap = (Map)localHashMap.get(localInteger);
                localObject = localMap;
                if (localMap == null)
                {
                  localObject = new HashMap();
                  localHashMap.put(localInteger, localObject);
                }
                ((Map)localObject).put(Integer.valueOf(i), Double.valueOf(Double.parseDouble(arrayOfString[(j + 1)])));
                j += 2;
              }
            }
          }
        }
        label251:
        if (arrayOfDouble[(i * 4 + 3)] > 0.0D) {
          return arrayOfDouble;
        }
        i += 1;
        break label29;
        label274:
        if (!regularizationGroup(arrayOfDouble, i))
        {
          Log.i("Time", "FlowRecognition.regularizationFlow Error:" + paramOntologyResults.toString());
          Log.println("FlowRecognition.regularizationFlow Error:" + paramOntologyResults.toString());
          return null;
        }
        i += 4;
        break label36;
      }
      paramOntologyTaskFrame = (Map)paramOntologyResults.next();
      Object localObject = new double[4];
      int j = regularizationParseGroup((double[])localObject, paramOntologyTaskFrame);
      if (j >= 0)
      {
        i = 0;
        while (i < localObject.length)
        {
          int k = j * 4 + i;
          arrayOfDouble[k] += localObject[i];
          i += 1;
        }
      }
    }
  }
  
  private static boolean regularizationGroup(double[] paramArrayOfDouble, int paramInt)
  {
    if (Math.abs(paramArrayOfDouble[(paramInt + 0)] - paramArrayOfDouble[(paramInt + 1)] - paramArrayOfDouble[(paramInt + 2)] + paramArrayOfDouble[(paramInt + 3)]) <= 1.0E-6D) {}
    for (;;)
    {
      return true;
      if ((paramArrayOfDouble[(paramInt + 1)] == flowFinish) || (paramArrayOfDouble[(paramInt + 3)] > 0.0D))
      {
        paramArrayOfDouble[(paramInt + 2)] = 0.0D;
        if (paramArrayOfDouble[(paramInt + 0)] > 0.0D) {
          paramArrayOfDouble[(paramInt + 1)] = (paramArrayOfDouble[(paramInt + 0)] - paramArrayOfDouble[(paramInt + 2)] + paramArrayOfDouble[(paramInt + 3)]);
        }
      }
      while (Math.abs(paramArrayOfDouble[(paramInt + 0)] - paramArrayOfDouble[(paramInt + 1)] - paramArrayOfDouble[(paramInt + 2)] + paramArrayOfDouble[(paramInt + 3)]) > 1.0E-6D)
      {
        return false;
        paramArrayOfDouble[(paramInt + 0)] = paramArrayOfDouble[(paramInt + 3)];
        paramArrayOfDouble[(paramInt + 1)] = 0.0D;
        continue;
        if (paramArrayOfDouble[(paramInt + 2)] == 0.0D)
        {
          paramArrayOfDouble[(paramInt + 2)] = (paramArrayOfDouble[(paramInt + 0)] - paramArrayOfDouble[(paramInt + 1)]);
        }
        else if (paramArrayOfDouble[(paramInt + 2)] > 0.0D)
        {
          paramArrayOfDouble[(paramInt + 3)] = 0.0D;
          if (paramArrayOfDouble[(paramInt + 0)] == 0.0D) {
            paramArrayOfDouble[(paramInt + 0)] = (paramArrayOfDouble[(paramInt + 1)] + paramArrayOfDouble[(paramInt + 2)]);
          } else if (paramArrayOfDouble[(paramInt + 1)] == 0.0D) {
            paramArrayOfDouble[(paramInt + 1)] = (paramArrayOfDouble[(paramInt + 0)] - paramArrayOfDouble[(paramInt + 2)]);
          }
        }
        else if ((paramArrayOfDouble[(paramInt + 2)] == parseError) && (paramArrayOfDouble[(paramInt + 0)] >= 0.0D) && (paramArrayOfDouble[(paramInt + 1)] >= 0.0D))
        {
          paramArrayOfDouble[(paramInt + 2)] = (paramArrayOfDouble[(paramInt + 0)] - paramArrayOfDouble[(paramInt + 1)] + paramArrayOfDouble[(paramInt + 3)]);
        }
      }
    }
  }
  
  private static int regularizationParseGroup(double[] paramArrayOfDouble, Map<Integer, Double> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    int j;
    for (int i = -1;; i = j)
    {
      if (!paramMap.hasNext())
      {
        if (regularizationGroup(paramArrayOfDouble, 0)) {
          break;
        }
        return -1;
      }
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      j = ((Integer)localEntry.getKey()).intValue() / 4;
      if ((i >= 0) && (i != j))
      {
        Log.i("Time", "FlowRecognition.regularizationParseGroup Error:" + j + "\t" + i);
        Log.println("FlowRecognition.regularizationParseGroup Error:" + j + "\t" + i);
        return -1;
      }
      paramArrayOfDouble[(((Integer)localEntry.getKey()).intValue() % 4)] = ((Double)localEntry.getValue()).doubleValue();
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.FlowRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */