package com.xiaomi.nlp;

import com.xiaomi.common.Bisection;
import com.xiaomi.common.FileOperator;
import com.xiaomi.common.IntInt;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringIntInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.common.StringProcess.ASCType;
import com.xiaomi.common.StringString;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser
  extends VersionControl
{
  private static String[] segPatsSignal = { "+", "&&&" };
  public static long startMemory;
  private static int timeOutThr = 100;
  private double acceptThr;
  private boolean caseSensitive = true;
  private int charReplaceIndex = -1;
  private Map<String, ArrayList<StringString>> constant = new HashMap();
  private boolean containCHAR = false;
  private boolean containENG = false;
  private boolean containEnd = false;
  private boolean containNUM = false;
  private boolean containStart = false;
  private boolean containStartRegularExpress = false;
  private boolean containWildCard = false;
  private boolean containWildcard = false;
  private boolean currentStartRegular = false;
  private int currentStartRegularStartIndexInSource = 0;
  private int endTrimLength = 0;
  private int engReplaceIndex = -1;
  private boolean finalProduction = true;
  private BitSet forbidMatchIndex;
  private HashMap<Integer, Integer> keyWords2Count;
  private ParserKeywordsDic keywordsDic = new ParserKeywordsDic();
  private HashMap<String, StringIntInt> knowledge;
  private boolean mustKP = false;
  private NLP nlp;
  private Map<String, List<Integer>> noTerminal2patternIndex = new HashMap();
  private int numberReplaceIndex = -1;
  private boolean oneMatch = false;
  private String parserName;
  private List<PatternForNER> patterns;
  private int patternsCount = 0;
  private boolean processedMostLeftRegularPrefix = true;
  private boolean replaceSeg = false;
  private List<ParseResult> returnValue = null;
  private String segPunctuation = "[。！!？?；;]";
  private ArrayList<String> segments;
  private ArrayList<String> segments_pattern;
  private boolean startContainCHAR = false;
  private boolean startContainENG = false;
  private boolean startContainNUM = false;
  private List<Integer> startRegularExpressPatIndex = null;
  private int startTrimLength = 0;
  private String targetSegment = null;
  private String task;
  private long timeoutStart = System.currentTimeMillis();
  private int wildCardMaxMatchLength = 18;
  
  static
  {
    startMemory = -1L;
  }
  
  public Parser()
  {
    nlp = new NLP(wildCardMaxMatchLength);
  }
  
  public Parser(String paramString)
  {
    buildPattern(paramString);
    printMemory("buildPattern");
    nlp = new NLP(wildCardMaxMatchLength, constant);
  }
  
  private void addNoTerminal2patternIndex(String paramString, int paramInt)
  {
    List localList = (List)noTerminal2patternIndex.get(paramString);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new ArrayList();
      noTerminal2patternIndex.put(paramString, localObject);
    }
    ((List)localObject).add(Integer.valueOf(paramInt));
  }
  
  private boolean addOtherStartEndKeywords(String paramString, int[] paramArrayOfInt, List<int[]> paramList, List<Integer> paramList1, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramArrayOfInt[2] == paramArrayOfInt[1]) {
      return paramBoolean2;
    }
    paramString = keywordsDic.findFirstAll(paramString, paramArrayOfInt[1], paramArrayOfInt[2], 1);
    if ((paramString == null) || (paramString.size() == 0)) {
      return paramBoolean2;
    }
    paramList.remove(paramList.size() - 1);
    if (paramBoolean1) {
      paramList1.remove(paramList1.size() - 1);
    }
    int i = paramString.size() - 1;
    for (;;)
    {
      if (i < 0) {}
      int[] arrayOfInt;
      do
      {
        paramList.add(paramArrayOfInt);
        if (paramBoolean1) {
          paramList1.add(Integer.valueOf(paramList.size() - 1));
        }
        return paramBoolean2;
        arrayOfInt = (int[])paramString.get(i);
      } while ((arrayOfInt[1] != paramArrayOfInt[1]) || ((arrayOfInt[1] == paramArrayOfInt[1]) && (arrayOfInt[2] == paramArrayOfInt[2])));
      paramList.add(arrayOfInt);
      boolean bool = paramBoolean2;
      if (!paramBoolean2)
      {
        bool = paramBoolean2;
        if (findMust(keywordsDic.getKeywords(arrayOfInt[0]).getKeyWords())) {
          bool = true;
        }
      }
      if ((bool) && (keywordsDic.getKeywords(arrayOfInt[0]).isEndkeyWords())) {
        paramList1.add(Integer.valueOf(paramList.size() - 1));
      }
      i -= 1;
      paramBoolean2 = bool;
    }
  }
  
  private static void addTempMustKeywords(Map<Integer, Integer> paramMap, int paramInt)
  {
    setTempKeywords(paramMap, paramInt, 8);
    paramMap.put(Integer.valueOf(paramInt), Integer.valueOf(((Integer)paramMap.get(Integer.valueOf(paramInt))).intValue() + 65536));
  }
  
  private static void addTempMustKeywords(Map<Integer, Integer> paramMap, int paramInt1, int paramInt2)
  {
    setTempKeywords(paramMap, paramInt1, 8);
    paramMap.put(Integer.valueOf(paramInt1), Integer.valueOf(((Integer)paramMap.get(Integer.valueOf(paramInt1))).intValue() + 65536 * paramInt2));
  }
  
  private Boolean analyseConstraint(List<PatternActionContent> paramList, String[] paramArrayOfString, int paramInt)
  {
    Iterator localIterator1 = paramList.iterator();
    paramList = Boolean.valueOf(true);
    Object localObject1;
    label26:
    Iterator localIterator2;
    label62:
    int i;
    label75:
    do
    {
      if (!localIterator1.hasNext())
      {
        localObject1 = paramList;
        return (Boolean)localObject1;
      }
      localObject1 = (PatternActionContent)localIterator1.next();
      localObject2 = new StringBuffer();
      localIterator2 = contents.iterator();
      if (localIterator2.hasNext()) {
        break;
      }
      i = 1;
    } while (i == 0);
    Object localObject2 = ((StringBuffer)localObject2).toString();
    switch (keywords)
    {
    }
    for (;;)
    {
      localObject1 = paramList;
      if (!paramList.booleanValue()) {
        break label26;
      }
      break;
      StringInt localStringInt = (StringInt)localIterator2.next();
      i = localStringInt.getNum() - paramInt;
      if (i < 0)
      {
        i = 0;
        break label75;
      }
      if (i >= paramArrayOfString.length - 1)
      {
        i = 0;
        break label75;
      }
      if (localStringInt.getName().equals(""))
      {
        ((StringBuffer)localObject2).append(paramArrayOfString[i]);
        break label62;
      }
      ((StringBuffer)localObject2).append(paramArrayOfString[i]);
      break label62;
      continue;
      if (((String)localObject2).length() == 0)
      {
        paramList = Boolean.valueOf(false);
        continue;
        if (NLP.EqualDic_Seg((String)localObject2, parameter))
        {
          paramList = Boolean.valueOf(false);
          continue;
          if (!NLP.EqualDic_Seg((String)localObject2, parameter))
          {
            paramList = Boolean.valueOf(false);
            continue;
            if (!nlp.containDic((String)localObject2, parameter))
            {
              paramList = Boolean.valueOf(false);
              continue;
              if (nlp.containDic((String)localObject2, parameter))
              {
                paramList = Boolean.valueOf(false);
                continue;
                if (!nlp.containPOS((String)localObject2, parameter))
                {
                  paramList = Boolean.valueOf(false);
                  continue;
                  if (nlp.containPOS((String)localObject2, parameter))
                  {
                    paramList = Boolean.valueOf(false);
                    continue;
                    if (((String)localObject2).length() <= Integer.valueOf(parameter).intValue())
                    {
                      paramList = Boolean.valueOf(false);
                      continue;
                      if (((String)localObject2).length() >= Integer.valueOf(parameter).intValue())
                      {
                        paramList = Boolean.valueOf(false);
                        continue;
                        if (StringProcess.wordsCount((String)localObject2) <= Integer.valueOf(parameter).intValue())
                        {
                          paramList = Boolean.valueOf(false);
                          continue;
                          if (StringProcess.wordsCount((String)localObject2) >= Integer.valueOf(parameter).intValue())
                          {
                            paramList = Boolean.valueOf(false);
                            continue;
                            if (nlp.startWithPOS((String)localObject2, parameter))
                            {
                              paramList = Boolean.valueOf(false);
                              continue;
                              if (nlp.endWithPOS((String)localObject2, parameter))
                              {
                                paramList = Boolean.valueOf(false);
                                continue;
                                if (nlp.noEndWithPOS((String)localObject2, parameter))
                                {
                                  paramList = Boolean.valueOf(false);
                                  continue;
                                  if (nlp.endWithDic((String)localObject2, parameter))
                                  {
                                    paramList = Boolean.valueOf(false);
                                    continue;
                                    if (!nlp.endWithDic((String)localObject2, parameter))
                                    {
                                      paramList = Boolean.valueOf(false);
                                      continue;
                                      if (nlp.startWithDic((String)localObject2, parameter))
                                      {
                                        paramList = Boolean.valueOf(false);
                                        continue;
                                        if (!nlp.startWithDic((String)localObject2, parameter))
                                        {
                                          paramList = Boolean.valueOf(false);
                                          continue;
                                          if ((((String)localObject2).length() != 4) && (((String)localObject2).length() != 2))
                                          {
                                            paramList = Boolean.valueOf(false);
                                          }
                                          else if (!StringProcess.isNumber((String)localObject2))
                                          {
                                            paramList = Boolean.valueOf(false);
                                          }
                                          else
                                          {
                                            i = Integer.parseInt((String)localObject2);
                                            if ((i >= 100) && (i <= 999))
                                            {
                                              paramList = Boolean.valueOf(false);
                                            }
                                            else if (i >= 2018)
                                            {
                                              paramList = Boolean.valueOf(false);
                                              continue;
                                              if ((((String)localObject2).length() != 1) && (((String)localObject2).length() != 2))
                                              {
                                                paramList = Boolean.valueOf(false);
                                              }
                                              else if (!StringProcess.isNumber((String)localObject2))
                                              {
                                                paramList = Boolean.valueOf(false);
                                              }
                                              else
                                              {
                                                i = Integer.parseInt((String)localObject2);
                                                if ((i < 1) || (i > 12))
                                                {
                                                  paramList = Boolean.valueOf(false);
                                                  continue;
                                                  if ((((String)localObject2).length() != 1) && (((String)localObject2).length() != 2))
                                                  {
                                                    paramList = Boolean.valueOf(false);
                                                  }
                                                  else if (!StringProcess.isNumber((String)localObject2))
                                                  {
                                                    paramList = Boolean.valueOf(false);
                                                  }
                                                  else
                                                  {
                                                    i = Integer.parseInt((String)localObject2);
                                                    if ((i < 1) || (i > 31))
                                                    {
                                                      paramList = Boolean.valueOf(false);
                                                      continue;
                                                      if ((((String)localObject2).length() != 1) && (((String)localObject2).length() != 2))
                                                      {
                                                        paramList = Boolean.valueOf(false);
                                                      }
                                                      else if (!StringProcess.isNumber((String)localObject2))
                                                      {
                                                        paramList = Boolean.valueOf(false);
                                                      }
                                                      else if (Integer.parseInt((String)localObject2) > 24)
                                                      {
                                                        paramList = Boolean.valueOf(false);
                                                        continue;
                                                        if ((((String)localObject2).length() != 1) && (((String)localObject2).length() != 2))
                                                        {
                                                          paramList = Boolean.valueOf(false);
                                                        }
                                                        else if (!StringProcess.isNumber((String)localObject2))
                                                        {
                                                          paramList = Boolean.valueOf(false);
                                                        }
                                                        else if (Integer.parseInt((String)localObject2) > 60)
                                                        {
                                                          paramList = Boolean.valueOf(false);
                                                          continue;
                                                          if ((((String)localObject2).length() != 1) && (((String)localObject2).length() != 2)) {
                                                            paramList = Boolean.valueOf(false);
                                                          } else if (!StringProcess.isNumber((String)localObject2)) {
                                                            paramList = Boolean.valueOf(false);
                                                          } else if (Integer.parseInt((String)localObject2) > 60) {
                                                            paramList = Boolean.valueOf(false);
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  private void buildPattern(String paramString)
  {
    Object localObject1 = new ArrayList();
    label1036:
    label1157:
    do
    {
      for (;;)
      {
        try
        {
          FileOperator.readFile(paramString, (ArrayList)localObject1);
          parserName = FileOperator.getFileNameWithoutExtend(paramString);
          initialPatternsNumber((List)localObject1);
          localArrayList = new ArrayList();
          localIterator1 = ((ArrayList)localObject1).iterator();
          if (localIterator1.hasNext()) {
            continue;
          }
          printMemory("buildPattern -1");
          i = 0;
        }
        catch (IOException paramString)
        {
          try
          {
            ArrayList localArrayList;
            Iterator localIterator1;
            int i;
            Object localObject2;
            boolean bool;
            int k;
            Object localObject3 = new PatternForNER((String)localObject2, (String)localObject3, paramString);
            printMemory("buildPattern - end " + ((PatternForNER)localObject3).toString());
            TreeMap localTreeMap = new TreeMap();
            int m = patterns.size();
            boolean[] arrayOfBoolean = getKeyWords((PatternForNER)localObject3, localTreeMap);
            if (arrayOfBoolean[0] != 0)
            {
              containWildCard = true;
              ((PatternForNER)localObject3).setContainsWildcard();
            }
            if (arrayOfBoolean[1] != 0)
            {
              containStart = true;
              ((PatternForNER)localObject3).setContainsStart();
            }
            if (arrayOfBoolean[2] != 0)
            {
              containEnd = true;
              ((PatternForNER)localObject3).setContainsEnd();
            }
            if (arrayOfBoolean[3] != 0)
            {
              containStartRegularExpress = true;
              ((PatternForNER)localObject3).setContainsStartRegularExpress();
            }
            patterns.add(localObject3);
            addNoTerminal2patternIndex((String)localObject2, m);
            int[] arrayOfInt = new int[4];
            Iterator localIterator2 = localTreeMap.entrySet().iterator();
            if (!localIterator2.hasNext())
            {
              setPatKeyWords((PatternForNER)localObject3, localTreeMap, arrayOfInt);
              continue;
            }
            Map.Entry localEntry = (Map.Entry)localIterator2.next();
            int n = localArrayList.size();
            int j;
            if (((Integer)localEntry.getKey()).intValue() >= n)
            {
              j = 0;
              if (j <= ((Integer)localEntry.getKey()).intValue() - n) {}
            }
            else
            {
              ((List)localArrayList.get(((Integer)localEntry.getKey()).intValue())).add(Integer.valueOf(m));
              if (!parseKeywordsSize(((Integer)localEntry.getValue()).intValue(), arrayOfInt)) {
                continue;
              }
              if (arrayOfBoolean[0] != 0) {
                keywordsDic.setKeyWordsType(((Integer)localEntry.getKey()).intValue(), 16);
              }
              if (arrayOfBoolean[2] == 0) {
                continue;
              }
              keywordsDic.setKeyWordsType(((Integer)localEntry.getKey()).intValue(), 32);
              continue;
            }
            localArrayList.add(new ArrayList());
            j += 1;
            continue;
            keywordsDic.addKeyWordsPatIndex(i, (List)localArrayList.get(i));
            i += 1;
          }
          catch (Exception localException) {}
          paramString = paramString;
          return;
        }
        if (i < localArrayList.size()) {
          continue;
        }
        printMemory("buildPattern -2");
        if (engReplaceIndex >= 0)
        {
          containENG = true;
          if (keywordsDic.isStartKeyWords(engReplaceIndex)) {
            startContainENG = true;
          }
        }
        if (charReplaceIndex >= 0)
        {
          containCHAR = true;
          if (keywordsDic.isStartKeyWords(charReplaceIndex)) {
            startContainCHAR = true;
          }
        }
        if (numberReplaceIndex >= 0)
        {
          containNUM = true;
          if (keywordsDic.isStartKeyWords(numberReplaceIndex)) {
            startContainNUM = true;
          }
        }
        fillKeywords();
        printMemory("buildPattern -3");
        keywordsDic.buildDic();
        printMemory("buildPattern -4");
        if (containStart) {
          startTrimLength = PatternForNER.startPatternReplaceStr.length();
        }
        if (containEnd) {
          endTrimLength = PatternForNER.endPatternReplaceStr.length();
        }
        if (version == null)
        {
          printMemory("buildPattern -4.51");
          version = SMSUnderstand.getVersion();
        }
        printMemory("buildPattern -5");
        return;
        paramString = ((String)localIterator1.next()).trim();
        if ((!paramString.equals("")) && (!paramString.startsWith("//")))
        {
          if (!paramString.startsWith("<!")) {
            continue;
          }
          localObject2 = StringProcess.noRegexSplit(paramString, new String[] { "::=", "|" });
          if (localObject2.length >= 2)
          {
            localObject2[0] = StringProcess.trim(localObject2[0].trim(), new Character[] { Character.valueOf('<'), Character.valueOf('>') });
            if (localObject2[0].equals("!must"))
            {
              i = 1;
              if (i < localObject2.length)
              {
                localObject2[i] = localObject2[i].trim();
                if (!localObject2[i].equals("")) {
                  keywordsDic.addKeywords(localObject2[i], 8);
                }
                i += 1;
              }
            }
            else if (localObject2[0].equals("!segPunctuation"))
            {
              if (localObject2.length == 2) {
                segPunctuation = ("[" + localObject2[1].trim() + "]");
              } else {
                Log.i("Parser", "segPunctuation Error ");
              }
            }
            else if (localObject2[0].equals("!task"))
            {
              if (localObject2.length == 2) {
                task = localObject2[1].trim();
              } else {
                Log.i("Parser", "task Error ");
              }
            }
            else if (localObject2[0].equals("!version"))
            {
              if (localObject2.length == 2)
              {
                version = localObject2[1].trim();
                if (matchResourceVersion(SMSUnderstand.getVersion()) != null) {}
              }
              else
              {
                Log.i("Parser", "version Error ");
              }
            }
            else if (localObject2[0].equals("!platform"))
            {
              if (localObject2.length == 2) {
                platform = Integer.parseInt(localObject2[1].trim());
              } else {
                Log.i("Parser", "platform Error ");
              }
            }
            else if (localObject2[0].equals("!systemLevel"))
            {
              if (localObject2.length == 2) {
                systemLevel = Integer.parseInt(localObject2[1].trim());
              } else {
                Log.i("Parser", "systemLevels Error ");
              }
            }
            else if (localObject2[0].equals("!caseSensitive"))
            {
              if (localObject2.length == 2)
              {
                if (Integer.parseInt(localObject2[1].trim()) == 0)
                {
                  bool = false;
                  caseSensitive = bool;
                }
                else
                {
                  bool = true;
                }
              }
              else {
                Log.i("Parser", "caseSensitive Error ");
              }
            }
            else if (localObject2[0].equals("!wildCardMaxMatchLength"))
            {
              if (localObject2.length == 2) {
                wildCardMaxMatchLength = Integer.parseInt(localObject2[1].trim());
              } else {
                Log.i("Parser", "wildCardMaxMatchLength Error ");
              }
            }
            else if (localObject2[0].equals("!mustKP"))
            {
              if (localObject2.length == 2) {
                mustKP = Boolean.parseBoolean(localObject2[1].trim());
              } else {
                Log.i("Parser", "mustKP Error ");
              }
            }
            else if (localObject2[0].equals("!oneMatch"))
            {
              if (localObject2.length == 2) {
                oneMatch = Boolean.parseBoolean(localObject2[1].trim());
              } else {
                Log.i("Parser", "oneMatch Error ");
              }
            }
            else if (constant.containsKey(localObject2[0]))
            {
              Log.i("buildPattern", localObject2[0] + " has contained!");
            }
            else
            {
              localObject3 = new ArrayList(localObject2.length - 1);
              paramString = "";
              i = 1;
              if (i < localObject2.length) {
                continue;
              }
              Collections.sort((List)localObject3);
              constant.put(localObject2[0], localObject3);
            }
          }
        }
      }
      if ((i == 1) || (i == localObject2.length - 1))
      {
        localObject1 = StringProcess.noRegexSplit(localObject2[i].trim(), "@");
        if (localObject1.length == 2)
        {
          paramString = localObject1[1];
          replaceSeg = true;
        }
        if (!caseSensitive) {
          localObject1[0] = localObject1[0].toLowerCase();
        }
        if (!localObject1[0].equals(""))
        {
          if (!localObject1[0].equals("，")) {
            break label1157;
          }
          localObject1[0] = ",";
        }
      }
      for (;;)
      {
        keywordsDic.addKeywords(localObject1[0], 1);
        ((ArrayList)localObject3).add(new StringString(localObject1[0], paramString));
        i += 1;
        break;
        localObject1 = StringProcess.noRegexSplit(localObject2[i], "@");
        break label1036;
        if (localObject1[0].equals("：")) {
          localObject1[0] = ":";
        }
      }
      paramString = StringProcess.noRegexSplit(paramString, "::=");
    } while (paramString.length != 2);
    localObject2 = paramString[0].trim();
    localObject1 = paramString[1].trim();
    i = ((String)localObject1).indexOf('\t');
    if (i > 0)
    {
      paramString = ((String)localObject1).substring(i + 1);
      localObject1 = ((String)localObject1).substring(0, i);
      label1240:
      if (!((String)localObject2).startsWith("<??")) {
        break label1310;
      }
      finalProduction = false;
      label1256:
      localObject1 = StringProcess.noRegexSplit((String)localObject1, "|||");
      k = localObject1.length;
      i = 0;
      label1273:
      if (i < k)
      {
        localObject3 = localObject1[i];
        if (!((String)localObject3).equals("")) {
          break label1318;
        }
      }
    }
    for (;;)
    {
      i += 1;
      break label1273;
      break;
      paramString = "";
      break label1240;
      label1310:
      finalProduction = true;
      break label1256;
      label1318:
      printMemory("buildPattern - befor " + (String)localObject3);
    }
  }
  
  private boolean checkMustKP(List<ParseResult> paramList)
  {
    if (!mustKP) {
      return true;
    }
    paramList = paramList.iterator();
    do
    {
      if (!paramList.hasNext()) {
        return false;
      }
    } while (!nextgetPatternleftProduction.endsWith("-M>"));
    return true;
  }
  
  private boolean checkOneMatch(List<ParseResult> paramList)
  {
    return (oneMatch) && (paramList.size() > 0) && (checkMustKP(paramList));
  }
  
  private boolean containKeyWordsInPat(int paramInt1, int paramInt2, int paramInt3)
  {
    return containKeyWordsInPat((PatternForNER)patterns.get(paramInt1), paramInt2, paramInt3);
  }
  
  private boolean containKeyWordsInPat(PatternForNER paramPatternForNER, int paramInt1, int paramInt2)
  {
    return paramPatternForNER.findKeywords(paramInt1, paramInt2) >= 0;
  }
  
  private boolean extractKnowledge(List<PatternActionContent> paramList, List<String> paramList1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, HashSet<String> paramHashSet)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return false;
    }
    if ((paramInt1 > paramList1.size()) || (paramInt2 > paramList1.size())) {
      return false;
    }
    paramList = paramList.iterator();
    if (!paramList.hasNext()) {}
    PatternActionContent localPatternActionContent;
    int j;
    int i2;
    int i;
    for (;;)
    {
      return true;
      localPatternActionContent = (PatternActionContent)paramList.next();
      if (keywords != PatternKeywords.Extract) {
        break;
      }
      j = -1;
      i2 = 0;
      i = paramInt4;
      if (i2 < contents.size()) {
        break label157;
      }
      if (j < 0) {
        break;
      }
      knowledge.put(remark, new StringIntInt(null, i + currentStartRegularStartIndexInSource, j + currentStartRegularStartIndexInSource));
      paramHashSet.add(remark);
    }
    label157:
    Object localObject1 = (StringInt)contents.get(i2);
    int n;
    if (paramInt3 != ((StringInt)localObject1).getNum())
    {
      n = i;
      i = j;
    }
    for (;;)
    {
      i2 += 1;
      j = i;
      i = n;
      break;
      int k = paramInt1;
      int m = j;
      j = k;
      k = m;
      label224:
      Object localObject2;
      if (j >= paramInt2)
      {
        localObject1 = parserFunction(((StringInt)localObject1).getWord());
        n = i;
        i1 = k;
        if (localObject1 == null) {
          break label800;
        }
        localObject2 = localObject1[0];
        j = i;
        m = k;
        if (i2 == 0)
        {
          if (!((String)localObject2).equals("trimstart")) {
            break label543;
          }
          if (localObject1.length != 2) {
            break label528;
          }
          j = Integer.MAX_VALUE;
          label297:
          n = trimStart(targetSegment, i, k, localObject1[1], j);
          label317:
          j = n;
          m = k;
          if (contents.size() == 1)
          {
            if (!((String)localObject2).equals("trim")) {
              break label623;
            }
            if (localObject1.length != 3) {
              break label608;
            }
            i = Integer.MAX_VALUE;
            label362:
            j = trimStart(targetSegment, n, k, localObject1[1], i);
            m = trimEnd(targetSegment, j, k, localObject1[2], i);
          }
        }
        label402:
        n = j;
        i1 = m;
        if (i2 != contents.size() - 1) {
          break label800;
        }
        if (!((String)localObject2).equals("trimend")) {
          break label727;
        }
        if (localObject1.length != 2) {
          break label712;
        }
      }
      label528:
      label543:
      label608:
      label623:
      label712:
      for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[2]).intValue())
      {
        i = trimEnd(targetSegment, j, m, localObject1[1], i);
        n = j;
        break;
        m = k;
        if (i2 == 0)
        {
          m = k;
          if (j == paramInt1) {
            m = i;
          }
        }
        k = m + ((String)paramList1.get(j)).length();
        j += 1;
        break label224;
        j = Integer.valueOf(localObject1[2]).intValue();
        break label297;
        n = i;
        if (!((String)localObject2).equals("trimstartall")) {
          break label317;
        }
        if (localObject1.length == 2) {}
        for (j = Integer.MAX_VALUE;; j = Integer.valueOf(localObject1[2]).intValue())
        {
          n = trimStartAll(targetSegment, i, k, localObject1[1], j);
          break;
        }
        i = Integer.valueOf(localObject1[3]).intValue();
        break label362;
        j = n;
        m = k;
        if (!((String)localObject2).equals("trimall")) {
          break label402;
        }
        if (localObject1.length == 3) {}
        for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[3]).intValue())
        {
          j = trimStartAll(targetSegment, n, k, localObject1[1], i);
          m = trimEndAll(targetSegment, j, k, localObject1[2], i);
          break;
        }
      }
      label727:
      n = j;
      int i1 = m;
      if (((String)localObject2).equals("trimendall"))
      {
        if (localObject1.length == 2) {}
        for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[2]).intValue())
        {
          i = trimEndAll(targetSegment, j, m, localObject1[1], i);
          n = j;
          break;
        }
      }
      label800:
      i = i1;
    }
  }
  
  private boolean extractKnowledge(List<PatternActionContent> paramList, String[] paramArrayOfString, int paramInt1, int paramInt2, HashSet<String> paramHashSet)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return false;
    }
    paramList = paramList.iterator();
    PatternActionContent localPatternActionContent;
    int j;
    int n;
    int i;
    for (;;)
    {
      if (!paramList.hasNext()) {
        return true;
      }
      localPatternActionContent = (PatternActionContent)paramList.next();
      if (keywords == PatternKeywords.Extract)
      {
        j = -1;
        n = 0;
        i = paramInt2;
        if (n < contents.size()) {
          break;
        }
        if (j >= 0)
        {
          knowledge.put(remark, new StringIntInt(null, i + currentStartRegularStartIndexInSource, j + currentStartRegularStartIndexInSource));
          paramHashSet.add(remark);
        }
      }
    }
    Object localObject1 = (StringInt)contents.get(n);
    int i2 = ((StringInt)localObject1).getNum() - paramInt1;
    int k;
    if (i2 < 0)
    {
      Log.i("Parser", "extractKnowledge Error!");
      m = i;
      k = j;
    }
    do
    {
      n += 1;
      j = k;
      i = m;
      break;
      k = j;
      m = i;
    } while (i2 >= paramArrayOfString.length - 1);
    localObject1 = parserFunction(((StringInt)localObject1).getWord());
    int i1 = 0;
    int m = 0;
    label249:
    Object localObject2;
    if (n == 0)
    {
      j = 0;
      k = i;
      i = j;
      if (i >= i2)
      {
        if (localObject1 == null) {
          break label906;
        }
        localObject2 = localObject1[0];
        if (!((String)localObject2).equals("trimstart")) {
          break label468;
        }
        if (localObject1.length != 2) {
          break label453;
        }
        i = Integer.MAX_VALUE;
        label290:
        i = trimStart(paramArrayOfString[i2], 0, paramArrayOfString[i2].length(), localObject1[1], i);
        label314:
        j = k + i;
      }
    }
    for (;;)
    {
      if (n == contents.size() - 1)
      {
        i1 = paramArrayOfString[i2].length();
        if (localObject1 == null) {
          break label899;
        }
        m = 0;
        if (n == 0) {
          m = i;
        }
        localObject2 = localObject1[0];
        if (((String)localObject2).equals("trimend")) {
          if (localObject1.length == 2)
          {
            i = Integer.MAX_VALUE;
            label393:
            i = trimEnd(paramArrayOfString[i2], m, paramArrayOfString[i2].length(), localObject1[1], i);
          }
        }
      }
      for (;;)
      {
        k += i;
        m = j;
        break;
        k += paramArrayOfString[i].length();
        i += 1;
        break label249;
        label453:
        i = Integer.valueOf(localObject1[2]).intValue();
        break label290;
        label468:
        if (((String)localObject2).equals("trimstartall"))
        {
          if (localObject1.length == 2) {}
          for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[2]).intValue())
          {
            i = trimStartAll(paramArrayOfString[i2], 0, paramArrayOfString[i2].length(), localObject1[1], i);
            break;
          }
        }
        if (((String)localObject2).equals("trim"))
        {
          if (localObject1.length == 3) {}
          for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[3]).intValue())
          {
            i = trimStart(paramArrayOfString[i2], 0, paramArrayOfString[i2].length(), localObject1[1], i);
            break;
          }
        }
        i = m;
        if (!((String)localObject2).equals("trimall")) {
          break label314;
        }
        if (localObject1.length == 3) {}
        for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[3]).intValue())
        {
          i = trimStartAll(paramArrayOfString[i2], 0, paramArrayOfString[i2].length(), localObject1[1], i);
          break;
        }
        i = Integer.valueOf(localObject1[2]).intValue();
        break label393;
        if (((String)localObject2).equals("trimendall"))
        {
          if (localObject1.length == 2) {}
          for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[2]).intValue())
          {
            i = trimEndAll(paramArrayOfString[i2], m, paramArrayOfString[i2].length(), localObject1[1], i);
            break;
          }
        }
        if (((String)localObject2).equals("trim"))
        {
          if (localObject1.length == 3) {}
          for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[3]).intValue())
          {
            i = trimEnd(paramArrayOfString[i2], m, paramArrayOfString[i2].length(), localObject1[2], i);
            break;
          }
        }
        if (((String)localObject2).equals("trimall"))
        {
          if (localObject1.length == 3) {}
          for (i = Integer.MAX_VALUE;; i = Integer.valueOf(localObject1[3]).intValue())
          {
            i = trimEndAll(paramArrayOfString[i2], m, paramArrayOfString[i2].length(), localObject1[2], i);
            break;
          }
          k += paramArrayOfString[i2].length();
          m = j;
          break;
        }
        label899:
        i = i1;
      }
      label906:
      j = k;
      i = i1;
      continue;
      m = i;
      i = i1;
      k = j;
      j = m;
    }
  }
  
  private void fillKeywords()
  {
    int k = 0;
    int m;
    int i;
    int j;
    if (containCHAR)
    {
      m = keywordsDic.getKeywords(charReplaceIndex).getType();
      if (numberReplaceIndex >= 0)
      {
        i = keywordsDic.getKeywords(numberReplaceIndex).getType();
        j = 0;
        label49:
        if (j <= 9) {
          break label122;
        }
        j = keywordsDic.getKeywords(charReplaceIndex).getType();
        i = k;
        if (engReplaceIndex >= 0) {
          i = keywordsDic.getKeywords(engReplaceIndex).getType();
        }
        j = j | 0x1 | i;
        i = 0;
        label102:
        if (i <= 25) {
          break label147;
        }
        i = 0;
        label110:
        if (i <= 25) {
          break label171;
        }
      }
    }
    for (;;)
    {
      return;
      i = 0;
      break;
      label122:
      keywordsDic.addKeywords(String.valueOf(j), m | 0x1 | i);
      j += 1;
      break label49;
      label147:
      keywordsDic.addKeywords(String.valueOf((char)(i + 65)), j);
      i += 1;
      break label102;
      label171:
      keywordsDic.addKeywords(String.valueOf((char)(i + 97)), j);
      i += 1;
      break label110;
      if (containENG)
      {
        if (containNUM)
        {
          j = keywordsDic.getKeywords(numberReplaceIndex).getType();
          i = 0;
          label226:
          if (i > 9)
          {
            j = keywordsDic.getKeywords(engReplaceIndex).getType() | 0x1;
            i = 0;
          }
          for (;;)
          {
            if (i > 25)
            {
              i = 0;
              while (i <= 25)
              {
                keywordsDic.addKeywords(String.valueOf((char)(i + 97)), j);
                i += 1;
              }
              break;
              keywordsDic.addKeywords(String.valueOf(i), j | 0x1);
              i += 1;
              break label226;
            }
            keywordsDic.addKeywords(String.valueOf((char)(i + 65)), j);
            i += 1;
          }
        }
        j = keywordsDic.getKeywords(engReplaceIndex).getType() | 0x1;
        i = 0;
        for (;;)
        {
          if (i > 25)
          {
            i = 0;
            while (i <= 25)
            {
              keywordsDic.addKeywords(String.valueOf((char)(i + 97)), j);
              i += 1;
            }
            break;
          }
          keywordsDic.addKeywords(String.valueOf((char)(i + 65)), j);
          i += 1;
        }
      }
      if (containNUM)
      {
        j = keywordsDic.getKeywords(numberReplaceIndex).getType();
        i = 0;
        while (i <= 9)
        {
          keywordsDic.addKeywords(String.valueOf(i), j | 0x1);
          i += 1;
        }
      }
    }
  }
  
  private int[] findFirstKeywordsByNoTerminal(String paramString1, int paramInt, String paramString2)
  {
    HashSet localHashSet = new HashSet();
    if (!noTerminal2patternIndex.containsKey(paramString2)) {}
    int j;
    int k;
    int i;
    do
    {
      return null;
      Iterator localIterator = ((List)noTerminal2patternIndex.get(paramString2)).iterator();
      j = Integer.MAX_VALUE;
      k = -1;
      i = Integer.MAX_VALUE;
      Object localObject1 = null;
      for (;;)
      {
        if (!localIterator.hasNext())
        {
          if (localHashSet.size() > 0) {
            return keywordsDic.findFirst(paramString1, paramInt, localHashSet);
          }
        }
        else
        {
          int m = ((Integer)localIterator.next()).intValue();
          if (patterns.get(m)).isRegularExpression)
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = paramString1.substring(paramInt);
            }
            Matcher localMatcher = Pattern.compile(patterns.get(m)).pattern).matcher((CharSequence)localObject2);
            localObject1 = localObject2;
            if (!localMatcher.find()) {
              continue;
            }
            localObject1 = localObject2;
            if (localMatcher.start() >= j) {
              continue;
            }
            j = localMatcher.start();
            i = localMatcher.end();
            k = m;
            i -= 1;
            localObject1 = localObject2;
            continue;
          }
          if (localObject1 != null)
          {
            Log.println("Parser.findFirstKeywordsByNoTerminal Error:" + paramString2);
            return null;
          }
          Object localObject2 = getStartKeyWordsByPatIndex(m);
          int n = localObject2.length;
          m = 0;
          while (m < n)
          {
            localHashSet.add(Integer.valueOf(localObject2[m]));
            m += 1;
          }
        }
      }
    } while (k < 0);
    return new int[] { k, j + paramInt, i + paramInt };
  }
  
  private List<ParseResult> findKnowledgeSeg(String paramString, double paramDouble)
    throws Exception
  {
    targetSegment = paramString;
    ArrayList localArrayList1 = new ArrayList();
    if (!findMust(paramString)) {
      return localArrayList1;
    }
    acceptThr = paramDouble;
    Object localObject1 = new int[paramString.length() * 2];
    int i = 0;
    if (System.currentTimeMillis() - timeoutStart > timeOutThr)
    {
      Log.println(timeOutThr + " millseconds. " + parserName + " Parse TimeOut: " + paramString);
      return localArrayList1;
    }
    if (checkOneMatch(localArrayList1)) {
      return localArrayList1;
    }
    List localList = keywordsDic.findFirstAll(paramString, i, 2);
    int m;
    int j;
    label181:
    ArrayList localArrayList2;
    Object localObject2;
    ArrayList localArrayList3;
    ArrayList localArrayList4;
    boolean bool1;
    label393:
    label398:
    int n;
    label433:
    label438:
    label454:
    label466:
    boolean bool3;
    if ((localList == null) || (localList.size() == 0))
    {
      if (!containStartRegularExpress) {
        break label1167;
      }
      i = 0;
      localObject1 = getStartRegularExpressResult(paramString, i);
      if (localObject1 == null) {
        return nomalResults(localArrayList1);
      }
    }
    else
    {
      m = 0;
      j = 0;
      if (m >= localList.size())
      {
        k = j;
        j = i;
      }
      boolean bool4;
      for (;;)
      {
        i = j;
        if (k != 0) {
          break;
        }
        i = getCandNextStartIndex(paramString, ((int[])localList.get(0))[1]);
        break;
        System.currentTimeMillis();
        localArrayList2 = new ArrayList();
        localObject2 = keywordsDic.getKeywords(((int[])localList.get(m))[0]);
        localArrayList2.add((int[])localList.get(m));
        localArrayList3 = new ArrayList();
        localArrayList4 = new ArrayList();
        bool1 = findMust(((ParserKeywords)localObject2).getKeyWords());
        if ((bool1) && (((ParserKeywords)localObject2).isEndkeyWords()) && (((ParserKeywords)localObject2).getKeyWords().length() >= 1)) {
          localArrayList3.add(Integer.valueOf(0));
        }
        localObject1 = keywordsDic.findFirst(paramString, ((int[])localList.get(m))[2] + 1, 1);
        System.currentTimeMillis();
        bool4 = ((ParserKeywords)localObject2).isStartKeywordsContainsWildcard();
        if (localObject1 != null) {
          break label466;
        }
        System.currentTimeMillis();
        if (!bool1) {
          break label1173;
        }
        System.currentTimeMillis();
        k = localArrayList4.size() - 1;
        n = localArrayList3.size() - 1;
        if (n >= 0) {
          break label919;
        }
        System.currentTimeMillis();
        k = j;
        j = i;
        i = k;
        if (i == 0) {
          break label1126;
        }
        k = i;
      }
      bool3 = false;
      k = ((int[])localArrayList2.get(localArrayList2.size() - 1))[2];
      if (localObject1[1] > k + 1) {
        if ((bool4) && (localObject1[1] - k - 1 < wildCardMaxMatchLength) && (StringProcess.findSegPunctuation(paramString, k + 1, localObject1[1]) <= 0)) {
          localArrayList4.add(Integer.valueOf(localArrayList2.size()));
        }
      }
    }
    for (int k = 1;; k = 0)
    {
      localArrayList2.add(localObject1);
      boolean bool2 = bool1;
      if (!bool1)
      {
        bool2 = bool1;
        if (findMust(keywordsDic.getKeywords(localObject1[0]).getKeyWords())) {
          bool2 = true;
        }
      }
      bool1 = bool3;
      if (bool2)
      {
        bool1 = bool3;
        if (keywordsDic.getKeywords(localObject1[0]).isEndkeyWords())
        {
          localArrayList3.add(Integer.valueOf(localArrayList2.size() - 1));
          bool1 = true;
        }
      }
      bool3 = addOtherStartEndKeywords(paramString, (int[])localObject1, localArrayList2, localArrayList3, bool1, bool2);
      bool2 = bool3;
      if (k != 0)
      {
        localObject2 = keywordsDic.findFirst(paramString, localObject1[1] + 1, localObject1[2], 1);
        bool2 = bool3;
        if (localObject2 != null)
        {
          bool2 = bool3;
          if (localObject2[2] == localObject1[2])
          {
            localArrayList2.add(localObject2);
            bool1 = bool3;
            if (!bool3)
            {
              bool1 = bool3;
              if (findMust(keywordsDic.getKeywords(localObject2[0]).getKeyWords())) {
                bool1 = true;
              }
            }
            bool2 = bool1;
            if (bool1)
            {
              bool2 = bool1;
              if (keywordsDic.getKeywords(localObject2[0]).isEndkeyWords())
              {
                localArrayList3.add(Integer.valueOf(localArrayList2.size() - 1));
                bool2 = bool1;
              }
            }
          }
        }
      }
      localObject1 = keywordsDic.findFirst(paramString, localObject1[2] + 1, 1);
      bool1 = bool2;
      break label393;
      k = localObject1[1];
      break label398;
      if (localObject1[1] <= ((int[])localArrayList2.get(localArrayList2.size() - 1))[2])
      {
        Log.i("Parser", "findKnowledge Error:\t" + paramString);
        break label398;
        label919:
        int i1 = ((int[])localArrayList2.get(((Integer)localArrayList3.get(n)).intValue()))[2];
        if (StringProcess.ifCharTypeSame(paramString, i1, i1 + 1)) {}
        for (;;)
        {
          n -= 1;
          break label433;
          int i2 = ((Integer)localArrayList3.get(n)).intValue();
          containWildcard = false;
          for (;;)
          {
            if (k < 0) {}
            for (;;)
            {
              int i3 = ((int[])localArrayList2.get(0))[1];
              i1 = ((int[])localArrayList2.get(i2))[2] + 1;
              if (i1 - i3 >= 1) {
                break label1086;
              }
              j = 1;
              i = i1;
              break;
              if (i2 < ((Integer)localArrayList4.get(k)).intValue()) {
                break label1077;
              }
              containWildcard = true;
            }
            label1077:
            k -= 1;
          }
          label1086:
          localObject1 = ifRightKnowledge(paramString, localArrayList2, 0, i2);
          if (localObject1 != null)
          {
            localArrayList1.add(localObject1);
            i = ((ParseResult)localObject1).getEndPositionInSentence();
            j = 1;
            break label438;
            label1126:
            m += 1;
            k = j;
            j = i;
            i = k;
            break label181;
            localArrayList1.add(localObject1);
            i = ((ParseResult)localObject1).getEndPositionInSentence();
            break;
            label1167:
            return localArrayList1;
          }
        }
        label1173:
        k = i;
        i = j;
        j = k;
        break label454;
      }
    }
  }
  
  private boolean findMust(String paramString)
  {
    if (keywordsDic.getMustKeywordsCount() == 0) {}
    do
    {
      return true;
      paramString = keywordsDic.find(paramString, 8);
    } while ((paramString != null) && (paramString.size() != 0));
    return false;
  }
  
  private IntInt findMustKeyWordsInPat(int paramInt1, int paramInt2)
  {
    return findMustKeyWordsInPat((PatternForNER)patterns.get(paramInt1), paramInt2);
  }
  
  private IntInt findMustKeyWordsInPat(PatternForNER paramPatternForNER, int paramInt)
  {
    paramInt = paramPatternForNER.findKeywords(paramInt, 8);
    if (paramInt >= 0) {
      return paramPatternForNER.getMustKeywords()[paramInt];
    }
    return null;
  }
  
  private String[] firstRegularResults(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (!paramBoolean) {
      Log.println("Parser.firstRegularResults Error:" + paramString2);
    }
    do
    {
      return null;
      paramString2 = Pattern.compile(paramString2).matcher(paramString1);
    } while (!paramString2.find());
    String[] arrayOfString = new String[2];
    arrayOfString[0] = paramString2.group();
    currentStartRegularStartIndexInSource = paramString2.start();
    if (paramString2.end() < paramString1.length())
    {
      arrayOfString[1] = paramString1.substring(paramString2.end());
      return arrayOfString;
    }
    arrayOfString[1] = "";
    return arrayOfString;
  }
  
  private List<Integer> getCandPatternIndex(List<int[]> paramList, int paramInt1, int paramInt2)
  {
    Object localObject1 = new HashSet();
    Object localObject3 = keywordsDic.getKeywords(((int[])paramList.get(0))[0]);
    Object localObject2 = new ArrayList(2);
    localObject3 = StringProcess.getASCType(((ParserKeywords)localObject3).getKeyWords());
    int i;
    if (localObject3 == StringProcess.ASCType.Number)
    {
      if (startContainNUM) {
        ((List)localObject2).add(Integer.valueOf(numberReplaceIndex));
      }
      if (startContainCHAR) {
        ((List)localObject2).add(Integer.valueOf(charReplaceIndex));
      }
      if (((List)localObject2).size() == 0) {
        ((List)localObject2).add(Integer.valueOf(((int[])paramList.get(0))[0]));
      }
      i = 0;
      label138:
      if (i < ((List)localObject2).size()) {
        break label288;
      }
      localObject3 = ((List)localObject2).iterator();
      label159:
      if (((Iterator)localObject3).hasNext()) {
        break label321;
      }
      i = paramInt1 + 1;
      if (i <= paramInt2) {
        break label401;
      }
      paramList = new HashSet(((HashSet)localObject1).size());
      localObject1 = ((HashSet)localObject1).iterator();
    }
    for (;;)
    {
      label288:
      label321:
      int[] arrayOfInt;
      if (!((Iterator)localObject1).hasNext())
      {
        paramList = new ArrayList(paramList);
        Collections.sort(paramList);
        return paramList;
        if ((localObject3 != StringProcess.ASCType.EnglishLowerCase) && (localObject3 != StringProcess.ASCType.EnglishUpper)) {
          break;
        }
        if (startContainENG) {
          ((List)localObject2).add(Integer.valueOf(engReplaceIndex));
        }
        if (!startContainCHAR) {
          break;
        }
        ((List)localObject2).add(Integer.valueOf(charReplaceIndex));
        break;
        keyWords2Count.put((Integer)((List)localObject2).get(i), Integer.valueOf(1));
        i += 1;
        break label138;
        int j = ((Integer)((Iterator)localObject3).next()).intValue();
        arrayOfInt = keywordsDic.getPatIndexByKeywords(j);
        int k = arrayOfInt.length;
        i = 0;
        while (i < k)
        {
          int m = arrayOfInt[i];
          if (containKeyWordsInPat(m, j, 2)) {
            ((HashSet)localObject1).add(Integer.valueOf(m));
          }
          i += 1;
        }
        break label159;
        label401:
        localObject3 = StringProcess.getASCType(keywordsDic.getKeywords(((int[])paramList.get(i))[0]).getKeyWords());
        ((List)localObject2).clear();
        if (localObject3 == StringProcess.ASCType.Number)
        {
          if (containNUM) {
            ((List)localObject2).add(Integer.valueOf(numberReplaceIndex));
          }
          if (containCHAR) {
            ((List)localObject2).add(Integer.valueOf(charReplaceIndex));
          }
          label488:
          if (((List)localObject2).size() == 0) {
            ((List)localObject2).add(Integer.valueOf(((int[])paramList.get(i))[0]));
          }
          paramInt1 = 0;
          if (i < paramInt2)
          {
            if (((int[])paramList.get(i))[1] != ((int[])paramList.get(i + 1))[1]) {
              break label654;
            }
            paramInt1 = 1;
          }
          label563:
          localObject3 = ((List)localObject2).iterator();
        }
        label654:
        label784:
        for (;;)
        {
          if (!((Iterator)localObject3).hasNext())
          {
            i += 1;
            break;
            if ((localObject3 != StringProcess.ASCType.EnglishLowerCase) && (localObject3 != StringProcess.ASCType.EnglishUpper)) {
              break label488;
            }
            if (containENG) {
              ((List)localObject2).add(Integer.valueOf(engReplaceIndex));
            }
            if (!containCHAR) {
              break label488;
            }
            ((List)localObject2).add(Integer.valueOf(charReplaceIndex));
            break label488;
            paramInt1 = 0;
            break label563;
          }
          j = ((Integer)((Iterator)localObject3).next()).intValue();
          if (!keyWords2Count.containsKey(Integer.valueOf(j))) {
            keyWords2Count.put(Integer.valueOf(j), Integer.valueOf(1));
          }
          for (;;)
          {
            if ((containWildCard) || (((List)localObject2).size() != 1) || (paramInt1 != 0)) {
              break label784;
            }
            retainAll((Collection)localObject1, keywordsDic.getPatIndexByKeywords(j));
            break;
            keyWords2Count.put(Integer.valueOf(j), Integer.valueOf(((Integer)keyWords2Count.get(Integer.valueOf(j))).intValue() + 1));
          }
        }
      }
      localObject2 = (Integer)((Iterator)localObject1).next();
      paramInt2 = 0;
      localObject3 = getMustKeyWordsByPatIndex(((Integer)localObject2).intValue());
      i = localObject3.length;
      paramInt1 = 0;
      for (;;)
      {
        if (paramInt1 >= i) {
          paramInt1 = paramInt2;
        }
        for (;;)
        {
          if (paramInt1 == 0) {
            break label916;
          }
          forbidMatchIndex.set(((Integer)localObject2).intValue(), true);
          break;
          arrayOfInt = localObject3[paramInt1];
          if (!keyWords2Count.containsKey(Integer.valueOf(arrayOfInt.getFirst())))
          {
            paramInt1 = 1;
          }
          else
          {
            if (((Integer)keyWords2Count.get(Integer.valueOf(arrayOfInt.getFirst()))).intValue() >= arrayOfInt.getSecond()) {
              break label909;
            }
            paramInt1 = 1;
          }
        }
        label909:
        paramInt1 += 1;
      }
      label916:
      paramList.add(localObject2);
    }
  }
  
  private List<Integer> getCandPatternIndexByNonTerminal(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    int i;
    if (paramString1.endsWith("-R>"))
    {
      paramString1 = ((List)noTerminal2patternIndex.get(paramString1)).iterator();
      for (;;)
      {
        if (!paramString1.hasNext()) {
          return localArrayList;
        }
        i = ((Integer)paramString1.next()).intValue();
        if (!patterns.get(i)).isRegularExpression) {
          break;
        }
        localArrayList.add(Integer.valueOf(i));
      }
      return new ArrayList();
    }
    if (currentStartRegular)
    {
      paramString1 = ((List)noTerminal2patternIndex.get(paramString1)).iterator();
      for (;;)
      {
        if (!paramString1.hasNext()) {
          return localArrayList;
        }
        localArrayList.add(Integer.valueOf(((Integer)paramString1.next()).intValue()));
      }
    }
    paramString2 = getStartKeywords(paramString2);
    if (paramString2 == null) {
      return localArrayList;
    }
    paramString1 = ((List)noTerminal2patternIndex.get(paramString1)).iterator();
    int k;
    do
    {
      if (!paramString1.hasNext()) {
        return localArrayList;
      }
      k = ((Integer)paramString1.next()).intValue();
    } while (forbidMatchIndex.get(k));
    Object localObject1 = getStartKeyWordsByPatIndex(k);
    if (localObject1 != null)
    {
      j = localObject1.length;
      i = 0;
      label259:
      if (i >= j)
      {
        i = 0;
        label267:
        if (i != 0) {
          break label436;
        }
      }
    }
    label408:
    label415:
    label436:
    for (int j = 1;; j = 0)
    {
      int m;
      if (j == 0)
      {
        localObject1 = getMustKeyWordsByPatIndex(k);
        m = localObject1.length;
        i = 0;
      }
      for (;;)
      {
        if (i >= m) {
          i = 0;
        }
        for (;;)
        {
          if (i == 0) {
            break label415;
          }
          forbidMatchIndex.set(k, true);
          break;
          if (paramString2.contains(Integer.valueOf(localObject1[i])))
          {
            i = 1;
            break label267;
          }
          i += 1;
          break label259;
          Object localObject2 = localObject1[i];
          if (!keyWords2Count.containsKey(Integer.valueOf(((IntInt)localObject2).getFirst())))
          {
            i = 1;
          }
          else
          {
            if (((Integer)keyWords2Count.get(Integer.valueOf(((IntInt)localObject2).getFirst()))).intValue() >= ((IntInt)localObject2).getSecond()) {
              break label408;
            }
            i = 1;
          }
        }
        i += 1;
      }
      if (j != 0) {
        break;
      }
      localArrayList.add(Integer.valueOf(k));
      break;
    }
  }
  
  private List<String> getConst(String paramString)
  {
    paramString = paramString.split("[<>\\|]");
    ArrayList localArrayList = new ArrayList();
    int j = paramString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return localArrayList;
      }
      Object localObject = paramString[i];
      if ((((String)localObject).startsWith("!")) && (!((String)localObject).equals("!空"))) {
        localArrayList.add(localObject);
      }
      i += 1;
    }
  }
  
  private boolean[] getKeyWords(PatternForNER paramPatternForNER, Map<Integer, Integer> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    paramPatternForNER = getKeyWords(paramPatternForNER, paramMap, localArrayList);
    paramPatternForNER[0] |= getMustKeyWords(localArrayList, paramMap);
    return paramPatternForNER;
  }
  
  private boolean[] getKeyWords(PatternForNER paramPatternForNER, Map<Integer, Integer> paramMap, List<String> paramList)
  {
    if (isRegularExpression)
    {
      paramPatternForNER = new boolean[4];
      paramPatternForNER[3] = 1;
      return paramPatternForNER;
    }
    paramPatternForNER = StringProcess.noRegexSplit(pattern, segPatsSignal);
    int i1 = 0;
    int n = 0;
    int i = 0;
    if (i >= paramPatternForNER.length) {
      return new boolean[] { n, getTwoEndsKeyWords(paramPatternForNER, paramMap, 2), getTwoEndsKeyWords(paramPatternForNER, paramMap, 4), i1 };
    }
    Object localObject1 = paramPatternForNER[i];
    int i2;
    if (((String)localObject1).startsWith("<?"))
    {
      paramList.add(localObject1);
      if (i != 0) {
        break label582;
      }
      localObject2 = (List)noTerminal2patternIndex.get(localObject1);
      if (localObject2 == null)
      {
        Log.i("Parser", "!noTerminal2patternIndex.containsKey(" + (String)localObject1 + ")");
        i2 = i1;
        i1 = n;
        n = i2;
      }
    }
    int j;
    for (;;)
    {
      i += 1;
      i2 = i1;
      i1 = n;
      n = i2;
      break;
      localObject1 = ((List)localObject2).iterator();
      do
      {
        if (!((Iterator)localObject1).hasNext())
        {
          i2 = n;
          n = i1;
          i1 = i2;
          break;
        }
        j = ((Integer)((Iterator)localObject1).next()).intValue();
      } while (!patterns.get(j)).isRegularExpression);
      i2 = 1;
      i1 = n;
      n = i2;
      continue;
      if (((String)localObject1).equals("<*>"))
      {
        n = i1;
        i1 = 1;
      }
      else if ((((String)localObject1).equals("<#m>")) || (((String)localObject1).equals("<#yyyy>")) || (((String)localObject1).equals("<#MM>")) || (((String)localObject1).equals("<#dd>")) || (((String)localObject1).equals("<#HH>")) || (((String)localObject1).equals("<#mm>")) || (((String)localObject1).equals("<#ss>")))
      {
        if (numberReplaceIndex < 0) {
          numberReplaceIndex = keywordsDic.addKeywords("NUM", 64);
        }
        addTempMustKeywords(paramMap, numberReplaceIndex);
        i2 = n;
        n = i1;
        i1 = i2;
      }
      else if (((String)localObject1).equals("<#char>"))
      {
        if (charReplaceIndex < 0) {
          charReplaceIndex = keywordsDic.addKeywords("CHAR", 64);
        }
        addTempMustKeywords(paramMap, charReplaceIndex);
        i2 = n;
        n = i1;
        i1 = i2;
      }
      else if (((String)localObject1).equals("<#eng>"))
      {
        if (engReplaceIndex < 0) {
          engReplaceIndex = keywordsDic.addKeywords("ENG", 64);
        }
        addTempMustKeywords(paramMap, engReplaceIndex);
        i2 = n;
        n = i1;
        i1 = i2;
      }
      else
      {
        localObject1 = StringProcess.noRegexSplit(StringProcess.trim((String)localObject1, new Character[] { Character.valueOf('<'), Character.valueOf('>') }), "|");
        int k = localObject1.length;
        j = 0;
        if (j < k) {
          break label597;
        }
        label582:
        i2 = n;
        n = i1;
        i1 = i2;
      }
    }
    label597:
    Object localObject2 = StringProcess.trim(localObject1[j], new Character[] { Character.valueOf('*') });
    if (((String)localObject2).equals("!空")) {}
    for (;;)
    {
      j += 1;
      break;
      int m;
      if ((!((String)localObject2).startsWith("!")) || (((String)localObject2).equals("!")))
      {
        m = keywordsDic.addKeywords((String)localObject2, 1);
        if (localObject1.length == 1) {
          addTempMustKeywords(paramMap, m);
        } else {
          setTempKeywords(paramMap, m, 1);
        }
      }
      else
      {
        localObject2 = (List)constant.get(localObject2);
        Iterator localIterator = ((List)localObject2).iterator();
        while (localIterator.hasNext())
        {
          StringString localStringString = (StringString)localIterator.next();
          m = keywordsDic.getKeyWordsIndex(localStringString.getKeyWord());
          if ((localObject1.length == 1) && (((List)localObject2).size() == 1)) {
            addTempMustKeywords(paramMap, m);
          } else {
            setTempKeywords(paramMap, m, 1);
          }
        }
      }
    }
  }
  
  private int[] getKeyWordsByPatIndex(int paramInt)
  {
    return ((PatternForNER)patterns.get(paramInt)).getKeywords();
  }
  
  private void getKeyWordsOfNonTerminal(Map<Integer, Integer> paramMap, String paramString, int paramInt)
  {
    if (!noTerminal2patternIndex.containsKey(paramString)) {
      return;
    }
    paramString = ((List)noTerminal2patternIndex.get(paramString)).iterator();
    while (paramString.hasNext())
    {
      int[] arrayOfInt = getKeyWordsByPatIndex(((Integer)paramString.next()).intValue());
      int j = arrayOfInt.length;
      int i = 0;
      while (i < j)
      {
        int k = arrayOfInt[i];
        setTempKeywords(paramMap, k, paramInt);
        if (finalProduction) {
          keywordsDic.setKeyWordsType(k, paramInt);
        }
        i += 1;
      }
    }
  }
  
  private boolean getMustKeyWords(List<String> paramList, Map<Integer, Integer> paramMap)
  {
    paramList = paramList.iterator();
    boolean bool2 = false;
    Object localObject1;
    for (;;)
    {
      if (!paramList.hasNext()) {
        return bool2;
      }
      localObject2 = (String)paramList.next();
      localObject1 = new HashMap();
      localObject3 = (List)noTerminal2patternIndex.get(localObject2);
      if (localObject3 != null) {
        break;
      }
      Log.i("Parser", "!noTerminal2patternIndex.containsKey(" + (String)localObject2 + ")");
    }
    Object localObject2 = ((List)localObject3).iterator();
    int i = 1;
    boolean bool1 = bool2;
    if (!((Iterator)localObject2).hasNext())
    {
      localObject1 = ((HashMap)localObject1).entrySet().iterator();
      for (;;)
      {
        bool2 = bool1;
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        if (((Integer)((Map.Entry)localObject2).getValue()).intValue() > 0) {
          addTempMustKeywords(paramMap, ((Integer)((Map.Entry)localObject2).getKey()).intValue(), ((Integer)((Map.Entry)localObject2).getValue()).intValue());
        }
      }
    }
    int k = ((Integer)((Iterator)localObject2).next()).intValue();
    label246:
    int j;
    if (((PatternForNER)patterns.get(k)).containsWildcard())
    {
      bool2 = true;
      localObject3 = getKeyWordsByPatIndex(k);
      int m = localObject3.length;
      j = 0;
      label262:
      if (j < m) {
        break label330;
      }
      if (i == 0) {
        break label384;
      }
      localObject3 = getMustKeyWordsByPatIndex(k);
      j = localObject3.length;
      i = 0;
    }
    label330:
    Map.Entry localEntry;
    for (;;)
    {
      if (i >= j)
      {
        i = 0;
        bool1 = bool2;
        break;
        bool2 = bool1;
        if (!patterns.get(k)).isRegularExpression) {
          break label246;
        }
        break;
        setTempKeywords(paramMap, localObject3[j], 1);
        j += 1;
        break label262;
      }
      localEntry = localObject3[i];
      ((HashMap)localObject1).put(Integer.valueOf(localEntry.getFirst()), Integer.valueOf(localEntry.getSecond()));
      i += 1;
    }
    label384:
    Object localObject3 = ((HashMap)localObject1).entrySet().iterator();
    for (;;)
    {
      bool1 = bool2;
      if (!((Iterator)localObject3).hasNext()) {
        break;
      }
      localEntry = (Map.Entry)((Iterator)localObject3).next();
      IntInt localIntInt = findMustKeyWordsInPat(k, ((Integer)localEntry.getKey()).intValue());
      if (localIntInt != null)
      {
        j = localIntInt.getSecond();
        ((HashMap)localObject1).put((Integer)localEntry.getKey(), Integer.valueOf(Math.min(j, ((Integer)localEntry.getValue()).intValue())));
      }
      else
      {
        ((HashMap)localObject1).put((Integer)localEntry.getKey(), Integer.valueOf(64536));
      }
    }
  }
  
  private IntInt[] getMustKeyWordsByPatIndex(int paramInt)
  {
    return ((PatternForNER)patterns.get(paramInt)).getMustKeywords();
  }
  
  private void getSegReplace(List<ParseResult> paramList)
  {
    Iterator localIterator = paramList.iterator();
    if (!localIterator.hasNext()) {
      return;
    }
    ParseResult localParseResult = (ParseResult)localIterator.next();
    ArrayList localArrayList1 = localParseResult.getSegments_pattern();
    ArrayList localArrayList2 = localParseResult.getSegments();
    ArrayList localArrayList3 = new ArrayList(localArrayList1.size());
    int i = 0;
    if (i >= localArrayList1.size()) {}
    String str;
    do
    {
      localParseResult.setSegments_replace(localArrayList3);
      break;
      str = (String)localArrayList1.get(i);
    } while (str == null);
    List localList = getConst(str);
    if ((localList.size() == 0) || (((String)localArrayList2.get(i)).equals(""))) {
      paramList = (String)localArrayList2.get(i);
    }
    label176:
    label326:
    for (;;)
    {
      localArrayList3.add(paramList);
      i += 1;
      break;
      paramList = localList.iterator();
      if (!paramList.hasNext()) {}
      Object localObject;
      int j;
      for (paramList = null;; paramList = ((StringString)((ArrayList)localObject).get(j)).getSecond())
      {
        if (paramList != null) {
          break label326;
        }
        Log.i("Parser", "getSegReplace Error:" + str + "\t" + (String)localArrayList2.get(i) + "\t" + localList);
        paramList = (String)localArrayList2.get(i);
        break;
        localObject = (String)paramList.next();
        localObject = (ArrayList)constant.get(localObject);
        j = containInDic((ArrayList)localObject, (String)localArrayList2.get(i));
        if (j < 0) {
          break label176;
        }
      }
    }
  }
  
  private int[] getStartKeyWordsByPatIndex(int paramInt)
  {
    return ((PatternForNER)patterns.get(paramInt)).getStartKeywords();
  }
  
  private HashSet<Integer> getStartKeywords(String paramString)
  {
    if (paramString.equals("")) {}
    for (paramString = keywordsDic.startWith(PatternForNER.endPatternReplaceStr, 0, 1); (paramString == null) || (paramString.size() == 0); paramString = keywordsDic.startWith(paramString, 0, 1)) {
      return null;
    }
    HashSet localHashSet = new HashSet();
    paramString = paramString.iterator();
    for (;;)
    {
      if (!paramString.hasNext()) {
        return localHashSet;
      }
      int[] arrayOfInt = (int[])paramString.next();
      String str = keywordsDic.getKeywords(arrayOfInt[0]).getKeyWords();
      if (str.length() == 1)
      {
        int i = str.charAt(0);
        if ((i >= 48) && (i <= 57))
        {
          if (containNUM) {
            localHashSet.add(Integer.valueOf(numberReplaceIndex));
          }
          if (containCHAR) {
            localHashSet.add(Integer.valueOf(charReplaceIndex));
          }
          if ((!containCHAR) && (!containNUM)) {
            localHashSet.add(Integer.valueOf(arrayOfInt[0]));
          }
        }
        else if (((i >= 97) && (i <= 122)) || ((i >= 65) && (i <= 90)))
        {
          if (containENG) {
            localHashSet.add(Integer.valueOf(engReplaceIndex));
          }
          if (containCHAR) {
            localHashSet.add(Integer.valueOf(charReplaceIndex));
          }
          if ((!containCHAR) && (!containENG)) {
            localHashSet.add(Integer.valueOf(arrayOfInt[0]));
          }
        }
        else
        {
          localHashSet.add(Integer.valueOf(arrayOfInt[0]));
        }
      }
      else
      {
        localHashSet.add(Integer.valueOf(arrayOfInt[0]));
      }
    }
  }
  
  private ParseResult getStartRegularExpressResult(String paramString, int paramInt)
  {
    Object localObject1;
    int i;
    if (startRegularExpressPatIndex == null)
    {
      localObject1 = new HashSet();
      i = 0;
    }
    int j;
    Object localObject2;
    for (;;)
    {
      if (i >= patterns.size())
      {
        startRegularExpressPatIndex = new ArrayList((Collection)localObject1);
        Collections.sort(startRegularExpressPatIndex);
        i = paramInt;
        if (paramInt < startTrimLength) {
          i = startTrimLength;
        }
        j = paramString.length() - endTrimLength;
        paramInt = j;
        if (j > paramString.length() - endTrimLength) {
          paramInt = paramString.length() - endTrimLength;
        }
        paramString = paramString.substring(i, paramInt);
        if (findMust(paramString)) {
          break;
        }
        return null;
      }
      localObject2 = (PatternForNER)patterns.get(i);
      if ((((PatternForNER)localObject2).containsStartRegularExpress()) && (!leftProduction.startsWith("<??"))) {
        ((Set)localObject1).add(Integer.valueOf(i));
      }
      i += 1;
    }
    if (paramString.length() == StringProcess.getNumberCount(paramString)) {
      return null;
    }
    keyWords2Count.clear();
    forbidMatchIndex.clear();
    knowledge.clear();
    i -= startTrimLength;
    currentStartRegular = true;
    paramString = parse(paramString, i, startRegularExpressPatIndex);
    System.currentTimeMillis();
    if ((paramString != null) && (paramString.getType() != ParseType.NoPass) && (getPatternscore >= acceptThr))
    {
      j = startTrimLength;
      paramString.setStartPositionInSentence(currentStartRegularStartIndexInSource);
      if (knowledge == null) {
        knowledge = new HashMap();
      }
      if ((knowledge == null) || (knowledge.size() == 0))
      {
        knowledge.put(StringProcess.trim(getPatternleftProduction, new Character[] { Character.valueOf('<'), Character.valueOf('>'), Character.valueOf('?') }), new StringIntInt(null, i, paramInt - j));
        localObject1 = getPatternresultContent.iterator();
      }
      for (;;)
      {
        if (!((Iterator)localObject1).hasNext())
        {
          paramString.setConfidence(Double.valueOf(getPatternscore));
          paramString.setParserName(parserName);
          return paramString;
          knowledge.putAll(knowledge);
          break;
        }
        localObject2 = (PatternActionContent)((Iterator)localObject1).next();
        if ((contents != null) && (contents.size() > 0) && (((StringInt)contents.get(0)).getNum() == -1)) {
          knowledge.put(remark, new StringIntInt(((StringInt)contents.get(0)).getName(), 0, 0));
        }
      }
    }
    return null;
  }
  
  private boolean getTwoEndsKeyWords(String[] paramArrayOfString, Map<Integer, Integer> paramMap, int paramInt)
  {
    int i = 0;
    int n;
    boolean bool1;
    int j;
    int m;
    if (paramInt == 2)
    {
      n = 1;
      if (n == 0) {
        i = paramArrayOfString.length - 1;
      }
      bool1 = false;
      j = 0;
      m = i;
      i = j;
    }
    Object localObject1;
    Object localObject2;
    for (;;)
    {
      if ((m < 0) || (m >= paramArrayOfString.length) || (i != 0))
      {
        return bool1;
        n = 0;
        break;
      }
      localObject1 = paramArrayOfString[m];
      if (!((String)localObject1).startsWith("<?")) {
        break label426;
      }
      if (i != 0) {
        break label1178;
      }
      if (n != 0) {
        break label319;
      }
      localObject2 = (List)noTerminal2patternIndex.get(localObject1);
      if (localObject2 == null)
      {
        Log.i("Parser", "!noTerminal2patternIndex.containsKey(" + (String)localObject1 + ")");
      }
      else
      {
        if ((((List)localObject2).size() != 1) || (!patterns.get(((Integer)((List)localObject2).get(0)).intValue())).isRegularExpression)) {
          break label192;
        }
        i = 1;
      }
    }
    label192:
    getKeyWordsOfNonTerminal(paramMap, (String)localObject1, paramInt);
    label200:
    i = 1;
    label246:
    label260:
    label295:
    label319:
    label330:
    label426:
    label1164:
    label1178:
    for (;;)
    {
      boolean bool2 = bool1;
      if (n == 0)
      {
        bool2 = bool1;
        if (m == paramArrayOfString.length - 1)
        {
          localObject2 = ((List)noTerminal2patternIndex.get(localObject1)).iterator();
          if (((Iterator)localObject2).hasNext()) {
            break label330;
          }
          bool2 = bool1;
        }
      }
      if ((n != 0) && (m == 0))
      {
        localObject1 = ((List)noTerminal2patternIndex.get(localObject1)).iterator();
        bool1 = bool2;
        if (((Iterator)localObject1).hasNext()) {}
      }
      for (;;)
      {
        if (n != 0)
        {
          m += 1;
          break;
          getKeyWordsOfNonTerminal(paramMap, (String)localObject1, paramInt);
          break label200;
          j = ((Integer)((Iterator)localObject2).next()).intValue();
          bool2 = ((PatternForNER)patterns.get(j)).containsEnd();
          bool1 = bool2;
          if (!bool2) {
            break label246;
          }
          break label260;
          j = ((Integer)((Iterator)localObject1).next()).intValue();
          bool2 = ((PatternForNER)patterns.get(j)).containsStart();
          bool1 = bool2;
          if (!bool2) {
            break label295;
          }
          bool1 = bool2;
          continue;
          bool2 = bool1;
          if (((String)localObject1).equals("<*>")) {
            break label1164;
          }
          if ((((String)localObject1).equals("<#m>")) || (((String)localObject1).equals("<#yyyy>")) || (((String)localObject1).equals("<#MM>")) || (((String)localObject1).equals("<#dd>")) || (((String)localObject1).equals("<#HH>")) || (((String)localObject1).equals("<#mm>")) || (((String)localObject1).equals("<#ss>")))
          {
            bool2 = bool1;
            if (i != 0) {
              break label1164;
            }
            setTempKeywords(paramMap, numberReplaceIndex, paramInt);
            if (finalProduction) {
              keywordsDic.setKeyWordsType(numberReplaceIndex, paramInt);
            }
            i = 1;
            continue;
          }
          if (((String)localObject1).equals("<#char>"))
          {
            bool2 = bool1;
            if (i != 0) {
              break label1164;
            }
            setTempKeywords(paramMap, charReplaceIndex, paramInt);
            if (finalProduction) {
              keywordsDic.setKeyWordsType(charReplaceIndex, paramInt);
            }
            i = 1;
            continue;
          }
          if (((String)localObject1).equals("<#eng>"))
          {
            bool2 = bool1;
            if (i != 0) {
              break label1164;
            }
            setTempKeywords(paramMap, engReplaceIndex, paramInt);
            if (finalProduction) {
              keywordsDic.setKeyWordsType(engReplaceIndex, paramInt);
            }
            i = 1;
            continue;
          }
          localObject2 = StringProcess.noRegexSplit(StringProcess.trim((String)localObject1, new Character[] { Character.valueOf('<'), Character.valueOf('>') }), "|");
          int i2 = localObject2.length;
          int k = 0;
          j = 0;
          int i1 = 0;
          if (i1 >= i2)
          {
            bool2 = bool1;
            if (k == 0) {
              break label1164;
            }
            bool2 = bool1;
            if (j != 0) {
              break label1164;
            }
            i = 1;
            continue;
          }
          Object localObject3 = StringProcess.trim(localObject2[i1], new Character[] { Character.valueOf('*') });
          if (((String)localObject3).equals("!空")) {
            j = 1;
          }
          for (;;)
          {
            i1 += 1;
            break;
            if (((String)localObject3).equals(PatternForNER.endPatternReplaceStr))
            {
              if ((n == 0) && (m == paramArrayOfString.length - 1)) {
                bool1 = true;
              }
              for (;;)
              {
                k = keywordsDic.getKeyWordsIndex((String)localObject3);
                setTempKeywords(paramMap, k, paramInt);
                if (finalProduction) {
                  keywordsDic.setKeyWordsType(k, paramInt);
                }
                k = 1;
                break;
                if (n == 0) {
                  Log.i("Parser", "End define Error:" + (String)localObject1);
                }
              }
            }
            if (((String)localObject3).equals(PatternForNER.startPatternReplaceStr))
            {
              if ((n != 0) && (m == 0)) {
                bool2 = true;
              }
              for (;;)
              {
                k = keywordsDic.getKeyWordsIndex((String)localObject3);
                setTempKeywords(paramMap, k, paramInt);
                if (finalProduction) {
                  keywordsDic.setKeyWordsType(k, paramInt);
                }
                k = 1;
                bool1 = bool2;
                break;
                bool2 = bool1;
                if (n != 0)
                {
                  Log.i("Parser", "Start define Error:" + (String)localObject1);
                  bool2 = bool1;
                }
              }
            }
            if ((!((String)localObject3).equals("!")) && (((String)localObject3).startsWith("!")))
            {
              localObject3 = ((List)constant.get(localObject3)).iterator();
              for (;;)
              {
                if (!((Iterator)localObject3).hasNext())
                {
                  k = 1;
                  break;
                }
                StringString localStringString = (StringString)((Iterator)localObject3).next();
                k = keywordsDic.getKeyWordsIndex(localStringString.getKeyWord());
                setTempKeywords(paramMap, k, paramInt);
                if (finalProduction) {
                  keywordsDic.setKeyWordsType(k, paramInt);
                }
              }
            }
            k = keywordsDic.getKeyWordsIndex((String)localObject3);
            setTempKeywords(paramMap, k, paramInt);
            if (finalProduction) {
              keywordsDic.setKeyWordsType(k, paramInt);
            }
            k = 1;
          }
        }
        m -= 1;
        break;
        bool1 = bool2;
        continue;
        bool1 = bool2;
      }
    }
  }
  
  private ParseResult ifRightKnowledge(String paramString, List<int[]> paramList, int paramInt1, int paramInt2)
  {
    int i = ((int[])paramList.get(0))[1];
    if (i < startTrimLength) {
      i = startTrimLength;
    }
    for (;;)
    {
      int k = ((int[])paramList.get(paramInt2))[2] + 1;
      int j = k;
      if (k > paramString.length() - endTrimLength) {
        j = paramString.length() - endTrimLength;
      }
      paramString = paramString.substring(i, j);
      if (!findMust(paramString)) {
        return null;
      }
      if (paramString.length() == StringProcess.getNumberCount(paramString)) {
        return null;
      }
      if (paramString.contains("GPRS优惠 省内GP5M免费，剩余 5.00 MB")) {
        Log.println("ok");
      }
      keyWords2Count.clear();
      forbidMatchIndex.clear();
      knowledge.clear();
      currentStartRegular = false;
      paramList = getCandPatternIndex(paramList, paramInt1, paramInt2);
      System.currentTimeMillis();
      paramInt1 = i - startTrimLength;
      paramString = parse(paramString, paramInt1, paramList);
      System.currentTimeMillis();
      if ((paramString != null) && (paramString.getType() != ParseType.NoPass) && (getPatternscore >= acceptThr))
      {
        paramInt2 = j - startTrimLength;
        paramString.setStartPositionInSentence(paramInt1);
        paramString.setEndPositionInSentence(paramInt2);
        if (knowledge == null) {
          knowledge = new HashMap();
        }
        if ((knowledge == null) || (knowledge.size() == 0))
        {
          knowledge.put(StringProcess.trim(getPatternleftProduction, new Character[] { Character.valueOf('<'), Character.valueOf('>'), Character.valueOf('?') }), new StringIntInt(null, paramInt1, paramInt2));
          paramList = getPatternresultContent.iterator();
        }
        for (;;)
        {
          if (!paramList.hasNext())
          {
            paramString.setConfidence(Double.valueOf(getPatternscore));
            paramString.setParserName(parserName);
            return paramString;
            knowledge.putAll(knowledge);
            break;
          }
          PatternActionContent localPatternActionContent = (PatternActionContent)paramList.next();
          if ((contents != null) && (contents.size() > 0) && (((StringInt)contents.get(0)).getNum() == -1)) {
            knowledge.put(remark, new StringIntInt(((StringInt)contents.get(0)).getName(), 0, 0));
          }
        }
      }
      return null;
    }
  }
  
  private void initialPatternsNumber(List<String> paramList)
  {
    int i = 0;
    if (i >= paramList.size())
    {
      patterns = new ArrayList(patternsCount);
      forbidMatchIndex = new BitSet(patternsCount);
      keyWords2Count = new HashMap();
      knowledge = new HashMap();
      keywordsDic = new ParserKeywordsDic();
      return;
    }
    paramList.set(i, ((String)paramList.get(i)).trim());
    if (((String)paramList.get(i)).startsWith("<?")) {
      patternsCount += 1;
    }
    for (int j = ((String)paramList.get(i)).indexOf("|||");; j = ((String)paramList.get(i)).indexOf("|||", j + 3))
    {
      if (j <= 0)
      {
        i += 1;
        break;
      }
      patternsCount += 1;
    }
  }
  
  private List<ParseResult> nomalResults(List<ParseResult> paramList)
  {
    Collections.sort(paramList);
    ArrayList localArrayList = new ArrayList();
    ParseResult localParseResult = null;
    Iterator localIterator = paramList.iterator();
    paramList = localParseResult;
    if (!localIterator.hasNext()) {
      return localArrayList;
    }
    localParseResult = (ParseResult)localIterator.next();
    if (paramList == null) {
      localArrayList.add(localParseResult);
    }
    for (;;)
    {
      paramList = localParseResult;
      break;
      if ((paramList.getStartPositionInSentence() <= localParseResult.getStartPositionInSentence()) && (paramList.getEndPositionInSentence() >= localParseResult.getEndPositionInSentence())) {
        break;
      }
      localArrayList.add(localParseResult);
    }
  }
  
  private ParseResult parse(String paramString, int paramInt, List<Integer> paramList)
  {
    Object localObject = new ArrayList();
    Iterator localIterator = paramList.iterator();
    paramList = (List<Integer>)localObject;
    Integer localInteger;
    String str;
    do
    {
      do
      {
        if (!localIterator.hasNext())
        {
          if ((paramList != null) && (paramList.size() != 0)) {
            break;
          }
          return null;
        }
        localInteger = (Integer)localIterator.next();
      } while (patterns.get(localInteger.intValue())).leftProduction.startsWith("<??"));
      str = patterns.get(localInteger.intValue())).pattern;
      if (patterns.get(localInteger.intValue())).isRegularExpression) {
        break;
      }
      localObject = StringProcess.noRegexSplit(str, "&&&");
      segments = new ArrayList(20);
      segments_pattern = new ArrayList(20);
      localObject = parse(0, paramString, paramInt, localInteger.intValue(), (String[])localObject, 0, 0, 0, new HashSet());
    } while ((localObject == null) || (!((PartOfParseResult)localObject).getParseOK().booleanValue()));
    int i = segments.size() - 1;
    for (;;)
    {
      if (i < ((PartOfParseResult)localObject).getNextADDStartPositon())
      {
        if (currentStartRegular) {
          break label345;
        }
        return new ParseResult(ParseType.OnlyPass, 0, 0, (PatternForNER)patterns.get(localInteger.intValue()), paramString, segments, segments_pattern);
        localObject = new String[1];
        localObject[0] = str;
        segments = new ArrayList(1);
        segments_pattern = new ArrayList(1);
        break;
      }
      segments.set(i, null);
      segments_pattern.set(i, null);
      i -= 1;
    }
    label345:
    if (paramList == null) {
      paramList = new ArrayList();
    }
    for (;;)
    {
      paramList.add(new ParseResult(ParseType.OnlyPass, currentStartRegularStartIndexInSource, ((PartOfParseResult)localObject).getNextStartPositionInTarget() + currentStartRegularStartIndexInSource, (PatternForNER)patterns.get(localInteger.intValue()), paramString, segments, segments_pattern));
      break;
      return (ParseResult)nomalResults(paramList).get(0);
    }
  }
  
  private PartOfParseResult parse(int paramInt1, String paramString, int paramInt2, int paramInt3, String[] paramArrayOfString, int paramInt4, int paramInt5, int paramInt6, HashSet<String> paramHashSet)
  {
    if (paramInt4 >= paramArrayOfString.length)
    {
      if ((paramInt1 > 0) || (paramString.length() == 0) || ((paramInt1 == 0) && (currentStartRegular)))
      {
        localObject1 = new PartOfParseResult(Boolean.valueOf(true), paramInt6, paramInt2, paramString);
        return (PartOfParseResult)localObject1;
      }
      return null;
    }
    Object localObject2 = paramArrayOfString[paramInt4];
    int i;
    label102:
    label186:
    label252:
    label289:
    label314:
    int j;
    int k;
    if ((!patterns.get(paramInt3)).isRegularExpression) && (((String)localObject2).startsWith("<?")))
    {
      List localList = getCandPatternIndexByNonTerminal((String)localObject2, paramString);
      i = 0;
      if (i >= localList.size()) {
        return null;
      }
      if ((currentStartRegular) && (paramInt1 == 0) && (paramInt4 == 0)) {
        processedMostLeftRegularPrefix = false;
      }
      localObject2 = (PatternForNER)patterns.get(((Integer)localList.get(i)).intValue());
      HashSet localHashSet;
      if (!isRegularExpression)
      {
        localObject1 = StringProcess.noRegexSplit(pattern, "&&&");
        localHashSet = new HashSet();
        localObject1 = parse(paramInt1 + 1, paramString, paramInt2, ((Integer)localList.get(i)).intValue(), (String[])localObject1, 0, 0, paramInt6, localHashSet);
        if ((localObject1 != null) && (((PartOfParseResult)localObject1).getParseOK().booleanValue())) {
          break label314;
        }
        localObject1 = localHashSet.iterator();
        if (((Iterator)localObject1).hasNext()) {
          break label289;
        }
      }
      for (;;)
      {
        i += 1;
        break label102;
        localObject1 = new String[1];
        localObject1[0] = pattern;
        break label186;
        localObject2 = (String)((Iterator)localObject1).next();
        knowledge.remove(localObject2);
        break label252;
        j = ((PartOfParseResult)localObject1).getNextADDStartPositon();
        k = ((PartOfParseResult)localObject1).getNextStartPositionInTarget();
        extractKnowledge(patterns.get(paramInt3)).resultContent, segments, paramInt6, j, paramInt5, paramInt2, paramHashSet);
        localObject2 = parse(paramInt1, ((PartOfParseResult)localObject1).getLeftStr(), k, paramInt3, paramArrayOfString, paramInt4 + 1, paramInt5 + 1, j, localHashSet);
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (((PartOfParseResult)localObject2).getParseOK().booleanValue()) {
            break;
          }
        }
        localObject1 = localHashSet.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          knowledge.remove(localObject2);
        }
      }
    }
    Log.println("parsing:" + (String)localObject2);
    if ((!processedMostLeftRegularPrefix) || ((currentStartRegular) && (paramInt1 == 0) && (paramInt4 == 0)))
    {
      paramString = firstRegularResults(paramString, (String)localObject2, patterns.get(paramInt3)).isRegularExpression);
      processedMostLeftRegularPrefix = true;
      if (paramString == null) {
        return null;
      }
    }
    else
    {
      if ((!((String)localObject2).endsWith("<*>")) || (paramInt4 >= paramArrayOfString.length) || (!paramArrayOfString[(paramInt4 + 1)].startsWith("<?"))) {
        break label867;
      }
    }
    label867:
    for (Object localObject1 = paramArrayOfString[(paramInt4 + 1)];; localObject1 = null)
    {
      paramString = startWith(paramString, (String)localObject2, (String)localObject1, patterns.get(paramInt3)).isRegularExpression);
      break;
      if (!analyseConstraint(patterns.get(paramInt3)).constraintContent, paramString, paramInt5).booleanValue()) {
        return null;
      }
      extractKnowledge(patterns.get(paramInt3)).resultContent, paramString, paramInt5, paramInt2, paramHashSet);
      k = segments.size();
      if (!patterns.get(paramInt3)).isRegularExpression) {
        localObject1 = ((String)localObject2).split("\\+");
      }
      for (;;)
      {
        j = 0;
        i = paramInt2;
        paramInt2 = paramInt6;
        paramInt6 = j;
        if (paramInt6 < paramString.length - 1) {
          break;
        }
        return parse(paramInt1, paramString[(paramString.length - 1)], i, paramInt3, paramArrayOfString, paramInt4 + 1, paramString.length + paramInt5 - 1, paramInt2, paramHashSet);
        localObject1 = new String[1];
        localObject1[0] = localObject2;
      }
      i += paramString[paramInt6].length();
      if (paramInt2 < k)
      {
        segments_pattern.set(paramInt2, localObject1[paramInt6]);
        segments.set(paramInt2, paramString[paramInt6]);
        paramInt2 += 1;
      }
      for (;;)
      {
        paramInt6 += 1;
        break;
        segments_pattern.add(localObject1[paramInt6]);
        segments.add(paramString[paramInt6]);
        paramInt2 += 1;
      }
    }
  }
  
  private static boolean parseKeywordsSize(int paramInt, int[] paramArrayOfInt)
  {
    if (KeywordsType.isTypeEqual(paramInt, 1)) {
      paramArrayOfInt[0] += 1;
    }
    if (KeywordsType.isTypeEqual(paramInt, 8)) {
      paramArrayOfInt[3] += 1;
    }
    if (KeywordsType.isTypeEqual(paramInt, 4)) {
      paramArrayOfInt[2] += 1;
    }
    if (KeywordsType.isTypeEqual(paramInt, 2))
    {
      paramArrayOfInt[1] += 1;
      return true;
    }
    return false;
  }
  
  private String[] parserFunction(String paramString)
  {
    if ((paramString != null) && (!paramString.equals(""))) {
      return StringProcess.noRegexSplit(paramString, "_");
    }
    return null;
  }
  
  public static void printMemory(String paramString)
  {
    if (Log.localTest) {
      Log.println(paramString + " Memory : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - startMemory) / 1024L + " KB");
    }
  }
  
  private void recoverPosition(List<ParseResult> paramList, int paramInt)
  {
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return;
      }
      Object localObject = (ParseResult)paramList.next();
      ((ParseResult)localObject).setStartPositionInSentence(((ParseResult)localObject).getStartPositionInSentence() + paramInt);
      ((ParseResult)localObject).setEndPositionInSentence(((ParseResult)localObject).getEndPositionInSentence() + paramInt);
      localObject = knowledge.entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        if (((StringIntInt)localEntry.getValue()).getName() == null)
        {
          ((StringIntInt)localEntry.getValue()).setStartIndex(((StringIntInt)localEntry.getValue()).getStartIndex() + paramInt);
          ((StringIntInt)localEntry.getValue()).setEndIndex(((StringIntInt)localEntry.getValue()).getEndIndex() + paramInt);
        }
      }
    }
  }
  
  private boolean retainAll(Collection<Integer> paramCollection, int[] paramArrayOfInt)
  {
    boolean bool = false;
    paramCollection = paramCollection.iterator();
    for (;;)
    {
      if (!paramCollection.hasNext()) {
        return bool;
      }
      if (Bisection.search(((Integer)paramCollection.next()).intValue(), paramArrayOfInt) < 0)
      {
        paramCollection.remove();
        bool = true;
      }
    }
  }
  
  private void setPatKeyWords(PatternForNER paramPatternForNER, TreeMap<Integer, Integer> paramTreeMap, int[] paramArrayOfInt)
  {
    paramPatternForNER.setAllKeywords(paramTreeMap, paramArrayOfInt);
  }
  
  private static void setTempKeywords(Map<Integer, Integer> paramMap, int paramInt1, int paramInt2)
  {
    paramInt2 |= 0x1;
    Integer localInteger = (Integer)paramMap.get(Integer.valueOf(paramInt1));
    if (localInteger == null)
    {
      paramMap.put(Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
      return;
    }
    paramMap.put(Integer.valueOf(paramInt1), Integer.valueOf(localInteger.intValue() | paramInt2));
  }
  
  public static void setTimeOutThr(int paramInt)
  {
    timeOutThr = paramInt;
  }
  
  private String[] startWith(String paramString1, String paramString2, String paramString3)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString3 == null)
    {
      if (!nlp.patMatch(paramString1, paramString2 + "+<*>", localArrayList)) {
        return null;
      }
    }
    else
    {
      if (!nlp.patMatch(paramString1, paramString2, localArrayList)) {
        return null;
      }
      paramString2 = findFirstKeywordsByNoTerminal(paramString1, ((Integer)localArrayList.get(localArrayList.size() - 2)).intValue(), paramString3);
      if (paramString2 == null) {
        return null;
      }
      localArrayList.set(localArrayList.size() - 1, Integer.valueOf(paramString2[1]));
      localArrayList.add(Integer.valueOf(paramString1.length()));
    }
    int j = localArrayList.size();
    paramString2 = new String[j - 1];
    int i = 0;
    for (;;)
    {
      if (i >= j - 1) {
        return paramString2;
      }
      paramString2[i] = paramString1.substring(((Integer)localArrayList.get(i)).intValue(), ((Integer)localArrayList.get(i + 1)).intValue());
      i += 1;
    }
  }
  
  private String[] startWith(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (!paramBoolean) {
      paramString2 = startWith(paramString1, paramString2, paramString3);
    }
    do
    {
      return paramString2;
      paramString3 = Pattern.compile("^" + paramString2).matcher(paramString1);
      paramString2 = null;
    } while (!paramString3.find());
    paramString2 = new String[2];
    paramString2[0] = paramString3.group();
    if (paramString3.end() < paramString1.length())
    {
      paramString2[1] = paramString1.substring(paramString3.end());
      return paramString2;
    }
    paramString2[1] = "";
    return paramString2;
  }
  
  private int trimEnd(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3)
  {
    if (paramInt2 - paramInt1 <= paramInt3) {}
    int j;
    int m;
    int i;
    do
    {
      return paramInt2;
      j = 0;
      m = -1;
      i = 0;
      if (i < ((ArrayList)constant.get(paramString2)).size()) {
        break;
      }
    } while (m < 0);
    return paramInt2 - ((StringString)((ArrayList)constant.get(paramString2)).get(m)).getKeyWord().length();
    String str = ((StringString)((ArrayList)constant.get(paramString2)).get(i)).getKeyWord();
    int n;
    int k;
    if (!StringProcess.endWith(paramString1, paramInt2, str))
    {
      n = j;
      k = m;
    }
    for (;;)
    {
      i += 1;
      m = k;
      j = n;
      break;
      k = m;
      n = j;
      if (str.length() > j)
      {
        k = m;
        n = j;
        if (paramInt2 - paramInt1 - str.length() >= paramInt3)
        {
          n = str.length();
          k = i;
        }
      }
    }
  }
  
  private int trimEndAll(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3)
  {
    for (;;)
    {
      int i = trimEnd(paramString1, paramInt1, paramInt2, paramString2, paramInt3);
      if (i == paramInt2) {
        return i;
      }
      paramInt2 = i;
    }
  }
  
  private int trimStart(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3)
  {
    if (paramInt2 - paramInt1 <= paramInt3) {}
    int j;
    int m;
    int i;
    do
    {
      return paramInt1;
      j = 0;
      m = -1;
      i = 0;
      if (i < ((ArrayList)constant.get(paramString2)).size()) {
        break;
      }
    } while (m < 0);
    return paramInt1 + ((StringString)((ArrayList)constant.get(paramString2)).get(m)).getKeyWord().length();
    String str = ((StringString)((ArrayList)constant.get(paramString2)).get(i)).getKeyWord();
    int n;
    int k;
    if (!StringProcess.startWith(paramString1, paramInt1, str))
    {
      n = j;
      k = m;
    }
    for (;;)
    {
      i += 1;
      m = k;
      j = n;
      break;
      k = m;
      n = j;
      if (str.length() > j)
      {
        k = m;
        n = j;
        if (paramInt2 - paramInt1 - str.length() >= paramInt3)
        {
          n = str.length();
          k = i;
        }
      }
    }
  }
  
  private int trimStartAll(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3)
  {
    for (;;)
    {
      int i = trimStart(paramString1, paramInt1, paramInt2, paramString2, paramInt3);
      if (i == paramInt1) {
        return i;
      }
      paramInt1 = i;
    }
  }
  
  public int containInDic(ArrayList<StringString> paramArrayList, String paramString)
  {
    int k = 0;
    int j = paramArrayList.size() - 1;
    int i = (0 + j) / 2;
    int m;
    if (k > j) {
      m = -1;
    }
    do
    {
      return m;
      m = i;
    } while (((StringString)paramArrayList.get(i)).getKeyWord().equals(paramString));
    if (paramString.compareTo(((StringString)paramArrayList.get(i)).getKeyWord()) < 0) {
      j = i - 1;
    }
    for (;;)
    {
      i = (k + j) / 2;
      break;
      k = i + 1;
    }
  }
  
  public List<ParseResult> findKnowledge(String paramString, double paramDouble)
    throws Exception
  {
    Object localObject1 = paramString;
    if (!caseSensitive) {
      localObject1 = paramString.toLowerCase();
    }
    if ((patterns == null) || (patterns.size() == 0)) {
      return new ArrayList();
    }
    if (!findMust((String)localObject1)) {
      return new ArrayList();
    }
    targetSegment = null;
    timeoutStart = System.currentTimeMillis();
    if (segPunctuation.toLowerCase().equals("[null]")) {}
    for (paramString = new String[] { localObject1 }; paramString.length == 0; paramString = ((String)localObject1).split(segPunctuation)) {
      return new ArrayList();
    }
    localObject1 = new int[paramString.length];
    localObject1[0] = 0;
    int i = 1;
    ArrayList localArrayList;
    if (i > paramString.length)
    {
      localArrayList = new ArrayList();
      i = 0;
      if (i < paramString.length) {
        break label369;
      }
    }
    label369:
    Object localObject2;
    for (;;)
    {
      return localArrayList;
      if (i < paramString.length) {
        localObject1[i] = (localObject1[(i - 1)] + paramString[(i - 1)].length() + 1);
      }
      if ((!containStart) && (!containEnd)) {
        paramString[(i - 1)] = paramString[(i - 1)];
      }
      for (;;)
      {
        i += 1;
        break;
        if ((containEnd) && (!containStart)) {
          paramString[(i - 1)] = (paramString[(i - 1)] + PatternForNER.endPatternReplaceStr);
        } else if ((!containEnd) && (containStart)) {
          paramString[(i - 1)] = (PatternForNER.startPatternReplaceStr + paramString[(i - 1)]);
        } else {
          paramString[(i - 1)] = (PatternForNER.startPatternReplaceStr + paramString[(i - 1)] + PatternForNER.endPatternReplaceStr);
        }
      }
      localObject2 = findKnowledgeSeg(paramString[i], paramDouble);
      if ((localObject2 == null) || (((List)localObject2).size() == 0)) {}
      do
      {
        i += 1;
        break;
        recoverPosition((List)localObject2, localObject1[i]);
        if (replaceSeg) {
          getSegReplace((List)localObject2);
        }
        localObject2 = ((List)localObject2).iterator();
        if (((Iterator)localObject2).hasNext()) {
          break label459;
        }
      } while (!checkOneMatch(localArrayList));
    }
    label459:
    ParseResult localParseResult = (ParseResult)((Iterator)localObject2).next();
    if ((task == null) || (task.toLowerCase().equals("null"))) {
      localParseResult.setTask(StringProcess.trim(getPatternleftProduction, new Character[] { Character.valueOf('<'), Character.valueOf('>'), Character.valueOf('?') }));
    }
    for (;;)
    {
      localArrayList.add(localParseResult);
      break;
      localParseResult.setTask(task);
    }
  }
  
  public int getCandNextStartIndex(String paramString, int paramInt)
  {
    for (;;)
    {
      if (paramInt >= paramString.length() - 1) {
        return -1;
      }
      if (!StringProcess.ifCharTypeSame(paramString, paramInt, paramInt + 1)) {
        break;
      }
      paramInt += 1;
    }
    return paramInt + 1;
  }
  
  public boolean match(String paramString1, String paramString2)
  {
    return nlp.strEqual(paramString2, paramString1);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.Parser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */