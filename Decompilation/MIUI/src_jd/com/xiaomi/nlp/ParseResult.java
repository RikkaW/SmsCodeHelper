package com.xiaomi.nlp;

import com.xiaomi.common.StringIntInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseResult
  implements Comparable<ParseResult>
{
  private Double confidence;
  private int endPositionInSentence;
  public HashMap<String, StringIntInt> knowledge;
  private String parserName;
  private PatternForNER pattern;
  private ArrayList<String> segments;
  private ArrayList<String> segments_pattern;
  private List<String> segments_replace;
  private String source;
  private int startPositionInSentence;
  private String task;
  private ParseType type;
  
  public ParseResult(ParseType paramParseType, int paramInt1, int paramInt2, PatternForNER paramPatternForNER, String paramString, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2)
  {
    type = paramParseType;
    startPositionInSentence = paramInt1;
    endPositionInSentence = paramInt2;
    pattern = paramPatternForNER;
    source = paramString;
    segments = paramArrayList1;
    segments_pattern = paramArrayList2;
  }
  
  public int compareTo(ParseResult paramParseResult)
  {
    if (startPositionInSentence < startPositionInSentence) {}
    do
    {
      return -1;
      if (startPositionInSentence > startPositionInSentence) {
        return 1;
      }
      if (endPositionInSentence < endPositionInSentence) {
        return 1;
      }
    } while (endPositionInSentence > endPositionInSentence);
    return 0;
  }
  
  public Double getConfidence()
  {
    return confidence;
  }
  
  public int getEndPositionInSentence()
  {
    return endPositionInSentence;
  }
  
  public PatternForNER getPattern()
  {
    return pattern;
  }
  
  public ArrayList<String> getSegments()
  {
    return segments;
  }
  
  public ArrayList<String> getSegments_pattern()
  {
    return segments_pattern;
  }
  
  public List<String> getSegments_replace()
  {
    return segments_replace;
  }
  
  public String getSource()
  {
    return source;
  }
  
  public int getStartPositionInSentence()
  {
    return startPositionInSentence;
  }
  
  public String getTask()
  {
    return task;
  }
  
  public ParseType getType()
  {
    return type;
  }
  
  public void setConfidence(Double paramDouble)
  {
    confidence = paramDouble;
  }
  
  public void setEndPositionInSentence(int paramInt)
  {
    endPositionInSentence = paramInt;
  }
  
  public void setParserName(String paramString)
  {
    parserName = paramString;
  }
  
  public void setSegments_replace(List<String> paramList)
  {
    segments_replace = paramList;
  }
  
  public void setSource(String paramString)
  {
    source = paramString;
  }
  
  public void setStartPositionInSentence(int paramInt)
  {
    startPositionInSentence = paramInt;
  }
  
  public void setTask(String paramString)
  {
    task = paramString;
  }
  
  public void setType(ParseType paramParseType)
  {
    type = paramParseType;
  }
  
  public String toString()
  {
    return type + "\t" + source + "\t" + pattern.leftProduction + " ::= " + pattern.pattern + "\t" + segments.toString();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.ParseResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */