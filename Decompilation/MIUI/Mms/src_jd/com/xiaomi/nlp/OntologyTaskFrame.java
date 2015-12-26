package com.xiaomi.nlp;

import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.util.Iterator;
import java.util.List;

public class OntologyTaskFrame
  extends Ontology
{
  protected List<ActionIdPeriod> actionIDs;
  private String area = "中国";
  private long endPeriodOfValidity = 4102416000000L;
  protected int operateID = -1;
  protected int priority = 0;
  private long startPeriodOfValidity = 1104508800000L;
  protected int summation = -1;
  protected EntityType taskType;
  
  public OntologyTaskFrame()
  {
    version = SMSUnderstand.getVersion();
    platform = 7;
  }
  
  public OntologyTaskFrame(List<ActionIdPeriod> paramList, String paramString, List<String> paramList1, List<Integer> paramList2)
  {
    actionIDs = paramList;
    ontologyName = paramString;
    setTaskType(paramString);
    attrNames = paramList1;
    attrType = paramList2;
    version = SMSUnderstand.getVersion();
    platform = 7;
  }
  
  public static String getFatherAttrName(String paramString)
  {
    int i = paramString.lastIndexOf("_");
    if (i >= 0) {
      return paramString.substring(0, i);
    }
    return "";
  }
  
  public int getActionID()
  {
    Iterator localIterator = actionIDs.iterator();
    ActionIdPeriod localActionIdPeriod;
    do
    {
      if (!localIterator.hasNext()) {
        return -1;
      }
      localActionIdPeriod = (ActionIdPeriod)localIterator.next();
    } while (!localActionIdPeriod.matchPeriod());
    return localActionIdPeriod.getActionId();
  }
  
  public int getAttrIndexByName(String paramString)
  {
    int i = 0;
    for (;;)
    {
      int j;
      if (i >= attrNames.size()) {
        j = -1;
      }
      do
      {
        return j;
        j = i;
      } while (((String)attrNames.get(i)).equals(paramString));
      i += 1;
    }
  }
  
  public int getAttrIndexByName(String paramString, int paramInt)
  {
    if ((attrNames.size() > paramInt) && (paramInt >= 0) && (((String)attrNames.get(paramInt)).equals(paramString))) {
      return paramInt;
    }
    return getAttrIndexByName(paramString);
  }
  
  public int getAttrIndexByType(int paramInt)
  {
    int i = 0;
    for (;;)
    {
      int j;
      if (i >= attrType.size()) {
        j = -1;
      }
      do
      {
        return j;
        j = i;
      } while (((Integer)attrType.get(i)).intValue() == paramInt);
      i += 1;
    }
  }
  
  public List<Integer> getAttrType()
  {
    return attrType;
  }
  
  public long getEndPeriodOfValidity()
  {
    return endPeriodOfValidity;
  }
  
  public int getFatherAttrIndex(String paramString)
  {
    return getAttrIndexByName(getFatherAttrName(paramString));
  }
  
  public int getOperateID()
  {
    return operateID;
  }
  
  public int getPriority()
  {
    return priority;
  }
  
  public long getStartPeriodOfValidity()
  {
    return startPeriodOfValidity;
  }
  
  public int getSummation()
  {
    return summation;
  }
  
  public EntityType getTaskType()
  {
    return taskType;
  }
  
  public boolean isEffective(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    if ((startPeriodOfValidity > l) || (endPeriodOfValidity < l)) {
      return false;
    }
    String[] arrayOfString;
    int j;
    int k;
    int i;
    if ((paramString1 != null) && (!area.equals("中国")))
    {
      arrayOfString = area.split("[,，]+");
      j = 0;
      k = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= k) {}
      for (i = j;; i = 1)
      {
        if (i == 0) {
          break label110;
        }
        if (matchVersion(paramInt1, paramInt2, paramString2) == null) {
          break;
        }
        return true;
        if (!paramString1.startsWith(arrayOfString[i])) {
          break label112;
        }
      }
      label110:
      break;
      label112:
      i += 1;
    }
  }
  
  public void setArea(String paramString)
  {
    area = paramString;
  }
  
  public void setEndPeriodOfValidity(long paramLong)
  {
    endPeriodOfValidity = paramLong;
  }
  
  public void setOperateID(int paramInt)
  {
    operateID = paramInt;
  }
  
  public void setPriority(int paramInt)
  {
    priority = paramInt;
  }
  
  public void setStartPeriodOfValidity(long paramLong)
  {
    startPeriodOfValidity = paramLong;
  }
  
  public void setSummation(int paramInt)
  {
    summation = paramInt;
  }
  
  public void setTaskType(String paramString)
  {
    if (paramString.equals("express"))
    {
      taskType = EntityType.EXPRESSNUMBER;
      return;
    }
    if (paramString.equals("verificationcode"))
    {
      taskType = EntityType.VERIFICATIONCODE;
      return;
    }
    if (paramString.equals("topup"))
    {
      taskType = EntityType.TOPUP;
      return;
    }
    if (paramString.equals("creditcardhuankuan"))
    {
      taskType = EntityType.CREDITCARDHUANKUAN;
      return;
    }
    if (paramString.equals("datetime"))
    {
      taskType = EntityType.TIME;
      return;
    }
    taskType = EntityType.SPECIALENTITY;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("defframe").append(" ").append(ontologyName).append("\r\n").append('{').append("\r\n");
    int i = 0;
    for (;;)
    {
      if (i >= getAttrCounts())
      {
        if (getActionID() >= 0) {
          localStringBuffer.append("\tactionID：").append(getActionID()).append("\r\n");
        }
        if (operateID >= 0) {
          localStringBuffer.append("\toperateID：").append(operateID).append("\r\n");
        }
        if (summation >= 0) {
          localStringBuffer.append("\tsummation：").append(summation).append("\r\n");
        }
        if (!version.equals(SMSUnderstand.getVersion())) {
          localStringBuffer.append("\tversion：").append(version).append("\r\n");
        }
        if (!area.equals("中国")) {
          localStringBuffer.append("\tarea：").append(area).append("\r\n");
        }
        String str = generalPeriod(startPeriodOfValidity, endPeriodOfValidity);
        if (str != null) {
          localStringBuffer.append("\tperiod：").append(str).append("\r\n");
        }
        if (platform != 7) {
          localStringBuffer.append("\tplatform：").append(platform).append("\r\n");
        }
        if (systemLevel != 1) {
          localStringBuffer.append("\tsystemLevel：").append(systemLevel).append("\r\n");
        }
        localStringBuffer.append('}').append("\r\n");
        return localStringBuffer.toString();
      }
      localStringBuffer.append("\t").append((String)attrNames.get(i)).append(65306).append(attrType.get(i)).append("\r\n");
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.OntologyTaskFrame
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */