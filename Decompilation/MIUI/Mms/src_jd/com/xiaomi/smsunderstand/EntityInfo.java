package com.xiaomi.smsunderstand;

import com.xiaomi.common.StringProcess;

public class EntityInfo
  implements Comparable<EntityInfo>
{
  private static String nomalRegex = "[\\.\\-\\+\\s\\一]+";
  private double confidence;
  private int endPosition;
  private int engCharCount = -1;
  private EntityType entityType = EntityType.UNKNOWN;
  private int groupEndPosition;
  private String groupEntity;
  private int groupStartPosition;
  private int numberCount = -1;
  private String remark;
  private int startPosition;
  private String target;
  private String target_nomal;
  
  public static String nomal(String paramString)
  {
    return StringProcess.trim(paramString.replaceAll(nomalRegex, "").toLowerCase(), new Character[] { Character.valueOf('*'), Character.valueOf(' '), Character.valueOf('@'), Character.valueOf('.'), Character.valueOf('-'), Character.valueOf('&'), Character.valueOf('?'), Character.valueOf('='), Character.valueOf('_'), Character.valueOf('一') });
  }
  
  public int compareTo(EntityInfo paramEntityInfo)
  {
    if (startPosition < startPosition) {}
    do
    {
      return -1;
      if (startPosition > startPosition) {
        return 1;
      }
    } while (endPosition < endPosition);
    if (endPosition > endPosition) {
      return 1;
    }
    return 0;
  }
  
  public double getConfidence()
  {
    return confidence;
  }
  
  public int getEndPosition()
  {
    return endPosition;
  }
  
  public int getEngCharCount()
  {
    if (engCharCount < 0) {
      engCharCount = StringProcess.getEngCharCount(target);
    }
    return engCharCount;
  }
  
  public EntityType getEntityType()
  {
    return entityType;
  }
  
  public int getGroupEndPosition()
  {
    return groupEndPosition;
  }
  
  public String getGroupEntity()
  {
    return groupEntity;
  }
  
  public int getGroupStartPosition()
  {
    return groupStartPosition;
  }
  
  public int getNumberCount()
  {
    if (numberCount < 0) {
      numberCount = StringProcess.getNumberCount(target);
    }
    return numberCount;
  }
  
  public String getRemark()
  {
    return remark;
  }
  
  public int getStartPosition()
  {
    return startPosition;
  }
  
  public String getTarget()
  {
    return target;
  }
  
  public String getTarget_nomal()
  {
    return target_nomal;
  }
  
  public void noNomal()
  {
    target_nomal = target;
  }
  
  public void setConfidence(double paramDouble)
  {
    confidence = paramDouble;
  }
  
  public void setEndPosition(int paramInt)
  {
    endPosition = paramInt;
  }
  
  public void setEngCharCount(int paramInt)
  {
    engCharCount = paramInt;
  }
  
  public void setEntityType(EntityType paramEntityType)
  {
    entityType = paramEntityType;
  }
  
  public void setGroupEndPosition(int paramInt)
  {
    groupEndPosition = paramInt;
  }
  
  public void setGroupEntity(String paramString)
  {
    groupEntity = paramString;
  }
  
  public void setGroupStartPosition(int paramInt)
  {
    groupStartPosition = paramInt;
  }
  
  public void setNumberCount(int paramInt)
  {
    numberCount = paramInt;
  }
  
  public void setRemark(String paramString)
  {
    remark = paramString;
  }
  
  public void setStartPosition(int paramInt)
  {
    startPosition = paramInt;
  }
  
  public void setTarget(String paramString)
  {
    target = paramString;
  }
  
  public void setTargetNomal(String paramString)
  {
    target_nomal = paramString;
  }
  
  public void setTarget_nomal(String paramString)
  {
    target_nomal = nomal(paramString);
  }
  
  public void setTarget_nomalFLOW(String paramString)
  {
    target_nomal = paramString.toUpperCase();
    if ((target_nomal.contains("（")) || (target_nomal.contains("(")))
    {
      target_nomal = target_nomal.replace("（", "");
      target_nomal = target_nomal.replace("）", "");
      target_nomal = target_nomal.replace("(", "");
      target_nomal = target_nomal.replace(")", "");
    }
    int i;
    if (!target_nomal.contains("B")) {
      i = 0;
    }
    for (;;)
    {
      if (i >= target_nomal.length()) {}
      while ((target_nomal.charAt(i) == 'K') || (target_nomal.charAt(i) == 'M') || (target_nomal.charAt(i) == 'g'))
      {
        target_nomal = (target_nomal.substring(0, i) + "B" + target_nomal.substring(i));
        return;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.EntityInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */