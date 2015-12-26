package com.xiaomi.nlp;

import com.xiaomi.common.StringProcess;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Ontology
  extends VersionControl
{
  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  protected List<String> attrNames;
  protected List<Integer> attrType;
  protected String ontologyName;
  
  public static String generalPeriod(long paramLong1, long paramLong2)
  {
    StringBuffer localStringBuffer = new StringBuffer(23);
    localStringBuffer.append('[');
    Date localDate = new Date();
    if (paramLong1 != 1104508800000L)
    {
      localDate.setTime(paramLong1);
      localStringBuffer.append(sdf.format(localDate)).append(',');
      localDate.setTime(paramLong2);
      localStringBuffer.append(sdf.format(localDate));
    }
    for (;;)
    {
      localStringBuffer.append(']');
      return localStringBuffer.toString();
      if (paramLong2 == 4102416000000L) {
        break;
      }
      localDate.setTime(paramLong2);
      localStringBuffer.append(sdf.format(localDate));
    }
    return null;
  }
  
  public static long[] parsePeriod(String paramString)
  {
    String str = paramString;
    if (paramString.indexOf("period") >= 0) {
      str = paramString.substring(7);
    }
    paramString = StringProcess.trim(str, new Character[] { Character.valueOf('['), Character.valueOf(']') }).split("[,，]");
    long l1;
    switch (paramString.length)
    {
    default: 
      return null;
    case 1: 
      try
      {
        l1 = sdf.parse(paramString[0]).getTime();
        return new long[] { l1 };
      }
      catch (ParseException paramString)
      {
        return null;
      }
    }
    try
    {
      l1 = sdf.parse(paramString[0]).getTime();
      long l2 = sdf.parse(paramString[1]).getTime();
      return new long[] { l1, l2 };
    }
    catch (ParseException paramString) {}
    return null;
  }
  
  public int getAttrCounts()
  {
    return attrNames.size();
  }
  
  public List<String> getAttrNames()
  {
    return attrNames;
  }
  
  public List<Integer> getAttrType()
  {
    return attrType;
  }
  
  public String getOntologyName()
  {
    return ontologyName;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.Ontology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */