package com.xiaomi.nlp;

import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringProcess;
import java.util.ArrayList;
import java.util.List;

public class PatternActionContent
{
  private static String[] splitPat = { ":", "：" };
  public List<StringInt> contents;
  public PatternKeywords keywords;
  public String parameter;
  public String remark = null;
  
  public PatternActionContent(String paramString)
  {
    fillContent(StringProcess.noRegexSplit(paramString, splitPat));
  }
  
  private void fillContent(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length < 2)
    {
      Log.println("PattrenActionContent()参数错误：" + paramArrayOfString);
      keywords = PatternKeywords.OTHER;
      return;
    }
    paramArrayOfString[0] = paramArrayOfString[0].trim();
    paramArrayOfString[1] = paramArrayOfString[1].trim();
    if (paramArrayOfString[0].equals("NoNull")) {
      keywords = PatternKeywords.NoNull;
    }
    String[] arrayOfString;
    int i;
    for (;;)
    {
      arrayOfString = StringProcess.noRegexSplit(paramArrayOfString[1], "+");
      contents = new ArrayList(1);
      i = 0;
      if (i < arrayOfString.length) {
        break;
      }
      if (paramArrayOfString.length != 3) {
        break label965;
      }
      parameter = paramArrayOfString[2].trim();
      return;
      if (paramArrayOfString[0].equals("Remark"))
      {
        keywords = PatternKeywords.Remark;
      }
      else if (paramArrayOfString[0].equals("Score"))
      {
        keywords = PatternKeywords.Score;
      }
      else if (paramArrayOfString[0].equals("ContainPOS"))
      {
        keywords = PatternKeywords.ContainPOS;
      }
      else if (paramArrayOfString[0].equals("EndWithPOS"))
      {
        keywords = PatternKeywords.EndWithPOS;
      }
      else if (paramArrayOfString[0].equals("EndWithDic"))
      {
        keywords = PatternKeywords.EndWithDic;
      }
      else if (paramArrayOfString[0].equals("NoEndWithDic"))
      {
        keywords = PatternKeywords.NoEndWithDic;
      }
      else if (paramArrayOfString[0].equals("NoEndWithPOS"))
      {
        keywords = PatternKeywords.NoEndWithPOS;
      }
      else if (paramArrayOfString[0].equals("NoContainPOS"))
      {
        keywords = PatternKeywords.NoContainPOS;
      }
      else if (paramArrayOfString[0].equals("NoEqualDic"))
      {
        keywords = PatternKeywords.NoEqualDic;
      }
      else if (paramArrayOfString[0].equals("EqualDic"))
      {
        keywords = PatternKeywords.EqualDic;
      }
      else if (paramArrayOfString[0].equals("StartWithDic"))
      {
        keywords = PatternKeywords.StartWithDic;
      }
      else if (paramArrayOfString[0].equals("ContainDic"))
      {
        keywords = PatternKeywords.ContainDic;
      }
      else if (paramArrayOfString[0].equals("NoContainDic"))
      {
        keywords = PatternKeywords.NoContainDic;
      }
      else if (paramArrayOfString[0].equals("NoStartWithDic"))
      {
        keywords = PatternKeywords.NoStartWithDic;
      }
      else if (paramArrayOfString[0].equals("NoStartWithPOS"))
      {
        keywords = PatternKeywords.NoStartWithPOS;
      }
      else if (paramArrayOfString[0].equals("CharLenLess"))
      {
        keywords = PatternKeywords.CharLenLess;
      }
      else if (paramArrayOfString[0].equals("CharLenLonger"))
      {
        keywords = PatternKeywords.CharLenLonger;
      }
      else if (paramArrayOfString[0].equals("WordsLenLonger"))
      {
        keywords = PatternKeywords.WordsLenLonger;
      }
      else if (paramArrayOfString[0].equals("WordsLenLess"))
      {
        keywords = PatternKeywords.WordsLenLess;
      }
      else if (paramArrayOfString[0].equals("Accept"))
      {
        keywords = PatternKeywords.Accept;
      }
      else if (paramArrayOfString[0].equals("Reject"))
      {
        keywords = PatternKeywords.Reject;
      }
      else if (paramArrayOfString[0].equals("yyyy"))
      {
        keywords = PatternKeywords.yyyy;
      }
      else if (paramArrayOfString[0].equals("mm"))
      {
        keywords = PatternKeywords.mm;
      }
      else if (paramArrayOfString[0].equals("MM"))
      {
        keywords = PatternKeywords.MM;
      }
      else if (paramArrayOfString[0].equals("dd"))
      {
        keywords = PatternKeywords.dd;
      }
      else if (paramArrayOfString[0].equals("HH"))
      {
        keywords = PatternKeywords.HH;
      }
      else if (paramArrayOfString[0].equals("ss"))
      {
        keywords = PatternKeywords.ss;
      }
      else if (paramArrayOfString[0].startsWith("Ext_"))
      {
        keywords = PatternKeywords.Extract;
        remark = paramArrayOfString[0].substring(4);
      }
      else
      {
        keywords = PatternKeywords.OTHER;
      }
    }
    arrayOfString[i] = arrayOfString[i].trim();
    if (!arrayOfString[i].equals(""))
    {
      if ((!arrayOfString[i].startsWith("\"")) || (!arrayOfString[i].endsWith("\""))) {
        break label815;
      }
      contents.add(new StringInt(arrayOfString[i].substring(1, arrayOfString[i].length() - 1), -1));
    }
    for (;;)
    {
      i += 1;
      break;
      label815:
      int j = arrayOfString[i].indexOf("(");
      if (j < 0)
      {
        if (StringProcess.isNumber(arrayOfString[i])) {
          contents.add(new StringInt("", Integer.valueOf(arrayOfString[i]).intValue()));
        } else {
          contents.add(new StringInt(arrayOfString[i], -1));
        }
      }
      else
      {
        String str = arrayOfString[i].substring(0, j).toLowerCase();
        j = Integer.valueOf(arrayOfString[i].substring(j + 1, arrayOfString[i].length() - 1)).intValue();
        contents.add(new StringInt(str, Integer.valueOf(j).intValue()));
      }
    }
    label965:
    parameter = "";
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.PatternActionContent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */